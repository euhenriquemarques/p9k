package br.com.p9k.p9k.domain.enums;

public enum CategoriaPatrimonio {
    
    // Ativos Imobiliários
    IMOVEL_RESIDENCIAL("Imóvel Residencial"),
    IMOVEL_COMERCIAL("Imóvel Comercial"),
    PROPRIEDADE_INVESTIMENTO("Propriedade de Investimento"),

    // Ativos Financeiros
    CONTA_CORRENTE("Conta Corrente"),
    POUPANCA("Poupança"),
    ACOES("Ações"),
    FUNDOS_INVESTIMENTO("Fundos de Investimento"),
    TITULOS_RENDA_FIXA("Títulos de Renda Fixa"),
    CRIPTOMOEDAS("Criptomoedas"),
    APOSENTADORIA_PRIVADA("Aposentadoria Privada"),
    SEGURO_VALOR_RESGATE("Seguro de Vida com Valor de Resgate"),

    // Veículos e Transporte
    AUTOMOVEL("Automóvel"),
    MOTO("Moto"),
    CAMINHAO("Caminhão"),
    VAN("Van"),
    BARCO("Barco"),
    LANCHA("Lancha"),
    AERONAVE("Aeronave"),
    VEICULO_COLECAO("Veículo de Coleção"),

    // Ativos Empresariais
    PARTICIPACAO_SOCIETARIA("Participação Societária"),
    NEGOCIO_PROPRIO("Negócio Próprio"),

    // Bens Móveis
    JOIAS("Joias"),
    METAIS_PRECIOSOS("Metais Preciosos"),
    OBRAS_ARTE("Obras de Arte"),
    ANTIGUIDADES("Antiguidades"),
    MOVEIS_LUXO("Móveis de Luxo"),

    // Propriedade Intelectual
    PATENTES("Patentes"),
    MARCAS("Marcas"),
    DIREITOS_AUTORAIS("Direitos Autorais"),

    // Ativos de Diversificação
    FUNDO_PENSAO("Fundo de Pensão"),
    COMMODITIES("Commodities"),

    // Bens de Consumo Duráveis
    ELETRODOMESTICOS("Eletrodomésticos"),
    ELETRONICOS("Eletrônicos"),
    FERRAMENTAS_ESPECIALIZADAS("Ferramentas Especializadas"),

    // Ativos Alternativos
    COLECOES("Coleções"),
    VINHEDOS("Vinhedos"),
    TERRAS_AGRICOLAS("Terras Agrícolas"),

    // Patrimônios Intangíveis
    GOODWILL("Goodwill");

    private final String descricao;

    CategoriaPatrimonio(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
