package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class TelaJogoController {

	@FXML
	private BorderPane pane;
	@FXML
	private Label lblUsuario;
	
	@FXML
	public void abreJanelaMatemática() {
		try {
			Stage AbreJogo = new Stage();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("TelaQuestao2.fxml"));
			Parent root = loader.load();
			AbreJogo.setScene(new Scene(root));
			AbreJogo.initModality(Modality.WINDOW_MODAL);
			AbreJogo.initStyle(StageStyle.UNDECORATED);
			AbreJogo.setResizable(false);
			
			TelaRespostaController controller = loader.getController();
			controller.trocaNome(lblUsuario.getText());
			
			AbreJogo.show();
			Stage stage = (Stage) pane.getScene().getWindow();
			stage.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void abreJanelaIngles() {
		try {
			Stage AbreJogo = new Stage();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("TelaQuestao2.fxml"));
			Parent root = loader.load();
			AbreJogo.setScene(new Scene(root));
			AbreJogo.initModality(Modality.WINDOW_MODAL);
			AbreJogo.initStyle(StageStyle.UNDECORATED);
			AbreJogo.setResizable(false);
			
			TelaRespostaController controller = loader.getController();
			controller.trocaNome(lblUsuario.getText());
			
			AbreJogo.show();
			Stage stage = (Stage) pane.getScene().getWindow();
			stage.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void trocaNome(String n) {
		lblUsuario.setText(n);
	}

}
