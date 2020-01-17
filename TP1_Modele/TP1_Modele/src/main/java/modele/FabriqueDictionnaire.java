package modele;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.text.Collator;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;
import java.util.TreeMap;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * TP1-CORENTIN ET HUGO
 *
 */
public class FabriqueDictionnaire {
	private static FabriqueDictionnaire instance = null;
	private Properties properties = new Properties();
	@FXML
	private TextField inputRecherche;
	@FXML
	private Button btnAjouter;
	@FXML
	private Button btnSupprimer;

	private static Collator collator = Collator.getInstance(Locale.CANADA);
	private Map<String, Mot> dictionary = new TreeMap<String, Mot>(collator);

	/**
	 * Crée un dictionnaire et lit la feuille des propriétés du programme
	 */
	private FabriqueDictionnaire() {
		lireProprietes();
		creerDictionaire();
	}

	/**
	 * Sert a obtenir la map du dictionnaire
	 * 
	 * @return la map du dictionnaire
	 */
	public Map<String, Mot> getMapDico() {
		return dictionary;
	}

	/**
	 * Remplis la map dictionnaire avec le fichier texte qui contient les mots
	 */
	private void creerDictionaire() {
		String motLu = "";

		try (FileReader fluxSource = new FileReader(

				new File("src/main/resources/liste.de.mots.francais.frgut.utf8.txt"));

				BufferedReader bufferSource = new BufferedReader(fluxSource);) {

			int j = 0;
			while ((motLu = bufferSource.readLine()) != null) {
				Scanner scan = new Scanner(motLu);
				while (scan.hasNext()) {
					String word = scan.next().toLowerCase();
					dictionary.put(motLu, new Mot(motLu));
					j++;

				}
			}

			bufferSource.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

	}

	/**
	 * Sert a obtenir l'etat d'instance de la fabriqueDictionnaire
	 * 
	 * @return l'etat d'instance de la fabriqueDictionnaire
	 */
	public static FabriqueDictionnaire getInstance() {
		if (instance == null) {
			instance = new FabriqueDictionnaire();
		}
		return instance;
	}

	/**
	 * Lit les propriétés qui se trouve dans le fichié xml
	 */
	private void lireProprietes() {
		InputStream in = this.getClass().getResourceAsStream("../properties.xml");

		try {
			properties.loadFromXML(in);

			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Retourne le mot saisie en parametre si il existe dans le dicitonnaire
	 * 
	 * @param str nom du mot
	 * @return le mot saisie en parametre
	 */
	public Mot getMot(String str) {

		return dictionary.get(str);

	}

	/**
	 * Retourn vrai si la clé saisie en parametre eciste dans le dictionnaire
	 * 
	 * @param ky clé recherché
	 * @return vrai si la clé saisie en parametre eciste dans le dictionnaire
	 */
	public boolean chercherKey(String ky) {
		boolean rep = false;
		if (dictionary.containsKey(ky)) {
			rep = true;
		}
		return rep;

	}

}