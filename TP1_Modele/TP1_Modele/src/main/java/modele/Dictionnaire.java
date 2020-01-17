package modele;

import java.text.Collator;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

/**
 * TP1-CORENTIN ET HUGO
 *
 */
public class Dictionnaire {
	private static Dictionnaire instance = null;
	private Properties properties = new Properties();

	private static Collator collator = Collator.getInstance(Locale.CANADA);
	private Map<String, Mot> dictionary = new TreeMap<String, Mot>(collator);

	/**
	 * Sert a obtenire la map du dictionnaire
	 * 
	 * @return la map du dictionnaire
	 */
	public Map<String, Mot> getMapDico() {
		return dictionary;
	}

	/**
	 * Sert a obtenir l'instance du dictionnaire
	 * 
	 * @return l'instance du dictionnaire
	 */
	public static Dictionnaire getInstance() {
		// TODO Auto-generated method stub
		return null;
	}

}
