package br.com.desafio.jokenpo.service;

import br.com.desafio.jokenpo.model.GenericServiceResponseModel;
import br.com.desafio.jokenpo.model.PlayersModel;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface PlayerService {
    GenericServiceResponseModel playerRegister(List<PlayersModel> players) throws JsonProcessingException;

    List<PlayersModel> playerConsultation(Integer idPlayer);

    GenericServiceResponseModel playerRemoval(Integer idPlayer) throws JsonProcessingException;

}
