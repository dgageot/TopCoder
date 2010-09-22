import static java.util.Arrays.*;
import static org.fest.assertions.Assertions.*;
import static org.fest.util.Arrays.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Test;

public class MatchMakerTest {
	@Test
	public void canPassTopCoderSamples() {
		String[] members = new String[] {
				"BETTY F M A A C C", //
				"TOM   M F A D C A", //
				"SUE   F M D D D D", //
				"ELLEN F M A A C A", //
				"JOE   M F A A C A", //
				"ED    M F A D D A", //
				"SALLY F M C D A B", //
				"MARGE F M A A C C"
		};
		assertThat(getBestMatches(members, "BETTY", 2)).isEqualTo(array("JOE", "TOM"));
		assertThat(getBestMatches(members, "JOE", 1)).isEqualTo(array("ELLEN", "BETTY", "MARGE"));
		assertThat(getBestMatches(members, "MARGE", 4)).isEqualTo(array());
	}

	@Test
	public void withLessThanTwoMembersThereIsNoMatch() {
		assertThat(getBestMatches(new String[0], "", 1)).isEqualTo(array());
		assertThat(getBestMatches(new String[1], "", 1)).isEqualTo(array());
	}

	@Test
	public void twoMembersWithSameAnwersIsPerfectMatch() {
		assertThat(getBestMatches(array("BETTY F M A", "TOM M F A"), "BETTY", 1)).isEqualTo(array("TOM"));
		assertThat(getBestMatches(array("BETTY F M A", "TOM M F A"), "TOM", 1)).isEqualTo(array("BETTY"));
	}

	@Test
	public void threeMembersWithSameAnwersIsTwoPerfectMatches() {
		assertThat(getBestMatches(array("BETTY F M A B", "TOM M F A B", "JOE M F A B"), "BETTY", 1)).isEqualTo(array("TOM", "JOE"));
	}

	@Test
	public void matchesWithEnoughSimilarAnswersAreSortedHigherFirst() {
		assertThat(getBestMatches(array("BETTY F M A B", "TOM M F A C", "JOE M F A B"), "BETTY", 1)).isEqualTo(array("JOE", "TOM"));
	}

	@Test
	public void withMembersWhoseGenreIsNotCompatibleThereIsNoMatch() {
		assertThat(getBestMatches(array("BETTY F F", "TOM M F"), "BETTY", 1)).isEqualTo(array());
	}

	private String[] getBestMatches(String[] members, String currentUser, int sf) {
		if (members.length < 2) {
			return new String[0];
		}

		String currentMember = null;
		String expectedGenre = null;
		for (String member : members) {
			if (name(member).equals(currentUser)) {
				currentMember = member;
				expectedGenre = expectedGenre(member);
			}
		}

		Map<Integer, List<String>> matchesPerSimilarAnswers = new HashMap<Integer, List<String>>();
		for (int i = 0; i <= 20; i++) {
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
		for (int nbSimilar = 20; nbSimilar >= sf; nbSimilar--) {
			matches.addAll(matchesPerSimilarAnswers.get(nbSimilar));
		}

		return matches.toArray(new String[0]);
	}

	private int similar(String currentMember, String member) {
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
		return copyOfRange(parts(member), 3, parts(member).length);
	}

	private static String genre(String member) {
		return parts(member)[1];
	}

	private static String expectedGenre(String member) {
		return parts(member)[2];
	}
}
