package es.simplesw.aws.config;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import org.springframework.context.annotation.Configuration;

/**
 * Created by fjmora on 28/03/17.
 */
@Configuration
public class SqsConfig {

    private static AWSCredentials credentials = null;

    public static AWSCredentials getAwsCredentials(){

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



}
