package com.mock.exampleTask.Steps;

import com.mock.exampleTask.enums.URIStand;
import com.mock.exampleTask.flows.BaseResponse;
import com.mock.exampleTask.flows.BaseResponseList;
import com.mock.exampleTask.flows.users.UserPutResponse;
import com.mock.exampleTask.flows.users.UsersByIdResponse;
import com.mock.exampleTask.service.RequestService;
import io.qameta.allure.Step;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static com.mock.exampleTask.enums.Type.REST;

@Component
public class UsersSteps {
    @Autowired
    protected RequestService requestService;

    @Step("Клиент по ид")
    public BaseResponse<UsersByIdResponse> getUser(Integer id, Integer code) {

        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();

        return requestService.getResponse(requestService.requestGetWithQueryParam(
                        URIStand.USERS,
                        REST,
                        params,
                        "id",
                        id,
                        code),
                UsersByIdResponse.class);
    }

    @Step("Список клиентов")
    public BaseResponseList<UsersByIdResponse> getListUsers(Integer page, Integer code) {

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("page", page.toString());

        return requestService.getResponseList(requestService.requestGetWithQueryParam(
                        URIStand.USERS,
                        REST,
                        params,
                        "",
                        null,
                        code),
                UsersByIdResponse.class);
    }

    @Step("put")
    public UserPutResponse putUser(Object request, Integer id, Integer code) {

        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();

        return requestService.getResponseUserPut(requestService.requestPutJson(
                request,
                URIStand.USERS,
                REST,
                params,
                id,
                code));
    }

    @Step("patch")
    public UserPutResponse patchUser(Object request, Integer id, Integer code) {

        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();

        return requestService.getResponseUserPut(requestService.requestPatchJson(
                request,
                URIStand.USERS,
                REST,
                params,
                id,
                code));
    }

    @Step("Удаление юзера")
    public String deleteUser(Integer id, Integer code) {

        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();

        return requestService.requestDeleteJson(
                URIStand.USERS,
                REST,
                params,
                id,
                code);
    }
}
