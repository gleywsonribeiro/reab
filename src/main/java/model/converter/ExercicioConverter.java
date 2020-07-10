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
import model.Exercicio;
import model.Hospital;
import model.dao.ExercicioDao;
import model.dao.HospitalDao;


/**
 *
 * @author gleywson
 */
@FacesConverter(value = "exercicioConverter")
public class ExercicioConverter implements Converter {

    
    private final ExercicioDao dao = new ExercicioDao();

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Exercicio exercicio = null;
       
        if (value != null) {
            Long id = new Long(value);
            exercicio = dao.find(id);
        }
        return exercicio;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            return ((Exercicio) value).getId().toString();
        } else {
            return "";
        }
    }

}
