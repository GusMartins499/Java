package application;

import java.util.ArrayList;

import dao.PerguntaDAO;
import dao.UsuariosDAO;
import domain.Pergunta;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class TelaRespostaController {

	@FXML
	private AnchorPane pane;
	@FXML
	private TextField txtResposta;
	@FXML
	private Label lblEnunciado;
	@FXML
	private Button btnResponder;
	@FXML
	private Label lblUsuario;
	@FXML
	private Label lblPonto;
	@FXML
	private Label lblTeste;
	
	PerguntaDAO pDAO = new PerguntaDAO();
	UsuariosDAO uDAO = new UsuariosDAO();
	private ArrayList<Pergunta> perguntas = pDAO.listaTodas();

	@FXML
	public void initialize() {
		botaoProximo();
	}

	public void trocaNome(String n) {
		lblUsuario.setText(n);
	}

	public int testeAleatorio() {
		int id2 = idAleatorio();
		System.out.println(id2);
		int[] vetor = new int[pDAO.listaTodas().size()];
		int i = 0;
		for (i = 0; i <= pDAO.listaTodas().size(); i++) {
			if (vetor[i] != i + 1) {
				vetor[i] = i + 1;
				lblEnunciado.setText(perguntas.get(id2).getColunaEnunciado());
				recebeID(id2);
				i = pDAO.listaTodas().size() + 1;

			}

		}
		return id2;

	}

	public int recebeID(int idDaPergunta) {
		int id = idDaPergunta;
		return id;
	}

	int contadorPonto = 0;

	public void verificaResposta() {
		for (int i = 0; i < pDAO.listaTodas().size(); i++) {
			if (txtResposta.getText().equals(pDAO.listaTodas().get(i).getColunaResposta())) {
				contadorPonto = contadorPonto + 1;
				uDAO.inserirPontuacao(lblUsuario.getText());
				txtResposta.setText("");
				lblPonto.setText(contadorPonto * 10 + "");
				i = pDAO.listaTodas().size();
			}
		}
	}

	int n = 0;

	@FXML
	public void botaoProximo() {
			if (n < 10) {
				testeAleatorio();
				verificaResposta();
				n++;
			} else {
				n = 0;
				Stage stage = (Stage) pane.getScene().getWindow();
				stage.close();
				try {
					Stage Principal = new Stage();
					FXMLLoader loader = new FXMLLoader(getClass().getResource("PontoFinal.fxml"));
					Parent root = loader.load();
					Principal.setScene(new Scene(root));
					Principal.initModality(Modality.WINDOW_MODAL);
					Principal.initStyle(StageStyle.UNDECORATED);
					Principal.setResizable(false);
					Principal.show();
					PontoFinalController controller = loader.getController();
					controller.trocaNome(lblUsuario.getText());
					controller.exibePonto(contadorPonto * 10);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	

	private int idAleatorio() {
		int id = (int) (Math.random() * pDAO.listaTodas().size());
		return id;
	}

	@FXML
	public void SairdoJogo() {
		Stage stage = (Stage) pane.getScene().getWindow();
		stage.close();
	}

}
