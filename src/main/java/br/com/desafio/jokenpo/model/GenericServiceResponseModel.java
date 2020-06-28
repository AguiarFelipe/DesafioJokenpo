package br.com.desafio.jokenpo.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class GenericServiceResponseModel {
    private Integer code;
    private String mesage;
}
