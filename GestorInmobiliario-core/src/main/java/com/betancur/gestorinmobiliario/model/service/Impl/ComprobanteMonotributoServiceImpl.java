/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.betancur.gestorinmobiliario.model.service.Impl;

import com.betancur.gestorinmobiliario.dto.ComprobanteMonotributoDTO;
import com.betancur.gestorinmobiliario.model.dao.Conexion;
import com.betancur.gestorinmobiliario.model.service.IComprobanteMonotributoService;
import java.util.List;

/**
 *
 * @author Ariel
 */
public class ComprobanteMonotributoServiceImpl implements IComprobanteMonotributoService{

    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public ComprobanteMonotributoServiceImpl() {
        new Conexion();
    }

    @Override
    public ComprobanteMonotributoDTO crear(ComprobanteMonotributoDTO dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ComprobanteMonotributoDTO modificar(ComprobanteMonotributoDTO dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ComprobanteMonotributoDTO listarID(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ComprobanteMonotributoDTO> listarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
