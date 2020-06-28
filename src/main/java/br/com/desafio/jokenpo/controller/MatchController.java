package br.com.desafio.jokenpo.controller;

import br.com.desafio.jokenpo.model.GenericServiceResponseModel;
import br.com.desafio.jokenpo.model.MatchModel;
import br.com.desafio.jokenpo.service.MatchService;
import br.com.desafio.jokenpo.utils.SwaggerTags;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = SwaggerTags.MATCH_CONTROLLER_TAG)
public class MatchController {

    @Autowired
    private MatchService service;

    @ApiOperation(value = "Endpoint for registration of matches",
            httpMethod = "POST",
            protocols = "HTTP",
            nickname = "matchregister")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "CREATED"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal JokenpoServer Error")})
    @PostMapping(value = "/matchregister",
            produces = MediaType.ALL_VALUE,
            consumes = MediaType.ALL_VALUE)
    public ResponseEntity matchRegister(List<MatchModel> register) throws JsonProcessingException {
        GenericServiceResponseModel result = service.registerMatch(register);
        if(result==null||result.getCode().equals(5)){
            return new ResponseEntity(result,HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity(result, HttpStatus.OK);
        }
    }

    @ApiOperation(value = "Endpoint for consultation of matches",
            httpMethod = "GET",
            protocols = "HTTP",
            nickname = "findmatch")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "CREATED"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal JokenpoServer Error")})
    @GetMapping(value = "/findmatch",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.ALL_VALUE)
    public ResponseEntity matchConsultation(@RequestParam(required = true) Integer idMatch) {
        List<MatchModel> result = service.getMatch(idMatch);
        return result != null ? new ResponseEntity(result, HttpStatus.OK) : new ResponseEntity(GenericServiceResponseModel.of(5, "None match finded with this id: " + idMatch), HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "Endpoint for exclude a match",
            httpMethod = "DELETE",
            protocols = "HTTP",
            nickname = "matchexclusion")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "CREATED"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal JokenpoServer Error")})
    @DeleteMapping(value = "/matchexclusion",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.ALL_VALUE)
    public ResponseEntity matchExclusion(@RequestParam(required = true) Integer idMatch) {
        if (idMatch != 0) {
            try {
                GenericServiceResponseModel result = service.deleteMatch(idMatch);
                return new ResponseEntity(result, HttpStatus.OK);
            } catch (JsonProcessingException e) {
                return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity("Match ID cannot be equal to 0", HttpStatus.BAD_REQUEST);
    }
}
