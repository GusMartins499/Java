package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class PontoFinalController {
	@FXML
	private AnchorPane pane;
	@FXML
	private Label lblUsuario;
	@FXML
	private Label lblPonto;

	@FXML
	public void fechaJanela() {
		Stage stage = (Stage) pane.getScene().getWindow();
		stage.close();
		try {
			Stage Principal = new Stage();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Principal.fxml"));
			Parent root = loader.load();
			Principal.setScene(new Scene(root));
			Principal.initModality(Modality.WINDOW_MODAL);
			Principal.initStyle(StageStyle.UNDECORATED);
			Principal.setResizable(false);
			Principal.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void trocaNome(String n) {
		lblUsuario.setText(n);
	}
	public void exibePonto(int ponto) {
		lblPonto.setText(ponto+"");
	}
}
