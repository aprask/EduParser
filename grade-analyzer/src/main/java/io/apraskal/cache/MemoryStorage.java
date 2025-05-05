package io.apraskal.cache;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import io.apraskal.model.StudentPage;

public class MemoryStorage {

    private volatile static MemoryStorage instance;
    private volatile static AtomicLong studentPages = new AtomicLong();

    private static final Lock lock = new ReentrantLock();

    private static final List<StudentPage> studentPageTable = new ArrayList<>();

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
            } catch (InterruptedException | RuntimeException e) {
                throw new RuntimeException("Exeception occurred: " + e);
            }
        }
        return instance;
    }

    public static StudentPage getStudentPage(long cacheKey) {
        for (int i = 0; i < studentPageTable.size(); i++) if (studentPageTable.get(i).getCacheKey() == cacheKey) return studentPageTable.get(i);
        throw new RuntimeException("Cannot find page from specified cache key: " + cacheKey);
    }

    public static void addStudentPage(List<List<String>> data) {
        long cacheKey = studentPages.getAndIncrement();
        StudentPage page = new StudentPage(cacheKey, data);
        studentPageTable.add(page);    
    }

    public List<StudentPage> getAllStudentPages() {
        return studentPageTable;
    }

}