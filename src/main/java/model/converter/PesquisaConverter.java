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



/**
 *
 * @author gleywson
 */
@FacesConverter(value = "pesquisaConverter")
public class PesquisaConverter implements Converter {

//    private final PesquisaDao dao = new PesquisaDao();

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
//        Pesquisa pesquisa = null;
//       
//        if (value != null) {
//            Long id = new Long(value);
//            pesquisa = dao.find(id);
//        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
//        if (value != null) {
//            return ((Pesquisa) value).getId().toString();
//        } else {
//            return "";
//        }
        return null;
    }

}
