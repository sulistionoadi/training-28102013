/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package training.web.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import org.joda.time.DateTime;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import training.web.domain.Barang;
import training.web.domain.BarangPK;
import training.web.domain.Transaksi;
import training.web.domain.TransaksiDetail;

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

    //@Test
    public void hello() {
        Barang b = new Barang();
        b.setId(new BarangPK("B-004", "KLM"));
        b.setDeskripsi("LCD TV 24 Inch");
        b.setHarga(new BigDecimal("3500000"));
        b.setTanggal(new Date());
        service.save(b);
        
        PageRequest pageRequest = 
                new PageRequest(0, 3, 
                    new Sort(Sort.Direction.ASC, "id.kode", "id.cabang"));
        
        List<Barang> listBarang = 
                service.findAllBarang(pageRequest).getContent();
        for (Barang barang : listBarang) {
            System.out.println(barang.toString());
        }
    }
    
    
//    @Test
    public void testFindByTanggal(){
        PageRequest pageRequest = 
                new PageRequest(0, 10, 
                    new Sort(Sort.Direction.ASC, 
                        "id.kode", "id.cabang"));
        
        DateTime tgl1 = new DateTime("2013-10-28");
        DateTime tgl2 = tgl1.plusDays(1);
        
        List<Barang> listBarang = 
                service.findBarangByTanggal(
                tgl1.toDate(), tgl2.toDate(), pageRequest);
        for (Barang barang : listBarang) {
            System.out.println(barang.toString());
        }
    }
    
    @Test
    public void testSaveTransaksi(){
        
        Transaksi transaksi = new Transaksi();
        transaksi.setTanggal(new Date());
        
        Barang b1 = service.findBarangById(
                new BarangPK("B-001", "KLM"));
        Barang b2 = service.findBarangById(
                new BarangPK("B-002", "KLM"));
        
        TransaksiDetail d1 = new TransaksiDetail();
        d1.setHeader(transaksi);
        d1.setBarang(b1);
        d1.setQty(5);
        d1.setHarga(b1.getHarga());
        d1.setTotal(d1.getHarga()
                .multiply(new BigDecimal(d1.getQty())));
        transaksi.getDetails().add(d1);
        
        TransaksiDetail d2 = new TransaksiDetail();
        d2.setHeader(transaksi);
        d2.setBarang(b2);
        d2.setQty(3);
        d2.setHarga(b2.getHarga());
        d2.setTotal(d2.getHarga()
                .multiply(new BigDecimal(d2.getQty())));
        transaksi.getDetails().add(d2);
        
        service.save(transaksi);
    }
}

