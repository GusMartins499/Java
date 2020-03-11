package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class LoginController {

	@FXML
	private PasswordField txtSenha;
	@FXML
	private Button btnGravar;
	@FXML
	private BorderPane pane;

	@FXML
	public void initialize() {

	}

	@FXML
	public void abreTelaPrincipal() {
		try {
			Stage AbreTelaPrincipal = new Stage();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Principal.fxml"));
			Parent root = loader.load();
			AbreTelaPrincipal.setScene(new Scene(root));
			AbreTelaPrincipal.setResizable(true);
			AbreTelaPrincipal.setMaximized(true);
			AbreTelaPrincipal.show();
			Stage stage = (Stage) pane.getScene().getWindow();
			stage.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void autenticaAcesso() {
		if (txtSenha.getText().equals("karini27")) {
			abreTelaPrincipal();
		}
	}
}
