package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class TelaAreaProfessorController {

	@FXML
	private TextField txtLogin;
	@FXML
	private PasswordField txtSenha;
	@FXML
	private Button btnSalvar;
	@FXML
	private BorderPane pane;

	@FXML
	public void abreJanela1() {
		try {
			Stage TelaConfiguracaoQuestao = new Stage();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("TelaConfiguracao.fxml"));
			Parent root = loader.load();
			TelaConfiguracaoQuestao.setScene(new Scene(root));
			TelaConfiguracaoQuestao.initModality(Modality.WINDOW_MODAL);
			TelaConfiguracaoQuestao.initStyle(StageStyle.UNDECORATED);
			TelaConfiguracaoQuestao.setResizable(false);
			TelaConfiguracaoQuestao.show();
			Stage stage = (Stage) pane.getScene().getWindow();
			stage.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void verificaLogin() {
		if (txtLogin.getText().equals("professor@letras")) {
			if (txtSenha.getText().equals("@comp")) {
				btnSalvar.setDisable(false);
			}
		}
	}

	@FXML
	private void FecharJanela() {
		Stage stage = (Stage) pane.getScene().getWindow();
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
