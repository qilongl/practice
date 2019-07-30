package com.lql.xml.XMLAnalyzer.service.impl;

import com.lql.xml.XMLAnalyzer.beans.Action;
import com.lql.xml.XMLAnalyzer.beans.BusiConfig;
import com.lql.xml.XMLAnalyzer.beans.Parameters;
import com.lql.xml.XMLAnalyzer.beans.UserException;
import com.lql.xml.XMLAnalyzer.service.IBusiService;
import com.lql.xml.XMLAnalyzer.util.ExceptionUtil;
import com.lql.xml.beans.SysProperties;
import com.lql.xml.db.DPFactory;
import com.lql.xml.db.DPService;
import com.lql.util.ResponseResult;
import com.lql.util.StringHelper;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.Serializable;
import java.util.*;

/**
 * Created by lql on 2017/5/25.
 */
@Component
@Service
@Scope("prototype")
public class BusiService implements IBusiService, Serializable {
    @Autowired
    private BusiConfigCache busiConfigCache;

    private static Logger logger = LoggerFactory.getLogger(BusiService.class);
    //系统参数定义
    private Parameters systemParameters = new Parameters();

    /****
     * 执行前端传递的参数和数据流
     *
     * @param jsonparam
     * @param fileMap
     * @return
     */
    @Override
    public ResponseResult exec(String jsonparam, Map<String, List<Map<String, byte[]>>> fileMap) {
        /***
         * 返回对象
         */
        ResponseResult rs = new ResponseResult();
        /***
         * 返回对象的结果
         */
        Map<String, Map<String, Object>> resultObject = new HashMap();
        /****
         * 执行业务操作
         */
        // 解析出functions
        JSONObject paramsObject = JSONObject.fromObject(jsonparam);
        JSONArray functionArray = paramsObject.getJSONArray("Functions");
        // 是否多数据源
//        boolean ismutiDataSource = isMutiDataSource(functionArray);
        // 该次请求都是一个数据源
        String moduleName = null;
        String functionName = null;
        String dataSourceName = null;
//        DataSource dataSourceObject = null;
        DPService dpService = null;
        BusiConfig bsconfig = null;
        Calendar beginTime = Calendar.getInstance();
        try {
            try {
                for (int i = 0; i < functionArray.size(); i++) {
                    JSONObject functionObject = functionArray.getJSONObject(i);
                    // 通过模块名和功能名从配置字典中取出业务配置类
                    moduleName = functionObject.getString("ModuleName");
                    functionName = functionObject.getString("FunctionName");
                    JSONObject parameters = functionObject.getJSONObject("Parameters");
                    String key = moduleName + "-" + functionName;
                    if (!BusiConfigCache.configDic.containsKey(key))
                        reloadFromDisk(key);
                    if (!BusiConfigCache.configDic.containsKey(key))
                        throw new UnsupportedOperationException("请求不存在的服务接口" + key);
                    logger.debug("请求路径:" + moduleName + "-" + functionName + ".线程-" + Thread.currentThread().getId());
                    /***
                     * 开发环境则重新加载xml业务配置文件
                     */
                    if (!"true".equalsIgnoreCase(SysProperties.getIsProduction()))
                        reloadFromDisk(key);
                    /***
                     * 执行业务
                     */
                    bsconfig = BusiConfigCache.configDic.get(key).myClone();
                    /***
                     * 第一次执行,初始化数据操作服务,启动事务
                     */
                    if (dpService == null) {
                        dataSourceName = bsconfig.getDataSource();
                        if (!StringHelper.isNotNull(dataSourceName))
                            throw new UnsupportedOperationException("接口" + key + "的dataSourceName配置为空！");
                        dpService = DPFactory.createDPService(BusiConfigCache.ctx, dataSourceName);
                        dpService.startTransaction();
                    }
                    /***
                     * 其次判断是否和上次的数据源相同,
                     *  如果相同则继续在之前的事务中执行,
                     *  如果不同则提交当前事务,重新创建事务管理器,开启事务,执行操作
                     */
                    else {
                        if (!bsconfig.getDataSource().equals(dataSourceName)) {
                            dpService.commit();
                            dataSourceName = bsconfig.getDataSource();
                            dpService = DPFactory.createDPService(BusiConfigCache.ctx, dataSourceName);
                            dpService.startTransaction();
                        }
                    }
                    /***
                     * 设置数据源操作类
                     */
                    bsconfig.setDpService(dpService);
                    Map<String, Object> result = bsconfig.exec(parameters, fileMap);
                    dpService = bsconfig.getDpService();
                    resultObject.put(key, result);
                    rs.setStatusCode(ResponseResult.RESULT_STATUS_CODE_SUCCESS);
                    rs.setMsg("业务执行成功！");
                    Calendar endTime = Calendar.getInstance();
                    logger.debug(key + "耗时：" + (endTime.getTimeInMillis() - beginTime.getTimeInMillis()) + "ms");
                }
                /****
                 * 整个业务操作集合执行完毕,提交事务
                 */
                if (dpService == null)
                    throw new UnsupportedOperationException(jsonparam + "传参错误，导致无法获取数据操作类！");
                dpService.commit();
            } catch (UserException e) {
                logger.error(moduleName + "-" + functionName + "的" + e.getId() + "业务执行异常，异常编码:" + e.getErrorId() + ",操作撤销！", e.getMessage());
                rs.setStatusCode(e.getErrorId());
                rs.setMsg(e.getMessage());
                if (null != bsconfig)
                    dpService = bsconfig.getDpService();
                if (null != dpService)
                    dpService.rollback();
            }
        } catch (Exception ex) {
            //执行失败，回滚事务
            ex.printStackTrace();
            logger.error(moduleName + "-" + functionName + "业务执行异常,操作撤销！", ExceptionUtil.getErrorInfoFromException(ex));
            rs.setStatusCode(ResponseResult.RESULT_STATUS_CODE_ERROR);
            rs.setMsg(moduleName + "-" + functionName + "业务执行时发生错误！" + ExceptionUtil.getErrorInfoFromException(ex));
            /****
             * 执行过程中出现异常,回滚事务
             */
            if (null != bsconfig)
                dpService = bsconfig.getDpService();
            if (null != dpService)
                dpService.rollback();
        }
        rs.setResult(resultObject);
        /***
         * 释放缓存资源
         */
        release(jsonparam, fileMap);
        return rs;
    }

