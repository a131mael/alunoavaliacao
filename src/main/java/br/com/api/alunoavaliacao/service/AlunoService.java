package br.com.api.alunoavaliacao.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.escola.model.AlunoTurma;
import org.escola.model.Avaliacao;
import org.escola.model.Configuracao;
import org.escola.model.ProfessorTurma;
import org.escola.model.Turma;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.api.alunoavaliacao.repository.AlunoRepository;
import br.com.api.alunoavaliacao.repository.AvaliacaoRepository;
import br.com.api.alunoavaliacao.repository.ConfiguracaoRepository;
import br.com.api.alunoavaliacao.repository.ProfessorRepository;
import br.com.api.alunoavaliacao.repository.TurmaRepository;

@Service
public class AlunoService {

	@Autowired
	private AlunoRepository alunoRepository;
	
	@Autowired
	private TurmaRepository turmaRepository;
	
	@Autowired
	private ConfiguracaoRepository configuracaoRepository;
	
	@Autowired
	private AvaliacaoRepository avaliacaoRepository;
	
	@Autowired
	private ProfessorRepository professorRepository;
	
	public List<AlunoTurma> getAlunoTurmaNative(Long idTurma){
		try {
			return alunoRepository.getAlunoTurmaNative(idTurma);
		}catch (NoResultException e) {
			return new ArrayList<AlunoTurma>();
		}
		
	}
	
	public List<Turma> getTurmas(){
		return turmaRepository.findAll();
	}

	public Configuracao getConfiguracao(){
		return configuracaoRepository.findAll().get(0);
	}
	
	public List<Avaliacao> getAvaliacoes(Long idProfessor,int anoletivo,int bimestre,int disciplina){
		return avaliacaoRepository.getAvaliacaoNative(idProfessor, anoletivo, bimestre, disciplina);
	}
	
	public List<ProfessorTurma> getProfessoresTurma(Long idTurma){
		return professorRepository.getProfessorTurmaNative(idTurma);
	}

}
