package com.webtech.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "credential"
)
public class Credential {

    @Id
    @Column(name = "cred_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @OneToOne(mappedBy = "credential")
    private User user;

    @Column(name = "hashed_password", nullable = false)
    private String hashedPassword;

    @Column(nullable = false)
    private String key;

    @Column(nullable = false)
    private String salt;

    public Credential() {
    }

    public Credential(User user, String hashedPassword, String key, String salt) {
        this.user = user;
        this.hashedPassword = hashedPassword;
        this.key = key;
        this.salt = salt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    @Override
    public String toString() {
        return "Credential{" +
                "id=" + id +
                ", user=" + user +
                '}';
    }
}
