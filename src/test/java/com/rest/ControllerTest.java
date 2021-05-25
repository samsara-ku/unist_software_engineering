package com.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import org.springframework.test.web.servlet.ResultActions;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ResponseBody
@WebMvcTest()
public class ControllerTest {
	@Autowired
    private MockMvc mvc;

    //CustomErrorController
	@Test
	public void TestHandleError() throws Exception {
        mvc.perform(get("/error"))
                .andExpect(status().isOk())
                .andExpect(content().string("Please input the appropriate json format and data type."));
        System.out.println("passed CustomErrorController_handleError1");
	}

	@Test
	public void TestHandleErrorWithReq() throws Exception {   
        mvc.perform(get("/errorWithReq"))
                .andExpect(status().isOk())
                .andExpect(content().string("Please input the appropriate json format and data type."));
        System.out.println("passed CustomErrorController_handleError2");
	}

    @Test
    public void TestGetErrorPath() throws Exception {
        CustomErrorController testController = new CustomErrorController();
        assertNull(testController.getErrorPath());
        System.out.println("passed CustomErrorController_getErrorPath");
    }
}
