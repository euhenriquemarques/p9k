package br.com.p9k.p9k.domain;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;

public class Utils {



    public static LocalDateTime buscarDataInicioMes (){
        LocalDateTime dataInicio = LocalDateTime.now(ZoneId.of("America/Sao_Paulo"))
                .with(TemporalAdjusters.firstDayOfMonth())
                .withHour(0)
                .withMinute(0)
                .withSecond(0)
                .truncatedTo(ChronoUnit.SECONDS);

        // Converte para ZonedDateTime no fuso horário local
        ZonedDateTime dataLimiteZoned = dataInicio.atZone(ZoneId.of("America/Sao_Paulo"));

        // Converte para LocalDateTime em UTC
       return dataLimiteZoned.withZoneSameInstant(ZoneOffset.UTC).toLocalDateTime();

    }
    public static LocalDateTime buscarDataAtual (){
          return  LocalDateTime.now(ZoneId.of("America/Sao_Paulo"));

    }
    public static LocalDateTime buscarDataFimMes (){
        LocalDateTime dataInicio = LocalDateTime.now(ZoneId.of("America/Sao_Paulo"))
                .with(TemporalAdjusters.lastDayOfMonth())
                .withHour(23)
                .withMinute(59)
                .withSecond(59)
                .truncatedTo(ChronoUnit.SECONDS);

        // Converte para ZonedDateTime no fuso horário local
        ZonedDateTime dataLimiteZoned = dataInicio.atZone(ZoneId.of("America/Sao_Paulo"));

        // Converte para LocalDateTime em UTC
       return dataLimiteZoned.withZoneSameInstant(ZoneOffset.UTC).toLocalDateTime();

    }

    public static LocalDateTime buscarDataFimMesPassado() {
        LocalDateTime dataInicio = LocalDateTime.now(ZoneId.of("America/Sao_Paulo"))
                .with(TemporalAdjusters.lastDayOfMonth())
                .withHour(23)
                .withMinute(59)
                .withSecond(59)
                .truncatedTo(ChronoUnit.SECONDS);

        // Converte para ZonedDateTime no fuso horário local
        ZonedDateTime dataLimiteZoned = dataInicio.atZone(ZoneId.of("America/Sao_Paulo"));

        // Converte para LocalDateTime em UTC
        return dataLimiteZoned.withZoneSameInstant(ZoneOffset.UTC).toLocalDateTime();
    }

    public static LocalDateTime buscarDataInicioMesPassado() {
        LocalDateTime dataInicio = LocalDateTime.now(ZoneId.of("America/Sao_Paulo"))
                .with(TemporalAdjusters.lastDayOfMonth())
                .withHour(23)
                .withMinute(59)
                .withSecond(59)
                .truncatedTo(ChronoUnit.SECONDS);

        // Converte para ZonedDateTime no fuso horário local
        ZonedDateTime dataLimiteZoned = dataInicio.atZone(ZoneId.of("America/Sao_Paulo"));

        // Converte para LocalDateTime em UTC
        return dataLimiteZoned.withZoneSameInstant(ZoneOffset.UTC).toLocalDateTime();
    }
}
