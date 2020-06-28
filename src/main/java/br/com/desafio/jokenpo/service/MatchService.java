package br.com.desafio.jokenpo.service;

import br.com.desafio.jokenpo.model.GenericServiceResponseModel;
import br.com.desafio.jokenpo.model.MatchModel;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface MatchService {

    GenericServiceResponseModel registerMatch(List<MatchModel> registerModel) throws JsonProcessingException;

    List<MatchModel> getMatch(Integer idMatch);

    GenericServiceResponseModel deleteMatch(Integer idMatch) throws JsonProcessingException;
}
