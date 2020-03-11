package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class TelaJogoController {

	@FXML
	Accordion accordion;

	@FXML
	public void iniciar() {
		try {
			Stage TelaJogo = new Stage();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("TelaMath1.fxml"));
			Parent root = loader.load();
			TelaJogo.setScene(new Scene(root, 600, 400));
			TelaJogo.initModality(Modality.WINDOW_MODAL);
			TelaJogo.initStyle(StageStyle.UNDECORATED);
			TelaJogo.setResizable(false);
			TelaJogo.show();
			Stage stage = (Stage) accordion.getScene().getWindow();
			stage.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void fechaJanela() {
		Stage stage = (Stage) accordion.getScene().getWindow();
		stage.close();
		try {
			Stage Principal = new Stage();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Principal.fxml"));
			Parent root = loader.load();
			Principal.setScene(new Scene(root));
			Principal.setResizable(false);
			Principal.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
