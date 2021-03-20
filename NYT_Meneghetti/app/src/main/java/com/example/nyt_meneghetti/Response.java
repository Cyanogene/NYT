package com.example.nyt_meneghetti;

import java.util.ArrayList;

public class Response {
    private transient ArrayList<Docs> docs;

    private transient Meta meta;

    public void setDocs(ArrayList<Docs> docs){
        this.docs = docs;
    }
    public ArrayList<Docs> getDocs(){
        return this.docs;
    }
    public void setMeta(Meta meta){
        this.meta = meta;
    }
    public Meta getMeta(){
        return this.meta;
    }
}
