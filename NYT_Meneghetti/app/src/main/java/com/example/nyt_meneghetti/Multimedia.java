package com.example.nyt_meneghetti;

public class Multimedia {
    private int rank;

    private transient String subtype;
    private transient String caption;
    private transient String credit;
    private transient String type;
    private transient String url;
    private transient int height;
    private transient int width;
    private transient Legacy legacy;
    private transient String subType;
    private transient String crop_name;

    public void setRank(int rank){
        this.rank = rank;
    }
    public int getRank(){
        return this.rank;
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
    public void setCredit(String credit){
        this.credit = credit;
    }
    public String getCredit(){
        return this.credit;
    }
    public void setType(String type){
        this.type = type;
    }
    public String getType(){
        return this.type;
    }
    public void setUrl(String url){
        this.url = url;
    }
    public String getUrl(){
        return this.url;
    }
    public void setHeight(int height){
        this.height = height;
    }
    public int getHeight(){
        return this.height;
    }
    public void setWidth(int width){
        this.width = width;
    }
    public int getWidth(){
        return this.width;
    }
    public void setLegacy(Legacy legacy){
        this.legacy = legacy;
    }
    public Legacy getLegacy(){
        return this.legacy;
    }
    public void setSubType(String subType){
        this.subType = subType;
    }
    public String getSubType(){
        return this.subType;
    }
    public void setCrop_name(String crop_name){
        this.crop_name = crop_name;
    }
    public String getCrop_name(){
        return this.crop_name;
    }
}
