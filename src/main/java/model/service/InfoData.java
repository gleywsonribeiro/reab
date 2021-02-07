/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.service;

import java.util.Date;
import model.Atendimento;

/**
 *
 * @author gleyw
 */
public interface InfoData {

    public Date getDateReferencia(Atendimento atendimento);
    
}
