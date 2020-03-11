package application;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import dao.QuestaoDAO;
import domain.Questao;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class TelaMath1Controller {

	@FXML
	private BorderPane pane;
	@FXML
	private TextArea txtEnunciado;
	@FXML
	private RadioButton rbResp1;
	@FXML
	private RadioButton rbResp2;
	@FXML
	private RadioButton rbResp3;
	@FXML
	private RadioButton rbResp4;
	@FXML
	private Button btnResponder;
	@FXML
	private Button btnSair;

	private QuestaoDAO questaoDAO = new QuestaoDAO();
	private ArrayList<Questao> q = questaoDAO.ListaMath();
	int vresp1 = 0, vresp2 = 0, vresp3 = 0, vresp4 = 0;

	@FXML
	public void initialize() {
		// enviaId();
		MostraQuestao();
	}

	// private int idPerguntaMath() {
	// Random gerador = new Random();
	// for (int i = 0; i < questaoDAO.listaMath().size(); i++) {
	// gerador.nextInt(questaoDAO.listaMath().size());
	// }
	// return gerador.nextInt();
	// }
	// private void enviaId() {
	// QuestaoDAO qDAO = new QuestaoDAO();
	// qDAO.ListaIdMath(idPerguntaMath());
	//
	// }
	@FXML
	private void MostraQuestao() {
		int id = idAleatorio();

		int vetor[] = new int[4];
		for (int i = 0; i <= 3; i++) {
			int rnd = (int) (Math.random() * 4);
			vetor[i] = rnd;
			System.out.println(vetor[i]);

		}
		String vetorResp[] = new String[4];

		txtEnunciado.setText(q.get(id).getEnunciado());

		vetorResp[vetor[0]] = q.get(id).getResp1();
		vetorResp[vetor[1]] = q.get(id).getResp2();
		vetorResp[vetor[2]] = q.get(id).getResp3();
		vetorResp[vetor[3]] = q.get(id).getResp4();

		rbResp1.setText(vetorResp[0]);
		if (rbResp1.getText().equals(q.get(id).getResp1()))
			vresp1 = 1;
		rbResp2.setText(vetorResp[1]);
		if (rbResp2.getText().equals(q.get(id).getResp1()))
			vresp2 = 1;
		rbResp3.setText(vetorResp[2]);
		if (rbResp3.getText().equals(q.get(id).getResp1()))
			vresp3 = 1;
		rbResp4.setText(vetorResp[3]);
		if (rbResp4.getText().equals(q.get(id).getResp1()))
			vresp4 = 1;
	}
	
	@FXML
	private void verificar() {
		if (rbResp1.isSelected()) {
			if (vresp1 == 1) {
				// soma ponto
			}
		}
		if (rbResp2.isSelected()) {
			if (vresp2 == 1) {
				// soma ponto
			}
		}
		if (rbResp3.isSelected()) {
			if (vresp3 == 1) {
				// soma ponto
			}
		}
		if (rbResp4.isSelected()) {
			if (vresp4 == 1) {
				// soma ponto
			}
		}
		MostraQuestao();

	}

	private int idAleatorio() {
		int id = (int) (Math.random() * q.size());
		return id;
	}

	// private void embaralhaResposta() {
	// for (int i = 0; i < 4; i++) {
	// int id = (int) (Math.random() * 4);
	//
	// }

	@FXML
	public void fechaJanela() {
		Stage stage = (Stage) pane.getScene().getWindow();
		stage.close();
		try {
			Stage TelaConfiguracaoQuestao = new Stage();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("TelaJogo.fxml"));
			Parent root = loader.load();
			TelaConfiguracaoQuestao.setScene(new Scene(root));
			TelaConfiguracaoQuestao.initModality(Modality.WINDOW_MODAL);
			TelaConfiguracaoQuestao.initStyle(StageStyle.UNDECORATED);
			TelaConfiguracaoQuestao.setResizable(false);
			TelaConfiguracaoQuestao.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
