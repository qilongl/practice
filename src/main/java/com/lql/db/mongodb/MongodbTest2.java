//package com.lql.db.mongodb;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.mongodb.core.FindAndModifyOptions;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.data.mongodb.core.query.Criteria;
//import org.springframework.data.mongodb.core.query.Query;
//import org.springframework.data.mongodb.core.query.Update;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.Date;
//import java.util.List;
//
//import static com.sun.xml.internal.ws.util.JAXWSUtils.getUUID;
//
///**
// * Created by StrangeDragon on 2019/5/28 15:52
// **/
//@RestController
//@RequestMapping("/mongodb")
//public class MongodbTest2 {
//    @Autowired
//    private MongoTemplate mongoTemplate;
//
//    @RequestMapping("/test/{num}")
//    public void redissonTest(@PathVariable("num") int num) {
//        String key = "key";
//        for (int i = 0; i < num; i++) {
//            Thread t = new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        Query query = new Query();
//                        query.addCriteria(Criteria.where("name").is("张三"));
//                        query.addCriteria(Criteria.where("age").is(18));
//                        List<T_CHA_TEST> test = mongoTemplate.find(query, T_CHA_TEST.class);
//                        for (int j = 0; j < test.size(); j++) {
//                            System.err.println(Thread.currentThread().getName() + "\t" + test.get(j));
//
//                        }
//                        Update update = new Update().set("qq", getUUID()).set("time",new Date());
//                        FindAndModifyOptions options = new FindAndModifyOptions();
//                        options.upsert(true);//允许不存在时新增
////                    options.returnNew(true);//true 返还更新后的文档值
//                        // 先查询，如果没有符合条件的，会执行插入，插入的值是查询值 ＋ 更新值
//                        T_CHA_TEST result = mongoTemplate.findAndModify(query, update, options, T_CHA_TEST.class);
//                        System.out.println(Thread.currentThread().getName()+"\t" +result);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//            });
//            t.start();
//        }
//    }
//}
