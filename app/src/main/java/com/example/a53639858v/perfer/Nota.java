package com.example.a53639858v.perfer;

public class Nota {

    private String urlImage;
    private String text;

    public Nota(String urlImage, String text) {
        this.urlImage = urlImage;
        this.text = text;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Nota{" +
                "urlImage='" + urlImage + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
