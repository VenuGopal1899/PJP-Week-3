import java.time.*;
import java.util.*;
import java.time.temporal.*;
import java.time.format.DateTimeFormatter;

class DateTimeCalculator {

	public static void main(String[] args) {

		DateTimeCalc d = new DateTimeCalc();

		String dt = "02/03/2020";
		String dt2 = "29/03/2021";

		d.addDate(dt, 2);
		d.subDate(dt, dt2);
		d.findDayWeek(dt);
		d.findWeekDay(dt);
		d.findTranslate(dt, "yesterday", 0);
		d.findTranslate(dt, "Month Before", 0);

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
		System.out.println("--------");
		System.out.println(d.ndate1);
	}
}

class DateTimeCalc{
	public long diffDays;
	public long diffWeeks;
	public long diffMonths;

	public int weekOfDay;
	public String ndate;
	public String weekname;
	public int dayOfWeek;
	public String ndate1;

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
		this.dayOfWeek = (localdt1.getDayOfWeek().getValue())%7;
	}

	public void findWeekDay(String date1) {
		DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate localdt1 = LocalDate.parse(date1, df);
		WeekFields weekFields = WeekFields.of(Locale.getDefault());

		this.weekOfDay = localdt1.get(weekFields.weekOfWeekBasedYear());
	}

	public void findTranslate(String date1, String phrases, int n) {

		/*
		Find the new date by using the phrases and store in ndate1
		Translate natural language phrases like
		Today, Tomorrow, Day-after-tomorrow, yesterday, Day-before-yesterday,
		Last week, Previous week, Next week, Next month, Next Year,  Last month,
		Last Year, Month after, Month before, n weeks from now, n days from now,
		n months from now, n years from now, n weeks earlier, n days earlier,
		months earlier, n years earlier
		*/

		DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate localdt = LocalDate.parse(date1, df);

		String givenPhrase = phrases.toLowerCase();

		switch(givenPhrase) {
			case "today":
				this.ndate1 =  df.format(localdt.plusDays(0));
				break;
			case "tomorrow":
				this.ndate1 =  df.format(localdt.plusDays(1));
				break;
			case "day-after-tomorrow":
				this.ndate1 =  df.format(localdt.plusDays(2));
				break;
			case "yesterday":
				this.ndate1 =  df.format(localdt.minusDays(1));
				break;
			case "previous week":
				this.ndate1 =  df.format(localdt.minusDays(7));
				break;
			case "next week":
				this.ndate1 =  df.format(localdt.plusDays(7));
				break;
			case "month after":
			case "next month":
				this.ndate1 =  df.format(localdt.plusMonths(1));
				break;
			case "next year":
				this.ndate1 =  df.format(localdt.plusYears(1));
				break;
			case "last week":
				this.ndate1 =  df.format(localdt.minusDays(7));
				break;
			case "month before":
			case "last month":
				this.ndate1 =  df.format(localdt.minusMonths(1));
				break;
			case "last year":
				this.ndate1 =  df.format(localdt.minusYears(1));
				break;
			case "n weeks from now":
				this.ndate1 =  df.format(localdt.plusDays(7*n));
				break;
			case "n days from now":
				this.ndate1 =  df.format(localdt.plusDays(n));
				break;
			case "n months from now":
				this.ndate1 =  df.format(localdt.plusMonths(n));
				break;
			case "n years from now":
				this.ndate1 =  df.format(localdt.plusYears(n));
				break;
			case "n weeks earlier":
				this.ndate1 =  df.format(localdt.minusDays(7*n));
				break;
			case "n days earlier":
				this.ndate1 =  df.format(localdt.minusDays(n));
				break;
			case "n months earlier":
				this.ndate1 =  df.format(localdt.minusMonths(n));
				break;
			case "n years earlier":
				this.ndate1 =  df.format(localdt.minusYears(n));
				break;
			default:
				this.ndate1 = "";
				break;
		}

	}

}
