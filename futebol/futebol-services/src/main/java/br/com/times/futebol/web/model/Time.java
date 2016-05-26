/**
 * 
 */
package br.com.times.futebol.web.model;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

/**
 * @author Hugo A. Martinez
 * @since 25/05/2016
 *
 */

@Data
@Entity
public class Time implements Serializable {
	
	private static final long serialVersionUID = -5418439743843709222L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(length = 48)
	private String nomeTime;
	
	@Column
	private Double pontos;

}
