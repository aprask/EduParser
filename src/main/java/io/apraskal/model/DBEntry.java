package io.apraskal.model;

public abstract class DBEntry {
    protected long cacheKey;

    public DBEntry() {}
    
    public DBEntry(long cacheKey) {
        this.cacheKey = cacheKey;
    }

    public abstract long getCacheKey();
    public abstract void setCacheKey(long cacheKey);
}