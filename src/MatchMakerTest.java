import static org.fest.assertions.Assertions.*;
import static org.fest.util.Arrays.*;
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
		return new MatchMaker().getBestMatches(members, currentUser, sf);
	}
}
