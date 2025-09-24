package com.sda.mentoring.tasks;

import org.testng.annotations.Test;

public class AttributePracticeTest {


    @Test(priority = 1, description = "Priority 1", groups = {"smoke"})
    public void testPriority1() {
        System.out.println("Test1");
    }

    @Test(priority = 3, description = "Priority 3", groups = {"regression"})
    public void testPriority3() {
        System.out.println("Test3");
    }

    @Test(priority = 2, description = "Priority 2", groups = {"api"})
    public void testPriority2() {
        System.out.println("Test2");
    }

    @Test(priority = 5, description = "Priority 5", groups = {"smoke", "regression"})
    public void testPriority5() {
        System.out.println("Test5");
    }

    @Test(priority = 4, description = "Priority 4", groups = {"api"})
    public void testPriority4() {
        System.out.println("Test4");
    }


    @Test(enabled = false, description = "Disabled test")
    public void disabledTest() {
        System.out.println("Disabled");
    }


    @Test(timeOut = 3000, description = "Timeout 3s")
    public void testWithTimeoutPass() throws InterruptedException {
        Thread.sleep(2000);
        System.out.println("TimeoutPass");
    }


    @Test(timeOut = 1000, description = "Timeout 1s")
    public void testWithTimeoutFail() throws InterruptedException {
        Thread.sleep(2000);
        System.out.println("TimeoutFail");
    }
}
