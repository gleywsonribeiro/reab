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
import model.Treino;
import model.dao.SetorDao;
import model.dao.TreinoDao;

/**
 *
 * @author gleywson
 */
@FacesConverter("treinoConverter")
public class TreinoConverter implements Converter {

    private final TreinoDao dao = new TreinoDao();

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        Treino treino;
        if (value == null || value.equals("")) {
            return null;
        }
        try {
            Long id = Long.valueOf(value);
            treino = dao.find(id);
            return treino;
        } catch (RuntimeException ex) {
            throw new RuntimeException("Não foi possível converter", ex);
        }

    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return "";
        }
        return ((Treino)value).getId().toString();
    }

    

}
