
package com.example.benchmark.json_parse.models.object_model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class OTemplate {

    @JsonProperty("name")
    private String mName;
    @JsonProperty("ord")
    private Long mOrd;
    @JsonProperty("qfmt")
    private String mQfmt;
    @JsonProperty("afmt")
    private String mAfmt;
    @JsonProperty("bqfmt")
    private String mBqfmt;
    @JsonProperty("bafmt")
    private String mBafmt;
    @JsonProperty("did")
    private Object mDid;
    @JsonProperty("bfont")
    private String mBfont;
    @JsonProperty("bsize")
    private Long mBsize;
    @JsonIgnore
    private final Map<String, Object> mAdditionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public OTemplate() {
    }


    public OTemplate(String name, Long ord, String qfmt, String afmt, String bqfmt, String bafmt, Object did, String bfont, Long bsize) {
        super();
        this.mName = name;
        this.mOrd = ord;
        this.mQfmt = qfmt;
        this.mAfmt = afmt;
        this.mBqfmt = bqfmt;
        this.mBafmt = bafmt;
        this.mDid = did;
        this.mBfont = bfont;
        this.mBsize = bsize;
    }

    @JsonProperty("name")
    public String getName() {
        return mName;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.mName = name;
    }

    @JsonProperty("ord")
    public Long getOrd() {
        return mOrd;
    }

    @JsonProperty("ord")
    public void setOrd(Long ord) {
        this.mOrd = ord;
    }

    @JsonProperty("qfmt")
    public String getQfmt() {
        return mQfmt;
    }

    @JsonProperty("qfmt")
    public void setQfmt(String qfmt) {
        this.mQfmt = qfmt;
    }

    @JsonProperty("afmt")
    public String getAfmt() {
        return mAfmt;
    }

    @JsonProperty("afmt")
    public void setAfmt(String afmt) {
        this.mAfmt = afmt;
    }

    @JsonProperty("bqfmt")
    public String getBqfmt() {
        return mBqfmt;
    }

    @JsonProperty("bqfmt")
    public void setBqfmt(String bqfmt) {
        this.mBqfmt = bqfmt;
    }

    @JsonProperty("bafmt")
    public String getBafmt() {
        return mBafmt;
    }

    @JsonProperty("bafmt")
    public void setBafmt(String bafmt) {
        this.mBafmt = bafmt;
    }

    @JsonProperty("did")
    public Object getDid() {
        return mDid;
    }

    @JsonProperty("did")
    public void setDid(Object did) {
        this.mDid = did;
    }

    @JsonProperty("bfont")
    public String getBfont() {
        return mBfont;
    }

    @JsonProperty("bfont")
    public void setBfont(String bfont) {
        this.mBfont = bfont;
    }

    @JsonProperty("bsize")
    public Long getBsize() {
        return mBsize;
    }

    @JsonProperty("bsize")
    public void setBsize(Long bsize) {
        this.mBsize = bsize;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.mAdditionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.mAdditionalProperties.put(name, value);
    }

}
