package br.com.desafio.jokenpo.model;

import br.com.desafio.jokenpo.enums.ResultMatchStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class ResultsModel {
    private PlayersModel player;
    private Integer idMatch;
    private ResultMatchStatus result;
}
