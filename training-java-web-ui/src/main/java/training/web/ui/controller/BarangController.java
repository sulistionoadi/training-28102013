/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package training.web.ui.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import training.web.service.BelajarService;

/**
 *
 * @author adi
 */

@Controller
public class BarangController {
    
    @Autowired
    private BelajarService service;
    
    @RequestMapping(value="/barang/list", method= RequestMethod.GET)
    public ModelMap viewFormBarang(){
        return new ModelMap();
    }
    
    @ResponseBody
    @RequestMapping(value="/barang/json", method= RequestMethod.GET)
    public Map<String, Object> getJsonBarang(Pageable pageable){
        Map<String, Object> result = 
                new HashMap<String, Object>();
        result.put("total", service.countBarang());
        result.put("rows", service.findAllBarang(pageable).getContent());
        return result;
    }
    
    @RequestMapping(value="/barang/print", 
            method= RequestMethod.GET)
    public ModelMap cetakDaftarBarang(){
        
        Long countBarang = service.countBarang();
        PageRequest pr = new PageRequest(0, countBarang.intValue());
        
        ModelMap mm = new ModelMap();
        mm.put("format", "pdf");
        mm.put("tanggalCetak", new Date());
        mm.put("listBarang", service.findAllBarang(pr).getContent());
        return mm;
    }
    
}
