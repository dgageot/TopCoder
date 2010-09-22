import static org.fest.assertions.Assertions.*;
import static org.fest.util.Arrays.*;
import org.junit.*;

public class PrerequisitesTest {
	@Test
	public void canOrderWellKnownExamples() {
		assertThat(orderClasses("CSE121: CSE110", "CSE110:", "MATH122:")).isEqualTo(array("CSE110", "CSE121", "MATH122"));
		assertThat(orderClasses("ENGL111: ENGL110", "ENGL110: ENGL111")).isEqualTo(array());
		assertThat(orderClasses("ENGL111: ENGL110")).isEqualTo(array());
		assertThat(orderClasses("CSE258: CSE244 CSE243 INTR100", "CSE221: CSE254 INTR100", "CSE254: CSE111 MATH210 INTR100", "CSE244: CSE243 MATH210 INTR100", "MATH210: INTR100", "CSE101: INTR100", "CSE111: INTR100", "ECE201: CSE111 INTR100", "ECE111: INTR100", "CSE243: CSE254", "INTR100:")).isEqualTo(array("INTR100", "CSE101", "CSE111", "ECE111", "ECE201", "MATH210", "CSE254", "CSE221", "CSE243", "CSE244", "CSE258"));
	}

	@Test
	public void canOrderSingleThreeLettersCourse() {
		assertThat(orderClasses("CSE121:")).isEqualTo(array("CSE121"));
	}

	@Test
	public void canOrderSingleFourLettersCourse() {
		assertThat(orderClasses("ENGL111:")).isEqualTo(array("ENGL111"));
	}

	@Test
	public void canOrderCoursesByNumber() {
		assertThat(orderClasses("ENGL900:", "ENGL111:", "ENGL100:")).isEqualTo(array("ENGL100", "ENGL111", "ENGL900"));
	}

	@Test
	public void canOrderCoursesByDepartement() {
		assertThat(orderClasses("CSE100:", "ENGL100:", "ABS100:", "ABSA100:")).isEqualTo(array("ABS100", "ABSA100", "CSE100", "ENGL100"));
	}

	@Test
	public void canOrderCoursesByNumberThenByDepartement() {
		assertThat(orderClasses("ENGL900:", "CSE100:", "ENGL111:", "ENGL100:", "ABS900:")).isEqualTo(array("CSE100", "ENGL100", "ENGL111", "ABS900", "ENGL900"));
		assertThat(orderClasses("CSE110:", "MATH122:")).isEqualTo(array("CSE110", "MATH122"));
		assertThat(orderClasses("CSE121:", "MATH122:")).isEqualTo(array("CSE121", "MATH122"));
	}

	@Test
	public void canOrderTwoDependantCourses() {
		assertThat(orderClasses("ENGL100: MATH100", "MATH100:")).isEqualTo(array("MATH100", "ENGL100"));
	}

	@Test
	public void canOrderThreeDependantCourses() {
		assertThat(orderClasses("ENGL100: MATH100", "MATH100:", "INTR099: ENGL100")).isEqualTo(array("MATH100", "ENGL100", "INTR099"));
	}

	private String[] orderClasses(String... classSchedule) {
		return new Prerequisites().orderClasses(classSchedule);
	}
}
