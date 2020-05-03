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
@FacesConverter(value = "hospitalConverter", forClass = Hospital.class)
public class HospitalConverter implements Converter {

    
    private final HospitalDao dao = new HospitalDao();

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Hospital hospital = null;
       
        if (value != null) {
            Long id = new Long(value);
            hospital = dao.find(id);
        }
        return hospital;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            return ((Hospital) value).getId().toString();
        } else {
            return "";
        }
    }

}
