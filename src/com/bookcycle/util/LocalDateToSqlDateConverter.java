package com.bookcycle.util;

import java.time.LocalDate;

public class LocalDateToSqlDateConverter {

	public static java.sql.Date convert(LocalDate localdate)
	{
		return java.sql.Date.valueOf( localdate );
	}
}
