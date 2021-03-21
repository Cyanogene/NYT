package com.example.nyt_meneghetti;

import java.util.ArrayList;

public class Docs {
    private transient String abstract_;

    private String web_url;

    private String snippet;

    private String lead_paragraph;

    private String print_section;

    private String print_page;

    private String source;

    private transient ArrayList<Multimedia> multimedia;

    private Headline headline; //titolo

    private ArrayList<Keywords> keywords;

    private String pub_date;

    private String document_type;

    private String news_desk;

    private String section_name;

    private String subsection_name;

    private Byline byline; //autore

    private String type_of_material;

    private String _id;

    private int word_count;

    private String uri;

    public void setAbstract_(String abstract_){
        this.abstract_ = abstract_;
    }
    public String getAbstract_(){
        return this.abstract_;
    }
    public void setWeb_url(String web_url){
        this.web_url = web_url;
    }
    public String getWeb_url(){
        return this.web_url;
    }
    public void setSnippet(String snippet){
        this.snippet = snippet;
    }
    public String getSnippet(){
        return this.snippet;
    }
    public void setLead_paragraph(String lead_paragraph){
        this.lead_paragraph = lead_paragraph;
    }
    public String getLead_paragraph(){
        return this.lead_paragraph;
    }
    public void setPrint_section(String print_section){
        this.print_section = print_section;
    }
    public String getPrint_section(){
        return this.print_section;
    }
    public void setPrint_page(String print_page){
        this.print_page = print_page;
    }
    public String getPrint_page(){
        return this.print_page;
    }
    public void setSource(String source){
        this.source = source;
    }
    public String getSource(){
        return this.source;
    }
    public void setMultimedia(ArrayList<Multimedia> multimedia){
        this.multimedia = multimedia;
    }
    public ArrayList<Multimedia> getMultimedia(){
        return this.multimedia;
    }
    public void setHeadline(Headline headline){
        this.headline = headline;
    }
    public Headline getHeadline(){
        return this.headline;
    }
    public void setKeywords(ArrayList<Keywords> keywords){
        this.keywords = keywords;
    }
    public ArrayList<Keywords> getKeywords(){
        return this.keywords;
    }
    public void setPub_date(String pub_date){
        this.pub_date = pub_date;
    }
    public String getPub_date(){
        return this.pub_date;
    }
    public void setDocument_type(String document_type){
        this.document_type = document_type;
    }
    public String getDocument_type(){
        return this.document_type;
    }
    public void setNews_desk(String news_desk){
        this.news_desk = news_desk;
    }
    public String getNews_desk(){
        return this.news_desk;
    }
    public void setSection_name(String section_name){
        this.section_name = section_name;
    }
    public String getSection_name(){
        return this.section_name;
    }
    public void setSubsection_name(String subsection_name){
        this.subsection_name = subsection_name;
    }
    public String getSubsection_name(){
        return this.subsection_name;
    }
    public void setByline(Byline byline){
        this.byline = byline;
    }
    public Byline getByline(){
        return this.byline;
    }
    public void setType_of_material(String type_of_material){
        this.type_of_material = type_of_material;
    }
    public String getType_of_material(){
        return this.type_of_material;
    }
    public void set_id(String _id){
        this._id = _id;
    }
    public String get_id(){
        return this._id;
    }
    public void setWord_count(int word_count){
        this.word_count = word_count;
    }
    public int getWord_count(){
        return this.word_count;
    }
    public void setUri(String uri){
        this.uri = uri;
    }
    public String getUri(){
        return this.uri;
    }
}
