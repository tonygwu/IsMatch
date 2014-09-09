
import java.util.*;

public class IsMatch {

	private Set<Map<Character, String>>[][] computeDynamicProgramming(String pattern, String input) {
		// null means any mapping is possible, empty set means no mappings work.
		Set<Map<Character, String>>[][] dp = new HashSet[pattern.length() + 1][input.length() + 1];
		
		// dp[i][j] is the mappings that are acceptable for pattern[0..i] on input[0..j] where i and j are inclusive
		for (int i = 1; i <= pattern.length(); i++) {
			for (int j = i; j <= input.length(); j++) {
				Set<Map<Character, String>> newMappings = new HashSet<>();
				char pi = pattern.charAt(i - 1);
				for (int k = i; k <= j; k++) {
					final String possibleString = input.substring(k - 1, j);
					final Set<Map<Character, String>> existingMappings = dp[i - 1][k - 1];
					if (existingMappings == null) {
						Map<Character, String> firstMapping = new HashMap<>();
						firstMapping.put(pi, possibleString);
						newMappings.add(firstMapping);
					} else {
						for (Map<Character, String> mapping : existingMappings) {
							final String alreadyMappedString = mapping.get(pi);
							if (alreadyMappedString != null && !alreadyMappedString.equals(possibleString)) {
								continue;
							}
							final Map<Character, String> newMapping = new HashMap<>(mapping);
							newMapping.put(pi, possibleString);
							newMappings.add(newMapping);
						}
					}
				}
				dp[i][j] = newMappings;
			}
		}
		return dp;
	}
	
	public boolean isMatch(String pattern, String input) {
		final Set<Map<Character, String>>[][] dp = computeDynamicProgramming(pattern, input);
		return !dp[pattern.length()][input.length()].isEmpty();
	}
	
	public int numMatches(String pattern, String input) {
		final Set<Map<Character, String>>[][] dp = computeDynamicProgramming(pattern, input);
		return dp[pattern.length()][input.length()].size();
	}
	
	public static void main(String[] args) {
		IsMatch isMatch = new IsMatch();
		System.out.println(isMatch.isMatch("AA", "XX"));
		System.out.println(isMatch.isMatch("AB", "XX"));
		System.out.println(isMatch.isMatch("AA", "XY"));
		System.out.println(isMatch.isMatch("ABAB", "XXYYXXYY"));
		System.out.println(isMatch.isMatch("ABAB", "XYZXYZ"));
		
		System.out.println(isMatch.numMatches("AA", "XX"));
		System.out.println(isMatch.numMatches("AB", "XX"));
		System.out.println(isMatch.numMatches("AA", "XY"));
		System.out.println(isMatch.numMatches("ABAB", "XXYYXXYY"));
		System.out.println(isMatch.numMatches("ABAB", "XYZXYZ"));
	}
}
