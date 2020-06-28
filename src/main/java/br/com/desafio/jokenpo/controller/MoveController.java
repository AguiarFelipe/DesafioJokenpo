package br.com.desafio.jokenpo.controller;

import br.com.desafio.jokenpo.model.GenericServiceResponseModel;
import br.com.desafio.jokenpo.model.MoveModel;
import br.com.desafio.jokenpo.service.MovesService;
import br.com.desafio.jokenpo.utils.SwaggerTags;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = SwaggerTags.MOVE_CONTROLLER_TAG)
public class MoveController {

    @Autowired
    private MovesService service;

    @ApiOperation(value = "Endpoint for players to make your move",
            httpMethod = "POST",
            protocols = "HTTP",
            nickname = "movechoice")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "CREATED"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal JokenpoServer Error")})
    @PostMapping(value = "/movechoice",
            produces = MediaType.ALL_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity makeAMove(@RequestBody MoveModel move) {
        GenericServiceResponseModel response = service.makeAMove(move);
        return new ResponseEntity(response, response.getCode().equals(1) ? HttpStatus.OK : response.getCode().equals(2) ? HttpStatus.NO_CONTENT : HttpStatus.NO_CONTENT);
    }
}
