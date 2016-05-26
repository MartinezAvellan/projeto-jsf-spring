package br.com.times.futebol.web.controllers;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.times.futebol.services.TimeDAO;
import br.com.times.futebol.web.model.Time;

/**
 * @author Hugo A. Martinez
 * @since 25/05/2016
 *
 */
@Component("timesBB")
@Scope("request")
public class TimesBackingBean implements Serializable {

	private static final long serialVersionUID = 2752715220325961635L;
	private static final Logger logger = LoggerFactory.getLogger(TimesBackingBean.class);
	private FacesContext fc = FacesContext.getCurrentInstance();
	private Time time = new Time();
	private List<Time> listaTimes;
	
	@Autowired
	private TimeDAO timeDAO;
	
	public void deletarTime(Time time) {
		logger.debug(time.toString());
		timeDAO.remover(time);
		this.invalidarTime();
	}
	
	public void atualizarTime(Time time) {
		logger.debug(time.toString());
		listaTimes = timeDAO.atualizar(time);
		this.invalidarTime();
	}
	
	public void salvarTime() {
		logger.debug(time.toString());
		timeDAO.inserir(time);
		this.invalidarTime();
		fc.addMessage(null, new FacesMessage("salvo com sucesso"));
	}

	private void invalidarTime() {
		time = new Time();
	}
	
	public Time getTime() {
		return time;
	}
	
	public List<Time> getListaTimes() {
		listaTimes = timeDAO.buscarTodos();
		logger.debug(listaTimes.toString());
		return listaTimes;
	}

}
