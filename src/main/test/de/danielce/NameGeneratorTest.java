package de.danielce;



import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

import de.danielce.namegenerator.NameGenerator;

public class NameGeneratorTest {
	
	NameGenerator nameGenerator = new NameGenerator();
	Pattern defaultPattern = Pattern.compile("^[a-zA-Z]+-[a-zA-Z]+-[a-zA-Z]+$");
	Pattern customPattern = Pattern.compile("^[a-z]+_[a-z]+_[a-z]+$");
	
	public static final String DEFAULT_FIELPATH_ADJECTIVES = "src/main/resources/adjectives.txt";
	public static final String DEFAULT_FIELPATH_VERBS = "src/main/resources/verbs.txt";
	public static final String DEFAULT_FIELPATH_NOUNS = "src/main/resources/nouns.txt";
	
	@Test
	public void shouldReturnName_whenGenerateName_GivenDefaultConfiguration() throws IOException {
		//given
		
		//when
		String generatedName = nameGenerator.generateName();

		//then
		assertNotNull(generatedName);
		assertTrue(defaultPattern.matcher(generatedName).find());

	}
	
	@Test
	public void shouldReturnName_whenGenerateName_GivenCustomConfiguration() throws IOException {
		//given
		
		//when
		String generatedName = nameGenerator.generateName(true, "_", DEFAULT_FIELPATH_ADJECTIVES, DEFAULT_FIELPATH_VERBS, DEFAULT_FIELPATH_NOUNS);

		//then
		assertNotNull(generatedName);
		System.out.println(generatedName);
		assertTrue(customPattern.matcher(generatedName).find());

	}

}
