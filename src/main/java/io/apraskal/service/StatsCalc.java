package io.apraskal.service;

import java.lang.Exception;
import java.util.concurrent.locks.*;
import java.util.concurrent.TimeUnit;
import java.math.*;
import io.apraskal.service.data.ProcessDataManager;
import io.apraskal.model.*;

public class StatsCalc {
    private volatile static StatsCalc instance;

    private static Lock instanceCreationLock = new ReentrantLock();

    private StatsCalc() {}

    public static StatsCalc getInstance() {
        if (instance == null) {
            try {
                if (instanceCreationLock.tryLock(1, TimeUnit.SECONDS)) {
                    try {
                        instance = new StatsCalc();
                    } finally {
                        instanceCreationLock.unlock();
                    }
                } else {
                    Thread.sleep(2000);
                    throw new RuntimeException("Could not acquire lock for stats calc instance");
                }
            } catch (Exception e) {
                throw new RuntimeException("Exeception occurred: " + e);
            }
        }
        return instance;
    }

    public static double calculateMean(Student[] students) {
        int sum = 0;
        for (int i = 0; i < students.length; i++) sum += students[i].getGrade();
        return sum/students.length;
    }

}