package es.simplesw.aws.controller;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClient;
import com.amazonaws.services.sqs.model.CreateQueueRequest;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by fjmora on 27/03/17.
 */
@RestController
public class SQSController {

    private static AWSCredentials credentials = null;

    private AWSCredentials getAwsCredentials(){

        try {
            if(credentials == null)
                credentials = new ProfileCredentialsProvider().getCredentials();
        } catch (Exception e) {
            throw new AmazonClientException(
                    "Cannot load the credentials from the credential profiles file. " +
                            "Please make sure that your credentials file is at the correct " +
                            "location (~/.aws/credentials), and is in valid format.",
                    e
            );
        }

        return credentials;
    }

    /**
     * Create AWS queue
     * @return
     */
    @RequestMapping("/sqs/create")
    public String createSqs(){
        AmazonSQS sqs = new AmazonSQSClient(credentials);
        CreateQueueRequest createQueueRequest = new CreateQueueRequest("awsqueue1");
        return sqs.createQueue(createQueueRequest).getQueueUrl();
    }

    @RequestMapping("/sqs/message")
    public String addSqsMessage(String sqsUrl, String message) {
        AmazonSQS sqs = new AmazonSQSClient(credentials);
        sqs.sendMessage(new SendMessageRequest(sqsUrl, message));
        return "message added.";
    }
}
