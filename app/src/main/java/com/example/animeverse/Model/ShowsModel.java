package com.example.animeverse.Model;

public class ShowsModel {

    private String Scast;
    private String Scover;
    private String Sdesc;
    private String Slink;
    private String Stitle;
    private String Tlink;
    private String Sthumb;

    public ShowsModel() {
    }

    public ShowsModel(String scast, String scover, String sdesc, String slink, String stitle, String tlink, String sthumb) {
        Scast = scast;
        Scover = scover;
        Sdesc = sdesc;
        Slink = slink;
        Stitle = stitle;
        Tlink = tlink;
        Sthumb = sthumb;
    }

    public String getScast() {
        return Scast;
    }

    public void setScast(String scast) {
        Scast = scast;
    }

    public String getScover() {
        return Scover;
    }

    public void setScover(String scover) {
        Scover = scover;
    }

    public String getSdesc() {
        return Sdesc;
    }

    public void setSdesc(String sdesc) {
        Sdesc = sdesc;
    }

    public String getSlink() {
        return Slink;
    }

    public void setSlink(String slink) {
        Slink = slink;
    }

    public String getStitle() {
        return Stitle;
    }

    public void setStitle(String stitle) {
        Stitle = stitle;
    }

    public String getTlink() {
        return Tlink;
    }

    public void setTlink(String tlink) {
        Tlink = tlink;
    }

    public String getSthumb() {
        return Sthumb;
    }

    public void setSthumb(String sthumb) {
        Sthumb = sthumb;
    }
}
