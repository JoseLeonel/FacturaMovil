/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emprendesoftcr.pdf;

import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;

/**
 *
 * @author Wesker
 */
public class JasperReporter {
    
    private String _jrxmlFilename;
    private JasperReport _report;
    private JRTableModelDataSource _dataSource;
   
    private HashMap<String,Object> _parameters;
    
    
    public JasperReporter(String jrxmlFilename){
        this._jrxmlFilename = jrxmlFilename;
      
        _parameters = new HashMap<String, Object>();
    }
    
    private void compile() throws JRException
    {
        this._report = JasperCompileManager.compileReport(_jrxmlFilename);
    }
    
   
    
    
    public void setQrImage(InputStream is){
        try {
            Image image = ImageIO.read(is);
            _parameters.put("pQr",image);
        } catch (IOException ex) {
            Logger.getLogger(JasperReporter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setlogoImage(InputStream is){
        
        try {
            Image image = ImageIO.read(is);
            _parameters.put("plogo",image);
        } catch (IOException ex) {
            Logger.getLogger(JasperReporter.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void exportToPDF(String OutputFilename) throws JRException
    {
        compile();
       
        
        JasperPrint print = JasperFillManager.fillReport(this._report, _parameters, this._dataSource);
        
        JasperExportManager.exportReportToPdfFile(print, OutputFilename);
    }
    
}
