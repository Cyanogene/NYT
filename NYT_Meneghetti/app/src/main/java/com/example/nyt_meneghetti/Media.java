package com.example.nyt_meneghetti;

import java.util.ArrayList;

public class Media {
    private String type;

    private String subtype;

    private String caption;

    private String copyright;

    private int approved_for_syndication;

    private transient ArrayList<Media_Img> media_metadata;

    public void setType(String type){
        this.type = type;
    }
    public String getType(){
        return this.type;
    }
    public void setSubtype(String subtype){
        this.subtype = subtype;
    }
    public String getSubtype(){
        return this.subtype;
    }
    public void setCaption(String caption){
        this.caption = caption;
    }
    public String getCaption(){
        return this.caption;
    }
    public void setCopyright(String copyright){
        this.copyright = copyright;
    }
    public String getCopyright(){
        return this.copyright;
    }
    public void setApproved_for_syndication(int approved_for_syndication){
        this.approved_for_syndication = approved_for_syndication;
    }
    public int getApproved_for_syndication(){
        return this.approved_for_syndication;
    }
    public void setMedia_metadata(ArrayList<Media_Img> media_metadata){
        this.media_metadata = media_metadata;
    }
    public ArrayList<Media_Img> getMedia_metadata(){
        return this.media_metadata;
    }
}
