
import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;


/*
 * This class contains tests for calculateSentenceScore.
 * 
 * The tests directly set the Map of word scores and do not use readFile or calculateWordScores.
 * 
 * These tests are assumed to be sound and should pass on a correct implementation of this method.
 */

public class CalculateSentenceScoreTest {

	@Test
	public void testAllWordsInSentenceAndScoresAreInts() {
		Analyzer a = new Analyzer();
		Map<String, Double> map = new HashMap<String, Double>();
		map.put("dog", 2.0);
		map.put("cat", 2.0);
		a.wordScores = map;
		
		try {
			double output = a.calculateSentenceScore("dog cat");
			assertEquals("calculateSentenceScore does not return correct value when all words in Map are in sentence and have integer scores ", 2.0, output, 0);
		}
		catch (Exception e) {
			fail("calculateSentenceScore throws exception when all words in Map are in sentence and have integer scores: " + e.toString());
		}

	}
	
	@Test
	public void testAllWordsInSentenceAndScoresAreDoubles() {
		Analyzer a = new Analyzer();
		Map<String, Double> map = new HashMap<String, Double>();
		map.put("dog", 1.7);
		map.put("cat", 1.9);
		a.wordScores = map;

		try {
			double output = a.calculateSentenceScore("dog cat");
			assertEquals("calculateSentenceScore does not return correct value when all words in Map are in sentence and have floating-point scores", 1.8, output, 0.0001);
		}
		catch (Exception e) {
			fail("calculateSentenceScore throws exception when all words in Map are in sentence and have floating-point scores: " + e.toString());
		}
	}
	
	@Test
	public void testWordInSentenceAppearsMultipleTimes() {
		Analyzer a = new Analyzer();
		Map<String, Double> map = new HashMap<String, Double>();
		map.put("dog", 2.0);
		map.put("cat", -1.0);
		a.wordScores = map;

		try {
			double output = a.calculateSentenceScore("dog cat dog");
			// note: dog should be counted twice, so the average should be (2 + (-1) + 2) / 3 = 1
			assertEquals("calculateSentenceScore does not return correct value when sentence contains multiple instances of word", 1.0, output, 0);
		}
		catch (Exception e) {
			fail("calculateSentenceScore throws exception when sentence contains multiple instances of word: " + e.toString());
		}

	}

	
	@Test
	public void testSomeWordsNotInMap() {
		Analyzer a = new Analyzer();
		Map<String, Double> map = new HashMap<String, Double>();
		map.put("dog", 2.0);
		map.put("cat", 1.0);
		a.wordScores = map;

		try {
			double output = a.calculateSentenceScore("dog cat gorilla");
			// note: gorilla should be treated as 0, so the average should be (2 + 1 + 0) / 3 = 1
			assertEquals("calculateSentenceScore does not return correct value when some words in sentence are not in input Map ", 1.0, output, 0.001);
		}
		catch (Exception e) {
			fail("calculateSentenceScore throws exception when some words in sentence are not in input Map: " + e.toString());
		}
	}
	
	@Test
	public void testIgnoreInvalidWords() {
		Analyzer a = new Analyzer();
		Map<String, Double> map = new HashMap<String, Double>();
		map.put("dog", 2.0);
		map.put("cat", 0.0);
		a.wordScores = map;

		try {
			double output = a.calculateSentenceScore("dog $pig cat");
			// note: $pig should be ignored so the average should be 1.0
			assertEquals("calculateSentenceScore does not return correct value when input sentence contains invalid words", 1.0, output, 0);
		}
		catch (Exception e) {
			fail("calculateSentenceScore throws exception when input sentence contains invalid words: " + e.toString());
		}
	}
	

	@Test
	public void testSentenceContainsNoValidWords() {
		Analyzer a = new Analyzer();
		Map<String, Double> map = new HashMap<String, Double>();
		map.put("dog", 2.0);
		a.wordScores = map;
		
		try {
			double output = a.calculateSentenceScore("$banana");
			assertEquals("calculateSentenceScore returns incorrect value when input sentence contains no valid words", 0, output, 0);
		}
		catch (Exception e) {
			fail("calculateSentenceScore throws exception when input sentence contains no valid words: " + e.toString());
		}

	}

	
	@Test
	public void testCaseInsensitivity() {
		Analyzer a = new Analyzer();
		Map<String, Double> map = new HashMap<String, Double>();
		map.put("dog", 2.0);
		map.put("cat", 0.0);
		a.wordScores = map;

		try {
			double output = a.calculateSentenceScore("DOG cat");
			assertEquals("calculateSentenceScore does not return correct value when input sentence contains words with uppercase letters", 1.0, output, 0);
		}
		catch (Exception e) {
			fail("calculateSentenceScore throws exception when input sentence contains words with uppercase letters: " + e.toString());
		}
	}

	
}