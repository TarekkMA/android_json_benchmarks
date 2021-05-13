
package com.example.benchmark.json_parse.models.primitives_model;

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
public class PModel {

    @JsonProperty("id")
    private long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("type")
    private long type;

    @JsonProperty("mod")
    private long mod;

    @JsonProperty("usn")
    private long usn;

    @JsonProperty("sortf")
    private long mSortingField;

    @JsonProperty("did")
    private long mDeckId;

    @JsonProperty("tmpls")
    private List<PTemplate> mTemplates = new ArrayList<>();

    @JsonProperty("flds")
    private List<PField> mFields = new ArrayList<>();

    @JsonProperty("css")
    private String css;

    @JsonProperty("latexPre")
    private String latexPre;

    @JsonProperty("latexPost")
    private String latexPost;

    @JsonProperty("latexsvg")
    private boolean latexsvg;

    @JsonProperty("req")
    private List<PRequirement> mRequirements = new ArrayList<>();

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
    public PModel() {
    }


    public PModel(long id, String name, long type, long mod, long usn, long sortf, long did, List<PTemplate> tmpls, List<PField> flds, String css, String latexPre, String latexPost, boolean latexsvg, List<PRequirement> req, List<Object> tags, List<Object> vers) {
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
    public long getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(long id) {
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
    public long getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(long type) {
        this.type = type;
    }

    @JsonProperty("mod")
    public long getMod() {
        return mod;
    }

    @JsonProperty("mod")
    public void setMod(long mod) {
        this.mod = mod;
    }

    @JsonProperty("usn")
    public long getUsn() {
        return usn;
    }

    @JsonProperty("usn")
    public void setUsn(long usn) {
        this.usn = usn;
    }

    @JsonProperty("sortf")
    public long getSortingField() {
        return mSortingField;
    }

    @JsonProperty("sortf")
    public void setSortingField(long sortingField) {
        this.mSortingField = sortingField;
    }

    @JsonProperty("did")
    public long getDeckId() {
        return mDeckId;
    }

    @JsonProperty("did")
    public void setDeckId(long deckId) {
        this.mDeckId = deckId;
    }

    @JsonProperty("tmpls")
    public List<PTemplate> getTemplates() {
        return mTemplates;
    }

    @JsonProperty("tmpls")
    public void setTemplates(List<PTemplate> templates) {
        this.mTemplates = templates;
    }

    @JsonProperty("flds")
    public List<PField> getFields() {
        return mFields;
    }

    @JsonProperty("flds")
    public void setFields(List<PField> fields) {
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
    public boolean isLatexsvg() {
        return latexsvg;
    }

    @JsonProperty("latexsvg")
    public void setLatexsvg(boolean latexsvg) {
        this.latexsvg = latexsvg;
    }

    @JsonProperty("req")
    public List<PRequirement> getRequirements() {
        return mRequirements;
    }

    @JsonProperty("req")
    public void setRequirements(List<PRequirement> requirements) {
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
