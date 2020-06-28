package br.com.desafio.jokenpo.service.impl;

import br.com.desafio.jokenpo.JokenpoServer;
import br.com.desafio.jokenpo.model.GenericServiceResponseModel;
import br.com.desafio.jokenpo.model.PlayersModel;
import br.com.desafio.jokenpo.service.PlayerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class PlayerServiceImpl implements PlayerService {
    @Autowired
    JokenpoServer server;

    @Override
    public GenericServiceResponseModel playerRegister(List<PlayersModel> players) throws JsonProcessingException {
        List<Integer> iD = new ArrayList<>();
        List<PlayersModel> noInsertedPlayers = new ArrayList<>();
        if (players.size() > 0) {
            for (PlayersModel player : players) {
                if (!iD.contains(player.getId())) {
                    server.getSERVER_PLAYERS().add(player);
                } else {
                    noInsertedPlayers.add(player);
                }
            }
            if (noInsertedPlayers.size() == 0) {
                return GenericServiceResponseModel.of(1, "All players inserted with sucess" + new ObjectMapper().writeValueAsString(players));
            } else {
                return GenericServiceResponseModel.of(1, "This players not inserted, please verifiy and re-insert: " + new ObjectMapper().writeValueAsString(noInsertedPlayers));
            }
        }
        return null;
    }

    @Override
    public List<PlayersModel> playerConsultation(Integer idPlayer) {
        if (idPlayer != null && !idPlayer.equals(0)) {
            for (PlayersModel player : server.getSERVER_PLAYERS()) {
                if (idPlayer.equals(player.getId())) {
                    return Collections.singletonList(player);
                }
            }
            return null;
        }
        return server.getSERVER_PLAYERS();
    }

    @Override
    public GenericServiceResponseModel playerRemoval(Integer idPlayer) throws JsonProcessingException {
        List<PlayersModel> players = new ArrayList<>();
        PlayersModel removed = new PlayersModel();
        for (PlayersModel player : server.getSERVER_PLAYERS()) {
            if (!idPlayer.equals(player.getId())) {
                players.add(player);
            } else {
                removed = player;
            }
        }
        server.setSERVER_PLAYERS(players);
        return GenericServiceResponseModel.of(1, new ObjectMapper().writeValueAsString(removed));
    }
}
