package com.example.nyt_meneghetti;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Results {
    private String uri;

    private String url;

    private long id;

    private long asset_id;

    private String source;

    private transient Calendar published_date;

    private transient Calendar updated;

    private String section;

    private String subsection;

    private String nytdsection;

    private String adx_keywords;

    private String column;

    private String byline;

    private String type;

    private String title;

    private transient String abstract_;

    private ArrayList<String> des_facet;

    private ArrayList<String> org_facet;

    private ArrayList<String> per_facet;

    private ArrayList<String> geo_facet;

    private ArrayList<Media> media;

    private int eta_id;

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getUri() {
        return this.uri;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return this.url;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return this.id;
    }

    public void setAsset_id(long asset_id) {
        this.asset_id = asset_id;
    }

    public long getAsset_id() {
        return this.asset_id;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSource() {
        return this.source;
    }

    public void setPublished_date(Calendar published_date) {
        this.published_date = published_date;
    }

    public Calendar getPublished_date() {
        return this.published_date;
    }

    public void setUpdated(Calendar updated) {
        this.updated = updated;
    }

    public Calendar getUpdated() {
        return this.updated;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getSection() {
        return this.section;
    }

    public void setSubsection(String subsection) {
        this.subsection = subsection;
    }

    public String getSubsection() {
        return this.subsection;
    }

    public void setNytdsection(String nytdsection) {
        this.nytdsection = nytdsection;
    }

    public String getNytdsection() {
        return this.nytdsection;
    }

    public void setAdx_keywords(String adx_keywords) {
        this.adx_keywords = adx_keywords;
    }

    public String getAdx_keywords() {
        return this.adx_keywords;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public String getColumn() {
        return this.column;
    }

    public void setByline(String byline) {
        this.byline = byline;
    }

    public String getByline() {
        return this.byline;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }

    public void setAbstract(String abstract_) {
        this.abstract_ = abstract_;
    }

    public String getAbstract() {
        return this.abstract_;
    }

    public void setDes_facet(ArrayList<String> des_facet) {
        this.des_facet = des_facet;
    }

    public ArrayList<String> getDes_facet() {
        return this.des_facet;
    }

    public void setOrg_facet(ArrayList<String> org_facet) {
        this.org_facet = org_facet;
    }

    public ArrayList<String> getOrg_facet() {
        return this.org_facet;
    }

    public void setPer_facet(ArrayList<String> per_facet) {
        this.per_facet = per_facet;
    }

    public ArrayList<String> getPer_facet() {
        return this.per_facet;
    }

    public void setGeo_facet(ArrayList<String> geo_facet) {
        this.geo_facet = geo_facet;
    }

    public ArrayList<String> getGeo_facet() {
        return this.geo_facet;
    }

    public void setMedia(ArrayList<Media> media) {
        this.media = media;
    }

    public ArrayList<Media> getMedia() {
        return this.media;
    }

    public void setEta_id(int eta_id) {
        this.eta_id = eta_id;
    }

    public int getEta_id() {
        return this.eta_id;
    }
}
