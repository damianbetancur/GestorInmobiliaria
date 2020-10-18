/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.betancur.gestorinmobiliario.model.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Ariel
 */
@Entity
@Table(name="aranceles_especiales")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="tipo_arancel")
public abstract class ArancelEspecial implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_recargo")
    private Date unaFechaDeRecargo;
    
    @Column(name="descripcion")
    private String descripcion;
    
    @Column(name = "monto")
    private float monto;
    
    @ManyToOne
    @JoinColumn(name = "fk_inmobiliaria", nullable = false, updatable = true)
    private Inmobiliaria unaInmobiliariaArancelEspecial;

    public ArancelEspecial() {
    }

    public ArancelEspecial(Date unaFechaDeRecargo, String descripcion, float monto, Inmobiliaria unaInmobiliariaArancelEspecial) {
        this.unaFechaDeRecargo = unaFechaDeRecargo;
        this.descripcion = descripcion;
        this.monto = monto;
        this.unaInmobiliariaArancelEspecial = unaInmobiliariaArancelEspecial;
    }
    
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ArancelEspecial)) {
            return false;
        }
        ArancelEspecial other = (ArancelEspecial) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.betancur.gestorinmobiliario.model.ArancelEspecial[ id=" + id + " ]";
    }

    public Date getUnaFechaDeRecargo() {
        return unaFechaDeRecargo;
    }

    public void setUnaFechaDeRecargo(Date unaFechaDeRecargo) {
        this.unaFechaDeRecargo = unaFechaDeRecargo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getMonto() {
        return monto;
    }

    public void setMonto(float monto) {
        this.monto = monto;
    }

    public Inmobiliaria getUnaInmobiliariaArancelEspecial() {
        return unaInmobiliariaArancelEspecial;
    }

    public void setUnaInmobiliariaArancelEspecial(Inmobiliaria unaInmobiliariaArancelEspecial) {
        this.unaInmobiliariaArancelEspecial = unaInmobiliariaArancelEspecial;
    }
    
    
}
