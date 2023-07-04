package com.mock.exampleTask.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.mock.exampleTask.enums.Type;
import com.mock.exampleTask.enums.URIStand;
import com.mock.exampleTask.exception.AutotestException;
import com.mock.exampleTask.flows.BaseResponse;
import com.mock.exampleTask.flows.BaseResponseList;
import com.mock.exampleTask.flows.register.RegisterResponse;
import com.mock.exampleTask.flows.users.UserPutResponse;
import com.mock.exampleTask.service.RequestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;
import static com.mock.exampleTask.enums.URIStand.getURI;
import static org.assertj.core.api.Assertions.assertThat;

@Service
@RequiredArgsConstructor
@Profile("test")
@Slf4j
public class RequestServiceImpl implements RequestService {
    @Value("${stand}")
    private String stand;

    private final RestTemplate restTemplate;


    @Override
    public <T> BaseResponse<T> getResponse(String json, Class<T> clazz) {
        return getBaseResponseFromResponse(json, clazz);
    }

    @Override
    public RegisterResponse getResponseRegister(String json) {
        return getBaseResponseFromResponseRegister(json);
    }

    @Override
    public UserPutResponse getResponseUserPut(String json) {
        return getBaseResponseFromResponsePut(json);
    }

    public static <T> BaseResponse<T> getBaseResponseFromResponse(String json, Class<T> clazz) {
        try {
            var mapper = new ObjectMapper();
            JavaType type = mapper.getTypeFactory()
                    .constructParametricType(BaseResponse.class, clazz);
            return mapper.readValue(json, type);
        } catch (JsonProcessingException exception) {
            throw new AutotestException(exception.getMessage());
        }
    }

    public static RegisterResponse getBaseResponseFromResponseRegister(String json) {
        try {
            return new ObjectMapper().readValue(json, RegisterResponse.class);
        } catch (JsonProcessingException exception) {
            throw new AutotestException(exception.getMessage());
        }
    }

    public static UserPutResponse getBaseResponseFromResponsePut(String json) {
        try {
            return new ObjectMapper().readValue(json, UserPutResponse.class);
        } catch (JsonProcessingException exception) {
            throw new AutotestException(exception.getMessage());
        }
    }


    @Override
    public <T> BaseResponseList<T> getResponseList(String json, Class<T> clazz) {
        return getBaseResponseFromResponseList(json, clazz);
    }

    public static <T> BaseResponseList<T> getBaseResponseFromResponseList(String json, Class<T> clazz) {
        try {
            var mapper = new ObjectMapper();
            JavaType type = mapper.getTypeFactory()
                    .constructParametricType(BaseResponseList.class, clazz);
            return mapper.readValue(json, type);
        } catch (JsonProcessingException exception) {
            throw new AutotestException(exception.getMessage());
        }
    }


