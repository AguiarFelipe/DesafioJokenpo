package br.com.desafio.jokenpo.controller;

import br.com.desafio.jokenpo.model.GenericServiceResponseModel;
import br.com.desafio.jokenpo.model.ResultsModel;
import br.com.desafio.jokenpo.service.ResultService;
import br.com.desafio.jokenpo.utils.SwaggerTags;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(tags = SwaggerTags.RESULT_CONTROLLER_TAG)
public class ResultController {

    @Autowired
    ResultService service;

    @ApiOperation(value = "Endpoint to get a match result or all results",
            httpMethod = "GET",
            protocols = "HTTP",
            nickname = "findresult")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "CREATED"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal JokenpoServer Error")})
    @GetMapping(value = "/findresult",
            produces = MediaType.ALL_VALUE,
            consumes = MediaType.ALL_VALUE)
    public ResponseEntity findResult(Integer idMatch) {
        List<ResultsModel> result = service.getMatchResult(idMatch);
        if (result.equals(null)) {
            return new ResponseEntity(GenericServiceResponseModel.of(5, "Not exists result for this match"), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(result, HttpStatus.OK);
    }
}
