package application;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.swing.JOptionPane;

import dao.LancamentoDAO;
import dao.PlanoContaDAO;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.util.Callback;
import javafx.util.converter.LocalDateStringConverter;
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
	TableView<Lancamento> tblLancamentos;
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
	private ComboBox<String> cbPlanoConta;
	@FXML
	private RadioButton rbPesquisaPorPeriodo;
	@FXML
	private RadioButton rbPesquisaPorEmissao;
	@FXML
	private RadioButton rbListaTudo;
	@FXML
	private DatePicker dataInicial;
	@FXML
	private DatePicker dataFinal;

	private LancamentoDAO lanDao = new LancamentoDAO();
	private PlanoContaDAO planoCDAO = new PlanoContaDAO();

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
		// atualizaTabela();
		if (cbPlanoConta != null) {
			cbPlanoConta.setItems(FXCollections.observableArrayList(planoCDAO.listarTodas()));
		}
		// dataEmissao.setValue(LocalDate.now());
		tblLancamentos.setItems(FXCollections.observableArrayList(lanDao.listarTodas()));

	}

	private String getDateTime() {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		return dateFormat.format(date);
	}

	private void pesquisaPorEmissao() {
		String data = dataEmissao.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		tblLancamentos.setItems(FXCollections.observableArrayList(lanDao.listarTodasPorData(data)));
	}

	private void pesquisaPorPeriodo() {
		String dataInicio = dataInicial.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		System.out.println(dataInicial);
		String dataFim = dataFinal.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		tblLancamentos.setItems(FXCollections.observableArrayList(lanDao.listarTodasPorPeriodo(dataInicio, dataFim)));
		
	}
	
	private void pesquisaTudo() {
		tblLancamentos.setItems(FXCollections.observableArrayList(lanDao.listarTodas()));
	}

	@FXML
	public void pesquisar() {
		if (rbPesquisaPorEmissao.isSelected()) {
			pesquisaPorEmissao();
		} else {
			if (rbPesquisaPorPeriodo.isSelected()) {
				pesquisaPorPeriodo();
			} else {
				pesquisaTudo();
			}
		}

	}

}
