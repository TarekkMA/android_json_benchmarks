package com.example.benchmark.json_parse.models;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

import java.io.IOException;
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

    public static N_Model createFromJacksonParser(JsonParser jp) throws IOException {
        if (jp.currentToken() != JsonToken.START_OBJECT) {
            throw new IllegalStateException("Expected '{'");
        }
        N_Model model = new N_Model();
        while (jp.nextToken() != JsonToken.END_OBJECT) {
            String field = jp.getCurrentName();
            jp.nextToken(); // move cursor to value token
            switch (field) {
                case "did":
                    model.setDid(jp.getLongValue());
                    break;
                case "sortf":
                    model.setSortf(jp.getLongValue());
                    break;
                case "usn":
                    model.setUsn(jp.getLongValue());
                    break;
                case "css":
                    model.setCss(jp.getText());
                    break;
                case "latexPre":
                    model.setLatexPre(jp.getText());
                    break;
                case "latexPost":
                    model.setLatexPost(jp.getText());
                    break;
                case "mod":
                    model.setMod(jp.getLongValue());
                    break;
                case "type":
                    model.setType(jp.getLongValue());
                    break;
                default:
                    jp.skipChildren();
            }
        }
        return model;
    }



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
