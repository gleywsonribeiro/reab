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
@FacesConverter("pacienteConverter")
public class PacienteConverter implements Converter {

    private final PacienteDao dao = new PacienteDao();

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        Paciente p;
        if (value == null || value.equals("")) {
            return null;
        }
        try {
            Long id = Long.valueOf(value);
            p = dao.find(id);
            return p;
        } catch (Exception ex) {
            throw new RuntimeException("Não foi possível converter", ex);

        }

    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return "";
        }
        return ((Paciente)value).getId().toString();
    }

    

}
