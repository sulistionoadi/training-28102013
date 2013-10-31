/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package training.web.ui.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import training.web.domain.Barang;
import training.web.domain.TransaksiDetail;

/**
 *
 * @author adi
 */

@Controller
public class TransaksiController {
    
    @RequestMapping(value="/transaksi/form",
            method= RequestMethod.GET)
    public ModelMap showFormTransaksi(){
        return new ModelMap();
    }
    
    @ResponseBody
    @RequestMapping(value="/transaksi/detail/list",
            method= RequestMethod.GET)
    public List<TransaksiDetail> jsonDetailsTransaksi(){
        List<TransaksiDetail> result = 
                new ArrayList<TransaksiDetail>();
        Barang b = new Barang();
        b.setDeskripsi("Barang Dummy");
        for (int i=1; i<6; i++) {
            TransaksiDetail td = new TransaksiDetail();
            td.setId(new Long(i));
            td.setBarang(b);
            td.setQty(i);
            td.setHarga(new BigDecimal(1000)
                    .multiply(new BigDecimal(i)));
            td.setTotal(new BigDecimal(td.getQty())
                    .multiply(td.getHarga()));
            result.add(td);
        }
        return result;
    }
}
