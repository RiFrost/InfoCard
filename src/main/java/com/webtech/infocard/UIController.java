package com.webtech.infocard;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class UIController {

  @RequestMapping("/**/{path:[^.]*}")
  public ModelAndView redirect() {
    return new ModelAndView("forward:/");
  }
  
}
