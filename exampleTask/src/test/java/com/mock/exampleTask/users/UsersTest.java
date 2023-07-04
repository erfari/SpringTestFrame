package com.mock.exampleTask.users;

import com.mock.exampleTask.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.web.client.HttpClientErrorException;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UsersTest extends BaseTest {

    private static Stream<Arguments> singleUser() {
        return Stream.of(
                Arguments.of(2, "janet.weaver@reqres.in", "Janet", "Weaver"),
                Arguments.of(4, "eve.holt@reqres.in", "Eve", "Holt"),
                Arguments.of(7, "michael.lawson@reqres.in", "Michael", "Lawson")
        );
    }

    @ParameterizedTest
    @MethodSource("singleUser")
    public void getSingleUserTest(Integer id, String email, String first_name, String last_name) {

        var response = usersSteps.getUser(id, 200);

        assertThat(response.getData().getId()).as("id").isEqualTo(id);
        assertThat(response.getData().getEmail()).as("email").isEqualTo(email);
        assertThat(response.getData().getFirstName()).as("first name").isEqualTo(first_name);
        assertThat(response.getData().getLastName()).as("last name").isEqualTo(last_name);
        assertThat(response.getData().getAvatar()).as("avatar").isNotNull();
    }

    @Test
    public void getSingleUserNotFoundTest() {

        HttpClientErrorException thrown = Assertions.assertThrows(HttpClientErrorException.class, () -> {
            usersSteps.getUser(null, 404);
        });
        assertEquals("404 NOT_FOUND", thrown.getMessage());
    }

    @Test
    public void getListUsersTest() {
        var response = usersSteps.getListUsers(2, 200);

        assertThat(response.getData().get(0).getId()).as("id").isEqualTo(1);

    }

    @Test
    public void putUserTest() {
        var request = fabric.valueObjectUser();
        var response = usersSteps.putUser(request, 2, 200);

        assertThat(response.getName()).as("name").isEqualTo("morpheus");

    }

    @Test
    public void patchUserTest() {
        var request = fabric.valueObjectUser();
        var response = usersSteps.patchUser(request, 2, 200);

        assertThat(response.getName()).as("name").isEqualTo("morpheus");
    }

    @Test
    public void deleteUser() {

        var response = usersSteps.deleteUser(2, 204);

        System.out.println(response);
    }
}
