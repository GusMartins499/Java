package application;

import dao.AgenciaDAO;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.Agencia;

public class TelaAgenciaController {

	@FXML
	TextField txtNumero;
	@FXML
	TextField txtCidade;
	@FXML
	CheckBox ckInativar;
	@FXML
	TextField txtFiltro;
	@FXML
	Button btAcao;
	@FXML
	TableView<Agencia> tblAgencias;
	@FXML
	TableColumn<Agencia, String> colNumero;
	@FXML
	TableColumn<Agencia, String> colCidade;

	private AgenciaDAO dao = new AgenciaDAO();
	private Agencia agencia = null;

	@FXML
	public void initialize() {
		tblAgencias.setItems(FXCollections.observableArrayList(dao.listarTodas()));
		configuraTabela();
	}

	@FXML
	public void selecionaAgencia() {
		agencia = tblAgencias.getSelectionModel().getSelectedItem();
		if (agencia != null) {
			Agencia4tela(agencia);
			btAcao.setText("Alterar");
			ckInativar.setDisable(false);
		}
	}

	private void configuraTabela() {
		colCidade.setCellValueFactory(cellData -> cellData.getValue().cidadeProperty());
		colNumero.setCellValueFactory(cellData -> cellData.getValue().numeroProperty());
	}

	private Agencia tela4agencia() { // tela para agencia
		Agencia a = new Agencia();
		a.setNumero(txtNumero.getText());
		a.setCidade(txtCidade.getText());
		return a;
	}

	private void Agencia4tela(Agencia agencia) { // pega uma agencia e joga pra tela
		txtNumero.setText(agencia.getNumero());
		txtCidade.setText(agencia.getCidade());
	}

	@FXML
	public void inserir() {
		Agencia a = tela4agencia();
		if (ckInativar.isSelected()) {
			dao.inativar(agencia);
		} else {
			if (agencia != null) {
				a.setId(agencia.getId());
				dao.alterar(a);
			} else {
				dao.inserir(a);
			}
		}
		ckInativar.setSelected(false);
		ckInativar.setDisable(true);
		agencia = null;
		btAcao.setText("Inserir");
		tblAgencias.setItems(FXCollections.observableArrayList(dao.listarTodas()));
	}

	@FXML
	public void filtrar() {
		if (txtFiltro.getText().equals(""))
			tblAgencias.setItems(FXCollections.observableArrayList(dao.listarTodas()));
		else
			tblAgencias.setItems(FXCollections.observableArrayList(dao.filtrar(txtFiltro.getText())));
	}
}