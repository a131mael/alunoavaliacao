package br.com.api.alunoavaliacao.repository;

import java.util.List;

import org.escola.model.AlunoAvaliacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AlunoAvaliacaoRepository extends JpaRepository<AlunoAvaliacao, Long> , AlunoAvaliacaoRepositoryCustom{

	@Query(value = "select * from alunoavaliacao  av"
			+ "	left join  aluno aluno on av.aluno_id = aluno.id"
			+ "	left join avaliacao avaliacao on av.avaliacao_id = avaliacao.id"
			+ "	left join AlunoTurma alunoTurma on alunoTurma.aluno_id = av.aluno_id"
			+ "	left join Turma turma on turma.id = alunoTurma.turma_id"
			+ "	left join Professor professor on professor.id = avaliacao.professor_id"
			+ "	where turma.id = ?1"
			+ "	and avaliacao.anoletivo = ?2"
			+ "	and avaliacao.bimestre = ?3"
			+ "	and avaliacao.disciplina = ?4"
			+ " order by aluno.nomealuno", nativeQuery = true)
	public List<AlunoAvaliacao> getAlunoAvaliacaoNative(Long idTurma, Integer anoletivo, Integer bimestre, int disciplina);
	
	
	@Query(value = "select * from alunoavaliacao  av"
			+ "	left join  aluno aluno on av.aluno_id = aluno.id"
			+ "	left join avaliacao avaliacao on av.avaliacao_id = avaliacao.id"
			+ "	left join AlunoTurma alunoTurma on alunoTurma.aluno_id = av.aluno_id"
			+ "	left join Turma turma on turma.id = alunoTurma.turma_id"
			+ "	left join Professor professor on professor.id = avaliacao.professor_id"
			+ "	where turma.id = ?1"
			+ "	and avaliacao.anoletivo = ?2"
			+ "	and avaliacao.bimestre = ?3"
			+ "	and aluno.id = ?4"
			+ "	and avaliacao.disciplina = ?5"
			+ " order by aluno.nomealuno", nativeQuery = true)
	public List<AlunoAvaliacao> getAlunoAvaliacaoNative(Long idTurma, Integer anoletivo, Integer bimestre, Long idAluno, int disciplina);
	
	@Query(value = "select * from alunoavaliacao  av"
			+ "	left join  aluno aluno on av.aluno_id = aluno.id"
			+ "	left join avaliacao avaliacao on av.avaliacao_id = avaliacao.id"
			+ "	left join AlunoTurma alunoTurma on alunoTurma.aluno_id = av.aluno_id"
			+ "	left join Turma turma on turma.id = alunoTurma.turma_id"
			+ "	left join Professor professor on professor.id = avaliacao.professor_id"
			+ "	where turma.id = ?1"
			+ "	and avaliacao.anoletivo = ?2"
			+ "	and avaliacao.bimestre = ?3"
			+ "	and aluno.id = ?4"
			+ "	and professor.id = ?5"
			+ "	and avaliacao.disciplina = ?6"
			+ " order by aluno.nomealuno", nativeQuery = true)
	public List<AlunoAvaliacao> getAlunoAvaliacaoNative(Long idTurma, Integer anoletivo, Integer bimestre, Long idAluno, Long idProfessor, int disciplina);

	@Query(value = "select * from alunoavaliacao  av"
			+ "	left join  aluno aluno on av.aluno_id = aluno.id"
			+ "	left join avaliacao avaliacao on av.avaliacao_id = avaliacao.id"
			+ "	left join AlunoTurma alunoTurma on alunoTurma.aluno_id = av.aluno_id"
			+ "	left join Turma turma on turma.id = alunoTurma.turma_id"
			+ "	left join Professor professor on professor.id = avaliacao.professor_id"
			+ "	where turma.id = ?1"
			+ "	and avaliacao.anoletivo = ?2"
			+ "	and avaliacao.bimestre = ?3"
			+ "	and professor.id = ?4"
			+ "	and avaliacao.disciplina = ?5"
			+ " order by aluno.nomealuno", nativeQuery = true)
	public List<AlunoAvaliacao> getAlunoAvaliacaoProfessorNative(Long idTurma, Integer anoletivo, Integer bimestre,	Long idProfessor, int disciplina);
	
//	List<AlunoAvaliacao> findByTurma(Long idturma);
	 
//    List<AlunoAvaliacao> findByTurmaEBimestre(Long idTurma, BimestreEnum bimestre);
    
//    @Query(value = "SELECT * FROM AlunoAvaliacao WHERE aluno_id = ?1", nativeQuery = true)
//    AlunoAvaliacao findByEmailAddress(String emailAddress);
//    
//    @Query("select u from User u where u.firstname = :firstname or u.lastname = :lastname")
//    AlunoAvaliacao findByLastnameOrFirstname(@Param("lastname") String lastname,
//                                   @Param("firstname") String firstname);
}
