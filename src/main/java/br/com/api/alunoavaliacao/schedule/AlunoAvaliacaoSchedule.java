package br.com.api.alunoavaliacao.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.api.alunoavaliacao.service.AlunoAvaliacaoService;
import br.com.api.alunoavaliacao.service.AlunoService;
import br.com.api.alunoavaliacao.service.AvaliacaoService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;

import org.escola.enums.BimestreEnum;
import org.escola.enums.DisciplinaEnum;
import org.escola.enums.Serie;
import org.escola.model.Aluno;
import org.escola.model.AlunoAvaliacao;
import org.escola.model.AlunoTurma;
import org.escola.model.Avaliacao;
import org.escola.model.Professor;
import org.escola.model.ProfessorTurma;
import org.escola.model.Turma;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class AlunoAvaliacaoSchedule {

	private static final Logger log = LoggerFactory.getLogger(AlunoAvaliacaoSchedule.class);

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	@Autowired
	private AlunoService alunoService;

	@Autowired
	private AvaliacaoService avaliacaoService;

	@Autowired
	private AlunoAvaliacaoService alunoAvaliacaoService;

//	@Scheduled(cron = "* 30 3 */1 * *")
	@Scheduled(fixedDelay = 50)
	public void criarAvaliacaoDeMediaAlunoAvaliacao() {
		int anoLetivo = alunoService.getConfiguracao().getAnoLetivo();
		BimestreEnum bimestre = alunoService.getConfiguracao().getBimestre();

		List<Turma> turmas = alunoService.getTurmas();
		List<ProfessorTurma> professoresTurma = null;

		for (Turma turma : turmas) {
			professoresTurma = alunoService.getProfessoresTurma(turma.getId());

			for (ProfessorTurma pf : professoresTurma) {
				if (!jaPossuiAvaliacao(pf.getDisciplina(), pf.getProfessor().getId(), bimestre, anoLetivo, turma.getId() , turma.getSerie()  )) {
					log.info("The time is now {}", dateFormat.format(new Date()));
					System.out.println("Criando avaliacao" + dateFormat.format(new Date()));
					Avaliacao avaliacao = criarAvaliacao(anoLetivo, bimestre, turma.getSerie(), pf.getProfessor(),pf.getDisciplina() );
					avaliacaoService.salvar(avaliacao);

				}
			}
		}
	}

	@Scheduled(fixedDelay = 200)
	public void criarAlunoAvaliacao() {
		int anoLetivo = alunoService.getConfiguracao().getAnoLetivo();
		BimestreEnum bimestre = alunoService.getConfiguracao().getBimestre();

		List<Turma> turmas = alunoService.getTurmas();
		List<ProfessorTurma> professoresTurma = null;
		List<AlunoTurma> alunosTurma = null;

		for (Turma turma : turmas) {
			if(Serie.PRE.ordinal() <  turma.getSerie().ordinal()) {
				professoresTurma = alunoService.getProfessoresTurma(turma.getId());
				alunosTurma = alunoService.getAlunoTurmaNative(turma.getId());

				for (ProfessorTurma pf : professoresTurma) {
					for (AlunoTurma at : alunosTurma) {
						if (!jaPossuiAlunoAvaliacao(pf.getDisciplina(), at.getAluno().getId(),pf.getProfessor().getId(), bimestre, anoLetivo,turma.getId())) {
							if (jaPossuiAvaliacao(pf.getDisciplina(), pf.getProfessor().getId(), bimestre, anoLetivo, turma.getId() , turma.getSerie()  )) {
								
								List<Avaliacao> alunosAvaliacao = alunoService.getAvaliacoes(pf.getProfessor().getId(),anoLetivo,bimestre.ordinal(),  pf.getDisciplina().ordinal(), turma.getSerie());
								
								for(Avaliacao av : alunosAvaliacao) {
									AlunoAvaliacao avaliacao = criarAlunoAvaliacao(at.getAluno(),anoLetivo,av);
									alunoAvaliacaoService.salvar(avaliacao);
								}
							}
	  					}
					}
				}
			}
			

		}

	}

	private Avaliacao criarAvaliacao(int ano, BimestreEnum bimestre, Serie serie, Professor professor, DisciplinaEnum discplina) {
		var avaliacao = new Avaliacao();
		avaliacao.setDisciplina(discplina);
		avaliacao.setBimestre(bimestre);
		avaliacao.setAnoLetivo(ano);
		avaliacao.setSerie(serie);
		avaliacao.setProfessor(professor);
		avaliacao.setBimestral(true);
		avaliacao.setData(new Date());
		avaliacao.setNome("Media");
		avaliacao.setPeso(1);
		return avaliacao;
	}

	private AlunoAvaliacao criarAlunoAvaliacao(Aluno aluno, int anoLetivo, Avaliacao avaliacao) {
		var alunoAValiacao = new AlunoAvaliacao();
		alunoAValiacao.setAluno(aluno);
		alunoAValiacao.setAnoLetivo(anoLetivo);
		alunoAValiacao.setAvaliacao(avaliacao);
		return alunoAValiacao;
	}

	private boolean jaPossuiAvaliacao(DisciplinaEnum disciplina, Long idProfessor, BimestreEnum bimestre, int anoLetivo,Long idTurma, Serie serie) {

		if(disciplina == null ) {
			return false;
		}
		try {
			List<Avaliacao> alunosAvaliacao = alunoService.getAvaliacoes(idProfessor,anoLetivo, bimestre.ordinal(),  disciplina.ordinal(), serie);
			if (alunosAvaliacao == null || alunosAvaliacao.size() == 0) {
				return false;
			}
			
		}catch (NoResultException nre) {
			return false;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	private boolean jaPossuiAlunoAvaliacao(DisciplinaEnum disciplina, Long idALuno, Long idProfessor, BimestreEnum bimestre, int anoLetivo,Long idTurma) {

		try {
			List<AlunoAvaliacao> alunosAvaliacao = alunoAvaliacaoService.getAlunoAvaliacaoNative(idTurma,anoLetivo, bimestre.ordinal(),idALuno, idProfessor, disciplina.ordinal());
			if (alunosAvaliacao == null || alunosAvaliacao.size() == 0) {
				return false;
			}
		}catch (Exception e) {
			return false;
		}
		
		return true;
	}

	
}
