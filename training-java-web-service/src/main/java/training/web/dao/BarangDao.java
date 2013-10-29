/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package training.web.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import training.web.domain.Barang;
import training.web.domain.BarangPK;

/**
 *
 * @author adi
 */
public interface BarangDao 
    extends PagingAndSortingRepository<Barang, BarangPK> {
    
}
