package es.simplesw.aws.service;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClient;
import com.amazonaws.services.sqs.model.CreateQueueRequest;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import es.simplesw.aws.config.SqsConfig;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Service;

/**
 * Created by fjmora on 5/04/17.
 */
@Service
public class SqsService {

    public String createSQS(String queueName){
        AmazonSQS sqs = new AmazonSQSClient(SqsConfig.getAwsCredentials());
        CreateQueueRequest createQueueRequest = new CreateQueueRequest(queueName);
        return sqs.createQueue(createQueueRequest).getQueueUrl();
    }

    public String addSqsMessage(String sqsUrl, String message) {
        AmazonSQS sqs = new AmazonSQSClient(SqsConfig.getAwsCredentials());
        sqs.sendMessage(new SendMessageRequest(sqsUrl, message));
        return "message added.";
    }

    @MessageMapping("awsqueue1")
    public void queueListener(Object obj) {
        System.out.println("Test message received: " + obj);

    }
}
