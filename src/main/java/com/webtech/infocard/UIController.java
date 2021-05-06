package com.webtech.infocard;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class UIController {

  // used to forward any unknow request URL to the frontend where the url gets mapped to a view
  // all backend calls are with the prefix /api/
  @RequestMapping("/**/{path:[^.]*}")
  public ModelAndView redirect() {
    return new ModelAndView("forward:/");
  }

  
  
}
