package com.nepaliapps.nepalinews.Pojo;

/**
 * Created by bino on 17/2/18.
 */

public class NewsPojo {

    int image;
    String webLink;

    public NewsPojo(  String webLink,int image) {
        this.image = image;
        this.webLink = webLink;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }


    public String getWebLink() {
        return webLink;
    }

    public void setWebLink(String webLink) {
        this.webLink = webLink;
    }
}
