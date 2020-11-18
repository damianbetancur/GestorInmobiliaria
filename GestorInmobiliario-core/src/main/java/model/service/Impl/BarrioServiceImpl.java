/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.service.Impl;

import converter.InmobiliariaMapper;
import dto.BarrioDTO;
import model.dao.BarrioJpaController;
import model.dao.Conexion;
import model.dao.exceptions.NonexistentEntityException;
import model.entity.Barrio;
import model.service.IBarrioService;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.mapstruct.factory.Mappers;

/**
 *
 * @author Ariel
 */
public class BarrioServiceImpl implements IBarrioService {

    private final BarrioJpaController barrioDAO;
    private final InmobiliariaMapper converter = Mappers.getMapper(InmobiliariaMapper.class);

    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public BarrioServiceImpl() {
        new Conexion();
        this.barrioDAO = new BarrioJpaController(Conexion.getEmf());
    }

    @Override
    public BarrioDTO crear(BarrioDTO dto) {
        if (dto != null) {
            Barrio entity = converter.toEntity(dto);
            this.barrioDAO.create(entity);
            dto.setId(entity.getId());
        } else {
            System.out.println("El DTO es null");
        }
        return dto;
    }

    @Override
    public BarrioDTO modificar(BarrioDTO dto) {
        if (dto != null) {
            if (dto.getId() != null) {
                try {
                    Barrio entity = converter.toEntity(dto);
                    barrioDAO.edit(entity);
                    dto.setId(entity.getId());
                } catch (Exception ex) {
                    Logger.getLogger(BarrioServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                System.out.println("ID  DTO is null");
            }
        } else {
            System.out.println("DTO is null");
        }
        return dto;

    }

    @Override
    public void eliminar(Long id) {
        if (id != null) {
            if (barrioDAO.findBarrio(id) != null) {
                try {
                    barrioDAO.destroy(id);
                } catch (NonexistentEntityException ex) {
                    Logger.getLogger(BarrioServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                System.out.println("NO EXIST Entity to Delete");
            }
        } else {
            System.out.println("ID is null");
        }
    }

    @Override
    public BarrioDTO listarID(Long id) {
        Barrio entity = barrioDAO.findBarrio(id);
        return converter.toDTO(entity);
    }

    @Override
    public List<BarrioDTO> listarTodos() {
        List<Barrio> entities = barrioDAO.findBarrioEntities();
        return converter.toDTOBarrioList(entities);
    }

}
