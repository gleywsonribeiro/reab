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
import model.Atendimento;
import model.dao.AtendimentoDao;


/**
 *
 * @author gleywson
 */
@FacesConverter("AtendimentoConverter")
public class AtendimentoConverter implements Converter {

    private final AtendimentoDao dao = new AtendimentoDao();

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        Atendimento atendimento;
        if (value == null || value.equals("")) {
            return null;
        }
        try {
            Long id = Long.valueOf(value);
            atendimento = dao.find(id);
            return atendimento;
        } catch (RuntimeException ex) {
            throw new RuntimeException("Não foi possível converter", ex);

        }

    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return "";
        }
        return ((Atendimento)value).getId().toString();
    }

    

}
