/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.betancur.gestorinmobiliario.model.service.Impl;

import com.betancur.gestorinmobiliario.converter.LocalidadConverter;
import com.betancur.gestorinmobiliario.dto.LocalidadDTO;
import com.betancur.gestorinmobiliario.model.dao.Conexion;
import com.betancur.gestorinmobiliario.model.dao.LocalidadJpaController;
import com.betancur.gestorinmobiliario.model.dao.exceptions.NonexistentEntityException;
import com.betancur.gestorinmobiliario.model.entity.Localidad;
import com.betancur.gestorinmobiliario.model.service.ILocalidadService;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ariel
 */
public class LocalidadServiceImpl implements ILocalidadService{

    private final LocalidadJpaController localidadDAO;
    
    private final LocalidadConverter converter;
    
    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public LocalidadServiceImpl() {
        new Conexion();
        this.localidadDAO = new LocalidadJpaController(Conexion.getEmf());
        this.converter = new LocalidadConverter();
    }

    
    
    @Override
    public LocalidadDTO crear(LocalidadDTO dto) {
        Localidad entity = this.converter.fromDto(dto);
        this.localidadDAO.create(entity);
        dto.setId(entity.getId());
        return dto;
    }

    @Override
    public LocalidadDTO modificar(LocalidadDTO dto) {
        Localidad entity = this.converter.fromDto(dto);
        try {
            localidadDAO.edit(entity);
        } catch (Exception ex) {
            Logger.getLogger(AlquilerServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dto;
    }

    @Override
    public void eliminar(Long id) {
        try {
            localidadDAO.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(AlquilerServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    @Override
    public LocalidadDTO listarID(Long id) {
        Localidad entity = localidadDAO.findLocalidad(id);
        return this.converter.fromEntity(entity);
    }

    @Override
    public List<LocalidadDTO> listarTodos() {
        List<Localidad> entities = localidadDAO.findLocalidadEntities();
        return this.converter.fromEntity(entities);
    }
    
}
