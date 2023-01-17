package fasttrackit.BugetPersonal.controller.dto;

import fasttrackit.BugetPersonal.enums.TipVenit;

import java.util.Date;

public record VenitOverviewDTO(int id, Date data, TipVenit tip, Integer valoare) {
}
