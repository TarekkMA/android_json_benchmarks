package com.example.benchmark.json_parse.models;



import com.dslplatform.json.CompiledJson;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(value = {"id"})
@CompiledJson(onUnknown = CompiledJson.Behavior.IGNORE)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class N_Deck {

    private String name;
    private long extendRev;
    private long usn;
    private boolean collapsed;
    private boolean browserCollapsed;
    private int[] newToday;
    private int[] revToday;
    private int[] lrnToday;
    private int[] timeToday;
    private int dyn;
    private long conf;
    private long id;
    private long mod;
    private String desc;

    private static int[] readIntArr(JsonParser jp) throws IOException {
        // Check the first token
        if (jp.currentToken() != JsonToken.START_ARRAY) {
            throw new IllegalStateException("Expected '['");
        }

        int[] arr = new int[2];
        int i = 0;

        while (jp.nextToken() != JsonToken.END_ARRAY) {
            arr[i] = jp.getIntValue();
            i++;
        }

        return arr;
    }

    public static N_Deck createFromJacksonParser(JsonParser jp) throws IOException {
        if (jp.currentToken() != JsonToken.START_OBJECT) {
            throw new IllegalStateException("Expected '{'");
        }
        N_Deck deck = new N_Deck();
        while (jp.nextToken() != JsonToken.END_OBJECT) {
            String field = jp.getCurrentName();
            jp.nextToken(); // move cursor to value token
            switch (field) {
                case "name":
                    deck.setName(jp.getText());
                    break;
                case "extendRev":
                    deck.setExtendRev(jp.getLongValue());
                    break;
                case "usn":
                    deck.setUsn(jp.getLongValue());
                    break;
                case "conf":
                    deck.setConf(jp.getLongValue());
                    break;
                case "id":
                    deck.setId(jp.getLongValue());
                    break;
                case "mod":
                    deck.setMod(jp.getLongValue());
                    break;
                case "desc":
                    deck.setDesc(jp.getText());
                    break;
                case "collapsed":
                    deck.setCollapsed(jp.getBooleanValue());
                    break;
                case "browserCollapsed":
                    deck.setBrowserCollapsed(jp.getBooleanValue());
                    break;
                case "newToday":
                    deck.setNewToday(readIntArr(jp));
                    break;
                case "lrnToday":
                    deck.setLrnToday(readIntArr(jp));
                    break;
                case "revToday":
                    deck.setRevToday(readIntArr(jp));
                    break;
                case "timeToday":
                    deck.setTimeToday(readIntArr(jp));
                    break;
                default:
                    jp.skipChildren();
            }
        }
        return deck;
    }

    public String getName() {
        return name;
    }

    public N_Deck setName(String name) {
        this.name = name;
        return this;
    }

    public long getExtendRev() {
        return extendRev;
    }

    public N_Deck setExtendRev(long extendRev) {
        this.extendRev = extendRev;
        return this;
    }

    public long getUsn() {
        return usn;
    }

    public N_Deck setUsn(long usn) {
        this.usn = usn;
        return this;
    }

    public boolean isCollapsed() {
        return collapsed;
    }

    public N_Deck setCollapsed(boolean collapsed) {
        this.collapsed = collapsed;
        return this;
    }

    public boolean isBrowserCollapsed() {
        return browserCollapsed;
    }

    public N_Deck setBrowserCollapsed(boolean browserCollapsed) {
        this.browserCollapsed = browserCollapsed;
        return this;
    }

    public int[] getNewToday() {
        return newToday;
    }

    public N_Deck setNewToday(int[] newToday) {
        this.newToday = newToday;
        return this;
    }

    public int[] getRevToday() {
        return revToday;
    }

    public N_Deck setRevToday(int[] revToday) {
        this.revToday = revToday;
        return this;
    }

    public int[] getLrnToday() {
        return lrnToday;
    }

    public N_Deck setLrnToday(int[] lrnToday) {
        this.lrnToday = lrnToday;
        return this;
    }

    public int[] getTimeToday() {
        return timeToday;
    }

    public N_Deck setTimeToday(int[] timeToday) {
        this.timeToday = timeToday;
        return this;
    }

    public int getDyn() {
        return dyn;
    }

    public N_Deck setDyn(int dyn) {
        this.dyn = dyn;
        return this;
    }

    public long getConf() {
        return conf;
    }

    public N_Deck setConf(long conf) {
        this.conf = conf;
        return this;
    }

    public long getId() {
        return id;
    }

    public N_Deck setId(long id) {
        this.id = id;
        return this;
    }

    public long getMod() {
        return mod;
    }

    public N_Deck setMod(long mod) {
        this.mod = mod;
        return this;
    }

    public String getDesc() {
        return desc;
    }

    public N_Deck setDesc(String desc) {
        this.desc = desc;
        return this;
    }
}
