package com.mock.exampleTask.Steps;

import com.mock.exampleTask.enums.URIStand;
import com.mock.exampleTask.flows.BaseResponse;
import com.mock.exampleTask.flows.BaseResponseList;
import com.mock.exampleTask.flows.resource.ResourceByIdResponse;
import com.mock.exampleTask.service.RequestService;
import io.qameta.allure.Step;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static com.mock.exampleTask.enums.Type.REST;

@Component
public class ResourceSteps {
    @Autowired
    protected RequestService requestService;

    @Step("Ресурс по ид")
    public BaseResponse<ResourceByIdResponse> getResource(Integer id, Integer code) {

        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();

        return requestService.getResponse(requestService.requestGetWithQueryParam(
                        URIStand.UNKNOWN,
                        REST,
                        params,
                        "id",
                        id,
                        code),
                ResourceByIdResponse.class);
    }

    @Step("Список клиентов")
    public BaseResponseList<ResourceByIdResponse> getListResource(Integer page, Integer code) {

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("page", page.toString());

        return requestService.getResponseList(requestService.requestGetWithQueryParam(
                        URIStand.UNKNOWN,
                        REST,
                        params,
                        "",
                        null,
                        code),
                ResourceByIdResponse.class);
    }
}
