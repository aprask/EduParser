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
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class ReportGenerator {
    private volatile static ReportGenerator instance;

    private static Lock instanceCreationLock = new ReentrantLock();

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

    public void createPDFReport(StatisticsResult statRes) {
        try {
            PDDocument report = new PDDocument();
            PDPage page = new PDPage();
            try (PDPageContentStream contentStream = new PDPageContentStream(report, page)) {
                contentStream.beginText();
                contentStream.setFont(PDType1Font.HELVETICA, 12);
                contentStream.newLineAtOffset(100, 700);
                contentStream.showText(statRes.toString());
                contentStream.endText();
            }
            report.addPage(page);
            report.save("/mnt/c/Users/andre/OneDrive/Desktop/OOP_PROJECT/grade_analyzer/gradeanalyzer/src/main/java/io/apraskal/test.pdf");
        } catch (Exception e) {
            throw new RuntimeException("Encountered an exception: " + e);
        }
    }

}