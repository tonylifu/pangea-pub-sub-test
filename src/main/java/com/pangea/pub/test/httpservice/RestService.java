package com.pangea.pub.test.httpservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MultivaluedMap;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Collections;

@Service
public class RestService {

    private static Logger LOG = LoggerFactory.getLogger(RestService.class);

    private RestFactory restFactory;

    @Autowired
    public RestService(RestFactory restFactory) {
        this.restFactory = restFactory;
    }

    public String restTemplateServiceCall(String request, String fullUrl) {
        //String fullUrl = env.getBaseApiUrl() + uriEndpoint; //env.getLookUpEndPoint();
        LOG.info("\n\nFull URL: {}\n\nAPI Request: \n{}\n\n", fullUrl, request);
        String resp;
        try{
            URI uri = new URI(fullUrl);
            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Collections.singletonList(MediaType.ALL));

            HttpEntity<String> entity = new HttpEntity<>(request, headers);

            ResponseEntity<String> response = restTemplate.postForEntity(
                    uri,
                    entity,
                    String.class
            );

            resp = response.getBody();
            LOG.info("\n\nAPI Response: {}\n\n", resp);

        }catch (URISyntaxException e){
            LOG.error("\n\nCommunication Syntax Error: {}\n\n", e.getMessage());
            resp = null;
        }catch (Exception e){
            LOG.error("\n\nAn Error Occurred: {}\n\n", e.getMessage());
            resp = null;
        }
        return resp;
    }

    /*public String makeReactiveWebclientCall(String request, String fullUrl) {
        //String fullUrl = env.getBaseApiUrl();
        LOG.info("\n\nFull URL: {}\n\nWebclient API Request: \n{}\n\n", fullUrl, request);
        String resp;
        try{
            URI uri = new URI(fullUrl);
            WebClient webClient = WebClient.create();
            Mono<String> stringMono = webClient.post()
                    .uri(uri)
                    .body(Mono.just(request), String.class)
                    .retrieve()
                    .bodyToMono(String.class);

            resp = stringMono.block();
            LOG.info("\n\nWebclient API Response: {}\n\n", resp);
        }catch (URISyntaxException e){
            LOG.error("\n\nCommunication Syntax Error: {}\n\n", e.getMessage());
            resp = null;
        }catch (Exception e){
            LOG.error("\n\nAn Error Occurred: {}\n\n", e.getMessage());
            resp = null;
        }
        return resp;
    }*/

    //Service call
    public String makeServiceCall(String apiRequest, String urlStr)  {

        LOG.info("\n\nREQUEST URL:\n {}\n\n", urlStr);

        LOG.info("\n\nAPI REQUEST:\n {}\n\n", apiRequest);

        //Client client = ClientBuilder.newClient();
        String apiResponse;

        try {
            URL url = new URL(urlStr);
            WebTarget target = restFactory.buildUnsecureWebTarget(url);
            apiResponse = target.request(MediaType.ALL_VALUE)
                    .post(Entity.json(apiRequest), String.class);

            LOG.info("\n\nAPI RESPONSE:\n {}\n\n", apiResponse);

            return apiResponse;

        } catch (MalformedURLException e) {
            LOG.error("\n\nAn error occurred:\n {}\n\n", e.getMessage());

            return null;
        } catch (Exception e) {
            LOG.error("\n\nAn error occurred:\n {}\n\n", e.getMessage());

            return null;
        }

    }

    //Service call
    public String makeServiceCall(String apiRequest, String urlStr, MultivaluedMap<String, Object> headers) {

        LOG.info("\n\nREQUEST URL:\n {}\n\n", urlStr);

        LOG.info("\n\nAPI REQUEST:\n {}\n\n", apiRequest);

        LOG.info("\n\nHeaders:\n {}\n\n", headers);

        //Client client = ClientBuilder.newClient();
        String apiResponse;

        try {
            URL url = new URL(urlStr);
            WebTarget target = restFactory.buildUnsecureWebTarget(url);
            apiResponse = target.request(MediaType.ALL_VALUE)
                    .headers(headers)
                    .post(Entity.json(apiRequest), String.class);

            LOG.info("\n\nAPI RESPONSE:\n {}\n\n", apiResponse);

            return apiResponse;

        } catch (MalformedURLException e) {
            LOG.error("\n\nAn error occurred:\n {}\n\n", e.getMessage());

            return null;
        } catch (Exception e) {
            LOG.error("\n\nAn error occurred:\n {}\n\n", e.getMessage());

            return null;
        }

    }

    //Service call
    public String makeServiceCall(String urlStr)  {

        //Client client = ClientBuilder.newClient();
        String apiResponse;

        try {
            URL url = new URL(urlStr);
            WebTarget target = restFactory.buildUnsecureWebTarget(url);
            apiResponse = target.request(MediaType.ALL_VALUE)
                    .get(String.class);

            LOG.info("\n\nAPI RESPONSE\n: {}\n\n", apiResponse);

            return apiResponse;

        } catch (MalformedURLException e) {
            LOG.error("\n\nAn error occurred:\n {}\n\n", e.getMessage());

            return null;
        } catch (Exception e) {
            LOG.error("\n\nAn error occurred:\n {}\n\n", e.getMessage());

            return null;
        }

    }
}
