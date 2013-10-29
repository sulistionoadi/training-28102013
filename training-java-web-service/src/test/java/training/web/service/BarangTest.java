/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package training.web.service;

import java.math.BigDecimal;
import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import training.web.domain.Barang;
import training.web.domain.BarangPK;

/**
 *
 * @author adi
 */
public class BarangTest {
    private static AbstractApplicationContext ctx;
    private static BelajarService service;
    
    public BarangTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        ctx = new ClassPathXmlApplicationContext("classpath*:training/**/applicationContext.xml");
        service = (BelajarService) ctx.getBean("belajarService");
    }

    @AfterClass
    public static void tearDownClass() {
        ctx.registerShutdownHook();
    }

    @Test
    public void hello() {
        Barang b = new Barang();
        b.setId(new BarangPK("B-001", "KLM"));
        b.setDeskripsi("LCD TV 32 Inch");
        b.setHarga(new BigDecimal("3500000"));
        b.setTanggal(new Date());
        service.save(b);
        
        PageRequest pageRequest = 
                new PageRequest(0, 20, 
                    new Sort(Sort.Direction.ASC, "id.kode", "id.cabang"));
        
        Page<Barang> listBarang = 
                service.findAllBarang(pageRequest);
        
    }
}
