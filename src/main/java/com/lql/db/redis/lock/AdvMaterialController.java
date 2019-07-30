//package com.lql.redis;
//
//import com.lql.util.StringHelper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.Map;
//import java.util.concurrent.TimeUnit;
//
//@RestController
//@RequestMapping("/redisson")
//public class AdvMaterialController {
//    @Autowired
//    private DistributedLocker distributedLocker;
//
//    @RequestMapping("/test")
//    public void redissonTest() {
//        String key = "key";
////        for (int i = 0; i < 10; i++) {
////            Thread t = new Thread(new Runnable() {
////                @Override
////                public void run() {
//                    try {
//                        System.err.println("=============线程开启============" + Thread.currentThread().getName());
//                    /*distributedLocker.lock(key,10L); //直接加锁，获取不到锁则一直等待获取锁
//                     Thread.sleep(100); //获得锁之后可以进行相应的处理
//					 System.err.println("======获得锁后进行相应的操作======"+Thread.currentThread().getName());
//					 distributedLocker.unlock(key);  //解锁
//					 System.err.println("============================="+Thread.currentThread().getName());*/
//                        boolean isGetLock = distributedLocker.tryLock(key, TimeUnit.SECONDS, 5L, 10L); //尝试获取锁，等待5秒，自己获得锁后一直不解锁则10秒后自动解锁
//                        if (isGetLock) {
//                            Thread.sleep(100); //获得锁之后可以进行相应的处理
//                            System.err.println("======获得锁后进行相应的操作======" + Thread.currentThread().getName());
//                            //todo
//                            System.out.println("哈撒给，面对疾风吧！");
//                            //distributedLocker.unlock(key);
//                            System.err.println("=============================" + Thread.currentThread().getName());
//                            distributedLocker.unlock(key);
//                        }else{
//                            System.err.println("未获取到锁"+ Thread.currentThread().getName());
//                            redissonTest();
//                        }
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
////            });
////            t.start();
////        }
////    }
//}
