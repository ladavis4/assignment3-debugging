import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class Analyzer {

	protected Map<String, Double> wordScores; 
	
	public Analyzer(String filename) {
		wordScores = calculateWordScores(filename);
	}
	
	public Analyzer() {
		
	}
	
	public Set<Sentence> readFile(String filename) {
		
		Set<Sentence> set = new HashSet<>();
		
		if (filename == null) return set;

		try {
			BufferedReader in = new BufferedReader(new FileReader(filename));
			String line;
			while ((line = in.readLine())  != null) {
				String[] tokens = line.split(" ");

				if (tokens.length >= 2) {
					String first = tokens[0];
					int score = 0;
					try {
						score = Integer.parseInt(first);
						if (score > 2 || score < -2) {
							continue;
						}
					}
					catch (Exception e) {
						continue;
					}

					String text = "";
					for (int i = 1; i < tokens.length; i++) {
						text += tokens[i] + " ";
					}					
					text = text.trim();
					
					Sentence s = new Sentence(score, text);
					set.add(s);
				}			
			}
			in.close();
		}
		catch (Exception e) {
			throw new IllegalStateException("Error occurred while reading input file: " + e.toString());
		}
		
		
		return set;
		
	}



	public Map<String, Double> calculateWordScores(String filename) {
		
		Set<Sentence> sentences = readFile(filename);
		
		Map<String, Double> map = null; 
		
		if (sentences == null || sentences.isEmpty()) return map;
		
		Map<String, Integer> count = new HashMap<>();
		Map<String, Double> total = new HashMap<>();

		
		for (Sentence s : sentences) {
			
			int score = s.getScore();
			String text = s.getText();
			
			String[] words = text.split(" ");
			
			for (String word : words) {
				word = word.toLowerCase();
				if (count.containsKey(word)) {
					count.put(word, count.get(word) + 1);
				}
				else {
					count.put(word, 1);
					total.put(word, score * 1.0);
				}
			}
			
		}
		
		for (String word : count.keySet()) {
			map.put(word, total.get(word) / count.get(word));
		}
		
		return map;
	}
	

	public double calculateSentenceScore(String sentence) {
		
		if (wordScores == null) {
			throw new IllegalStateException("wordScores Map has not been initialized");
		}
				
		String[] words = sentence.split(" ");
		
		int total = 0, count = 0;
		
		for (String word : words) {
			if (wordScores.containsKey(word)) {
				count++;
				total += wordScores.get(word);
			}
		}
		
		if (count == 0) return 0;
		
		return total / count;
		
	}
	

}
