package com.shitikov.task8.validator;

public class BookValidator {
    private static final int MIN_PAGES_VALUE = 1;
    private static final int MAX_PAGES_VALUE = 10000;
    private static final int MIN_CHAR_NUMBER = 1;
    private static final int MAX_CHAR_NUMBER = 150;

    private static BookValidator instance;

    private BookValidator() {
    }

    public static BookValidator getInstance() {
        if (instance == null) {
            instance = new BookValidator();
        }
        return instance;
    }

    public boolean isNameCorrect(String name) {
        if (name == null) {
            return false;
        }
        return isStringParameterCorrect(name);
    }

    public boolean isAuthorCorrect(String author) {
        if (author == null) {
            return false;
        }
        return isStringParameterCorrect(author);
    }

    public boolean isPublishingHouseCorrect(String publishingHouse) {
        if (publishingHouse == null) {
            return false;
        }
        return isStringParameterCorrect(publishingHouse);
    }

    public boolean arePagesCorrect(int pages) {
        return pages >= MIN_PAGES_VALUE && pages <= MAX_PAGES_VALUE;
    }

    private boolean isStringParameterCorrect(String parameter) {
        String parameterWithoutSpaces = parameter.replace(" ", "");
        int length = parameterWithoutSpaces.length();

        return length >= MIN_CHAR_NUMBER && length <= MAX_CHAR_NUMBER;
    }
}
