
package com.example.benchmark.json_parse.models.object_model;

import android.annotation.SuppressLint;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressLint("NonPublicNonStaticFieldName")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OModel {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("type")
    private Long type;

    @JsonProperty("mod")
    private Long mod;

    @JsonProperty("usn")
    private Long usn;

    @JsonProperty("sortf")
    private Long mSortingField;

    @JsonProperty("did")
    private Long mDeckId;

    @JsonProperty("tmpls")
    private List<OTemplate> mTemplates = new ArrayList<>();

    @JsonProperty("flds")
    private List<OField> mFields = new ArrayList<>();

    @JsonProperty("css")
    private String css;

    @JsonProperty("latexPre")
    private String latexPre;

    @JsonProperty("latexPost")
    private String latexPost;

    @JsonProperty("latexsvg")
    private Boolean latexsvg;

    @JsonProperty("req")
    private List<ORequirement> mRequirements = new ArrayList<>();

    @JsonProperty("tags")
    private List<Object> tags = new ArrayList<>();

    @JsonProperty("vers")
    private List<Object> vers = new ArrayList<>();

    @JsonIgnore
    private final Map<String, Object> additionalProperties = new HashMap<>();

    /**
     * No args constructor for use in serialization
     *
     */
    public OModel() {
    }


    public OModel(Long id, String name, Long type, Long mod, Long usn, Long sortf, Long did, List<OTemplate> tmpls, List<OField> flds, String css, String latexPre, String latexPost, Boolean latexsvg, List<ORequirement> req, List<Object> tags, List<Object> vers) {
        super();
        this.id = id;
        this.name = name;
        this.type = type;
        this.mod = mod;
        this.usn = usn;
        this.mSortingField = sortf;
        this.mDeckId = did;
        this.mTemplates = tmpls;
        this.mFields = flds;
        this.css = css;
        this.latexPre = latexPre;
        this.latexPost = latexPost;
        this.latexsvg = latexsvg;
        this.mRequirements = req;
        this.tags = tags;
        this.vers = vers;
    }

    @JsonProperty("id")
    public Long getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Long id) {
        this.id = id;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("type")
    public Long getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(Long type) {
        this.type = type;
    }

    @JsonProperty("mod")
    public Long getMod() {
        return mod;
    }

    @JsonProperty("mod")
    public void setMod(Long mod) {
        this.mod = mod;
    }

    @JsonProperty("usn")
    public Long getUsn() {
        return usn;
    }

    @JsonProperty("usn")
    public void setUsn(Long usn) {
        this.usn = usn;
    }

    @JsonProperty("sortf")
    public Long getSortingField() {
        return mSortingField;
    }

    @JsonProperty("sortf")
    public void setSortingField(Long sortingField) {
        this.mSortingField = sortingField;
    }

    @JsonProperty("did")
    public Long getDeckId() {
        return mDeckId;
    }

    @JsonProperty("did")
    public void setDeckId(Long deckId) {
        this.mDeckId = deckId;
    }

    @JsonProperty("tmpls")
    public List<OTemplate> getTemplates() {
        return mTemplates;
    }

    @JsonProperty("tmpls")
    public void setTemplates(List<OTemplate> templates) {
        this.mTemplates = templates;
    }

    @JsonProperty("flds")
    public List<OField> getFields() {
        return mFields;
    }

    @JsonProperty("flds")
    public void setFields(List<OField> fields) {
        this.mFields = fields;
    }

    @JsonProperty("css")
    public String getCss() {
        return css;
    }

    @JsonProperty("css")
    public void setCss(String css) {
        this.css = css;
    }

    @JsonProperty("latexPre")
    public String getLatexPre() {
        return latexPre;
    }

    @JsonProperty("latexPre")
    public void setLatexPre(String latexPre) {
        this.latexPre = latexPre;
    }

    @JsonProperty("latexPost")
    public String getLatexPost() {
        return latexPost;
    }

    @JsonProperty("latexPost")
    public void setLatexPost(String latexPost) {
        this.latexPost = latexPost;
    }

    @JsonProperty("latexsvg")
    public Boolean isLatexsvg() {
        return latexsvg;
    }

    @JsonProperty("latexsvg")
    public void setLatexsvg(Boolean latexsvg) {
        this.latexsvg = latexsvg;
    }

    @JsonProperty("req")
    public List<ORequirement> getRequirements() {
        return mRequirements;
    }

    @JsonProperty("req")
    public void setRequirements(List<ORequirement> requirements) {
        this.mRequirements = requirements;
    }

    @JsonProperty("tags")
    public List<Object> getTags() {
        return tags;
    }

    @JsonProperty("tags")
    public void setTags(List<Object> tags) {
        this.tags = tags;
    }

    @JsonProperty("vers")
    public List<Object> getVers() {
        return vers;
    }

    @JsonProperty("vers")
    public void setVers(List<Object> vers) {
        this.vers = vers;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
