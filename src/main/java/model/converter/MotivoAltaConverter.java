/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.converter;

import model.Leito;
import model.MotivoAlta;
import model.dao.LeitoDao;
import model.dao.MotivoAltaDao;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author gleywson
 */
@FacesConverter("motivoAltaConverter")
public class MotivoAltaConverter implements Converter {

    private final MotivoAltaDao dao = new MotivoAltaDao();

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        MotivoAlta motivoAlta;
        if (value == null || value.equals("")) {
            return null;
        }
        try {
            Long id = Long.valueOf(value);
            motivoAlta = dao.find(id);
            return motivoAlta;
        } catch (RuntimeException ex) {
            throw new RuntimeException("Não foi possível converter", ex);

        }

    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return "";
        }
        return ((MotivoAlta)value).getId().toString();
    }
}
