import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class IsMatchTest {

	private IsMatch isMatch;
	
	@Before
	public void setUp() throws Exception {
		isMatch = new IsMatch();
	}

	@Test
	public final void testIsMatch() {
		assertTrue(isMatch.isMatch("AA", "XX"));
		assertTrue(isMatch.isMatch("AB", "XX"));
		assertFalse(isMatch.isMatch("AA", "XY"));
		assertTrue(isMatch.isMatch("ABAB", "XXYYXXYY"));
		assertTrue(isMatch.isMatch("ABAB", "XYZXYZ"));
	}

	@Test
	public final void testNumMatches() {
		assertEquals(1, isMatch.numMatches("AB", "XX"));
		assertEquals(0, isMatch.numMatches("AA", "XY"));
		assertEquals(3, isMatch.numMatches("ABAB", "XXYYXXYY")); // 3 solutions: {A = X, B = XYY}, {A = XX, B = YY}, {A = XXY, B = Y}
		assertEquals(2, isMatch.numMatches("ABAB", "XYZXYZ")); // 2 solutions: {A = X, B = YZ}, {A = XY, B = Z}
	}
}
