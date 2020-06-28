package br.com.desafio.jokenpo.controller;

import br.com.desafio.jokenpo.model.GenericServiceResponseModel;
import br.com.desafio.jokenpo.model.PlayersModel;
import br.com.desafio.jokenpo.service.PlayerService;
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
@Api(tags = SwaggerTags.PLAYER_CONTROLLER_TAG)
public class PlayerController {

    @Autowired
    private PlayerService service;

    @ApiOperation(value = "Endpoint for registring players",
            httpMethod = "POST",
            protocols = "HTTP",
            nickname = "playerregister")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "CREATED"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal JokenpoServer Error")})
    @PostMapping(value = "/playerregister",
            produces = MediaType.ALL_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity playerRegister(@RequestBody List<PlayersModel> players) throws JsonProcessingException {
        GenericServiceResponseModel response = service.playerRegister(players);
        return response != null ? new ResponseEntity(response, HttpStatus.OK) : new ResponseEntity("You need to provide at least one player", HttpStatus.BAD_REQUEST);
    }

    @ApiOperation(value = "Endpoint for find players",
            httpMethod = "GET",
            protocols = "HTTP",
            nickname = "playerfinder")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "CREATED"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal JokenpoServer Error")})
    @GetMapping(value = "/playerfinder",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.ALL_VALUE)
    public ResponseEntity<List<PlayersModel>> playerFinder(@RequestParam(required = true) Integer idPlayer) {
        List<PlayersModel> result = service.playerConsultation(idPlayer);
        return result != null ? new ResponseEntity(result, HttpStatus.OK) : new ResponseEntity(GenericServiceResponseModel.of(5, "None match finded with this id: " + idPlayer), HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "Endpoint for exclude players",
            httpMethod = "DELETE",
            protocols = "HTTP",
            nickname = "playerexclusion")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "CREATED"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal JokenpoServer Error")})
    @DeleteMapping(value = "/playerexclusion",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.ALL_VALUE)
    public ResponseEntity playerExclusion(@RequestParam(required = true) Integer idMatch) {
        try {
            return new ResponseEntity(service.playerRemoval(idMatch), HttpStatus.OK);
        } catch (JsonProcessingException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
