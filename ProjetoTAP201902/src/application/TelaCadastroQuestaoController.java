package application;

import dao.PerguntaDAO;

import domain.Pergunta;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class TelaCadastroQuestaoController {

	@FXML
	private TextArea txtEnunciado;
	@FXML
	private TextField txtResposta;
	@FXML
	private Button btnGravar;
	@FXML
	private Button btnGerar;
	@FXML
	private Button btnAlterar;
	@FXML
	private Label lblPergunta;
	@FXML
	private Label lblResposta;
	@FXML
	private BorderPane pane;

	private PerguntaDAO perguntaDAO = new PerguntaDAO();

	@FXML
	public void initialize() {

	}

	@FXML
	public void CriaAlternativas() {
		try {
			if (txtEnunciado.getText().equals("") || txtResposta.getText().equals("")) {
				throw new NullPointerException();
			}
			lblPergunta.setText(txtEnunciado.getText());
			lblResposta.setText(txtResposta.getText());
			limpaTela();
			btnGravar.setDisable(false);
			btnAlterar.setDisable(false);
			btnGerar.setDisable(true);
		} catch (NullPointerException e) {
			mensagemErroValidacao("Enunciado, resposta devem ser preenchidos !");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void limpaTela() {
		txtEnunciado.setText("");
		txtResposta.setText("");
		btnGravar.setDisable(false);
	}

	public void limpaTelaPR() {
		lblPergunta.setText("");
		lblResposta.setText("");

	}

	private Pergunta tela4Pergunta() {
		Pergunta p = new Pergunta();

		p.setColunaEnunciado(lblPergunta.getText());
		p.setColunaResposta(lblResposta.getText());
		return p;
	}

	@FXML
	public void cadastrar() {
		try {
			Pergunta p = tela4Pergunta();
			perguntaDAO.inserir(p);
			limpaTelaPR();
			btnGerar.setDisable(false);
			btnAlterar.setDisable(true);
			btnGravar.setDisable(true);
			Stage Principal = new Stage();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("TelaConfiguracao.fxml"));
			Parent root = loader.load();
			Principal.setScene(new Scene(root));
			Principal.initModality(Modality.WINDOW_MODAL);
			Principal.initStyle(StageStyle.UNDECORATED);
			Principal.setResizable(false);
			Principal.show();
			Stage stage = (Stage) pane.getScene().getWindow();
			stage.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void alterar() {
		txtEnunciado.setText(lblPergunta.getText());
		txtResposta.setText(lblResposta.getText());
		lblPergunta.setText("lblPergunta");
		lblResposta.setText("lblResposta");
		btnAlterar.setDisable(true);
		btnGravar.setDisable(true);
		btnGerar.setDisable(false);
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
