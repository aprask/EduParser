package io.apraskal.service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import io.apraskal.model.*;
import io.apraskal.cache.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ClassroomManager {
    // batch id:class
    private static ConcurrentHashMap<Integer, Classroom> classes = new ConcurrentHashMap<>();
    private static ConcurrentLinkedQueue<Classroom> classRoomQueue = new ConcurrentLinkedQueue<>();

    private static Lock classroomQueueLock = new ReentrantLock();

    private ClassroomManager() {

    }

    // public static ClassroomManager getInstance() {
    // }
    
}