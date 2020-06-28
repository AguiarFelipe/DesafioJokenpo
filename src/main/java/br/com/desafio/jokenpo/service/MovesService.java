package br.com.desafio.jokenpo.service;

import br.com.desafio.jokenpo.model.GenericServiceResponseModel;
import br.com.desafio.jokenpo.model.MoveModel;

public interface MovesService {
    GenericServiceResponseModel makeAMove(MoveModel move);
}
