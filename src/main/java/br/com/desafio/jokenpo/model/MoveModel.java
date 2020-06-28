package br.com.desafio.jokenpo.model;

import lombok.Data;

import java.util.List;

@Data
public class MoveModel {
    private List<MovePlayerModel> moveOfPlayer;
    private Integer idMatch;
}
