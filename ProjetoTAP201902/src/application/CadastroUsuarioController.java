
package application;

import dao.UsuariosDAO;
import domain.Usuario;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class CadastroUsuarioController {

	@FXML
	private TextField txtLogin;
	@FXML
	private TextField txtTurma;
	@FXML
	private DatePicker dtNascimento;
	@FXML
	private Button btnSalvar;
	@FXML
	private BorderPane pane;

	private UsuariosDAO userDAO = new UsuariosDAO();

	@FXML
	public void fecharTela() {
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

	@FXML
	public void inserir() {
		Usuario u = tela4Usuario();
		userDAO.inserir(u);
		fecharTela();
		
	}

	private Usuario tela4Usuario() {
		Usuario u = new Usuario();
		u.setLogin(txtLogin.getText());
		u.setTurma(txtTurma.getText());
		return u;
	}
	
}
