package com.webtech.infocard;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class HomeController {
  
  @GetMapping("/")
  public String home() {
    return "InfoCard - Your new Way of learning!";
  }

}
