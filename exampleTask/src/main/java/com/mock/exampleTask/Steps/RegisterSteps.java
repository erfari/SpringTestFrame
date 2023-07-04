package com.mock.exampleTask.Steps;

import com.mock.exampleTask.enums.URIStand;
import com.mock.exampleTask.flows.register.RegisterResponse;
import com.mock.exampleTask.service.RequestService;
import io.qameta.allure.Step;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static com.mock.exampleTask.enums.Type.REST;

@Component
public class RegisterSteps {
    @Autowired
    protected RequestService requestService;

    @Step("Вход")
    public RegisterResponse registerUser(Object request, Integer code) {

        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();

        return requestService.getResponseRegister(requestService.requestPostJson(
                        request,
                        URIStand.REGISTER,
                        REST,
                        params,
                        code));
    }
}
