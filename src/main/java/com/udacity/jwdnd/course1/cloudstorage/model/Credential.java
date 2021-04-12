package com.udacity.jwdnd.course1.cloudstorage.model;

public class Credential {
    private Integer credentialid;

    private String url;
    private String username;
    private String key;
    private String password;
    private Integer userid;

    public Credential(int credentialid, String url, String username, String password, String key) {
        this.credentialid = credentialid;
        this.url = url;
        this.username = username;
        this.password = password;
        this.key = key;
    }

    public Credential(String url, String username, String password, String key, int userid) {
        this.url = url;
        this.username = username;
        this.password = password;
        this.key = key;
        this.userid = userid;
    }

    public Credential() {
    }

    public int getCredentialid() {
        return credentialid;
    }

    public void setCredentialid(int credentialid) {
        this.credentialid = credentialid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }
}
