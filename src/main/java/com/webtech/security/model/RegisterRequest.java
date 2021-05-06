package com.webtech.security.model;

import java.util.Set;

import javax.validation.constraints.*;

public class RegisterRequest {
  @NotBlank
  @Size(min = 3, max = 20)
  private String firstname;

  @NotBlank
  @Size(min = 3, max = 20)
  private String lastname;


  @NotBlank
  @Size(max = 50)
  @Email
  private String email;
  
  private Set<String> role;
  
  @NotBlank
  @Size(min = 6, max = 40)
  private String password;

  public String getFirstname() {
      return firstname;
  }

  public void setFirstname(String firstname) {
      this.firstname = firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  public String getEmail() {
      return email;
  }

  public void setEmail(String email) {
      this.email = email;
  }

  public String getPassword() {
      return password;
  }

  public void setPassword(String password) {
      this.password = password;
  }
  
  public Set<String> getRole() {
    return this.role;
  }
  
  public void setRole(Set<String> role) {
    this.role = role;
  }
}
