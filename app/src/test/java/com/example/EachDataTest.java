package com.example;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import com.example.beattrack.EachData;

@RunWith(JUnit4.class)

public class EachDataTest {
    private EachData eachData;

    @Before
    public void setUp() {
        eachData = new EachData();
    }

    @Test
    public void testDateGetterSetter() {
        String expected = "2023-07-05";
        eachData.setDate(expected);
        String actual = eachData.getDate();
        assertEquals(expected, actual);
    }

    @Test
    public void testTimeGetterSetter() {
        String expected = "12:34";
        eachData.setTime(expected);
        String actual = eachData.getTime();
        assertEquals(expected, actual);
    }

    @Test
    public void testSystolicGetterSetter() {
        String expected = "120";
        eachData.setSystolic(expected);
        String actual = eachData.getSystolic();
        assertEquals(expected, actual);
    }

    @Test
    public void testDiastolicGetterSetter() {
        String expected = "80";
        eachData.setDiastolic(expected);
        String actual = eachData.getDiastolic();
        assertEquals(expected, actual);
    }

    @Test
    public void testHeartRateGetterSetter() {
        String expected = "75";
        eachData.setHeartRate(expected);
        String actual = eachData.getHeartRate();
        assertEquals(expected, actual);
    }

    @Test
    public void testCommentGetterSetter() {
        String expected = "Test comment";
        eachData.setComment(expected);
        String actual = eachData.getComment();
        assertEquals(expected, actual);
    }

}




