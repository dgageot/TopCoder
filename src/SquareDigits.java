import java.util.HashSet;
import java.util.Set;

public class SquareDigits {
	Set<Integer> squareSet(int n) {
		Set<Integer> set = new HashSet<Integer>();

		int nextSquare = squareDigits(n);
		while (set.add(nextSquare)) {
			nextSquare = squareDigits(nextSquare);
		}

		return set;
	}

	int squareDigits(int n) {
		int square = 0;
		while (n >= 10) {
			int digit = n % 10;
			square += digit * digit;
			n /= 10;
		}
		return square + n * n;
	}

	public int smallestResult(int n) {
		for (int i = 0; i <= 2666; i++) {
			Set<Integer> squareSet = squareSet(i);
			if (squareSet.contains(n)) {
				return i;
			}
		}
		throw new IllegalArgumentException("Value too large");
	}
}
