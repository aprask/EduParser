package io.apraskal.cache;

import io.apraskal.model.Page;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.TimeUnit;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

public class MemoryStorage {

    private volatile static MemoryStorage instance = null;
    private volatile static AtomicLong pages = new AtomicLong();

    private static Lock lock = new ReentrantLock();
    private static List<Page> pageTable = new ArrayList<>();

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

    public static List<Page> getPages() {
        return pageTable;
    }

    public static void addPage(List<List<String>> data) {
        long cacheKey = pages.getAndIncrement();
        Page page = new Page(cacheKey, data);
        pageTable.add(page);
    }
}