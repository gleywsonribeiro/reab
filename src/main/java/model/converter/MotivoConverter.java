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
import model.Motivo;

import model.dao.MotivoDao;



/**
 *
 * @author gleywson
 */
@FacesConverter("motivoConverter")
public class MotivoConverter implements Converter {

    private final MotivoDao dao = new MotivoDao();

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        Motivo m;
        if (value == null || value.equals("")) {
            return null;
        }
        try {
            Long id = Long.valueOf(value);
            m = dao.find(id);
            return m;
        } catch (RuntimeException ex) {
            throw new RuntimeException("Não foi possível converter", ex);

        }

    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return "";
        }
        return ((Motivo)value).getId().toString();
    }

    

}
