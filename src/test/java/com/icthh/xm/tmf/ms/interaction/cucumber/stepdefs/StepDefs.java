package com.icthh.xm.tmf.ms.interaction.cucumber.stepdefs;

import com.icthh.xm.tmf.ms.interaction.InteractionApp;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.ResultActions;

import org.springframework.boot.test.context.SpringBootTest;

@WebAppConfiguration
@SpringBootTest
@ContextConfiguration(classes = InteractionApp.class)
public abstract class StepDefs {

    protected ResultActions actions;

}
