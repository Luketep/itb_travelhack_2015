package com.aakhmerov.thack.api.service.gyg;

import com.aakhmerov.thack.api.service.gyg.tos.GygResponseTO;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by aakhmerov on 28.02.15.
 */
@Service
public class GygService {
    private static final Logger logger = LoggerFactory.getLogger(GygService.class);

    @Value("${gyg.token}")
    private String token;

    @Value("${gyg.endpoint}")
    private String endpoint;

    public GygResponseTO getTours(String iata) {
        GygResponseTO result= null;
        HttpGet request = new HttpGet(getURI(iata));
        request.addHeader("X-ACCESS-TOKEN",token);
        request.addHeader("Accept","application/json");

        CloseableHttpClient client = HttpClientBuilder.create().build();
        try {
            CloseableHttpResponse response = client.execute(request);
            result = this.parseResponse(EntityUtils.toString(response.getEntity()));
        } catch (IOException e) {
            logger.error("cant get destinations from sabre",e);
        }
        return result;
    }

    private URI getURI(String iata) {
        URIBuilder builder = null;
        URI uri = null;
        try {
            builder = new URIBuilder(endpoint);
            builder.addParameter("cnt_language","EN");
            builder.addParameter("currency","EUR");
            builder.addParameter("q","iata:" + iata);
            uri = builder.build();
        } catch (URISyntaxException e) {
            logger.error("cant build uri",e);
        }
        return uri;
    }

    public GygResponseTO parseResponse(String response) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        GygResponseTO result = null;
        try {
             result = objectMapper.readValue(response, GygResponseTO.class);
        } catch (IOException e) {
            logger.error("cant parse gyg result",e);
        }
        return result;
    }
}
