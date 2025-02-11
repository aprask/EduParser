package io.apraskal.model;

import java.math.BigDecimal;
import java.util.List;

public class StatisticsResult {

    private BigDecimal mean;
    private BigDecimal mode;
    private BigDecimal range;
    private BigDecimal median;
    private BigDecimal variance;
    private BigDecimal standardDeviation;
    private BigDecimal IQR;
    private Boolean isNormallyDistributed;
    private BigDecimal skewness;
    private BigDecimal kurtosis;
    private BigDecimal firstQuartile;
    private BigDecimal thirdQuartile;
    private BigDecimal percentile90;
    private BigDecimal percentile95;
    private BigDecimal minimum;
    private BigDecimal maximum;
    private Long count;
    private BigDecimal sum;
    private BigDecimal coefficientOfVariation;
    private BigDecimal confidenceIntervalLowerBound;
    private BigDecimal confidenceIntervalUpperBound;
    private List<BigDecimal> outliers;
    private BigDecimal sumOfSquares;
    private Integer modeCount;

    public StatisticsResult() {
    }

    public StatisticsResult(BigDecimal mean, BigDecimal mode, BigDecimal range, BigDecimal median, BigDecimal variance,
                            BigDecimal standardDeviation, BigDecimal IQR, Boolean isNormallyDistributed, BigDecimal skewness,
                            BigDecimal kurtosis, BigDecimal firstQuartile, BigDecimal thirdQuartile, BigDecimal percentile90,
                            BigDecimal percentile95, BigDecimal minimum, BigDecimal maximum, Long count, BigDecimal sum,
                            BigDecimal coefficientOfVariation, BigDecimal confidenceIntervalLowerBound, BigDecimal confidenceIntervalUpperBound,
                            List<BigDecimal> outliers, BigDecimal sumOfSquares, Integer modeCount) {
        this.mean = mean;
        this.mode = mode;
        this.range = range;
        this.median = median;
        this.variance = variance;
        this.standardDeviation = standardDeviation;
        this.IQR = IQR;
        this.isNormallyDistributed = isNormallyDistributed;
        this.skewness = skewness;
        this.kurtosis = kurtosis;
        this.firstQuartile = firstQuartile;
        this.thirdQuartile = thirdQuartile;
        this.percentile90 = percentile90;
        this.percentile95 = percentile95;
        this.minimum = minimum;
        this.maximum = maximum;
        this.count = count;
        this.sum = sum;
        this.coefficientOfVariation = coefficientOfVariation;
        this.confidenceIntervalLowerBound = confidenceIntervalLowerBound;
        this.confidenceIntervalUpperBound = confidenceIntervalUpperBound;
        this.outliers = outliers;
        this.sumOfSquares = sumOfSquares;
        this.modeCount = modeCount;
    }

    public BigDecimal getMean() {
        return mean;
    }

    public void setMean(BigDecimal mean) {
        this.mean = mean;
    }

    public BigDecimal getMode() {
        return mode;
    }

    public void setMode(BigDecimal mode) {
        this.mode = mode;
    }

    public BigDecimal getRange() {
        return range;
    }

    public void setRange(BigDecimal range) {
        this.range = range;
    }

    public BigDecimal getMedian() {
        return median;
    }

    public void setMedian(BigDecimal median) {
        this.median = median;
    }

    public BigDecimal getVariance() {
        return variance;
    }

    public void setVariance(BigDecimal variance) {
        this.variance = variance;
    }

    public BigDecimal getStandardDeviation() {
        return standardDeviation;
    }

    public void setStandardDeviation(BigDecimal standardDeviation) {
        this.standardDeviation = standardDeviation;
    }

    public BigDecimal getIQR() {
        return IQR;
    }

    public void setIQR(BigDecimal IQR) {
        this.IQR = IQR;
    }

    public Boolean getIsNormallyDistributed() {
        return isNormallyDistributed;
    }

    public void setIsNormallyDistributed(Boolean isNormallyDistributed) {
        this.isNormallyDistributed = isNormallyDistributed;
    }

    public BigDecimal getSkewness() {
        return skewness;
    }

    public void setSkewness(BigDecimal skewness) {
        this.skewness = skewness;
    }

    public BigDecimal getKurtosis() {
        return kurtosis;
    }

    public void setKurtosis(BigDecimal kurtosis) {
        this.kurtosis = kurtosis;
    }

    public BigDecimal getFirstQuartile() {
        return firstQuartile;
    }

    public void setFirstQuartile(BigDecimal firstQuartile) {
        this.firstQuartile = firstQuartile;
    }

    public BigDecimal getThirdQuartile() {
        return thirdQuartile;
    }

    public void setThirdQuartile(BigDecimal thirdQuartile) {
        this.thirdQuartile = thirdQuartile;
    }

    public BigDecimal getPercentile90() {
        return percentile90;
    }

    public void setPercentile90(BigDecimal percentile90) {
        this.percentile90 = percentile90;
    }

    public BigDecimal getPercentile95() {
        return percentile95;
    }

    public void setPercentile95(BigDecimal percentile95) {
        this.percentile95 = percentile95;
    }

    public BigDecimal getMinimum() {
        return minimum;
    }

    public void setMinimum(BigDecimal minimum) {
        this.minimum = minimum;
    }

    public BigDecimal getMaximum() {
        return maximum;
    }

    public void setMaximum(BigDecimal maximum) {
        this.maximum = maximum;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    public BigDecimal getCoefficientOfVariation() {
        return coefficientOfVariation;
    }

    public void setCoefficientOfVariation(BigDecimal coefficientOfVariation) {
        this.coefficientOfVariation = coefficientOfVariation;
    }

    public BigDecimal getConfidenceIntervalLowerBound() {
        return confidenceIntervalLowerBound;
    }

    public void setConfidenceIntervalLowerBound(BigDecimal confidenceIntervalLowerBound) {
        this.confidenceIntervalLowerBound = confidenceIntervalLowerBound;
    }

    public BigDecimal getConfidenceIntervalUpperBound() {
        return confidenceIntervalUpperBound;
    }

    public void setConfidenceIntervalUpperBound(BigDecimal confidenceIntervalUpperBound) {
        this.confidenceIntervalUpperBound = confidenceIntervalUpperBound;
    }

    public List<BigDecimal> getOutliers() {
        return outliers;
    }

    public void setOutliers(List<BigDecimal> outliers) {
        this.outliers = outliers;
    }

    public BigDecimal getSumOfSquares() {
        return sumOfSquares;
    }

    public void setSumOfSquares(BigDecimal sumOfSquares) {
        this.sumOfSquares = sumOfSquares;
    }

    public Integer getModeCount() {
        return modeCount;
    }

    public void setModeCount(Integer modeCount) {
        this.modeCount = modeCount;
    }
}
