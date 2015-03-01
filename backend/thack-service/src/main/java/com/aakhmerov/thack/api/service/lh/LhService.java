package com.aakhmerov.thack.api.service.lh;

import com.aakhmerov.thack.api.service.lh.jackson.LhNameStrategy;
import com.aakhmerov.thack.api.service.lh.tos.LHFlightSearchRequestTO;
import com.aakhmerov.thack.api.service.lh.tos.LHFlightsTO;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by aakhmerov on 01.03.15.
 */
@Service
public class LhService {
    private static final Logger logger = LoggerFactory.getLogger(LhService.class);

    @Value("${lufthansa.password}")
    private String password;

    @Value("${lufthansa.endpoint}")
    private String endpoint;

    @Value("${lufthansa.username}")
    private String username;

    @Value("${lufthansa.environment}")
    private String environment;


    public LHFlightsTO getFlights(String source, String destination, DateTime dateTime) {
        LHFlightsTO result = new LHFlightsTO();
        ObjectMapper objectMapper = getObjectMapper();
        objectMapper.setPropertyNamingStrategy(new LhNameStrategy());
        LHFlightSearchRequestTO toPost = prepareData(source,destination,dateTime);


        HttpPost post = new HttpPost(endpoint);

        post.addHeader("Content-Type","application/json");
        CloseableHttpClient client = HttpClientBuilder.create().build();
        try {
            String payload = objectMapper.writeValueAsString(toPost);
            post.setEntity(new StringEntity(payload));
            CloseableHttpResponse response = client.execute(post);
            String v = EntityUtils.toString(response.getEntity());
            result = parseResult(v);
        } catch (IOException e) {
            logger.error("cant get flights from LH",e);
        }
        return result;
    }

    public LHFlightsTO parseResult(String v) {
        ObjectMapper objectMapper = getObjectMapper();
        objectMapper.setPropertyNamingStrategy(new LhNameStrategy());
        LHFlightsTO result = null;
        try {
            result = objectMapper.readValue(v, LHFlightsTO.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private LHFlightSearchRequestTO prepareData(String source, String destination, DateTime dateTime) {
        LHFlightSearchRequestTO result = new LHFlightSearchRequestTO();
        result.setDepartureFrom(formatDate(dateTime));
        result.setEnvironment(environment);
        List<String> dest = new ArrayList<String>();
        dest.add(destination);
        result.setDestination(dest);
        result.setLengthOfStay("1");

        List<String> s = new ArrayList<String>();
        s.add(source);
        result.setResponse("json");
        result.setOrigin(s);
        result.setUser(username);
        result.setPass(password);
        result.setEnvironment(environment);
        return result;
    }

    public String formatDate(DateTime date) {
        DateTimeFormatter format = DateTimeFormat.forPattern("YYYY-MM-dd");
        return format.print(date);
    }


    private ObjectMapper getObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper;
    }

}
