package br.com.api.alunoavaliacao.service;

import java.util.List;

import org.escola.model.AlunoAvaliacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.api.alunoavaliacao.repository.AlunoAvaliacaoRepository;

@Service
public class AlunoAvaliacaoService {

	@Autowired
	private AlunoAvaliacaoRepository alunoAvaliacaoRepository;
	
	
	public List<AlunoAvaliacao> getAlunoAvaliacaoNative(Long idTurma, Integer anoletivo, Integer bimestre, int disciplina){
		return alunoAvaliacaoRepository.getAlunoAvaliacaoNative(idTurma, anoletivo, bimestre, disciplina);
	}
	
	public List<AlunoAvaliacao> getAlunoAvaliacaoNative(Long idTurma, Integer anoletivo, Integer bimestre, Long idAluno, int disciplina){
		return alunoAvaliacaoRepository.getAlunoAvaliacaoNative(idTurma, anoletivo, bimestre, idAluno, disciplina);
	}
	
	public List<AlunoAvaliacao> getAlunoAvaliacaoNative(Long idTurma, Integer anoletivo, Integer bimestre, Long idAluno, Long idProfessor, int disciplina){
		return alunoAvaliacaoRepository.getAlunoAvaliacaoNative(idTurma, anoletivo, bimestre, idAluno, idProfessor, disciplina);
	}

	public List<AlunoAvaliacao> getAlunoAvaliacaoProfessorNative(Long idTurma, Integer anoletivo, Integer bimestre,	Long idProfessor, int disciplina) {
		return alunoAvaliacaoRepository.getAlunoAvaliacaoProfessorNative(idTurma, anoletivo, bimestre, idProfessor, disciplina);
	}
	
}
