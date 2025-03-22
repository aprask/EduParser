package io.apraskal.cache;

import io.apraskal.model.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.TimeUnit;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import io.apraskal.model.Exam;

public class MemoryStorage {

    private volatile static MemoryStorage instance;
    private volatile static AtomicLong studentPages = new AtomicLong();
    private volatile static AtomicLong examPages = new AtomicLong();

    private static Lock lock = new ReentrantLock();
    private static Lock studentInsertLock = new ReentrantLock();
    private static Lock examInsertLock = new ReentrantLock();

    private static Vector<StudentPage> studentPageTable = new Vector<>();
    private static Vector<ExamPage> examPageTable = new Vector<>();

    private MemoryStorage() {}

    public static MemoryStorage getInstance() {
        if (instance == null) {
            try {
                if (lock.tryLock(1, TimeUnit.SECONDS)) {
                    try {
                        instance = new MemoryStorage();
                    } finally {
                        lock.unlock();
                    }
                } else {
                    Thread.sleep(2000);
                    throw new RuntimeException("Could not acquire lock for file manager instance");
                }
            } catch (Exception e) {
                throw new RuntimeException("Exeception occurred: " + e);
            }
        }
        return instance;
    }

    public static StudentPage getStudentPage(long cacheKey) {
        for (int i = 0; i < studentPageTable.size(); i++) if (studentPageTable.get(i).getCacheKey() == cacheKey) return studentPageTable.get(i);
        throw new RuntimeException("Cannot find page from specified cache key: " + cacheKey);
    }

    public static ExamPage getExamPage(long cacheKey) {
        for (int i = 0; i < examPageTable.size(); i++) if (examPageTable.get(i).getCacheKey() == cacheKey) return examPageTable.get(i);
        throw new RuntimeException("Cannot find page from specified cache key: " + cacheKey);
    }

    public static void addStudentPage(List<List<String>> data) {
        try {
            if (studentInsertLock.tryLock(1, TimeUnit.SECONDS)) {
                try {
                    long cacheKey = studentPages.getAndIncrement();
                    StudentPage page = new StudentPage(cacheKey, data);
                    studentPageTable.add(page);
                } finally {
                    studentInsertLock.unlock();
                }
            }
        } catch (Exception e) {
             throw new RuntimeException("Exeception occurred: " + e);
        }
    }

    public static void addExamPage(List<Question> data) {
        try {
            if (examInsertLock.tryLock(1, TimeUnit.SECONDS)) {
                try {
                    long cacheKey = examPages.getAndIncrement();
                    ExamPage page = new ExamPage(cacheKey, data);
                    examPageTable.add(page);
                } finally {
                    examInsertLock.unlock();
                }
            }
        } catch (Exception e) {

        }
    }

    public static Vector<StudentPage> getAllStudentPages() {
        return studentPageTable;
    }

    public static Vector<ExamPage> getAllExamPages() {
        return examPageTable;
    }
}