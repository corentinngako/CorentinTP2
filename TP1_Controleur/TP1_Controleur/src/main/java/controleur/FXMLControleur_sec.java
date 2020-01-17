package controleur;

import java.net.URL;
import java.time.LocalDate;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import modele.FabriqueDictionnaire;
import modele.GestionFiltre;
import modele.Mot;

/**
 * TP1-CORENTIN ET HUGO
 *
 */
public class FXMLControleur_sec implements Initializable {

	private Stage stageFiltre;
	private ObservableList<Mot> obstemp2 = FXCollections.observableArrayList();
	private ObservableList<Mot> obstemp3 = FXCollections.observableArrayList();
	private Map<String, Mot> mapTemp;

	@FXML
	private ImageView idimsec;

	@FXML
	private CheckBox chbox1;

	@FXML
	private ChoiceBox<String> chcbox1;

	@FXML
	private ChoiceBox<String> chcbox2;

	@FXML
	private DatePicker datebox;

	@FXML
	private CheckBox chbox2;

	@FXML
	private Button btnAnnuler;

	@FXML
	private Button btnAppliquer;

	protected void setStage(Stage pStage) {
		this.stageFiltre = pStage;

	}

	@FXML
	/**
	 * Ferme l'interface de gestion du filtre
	 * 
	 * @param event click sur le bouton annuler 
	 */
	void stopFiltre(MouseEvent event) {
		this.stageFiltre.close();
	}

	@FXML
	/**
	 * Determine la bonne selection des differents elements de l'interface et appele
	 * ensuite la bonne methode de la classe GestionFiltre
	 * 
	 * @param event click sur le bouton appliquer
	 */
	void appliquerFiltre(MouseEvent event) {
		mapTemp = FabriqueDictionnaire.getInstance().getMapDico();
		obstemp2.addAll(mapTemp.values());
		if (!chbox2.isSelected()) {
			if (chbox1.isSelected()) {
				if (chcbox1.getSelectionModel().getSelectedItem() == "Mot saisi") {
					if (chcbox2.getSelectionModel().getSelectedItem() == "Avant le") {
						obstemp3.setAll((obstemp2.stream().filter(GestionFiltre.dateSaisiAvant(datebox.getValue()))
								.collect(Collectors.<Mot>toList())));
					} else {
						obstemp3.setAll((obstemp2.stream().filter(GestionFiltre.dateSaisiApres(datebox.getValue()))
								.collect(Collectors.<Mot>toList())));
					}
				} else {
					if (chcbox2.getSelectionModel().getSelectedItem() == "Avant le") {
						obstemp3.setAll((obstemp2.stream().filter(GestionFiltre.dateModifAvant(datebox.getValue()))
								.collect(Collectors.<Mot>toList())));
					} else {
						obstemp3.setAll((obstemp2.stream().filter(GestionFiltre.dateModifApres(datebox.getValue()))
								.collect(Collectors.<Mot>toList())));
					}
				}
			}

			for (int i = 0; i < obstemp3.size(); i++) {
				System.out.println(obstemp3.get(i).getMot());
			}

		} else {
			if (chbox1.isSelected()) {
				if (chcbox1.getSelectionModel().getSelectedItem() == "Mot saisi") {
					if (chcbox2.getSelectionModel().getSelectedItem() == "Avant le") {
						obstemp3.setAll((obstemp2.stream().filter(GestionFiltre.dateSaisiAvantImg(datebox.getValue()))
								.collect(Collectors.<Mot>toList())));
					} else {
						obstemp3.setAll((obstemp2.stream().filter(GestionFiltre.dateSaisiApresImg(datebox.getValue()))
								.collect(Collectors.<Mot>toList())));
					}
				} else {
					if (chcbox2.getSelectionModel().getSelectedItem() == "Avant le") {
						obstemp3.setAll((obstemp2.stream().filter(GestionFiltre.dateModifAvantImg(datebox.getValue()))
								.collect(Collectors.<Mot>toList())));
					} else {
						obstemp3.setAll((obstemp2.stream().filter(GestionFiltre.dateModifApresImg(datebox.getValue()))
								.collect(Collectors.<Mot>toList())));
					}
				}
			} else {
				obstemp3.setAll((obstemp2.stream().filter(GestionFiltre.imgInclu()).collect(Collectors.<Mot>toList())));
			}

			for (int i = 0; i < obstemp3.size(); i++) {
				System.out.println(obstemp3.get(i).getMot());
			}

		}
	}

	public void initialize(URL location, ResourceBundle resources) {

		chcbox1.getItems().addAll("Mot saisi", "Mot modifier");
		chcbox1.setValue("Mot saisi");
		chcbox2.getItems().addAll("Avant le", "Apres le");
		chcbox2.setValue("Avant le");
		datebox.setValue(LocalDate.now());
	}

}