    public String requestGetWithQueryParam(URIStand flow, Type type, MultiValueMap<String, String> params, String path, Integer id, Integer code) {
        ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<?> entity = new HttpEntity<>(headers);

        var endpoint = switch (path) {
            case "id" -> "https://reqres.in/" + getURI(flow) + "/" + id;
            //1
            //2
            //3
            default -> "https://reqres.in/" + getURI(flow);
        };
        log.info("Endpoint: \n" + endpoint);
        try {
            URI uri = UriComponentsBuilder
                    .fromUri(URI.create(endpoint))
                    .queryParams(params)
                    .build()
                    .toUri();
            var response = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);

            assertThat(response.getStatusCode().value()).as("Status code").isEqualTo(code);

            String responseJson = mapper.writeValueAsString(mapper.readTree(response.getBody()));
            log.info("Response: \n" + response);
            log.info("Params: \n" + params.get("page"));
            return responseJson;
        } catch (JsonProcessingException | HttpClientErrorException.NotFound e) {
            throw new HttpClientErrorException(HttpStatusCode.valueOf(404));
        }
    }

    @Override
    public String requestPostJson(Object request, URIStand flow,
                                  Type type, MultiValueMap<String,
            String> params, Integer code) {

        ObjectMapper mapper = new ObjectMapper();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<?> entity = new HttpEntity<>(request, headers);
        var endpoint = "https://reqres.in/" + getURI(flow);
        log.info("Endpoint: \n" + endpoint);
        try {
            var requestString = mapper.writeValueAsString(request);
            log.info("Request: \n" + requestString);
            var response = restTemplate.exchange(endpoint, HttpMethod.POST, entity, String.class);

            String responseJson = mapper.writeValueAsString(mapper.readTree(response.getBody()));
            log.info("Response: \n" + response);
            assertThat(response.getStatusCode().value()).as("Status code").isEqualTo(code);
            return responseJson;
        } catch (AutotestException ex) {
            var response = ex.getExceptionMessage();
            log.info("Response: \n" + response);
            return response;
        } catch (JsonProcessingException | HttpClientErrorException.BadRequest exception) {
            throw new HttpClientErrorException(HttpStatusCode.valueOf(400));
        }
    }

    @Override
    public String requestPutJson(Object request, URIStand flow, Type type, MultiValueMap<String, String> params, Integer id, Integer code) {
        ObjectMapper mapper = new ObjectMapper();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<?> entity = new HttpEntity<>(request, headers);
        var endpoint = "https://reqres.in/" + getURI(flow) + "/" + id;

        log.info("Endpoint: \n" + endpoint);
        try {
            var requestString = mapper.writeValueAsString(request);
            log.info("Request: \n" + requestString);
            var response = restTemplate.exchange(endpoint, HttpMethod.PUT, entity, String.class);
            assertThat(response.getStatusCode().value()).as("Status code").isEqualTo(code);
            String responseJson = mapper.writeValueAsString(mapper.readTree(response.getBody()));
            log.info("Response: \n" + responseJson);
            return responseJson;
        } catch (AutotestException ex) {
            var response = ex.getExceptionMessage();
            log.info("Response: \n" + response);
            return response;
        } catch (JsonProcessingException exception) {
            throw new AutotestException(exception.getMessage());
        }
    }

    @Override
    public String requestPatchJson(Object request, URIStand flow, Type type, MultiValueMap<String, String> params, Integer id, Integer code) {
        ObjectMapper mapper = new ObjectMapper();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<?> entity = new HttpEntity<>(request, headers);
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setConnectTimeout(1000);

        restTemplate.setRequestFactory(requestFactory);
        var endpoint = "https://reqres.in/" + getURI(flow) + "/" + id;

        log.info("Endpoint: \n" + endpoint);
        try {
            var requestString = mapper.writeValueAsString(request);
            log.info("Request: \n" + requestString);
            var response = restTemplate.exchange(endpoint, HttpMethod.PATCH, entity, String.class);
            assertThat(response.getStatusCode().value()).as("Status code").isEqualTo(code);
            String responseJson = mapper.writeValueAsString(mapper.readTree(response.getBody()));
            log.info("Response: \n" + responseJson);
            return responseJson;
        } catch (AutotestException ex) {
            var response = ex.getExceptionMessage();
            log.info("Response: \n" + response);
            return response;
        } catch (JsonProcessingException exception) {
            throw new AutotestException(exception.getMessage());
        }
    }

    @Override
    public String requestDeleteJson(URIStand flow, Type type, MultiValueMap<String, String> params, Integer id, Integer code) {
        ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

        var endpoint = "https://reqres.in/" + getURI(flow) + "/" + id;
        log.info("Endpoint: \n" + endpoint);
        URI uri = UriComponentsBuilder
                .fromUri(URI.create(endpoint))
                .queryParams(params)
                .build()
                .toUri();
        try {
            ResponseEntity<Void> response = restTemplate.exchange(uri, HttpMethod.DELETE,
                    null, Void.class);
            assertThat(response.getStatusCode().value()).as("Status code").isEqualTo(code);
        } catch (HttpClientErrorException ex) {
            ex.getResponseBodyAsString();
        }
        return null;
    }
}
