package com.lql.astheroadtogod;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class TopicConsumer {

    public static void main(String[] args) {
        //连接信息设置
        String username = "system";
        String password = "manager";
        String brokerURL = "failover://tcp://localhost:61616";
        //连接工厂
        ConnectionFactory connectionFactory = null;
        //连接
        Connection connection = null;
        //会话 接受或者发送消息的线程
        Session session = null;
        //主题的目的地
        Topic topic = null;
        //主题消费者
        MessageConsumer messageConsumer = null;
        //实例化连接工厂
        connectionFactory = new ActiveMQConnectionFactory(username, password, brokerURL);

        try {
            //通过连接工厂获取连接
            connection = connectionFactory.createConnection();
            //启动连接
            connection.start();
            //创建session
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            //创建一个连接TopicTest的主题
            topic = session.createTopic("TopicTest");  
            //创建主题消费者
            messageConsumer = session.createConsumer(topic);

            messageConsumer.setMessageListener(new MyMessageListener());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

}

class MyMessageListener implements MessageListener {

    @Override
    public void onMessage(Message message) {  
        TextMessage textMessage = (TextMessage) message;  
        try {  
            System.out.println("接收订阅主题：" + textMessage.getText());  
        } catch (JMSException e) {  
            e.printStackTrace();  
        }  
    } 

}