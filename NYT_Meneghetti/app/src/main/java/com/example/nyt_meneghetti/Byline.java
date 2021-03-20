package com.example.nyt_meneghetti;

import java.util.ArrayList;

public class Byline {
    private String original;

    private transient ArrayList<Person> person;

    private transient String organization;

    public void setOriginal(String original){
        this.original = original;
    }
    public String getOriginal(){
        return this.original;
    }
    public void setPerson(ArrayList<Person> person){
        this.person = person;
    }
    public ArrayList<Person> getPerson(){
        return this.person;
    }
    public void setOrganization(String organization){
        this.organization = organization;
    }
    public String getOrganization(){
        return this.organization;
    }
}
