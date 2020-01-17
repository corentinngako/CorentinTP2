package modele;

import java.net.URL;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * TP1-CORENTIN ET HUGO
 *
 */
public class RechercheMot implements Initializable {

	private ObservableList<Mot> obstemp = FXCollections.observableArrayList();
	// un map dictionnaire pour recuperer le fichier
	private Map<String, Mot> mapTemp;

	@FXML
	/**
	 * Supprime un mot du dictionnaire a l'aire de l'index fournis en parametre
	 * 
	 * @param index index de recherche
	 */
	public void supprimerMotModel(String index) {
		FabriqueDictionnaire.getInstance().getMapDico().remove(index);
	}

	@FXML
	/**
	 * Ajout le mot fournis en parametre au dictionnaire
	 * 
	 * @param unMot mot a ajouter
	 */
	public void ajouterrMotModel(Mot unMot) {
		FabriqueDictionnaire.getInstance().getMapDico().put(unMot.getMot(), unMot);
	}

	/**
	 * Gere si l'entré de définition est valide
	 * 
	 * @param str définition du mot
	 * @return vrai si la définition a été envoyé
	 */
	public boolean ajouterDefinitionModel(String str) {

		boolean ok = true;
		if (str != null) {
			if (str.length() > 20) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Erreur");
				alert.setHeaderText("Definition trop longue");
				alert.setContentText("le d?finition ne doit pas depasser 20 mots");
				alert.showAndWait();
				ok = false;
			}
		}
		return ok;
	}

	/**
	 * @param st mot a recherché
	 * @return le mot ou le mot le plus près du mot en parametre dans la map
	 */
	public String rechercherDict(String st) {

		String str = st.toLowerCase();
		List<String> list = new ArrayList<String>();

		Stream<String> setKeyMap = FabriqueDictionnaire.getInstance().getMapDico().keySet().stream();

		list = setKeyMap.filter(x -> x.startsWith(str)).collect(Collectors.toList());
		if (list.size() > 0) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * @param str clé a recherché
	 * @return vrai si la clé envoyé en parametre existe dans la map du dictionnaire
	 */
	public boolean rechMot(String str) {

		return FabriqueDictionnaire.getInstance().chercherKey(str);

	}

	/**
	 * Retourne le mot associé au string envoyé en parametre si il existe dans la
	 * map du dicitionnaire
	 * 
	 * @param str mot a recherché
	 * @return le mot associé au string envoyé en parametre
	 */
	public Mot retourneMot(String str) {
		return FabriqueDictionnaire.getInstance().getMot(str);
	}

	@FXML
	/**
	 * @param index index du mot 
	 * @return Retourne le mot situer a l'index saisie en parametre
	 */
	public Mot cliqueMotModel(String index) {
		mapTemp = FabriqueDictionnaire.getInstance().getMapDico();
		return mapTemp.get(index);

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

}
