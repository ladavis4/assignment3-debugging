


import java.util.*;

/*
 * DO NOT CHANGE THIS CODE!
 * You may add debugging statements, but notify the instructor if you think
 * other modifications are necessary. 
 */

public class Sentence implements Comparable<Sentence> {
	
	private int score;
	private String text;
	
	public Sentence(int score, String text) {
		this.score = score;
		this.text = text;
	}
	
	public int getScore() {
		return score;
	}
	
	public String getText() {
		return text;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		Sentence other = (Sentence) obj;
		return text.equals(other.text) && score == other.score;
	}

	@Override
	public int compareTo(Sentence other) {
		if (text.equals(other.text) && score == other.score) return 0;
		else if (text.equals(other.text) == false) return text.compareTo(other.text);
		else return score - other.score;
	}
	
	@Override
	public int hashCode() {
		return text.hashCode();
	}
	
}
