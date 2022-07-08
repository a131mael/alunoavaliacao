package br.com.api.alunoavaliacao.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.api.alunoavaliacao.service.AlunoAvaliacaoService;
import br.com.api.alunoavaliacao.service.AlunoService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.escola.enums.BimestreEnum;
import org.escola.enums.DisciplinaEnum;
import org.escola.model.AlunoAvaliacao;
import org.escola.model.AlunoTurma;
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
	private AlunoAvaliacaoService alunoAvaliacaoService;

	
//	@Scheduled(cron = "* 30 3 */1 * *")
	@Scheduled(fixedRate = 500)
	public void criarAvaliacaoDeMediaAlunoAvaliacao() {
		int anoLetivo = alunoService.getConfiguracao().getAnoLetivo();
		BimestreEnum bimestre = alunoService.getConfiguracao().getBimestre();
		
		List<Turma> turmas = alunoService.getTurmas();
		List<ProfessorTurma> professoresTurma = null;
		
		for(Turma turma : turmas) {
			professoresTurma = alunoService.getProfessoresTurma(turma.getId());
			 
			List<AlunoTurma> alunosTurma = alunoService.getAlunoTurmaNative(turma.getId());
			for(AlunoTurma at : alunosTurma) {
				for(DisciplinaEnum disciplina : DisciplinaEnum.values()) {
					List<AlunoAvaliacao> alunosAvaliacao=	alunoAvaliacaoService.getAlunoAvaliacaoNative(at.getTurma().getId(), anoLetivo, bimestre.ordinal(), at.getAluno().getId(), disciplina.ordinal());
					if(alunosAvaliacao == null || alunosAvaliacao.size() == 0) {
						for(ProfessorTurma pf : professoresTurma) {
							if(pf.getPrincipal() !=null && pf.getPrincipal()) {
								if(!disciplina.equals(DisciplinaEnum.EDUCACAO_FISICA) &&  !disciplina.equals(DisciplinaEnum.INGLES)) {
									System.out.println("C");
								}
							}
						}
						
						//criarAvaliacaoParaAluno
						System.out.println("b");
					}else {
						System.out.println("a");
					}
				}
			}
		}
		
		log.info("The time is now {}", dateFormat.format(new Date()));
		System.out.println("The time is now {}"+ dateFormat.format(new Date()));
	}
	
}
 