
package com.example.benchmark.json_parse.models.primitives_model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PField {

    @JsonProperty("name")
    private String mName;
    @JsonProperty("ord")
    private long mOrd;
    @JsonProperty("sticky")
    private boolean mSticky;
    @JsonProperty("rtl")
    private boolean mRtl;
    @JsonProperty("font")
    private String mFont;
    @JsonProperty("size")
    private long mSize;
    @JsonProperty("media")
    private List<Object> mMedia = new ArrayList<Object>();
    @JsonProperty("single line")
    private boolean mSingleLine;
    @JsonIgnore
    private final Map<String, Object> mAdditionalProperties = new HashMap<>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public PField() {
    }


    public PField(String name, long ord, boolean sticky, boolean rtl, String font, long size, List<Object> media, boolean singleLine) {
        super();
        this.mName = name;
        this.mOrd = ord;
        this.mSticky = sticky;
        this.mRtl = rtl;
        this.mFont = font;
        this.mSize = size;
        this.mMedia = media;
        this.mSingleLine = singleLine;
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
    public long getOrd() {
        return mOrd;
    }

    @JsonProperty("ord")
    public void setOrd(long ord) {
        this.mOrd = ord;
    }

    @JsonProperty("sticky")
    public boolean isSticky() {
        return mSticky;
    }

    @JsonProperty("sticky")
    public void setSticky(boolean sticky) {
        this.mSticky = sticky;
    }

    @JsonProperty("rtl")
    public boolean isRtl() {
        return mRtl;
    }

    @JsonProperty("rtl")
    public void setRtl(boolean rtl) {
        this.mRtl = rtl;
    }

    @JsonProperty("font")
    public String getFont() {
        return mFont;
    }

    @JsonProperty("font")
    public void setFont(String font) {
        this.mFont = font;
    }

    @JsonProperty("size")
    public long getSize() {
        return mSize;
    }

    @JsonProperty("size")
    public void setSize(long size) {
        this.mSize = size;
    }

    @JsonProperty("media")
    public List<Object> getMedia() {
        return mMedia;
    }

    @JsonProperty("media")
    public void setMedia(List<Object> media) {
        this.mMedia = media;
    }

    @JsonProperty("single line")
    public boolean isSingleLine() {
        return mSingleLine;
    }

    @JsonProperty("single line")
    public void setSingleLine(boolean singleLine) {
        this.mSingleLine = singleLine;
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
