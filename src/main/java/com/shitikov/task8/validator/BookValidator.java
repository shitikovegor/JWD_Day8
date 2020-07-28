package com.shitikov.task8.validator;

public class BookValidator {
    private static final int MIN_PAGES_VALUE = 1;
    private static final int MAX_PAGES_VALUE = 10000;
    private static final int MIN_CHAR_NUMBER = 1;
    private static final int MAX_CHAR_NUMBER = 150;
    private static final int MIN_ID_NUMBER = 1;
    private static final int MAX_ID_NUMBER = 100;

    private static BookValidator instance;

    private BookValidator() {
    }

    public static BookValidator getInstance() {
        if (instance == null) {
            instance = new BookValidator();
        }
        return instance;
    }

    public boolean arePagesCorrect(String pages) {
        try {
            int pagesNumber = Integer.parseInt(pages);
            return pagesNumber >= MIN_PAGES_VALUE && pagesNumber <= MAX_PAGES_VALUE;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean isIdCorrect(String id) {
        try {
            int idNumber = Integer.parseInt(id);
            return idNumber >= MIN_ID_NUMBER && idNumber <= MAX_ID_NUMBER;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean isStringParameterCorrect(String parameter) {
        if (parameter == null) {
            return false;
        }
        String parameterWithoutSpaces = parameter.replace(" ", "");
        int length = parameterWithoutSpaces.length();

        return length >= MIN_CHAR_NUMBER && length <= MAX_CHAR_NUMBER;
    }
}
