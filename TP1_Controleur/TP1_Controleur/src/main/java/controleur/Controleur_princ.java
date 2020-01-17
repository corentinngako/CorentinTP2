package controleur;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * TP1-CORENTIN ET HUGO
 *
 */
public class Controleur_princ extends Application {

	private AnchorPane root;

	@Override
	public void start(Stage primaryStage) throws Exception {
		root = FXMLLoader.load(getClass().getResource("../TP1_InterfacePrincipal.fxml"));
		Scene scene = new Scene(root, 732, 524);
		primaryStage.setScene(scene);
		primaryStage.setTitle("TP1_Corentin_Hugo");
		primaryStage.show();
	}

	public static void main(String[] args) {
		Application.launch(args);

	}
}
