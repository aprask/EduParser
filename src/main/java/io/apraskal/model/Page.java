package io.apraskal.model;

public class Page {
    private long cacheKey;
    private String data;

    public Page() {

    }
    
    public Page(long cacheKey, String data) {
        this.cacheKey = cacheKey;
        this.data = data;
    }

    public long getCacheKey() {
        return this.cacheKey;
    }

    public void setCacheKey(long key) {
        this.cacheKey = key;
    }

    public String getData() {
        return this.data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "{Key: " + this.cacheKey + "\n" + "Data: " + this.data + "}\n";
    }
}