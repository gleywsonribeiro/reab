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
import model.Resultado;

import model.dao.MotivoDao;
import model.dao.ResultadoDao;



/**
 *
 * @author gleywson
 */
@FacesConverter("resultadConverter")
public class ResultadoConverter implements Converter {

    private final ResultadoDao dao = new ResultadoDao();

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        Resultado m;
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
        return ((Resultado)value).getId().toString();
    }

    

}