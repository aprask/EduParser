package io.apraskal.cache;

import io.apraskal.model.Page;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.TimeUnit;
import java.util.List;

public class MemoryStorage {

    private static MemoryStorage instance;
    private static Lock lock = new ReentrantLock();
    private static List<Page> pageTable;
    private static long pages = 0;

    private MemoryStorage(List<List<String>> data) {
        long cacheKey = pages++;
        Page page = new Page(cacheKey, data);
        System.out.println(page);
    }

    public static MemoryStorage getInstance(List<List<String>> data) {
        try {
            if (lock.tryLock(1, TimeUnit.SECONDS)) {
                try {
                    instance = new MemoryStorage(data);
                } finally {
                    lock.unlock();
                }
            } else {
                Thread.sleep(2000);
                throw new RuntimeException("Could not acquire lock for file manager instance");
            }
        } catch (Exception e) {
            lock.unlock();
            throw new RuntimeException("Exeception occurred: " + e);
        }
        return instance;
    }

    public static List<Page> getPages() {
        return pageTable;
    }
}