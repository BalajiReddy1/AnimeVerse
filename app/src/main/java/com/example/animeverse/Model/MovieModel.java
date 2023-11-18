package com.example.animeverse.Model;

public class MovieModel {

    private String Fcast;
    private String Fcover;
    private String Fdes;
    private String Flink;
    private String Fthumb;
    private String Ftitle;
    private String Tlink;


    public MovieModel() {

    }

    public MovieModel(String fcast, String fcover, String fdes, String flink, String fthumb, String ftitle, String tlink) {
        Fcast = fcast;
        Fcover = fcover;
        Fdes = fdes;
        Flink = flink;
        Fthumb = fthumb;
        Ftitle = ftitle;
        Tlink = tlink;
    }

    public String getFcast() {
        return Fcast;
    }

    public void setFcast(String fcast) {
        Fcast = fcast;
    }

    public String getFcover() {
        return Fcover;
    }

    public void setFcover(String fcover) {
        Fcover = fcover;
    }

    public String getFdes() {
        return Fdes;
    }

    public void setFdes(String fdes) {
        Fdes = fdes;
    }

    public String getFlink() {
        return Flink;
    }

    public void setFlink(String flink) {
        Flink = flink;
    }

    public String getFthumb() {
        return Fthumb;
    }

    public void setFthumb(String fthumb) {
        Fthumb = fthumb;
    }

    public String getFtitle() {
        return Ftitle;
    }

    public void setFtitle(String ftitle) {
        Ftitle = ftitle;
    }

    public String getTlink() {
        return Tlink;
    }

    public void setTlink(String tlink) {
        Tlink = tlink;
    }
}
