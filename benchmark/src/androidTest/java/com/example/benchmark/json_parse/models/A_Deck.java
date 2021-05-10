package com.example.benchmark.json_parse.models;


import java.util.List;

public class A_Deck {

    private String name;
    private long extendRev;
    private long usn;
    private boolean collapsed;
    private boolean browserCollapsed;
    private List<Integer> newToday;
    private List<Integer> revToday;
    private List<Integer> lrnToday;
    private List<Integer> timeToday;
    private int dyn;
    private long conf;
    private long id;
    private long mod;
    private String desc;

    public String getName() {
        return name;
    }

    public A_Deck setName(String name) {
        this.name = name;
        return this;
    }

    public long getExtendRev() {
        return extendRev;
    }

    public A_Deck setExtendRev(long extendRev) {
        this.extendRev = extendRev;
        return this;
    }

    public long getUsn() {
        return usn;
    }

    public A_Deck setUsn(long usn) {
        this.usn = usn;
        return this;
    }

    public boolean isCollapsed() {
        return collapsed;
    }

    public A_Deck setCollapsed(boolean collapsed) {
        this.collapsed = collapsed;
        return this;
    }

    public boolean isBrowserCollapsed() {
        return browserCollapsed;
    }

    public A_Deck setBrowserCollapsed(boolean browserCollapsed) {
        this.browserCollapsed = browserCollapsed;
        return this;
    }

    public List<Integer> getNewToday() {
        return newToday;
    }

    public A_Deck setNewToday(List<Integer> newToday) {
        this.newToday = newToday;
        return this;
    }

    public List<Integer> getRevToday() {
        return revToday;
    }

    public A_Deck setRevToday(List<Integer> revToday) {
        this.revToday = revToday;
        return this;
    }

    public List<Integer> getLrnToday() {
        return lrnToday;
    }

    public A_Deck setLrnToday(List<Integer> lrnToday) {
        this.lrnToday = lrnToday;
        return this;
    }

    public List<Integer> getTimeToday() {
        return timeToday;
    }

    public A_Deck setTimeToday(List<Integer> timeToday) {
        this.timeToday = timeToday;
        return this;
    }

    public int getDyn() {
        return dyn;
    }

    public A_Deck setDyn(int dyn) {
        this.dyn = dyn;
        return this;
    }

    public long getConf() {
        return conf;
    }

    public A_Deck setConf(long conf) {
        this.conf = conf;
        return this;
    }

    public long getId() {
        return id;
    }

    public A_Deck setId(long id) {
        this.id = id;
        return this;
    }

    public long getMod() {
        return mod;
    }

    public A_Deck setMod(long mod) {
        this.mod = mod;
        return this;
    }

    public String getDesc() {
        return desc;
    }

    public A_Deck setDesc(String desc) {
        this.desc = desc;
        return this;
    }
}
