/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package training.web.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author adi
 */

@Entity
@Table(name="transaksi")
public class Transaksi {
    
    @Id @GeneratedValue
    private Long id;
    
    @Column(nullable=false)
    @Temporal(TemporalType.DATE)
    private Date tanggal;
    
    @OneToMany(mappedBy="header",
            cascade= CascadeType.ALL)
    private List<TransaksiDetail> details = 
            new ArrayList<TransaksiDetail>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public List<TransaksiDetail> getDetails() {
        return details;
    }

    public void setDetails(List<TransaksiDetail> details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "Transaksi{" + "id=" + id 
                + ", tanggal=" + tanggal 
                + ", details=" + details.size() + '}';
    }
 
    
}
