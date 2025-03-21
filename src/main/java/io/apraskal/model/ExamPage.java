package io.apraskal.model;

import java.util.List;
import io.apraskal.model.*;

public class ExamPage extends DBEntry {
    private List<Question> data;

    public ExamPage() {

    }
    
    public ExamPage(long cacheKey, List<Question> data) {
        super(cacheKey);
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

    public List<Question> getData() {
        return this.data;
    }

    public void setData(List<Question> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "{Key: " + super.cacheKey + "\n" + "Data: " + this.data + "}\n";
    }
}