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
import model.Leito;
import model.Setor;
import model.dao.LeitoDao;
import model.dao.SetorDao;

/**
 *
 * @author gleywson
 */
@FacesConverter("leitoConverter")
public class LeitoConverter implements Converter {

    private final LeitoDao dao = new LeitoDao();

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        Leito leito;
        if (value == null || value.equals("")) {
            return null;
        }
        try {
            Long id = Long.valueOf(value);
            leito = dao.find(id);
            return leito;
        } catch (RuntimeException ex) {
            throw new RuntimeException("Não foi possível converter", ex);

        }

    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return "";
        }
        return ((Leito)value).getId().toString();
    }

    

}
