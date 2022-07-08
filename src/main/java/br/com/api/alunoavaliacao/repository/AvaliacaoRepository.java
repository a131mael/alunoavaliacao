package br.com.api.alunoavaliacao.repository;

import java.util.List;

import org.escola.model.Avaliacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long>, AvaliacaoRepositoryCustom {

	@Query(value = "select * from Avaliacao  ava"
			+ "	left join Professor professor on professor.id = avaliacao.professor_id"
			+ "	where "
			+ " professor.id = ?1"
			+ "	and avaliacao.anoletivo = ?2"
			+ "	and avaliacao.bimestre = ?3"
			+ "	and avaliacao.disciplina = ?4"
			+ " order by aluno.nomealuno", nativeQuery = true)
	public List<Avaliacao> getAvaliacaoNative(Long idProfessor, Integer anoletivo, Integer bimestre, int disciplina);

	
}
