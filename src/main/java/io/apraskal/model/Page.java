package io.apraskal.model;

import java.util.List;

public class Page {
    private long cacheKey;
    private List<List<String>> data;

    public Page() {

    }
    
    public Page(long cacheKey, List<List<String>> data) {
        this.cacheKey = cacheKey;
        this.data = data;
    }

    public long getCacheKey() {
        return this.cacheKey;
    }

    public void setCacheKey(long key) {
        this.cacheKey = key;
    }

    public List<List<String>> getData() {
        return this.data;
    }

    public void setData(List<List<String>> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "{Key: " + this.cacheKey + "\n" + "Data: " + this.data + "}\n";
    }
}