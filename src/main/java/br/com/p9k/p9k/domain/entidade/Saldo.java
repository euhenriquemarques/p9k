package br.com.p9k.p9k.domain.entidade;

import lombok.Data;

@Data
public class Saldo {

    private int id;
    private Double saldo;
    private Conta idConta;
}
