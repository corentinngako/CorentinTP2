package modele;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class TrierMot implements Initializable {

	@FXML
	/**
	 * Sauvegarde les informations du mot dans la map du dictionnaire
	 * 
	 * @param unMot
	 */
	public void sauvegarderInfoMotModel(Mot unMot) {
		FabriqueDictionnaire.getInstance().getMapDico().get(unMot.getMot()).setDefinition(unMot.getDefinition());
		FabriqueDictionnaire.getInstance().getMapDico().get(unMot.getMot()).setDateModificationMot(LocalDate.now());
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

}
