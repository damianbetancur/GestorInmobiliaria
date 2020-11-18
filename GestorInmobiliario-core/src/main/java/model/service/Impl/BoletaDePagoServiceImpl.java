/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.service.Impl;

import converter.InmobiliariaMapper;
import dto.BoletaDePagoDTO;
import model.dao.Conexion;
import model.service.IBoletaDePagoService;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.dao.BoletaDePagoJpaController;
import model.dao.exceptions.NonexistentEntityException;
import model.entity.BoletaDePago;
import org.mapstruct.factory.Mappers;

/**
 *
 * @author Ariel
 */
public class BoletaDePagoServiceImpl implements IBoletaDePagoService {

    private final BoletaDePagoJpaController boletaDePagoDAO;
    private final InmobiliariaMapper converter = Mappers.getMapper(InmobiliariaMapper.class);

    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public BoletaDePagoServiceImpl() {
        new Conexion();
        this.boletaDePagoDAO = new BoletaDePagoJpaController(Conexion.getEmf());
    }

    @Override
    public BoletaDePagoDTO crear(BoletaDePagoDTO dto) {
        if (dto != null) {
            BoletaDePago entity = converter.toEntity(dto);
            this.boletaDePagoDAO.create(entity);
            dto.setId(entity.getId());
        } else {
            System.out.println("El DTO es null");
        }
        return dto;
    }

    @Override
    public BoletaDePagoDTO modificar(BoletaDePagoDTO dto) {
        if (dto != null) {
            if (dto.getId() != null) {
                try {
                    BoletaDePago entity = converter.toEntity(dto);
                    boletaDePagoDAO.edit(entity);
                    dto.setId(entity.getId());
                } catch (Exception ex) {
                    Logger.getLogger(BoletaDePagoServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
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
            if (boletaDePagoDAO.findBoletaDePago(id) != null) {
                try {
                    boletaDePagoDAO.destroy(id);
                } catch (NonexistentEntityException ex) {
                    Logger.getLogger(BoletaDePagoServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                System.out.println("NO EXIST Entity to Delete");
            }
        } else {
            System.out.println("ID is null");
        }
    }

    @Override
    public BoletaDePagoDTO listarID(Long id) {
        BoletaDePago entity = boletaDePagoDAO.findBoletaDePago(id);
        return converter.toDTO(entity);
    }

    @Override
    public List<BoletaDePagoDTO> listarTodos() {
        List<BoletaDePago> entities = boletaDePagoDAO.findBoletaDePagoEntities();
        return converter.toDTOBoletaDePagoList(entities);
    }
}