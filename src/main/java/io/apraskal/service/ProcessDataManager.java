package io.apraskal.service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.TimeUnit;
import java.lang.Exception;
import java.lang.StringBuilder;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import io.apraskal.cache.*;

public class ProcessDataManager {
    private static ProcessDataManager instance;
    private static String data;

    private static Lock processDataLock = new ReentrantLock();

    private ProcessDataManager(String data) {
        this.data = data;
    }
}