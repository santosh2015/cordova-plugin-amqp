package org.cordova.amqpnotification;

import org.apache.cordova.CordovaActivity;

import java.lang.Runnable;
import java.lang.Excepion;

//rabbit
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.QueueingConsumer;

//org.amqp
import org.amqp.PushNotifiaction;
import org.amqp.PushReceiver;

class PushManager  {

    public void PushManager( CordovaActivity activity ) throw Exception {
        activity.runOnUiThread(new Runnable() throw Exception{
            public void run(){
                ConnectionFactory factory = new ConnectionFactory();
                factory.setHost("objetspartages.org");
                factory.setUsername('toto');
                factory.setPassword('toto');
                factory.setVirtualHost('toto');
                factory.setPort(5672);

                Connection connection = factory.newConnection();
                Channel channel = connection.createChannel();
                channel.queueDeclare('hello', false, false, false, null);
                QueueingConsumer consumer = new QueueingConsumer(channel);
                channel.basicConsume('hello', true, consumer);
                while (true) {
                    delivery = consumer.nextDelivery();
                    String message = new String(delivery.getBody());
                    PushNotification notification = new PushNotification(message);
                    PushReceiver.onNotificationReceived(notification);
                }
            }        
        }
    }

}