/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package training.web.dao;

import java.util.Date;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import training.web.domain.Barang;
import training.web.domain.BarangPK;

/**
 *
 * @author adi
 */
public interface BarangDao 
    extends PagingAndSortingRepository<Barang, BarangPK> {
 
    @Query("select count(b) from Barang b "
            + "where b.tanggal between :start and :end")
    public Long countBarangByTanggal(
            @Param("start") Date start,
            @Param("end") Date end);
    
    @Query("select b from Barang b "
            + "where b.id.cabang = :kode")
    public Page<Barang> cariByKodeCabang(
            @Param("kode") String kode, Pageable pageable);
    
    //select * from barang where tanggal between ? and ?;
    //pageable sebagai limit di MySQL
    public Page<Barang> findByTanggalBetween(
            Date start, Date end, Pageable pageable);
    
    //select * from barang where deskripsi=? and tanggal=?
    public Page<Barang> findByDeskripsiAndTanggal(
            String deskripsi, 
            Date tanggal, Pageable pageable);
    
//    @Query("select b from Barang b where b.kolom1=:p1 "
//            + "and (b.kolom2=:p2 or b.kolom3=:p3)")
    
    
    //query where 3 kolom
    //where kolom1=? and (kolom2=? or kolom3=?)
    //findByKolom1AndKolom2OrKolom3
}
