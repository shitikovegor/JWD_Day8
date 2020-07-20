package com.shitikov.task6.validator;

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

    @DataProvider(name = "StringDataInvalid")
    public Object[] createStringData() {
        String maxInvalid = "ssjsjskfkdjdlfldjf kkfldkdihuuvyvbljbbsdfsdfdvrgrgr" +
                "ssjsjskfkdjdlfldj fkkfldkdihuuvyvbljbbsdfsdfdvrgrgr" +
                "ssjsjskfkdjdlfldjfkkfldkdihuuvyv bljbbsdfsdfdvrgrgr" +
                "owsds";

        return new Object[] {null, "", maxInvalid};
    }

    @Test
    public void testIsNameCorrectTrue() {
        boolean condition = validator.isNameCorrect("Java start");
        assertTrue(condition);
    }

    @Test(dataProvider = "StringDataInvalid")
    public void testIsNameCorrectFalse(String name) {
        boolean condition = validator.isNameCorrect(name);
        assertFalse(condition);
    }

    @DataProvider(name = "AuthorDataValid")
    public Object[] createAuthorData() {
        return new Object[] {"Herbert Bos, Andrew S. Tanenbaum", "Herbert Bos"};
    }

    @Test(dataProvider = "AuthorDataValid")
    public void testIsAuthorCorrectTrue(String author) {
        boolean condition = validator.isAuthorCorrect(author);
        assertTrue(condition);
    }

    @Test(dataProvider = "StringDataInvalid")
    public void testIsAuthorCorrectFalse(String author) {
        boolean condition = validator.isAuthorCorrect(author);
        assertFalse(condition);
    }

    @Test
    public void testIsPublishingHouseCorrectTrue() {
        boolean condition = validator.isNameCorrect("Java start");
        assertTrue(condition);
    }

    @Test(dataProvider = "StringDataInvalid")
    public void testIsPublishingHouseCorrectFalse(String publishingHouse) {
        boolean condition = validator.isPublishingHouseCorrect(publishingHouse);
        assertFalse(condition);
    }

    @Test
    public void testArePagesCorrectTrue() {
        boolean condition = validator.arePagesCorrect(253);
        assertTrue(condition);
    }

    @DataProvider(name = "PagesDataInvalid")
    public Object[] createPagesData() {
        return new Object[] {-34, 0, 13432};
    }

    @Test(dataProvider = "PagesDataInvalid")
    public void testArePagesCorrectFalse(int pages) {
        boolean condition = validator.arePagesCorrect(pages);
        assertFalse(condition);
    }
}