package fasttrackit.BugetPersonal.controller.dto;

import fasttrackit.BugetPersonal.enums.TipCheltuiala;

import java.util.Date;

public record CheltuialaOverviewDTO(int id, Date data, TipCheltuiala tip, Double valoare) {
}
