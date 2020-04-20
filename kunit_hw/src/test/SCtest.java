package test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import app.StringCalculator;

public class SCtest {

    StringCalculator calculator;

    // assertEquals: 看是否跟我們預期的答案一樣

    @Before
    public void init()
    {
        calculator = new StringCalculator();
    }

    @Test
    public void testDefaultDelimiters()
    {
        String input = "1\n2,3";
        assertEquals(6,calculator.add(input));
    }

    @Test
    public void testMultiDelimiters()
    {
        String input = "//[***][%]\n1***2%3";
        assertEquals(6,calculator.add(input));
    }

    @Test
    public void testNumbersMoreThan1000()
    {
        String input = "//[***][%]\n1***2%3,2000";
        assertEquals(6,calculator.add(input));
    }

    @Test // Test1
    public void test1()
    {//未添加)**)*的token
        String input = "//[***][))][%]\n1)**)*2%3))24";
        assertEquals(12,calculator.add(input));
    }
    @Test // Test2
    public void test2()
    {
        String input = "//[***][))][%][)**)*]\n1)**)*2%3))24";
        assertEquals(30,calculator.add(input));
    }

    @Test // Test3
    public void test3()
    {
        String input = "//[***][%][))***]\n1***2%3))***21";
        assertEquals(27,calculator.add(input));
    }

    @Test // Test4
    public void test4()
    {//答案不符
        String input = "//[***][%][))***]\n1***2%3))***21";
        assertEquals(27,calculator.add(input));
    }
    @Test // Test5
    public void test5()
    {//最後產生空字串錯了
        String input = "//[***][%][))][**]\n1***))2%3";
        assertEquals(0,calculator.add(input));
    }
}
