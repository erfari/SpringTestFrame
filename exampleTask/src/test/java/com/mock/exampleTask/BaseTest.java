package com.mock.exampleTask;

import com.mock.exampleTask.Steps.RegisterSteps;
import com.mock.exampleTask.Steps.ResourceSteps;
import com.mock.exampleTask.Steps.UsersSteps;
import com.mock.exampleTask.fabric.Fabric;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public abstract class BaseTest {
    @Autowired
    protected UsersSteps usersSteps;

    @Autowired
    protected ResourceSteps resourceSteps;

    @Autowired
    protected RegisterSteps registerSteps;

    @Autowired
    protected Fabric fabric;
}
