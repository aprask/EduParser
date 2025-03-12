package io.apraskal.service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import io.apraskal.model.Page;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.TimeUnit;
import java.util.List;
import java.lang.Exception;
import java.lang.StringBuilder;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import io.apraskal.cache.*;
import io.apraskal.model.*;
import io.apraskal.service.*;

public class ProcessDataManager {
    private volatile static ProcessDataManager instance;
    private volatile static List<Page> pages = new ArrayList<>();

    private static Lock processDataLock = new ReentrantLock();
    private static Lock pageReplacementLock = new ReentrantLock();
    private static Lock transformDataLock = new ReentrantLock();

    private ProcessDataManager() {
        
    }

    public static ProcessDataManager getInstance() {
        if (instance == null) {
            try {
                if (processDataLock.tryLock(1, TimeUnit.SECONDS)) {
                    try {
                        instance = new ProcessDataManager();
                    } finally {
                        processDataLock.unlock();
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException("Exeception occurred: " + e);
            }
        }
        return instance;
    }

    public static void pageReplace(List<Page> newPages) {
        if (pages != null) {
            try {
                if (processDataLock.tryLock(1, TimeUnit.SECONDS)) {
                    try {
                        pages = newPages;
                    } finally {
                        processDataLock.unlock();
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException("Exeception occurred: " + e);
            }
        }
    }

    public static void transformData() {
        try {
            if (transformDataLock.tryLock(1, TimeUnit.SECONDS)) {
                try {
                    for (int i = 0; i < pages.size(); i++) {
                        Page currentPage = pages.get(i);
                        List<List<String>> allRows = currentPage.getData();
                        for (int j = 0; j < allRows.size(); j++) {
                            System.out.println(allRows.get(j));
                        }
                    }
                } finally {
                    transformDataLock.unlock();
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Exeception occurred: " + e);
        }
    }
}