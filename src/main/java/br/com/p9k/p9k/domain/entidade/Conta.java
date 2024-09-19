package br.com.p9k.p9k.domain.entidade;

import lombok.Data;

@Data
public class Conta {


    private int id;
    private String numero;
    private String agencia;
    private Banco idBanco;
    private boolean compartilhado;
    private boolean status;
    private boolean nacional;
    private Usuario idUsuario;

}
