package com.shitikov.task8.controller.command.type;

public enum CommandResponse {
    ADD_RESPONSE ("Book added to library"),
    NO_ADD_RESPONSE ("Book didn't add to library"),
    REMOVE_RESPONSE ("Book removed from library"),
    NOT_EXIST_RESPONSE ("Book doesn't exist in library"),
    FOUND_RESPONSE ("Books found"),
    BOOK_NOT_FOUND_RESPONSE("Book not found"),
    SORT_RESPONSE ("Books sorted by "),
    BAD_RESPONSE ("Something went wrong. ");

    private String message;

    CommandResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
