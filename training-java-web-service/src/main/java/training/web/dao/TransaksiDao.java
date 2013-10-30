/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package training.web.dao;

import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import training.web.domain.Transaksi;

/**
 *
 * @author adi
 */
public interface TransaksiDao 
    extends PagingAndSortingRepository<Transaksi, Long> {
    
    public Page<Transaksi> findByTanggalBetween(
            Date start, Date end, Pageable pageable);
    
}
