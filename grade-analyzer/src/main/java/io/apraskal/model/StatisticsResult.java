package io.apraskal.model;

public class StatisticsResult {
    private double cacheKey;
    private double mean;
    private double median;
    private double mode;
    private double variance;
    private double sd;
    private double range;
    private double iqr;

    public StatisticsResult() {}

    public StatisticsResult(double cacheKey, double mean, double median, double mode, double variance, double sd, double range, double iqr) {
        this.cacheKey = cacheKey;
        this.mean = mean;
        this.median = median;
        this.mode = mode;
        this.variance = variance;
        this.sd = sd;
        this.range = range;
        this.iqr = iqr;
    }

    public double getCacheKey() {
        return cacheKey;
    }

    public void setCacheKey(double cacheKey) {
        this.cacheKey = cacheKey;
    }

    public double getMean() {
        return mean;
    }

    public void setMean(double mean) {
        this.mean = mean;
    }

    public double getMedian() {
        return median;
    }

    public void setMedian(double median) {
        this.median = median;
    }

    public double getMode() {
        return mode;
    }

    public void setMode(double mode) {
        this.mode = mode;
    }

    public double getVariance() {
        return variance;
    }

    public void setVariance(double variance) {
        this.variance = variance;
    }

    public double getSd() {
        return sd;
    }

    public void setSd(double sd) {
        this.sd = sd;
    }

    public double getRange() {
        return range;
    }

    public void setRange(double range) {
        this.range = range;
    }

    public double getIqr() {
        return iqr;
    }

    public void setIqr(double iqr) {
        this.iqr = iqr;
    }

    @Override
    public String toString() {
        return "{" +
                "cacheKey=" + cacheKey +
                ", mean=" + mean +
                ", median=" + median +
                ", mode=" + mode +
                ", sd=" + sd +
                ", var=" + variance +
                ", range=" + range +
                ", iqr=" + iqr +
                '}';
    }
}
