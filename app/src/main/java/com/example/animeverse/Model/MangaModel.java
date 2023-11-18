package com.example.animeverse.Model;

public class MangaModel {

    private String Ctitle;
    private String Ccover;
    private String Cdes;
    private String Clink;
    private String Cthumb;

    public MangaModel() {
    }
    public MangaModel(String ctitle, String ccover, String cdes, String clink, String cthumb) {
        Ctitle = ctitle;
        Ccover = ccover;
        Cdes = cdes;
        Clink = clink;
        Cthumb = cthumb;
    }

    public String getCtitle() {
        return Ctitle;
    }

    public void setCtitle(String ctitle) {
        Ctitle = ctitle;
    }

    public String getCcover() {
        return Ccover;
    }

    public void setCcover(String ccover) {
        Ccover = ccover;
    }

    public String getCdes() {
        return Cdes;
    }

    public void setCdes(String cdes) {
        Cdes = cdes;
    }

    public String getClink() {
        return Clink;
    }

    public void setClink(String clink) {
        Clink = clink;
    }

    public String getCthumb() {
        return Cthumb;
    }

    public void setCthumb(String cthumb) {
        Cthumb = cthumb;
    }
}
