package com.example.nyt_meneghetti;

public class RootFindArticles {
    private String status;

    private String copyright;

    private transient Response response;

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
    public void setResponse(Response response){
        this.response = response;
    }
    public Response getResponse(){
        return this.response;
    }
}
