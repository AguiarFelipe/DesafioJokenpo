package br.com.desafio.jokenpo;

import br.com.desafio.jokenpo.model.MatchModel;
import br.com.desafio.jokenpo.model.MoveModel;
import br.com.desafio.jokenpo.model.PlayersModel;
import br.com.desafio.jokenpo.model.ResultsModel;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Data
@Service
public class JokenpoServer {
    private List<MatchModel> SERVER_MATCHS = new ArrayList<>();
    private List<PlayersModel> SERVER_PLAYERS = new ArrayList<>();
    private List<MoveModel> SERVER_MOVES = new ArrayList<>();
    private List<ResultsModel> SERVER_RESULTS = new ArrayList<>();
}
