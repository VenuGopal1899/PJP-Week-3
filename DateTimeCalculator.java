import java.time.*;
import java.util.*;
import java.time.temporal.*;
import java.time.format.DateTimeFormatter;

class DateTimeCalculator {

	public static void main(String[] args) {

		DateTimeCalc d = new DateTimeCalc();

		String dt = "20/03/2021";
		String dt2 = "29/03/2021";

		d.addDate(dt, 2);
		d.subDate(dt, dt2);
		d.findDayWeek(dt);
		d.findWeekDay(dt);

		System.out.println("--------");
		System.out.println(d.ndate);
		System.out.println("--------");
		System.out.println(d.diffDays);
		System.out.println(d.diffWeeks);
		System.out.println(d.diffMonths);
		System.out.println("--------");
		System.out.println(d.weekname);
		System.out.println(d.dayOfWeek);
		System.out.println("--------");
		System.out.println(d.weekOfDay);
	}
}

public class DateTimeCalc{
	public long diffDays;
	public long diffWeeks;
	public long diffMonths;

	public int weekOfDay;
	public String ndate;
	public String weekname;
	public int dayOfWeek;
	private String ndate1;

	public void addDate(String date1, int day) {
		DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate localdt = LocalDate.parse(date1, df);

		this.ndate =  df.format(localdt.plusDays(day));
	}

	public void subDate(String date1, String date2) {
		DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate startDate = LocalDate.parse(date1, df);
		LocalDate endDate = LocalDate.parse(date2, df);

		this.diffDays = ChronoUnit.DAYS.between(startDate, endDate);
		this.diffWeeks = ChronoUnit.WEEKS.between(startDate, endDate);
		this.diffMonths = ChronoUnit.MONTHS.between(startDate, endDate);
	}

	public void findDayWeek(String date1) {
		DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate localdt1 = LocalDate.parse(date1, df);

		this.weekname = localdt1.getDayOfWeek().name();

		int weekNumber = localdt1.getDayOfWeek().getValue();
		this.dayOfWeek = (weekNumber == 7) ? 0 : weekNumber;
	}

	public void findWeekDay(String date1) {
		DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate localdt1 = LocalDate.parse(date1, df);
		WeekFields weekFields = WeekFields.of(Locale.getDefault());

		this.weekOfDay = localdt1.get(weekFields.weekOfWeekBasedYear());
	}

	// public void findTranslate(String date1, String phrases, int n) {

	// 	// find the new date by using the phrases and store in ndate1
	// 	// Translate natural language phrases like
	// 	// *Today, Tomorrow, Day-after-tomorrow,
	// 	// yesterday, Day-before-yesterday, Last week, Previous week, Next week,
	// 	// Next month, Next Year,  Last month,
	// 	// Last Year, Month after, Month before,
	// 	// n weeks from now, n days from now,
	// 	// n months from now, n years from now,
	// 	// n weeks earlier, n days earlier,
	// 	// n months earlier, n years earlier*

	// }

}
