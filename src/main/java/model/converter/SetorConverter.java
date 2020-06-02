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
import model.Setor;
import model.dao.SetorDao;

/**
 *
 * @author gleywson
 */
@FacesConverter("setorConverter")
public class SetorConverter implements Converter {

    private final SetorDao dao = new SetorDao();

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        Setor setor;
        if (value == null || value.equals("")) {
            return null;
        }
        try {
            Long id = Long.valueOf(value);
            setor = dao.find(id);
            return setor;
        } catch (RuntimeException ex) {
            throw new RuntimeException("Não foi possível converter", ex);

        }

    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return "";
        }
        return ((Setor)value).getId().toString();
    }

    

}
