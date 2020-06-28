package br.com.desafio.jokenpo.service.impl;

import br.com.desafio.jokenpo.JokenpoServer;
import br.com.desafio.jokenpo.enums.ResultMatchStatus;
import br.com.desafio.jokenpo.model.*;
import br.com.desafio.jokenpo.service.MatchService;
import br.com.desafio.jokenpo.service.MovesService;
import br.com.desafio.jokenpo.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovesServiceImpl implements MovesService {
    @Autowired
    JokenpoServer server;

    @Autowired
    PlayerService playerService;

    @Autowired
    MatchService matchService;

    @Override
    public GenericServiceResponseModel makeAMove(MoveModel moves) {
        MovePlayerModel winner = new MovePlayerModel();
        List<PlayersModel> listPlayer = new ArrayList<>();
        PlayersModel player;

        if (matchService.getMatch(moves.getIdMatch()) != null) {
            for (MovePlayerModel mp : moves.getMoveOfPlayer()) {
                player = playerService.playerConsultation(mp.getIdPlayer()).get(0);
                if (player != null) {
                    listPlayer.add(player);
                }
            }
        } else {
            return GenericServiceResponseModel.of(3, "This move was not possible because the informed match does not exist");
        }
        Integer pivot = 0;
        if (listPlayer.size() > 0) {
            winner = moves.getMoveOfPlayer().get(0);
            for (int x = 0; x < moves.getMoveOfPlayer().size(); x++) {
                if (winner.getMove().name().equals("PEDRA") && moves.getMoveOfPlayer().get(x).getMove().name().equals("PAPEL")) {
                    winner = moves.getMoveOfPlayer().get(x);
                    pivot+=1;
                } else if (winner.getMove().name().equals("PAPEL") && moves.getMoveOfPlayer().get(x).getMove().name().equals("TESOURA")) {
                    winner = moves.getMoveOfPlayer().get(x);
                    pivot+=1;
                } else if (winner.getMove().name().equals("TESOURA") && moves.getMoveOfPlayer().get(x).getMove().name().equals("PEDRA")) {
                    winner = moves.getMoveOfPlayer().get(x);
                    pivot+=1;
                }
            }
        } else {
            return GenericServiceResponseModel.of(2, "This move was not possible because the informed player does not exist");
        }
        if (pivot>0){
            server.getSERVER_RESULTS().add(ResultsModel.of(playerService.playerConsultation(winner.getIdPlayer()).get(0), moves.getIdMatch(), ResultMatchStatus.WINNER));
            return GenericServiceResponseModel.of(1, "Move executed with sucess.");
        }else{
            server.getSERVER_RESULTS().add(ResultsModel.of(null, moves.getIdMatch(), ResultMatchStatus.TIED));
            return GenericServiceResponseModel.of(1, "Move executed with sucess.");
        }

    }
}
