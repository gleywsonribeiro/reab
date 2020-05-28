/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.converter;


import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import model.Paciente;

import model.dao.PacienteDao;


/**
 *
 * @author gleywson
 */
@FacesConverter(value = "pacienteConverter")
public class PacienteConverter implements Converter {

    
    private final PacienteDao dao = new PacienteDao();

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Paciente paciente = null;
       
        if (value != null) {
            Long id = new Long(value);
            paciente = dao.find(id);
        }
        return paciente;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            return ((Paciente) value).getId().toString();
        } else {
            return "";
        }
    }

}
