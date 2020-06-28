package br.com.desafio.jokenpo.service;

import br.com.desafio.jokenpo.model.ResultsModel;

import java.util.List;

public interface ResultService {
    List<ResultsModel> getMatchResult(Integer idMatch);
}
