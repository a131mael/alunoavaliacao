package br.com.api.alunoavaliacao.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlunoAvaliacaoDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private AlunoDTO aluno;

	private AvaliacaoDTO avaliacao;

	private float nota;

	private Integer anoLetivo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public AlunoDTO getAluno() {
		return aluno;
	}

	public void setAluno(AlunoDTO aluno) {
		this.aluno = aluno;
	}

	public AvaliacaoDTO getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(AvaliacaoDTO avaliacao) {
		this.avaliacao = avaliacao;
	}

	public float getNota() {
		return nota;
	}

	public void setNota(float nota) {
		this.nota = nota;
	}

	public Integer getAnoLetivo() {
		return anoLetivo;
	}

	public void setAnoLetivo(Integer anoLetivo) {
		this.anoLetivo = anoLetivo;
	}

}
