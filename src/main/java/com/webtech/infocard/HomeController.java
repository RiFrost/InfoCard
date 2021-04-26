package com.webtech.infocard;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import databaseConnection.DbConnection;

@RestController
@CrossOrigin
public class HomeController {
  
  @GetMapping("/api")
  public String home() {
    return "InfoCard - Your new Way of learning!";
  }

  @GetMapping("/db")
  public String db() {
    boolean checkDbConnection = DbConnection.getInstance().checkDbConnection();
    return checkDbConnection ? "DB Verbindung erfolgreich" : "DB Verbindung fehlgeschlagen";
  }


}
