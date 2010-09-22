import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MatchMaker {
	private static final int MAX_ANSWERS = 20;

	public String[] getBestMatches(String[] members, String currentUser, int sf) {
		if (members.length < 2) {
			return new String[0];
		}

		String currentMember = null;
		String expectedGenre = null;
		for (String member : members) {
			if (name(member).equals(currentUser)) {
				currentMember = member;
				expectedGenre = expectedGenre(member);
				break;
			}
		}

		Map<Integer, List<String>> matchesPerSimilarAnswers = new HashMap<Integer, List<String>>();
		for (int i = 0; i <= MAX_ANSWERS; i++) {
			matchesPerSimilarAnswers.put(i, new ArrayList<String>());
		}

		for (String member : members) {
			String name = name(member);
			if (!name.equals(currentUser)) {
				if (genre(member).equals(expectedGenre)) {
					int nbSimilar = similar(currentMember, member);
					if (nbSimilar >= sf) {
						matchesPerSimilarAnswers.get(nbSimilar).add(name);
					}
				}
			}
		}

		List<String> matches = new ArrayList<String>();
		for (int nbSimilar = MAX_ANSWERS; nbSimilar >= sf; nbSimilar--) {
			matches.addAll(matchesPerSimilarAnswers.get(nbSimilar));
		}

		return matches.toArray(new String[0]);
	}

	private static int similar(String currentMember, String member) {
		String[] currentMemberAnswers = answers(currentMember);
		String[] memberAnswers = answers(member);

		int similar = 0;
		for (int i = 0; i < currentMemberAnswers.length; i++) {
			if (currentMemberAnswers[i].equals(memberAnswers[i])) {
				similar++;
			}
		}
		return similar;
	}

	private static String[] parts(String member) {
		return member.split("[ ]+");
	}

	private static String name(String member) {
		return parts(member)[0];
	}

	private static String[] answers(String member) {
		return Arrays.asList(parts(member)).subList(0, parts(member).length).toArray(new String[0]);
	}

	private static String genre(String member) {
		return parts(member)[1];
	}

	private static String expectedGenre(String member) {
		return parts(member)[2];
	}
}
