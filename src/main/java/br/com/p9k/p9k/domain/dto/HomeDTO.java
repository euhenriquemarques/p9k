package br.com.p9k.p9k.domain.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class HomeDTO {

    private List<SaldoDTO> listaSaldo;
}
