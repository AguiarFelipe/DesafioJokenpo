package br.com.desafio.jokenpo.service.impl;

import br.com.desafio.jokenpo.JokenpoServer;
import br.com.desafio.jokenpo.model.ResultsModel;
import br.com.desafio.jokenpo.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ResultServiceImpl implements ResultService {
    @Autowired
    JokenpoServer server;

    @Override
    public List<ResultsModel> getMatchResult(Integer idMatch) {
        List<String> moves = new ArrayList<>();
        ResultsModel result = new ResultsModel();

        for (ResultsModel resultMatch : server.getSERVER_RESULTS()) {
            if (resultMatch.getIdMatch().equals(idMatch)) {
                return Collections.singletonList(resultMatch);
            }
        }
        return null;
    }
}
