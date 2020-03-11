package application;

import dao.UsuariosDAO;
import domain.Usuario;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class PrincipalController {
	@FXML
	private ComboBox<Usuario> CbUsuario;
	@FXML
	private ComboBox<String> CbUsuarioTurma;
	@FXML
	private PasswordField PfSenha;
	@FXML
	private Button btnLogin;
	@FXML
	private Button btnSair;
	@FXML
	private TextField txtUsuario;
	@FXML
	TextField txtSenha;
	@FXML
	private Button btnConfig;
	@FXML
	private BorderPane panePrincipal;

	UsuariosDAO userDAO = new UsuariosDAO();

	@FXML
	public void initialize() {
		CbUsuarioTurma.setItems(FXCollections.observableArrayList(userDAO.listarTodasTurmas()));
		// CbUsuario.setItems(FXCollections.observableArrayList(userDAO.filtrar("200")));
		//String turma = CbUsuarioTurma.getSelectionModel().getSelectedItem();
		// CbUsuario.setItems(FXCollections.observableArrayList(userDAO.filtrar(CbUsuarioTurma.getSelectionModel().getSelectedItem().toString())));
		//CbUsuario.setItems(FXCollections.observableArrayList(userDAO.filtrar(turma)));
		//System.out.println(turma);
	}

	@FXML
	private void PreencheUsuario() {
		String turma = CbUsuarioTurma.getSelectionModel().getSelectedItem();
		CbUsuario.setItems(FXCollections.observableArrayList(userDAO.filtrar(turma)));
	}

	@FXML
	public void abreJanela2() {
		try {
			Stage AbreTelaJogo = new Stage();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("TelaJogo.fxml"));
			Parent root = loader.load();
			AbreTelaJogo.setScene(new Scene(root));
			AbreTelaJogo.initModality(Modality.WINDOW_MODAL);
			AbreTelaJogo.initStyle(StageStyle.UNDECORATED);
			AbreTelaJogo.setResizable(false);
			AbreTelaJogo.show();
			Stage stage = (Stage) panePrincipal.getScene().getWindow();
			stage.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void abreJanela3() {
		try {
			Stage AbreTelaJogo = new Stage();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("TelaCadastroUsuario.fxml"));
			Parent root = loader.load();
			AbreTelaJogo.setScene(new Scene(root));
			AbreTelaJogo.initModality(Modality.WINDOW_MODAL);
			AbreTelaJogo.initStyle(StageStyle.UNDECORATED);
			AbreTelaJogo.setResizable(false);
			AbreTelaJogo.show();
			Stage stage = (Stage) panePrincipal.getScene().getWindow();
			stage.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void abreJanela4() {
		try {
			Stage AbreTelaJogo = new Stage();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("TelaAreaProfessor.fxml"));
			Parent root = loader.load();
			AbreTelaJogo.setScene(new Scene(root));
			AbreTelaJogo.initModality(Modality.WINDOW_MODAL);
			AbreTelaJogo.initStyle(StageStyle.UNDECORATED);
			AbreTelaJogo.setResizable(false);
			AbreTelaJogo.show();
			Stage stage = (Stage) panePrincipal.getScene().getWindow();
			stage.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void MudaNomeButton() {
		if (txtUsuario.getText().equals("admin") && PfSenha.getText().equals("1234"))
			btnConfig.setVisible(true);
		else
			btnConfig.setVisible(false);
	}

	@FXML
	public void SairdoJogo() {
		Stage stage = (Stage) panePrincipal.getScene().getWindow();
		stage.close();
	}

}
