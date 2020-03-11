package application;

import dao.MateriasDAO;
import dao.QuestaoDAO;
import domain.Materias;
import domain.Questao;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


public class TelaCadastroQuestaoController {

	@FXML
	private TextArea txtEnunciado;
	@FXML
	private TextField txtQ1;
	@FXML
	private TextField txtQ2;
	@FXML
	private TextField txtQ3;
	@FXML
	private TextField txtQ4;
	@FXML
	private RadioButton rbA;
	@FXML
	private RadioButton rbB;
	@FXML
	private RadioButton rbC;
	@FXML
	private RadioButton rbD;
	@FXML
	private Button btnGravar;
	@FXML
	private Button btnGerar;
	@FXML
	private ComboBox<Materias> cbMaterias;

	private MateriasDAO materiasDAO = new MateriasDAO();
	private QuestaoDAO questaoDAO = new QuestaoDAO();

	@FXML
	public void initialize() {
		if (materiasDAO != null)
			cbMaterias.setItems(FXCollections.observableArrayList(materiasDAO.listarTodas()));
	}

	@FXML
	public void CriaAlternativas() {
		rbA.setText("A) " + txtQ1.getText());
		rbB.setText("B) " + txtQ2.getText());
		rbC.setText("C) " + txtQ3.getText());
		rbD.setText("D) " + txtQ4.getText());
		btnGravar.setDisable(false);

	}

	public void limpaTela() {
		txtQ1.setText("");
		txtQ2.setText("");
		txtQ3.setText("");
		txtQ4.setText("");
		rbA.setSelected(false);
		rbB.setSelected(false);
		rbC.setSelected(false);
		rbD.setSelected(false);
		btnGravar.setDisable(false);
	}

	private Questao tela4Questao() {
		Questao q = new Questao();
		q.setMateria(cbMaterias.getSelectionModel().getSelectedItem());
		q.setEnunciado(txtEnunciado.getText());
		q.setResp1(rbA.getText());
		q.setResp2(rbB.getText());
		q.setResp3(rbC.getText());
		q.setResp4(rbD.getText());
		return q;
	}

	@FXML
	public void cadastrar() {
		Questao q = tela4Questao();
		questaoDAO.inserir(q);

	}

}
