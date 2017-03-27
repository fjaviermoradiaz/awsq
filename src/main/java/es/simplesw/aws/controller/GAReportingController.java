package es.simplesw.aws.controller;

import com.google.api.services.analyticsreporting.v4.AnalyticsReporting;
import com.google.api.services.analyticsreporting.v4.model.GetReportsResponse;
import es.simplesw.aws.util.GAUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by fjmora on 27/03/17.
 */
@RestController
public class GAReportingController {



    @RequestMapping("/ga")
    public String getReporting() {
        try {
            AnalyticsReporting service = GAUtil.initializeAnalyticsReporting();

            GetReportsResponse response = GAUtil.getReport(service);
            GAUtil.printResponse(response);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "Pending development";
    }

}
