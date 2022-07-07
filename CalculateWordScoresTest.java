
import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

public class CalculateWordScoresTest {
	
/*
 * This class contains tests for calculateWordScores.
 * 
 * It uses a mock object to implement readFile, so that the tests do not actually
 * need to read from any input files.
 * 
 * These tests are assumed to be sound and should pass on a correct implementation of this method.
 */
	
	@Test
	public void testWordAppearsOnceInOneSentence() {
		
		Map<String, Double> map = null;
		try {
			Analyzer a = new Analyzer() {
				@Override
				public Set<Sentence> readFile(String filename) {
					Set<Sentence> set = new HashSet<>();
					set.add(new Sentence(2, "dog cat"));
					return set;
				}
			};
			map = a.calculateWordScores("placeholder");
		}
		catch (Exception e) {
			fail("calculateWordScores throws exception when Set contains one Sentence: " + e.toString());
		}

		assertNotNull("calculateWordScores returns null when Set contains one Sentence", map);
		
		assertEquals("Map returned by calculateWordScores has incorrect number of entries when Set contains one Sentence", 2, map.size());
		assertTrue("Map returned by calculateWordScores has incorrect key when Set contains one Sentence", map.containsKey("dog"));
		assertTrue("Map returned by calculateWordScores has incorrect key when Set contains one Sentence", map.containsKey("cat"));
		assertTrue("Map returned by calculateWordScores has incorrect value when Set contains one Sentence", map.get("dog") == 2.0);
		assertTrue("Map returned by calculateWordScores has incorrect value when Set contains one Sentence", map.get("cat") == 2.0);
		
	}

	@Test
	public void testWordAppearsOnceInMultipleSentences() {
		
		Map<String, Double> map = null;
		try {
			Analyzer a = new Analyzer() {
				@Override
				public Set<Sentence> readFile(String filename) {
					Set<Sentence> set = new HashSet<>();
					set.add(new Sentence(2, "dog cat"));
					set.add(new Sentence(1, "banana dog"));
					return set;
				}
			};
			map = a.calculateWordScores("placeholder");
		}
		catch (Exception e) {
			fail("calculateWordScores throws exception when Set contains multiple Sentences: " + e.toString());
		}
		
		assertNotNull("calculateWordScores returns null when Set contains multiple Sentences", map);

		assertEquals("Map returned by calculateWordScores has incorrect number of entries when Set contains multiple Sentences", 3, map.size());
		assertTrue("Map returned by calculateWordScores has incorrect key when Set contains multiple Sentences with same word", map.containsKey("dog"));
		assertTrue("Map returned by calculateWordScores has incorrect value when Set contains multiple Sentences with same word", map.get("dog") == 1.5);		
	}
	
	@Test
	public void testWordAppearsMultipleTimesInMultipleSentences() {

		
		Map<String, Double> map = null;
		try {
			Analyzer a = new Analyzer() {
				@Override
				public Set<Sentence> readFile(String filename) {
					Set<Sentence> set = new HashSet<>();
					set.add(new Sentence(1, "dog cat"));
					set.add(new Sentence(-2, "dog banana dog"));
					return set;
				}
			};
			map = a.calculateWordScores("placeholder");		
		}
		catch (Exception e) {
			fail("calculateWordScores throws exception when Set contains Sentence with multiple instances of same word: " + e.toString());
		}
		assertNotNull("calculateWordScores returns null when Set contains Sentence with multiple instances of same word", map);
		
		assertEquals("Map returned by calculateWordScores has incorrect number of entries when Set contains Sentences with multiple instances of same word", 3, map.size());
		assertTrue("Map returned by calculateWordScores has incorrect key when Set contains Sentences with multiple instances of same word", map.containsKey("dog"));
		assertTrue("Map returned by calculateWordScores has incorrect value when Set contains Sentences with multiple instances of same word", map.get("dog") == -1);		
	}

	
	@Test
	public void testIgnoreWordThatDoesNotStartWithLetter() {
		
		Map<String, Double> map = null;
		try {
			Analyzer a = new Analyzer() {
				@Override
				public Set<Sentence> readFile(String filename) {
					Set<Sentence> set = new HashSet<>();
					set.add(new Sentence(1, "dog $cat banana"));
					return set;
				}
			};
			map = a.calculateWordScores("placeholder");	
		}
		catch (Exception e) {
			fail("calculateWordScores throws exception when Set contains Sentence with word that does not start with letter: " + e.toString());
		}
		
		assertNotNull("calculateWordScores returns null when Set contains Sentence with word that does not start with letter", map);
		
		assertFalse("Map returned by calculateWordScores contains key that does not start with letter", map.containsKey("$cat"));
				
	}
	
	
	
	@Test
	public void testCaseInsensitive() {
		
		Map<String, Double> map = null;
		try {
			Analyzer a = new Analyzer() {
				@Override
				public Set<Sentence> readFile(String filename) {
					Set<Sentence> set = new HashSet<>();
					set.add(new Sentence(1, "banana DOG"));
					return set;
				}
			};
			map = a.calculateWordScores("placeholder");	
		}
		catch (Exception e) {
			fail("calculateWordScores throws exception when Set contains Sentence with word in uppercase letters: " + e.toString());
		}
		assertNotNull("calculateWordScores returns null when Set contains Sentence with word in uppercase letters", map);

		assertTrue("Map returned by calculateWordScores has incorrect key when Set contains Sentence with word in uppercase letters", map.containsKey("dog"));
		assertFalse("Map returned by calculateWordScores contains key with word in uppercase letters", map.containsKey("DOG"));
		
		assertTrue("Map returned by calculateWordScores has incorrect value when Set contains Sentence with word in uppercase letters", map.get("dog") == 1.0);
	}


}
