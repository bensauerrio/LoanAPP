package br.edu.infnet.loanapp.core.utils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.Date;

public final class DateUtils {

	private DateUtils() {

	}

	public static Date addMonth(final Date date, final int months) {
		if (date == null) {
			throw new RuntimeException("A data não deve ser nula");
		}

		LocalDate localDate = date instanceof java.sql.Date //
				? ((java.sql.Date) date).toLocalDate().plusMonths(months) //
				: date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().plusMonths(months);

		while (localDate.getDayOfWeek() == DayOfWeek.SATURDAY || localDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
			localDate = localDate.plusDays(1);
		}

		return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
	}

	public static void main(final String[] args) {
		DateUtils.addMonth(Date.from(
				LocalDate.of(2020, Month.FEBRUARY, 14).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()), 1);
	}
}
