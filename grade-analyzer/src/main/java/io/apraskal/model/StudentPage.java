package io.apraskal.model;

import java.util.List;
import io.apraskal.model.*;

public class StudentPage extends DBEntry {
    private List<List<String>> data;

    public StudentPage() {
    }
    
    public StudentPage(long cacheKey, List<List<String>> data) {
        super(cacheKey);
        System.out.println("This objects key: " + cacheKey);
        this.data = data;
    }

    @Override
    public long getCacheKey() {
        return super.cacheKey;
    }

    @Override
    public void setCacheKey(long cacheKey) {
        super.cacheKey = cacheKey;
    }

    public List<List<String>> getData() {
        return this.data;
    }

    public void setData(List<List<String>> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "{Key: " + super.cacheKey + "\n" + "Data: " + this.data + "}\n";
    }
}