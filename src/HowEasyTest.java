import static org.fest.assertions.Assertions.*;
import org.junit.Test;

public class HowEasyTest {
	@Test
	public void canScoreEmptyStatement() {
		assertThat(pointVal("")).isEqualTo(250);
	}

	@Test
	public void canScoreSingleWordStatements() {
		assertThat(pointVal("Two")).isEqualTo(250);
		assertThat(pointVal("Four")).isEqualTo(500);
		assertThat(pointVal("Five.")).isEqualTo(500);
		assertThat(pointVal("SixSix")).isEqualTo(1000);
		assertThat(pointVal("SevenSeven")).isEqualTo(1000);
	}

	@Test
	public void canScoreBasedOnAverageOfTwoIdenticalWords() {
		assertThat(pointVal("Two Two")).isEqualTo(250);
		assertThat(pointVal("Four Four")).isEqualTo(500);
		assertThat(pointVal("Five. Five.")).isEqualTo(500);
		assertThat(pointVal("SixSix SixSix")).isEqualTo(1000);
		assertThat(pointVal("SevenSeven SevenSeven")).isEqualTo(1000);
	}

	@Test
	public void canRecognizeInvalidWords() {
		assertThat(pointVal("+Five.")).isEqualTo(250);
		assertThat(pointVal("Five..")).isEqualTo(250);
		assertThat(pointVal("+++++")).isEqualTo(250);
		assertThat(pointVal("F1ve.")).isEqualTo(250);
		assertThat(pointVal(".seven7")).isEqualTo(250);
	}

	@Test
	public void canScoreWellKnownStatements() {
		assertThat(pointVal("This is a problem statement")).isEqualTo(500);
		assertThat(pointVal("523hi.")).isEqualTo(250);
		assertThat(pointVal("Implement a class H5 which contains some method.")).isEqualTo(500);
		assertThat(pointVal(" no9 . wor7ds he8re. hj..")).isEqualTo(250);
	}

	private int pointVal(String statement) {
		return new HowEasy().pointVal(statement);
	}
}
