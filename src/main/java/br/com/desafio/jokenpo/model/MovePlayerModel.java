package br.com.desafio.jokenpo.model;

import br.com.desafio.jokenpo.enums.MovesChoice;
import lombok.Data;

@Data
public class MovePlayerModel {
    private Integer idPlayer;
    private MovesChoice move;
}
