package application;

import dao.AgenciaDAO;
import dao.ContaDAO;
import dao.CorrentistaDAO;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.Agencia;
import model.Conta;
import model.Correntista;

public class TelaContaController {

	@FXML
	private ComboBox<Agencia> cbAgencia;
	@FXML
	private ComboBox<Correntista> cbConrrentista;
	@FXML
	private CheckBox ckInativar;
	@FXML
	private TextField txtSaldo;
	@FXML
	private TextField txtFiltro;
	@FXML
	private TableView<Conta> tblContas;
	@FXML
	private TableColumn<Conta, String> colCorrentista;
	@FXML
	private TableColumn<Conta, String> colAgencia;
	@FXML
	private TableColumn<Conta, Number> colSaldo;
	@FXML
	private Button btnAcao;

	private ContaDAO contaDAO = new ContaDAO();
	private Conta conta = null;

	@FXML
	public void initialize() {
		configuraTabela();
		preencheAgencia();
		preencheCorrentista();
		tblContas.setItems(FXCollections.observableArrayList(contaDAO.listarTodas()));
	}

	private void configuraTabela() {
		colCorrentista.setCellValueFactory(cellData -> cellData.getValue().getCorrentista().nomeProperty());
		// colAgencia.setCellValueFactory(cellData ->
		// cellData.getValue().getAgencia().cidadeProperty());
		colAgencia.setCellValueFactory(cellData -> cellData.getValue().getAgencia().strProperty());
		colSaldo.setCellValueFactory(cellData -> cellData.getValue().saldoProperty());
	}

	private void preencheAgencia() {
		AgenciaDAO agDao = new AgenciaDAO();
		cbAgencia.setItems(FXCollections.observableArrayList(agDao.listarTodas()));
	}

	private void preencheCorrentista() {
		CorrentistaDAO corDao = new CorrentistaDAO();
		cbConrrentista.setItems(FXCollections.observableArrayList(corDao.listarTodas()));
	}

	private Conta tela4Conta() {
		Conta c = new Conta();
		c.setAgencia(cbAgencia.getSelectionModel().getSelectedItem());
		c.setCorrentista(cbConrrentista.getSelectionModel().getSelectedItem());
		return c;
	}

	private void conta4Tela(Conta c) {
		localizaAgencia(c.getAgencia());
		localizaCorrentista(c.getCorrentista());
		txtSaldo.setText("R$ " + c.getSaldo());
	}

	private void localizaAgencia(Agencia agConta) {
		int indice = 0;
		int cont = 0;
		for (Agencia a : cbAgencia.getItems()) {
			if (agConta.getId() == a.getId())
				indice = cont;
			cont++;
		}
		cbAgencia.getSelectionModel().select(indice);
	}

	private void localizaCorrentista(Correntista corConta) {
		int indice = 0;
		int cont = 0;
		for (Correntista cor : cbConrrentista.getItems()) {
			if (corConta.getId() == cor.getId())
				indice = cont;
			cont++;
		}
		cbConrrentista.getSelectionModel().select(indice);
	}

	@FXML
	public void inserir() {
		Conta c = tela4Conta();
		if (ckInativar.isSelected()) {
			contaDAO.inativar(conta);
		} else {
			if (conta != null) {
				c.setId(conta.getId());
				contaDAO.alterar(c);
			} else {
				contaDAO.inserir(c);
			}
		}
		ckInativar.setSelected(false);
		ckInativar.setDisable(true);
		conta = null;
		btnAcao.setText("Inserir");
		tblContas.setItems(FXCollections.observableArrayList(contaDAO.listarTodas()));
	}

	@FXML
	public void selecionaConta() {
		conta = tblContas.getSelectionModel().getSelectedItem();
		if (conta != null) {
			conta4Tela(conta);
			btnAcao.setText("Alterar");
			ckInativar.setDisable(false);
		}
	}

	@FXML
	public void filtrar() {
		if (txtFiltro.getText().equals(""))
			tblContas.setItems(FXCollections.observableArrayList(contaDAO.listarTodas()));
		else
			tblContas.setItems(FXCollections.observableArrayList(contaDAO.filtraPorCorrentista(txtFiltro.getText())));
	}
}
