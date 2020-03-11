package application;

import dao.UsuariosDAO;
import domain.Usuario;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
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
	private PasswordField txtSenha;
	@FXML
	private Button btnLogin;
	@FXML
	private Button btnSair;
	@FXML
	private TextField txtUsuario;
	@FXML
	private Button btnConfig;
	@FXML
	private BorderPane panePrincipal;

	UsuariosDAO userDAO = new UsuariosDAO();

	@FXML
	public void initialize() {
		if (CbUsuarioTurma != null) {
			CbUsuarioTurma.setItems(FXCollections.observableArrayList(userDAO.listarTodasTurmas()));
		}
	}

	@FXML
	private void PreencheUsuario() {
		String turma = CbUsuarioTurma.getSelectionModel().getSelectedItem();
		CbUsuario.setItems(FXCollections.observableArrayList(userDAO.filtrar(turma)));
	}

	@FXML
	public void abreJanela2() {
		try {
			if (CbUsuario.getSelectionModel().getSelectedItem().equals(null)
					|| CbUsuarioTurma.getSelectionModel().getSelectedItem().equals(null)) {
				throw new NullPointerException();
			}
			Stage AbreTelaJogo = new Stage();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("TelaQuestao2.fxml"));
			Parent root = loader.load();
			AbreTelaJogo.setScene(new Scene(root));
			AbreTelaJogo.initModality(Modality.WINDOW_MODAL);
			AbreTelaJogo.initStyle(StageStyle.UNDECORATED);
			AbreTelaJogo.setResizable(false);
			AbreTelaJogo.show();
			TelaRespostaController controller = loader.getController();
			controller.trocaNome(CbUsuario.getSelectionModel().getSelectedItem().getLogin());
			Stage stage = (Stage) panePrincipal.getScene().getWindow();
			stage.close();
		} catch (NullPointerException e) {
			mensagemErroValidacao("Selecione a TURMA e USUÁRIO");
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
	public void abreJanela1() {
		try {
			Stage AbreTelaSalaoCampeoes = new Stage();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("TelaRank.fxml"));
			Parent root = loader.load();
			AbreTelaSalaoCampeoes.setScene(new Scene(root));
			AbreTelaSalaoCampeoes.initModality(Modality.WINDOW_MODAL);
			AbreTelaSalaoCampeoes.initStyle(StageStyle.UNDECORATED);
			AbreTelaSalaoCampeoes.setResizable(false);
			AbreTelaSalaoCampeoes.show();
			Stage stage = (Stage) panePrincipal.getScene().getWindow();
			stage.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void SairdoJogo() {
		Stage stage = (Stage) panePrincipal.getScene().getWindow();
		stage.close();
	}

	private void mensagemErroValidacao(String erro) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setHeaderText("Erro !");
		alert.setContentText(erro);
		alert.initStyle(StageStyle.UNDECORATED);
		alert.getDialogPane().setStyle("-fx-border-color: black); -fx-border-widht: 3;");
		alert.show();
	}

}
