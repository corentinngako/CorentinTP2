package controleur;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import modele.FabriqueDictionnaire;
import modele.Mot;
import modele.RechercheMot;
import modele.TrierMot;

/**
 * TP1-CORENTIN ET HUGO
 *
 */
public class FXMLControleur_princ implements Initializable {
	private String path;
	private RechercheMot rechMot;
	private TrierMot triMot;
	private Mot unMot;

	private ObservableList<String> obstemp = FXCollections.observableArrayList();
	FXMLLoader loaderAjouter = new FXMLLoader(this.getClass().getResource("../TP1_InterfaceFiltre.fxml"));

	@FXML
	private ListView<String> listviewList;

	@FXML
	private ImageView idImp;

	@FXML
	private ImageView imgView;

	@FXML
	private TextField inputRecherche;

	@FXML
	public Label titreFiltre;

	@FXML
	private Hyperlink lienFiltres;

	@FXML
	private Button btnAjouter;

	@FXML
	private Button btnSupprimer;

	@FXML
	private Button btnSauvegarder;

	@FXML
	private Label motTitre;

	@FXML
	private TextArea zoneDefinition;

	@FXML
	private Label saisiModif;

	/**
	 * Controle la barre de recherche en temps réel avec le texte saisie dans le
	 * textfield de recherche.
	 * 
	 * @param str text de la barre de recherche
	 * 
	 */
	private void agirsaisie(String str) {

		String listtemp = rechMot.rechercherDict(str);

		if (listtemp != null) {

			listviewList.scrollTo(listtemp);
			listviewList.getSelectionModel().select(listtemp);
			if (rechMot.rechMot(listtemp)) {
				Mot unMot = rechMot.retourneMot(listtemp);
				afficherMot(unMot);

			}
		}

	}

	/**
	 * Affiche les informations du mot dnas la zone de description du mot
	 * 
	 * @param unMot le mot a afficher
	 */
	private void afficherMot(Mot unMot) {
		btnSupprimer.setDisable(false);
		zoneDefinition.setVisible(true);
		btnSauvegarder.setDisable(false);
		motTitre.setText(unMot.getMot());
		zoneDefinition.setText(unMot.getDefinition());
		saisiModif.setText(unMot.getDateSaisieMot().toString());
	}

	@FXML
	/**
	 * Crée l'interface de gestion du filtre
	 * 
	 * @param event click sur le len filtre
	 */
	void filtrer(ActionEvent event) {
		Stage stage = new Stage();
		AnchorPane r;

		try {
			r = (AnchorPane) loaderAjouter.load();
			Scene scene = new Scene(r, 730, 194);
			stage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();

		}

		FXMLControleur_sec ajoutController = loaderAjouter.<FXMLControleur_sec>getController();
		ajoutController.setStage(stage);
		stage.show();

	}

	@FXML
	/**
	 * Sauvegarde en mémoire les informations entré dans la zone de définition ou
	 * l'image introduite
	 * 
	 * @param event click sur le bouton sauvegarde
	 */
	void sauvegarderInfoMot(ActionEvent event) {
		unMot = rechMot.cliqueMotModel(listviewList.getSelectionModel().getSelectedItem());
		if (zoneDefinition.getText() != null) {
			if (rechMot.ajouterDefinitionModel(zoneDefinition.getText())) {
				unMot.setDefinition(zoneDefinition.getText());
				triMot.sauvegarderInfoMotModel(unMot);
			}
		}
		if (imgView.getImage() != null)
			unMot.setNomImageAssocie(path);
		triMot.sauvegarderInfoMotModel(unMot);
	}

	@FXML
	/**
	 * Supprime le mot selectionné dans la listview de la listview ainsi que du
	 * dictionnaire
	 * 
	 * @param event click sur le bouton supprimer
	 */
	void supprimerMot(ActionEvent event) {

		rechMot.supprimerMotModel(listviewList.getSelectionModel().getSelectedItem());
		listviewList.getItems().remove(listviewList.getSelectionModel().getSelectedIndex());
		motTitre.setText(null);
		zoneDefinition.setVisible(false);
		saisiModif.setText(null);
		imgView.setDisable(true);
	}

