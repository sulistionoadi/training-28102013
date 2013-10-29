/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package training.web.service;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author adi
 */
public class BarangTest {
    private static AbstractApplicationContext ctx;
    
    public BarangTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        ctx = new ClassPathXmlApplicationContext("classpath*:training/**/applicationContext.xml");
    }

    @AfterClass
    public static void tearDownClass() {
        ctx.registerShutdownHook();
    }

    @Test
    public void hello() {
        System.out.println("Hellow");
    }
}
