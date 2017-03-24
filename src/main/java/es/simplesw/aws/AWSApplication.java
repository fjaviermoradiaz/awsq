package es.simplesw.aws;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;

import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClient;
import com.amazonaws.services.sqs.model.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by fjmora on 24/03/17.
 */
@SpringBootApplication
public class AWSApplication {
    public static void main(String[] args) {
        SpringApplication.run(AWSApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            AWSCredentials credentials = null;

            try {
                credentials = new ProfileCredentialsProvider().getCredentials();
            } catch (Exception e) {
                throw new AmazonClientException(
                        "Cannot load the credentials from the credential profiles file. " +
                                "Please make sure that your credentials file is at the correct " +
                                "location (~/.aws/credentials), and is in valid format.",
                        e
                );
            }

            AmazonSQS sqs = new AmazonSQSClient(credentials);
            Region usWest2 = Region.getRegion(Regions.US_WEST_2);
            sqs.setRegion(usWest2);

            System.out.println("===========================================");
            System.out.println("Getting Started with Amazon SQS");
            System.out.println("===========================================\n");

            try {

                // Create queue
//                System.out.println("Creating a new SQS queue called MyQueue. \n");
//                CreateQueueRequest createQueueRequest = new CreateQueueRequest("MyQueue");
//                String myQueueUrl = sqs.createQueue(createQueueRequest).getQueueUrl();

                // List queue
                System.out.println("Listing all queues in your account. \n");
                for(String queueUrl : sqs.listQueues().getQueueUrls()) {
                    System.out.println("    QueueUrl: " + queueUrl);
                }
                System.out.println();

                // Send message
//                System.out.println("Sending a message to MyQueue. \n");
//                sqs.sendMessage(new SendMessageRequest(myQueueUrl, "This is my new message in my queue"));
//
//                // Receive message
//                System.out.println("Receiving messages from MyQueue. \n");
//                ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest(myQueueUrl);
//                List<Message> messages = sqs.receiveMessage(receiveMessageRequest).getMessages();
//                for(Message message: messages){
//                    System.out.println("    Message:");
//                    System.out.println("        MessageId:     " + message.getMessageId());
//                    System.out.println("        ReceiptHandle: " + message.getReceiptHandle());
//                    System.out.println("        MD5OfBody:     " + message.getMD5OfBody());
//                    System.out.println("        Body:          " + message.getBody());
//                    for(Map.Entry<String, String> entry : message.getAttributes().entrySet()){
//                        System.out.println("    Attribute");
//                        System.out.println("        Name:  " + entry.getKey());
//                        System.out.println("        Value: " + entry.getValue());
//                    }
//                }
//                System.out.println();
//
//                // Delete a message
//                System.out.println("Deleting a message. \n");
//                sqs.deleteQueue(new DeleteQueueRequest(myQueueUrl));
            } catch (AmazonServiceException ase) {
                System.out.println("Caught an AmazonServiceException, which means your request made it " +
                        "to Amazon SQS, but was rejected with an error response for some reason.");
                System.out.println("Error Message:    " + ase.getMessage());
                System.out.println("HTTP Status Code: " + ase.getStatusCode());
                System.out.println("AWS Error Code:   " + ase.getErrorCode());
                System.out.println("Error Type:       " + ase.getErrorType());
                System.out.println("Request ID:       " + ase.getRequestId());
            } catch (AmazonClientException ace) {
                System.out.println("Caught an AmazonClientException, which means the client encountered " +
                        "a serious internal problem while trying to communicate with SQS, such as not " +
                        "being able to access the network.");
                System.out.println("Error Message: " + ace.getMessage());
            }

        };
    }
}
