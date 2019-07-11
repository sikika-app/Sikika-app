package com.jr.sikika.classes;

import android.widget.ImageView;

public class ArticleClass {
    String title, snippet, url, imageSource, by, byLink;

    public ArticleClass(String title, String snippet, String url, String imageSource, String by, String byLink) {
        this.title = title;
        this.snippet = snippet;
        this.url = url;
        this.imageSource = imageSource;
        this.by = by;
        this.byLink = byLink;
    }

    public String getTitle() {
        return title;
    }

    public String getSnippet() {
        return snippet;
    }

    public String getUrl() {
        return url;
    }

    public String getImageSource() {
        return imageSource;
    }

    public String getBy() {
        return by;
    }

    public String getByLink() {
        return byLink;
    }
}
