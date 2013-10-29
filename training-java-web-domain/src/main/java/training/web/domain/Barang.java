/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package training.web.domain;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    private String kode;
    private String deskripsi;
    private BigDecimal harga;
    
    @Temporal(TemporalType.DATE)
    private Date tanggal;
    
    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
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
                + "kode=" + kode 
                + ", deskripsi=" + deskripsi 
                + ", harga=" + harga 
                + ", tanggal=" + sdf.format(tanggal) + '}';
    }
    
}
