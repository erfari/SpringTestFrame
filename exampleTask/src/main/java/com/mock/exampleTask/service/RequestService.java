package com.mock.exampleTask.service;

import com.mock.exampleTask.enums.Type;
import com.mock.exampleTask.enums.URIStand;
import com.mock.exampleTask.flows.BaseResponse;
import com.mock.exampleTask.flows.BaseResponseList;
import com.mock.exampleTask.flows.register.RegisterResponse;
import com.mock.exampleTask.flows.users.UserPutResponse;
import org.springframework.util.MultiValueMap;

public interface RequestService {

    <T> BaseResponse<T> getResponse(String json, Class<T> clazz);
    RegisterResponse getResponseRegister(String json); //Тут дженерики нужны
    UserPutResponse getResponseUserPut(String json);
    String requestGetWithQueryParam(URIStand flow, Type type, MultiValueMap<String, String> params, String path, Integer id, Integer code);
    String requestPostJson(Object request, URIStand flow, Type type, MultiValueMap<String, String> params, Integer code);
    String requestPutJson(Object request, URIStand flow, Type type, MultiValueMap<String, String> params, Integer id, Integer code);
    String requestPatchJson(Object request, URIStand flow, Type type, MultiValueMap<String, String> params, Integer id, Integer code);
    String requestDeleteJson(URIStand flow, Type type, MultiValueMap<String, String> params, Integer id, Integer code);
    <T> BaseResponseList<T> getResponseList(String json, Class<T> clazz);
}
