/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.service.Impl;

import dto.LocatarioDependienteDTO;
import model.dao.Conexion;
import model.service.ILocatarioDependienteService;
import java.util.List;

/**
 *
 * @author Ariel
 */
public class LocatarioDependienteServiceImpl  implements ILocatarioDependienteService {

    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public LocatarioDependienteServiceImpl() {
        new Conexion();
    }

    
    @Override
    public LocatarioDependienteDTO crear(LocatarioDependienteDTO dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LocatarioDependienteDTO modificar(LocatarioDependienteDTO dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LocatarioDependienteDTO listarID(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<LocatarioDependienteDTO> listarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}