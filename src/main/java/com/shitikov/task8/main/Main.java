package com.shitikov.task8.main;

import com.shitikov.task8.controller.LibraryController;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        System.out.println(LibraryController.getInstance().processRequest("find all", new HashMap<>()));

        Map<String, String> parameters = new HashMap<>();
        parameters.put("name", "Java Software Solutions");
        parameters.put("author", "Lewis J., Loftus W.");
        parameters.put("publishingHouse", "Pearson");
        parameters.put("pages", "800");

        System.out.println(LibraryController.getInstance().processRequest("add book", parameters));
    }
}
