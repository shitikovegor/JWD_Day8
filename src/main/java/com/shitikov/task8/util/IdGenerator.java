package com.shitikov.task8.util;

public class IdGenerator {
    private static int id = 0;

    private IdGenerator() {
    }

    public static int generateId() {
        id++;
        return id;
    }
}
