package application;

import dao.LancamentoDAO;
import dao.PlanoContaDAO;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import javafx.stage.Stage;
import model.Lancamento;
import model.PlanoConta;

public class CadLancamentoController {

	@FXML
	private BorderPane pane;
	@FXML
	private TextField txtHistorico;
	@FXML
	private TextField txtReferencia;
	@FXML
	private TextField txtValor;
	@FXML
	private TextArea txtobservacoes;
	@FXML
	private Button btnVoltar;
	@FXML
	private Button btnGravar;
	@FXML
	private DatePicker dataEmissao;
	@FXML
	private ComboBox<PlanoConta> cbPlanoConta;

	private LancamentoDAO lacamentoDAO = new LancamentoDAO();
	//private Lancamento lancamento = null;

	private PlanoContaDAO planoCDAO = new PlanoContaDAO();

	@FXML
	public void initialize() {
		if (cbPlanoConta != null) {
			cbPlanoConta.setItems(FXCollections.observableArrayList(planoCDAO.listarTodas()));
		}
	}

	@FXML
	private void voltarParaPrincipal() {
		Stage stage = (Stage) pane.getScene().getWindow();
		stage.close();
		// try {
		// Stage Principal = new Stage();
		// FXMLLoader loader = new FXMLLoader(getClass().getResource("Principal.fxml"));
		// Parent root = loader.load();
		// Principal.setScene(new Scene(root));
		// Principal.setResizable(true);
		// Principal.show();
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
	}

//	private void Lancamento4tela(Lancamento lancamento) {
//		txtHistorico.setText(lancamento.getHistorico());
//		txtobservacoes.setText(lancamento.getObs());
//		txtReferencia.setText(lancamento.getReferencia());
//		txtValor.setText(lancamento.getValor()+"");
//		
//		
//	}
	
	private Lancamento tela4Lancamento() {
		Lancamento l = new Lancamento();
		l.setReferencia(txtReferencia.getText());
		l.setHistorico(txtHistorico.getText());
		l.setObs(txtobservacoes.getText());
		l.setValor(Double.parseDouble(txtValor.getText()));
		l.setEmissao(dataEmissao.getDayCellFactory().toString());
		l.setPlanoConta(cbPlanoConta.getSelectionModel().getSelectedItem().toString());
		return l;
	}

	@FXML
	public void inserir() {
		Lancamento l = tela4Lancamento();
		lacamentoDAO.inserir(l);
	}
}

// GRAVAR LANCAMENTO - DAO E ETC... TUDO É OBRIGATÓRIO MENOS O CAMPO OBSERVAÇÕES
