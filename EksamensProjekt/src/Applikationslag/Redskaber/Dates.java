package Applikationslag.Redskaber;

import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.Locale;

public final class Dates {
	public static int getCurrentWeek() {
	    LocalDate date = LocalDate.now();
	    WeekFields weekFields = WeekFields.of(Locale.getDefault());
	    return date.get(weekFields.weekOfWeekBasedYear());
	}
}
