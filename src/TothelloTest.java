import static org.fest.assertions.Assertions.*;
import org.fest.util.*;
import org.junit.*;

public class TothelloTest {
	@Test
	public void canPassTopCoderSample() {
		assertThat(bestMove(Arrays.array("A1", "B8", "C6", "C8", "D8"), Arrays.array("B2", "C2", "C3", "C4", "C5"), "Red")).isEqualTo(11);
	}

	private int bestMove(String[] redPieces, String[] blackPieces, String whoseTurn) {
		return 0;
	}
}
