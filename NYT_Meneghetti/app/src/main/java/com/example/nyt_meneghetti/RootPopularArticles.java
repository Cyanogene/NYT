package com.example.nyt_meneghetti;

import java.util.ArrayList;

public class RootPopularArticles {

    private String status;

    private String copyright;

    private int num_results;

    private ArrayList<Results> results;

    public void setStatus(String status){
        this.status = status;
    }
    public String getStatus(){
        return this.status;
    }
    public void setCopyright(String copyright){
        this.copyright = copyright;
    }
    public String getCopyright(){
        return this.copyright;
    }
    public void setNum_results(int num_results){
        this.num_results = num_results;
    }
    public int getNum_results(){
        return this.num_results;
    }
    public void setResults(ArrayList<Results> results){
        this.results = results;
    }
    public ArrayList<Results> getResults(){
        return this.results;
    }
}
