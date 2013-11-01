/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package training.web.ui.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import training.web.domain.Barang;
import training.web.domain.BarangPK;
import training.web.domain.Transaksi;
import training.web.domain.TransaksiDetail;
import training.web.service.BelajarService;

/**
 *
 * @author adi
 */

@Controller
public class TransaksiController {
    
    @Autowired
    private BelajarService belajarService;
    private final String SESSION_KEY_DETAIL_LIST = "listDetailTransaksi";
    
    @RequestMapping(value="/transaksi/form",
            method= RequestMethod.GET)
    public ModelMap showFormTransaksi(){
        return new ModelMap();
    }
    
    @RequestMapping(value="/transaksi/save",
            method= RequestMethod.POST)
    public String saveFormTransaksi(
            @RequestBody Transaksi t, HttpSession session){
        List<TransaksiDetail> result = 
                new ArrayList<TransaksiDetail>();
        if(session.getAttribute(SESSION_KEY_DETAIL_LIST) !=null){
            result = (List<TransaksiDetail>) 
                    session.getAttribute(SESSION_KEY_DETAIL_LIST);
        }
        
        for (TransaksiDetail td : result) {
            td.setHeader(t);
        }
        t.setDetails(result);
        belajarService.save(t);
        session.removeAttribute(SESSION_KEY_DETAIL_LIST);
        return "redirect:form";
    }
    
    @ResponseBody
    @RequestMapping(value="/transaksi/detail/list",
            method= RequestMethod.GET)
    public Map<String, Object> jsonDetailsTransaksi(
            Pageable pageable, HttpSession session){
        List<TransaksiDetail> result = 
                new ArrayList<TransaksiDetail>();
        if(session.getAttribute(SESSION_KEY_DETAIL_LIST) !=null){
            result = (List<TransaksiDetail>) 
                    session.getAttribute(SESSION_KEY_DETAIL_LIST);
        }
        Integer start = pageable.getPageNumber() * pageable.getPageSize();
        Integer end = start + pageable.getPageSize();
        
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("total", result.size());
        map.put("rows", result.subList(start, end<=result.size() ? end : result.size()));
        
        return map;
    }
    
    @ResponseBody
    @RequestMapping(value="/transaksi/barang/list", 
            method= RequestMethod.GET)
    private List<Barang> getListBarang(Pageable pageable){
        return belajarService
                .findAllBarang(pageable).getContent();
    }
    
    @RequestMapping(value="/transaksi/detail/list", 
            method= RequestMethod.POST)
    @ResponseBody
    private String saveDetail(
            @RequestBody TransaksiDetail td,
            HttpSession session){
        List<TransaksiDetail> list = new ArrayList<TransaksiDetail>();
        if(session.getAttribute(SESSION_KEY_DETAIL_LIST) != null) {
            list = (List<TransaksiDetail>) 
                    session.getAttribute(SESSION_KEY_DETAIL_LIST);
        }
        
        list.add(td);
        session.setAttribute(SESSION_KEY_DETAIL_LIST, list);
        return "sukses";
    }
    
    @RequestMapping(value="/transaksi/detail/list/{id}", 
            method= RequestMethod.DELETE)
    @ResponseBody
    private String delete(
            @PathVariable String id,
            HttpSession session){
        
        List<TransaksiDetail> list = new ArrayList<TransaksiDetail>();
        if(session.getAttribute(SESSION_KEY_DETAIL_LIST) != null) {
            list = (List<TransaksiDetail>) 
                    session.getAttribute(SESSION_KEY_DETAIL_LIST);
        }
        
        TransaksiDetail removedObject = null;
        for (TransaksiDetail td : list) {
            if(td.getBarang().getIdBarang().equals(id)){
                removedObject = td;
            }
        }
        
        list.remove(removedObject);
        session.setAttribute(SESSION_KEY_DETAIL_LIST, list);
        return "SUKSES";
    }
    
    
}
