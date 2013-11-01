/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package training.web.domain;

import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author adi
 */

@Entity
@Table(name="transaksi_detail")
public class TransaksiDetail {
    
    @Id @GeneratedValue
    private Long id;
    
    @ManyToOne
    @JoinColumn(name="id_header", nullable=false)
    private Transaksi header;
    
    @ManyToOne
    @JoinColumns({
        @JoinColumn(name="id_brg_kode", 
            referencedColumnName="kode_barang"),
        @JoinColumn(name="id_brg_cabang", 
            referencedColumnName="kode_cabang")
    })
    private Barang barang;
    
    private Integer qty = 0;
    
    private BigDecimal harga = BigDecimal.ZERO;
    
    private BigDecimal total = BigDecimal.ZERO;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Transaksi getHeader() {
        return header;
    }

    public void setHeader(Transaksi header) {
        this.header = header;
    }

    public Barang getBarang() {
        return barang;
    }

    public void setBarang(Barang barang) {
        this.barang = barang;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public BigDecimal getHarga() {
        return harga;
    }

    public void setHarga(BigDecimal harga) {
        this.harga = harga;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    @Override
    public String toString() {
        if(header!=null && barang!=null){
            return "TransaksiDetail{" + "id=" + id 
                    + ", header=" + header.getId() 
                    + ", barang=" + barang.getId().getKode() 
                    + ", barang=" + barang.getId().getCabang()
                    + ", qty=" + qty 
                    + ", harga=" + harga 
                    + ", total=" + total + '}';
        } else {
            return "";
        }
    }
    
    
    
}
