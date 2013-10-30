/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package training.web.service.impl;

import java.util.Date;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import training.web.dao.BarangDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import training.web.dao.TransaksiDao;
import training.web.domain.Barang;
import training.web.domain.BarangPK;
import training.web.domain.Transaksi;
import training.web.service.BelajarService;
import training.web.view.ViewBarang;

/**
 *
 * @author adi
 */

@Service("belajarService")
@Transactional
public class BelajarServiceImpl 
    implements BelajarService {
    
    @Autowired
    private BarangDao barangDao;
    @Autowired
    private TransaksiDao transaksiDao;
    
    @Override
    public void save(Barang b) {
        //RunningNumber number = runningNumberDao.getLastNumber(currnet);
        //b.setDocumentNumber(number.getNumber());
        //number.setNumber(number.getNumber + 1);
        barangDao.save(b);
        //runningNumberDao.update(number);
    }

    @Override
    public void delete(Barang b) {
        barangDao.delete(b);
    }

    @Override
    public Barang findBarangById(BarangPK barangPK) {
        return barangDao.findOne(barangPK);
    }

    @Override
    public Page<Barang> findAllBarang(Pageable pageable) {
        return barangDao.findAll(pageable);
    }

    @Override
    public Long countBarangByTanggal(Date start, Date end) {
        return barangDao.countBarangByTanggal(start, end);
    }

    // select * from barang limit 0, 100
    @Override
    public List<Barang> findBarangByTanggal(
            Date start, Date end, Pageable pageable) {
        return barangDao.findByTanggalBetween(
                start, end, pageable).getContent();
    }

    @Override
    public void save(Transaksi t) {
        transaksiDao.save(t);
    }

    @Override
    public Page<Transaksi> findTransaksiByTanggal(
            Date start, Date end, Pageable pageable) {
        
        Page<Transaksi> result = 
                transaksiDao.findByTanggalBetween(
                start, end, pageable);
        
        for (Transaksi tr : result.getContent()) {
            Hibernate.initialize(tr.getDetails());
        }
        
        return result;
    }

    
}
