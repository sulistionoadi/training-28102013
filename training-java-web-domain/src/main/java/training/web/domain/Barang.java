/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package training.web.domain;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author adi
 */

@Entity
@Table(name="barang")
public class Barang {
    
    @Id
    private BarangPK id;
    
    @Column(length=50)
    private String deskripsi;
    
    @Column(nullable=false, scale=2, precision=12)
    private BigDecimal harga = new BigDecimal(100);
    
    @Temporal(TemporalType.DATE)
    @Column(nullable=false)
    private Date tanggal = new Date();
    
    public String getIdBarang(){
        if(id!=null){
            return id.getKode() + '-' + id.getCabang();
        } else {
            return null;
        }
    }

    public BarangPK getId() {
        return id;
    }

    public void setId(BarangPK id) {
        this.id = id;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public BigDecimal getHarga() {
        return harga;
    }

    public void setHarga(BigDecimal harga) {
        this.harga = harga;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        return "Barang{" 
                + "kode=" + id.getKode() 
                + ", deskripsi=" + deskripsi 
                + ", harga=" + harga 
                + ", tanggal=" + sdf.format(tanggal) + '}';
    }
    
}
