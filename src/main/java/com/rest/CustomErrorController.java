package com.rest;

import javax.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomErrorController implements ErrorController {

  //Print custom error message if there is an exception
  @GetMapping("/error")
  public Object handleError() {
    return "Please input the appropriate json format and data type.";
  }

  @GetMapping("/errorWithReq")
  public Object handleError(HttpServletRequest request) {
    return "Please input the appropriate json format and data type.";
  }

  @Override
  public String getErrorPath() {
    return null;
  }
}
