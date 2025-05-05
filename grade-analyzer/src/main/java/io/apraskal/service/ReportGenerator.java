package io.apraskal.service;

import java.lang.Exception;
import java.util.concurrent.locks.*;
import java.util.concurrent.TimeUnit;
import io.apraskal.service.data.ProcessDataManager;
import io.apraskal.model.*;
import java.util.*;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.PDPageContentStream.AppendMode;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import java.nio.file.Path;
import java.io.File;

public class ReportGenerator {
    private volatile static ReportGenerator instance;

    private static Lock instanceCreationLock = new ReentrantLock();

    // TODO -- MAKE CROSS PLATFORM
    private File regularText = new File("src/main/java/io/apraskal/resources/fonts/liberation/LiberationSans-Regular.ttf");
    private File boldedText = new File("src/main/java/io/apraskal/resources/fonts/liberation/LiberationMono-Bold.ttf");

    private ReportGenerator() {}

    public static ReportGenerator getInstance() {
        if (instance == null) {
            try {
                if (instanceCreationLock.tryLock(1, TimeUnit.SECONDS)) {
                    try {
                        instance = new ReportGenerator();
                    } finally {
                        instanceCreationLock.unlock();
                    }
                } else {
                    Thread.sleep(2000);
                    throw new RuntimeException("Could not acquire lock for report gen instance");
                }
            } catch (Exception e) {
                throw new RuntimeException("Exeception occurred: " + e);
            }
        }
        return instance;
    }

    private List<String> formatResult(StatisticsResult statRes) {
        List<String> dataPoints = new ArrayList<String>();
        dataPoints.add(String.format("Mean: %.2f",statRes.getMean()));
        dataPoints.add(String.format("Median: %.2f",statRes.getMedian()));
        dataPoints.add(String.format("Mode: %.2f",statRes.getMode()));
        dataPoints.add(String.format("Variance: %.2f",statRes.getVariance()));
        dataPoints.add(String.format("Standard Deviation: %.2f",statRes.getSd()));
        dataPoints.add(String.format("Range: %.2f",statRes.getRange()));
        dataPoints.add(String.format("IQR: %.2f",statRes.getIqr()));
        return dataPoints;
    }

    private PDPage formatPDFPageContent(PDDocument report, PDPage page, StatisticsResult statRes) {
        List<String> dataPointList = formatResult(statRes);
        float lineSpacing = 30f;
        float currentY = 650f;
        float labelX = 100f; // ex "Mean"
        float valueX = 400f; // ex 42.00
        try {
            PDType0Font boldFont = PDType0Font.load(report, boldedText);
            PDType0Font regularFont = PDType0Font.load(report, regularText);
            try (PDPageContentStream contentStream = new PDPageContentStream(
                report,
                page,
                PDPageContentStream.AppendMode.APPEND,
                true,
                true)) {

            for (String dataPoint : dataPointList) {
                String[] splitDataPoints = dataPoint.split(":");
                if (splitDataPoints.length != 2) continue;

                contentStream.beginText();
                contentStream.setFont(boldFont, 16);
                contentStream.newLineAtOffset(labelX, currentY);
                contentStream.showText(splitDataPoints[0].trim());
                contentStream.endText();

                contentStream.beginText();
                contentStream.setFont(regularFont, 16);
                contentStream.newLineAtOffset(valueX, currentY);
                contentStream.showText(splitDataPoints[1].trim());
                contentStream.endText();

                currentY -= lineSpacing;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Encountered an exception: " + e);
        } 
        return page;
    }


    public void createPDFReport(StatisticsResult statRes, Path writePath) {
        try {
            PDDocument report = new PDDocument();
            PDPage page = new PDPage();
            PDType0Font font = PDType0Font.load(report, boldedText);
            try (PDPageContentStream contentStream = new PDPageContentStream(
                report, 
                page,
                PDPageContentStream.AppendMode.OVERWRITE,
                true,
                true)) {
                contentStream.beginText();
                contentStream.setFont(font, 75);
                contentStream.newLineAtOffset(100, 700);
                contentStream.showText("Statistics");
                contentStream.endText();
            }
            report.addPage(formatPDFPageContent(report, page, statRes));
            report.save(writePath.toString());
        } catch (Exception e) {
            throw new RuntimeException("Encountered an exception: " + e);
        }
    }

}