    /****
     * 释放资源
     *
     * @param jsonparams
     * @param fileMap
     */
    private void release(String jsonparams, Map<String, List<Map<String, byte[]>>> fileMap) {
        jsonparams = null;
        if (null != fileMap)
            fileMap.clear();
        fileMap = null;
    }

    /***
     * 查看当前请求是否包含多个数据源
     *
     * @param functionArray
     * @return
     */

    private boolean isMutiDataSource(JSONArray functionArray) {
        List<String> dataSourceList = new ArrayList<>();
        Map<String, Object> mutiDic = new HashMap();
        for (int i = 0; i < functionArray.size(); i++) {
            JSONObject functionObject = functionArray.getJSONObject(i);
            String moduleName = functionObject.getString("ModuleName");
            String functionName = functionObject.getString("FunctionName");
            String key = moduleName + "-" + functionName;
            BusiConfig bsconfig = BusiConfigCache.configDic.get(key);
            /**
             * 取到所有的数据源
             */
            getAllDataSourceName(bsconfig, dataSourceList, mutiDic);
        }
        String firstDataSource = null;
        for (int i = 0; i < dataSourceList.size(); i++) {
            String dataSource = dataSourceList.get(i);
            if (firstDataSource == null)
                firstDataSource = dataSource;
            else if (!dataSource.equals(firstDataSource))
                return true;
        }
        return false;
    }

    /***
     * 从busiconfig 中取得所有的数据源
     *
     * @param busiConfig
     * @return
     */
    public void getAllDataSourceName(BusiConfig busiConfig, List<String> dataSourceList, Map<String, Object> mutiDic) {
        dataSourceList.add(busiConfig.getDataSource());
        if (mutiDic.containsKey(busiConfig.getId()))
            throw new IndexOutOfBoundsException("存在循环调用，请检查业务配置文件的引用关系!");
        mutiDic.put(busiConfig.getId(), busiConfig);
        List<Action> actionList = busiConfig.getCmdList();
        for (int i = 0; i < actionList.size(); i++) {
            Action action = actionList.get(i);
            if (action.getCmdType().equals(Action.IMPORT)) {
                String target = action.getSource();
                BusiConfig bsconfig = BusiConfigCache.configDic.get(target);
                getAllDataSourceName(bsconfig, dataSourceList, mutiDic);
            }
        }
    }

    /***
     * 重新加载业务配置文件
     */
    public void reloadBusiConfig(String key, DPService dpService) throws Exception {
        /***
         * 开发环境从磁盘加载该业务文件到内存
         */
        if (!"true".equalsIgnoreCase(SysProperties.getIsProduction())) {
            reloadFromDisk(key);
        } else {
            /****
             * 生产环境从数据库中加载业务文件到内存
             */
            reloadFromDb(key, dpService);
        }
    }

    /***
     * 自身数据源加载业务文件
     *
     * @param key
     * @throws Exception
     */
    public void reloadFunction(String key) throws Exception {
        DPService dpService = DPFactory.createDPService(BusiConfigCache.ctx, SysProperties.DEFAULT_DATASOURCE);
        try {
            dpService.startTransaction();
            reloadBusiConfig(key, dpService);
        } catch (Exception ex) {
            dpService.rollback();
            throw ex;
        }
    }

    private void reloadFromDisk(String key) throws Exception {
        String path = key.replace("-", File.separator);
        String sysBaseDir = SysProperties.getSysBaseDir();
        String root = sysBaseDir.substring(0, sysBaseDir.lastIndexOf(File.separator));
        path = root + File.separator + path + ".xml";
        // TODO: 2018/5/31 修改为通过file方式读取
        busiConfigCache.resetBusiConfig(key, new File(path));
    }

    private void reloadFromDb(String key, DPService dpService) throws Exception {
        List<Map<String, Object>> list = busiConfigCache.getFunctionByUrl(key, dpService);
        if (list.size() == 1) {
            Map<String, Object> objectMap = list.get(0);
            byte[] buf = (byte[]) objectMap.get("SOURCE");
            BufferedInputStream inputStream = new BufferedInputStream(new ByteArrayInputStream(buf));
            // TODO: 2018/5/31 把fileinputstream方式读取xml调整为file方式
            String filepath = SysProperties.getAttachmentPath() + File.separator + StringHelper.getUUID();
            busiConfigCache.writeToLocal(filepath, inputStream);
            File file = new File(filepath);
            busiConfigCache.resetBusiConfig(key, file);
            busiConfigCache.removeFile(filepath);
            // TODO: 2018/5/31  ok
        } else if (list.size() == 0) {
            throw new UnsupportedOperationException("请求不存在的接口:" + key);
        } else {
            throw new UnsupportedOperationException("数据库中找到" + key + "冗余记录");
        }
    }

    public static void main(String args[]) throws Exception {


    }
}
