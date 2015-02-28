package com.aakhmerov.thack.api.service.sabre;

import com.aakhmerov.thack.api.service.sabre.jackson.MyNameStrategy;
import com.aakhmerov.thack.api.service.sabre.tos.SabreDestinationTO;
import com.aakhmerov.thack.api.service.sabre.tos.SabreDestinationWrapperTO;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by aakhmerov on 28.02.15.
 */
@Service
public class SabreService {
    private static final Logger logger = LoggerFactory.getLogger(SabreService.class);

    @Value("${sabre.secret}")
    private String secret;

    @Value("${sabre.key}")
    private String key;

    @Value("${sabre.token.header}")
    private String header;

    @Value("${sabre.destination.endpoint}")
    private String destinationEndpoint;

    @Value("${sabre.authToken.endpoint}")
    private String authEndpoint;

    /**
     * Obtain possible destination from sabre web service
     *
     * https://developer.sabre.com/docs/read/rest_apis/air/search/destination_finder
     *
     * @param from
     * @param departure
     * @param arrival
     * @return
     */
    public List<SabreDestinationTO> getDestinations(String from, DateTime departure, DateTime arrival) {
        List<SabreDestinationTO> result = null;

        HttpGet request = new HttpGet(buildUri( from,  departure,  arrival));
//        request.addHeader(header,);
        request.addHeader("Authorization","Bearer " + obtainAuth());
//        request.addHeader("accept", "application/json");

//        VHpsak0yZGhURTg9OlZqRTZielZ2ZDJRMmNqaHpZMng0ZUdzd1pqcEVSVlpEUlU1VVJWSTZSVmhV

//        StringEntity input = composeDestinationSearchEntity(from, departure, arrival);

        CloseableHttpClient client = HttpClientBuilder.create().build();
        try {
            CloseableHttpResponse response = client.execute(request);
            String v = EntityUtils.toString(response.getEntity());
            result = parseResult(v);
        } catch (IOException e) {
            logger.error("cant get destinations from sabre",e);
        }
        return result;
    }

    /**
     * parse token itself from the JSON response with token
     * @return
     */
    public String obtainAuth() {
        String tokenOnly = null;
        String result = getAuthorizationToken();

        ObjectMapper objectMapper = getObjectMapper();
        try {
            HashMap map = objectMapper.readValue(result, HashMap.class);
            tokenOnly = (String) map.get("access_token");
//            tokenOnly = tokenOnly.substring(tokenOnly.indexOf(":")+1,tokenOnly.length());
        } catch (IOException e) {
            logger.error("cant map response",e);
        }
        return tokenOnly;
    }

    private ObjectMapper getObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper;
    }

    /**
     *
     * @return
     */
    public String getAuthorizationToken() {
        String result = null;
        HttpPost post = new HttpPost(authEndpoint);
//        VHpsak0yZGhURTg9OlZqRTZielZ2ZDJRMmNqaHpZMng0ZUdzd1pqcEVSVlpEUlU1VVJWSTZSVmhV
        post.addHeader("Authorization","Basic " + composeToken(secret, key));
        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
        urlParameters.add(new BasicNameValuePair("grant_type", "client_credentials"));


        try {
            post.setEntity(new UrlEncodedFormEntity(urlParameters));
        } catch (UnsupportedEncodingException e) {
            logger.error("cant compose auth request", e);
        }
        CloseableHttpClient client = HttpClientBuilder.create().build();
        try {
            CloseableHttpResponse response = client.execute(post);
            result = EntityUtils.toString(response.getEntity());
        } catch (IOException e) {
            logger.error("cant get destinations from sabre",e);
        }

        return result;
    }

    /**
     * Parse Sabre response into usable data structures
     * @param s
     * @return
     */
    public List<SabreDestinationTO> parseResult(String s) {
        ObjectMapper objectMapper = getObjectMapper();
        objectMapper.setPropertyNamingStrategy(new MyNameStrategy());

        SabreDestinationWrapperTO parsed = null;
        try {
            parsed = objectMapper.readValue(s,SabreDestinationWrapperTO.class);
        } catch (IOException e) {
            logger.error("cant parse sabre response",e);
        }

        return parsed.getFareInfo();
    }

    /**
     * Build get request uri from given params with adding other mandatory parameters
     * @param from
     * @param departure
     * @param arrival
     * @return
     */
    private URI buildUri(String from, DateTime departure, DateTime arrival) {
        URIBuilder builder = null;
        URI uri = null;
        try {
            builder = new URIBuilder(destinationEndpoint);
            builder.addParameter("origin",from);
            builder.addParameter("departuredate",format(departure));
            builder.addParameter("returndate",format(arrival));
            builder.addParameter("pointofsalecountry","DE");
            builder.addParameter("topdestinations","50");
            uri = builder.build();
        } catch (URISyntaxException e) {
            logger.error("cant build uri",e);
        }
        return uri;
    }

    public String format(DateTime date) {
        DateTimeFormatter format = DateTimeFormat.forPattern("YYYY-MM-dd");
        return format.print(date);
    }

    /**
     * Utility method to compose api token for sabre requests
     *
     * @param secret
     * @param key
     * @return
     */
    private String composeToken(String secret, String key) {
        String secretB64 = Base64.encodeBase64String(secret.getBytes());
        String keyB64 = Base64.encodeBase64String(key.getBytes());
        String initial = keyB64 + ":" + secretB64;
        return Base64.encodeBase64String(initial.getBytes());
    }


    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getDestinationEndpoint() {
        return destinationEndpoint;
    }

    public void setDestinationEndpoint(String destinationEndpoint) {
        this.destinationEndpoint = destinationEndpoint;
    }
}
