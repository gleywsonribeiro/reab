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
import model.Opcao;
import model.dao.OpcaoDao;

/**
 *
 * @author gleywson
 */
@FacesConverter(value = "opcaoConverter", forClass = Opcao.class)
public class OpcaoConverter implements Converter {

    private OpcaoDao repositorio = new OpcaoDao();

   
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Opcao opcao = null;
        if (value != null) {
            Long id = new Long(value);
            opcao = repositorio.find(id);
        }
        return opcao;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            return ((Opcao) value).getId().toString();
        } else {
            return "";
        }
    }

}
