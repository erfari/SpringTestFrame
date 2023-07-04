package com.mock.exampleTask.fabric;

import com.mock.exampleTask.flows.register.RegisterRequest;
import com.mock.exampleTask.flows.users.UserPutRequest;
import org.springframework.stereotype.Component;


@Component
public class Fabric {
    public RegisterRequest valueObjectRegister() {
        var valueObject = new RegisterRequest();
        valueObject.setEmail("eve.holt@reqres.in");
        valueObject.setPassword("pistol");
        return valueObject;
    }

    public UserPutRequest valueObjectUser() {
        var valueObject = new UserPutRequest();
        valueObject.setName("morpheus");
        valueObject.setJob("zion resident");
        return valueObject;
    }
}
