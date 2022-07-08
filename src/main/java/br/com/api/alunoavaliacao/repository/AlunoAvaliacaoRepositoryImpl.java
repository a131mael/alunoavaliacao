package br.com.api.alunoavaliacao.repository;

import java.util.ArrayList;
import java.util.List;

import org.escola.model.AlunoAvaliacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

public class AlunoAvaliacaoRepositoryImpl implements AlunoAvaliacaoRepositoryCustom {

	@Autowired
	@Lazy
	AlunoAvaliacaoRepository alunoAvaliacaoRepository;

//	@Override
//	public List<AlunoAvaliacao> getAlunoAvaliacao(Long idTurma) {
//
//		List<AlunoAvaliacao> al = new ArrayList<AlunoAvaliacao>();
//
//		AlunoAvaliacao al1 = new AlunoAvaliacao();
//		al1.setAnoLetivo(2022);
//		al.add(al1);
//		return al;
//	}

	@Override
	public List<AlunoAvaliacao> getAlunoAvaliacao(Long idTurma, int bimestre) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AlunoAvaliacao> getAlunoAvaliacao(Long idTurma, int bimestre, Long idAluno) {
		// TODO Auto-generated method stub
		return null;
	}

}
