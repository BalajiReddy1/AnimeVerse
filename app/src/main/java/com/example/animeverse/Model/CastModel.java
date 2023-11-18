package com.example.animeverse.Model;

public class CastModel {

    String cname;
    String curl;

    public CastModel() {
    }

    public CastModel(String cname, String curl) {
        this.cname = cname;
        this.curl = curl;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getCurl() {
        return curl;
    }

    public void setCurl(String curl) {
        this.curl = curl;
    }
}

