


import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Before;
import org.junit.Test;

/*
 * This class contains tests for readFile.
 * 
 * It relies on the "single.txt" and "multiple.txt" files, which should be in your project's
 * root directory, or the directory where you started Java.
 * 
 * These tests are assumed to be sound and should pass on a correct implementation of this method.
 */

public class ReadFileTest {

	
	@Test
	public void testSingleSentence() {
		Set<Sentence> set = null;
		try {
			set = new Analyzer().readFile("single.txt");
		}
		catch (Exception e) {
			fail("readFile throws exception when processing file with one sentence (single.txt): " + e.toString());
		}
		assertNotNull("readFile returns null when processing file with one sentence (single.txt)", set);
		assertEquals("Set returned by readFile contains wrong number of elements when file has one sentence (single.txt)", 1, set.size());

		assertTrue("Set returned by readFile does not contain correct Sentence object when file has one sentence (single.txt)", set.contains(new Sentence(2, "this is a test")));
	
	}

	@Test
	public void testMultipleSentences() {
		Set<Sentence> set = null;
		try {
			set = new Analyzer().readFile("multiple.txt");
		}
		catch (Exception e) {
			fail("readFile throws exception when processing file with multiple sentences (multiple.txt): " + e.toString());
		}
		assertNotNull("readFile returns null when processing file with multiple sentences (multiple.txt)", set);
		assertEquals("Set returned by readFile contains wrong number of elements when file has multiple sentences (multiple.txt)", 2, set.size());

		assertTrue("Set returned by readFile does not contain correct Sentence object when file has multiple sentences (multiple.txt)", set.contains(new Sentence(0, "this is a test")));
		assertTrue("Set returned by readFile does not contain correct Sentence object when file has multiple sentences (multiple.txt)", set.contains(new Sentence(1, "dogs are cute")));
	}


}

