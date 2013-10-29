/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package training.web.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author adi
 */


@Embeddable
public class BarangPK implements Serializable{
    
    @Column(name="kode_barang", length=6)
    private String kode;
    
    @Column(name="kode_cabang", length=3)
    private String cabang;

    public BarangPK() {
    }

    public BarangPK(String kode, String cabang) {
        this.kode = kode;
        this.cabang = cabang;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getCabang() {
        return cabang;
    }

    public void setCabang(String cabang) {
        this.cabang = cabang;
    }
    
    
}
