package com.shitikov.task8.validator;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class BookValidatorTest {
    BookValidator validator;

    @BeforeClass
    public void setUp() {
        validator = BookValidator.getInstance();
    }

    @DataProvider(name = "StringDataValid")
    public Object[] createAuthorData() {
        return new Object[] {"Herbert Bos, Andrew S. Tanenbaum", "Herbert Bos", "Java start"};
    }

    @Test(dataProvider = "StringDataValid")
    public void testisStringParameterCorrectTrue(String parameter) {
        boolean condition = validator.isStringParameterCorrect(parameter);
        assertTrue(condition);
    }

    @DataProvider(name = "StringDataInvalid")
    public Object[] createStringData() {
        String maxInvalid = "ssjsjskfkdjdlfldjf kkfldkdihuuvyvbljbbsdfsdfdvrgrgr" +
                "ssjsjskfkdjdlfldj fkkfldkdihuuvyvbljbbsdfsdfdvrgrgr" +
                "ssjsjskfkdjdlfldjfkkfldkdihuuvyv bljbbsdfsdfdvrgrgr" +
                "owsds";

        return new Object[] {null, "", maxInvalid};
    }

    @Test(dataProvider = "StringDataInvalid")
    public void testIsisStringParameterCorrectFalse(String parameter) {
        boolean condition = validator.isStringParameterCorrect(parameter);
        assertFalse(condition);
    }

    @Test
    public void testArePagesCorrectTrue() {
        boolean condition = validator.arePagesCorrect("253");
        assertTrue(condition);
    }

    @DataProvider(name = "PagesDataInvalid")
    public Object[] createPagesData() {
        return new Object[] {"-34", "0", "13432"};
    }

    @Test(dataProvider = "PagesDataInvalid")
    public void testArePagesCorrectFalse(String pages) {
        boolean condition = validator.arePagesCorrect(pages);
        assertFalse(condition);
    }

    @Test
    public void testIsIdCorrectTrue() {
        boolean condition = validator.isIdCorrect("53");
        assertTrue(condition);
    }

    @DataProvider(name = "IdDataInvalid")
    public Object[] createIdData() {
        return new Object[] {"-34", "0", "134"};
    }

    @Test(dataProvider = "PagesDataInvalid")
    public void testIsIdCorrectFalse(String id) {
        boolean condition = validator.isIdCorrect(id);
        assertFalse(condition);
    }
}