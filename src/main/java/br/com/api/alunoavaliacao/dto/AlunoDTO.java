package br.com.api.alunoavaliacao.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlunoDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String nomeAluno;
	
	private Integer faltas1Bimestre;
	private Integer faltas2Bimestre;
	private Integer faltas3Bimestre;
	private Integer faltas4Bimestre;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeAluno() {
		return nomeAluno;
	}

	public void setNomeAluno(String nomeAluno) {
		this.nomeAluno = nomeAluno;
	}

	public Integer getFaltas1Bimestre() {
		return faltas1Bimestre;
	}

	public void setFaltas1Bimestre(Integer faltas1Bimestre) {
		this.faltas1Bimestre = faltas1Bimestre;
	}

	public Integer getFaltas2Bimestre() {
		return faltas2Bimestre;
	}

	public void setFaltas2Bimestre(Integer faltas2Bimestre) {
		this.faltas2Bimestre = faltas2Bimestre;
	}

	public Integer getFaltas3Bimestre() {
		return faltas3Bimestre;
	}

	public void setFaltas3Bimestre(Integer faltas3Bimestre) {
		this.faltas3Bimestre = faltas3Bimestre;
	}

	public Integer getFaltas4Bimestre() {
		return faltas4Bimestre;
	}

	public void setFaltas4Bimestre(Integer faltas4Bimestre) {
		this.faltas4Bimestre = faltas4Bimestre;
	}

}
