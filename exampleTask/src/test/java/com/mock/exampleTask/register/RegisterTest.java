package com.mock.exampleTask.register;

import com.mock.exampleTask.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.HttpClientErrorException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegisterTest extends BaseTest {

    @Test
    public void registerUser() {

        var request = fabric.valueObjectRegister();
        var response = registerSteps.registerUser(request, 200);

        assertThat(response.getId()).as("id").isEqualTo(4);
        assertThat(response.getToken()).as("token").isEqualTo("QpwL5tke4Pnpja7X4");
    }

    @Test
    public void registerUserNegative() {

        var request = fabric.valueObjectRegister();
        request.setPassword(null);
        HttpClientErrorException thrown = Assertions.assertThrows(HttpClientErrorException.class, () -> {
            registerSteps.registerUser(request, 400);
        });
        assertEquals("400 BAD_REQUEST", thrown.getMessage());
    }

}
