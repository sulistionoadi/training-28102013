/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package training.web.service.impl;

import java.util.List;
import org.hibernate.SessionFactory;
import training.web.dao.BarangDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import training.web.domain.Barang;
import training.web.domain.BarangPK;
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
    private SessionFactory sessionFactory;

    @Override
    public void save(Barang b) {
        barangDao.save(b);
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
    public List<ViewBarang> getViewBarang() {
//        return sessionFactory.getCurrentSession()
//                .createSQLQuery("select kode_cabang as kodeCabang from view_barang").list();
        return null;
    }
    
}
