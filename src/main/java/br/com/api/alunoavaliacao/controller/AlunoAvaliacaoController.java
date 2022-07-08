package br.com.api.alunoavaliacao.controller;

import java.util.ArrayList;
import java.util.List;

import org.escola.model.AlunoAvaliacao;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.alunoavaliacao.dto.AlunoAvaliacaoDTO;
import br.com.api.alunoavaliacao.dto.AlunoDTO;
import br.com.api.alunoavaliacao.dto.AvaliacaoDTO;
import br.com.api.alunoavaliacao.dto.RetornoAlunosAvaliacaoDTO;
import br.com.api.alunoavaliacao.service.AlunoAvaliacaoService;

@RestController
@RequestMapping("/api")
public class AlunoAvaliacaoController {

	@Autowired
	AlunoAvaliacaoService alunoAvaliacaoService;

	@GetMapping("/alunosavaliacoes")
	public ResponseEntity<RetornoAlunosAvaliacaoDTO> getalunosAvaliacaoes(@RequestParam(required = true) Long idTurma,
			@RequestParam(required = true) Integer anoletivo, 
			@RequestParam(required = true) Integer bimestre,
			@RequestParam(required = false) Long idAluno, 
			@RequestParam(required = false) Long idProfessor,
			@RequestParam(required = true) int disciplina) {
		try {

			List<AlunoAvaliacao> alunosAvaliacoes = null;
			if (idProfessor == null && idAluno == null) {
				alunosAvaliacoes = alunoAvaliacaoService.getAlunoAvaliacaoNative(idTurma, anoletivo, bimestre, disciplina);
			}

			if (idAluno != null && idProfessor == null ) {
				alunosAvaliacoes = alunoAvaliacaoService.getAlunoAvaliacaoNative(idTurma, anoletivo, bimestre, idAluno, disciplina);
			}

			if (idAluno == null && idProfessor != null) {
				alunosAvaliacoes = alunoAvaliacaoService.getAlunoAvaliacaoProfessorNative(idTurma, anoletivo, bimestre,	idProfessor, disciplina);
			}

			if (idAluno != null && idProfessor != null) {
				alunosAvaliacoes = alunoAvaliacaoService.getAlunoAvaliacaoNative(idTurma, anoletivo, bimestre, idAluno,	idProfessor,disciplina);
			}

			if (alunosAvaliacoes.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			var avDTOs = new ArrayList<AlunoAvaliacaoDTO>();
			for (AlunoAvaliacao av : alunosAvaliacoes) {
				AlunoAvaliacaoDTO avDTO = new AlunoAvaliacaoDTO();
				AlunoDTO aluno = new AlunoDTO();
				AvaliacaoDTO avaliacao = new AvaliacaoDTO();

				aluno.setId(av.getAluno().getId());
				aluno.setNomeAluno(av.getAluno().getNomeAluno());
				
				avaliacao.setId(av.getAvaliacao().getId());
				avaliacao.setNome(av.getAvaliacao().getNome());
				
				avDTO.setId(av.getId());
				avDTO.setNota(av.getNota());
				avDTO.setAnoLetivo(av.getAnoLetivo());
				avDTO.setAluno(aluno);
				avDTO.setAvaliacao(avaliacao);
				avDTOs.add(avDTO);
			}
			
			RetornoAlunosAvaliacaoDTO retorno = new RetornoAlunosAvaliacaoDTO();
			retorno.setAlunosAvaliacao(avDTOs);
			retorno.setCodigo(String.valueOf(avDTOs.size()));
			
			
			return new ResponseEntity<>(retorno, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

//	@GetMapping("/aluno/alunosavaliacoes")
//	public ResponseEntity<List<AlunoAvaliacao>> getAllQuadrinhos(@RequestParam(required = false) Long idTurma,
//			@RequestParam(required = false) Integer anoletivo, @RequestParam(required = false) Integer bimestre,
//			@RequestParam(required = false) Long idAluno) {
//		try {
//
//			List<AlunoAvaliacao> alunosAvaliacoes = alunoAvaliacaoService.getAlunoAvaliacaoNative(idTurma, anoletivo,	bimestre, idAluno,"");
//
//			return new ResponseEntity<>(alunosAvaliacoes, HttpStatus.OK);
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}
//
//	@GetMapping("/professor/alunosavaliacoes")
//	public ResponseEntity<List<AlunoAvaliacao>> getAllQuadrinhos(@RequestParam(required = false) Long idTurma,
//			@RequestParam(required = false) Integer anoletivo, @RequestParam(required = false) Integer bimestre,
//			@RequestParam(required = false) Long idAluno, @RequestParam(required = false) Long idProfessor) {
//		try {
//
//			List<AlunoAvaliacao> alunosAvaliacoes = alunoAvaliacaoService.getAlunoAvaliacaoNative(idTurma, anoletivo,
//					bimestre, idAluno, idProfessor);
//
//			return new ResponseEntity<>(alunosAvaliacoes, HttpStatus.OK);
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}

//    @GetMapping("/alunosavaliacoes/{id}")
//    public ResponseEntity<AlunoAvaliacao> getQuadrinhobyId(@PathVariable("id") long idTurma) {
//        Optional<AlunoAvaliacao> quadrinhoData = alunoAvaliacaoRepository.findById(idTurma);
// 
//        if (quadrinhoData.isPresent()) {
//            return new ResponseEntity<>(quadrinhoData.get(), HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
// 
//    @PostMapping("/alunoavaliacao")
//    public ResponseEntity<AlunoAvaliacao> createQuadrinho(@RequestBody AlunoAvaliacao alunoAvaliacao) {
//        try {
//        	
//        	
//            AlunoAvaliacao _alunoAvaliacao  = alunoAvaliacaoRepository.save(alunoAvaliacao);
//            return new ResponseEntity<>(_alunoAvaliacao, HttpStatus.CREATED);
//        } catch (Exception e) {
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
// 
//    @PutMapping("/alunosavaliacoes/{id}")
//    public ResponseEntity<AlunoAvaliacao> updateAlunoAvaliacao(@PathVariable("id") long id, @RequestBody AlunoAvaliacao alunoAvaliacao) {
//        Optional<AlunoAvaliacao> alunoAvaliacaoData = alunoAvaliacaoRepository.findById(id);
// 
//        if (alunoAvaliacaoData.isPresent()) {
//        	AlunoAvaliacao _alunoAvaliacao = alunoAvaliacaoData.get();
//        	_alunoAvaliacao.setAluno(alunoAvaliacao.getAluno());
//        	_alunoAvaliacao.setAnoLetivo(alunoAvaliacao.getAnoLetivo());
//        	_alunoAvaliacao.setAvaliacao(alunoAvaliacao.getAvaliacao());
//            return new ResponseEntity<>(alunoAvaliacaoRepository.save(_alunoAvaliacao), HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
// 
//    @DeleteMapping("/alunosavaliacoes/{id}")
//    public ResponseEntity<HttpStatus> deleteQuadrinho(@PathVariable("id") long id) {
//        try {
//            alunoAvaliacaoRepository.deleteById(id);
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
// 
////    @GetMapping("/turmas/{idturma}/{bimestre}")
////    public ResponseEntity<List<AlunoAvaliacao>> findByEditora(@PathVariable("idturma") Long idTurma, int bimestre) {
////        try {
////            List<AlunoAvaliacao> quadrinhos = alunoAvaliacaoRepository.findByTurmaEBimestre(idTurma, BimestreEnum.values()[bimestre-1]);
//// 
////            if (quadrinhos.isEmpty()) {
////                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
////            }
//// 
////            return new ResponseEntity<>(quadrinhos, HttpStatus.OK);
//// 
////        } catch (Exception e) {
////            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
////        }
////    }
//	
}