	@FXML
	/**
	 * Affiche les informations du mot selectionné dans le listview par
	 * l'utilisateur
	 * 
	 * @param event click sur un mot de la listview
	 */
	void cliqueMot(MouseEvent event) {
		imgView.setDisable(false);
		unMot = rechMot.cliqueMotModel(listviewList.getSelectionModel().getSelectedItem());
		motTitre.setText(unMot.getMot());
		saisiModif.setText(unMot.getDateSaisieMot());
		if (unMot.getNomImageAssocie() != null) {
			Image dbimage = new Image(unMot.getNomImageAssocie());
			imgView.setImage(dbimage);
		} else {
			imgView.setImage(null);
		}

		zoneDefinition.setVisible(true);
		zoneDefinition.setText(unMot.getDefinition());
	}

	/**
	 * Gere le depot d'image dans le liste view pour etre ensuite en mesure de
	 * sauvegarder le path en memoire
	 */
	private void m_drag_and_drop() {
		imgView.setOnDragOver((DragEvent event) -> {
			Dragboard db = event.getDragboard();
			if (db.hasFiles()) {
				event.acceptTransferModes(TransferMode.COPY);
			}
			event.consume();
		});
		imgView.setOnDragDropped((DragEvent e) -> {
			Dragboard db = e.getDragboard();
			btnSauvegarder.setDisable(false);
			if (db.hasFiles()) {
				for (File file : db.getFiles()) {
					String absolutePath = null;
					try {
						absolutePath = file.toURI().toURL().toString();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					Image dbimage = new Image(absolutePath);
					imgView.setImage(dbimage);
					path = absolutePath;
				}
				e.setDropCompleted(true);
			} else {
				e.setDropCompleted(false);
			}
			e.consume();
		});
	}

	@FXML
	/**
	 * Permet a l'utilisateur de sauvegarder les informations du mot entré dans les
	 * zones d'information
	 * 
	 * @param event touche pressé dans la zone de définition
	 */
	void unableButton(KeyEvent event) {
		btnSauvegarder.setDisable(false);
	}

	@FXML
	/**
	 * ajout un écouteur a la barre de recherche et appele la methode agirsaisie a
	 * toute les nouvelles saisie de touche
	 * 
	 * @param event touche pressé dans la barre de recherche
	 */
	void saisirMot(KeyEvent event) {
		inputRecherche.textProperty().addListener((o, oldValue, newValue) -> {
			if (newValue != null) {
				motTitre.setText(newValue);

				if (newValue.length() > 0) {

					agirsaisie(newValue);

				}
			}
		});

	}

	@FXML
	/**
	 * Appele la methode d'ajout de mot de la classe RechercherMot apres avoir
	 * verifier si il existe deja dans le dictionnaire
	 * 
	 * @param event click sur bouton ajout
	 */
	void ajouterMot(MouseEvent event) {
		obstemp.addAll(FabriqueDictionnaire.getInstance().getMapDico().keySet());
		if (!obstemp.contains(inputRecherche.getText())) {
			unMot = new Mot(inputRecherche.getText());
			rechMot.ajouterrMotModel(unMot);
			listviewList.getItems().add(unMot.getMot());
		}
	}

	public void initialize(URL location, ResourceBundle resources) {

		rechMot = new RechercheMot();
		triMot = new TrierMot();
		m_drag_and_drop();

		imgView.setDisable(true);
		ObservableList<String> obStr = FXCollections.observableArrayList();
		obStr.add("chargement..................");
		listviewList.setItems(obStr);
		obstemp.addAll(FabriqueDictionnaire.getInstance().getMapDico().keySet());
		listviewList.setItems(obstemp);

	}

}
