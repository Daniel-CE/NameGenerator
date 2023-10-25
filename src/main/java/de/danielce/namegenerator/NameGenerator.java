package de.danielce.namegenerator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.rmi.server.LoaderHandler;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/***
 * Generating human readable names for example files by pattern:
 * <b>adjective</b> <sup>separator</sup><b> progressive verb</b> <sup>separator</sup><b> noun</b>
 * <p>The used files are cached in memory after the first usage.</p>
 * @author Daniel Tegtmeyer
 *
 */
public class NameGenerator {

	
	public static final String DEFAULT_FIELPATH_ADJECTIVES = "src/main/resources/adjectives.txt";
	public static final String DEFAULT_FIELPATH_VERBS = "src/main/resources/verbs.txt";
	public static final String DEFAULT_FIELPATH_NOUNS = "src/main/resources/nouns.txt";
	public static final String DEFAULT_SEPERATOR = "-";
	
	private List<String> adjectives;
	private List<String> verbs;
	private List<String> nouns;

	public NameGenerator() {

	}
	
	/**
	 * Generates a name using NameGenerator.DEFAULT_SEPERATOR -> '-'
	 * @return String in mixed case
	 * @throws IOException
	 */
	public String generateName() throws IOException {
		return generateName(false, DEFAULT_SEPERATOR, DEFAULT_FIELPATH_ADJECTIVES, DEFAULT_FIELPATH_VERBS, DEFAULT_FIELPATH_NOUNS);
	}

	/***
	 * Generates a name using custom config;
	 * @param lowerCase - boolean indicating if lowerCase or not
	 * @param seperator - defines the separator that is used between words
	 * @param filePathAdjectives - path to file (or use NameGenerator.DEFAULT_FIELPATH_ADJECTIVES)
	 * @param filePathVerbs - path to file (or use NameGenerator.DEFAULT_FIELPATH_VERBS)
	 * @param filePathNouns - path to file (or use NameGenerator.DEFAULT_FIELPATH_NOUNS)
	 * @return String 
	 * @throws IOException
	 */
	public String generateName(boolean lowerCase, String seperator, String filePathAdjectives, String filePathVerbs, String filePathNouns) throws IOException {

		String uppercase = getRandomAdjective(filePathAdjectives) + seperator +  getRandomVerb(filePathVerbs) + seperator + getRandomNoun(filePathNouns);

		return lowerCase ?  uppercase.toLowerCase() : uppercase;
	}
	

	private String getRandomAdjective(String filePath) throws IOException {
		
		if(adjectives == null) {
			adjectives = loadListFromFile(filePath);
		}
		return  adjectives.get(new Random().nextInt(adjectives.size() - 1) + 1);
	}
	
	private String getRandomVerb(String filePath) throws IOException {
		
		if(verbs == null) {
			verbs = loadListFromFile(filePath);
		}
		return  verbs.get(new Random().nextInt(verbs.size() - 1) + 1);
	}
	
	private String getRandomNoun(String filePath) throws IOException {
		
		if(nouns == null) {
			nouns = loadListFromFile(filePath);
		}
		return  nouns.get(new Random().nextInt(nouns.size() - 1) + 1);
	}
	
	private List<String> loadListFromFile(String filePathString) throws IOException {
		Path path = Paths.get(filePathString);
		return Files.lines(path).toList();
	}


}
