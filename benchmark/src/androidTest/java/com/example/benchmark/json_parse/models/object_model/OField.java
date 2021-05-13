
package com.example.benchmark.json_parse.models.object_model;

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
public class OField {

    @JsonProperty("name")
    private String mName;
    @JsonProperty("ord")
    private Long mOrd;
    @JsonProperty("sticky")
    private Boolean mSticky;
    @JsonProperty("rtl")
    private Boolean mRtl;
    @JsonProperty("font")
    private String mFont;
    @JsonProperty("size")
    private Long mSize;
    @JsonProperty("media")
    private List<Object> mMedia = new ArrayList<Object>();
    @JsonProperty("single line")
    private Boolean mSingleLine;
    @JsonIgnore
    private final Map<String, Object> mAdditionalProperties = new HashMap<>();

    /**
     * No args constructor for use in serialization
     */
    public OField() {
    }


    public OField(String name, Long ord, Boolean sticky, Boolean rtl, String font, Long size, List<Object> media, Boolean singleLine) {
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
    public Long getOrd() {
        return mOrd;
    }

    @JsonProperty("ord")
    public void setOrd(Long ord) {
        this.mOrd = ord;
    }

    @JsonProperty("sticky")
    public Boolean isSticky() {
        return mSticky;
    }

    @JsonProperty("sticky")
    public void setSticky(Boolean sticky) {
        this.mSticky = sticky;
    }

    @JsonProperty("rtl")
    public Boolean isRtl() {
        return mRtl;
    }

    @JsonProperty("rtl")
    public void setRtl(Boolean rtl) {
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
    public Long getSize() {
        return mSize;
    }

    @JsonProperty("size")
    public void setSize(Long size) {
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
    public Boolean isSingleLine() {
        return mSingleLine;
    }

    @JsonProperty("single line")
    public void setSingleLine(Boolean singleLine) {
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
