package com.seyma.chatboot.models;

import android.net.Uri;

/**
 * @author AOS-SEYMA
 * @since 19-Aug-22
 */
public class ImageItem {
    private String url;
    private Uri uri;

    public ImageItem(Uri uri){ this.uri = uri;}

    public ImageItem(String url){this.url = url;}

    public ImageItem(){

    }
    public String getUrl(){ return url;}

    public void setUrl(String url){ this.url = url;}

    public Uri getUri(){return uri;}

    public void setUri(Uri uri){this.uri= uri;}
}
