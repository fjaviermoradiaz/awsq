package es.simplesw.aws.controller;

import es.simplesw.aws.service.SqsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by fjmora on 27/03/17.
 */
@RestController
public class SqsController {

    @Autowired
    private SqsService sqsService;
    /**
     * Create AWS queue
     * @return
     */
    @RequestMapping("/sqs/create")
    public String createSqs(){
        return sqsService.createSQS("awsqueue1");
    }

    @RequestMapping("/sqs/message")
    public String addSqsMessage(String sqsUrl, String message) {
        sqsService.addSqsMessage(sqsUrl,message);
        return "message added.";
    }
}
