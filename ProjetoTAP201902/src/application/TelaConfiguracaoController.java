package application;

import dao.PerguntaDAO;
import domain.Pergunta;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class TelaConfiguracaoController {

	@FXML
	private Pane pane;
	@FXML
	private TableView<Pergunta> tbl;
	@FXML
	private TableColumn<Pergunta, String> colEnunciado;
	@FXML
	private TableColumn<Pergunta, String> colResposta;
	@FXML
	private TextField txtFiltro;
	@FXML
	private TextField txtPergunta;
	@FXML
	private TextField txtResposta;

	private PerguntaDAO dao = new PerguntaDAO();
	private Pergunta pergunta = null;

	@FXML
	public void initialize() {
		tbl.setItems(FXCollections.observableArrayList(dao.listaTodas()));
		configuraTabela();
	}

	private void configuraTabela() {
		colEnunciado.setCellValueFactory(cellData -> cellData.getValue().ColunaEnunciadoProperty());
		colResposta.setCellValueFactory(cellData -> cellData.getValue().ColunaRespostaProperty());
	}

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

	@FXML
	public void TelaCadastroQuestao() {
		try {
			Stage Principal = new Stage();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("TelaCadastroQuestao.fxml"));
			Parent root = loader.load();
			Principal.setScene(new Scene(root));
			Principal.initModality(Modality.WINDOW_MODAL);
			Principal.initStyle(StageStyle.UNDECORATED);
			Principal.setResizable(false);
			Principal.show();
			Stage stage = (Stage) pane.getScene().getWindow();
			stage.close();
		} catch (Exception e) {

		}
	}

	@FXML
	public void SelecionaPergunta() {
		pergunta = tbl.getSelectionModel().getSelectedItem();
		if (pergunta != null) {
			Pergunta4tela(pergunta);

		}
	}

	@FXML
	public void alterar() {
		Pergunta p = tela4Pergunta();
		if (pergunta != null) {
			p.setColunaId(pergunta.getColunaId());
			dao.alterar(p);
		}
		tbl.setItems(FXCollections.observableArrayList(dao.listaTodas()));
		txtPergunta.setText("");
		txtResposta.setText("");
	}
	@FXML
	public void apagar() {
		Pergunta p = tela4Pergunta();
		if(pergunta != null) {
			p.setColunaId(pergunta.getColunaId());
			dao.deletar(p);
		}
		tbl.setItems(FXCollections.observableArrayList(dao.listaTodas()));
		txtPergunta.setText("");
		txtResposta.setText("");
	}

	private void Pergunta4tela(Pergunta pergunta) {
		txtPergunta.setText(pergunta.getColunaEnunciado());
		txtResposta.setText(pergunta.getColunaResposta());
	}

	private Pergunta tela4Pergunta() { // tela para agencia
		Pergunta p = new Pergunta();
		p.setColunaEnunciado(txtPergunta.getText());
		p.setColunaResposta(txtResposta.getText());
		return p;
	}

	@FXML
	public void filtrar() {
		if (txtFiltro.getText().equals(""))
			tbl.setItems(FXCollections.observableArrayList(dao.listaTodas()));
		else
			tbl.setItems(FXCollections.observableArrayList(dao.filtrar(txtFiltro.getText())));
	}

}
