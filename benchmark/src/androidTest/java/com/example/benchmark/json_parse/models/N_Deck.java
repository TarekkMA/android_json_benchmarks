package com.example.benchmark.json_parse.models;


import java.util.ArrayList;
import java.util.List;

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
