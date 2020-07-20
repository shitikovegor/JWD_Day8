package com.shitikov.task6.controller.command.type;

public enum KeyType {

    ID("id"),
    NAME("name"),
    AUTHOR("author"),
    PUBLISHING_HOUSE("publishingHouse"),
    PAGES("pages");

    private String name;

    KeyType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
