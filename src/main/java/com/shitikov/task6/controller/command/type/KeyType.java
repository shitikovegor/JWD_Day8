package com.shitikov.task6.controller.command.type;

public enum KeyType {

    ID("id"),
    NAME("name"),
    AUTHOR("author"),
    PUBLISHING_HOUSE("publishinHouse"),
    PAGES("pages");

    String name;

    KeyType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
