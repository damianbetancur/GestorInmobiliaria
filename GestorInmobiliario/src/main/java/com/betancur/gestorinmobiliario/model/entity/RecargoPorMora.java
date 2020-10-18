/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.betancur.gestorinmobiliario.model.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "recargos_moras")
public class RecargoPorMora implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_recargo")
    private Date unaFechaDeRecargo;

    @Column(name = "monto")
    private float monto;

    @ManyToOne
    @JoinColumn(name = "fk_inmobiliaria", nullable = true, updatable = true)
    private Inmobiliaria unaInmobiliariaRecargoPorMora;

    public RecargoPorMora() {
    }

    public RecargoPorMora(Date unaFechaDeRecargo, float monto, Inmobiliaria unaInmobiliariaRecargoPorMona) {
        this.unaFechaDeRecargo = unaFechaDeRecargo;
        this.monto = monto;
        this.unaInmobiliariaRecargoPorMora = unaInmobiliariaRecargoPorMona;
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
        if (!(object instanceof RecargoPorMora)) {
            return false;
        }
        RecargoPorMora other = (RecargoPorMora) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.betancur.gestorinmobiliario.model.RecargoPorMora[ id=" + id + " ]";
    }

    public Date getUnaFechaDeRecargo() {
        return unaFechaDeRecargo;
    }

    public void setUnaFechaDeRecargo(Date unaFechaDeRecargo) {
        this.unaFechaDeRecargo = unaFechaDeRecargo;
    }

    public float getMonto() {
        return monto;
    }

    public void setMonto(float monto) {
        this.monto = monto;
    }

    public Inmobiliaria getUnaInmobiliariaRecargoPorMora() {
        return unaInmobiliariaRecargoPorMora;
    }

    public void setUnaInmobiliariaRecargoPorMora(Inmobiliaria unaInmobiliariaRecargoPorMora) {
        this.unaInmobiliariaRecargoPorMora = unaInmobiliariaRecargoPorMora;
    }

}
