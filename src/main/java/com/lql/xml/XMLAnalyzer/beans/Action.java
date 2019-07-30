package com.lql.xml.XMLAnalyzer.beans;

import com.lql.util.StringHelper;
import org.dom4j.Element;

import java.io.Serializable;

/**
 * Created by lql on 2017/5/25.
 * 操作Action的定义
 */
public class Action implements Serializable {
    /***
     * ==================定义命令类型========================
     */
    /***
     * 查询
     */
    public static final String SELECT = "SELECT";
    /***
     * 添加
     */
    public static final String INSERT = "INSERT";
    /***
     * 更新
     */
    public static final String UPDATE = "UPDATE";
    /***
     * 删除
     */
    public static final String DELETE = "DELETE";
    /***
     * web服务
     */
    public static final String SERVICE = "SERVICE";
    /***
     * 导入其他操作
     */
    public static final String IMPORT = "IMPORT";
    /***
     * 上传
     */
    public static final String UPLOAD = "UPLOAD";
//    /***
//     * 集合合并
//     */
//    public static final String LEFTJOIN = "LEFTJOIN";
//    /****
//     * 引入工具类
//     */
//    public static final String TOOLCLASS = "CLASS";
    /***
     * 定义变量
     */
    public static final String VAR = "VAR";
//    /***
//     * 取合集
//     */
//    public static final String UNION = "UNION";
//    /***
//     * 取交集
//     */
//    public static final String MIX = "MIX";
//    /***
//     * 交叉组合
//     */
//    public static final String CROSS = "CROSS";
    /***
     * 文件下载
     */
    public static final String DOWNLOAD = "DOWNLOAD";
    /***
     * 在磁盘创建文件
     */
    public static final String CREATEFILE = "CREATEFILE";
    /***
     * 在磁盘删除文件
     */
    public static final String DELETEFILE = "DELETEFILE";
    /***
     * 读取磁盘文件
     */
    public static final String READFILE = "READFILE";
    /***
     * 转换
     */
    public static final String CONVERT = "CONVERT";

    /***
     * 条件判断标签
     */
    public static final String IF = "IF";

    public static final String REST = "REST";
    /***
     * map 和 map 一对一合并
     */
//    public static final String ONE_TO_ONE_COMBIN = "ONE-TO-ONE-COMBINE";
    /***
     * 抛出异常
     */
    public static final String ERROR = "ERROR";
    /***
     * 提交现有事务,开启新的事务
     */
    public static final String COMMIT = "COMMIT";

    /*****
     * =======================定义操作的属性=============================================
     * /***
     * 通用属性
     */
    private String id = "";//唯一标识
    private String params;// 参数ID
    private boolean isList;// 是否依据参数批量执行
    private boolean isreturn;//是否把执行结果加入到返回值列表中
    private String cmdStr;// 操作的正文语句
    private String cmdType;//操作的类型
    private String content;// 操作的字符串

    private String propertyname;//上传文件对应的属性名
    private String iszip;       //文件上传后是否压缩或文件下载时是否压缩,多个文件同时下载默认压缩,压缩格式为zip
    private String isencrypt;   //文件上传后是否加密

    private String size = "0";//结果集大小
    private String range;// 行整体循环次数
    private String left;//左测数据源
    private String right;//右侧数据源
    private String on;// 左连接的共有列

    private String source;// import 的属性，指向导入的业务id

    private boolean required;// upload 属性是否在前端是必选字段

    private String expect;// 预期结果总数量

    private String savepath;// 保存的相对目录

    private String classpath;// 工具类路径

    private String method;//工具类方法

    private String value;// 给 var 命令定义value 属性

    private String dataset;// 数据集
    private String mixcol;// 取交集的col

    private String returntype;// 返回数据的方式

    private boolean ispaging;// 是否分页

    private String filename;// 文件类型,用来做磁盘文件创建

    private String test;// if标签的test属性

    private String columnname;//convert 命令要转换的列名

    private String errorid = "";//错误编号

    private String msg;//异常提示消息

    private boolean usegenname = true;//使用自动产生的文件名

    private String basesavepath;//文件上传，自定义的存储跟目录，不填该属性，默认使用系统的附件存储目录attachment

    private String contenttype = "STRING";//读取文件的正文类型

    private String abspath;//文件的绝对路径

    public String getAbspath() {
        return abspath;
    }

    public void setAbspath(String abspath) {
        this.abspath = abspath;
    }

    public String getContenttype() {
        return contenttype;
    }

    public void setContenttype(String contenttype) {
        this.contenttype = contenttype;
    }

    private boolean isdatedir = true;//默认true,在basesavepath+savepath后面自动根据日期产生的目录结构

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getColumnname() {
        return columnname;
    }

    public String getErrorid() {
        return errorid;
    }

    public void setErrorid(String errorid) {
        this.errorid = errorid;
    }

    public void setColumnname(String columnname) {
        this.columnname = columnname;
    }

