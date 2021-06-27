/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.io.Serializable;
import java.time.ZoneId;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;

import model.Atendimento;
import model.Hospital;
import model.LeitoSexo;
import model.Setor;
import model.dao.AtendimentoDao;
import util.exception.DBException;
import util.exception.NegocioException;

/**
 * @author gleyw
 */
public class AtendimentoService implements Serializable {

    private AtendimentoDao dao = new AtendimentoDao();

    public void salvar(Atendimento atendimento) {
        LeitoSexo sexo = atendimento.getLeito().getSexo();

        if (atendimento.getDataAtendimento() != null) {
            if ( atendimento.getDataPrimeiraDeambulacao() != null && atendimento.getDataAtendimento().after(atendimento.getDataPrimeiraDeambulacao())) {
                throw new NegocioException("Deambulação não pode ocorrer antes da admissão!");
            }
            if (atendimento.getDataPrimeiraOrtostase() != null && atendimento.getDataAtendimento().after(atendimento.getDataPrimeiraOrtostase())) {
                throw new NegocioException("Ortostase não pode ocorrer antes da admissão!");
            }
            if (atendimento.getDataPrimeiraSedestacao() != null && atendimento.getDataAtendimento().after(atendimento.getDataPrimeiraSedestacao())) {
                throw new NegocioException("Sedestação não pode ocorrer antes da admissão!");
            }

            if (atendimento.getDataExtubacao() != null && atendimento.getDataAtendimento().after(atendimento.getDataExtubacao())) {
                throw new NegocioException("Extubação não pode ocorrer antes da admissão!");
            }
        }

        if (!sexo.getDescricao().equals(atendimento.getPaciente().getSexo().getDescricao()) && sexo != LeitoSexo.AMBOS) {
            throw new NegocioException("Leito incompatível com o paciente!");
        }

        if (dao.isPacienteEmAtendimento(atendimento.getPaciente())) {
            if (atendimento.getId() != null) {
                dao.edit(atendimento);
            } else {
                throw new NegocioException("Paciente já está em atendimento");
            }

        }
        dao.create(atendimento);
    }

    public void edicao(Atendimento atendimento) {
        if (atendimento.getDataAtendimento() != null) {
            if ( atendimento.getDataPrimeiraDeambulacao() != null && atendimento.getDataAtendimento().after(atendimento.getDataPrimeiraDeambulacao())) {
                throw new NegocioException("Deambulação não pode ocorrer antes da admissão!");
            }
            if (atendimento.getDataPrimeiraOrtostase() != null && atendimento.getDataAtendimento().after(atendimento.getDataPrimeiraOrtostase())) {
                throw new NegocioException("Ortostase não pode ocorrer antes da admissão!");
            }
            if (atendimento.getDataPrimeiraSedestacao() != null && atendimento.getDataAtendimento().after(atendimento.getDataPrimeiraSedestacao())) {
                throw new NegocioException("Sedestação não pode ocorrer antes da admissão!");
            }

            if (atendimento.getDataExtubacao() != null && atendimento.getDataAtendimento().after(atendimento.getDataExtubacao())) {
                throw new NegocioException("Extubação não pode ocorrer antes da admissão!");
            }
        }
        dao.edit(atendimento);
    }

    public List<Atendimento> listarTodos(Hospital hospital) {
        return dao.listarPorHospital(hospital);
    }

    public List<Atendimento> getAtendimentosEmAndamento() {
        return dao.getAtendimentosEmAndamento();
    }

    public List<Atendimento> getAtendimentosLiberados() {
        List<Atendimento> teste = dao.getAtendimentosEmAndamento().stream().filter(a -> a.isLiberadoMobilizacao() == true).collect(Collectors.toList());
        System.out.println(teste);
        return teste;
    }

    public List<Atendimento> getAtendimentosPorUnidade(Setor setor) {
        return dao.getAtendimentosPorUnidade(setor);
    }

    public Long getPacientesInternados(Hospital hospital) {
        return dao.getPacientesInternados(hospital);
    }

    public Atendimento buscarPorId(Long id) {
        return dao.find(id);
    }

    public void remover(Atendimento atendimento) {
        try {
            dao.remove(atendimento);
        } catch (Exception e) {
            throw new DBException("Não foi possível remover:" + e.getMessage());
        }
    }

    public List<Atendimento> getPacientesExtubados(Setor setor) {
        return dao.getPacientesExtubados(setor);
    }

    public int getQtdExtubacoesMes(List<Atendimento> atendimentos, int i) {
        int contador = 0;

        int ano = new Date().toInstant().atZone(ZoneId.systemDefault()).getYear();
        //filter the current year
        List<Atendimento> atendimentosMesAno = atendimentos.stream()
                .filter(a -> ano == a.getDataAtendimento().toInstant().atZone(ZoneId.systemDefault()).getYear())
                .collect(Collectors.toList());

        GregorianCalendar calendar = new GregorianCalendar();
        int mes = calendar.get(GregorianCalendar.MONTH);

        contador = atendimentosMesAno.stream().filter((_item) -> (mes == i)).map((_item) -> 1).reduce(contador, Integer::sum);

        return contador;
    }

}
