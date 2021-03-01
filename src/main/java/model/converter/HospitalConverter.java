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
import model.Hospital;
import model.dao.HospitalDao;

/**
 *
 * @author gleywson
 */
@FacesConverter("hospitalConverter")
public class HospitalConverter implements Converter {

    private final HospitalDao dao = new HospitalDao();

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        Hospital hospital;
        if (value == null || value.equals("")) {
            return null;
        }
        try {
            Long id = Long.valueOf(value);
            hospital = dao.find(id);
            return hospital;
        } catch (RuntimeException ex) {
            throw new RuntimeException("Não foi possível converter", ex);

        }

    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return "";
        }
        return ((Hospital)value).getId().toString();
    }

    

}
