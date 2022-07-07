import static org.junit.Assert.*;

import org.junit.Test;

public class AnalyzerTest {
	
	/*
	 * This class contains a unit test for calculateSentenceScore.
	 * 
	 * Because it uses the Analyzer(String) constructor, which calls calculateWordScores, which
	 * calls readFile, this test executes all three of those methods.
	 * 
	 * Be sure that the "reviews.txt" file is in your project's root directory or the directory
	 * where you started Java.
	 * 
	 * This test is assumed to be sound and should pass on a correct implementation of this program.
	 */

	@Test
	public void test() {
		Analyzer a = new Analyzer("reviews.txt");
		String sentence = "Ratatouille is a funny , cute , brilliant , and wonderful movie . It is my favorite and I love love LOVE it .";
		double score = a.calculateSentenceScore(sentence);
		assertEquals(0.26478, score, 0.0001);
	}

}
