package br.com.api.alunoavaliacao.repository;

import java.util.List;

import org.escola.model.AlunoTurma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AlunoRepository extends JpaRepository<AlunoTurma, Long> {

	@Query(value = "select * from AlunoTurma alunoturma"
			+ "	left join Aluno aluno on alunoTurma.aluno_id = aluno.id"
			+ "	left join Turma turma on turma.id = alunoTurma.turma_id"
			+ "	where turma.id = ?1"
			+ " order by aluno.nomealuno", nativeQuery = true)
	public List<AlunoTurma> getAlunoTurmaNative(Long idTurma);
	

}
