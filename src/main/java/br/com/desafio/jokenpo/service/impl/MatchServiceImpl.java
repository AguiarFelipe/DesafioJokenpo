package br.com.desafio.jokenpo.service.impl;

import br.com.desafio.jokenpo.JokenpoServer;
import br.com.desafio.jokenpo.model.GenericServiceResponseModel;
import br.com.desafio.jokenpo.model.MatchModel;
import br.com.desafio.jokenpo.service.MatchService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class MatchServiceImpl implements MatchService {

    @Autowired
    private JokenpoServer server;

    @Override
    public GenericServiceResponseModel registerMatch(List<MatchModel> registerModel) throws JsonProcessingException {
        List<MatchModel> models = new ArrayList<>();
        for (MatchModel model : registerModel) {
            if (model.getId() != null && model.getId() != 0) {
                server.getSERVER_MATCHS().add(model);
            } else {
                models.add(model);
            }
            if (models.size() > 0) {
                return GenericServiceResponseModel.of(1, "Match created with sucess!" + new ObjectMapper().writeValueAsString(registerModel));
            } else if (models.size() == registerModel.size()) {
                return GenericServiceResponseModel.of(5, "None of the matches were created, please check and try again" + new ObjectMapper().writeValueAsString(registerModel));
            } else {
                return GenericServiceResponseModel.of(1, "This match(es) was not created. Please check and try again!" + new ObjectMapper().writeValueAsString(registerModel));
            }
        }
        return null;
    }

    @Override
    public List<MatchModel> getMatch(Integer idMatch) {
        if (idMatch != null && !idMatch.equals(0)) {
            for (MatchModel match : server.getSERVER_MATCHS()) {
                if (idMatch.equals(match.getId())) {
                    return Collections.singletonList(match);
                }
            }
            return null;
        }
        return server.getSERVER_MATCHS();
    }

    @Override
    public GenericServiceResponseModel deleteMatch(Integer idMatch) throws JsonProcessingException {
        List<MatchModel> matches = new ArrayList<>();
        MatchModel removed = new MatchModel();
        for (MatchModel match : server.getSERVER_MATCHS()) {
            if (!idMatch.equals(match.getId())) {
                matches.add(match);
            } else {
                removed = match;
            }
        }
        server.setSERVER_MATCHS(matches);
        return GenericServiceResponseModel.of(1, new ObjectMapper().writeValueAsString(removed));
    }
}