    public boolean isUsegenname() {
        return usegenname;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setUsegenname(boolean usegenname) {
        this.usegenname = usegenname;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getClasspath() {
        return classpath;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setClasspath(String classpath) {
        this.classpath = classpath;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public boolean ispaging() {
        return ispaging;
    }

    public void setIspaging(boolean ispaging) {
        this.ispaging = ispaging;
    }

    public String getIsencrypt() {
        return isencrypt;
    }

    public void setIsencrypt(String isencrypt) {
        this.isencrypt = isencrypt;
    }

    public String getIszip() {
        return iszip;
    }

    public void setIszip(String iszip) {
        this.iszip = iszip;
    }

    public boolean isdatedir() {
        return isdatedir;
    }

    public void setIsdatedir(boolean isdatedir) {
        this.isdatedir = isdatedir;
    }

    /***
     * 命令执行的结果集
     */
    private Object result;

    public String getReturntype() {
        return returntype;
    }

    public void setReturntype(String returntype) {
        this.returntype = returntype;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public String getExpect() {
        return expect;
    }

    public void setExpect(String expect) {
        this.expect = expect;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public String getSavepath() {
        return savepath;
    }

    public void setSavepath(String savepath) {
        this.savepath = savepath;
    }

    public String getOn() {
        return on;
    }

    public void setOn(String on) {
        this.on = on;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public boolean isList() {
        return isList;
    }

    public void setList(boolean list) {
        isList = list;
    }

    public boolean isreturn() {
        return isreturn;
    }

    public void setIsreturn(boolean isreturn) {
        this.isreturn = isreturn;
    }

    public String getCmdStr() {
        return cmdStr;
    }

    public void setCmdStr(String cmdStr) {
        this.cmdStr = cmdStr;
    }

    public String getCmdType() {
        return cmdType;
    }

    public void setCmdType(String cmdType) {
        this.cmdType = cmdType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPropertyname() {
        return propertyname;
    }

    public void setPropertyname(String propertyname) {
        this.propertyname = propertyname;
    }

    public String getLeft() {
        return left;
    }

    public void setLeft(String left) {
        this.left = left;
    }

    public String getRight() {
        return right;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setRight(String right) {
        this.right = right;
    }

    public String getDataset() {
        return dataset;
    }

    public String getMixcol() {
        return mixcol;
    }

    public void setMixcol(String mixcol) {
        this.mixcol = mixcol;
    }

    public void setDataset(String dataset) {
        this.dataset = dataset;
    }

    public String getBasesavepath() {
        return basesavepath;
    }

    public void setBasesavepath(String basesavepath) {
        this.basesavepath = basesavepath;
    }

    public Action(Element element) {
        String cmdType = element.getName();
        String id = element.attributeValue("id");
        String params = element.attributeValue("params");
        String islist = element.attributeValue("islist");
        String isreturn = element.attributeValue("isreturn");
        String propertyname = element.attributeValue("propertyname");
        String iszip = element.attributeValue("iszip");
        iszip = StringHelper.isNotNull(iszip) ? iszip : "";
        String isencrypt = element.attributeValue("isencrypt");
        isencrypt = StringHelper.isNotNull(isencrypt) ? isencrypt : "";
        String left = element.attributeValue("left");
        String right = element.attributeValue("right");
        String on = element.attributeValue("on");
        String source = element.attributeValue("source");
        String content = element.asXML().toString();
        String cmdStr = element.getText();
        String required = element.attributeValue("required");
        String expect = element.attributeValue("expect");
        String savepath = element.attributeValue("savepath");
        String classpath = element.attributeValue("classpath");
        String method = element.attributeValue("method");
        String value = element.attributeValue("value");
        String returntype = element.attributeValue("returntype");
        String dataset = element.attributeValue("dataset");
        String mixcol = element.attributeValue("mixcol");
        String isPaging = element.attributeValue("ispaging");
        String filename = element.attributeValue("filename");
        String test = element.attributeValue("test");
        String range = element.attributeValue("range");
        String errorId = element.attributeValue("errorid");
        String msg = element.attributeValue("msg");
        String basesavepath = element.attributeValue("basesavepath");
        String isdatedir = element.attributeValue("isdatedir");
        String usegenname = element.attributeValue("usegenname");
        String contenttype = element.attributeValue("contenttype");
        String abspath = element.attributeValue("abspath");
        returntype = returntype == null ? "" : returntype;
        returntype = "null".equalsIgnoreCase(returntype) ? "" : returntype;


        range = range == null ? "" : range;
        range = "null".equalsIgnoreCase(range) ? "" : range;


        filename = filename == null ? "" : filename;
        filename = "null".equalsIgnoreCase(filename) ? "" : filename;

        basesavepath = basesavepath == null ? "" : basesavepath;
        basesavepath = "null".equalsIgnoreCase(basesavepath) ? "" : basesavepath;

        dataset = dataset == null ? "" : dataset;
        dataset = "null".equalsIgnoreCase(dataset) ? "" : dataset;
        mixcol = mixcol == null ? "" : mixcol;
        mixcol = "null".equalsIgnoreCase(mixcol) ? "" : mixcol;
        this.msg = msg;
        this.cmdType = cmdType;
        this.id = id;
        this.params = params;
        this.isList = islist == null ? false : Boolean.parseBoolean(islist);
        this.isreturn = isreturn == null ? false : Boolean.parseBoolean(isreturn);
        this.isdatedir = isdatedir == null ? true : Boolean.parseBoolean(isdatedir);
        this.usegenname = usegenname == null ? true : Boolean.parseBoolean(usegenname);
        this.on = StringHelper.isNotNull(on) ? on : "";
        this.propertyname = propertyname;
        this.left = left;
        this.right = right;
        this.content = content;
        this.cmdStr = cmdStr;
        this.source = source;
        this.required = required == null ? false : Boolean.parseBoolean(required);
        this.ispaging = isPaging == null ? false : Boolean.parseBoolean(isPaging);
        this.expect = expect;
        this.savepath = savepath;
        this.classpath = classpath;
        this.method = method;
        this.value = value;
        this.returntype = returntype;
        this.dataset = dataset;
        this.mixcol = mixcol;
        this.iszip = iszip;
        this.isencrypt = isencrypt;
        this.filename = filename;
        this.test = test;
        this.range = range;
        this.errorid = errorId;
        this.basesavepath = basesavepath;
        this.contenttype = contenttype == null ? this.contenttype : contenttype;
        this.abspath = abspath == null ? this.abspath : abspath;
    }
}
