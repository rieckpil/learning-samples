package de.rieckpil.learning;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.analyticsreporting.v4.AnalyticsReporting;
import com.google.api.services.analyticsreporting.v4.AnalyticsReportingScopes;
import com.google.api.services.analyticsreporting.v4.model.DateRange;
import com.google.api.services.analyticsreporting.v4.model.Dimension;
import com.google.api.services.analyticsreporting.v4.model.GetReportsRequest;
import com.google.api.services.analyticsreporting.v4.model.GetReportsResponse;
import com.google.api.services.analyticsreporting.v4.model.Metric;
import com.google.api.services.analyticsreporting.v4.model.ReportRequest;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class SampleReporting {
    private static final String APPLICATION_NAME = "Hello Analytics Reporting";
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final String KEY_FILE_LOCATION = "/tokens.json";
    private static final String VIEW_ID = System.getenv("VIEW_ID");

    public static void main(String[] args) throws GeneralSecurityException, IOException {
        AnalyticsReporting service = initializeAnalyticsReporting();
        GetReportsResponse response = getReport(service);
        printResponse(response);
    }

    private static AnalyticsReporting initializeAnalyticsReporting() throws GeneralSecurityException, IOException {
        HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        GoogleCredentials credentials = GoogleCredentials
                .fromStream(SampleReporting.class.getResourceAsStream(KEY_FILE_LOCATION))
                .createScoped(AnalyticsReportingScopes.all());

        HttpRequestInitializer requestInitializer = new HttpCredentialsAdapter(credentials);

        return new AnalyticsReporting.Builder(httpTransport, JSON_FACTORY, requestInitializer)
                .setApplicationName(APPLICATION_NAME).build();
    }

    private static GetReportsResponse getReport(AnalyticsReporting service) throws IOException {
        DateRange dateRange = new DateRange();
        dateRange.setStartDate("2019-10-21");
        dateRange.setEndDate("2019-10-21");

        Metric sessions = new Metric()
                .setExpression("ga:sessions")
                .setAlias("sessions");

        Dimension pageTitle = new Dimension().setName("ga:pageTitle");

        ReportRequest request = new ReportRequest()
                .setViewId(VIEW_ID)
                .setDateRanges(Arrays.asList(dateRange))
                .setMetrics(Arrays.asList(sessions))
                .setDimensions(Arrays.asList(pageTitle));

        var getReport = new GetReportsRequest().setReportRequests(List.of(request));

        return service.reports().batchGet(getReport).execute();
    }

    private static void printResponse(GetReportsResponse response) {
        response.getReports().stream()
                .map(report -> report.getData().getRows())
                .flatMap(List::stream)
                .map(reportRow -> new PageReporting(reportRow.getDimensions().get(0), Long.valueOf(reportRow.getMetrics().get(0).getValues().get(0))))
                .sorted(Comparator.reverseOrder())
                .forEach(System.out::println);
    }
}

class PageReporting implements Comparable<PageReporting> {
    String pageName;
    long amountOfSessions;

    public PageReporting(String pageName, long amountOfSessions) {
        this.pageName = pageName;
        this.amountOfSessions = amountOfSessions;
    }

    @Override
    public String toString() {
        return "PageReporting{" +
                "pageName='" + pageName + '\'' +
                ", amountOfSessions=" + amountOfSessions +
                '}';
    }

    @Override
    public int compareTo(PageReporting pageReporting) {
        return Long.compare(this.amountOfSessions, pageReporting.amountOfSessions);
    }
}
