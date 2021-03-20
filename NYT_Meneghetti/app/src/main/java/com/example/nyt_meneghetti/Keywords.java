package com.example.nyt_meneghetti;

public class Keywords {
    private String name;

    private String value;

    private int rank;

    private String major;

    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setValue(String value){
        this.value = value;
    }
    public String getValue(){
        return this.value;
    }
    public void setRank(int rank){
        this.rank = rank;
    }
    public int getRank(){
        return this.rank;
    }
    public void setMajor(String major){
        this.major = major;
    }
    public String getMajor(){
        return this.major;
    }
}
