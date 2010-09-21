public class HowEasy {
	private static String VALID_WORD = "[a-zA-Z]+[\\.]?";

	public int pointVal(String statement) {
		int averageLength = averageLength(statement);
		return averageLength >= 6 ? 1000 : //
				averageLength >= 4 ? 500 : //
						250;
	}

	private static int averageLength(String statement) {
		int valid = 0;
		int sum = 0;

		for (String token : statement.split("[ ]")) {
			if (token.matches(VALID_WORD)) {
				sum += token.length();
				valid++;
			}
		}

		return 0 == valid ? 0 : sum / valid;
	}
}
