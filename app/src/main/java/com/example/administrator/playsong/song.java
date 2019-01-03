package com.example.administrator.playsong;

public class song {

    private String cover;
    private String title;
    private String songLink;

    public song(String cover, String title, String songLink) {
        this.cover = cover;
        this.title = title;
        this.songLink = songLink;
    }

    public String getSongLink() {
        return songLink;
    }

    public String getCover() {
        return cover;
    }

    public String getTitle() {
        return title;
    }

}
