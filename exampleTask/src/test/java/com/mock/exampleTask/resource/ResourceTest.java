package com.mock.exampleTask.resource;

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

public class ResourceTest extends BaseTest {

    private static Stream<Arguments> singleUser() {
        return Stream.of(
                Arguments.of(2, "fuchsia rose", 2001, "#C74375", "17-2031"),
                Arguments.of(3, "true red", 2002, "#BF1932", "19-1664")
        );
    }

    @ParameterizedTest
    @MethodSource("singleUser")
    public void searchSingleUser(Integer id, String name, Integer year, String color, String pantoneValue) {

        var response = resourceSteps.getResource(id, 200);

        assertThat(response.getData().getId()).as("id").isEqualTo(id);
        assertThat(response.getData().getName()).as("name").isEqualTo(name);
        assertThat(response.getData().getYear()).as("year").isEqualTo(year);
        assertThat(response.getData().getColor()).as("color").isEqualTo(color);
        assertThat(response.getData().getPantoneValue()).as("pahntom_value").isEqualTo(pantoneValue);
    }

    @Test
    public void searchSingleUserNotFound() {

        HttpClientErrorException thrown = Assertions.assertThrows(HttpClientErrorException.class, () -> {
            resourceSteps.getResource(null, 404);
        });
        assertEquals("404 NOT_FOUND", thrown.getMessage());
    }

    @Test
    public void getListUsers() {
        var response = resourceSteps.getListResource(2, 200);

        assertThat(response.getData().get(0).getId()).as("id").isEqualTo(7);
    }
}
