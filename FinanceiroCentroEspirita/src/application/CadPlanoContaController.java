package application;

import dao.PlanoContaDAO;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import model.PlanoConta;

public class CadPlanoContaController {

	@FXML
	private BorderPane pane;
	@FXML
	private Button btnVoltar;
	@FXML
	private Button btnGravar;
	@FXML
	private TextField txtConta;
	@FXML
	private RadioButton rbRecebimento;
	@FXML
	private RadioButton rbPagamento;
	
	// txtCodigo ser� gerado automaticamente pelo banco

	private PlanoContaDAO planoCDAO = new PlanoContaDAO();

	@FXML
	public void initialize() {
		
	}

	@FXML
	private void voltarParaPrincipal() {
		Stage stage = (Stage) pane.getScene().getWindow();
		stage.close();

	}

	private PlanoConta tela4PlanoConta() {
		PlanoConta planoC = new PlanoConta();
		planoC.setConta(txtConta.getText());
		if (rbPagamento.isSelected()) {
			planoC.setTipoConta("Pagamento");
		} else {
			if (rbRecebimento.isSelected()) {
				planoC.setTipoConta("Recebimento");
			}
		}
		return planoC;
	}

	@FXML
	public void inserir() {
		PlanoConta planoC = tela4PlanoConta();
		planoCDAO.inserir(planoC);
	}
}