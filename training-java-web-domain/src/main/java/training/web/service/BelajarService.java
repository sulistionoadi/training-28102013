/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package training.web.service;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import training.web.domain.Barang;
import training.web.domain.BarangPK;
import training.web.view.ViewBarang;

/**
 *
 * @author adi
 */
public interface BelajarService {
    
    public void save(Barang b);
    public void delete(Barang b);
    public Barang findBarangById(BarangPK barangPK);
    public Page<Barang> findAllBarang(Pageable pageable);
    
    public List<ViewBarang> getViewBarang();
    
}
