//package com.lql.db.mongodb;
//
//import com.mongodb.MongoClient;
//import com.mongodb.client.FindIterable;
//import com.mongodb.client.MongoCollection;
//import com.mongodb.client.MongoCursor;
//import com.mongodb.client.MongoDatabase;
//import org.bson.Document;
//
///**
// * Created by lql on 2018/11/28 14:05
// **/
//public class MongoTest {
//    public static void main(String[] args) {
//        MongoClient mongoClient = new MongoClient("localhost",27017);
//        MongoDatabase mongoDatabase = mongoClient.getDatabase("admin");
//        MongoCollection<Document> collection = mongoDatabase.getCollection("col");
//        //检索所有文档
//        /**
//         * 1. 获取迭代器FindIterable<Document>
//         * 2. 获取游标MongoCursor<Document>
//         * 3. 通过游标遍历检索出的文档集合
//         * */
//        FindIterable<Document> findIterable = collection.find();
//        MongoCursor<Document> mongoCursor = findIterable.iterator();
//        while(mongoCursor.hasNext()){
//            System.out.println(mongoCursor.next());
//        }
//    }
//}
