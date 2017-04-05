package es.simplesw.aws.component;

import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * Created by fjmora on 5/04/17.
 */
@Component
public class SQSListener implements MessageListener {

    public void onMessage(Message message) {

        TextMessage textMessage = (TextMessage) message;

        try {
            System.out.println("Received message "+ textMessage.getText());
        } catch (JMSException e) {
            System.out.println("Error processing message " + e);
        }
    }
}
