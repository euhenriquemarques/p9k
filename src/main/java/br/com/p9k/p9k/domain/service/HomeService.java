package br.com.p9k.p9k.domain.service;

import br.com.p9k.p9k.domain.Utils;
import br.com.p9k.p9k.domain.dto.*;
import br.com.p9k.p9k.domain.entidade.*;
import br.com.p9k.p9k.domain.repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class HomeService {


    private final SaldoRepository saldoRepository;
    private final DespesaRepository despesaRepository;
    private final DespesaCartaoRepository despesaCartaoRepository;
    private final ExtratoDespesaRepository extratoDespesaRepository;
    private final ExtratoDespesaCartaoRepository extratoDespesaCartaoRepository;
    private final CartaoRepository cartaoRepository;

    public HomeService(SaldoRepository saldoRepository, DespesaRepository despesaRepository, DespesaCartaoRepository despesaCartaoRepository, ExtratoDespesaRepository extratoDespesaRepository, ExtratoDespesaCartaoRepository extratoDespesaCartaoRepository, CartaoRepository cartaoRepository) {
        this.saldoRepository = saldoRepository;
        this.despesaRepository = despesaRepository;
        this.despesaCartaoRepository = despesaCartaoRepository;
        this.extratoDespesaRepository = extratoDespesaRepository;
        this.extratoDespesaCartaoRepository = extratoDespesaCartaoRepository;
        this.cartaoRepository = cartaoRepository;
    }

    public HomeDTO buscarHome(int idUsuario) {

        List<Saldo> listaSaldo = saldoRepository.buscarPorIdUsuario(idUsuario);
        List<Despesa> listaDespesa = despesaRepository.buscarDespesasVigentes(idUsuario, Utils.buscarDataInicioMes(), Utils.buscarDataFimMes());
        Double valortotalParcelado = despesaRepository.buscarValorTotalParcelado(idUsuario, Utils.buscarDataInicioMes());
        Double valortotalRecorrente = despesaRepository.buscarValorTotalRecorrente(idUsuario);
        List<ExtratoDespesa> listaDespesaPagas = extratoDespesaRepository.findDespesasAtivasByUsuarioAndPagas(idUsuario, Utils.buscarDataInicioMes(), Utils.buscarDataFimMes());
        List<ExtratoDespesaCartao> listaDespesaCartaoPaga = extratoDespesaCartaoRepository.findDespesaCartaoPaga(idUsuario, Utils.buscarDataInicioMes(), Utils.buscarDataFimMes());

        List<DespesaCartaoDTO> listaDespesaCartaoMesFechado = getExtratoDespesaCartaoFechado(idUsuario);
        List<DespesaCartaoDTO> listaDespesaCartao12Meses = getExtratoDespesaCartaoAberto12Meses(idUsuario);
        List<ResumoMensalDTO> listaResumoMensal = buscarResumoMensal(idUsuario, listaDespesaCartao12Meses);
        listaDespesaCartaoMesFechado = listaDespesaCartaoMesFechado.stream()
                .filter(despesaFechada ->
                        listaDespesaCartaoPaga.stream()
                                .map(despesaPaga -> despesaPaga.getCartao().getId())
                                .noneMatch(cartaoPaga -> cartaoPaga.equals(despesaFechada.getIdCartao()))
                )
                .collect(Collectors.toList());


        return montarHomeDTO(listaSaldo, listaDespesa, listaDespesaPagas, listaDespesaCartaoPaga, valortotalParcelado, valortotalRecorrente, listaDespesaCartaoMesFechado, listaDespesaCartao12Meses, listaResumoMensal);

    }

    private List<ResumoMensalDTO> buscarResumoMensal(int idUsuario, List<DespesaCartaoDTO> listaDespesaCartao12Meses) {
        List<ResumoMensalDTO> listaResumo = new ArrayList<>();
        listaResumo.add(buscarMensalDespesas(idUsuario));
        listaResumo.add(buscarMensalReceita(idUsuario));
        listaResumo.add(buscarMensalCartao(listaDespesaCartao12Meses));
       return listaResumo;
    }

    private static HomeDTO montarHomeDTO(List<Saldo> listaSaldo, List<Despesa> listaDespesa, List<ExtratoDespesa> listaDespesaPagas,
                                         List<ExtratoDespesaCartao> listaDespesaCartaoPaga, Double valortotalParcelado, Double valortotalRecorrente,
                                         List<DespesaCartaoDTO> listaDespesaCartaoMesFechado, List<DespesaCartaoDTO> listaDespesaCartao12Meses, List<ResumoMensalDTO> listaResumoMensal) {
        HomeDTO homeDTO = HomeDTO.builder().listaSaldo(listaSaldo.stream().map(item -> {
                    return SaldoDTO.builder().saldo(item.getSaldo())
                            .banco(item.getConta().getBanco().getDescricao())
                            .numero(item.getConta().getNumero().replaceFirst("^.{2}", "XX"))
                            .build();
                }).collect(Collectors.toList()))
                .listaDespesa(listaDespesa.stream().map(item -> {
                    return DespesaDTO.builder()
                            .categoria(item.getCategoria().getDescricao())
                            .parcela(item.getParcela())
                            .parcelaTotais(item.getParcelaTotais())
                            .dataVencimentoParcela(item.getDataVencimentoParcela())
                            .valorParcela(item.getValorParcela())
                            .descricao(item.getDescricao())
                            .build();
                }).collect(Collectors.toList()))
                .listaExtratoDespesa(listaDespesaPagas.stream().map(item -> {
                    return ExtratoDespesaDTO.builder()
                            .despesa(item.getDespesa().getDescricao())
                            .valor(item.getValor())
                            .valorDesconto(item.getValorDesconto())
                            .valorJuros(item.getValorJuros())
                            .dataPagamento(item.getDataPagamento())
                            .parcela(item.getDespesa().getParcela())
                            .dataVencimento(item.getDespesa().getDataVencimentoParcela())
                            .build();
                }).collect(Collectors.toList()))
                .listaExtratoDespesaCartao(listaDespesaCartaoPaga.stream().map(item -> {
                    return ExtratoDespesaCartaoDTO.builder()
                            .cartao(item.getCartao().getDescricao())
                            .valor(item.getValor())
                            .valorDesconto(item.getValorDesconto())
                            .valorJuros(item.getValorJuros())
                            .dataPagamento(item.getDataPagamento())
                            .build();
                }).collect(Collectors.toList()))
                .valortotalParcelado(valortotalParcelado)
                .valortotalRecorrente(valortotalRecorrente)
                .listaDespesaCartao(listaDespesaCartaoMesFechado)
                .listaDespesaCartao12Meses(listaDespesaCartao12Meses)
                .listaResumoMensal(listaResumoMensal)
                .build();
        return homeDTO;
    }


    public List<DespesaCartaoDTO> getExtratoDespesaCartaoFechado(int idUsuario) {

        List<Cartao> listaCartoes = cartaoRepository.buscarCartaoPorUsuario(idUsuario);
        List<DespesaCartaoDTO> listaRetorno = new ArrayList<>();

        for (Cartao cartao : listaCartoes) {

//            28
            String dataFechamento = cartao.getDataFechamento();

//        17
            LocalDate hoje = LocalDate.from(Utils.buscarDataAtual());

//        28/10/2024
            LocalDate fimPeriodo = LocalDate.of(hoje.getYear(), hoje.getMonth(), Integer.parseInt(dataFechamento));

            // Se a data de fechamento ainda não ocorreu neste mês, retrocede um mês
            if (hoje.isBefore(fimPeriodo) || hoje.isEqual(fimPeriodo)) {
//            28/09/2024
                fimPeriodo = fimPeriodo.minusMonths(1);
            }

            // Calcula a data de início (1 mês antes do fim do período)
//        29/08/2024
            LocalDate inicioPeriodo = fimPeriodo.minusMonths(1).withDayOfMonth(Integer.parseInt(dataFechamento)).plusDays(1);

            // Converte para LocalDateTime (início do dia para a data de início e fim do dia para a data de fim)
            LocalDateTime inicio = inicioPeriodo.atStartOfDay();
            LocalDateTime fim = fimPeriodo.plusDays(1).atStartOfDay().minusSeconds(1);

            // Chama o repositório com as datas calculadas
            Double somaPorCartaoDentroPeriodo = despesaCartaoRepository.findSomaPorCartaoDentroPeriodo(cartao.getId(), inicio, fim);
            if (somaPorCartaoDentroPeriodo != null) {
                listaRetorno.add(DespesaCartaoDTO.builder().idCartao(cartao.getId()).cartao(cartao.getDescricao()).valor(somaPorCartaoDentroPeriodo).build());
            }
        }
        return listaRetorno;
    }

    public ResumoMensalDTO buscarMensalDespesas(int idUsuario) {



            ResumoMensalDTO despesaDTO = ResumoMensalDTO.builder()
                    .categoria("Despesa")
                    .meses(new LinkedHashMap<>())
                    .build();

        for (int somaMes = 0; somaMes <= 12; somaMes++) {

            LocalDate hoje = LocalDate.from(Utils.buscarDataAtual());

            LocalDate inicioPeriodo = LocalDate.of(hoje.getYear(), hoje.getMonth(), 1);
            inicioPeriodo = inicioPeriodo.plusMonths(somaMes);
            LocalDate fimNovoPeriodo = inicioPeriodo.plusMonths(1);

            LocalDateTime inicio = inicioPeriodo.atStartOfDay();
            LocalDateTime fim = fimNovoPeriodo.plusDays(1).atStartOfDay().minusSeconds(1);
            fim = fim.minusDays(1);

            Double somaPorCartaoDentroPeriodo = despesaRepository.findSomaMensal(idUsuario, inicio, fim);
            if (somaPorCartaoDentroPeriodo != null) {
                String mesAno = fim.getMonth().getDisplayName(TextStyle.FULL, Locale.getDefault()) + "/" + fim.getYear();
                despesaDTO.getMeses().put(mesAno, somaPorCartaoDentroPeriodo);
                despesaDTO.setValor(somaPorCartaoDentroPeriodo);
            }

        }

        return despesaDTO;

    }
    public ResumoMensalDTO buscarMensalReceita(int idUsuario) {

            ResumoMensalDTO despesaDTO = ResumoMensalDTO.builder()
                    .categoria("Receita")
                    .meses(new LinkedHashMap<>())
                    .build();

        for (int somaMes = 0; somaMes <= 12; somaMes++) {



            LocalDate hoje = LocalDate.from(Utils.buscarDataAtual());

            LocalDate inicioPeriodo = LocalDate.of(hoje.getYear(), hoje.getMonth(), 1);
            inicioPeriodo = inicioPeriodo.plusMonths(somaMes);
            LocalDate fimNovoPeriodo = inicioPeriodo.plusMonths(1);

            LocalDateTime inicio = inicioPeriodo.atStartOfDay();
            LocalDateTime fim = fimNovoPeriodo.plusDays(1).atStartOfDay().minusSeconds(1);
            fim = fim.minusDays(1);

            Double somaPorCartaoDentroPeriodo = despesaRepository.findSomaMensal(idUsuario, inicio, fim);
            if (somaPorCartaoDentroPeriodo != null) {
                String mesAno = fim.getMonth().getDisplayName(TextStyle.FULL, Locale.getDefault()) + "/" + fim.getYear();
                despesaDTO.getMeses().put(mesAno, somaPorCartaoDentroPeriodo);
                despesaDTO.setValor(somaPorCartaoDentroPeriodo);
            }


    }

        return despesaDTO;
    }

    public ResumoMensalDTO buscarMensalCartao(List<DespesaCartaoDTO> listaCartao) {
        List<ResumoMensalDTO> listaRetorno = new ArrayList<>();


            ResumoMensalDTO despesaDTO = ResumoMensalDTO.builder()
                    .categoria("Cartao")
                    .meses(new LinkedHashMap<>())
                    .build();
        for (int somaMes = 0; somaMes <= 12; somaMes++) {



            LocalDate hoje = LocalDate.from(Utils.buscarDataAtual());

            LocalDate inicioPeriodo = LocalDate.of(hoje.getYear(), hoje.getMonth(), 1);
            inicioPeriodo = inicioPeriodo.plusMonths(somaMes);
            LocalDate fimNovoPeriodo = inicioPeriodo.plusMonths(1);

            LocalDateTime fim = fimNovoPeriodo.plusDays(1).atStartOfDay().minusSeconds(1);
            fim = fim.minusDays(1);

            String mesAno = fim.getMonth().getDisplayName(TextStyle.FULL, Locale.getDefault()) + "/" + fim.getYear();

            double total = listaCartao.stream()
                    .map(DespesaCartaoDTO::getMeses)
                    .map(meses -> meses.get(mesAno))
                    .filter(Objects::nonNull)
                    .mapToDouble(Double::doubleValue)
                    .sum();

                despesaDTO.getMeses().put(mesAno, total);
                despesaDTO.setValor(total);


        }

        return despesaDTO;

    }


    public List<DespesaCartaoDTO> getExtratoDespesaCartaoAberto12Meses(int idUsuario) {

        List<Cartao> listaCartoes = cartaoRepository.buscarCartaoPorUsuario(idUsuario);
        List<DespesaCartaoDTO> listaRetorno = new ArrayList<>();

        for (Cartao cartao : listaCartoes) {

            DespesaCartaoDTO despesaCartaoDTO = DespesaCartaoDTO.builder()
                    .idCartao(cartao.getId())
                    .cartao(cartao.getDescricao())
                    .meses(new LinkedHashMap<>())
                    .build();

            for (int somaMes = 0; somaMes <= 12; somaMes++) {

                String dataFechamento = cartao.getDataFechamento();
                LocalDate hoje = LocalDate.from(Utils.buscarDataAtual());

                LocalDate inicioPeriodo = LocalDate.of(hoje.getYear(), hoje.getMonth(), Integer.parseInt(dataFechamento)).plusDays(1);
                inicioPeriodo = inicioPeriodo.plusMonths(somaMes);


                if (hoje.isBefore(inicioPeriodo) || hoje.isEqual(inicioPeriodo)) {
                    inicioPeriodo = inicioPeriodo.minusMonths(1);
                }

                LocalDate fimNovoPeriodo = inicioPeriodo.plusMonths(1);

                LocalDateTime inicio = inicioPeriodo.atStartOfDay();
                LocalDateTime fim = fimNovoPeriodo.plusDays(1).atStartOfDay().minusSeconds(1);
                fim = fim.minusDays(1);


                Double somaPorCartaoDentroPeriodo = despesaCartaoRepository.findSomaPorCartaoDentroPeriodo(cartao.getId(), inicio, fim);
                if (somaPorCartaoDentroPeriodo != null) {
                    String mesAno = fim.getMonth().getDisplayName(TextStyle.FULL, Locale.getDefault()) + "/" + fim.getYear();
                    despesaCartaoDTO.getMeses().put(mesAno, somaPorCartaoDentroPeriodo);
                    despesaCartaoDTO.setValor(somaPorCartaoDentroPeriodo);
                }
            }

            listaRetorno.add(despesaCartaoDTO);
        }
        return listaRetorno;

    }


}
