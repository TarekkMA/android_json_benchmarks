package com.example.benchmark.json_parse.models;


import java.util.ArrayList;
import java.util.List;

public class N_Model {

    private long sortf;
    private long did;
    private String latexPre;
    private String latexPost;
    private long mod;
    private long usn;
    private List<Object> vers = new ArrayList<Object>();
    private long type;
    private String css;


    public long getSortf() {
        return sortf;
    }

    public void setSortf(long sortf) {
        this.sortf = sortf;
    }

    public long getDid() {
        return did;
    }

    public void setDid(long did) {
        this.did = did;
    }

    public String getLatexPre() {
        return latexPre;
    }

    public void setLatexPre(String latexPre) {
        this.latexPre = latexPre;
    }

    public String getLatexPost() {
        return latexPost;
    }

    public void setLatexPost(String latexPost) {
        this.latexPost = latexPost;
    }

    public long getMod() {
        return mod;
    }

    public void setMod(long mod) {
        this.mod = mod;
    }

    public long getUsn() {
        return usn;
    }

    public void setUsn(long usn) {
        this.usn = usn;
    }

    public List<Object> getVers() {
        return vers;
    }

    public void setVers(List<Object> vers) {
        this.vers = vers;
    }

    public long getType() {
        return type;
    }

    public void setType(long type) {
        this.type = type;
    }

    public String getCss() {
        return css;
    }

    public void setCss(String css) {
        this.css = css;
    }
}
