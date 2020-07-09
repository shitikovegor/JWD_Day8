package com.shitikov.task6.model.util;

import java.util.UUID;

public class IdGenerator {

    public static String generateId() {
        UUID newId = UUID.randomUUID();
        return newId.toString();
    }
}
