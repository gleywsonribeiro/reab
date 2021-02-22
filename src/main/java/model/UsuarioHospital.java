/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

/**
 *
 * @author Gleywson
 */
@Entity
public class UsuarioHospital implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private UsuarioHospitalPK id;

    @ManyToOne
    @MapsId("hospitalId")
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;

    @ManyToOne
    @MapsId("usuarioId")
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public UsuarioHospital(UsuarioHospitalPK id, Hospital hospital, Usuario usuario) {
        this.id = id;
        this.hospital = hospital;
        this.usuario = usuario;
    }

    public UsuarioHospital() {
    }

    public UsuarioHospitalPK getId() {
        return id;
    }

    public void setId(UsuarioHospitalPK id) {
        this.id = id;
    }

    public Hospital getHospital() {
        return hospital;
    }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    
    
}
