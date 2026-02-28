package br.com.api.alunoavaliacao.service;

import org.escola.model.Avaliacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.api.alunoavaliacao.repository.AvaliacaoRepository;

@Service
public class AvaliacaoService {

	@Autowired
	private AvaliacaoRepository avaliacaoRepository;
	
	public Avaliacao salvar(Avaliacao avaliacao) {
		return avaliacaoRepository.save(avaliacao);
	}

}
