/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Gleywson
 */
@Embeddable
public class UsuarioHospitalPK implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Column(name = "hospital_id")
    private Long hospitalId;
    @Column(name = "usuario_id")
    private Long usuarioId;

    public UsuarioHospitalPK() {
    }

    public UsuarioHospitalPK(Long hospitalId, Long usuarioId) {
        this.hospitalId = hospitalId;
        this.usuarioId = usuarioId;
    }

    public Long getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(Long hospitalId) {
        this.hospitalId = hospitalId;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    
   
    
    
}
