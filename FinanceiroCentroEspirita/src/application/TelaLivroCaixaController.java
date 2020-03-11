package application;

import dao.PlanoContaDAO;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.Lancamento;
import model.PlanoConta;

public class TelaLivroCaixaController {

	@FXML
	private TextField txtHistorico;
	@FXML
	private Button btnLancamento;
	@FXML
	private Button btnPesquisar;
	@FXML
	private Label lblFaturado;
	@FXML
	private Label lblEstornado;
	@FXML
	private Label lblReceber;
	@FXML
	private DatePicker dataEmissao;
	@FXML
	private TableView<Lancamento> tblLancamentos;
	@FXML
	private TableColumn<Lancamento, String> colPlanoConta;
	@FXML
	private TableColumn<Lancamento, String> colReferencia;
	@FXML
	private TableColumn<Lancamento, String> colEmissao;
	@FXML
	private TableColumn<Lancamento, String> colHistorico;
	@FXML
	private TableColumn<Lancamento, Number> colValor;
	@FXML
	private TableColumn<Lancamento, String> colObs;
	@FXML
	private ComboBox<PlanoConta> cbPlanoConta;

	private PlanoContaDAO dao = new PlanoContaDAO();

	private void configuraTabela() {
		colPlanoConta.setCellValueFactory(cellData -> cellData.getValue().planoContaProperty());
		colReferencia.setCellValueFactory(cellData -> cellData.getValue().referenciaProperty());
		colEmissao.setCellValueFactory(cellData -> cellData.getValue().emissaoProperty());
		colHistorico.setCellValueFactory(cellData -> cellData.getValue().historicoProperty());
		colValor.setCellValueFactory(cellData -> cellData.getValue().valorProperty());
		colObs.setCellValueFactory(cellData -> cellData.getValue().obsProperty());

	}

	@FXML
	public void initialize() {
		configuraTabela();
		if (cbPlanoConta != null) {
			cbPlanoConta.setItems(FXCollections.observableArrayList(dao.listarTodas()));
		}
	}

}
