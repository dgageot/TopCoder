import static org.fest.assertions.Assertions.*;
import java.util.Set;
import org.junit.Test;

public class SquareDigitsTest {
	@Test
	public void canComputeForWellKnownNumbers() {
		assertThat(smallestResult(0)).isZero();
		assertThat(smallestResult(1)).isEqualTo(1);
		assertThat(smallestResult(2)).isEqualTo(11);
		assertThat(smallestResult(10)).isEqualTo(7);
		assertThat(smallestResult(19)).isEqualTo(133);
		assertThat(smallestResult(85)).isEqualTo(5);
		assertThat(smallestResult(112)).isEqualTo(2666);
	}

	@Test
	public void canComputeSquareForSingleDigitsNumbers() {
		for (int digit = 0; digit <= 9; digit++) {
			assertThat(squareDigits(digit)).isEqualTo(digit * digit);
		}
	}

	@Test
	public void canComputeSquareForTwoDigitsNumbers() {
		for (int digit1 = 1; digit1 <= 9; digit1++) {
			for (int digit2 = 0; digit2 <= 9; digit2++) {
				assertThat(squareDigits(digit1 * 10 + digit2)).isEqualTo(digit1 * digit1 + digit2 * digit2);
			}
		}
	}

	@Test
	public void canComputeSquareForLargestNumber() {
		assertThat(squareDigits(2666)).isEqualTo(2 * 2 + 6 * 6 + 6 * 6 + 6 * 6);
	}

	@Test
	public void ensureSquareSetContainsFirstSquare() {
		assertThat(squareSet(0)).contains(squareDigits(0));
		assertThat(squareSet(1)).contains(squareDigits(1));
		assertThat(squareSet(100)).contains(squareDigits(100));
	}

	@Test
	public void ensureSquareSetContainsSecondSquare() {
		assertThat(squareSet(2)).contains(squareDigits(squareDigits(2)));
		assertThat(squareSet(100)).contains(squareDigits(squareDigits(100)));
	}

	@Test
	public void canComputeSquareSetForWellKnownNumber() {
		assertThat(squareSet(37)).containsOnly(58, 89, 145, 42, 20, 4, 16, 37);
	}

	@Test
	public void canComputeSmallestResultForEasyCases() {
		assertThat(smallestResult(0)).isEqualTo(0);
		assertThat(smallestResult(1)).isEqualTo(1);
	}

	private Set<Integer> squareSet(int n) {
		return new SquareDigits().squareSet(n);
	}

	private int squareDigits(int n) {
		return new SquareDigits().squareDigits(n);
	}

	private int smallestResult(int n) {
		return new SquareDigits().smallestResult(n);
	}
}
