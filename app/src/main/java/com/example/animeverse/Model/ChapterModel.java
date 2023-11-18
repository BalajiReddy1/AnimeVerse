package com.example.animeverse.Model;

public class ChapterModel {

    private String part,url,pdfUrl;

    public ChapterModel() {
    }
    public ChapterModel(String part, String url, String pdfUrl) {
        this.part = part;
        this.url = url;
        this.pdfUrl = pdfUrl;
    }

    public String getPart() {
        return part;
    }

    public void setPart(String part) {
        this.part = part;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPdfUrl() {
        return pdfUrl;
    }

    public void setPdfUrl(String pdfUrl) {
        this.pdfUrl = pdfUrl;
    }
}
