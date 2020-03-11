package application;

import dao.CorrentistaDAO;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.Correntista;

public class TelaCorrentistaController {

	@FXML
	TextField txtNome;
	@FXML
	TextField txtNascimento;
	@FXML
	TextField txtFiltro;
	@FXML
	CheckBox ckInativar;
	@FXML
	Button btnAcao;
	@FXML
	TableView<Correntista> tblCorrentista;
	@FXML
	TableColumn<Correntista, String> colNome;
	@FXML
	TableColumn<Correntista, String> colNascimento;

	private CorrentistaDAO correntistaDAO = new CorrentistaDAO();
	private Correntista correntista = null;

	@FXML
	public void selecionaCorrentista() {
		correntista = tblCorrentista.getSelectionModel().getSelectedItem();
		if (correntista != null) {
			correntista4tela(correntista);
			btnAcao.setText("Alterar");
			ckInativar.setDisable(false);
		}
	}

	@FXML
	public void inserir() {
		Correntista c = tela4correntista();
		if (ckInativar.isSelected()) {
			correntistaDAO.inativar(correntista);
		} else {
			if (correntista != null) {
				c.setId(correntista.getId());
				correntistaDAO.alterar(c);
			} else {
				correntistaDAO.inserir(c);
			}
		}
		ckInativar.setSelected(false);
		ckInativar.setDisable(true);
		correntista = null;
		btnAcao.setText("Inserir");
		tblCorrentista.setItems(FXCollections.observableArrayList(correntistaDAO.listarTodas()));
	}

	@FXML
	public void filtrar() {
		if (txtFiltro.getText().equals(""))
			tblCorrentista.setItems(FXCollections.observableArrayList(correntistaDAO.listarTodas()));
		else
			tblCorrentista.setItems(FXCollections.observableArrayList(correntistaDAO.filtrar(txtFiltro.getText())));
	}

	@FXML
	public void initialize() {
		configuraTabela();
		tblCorrentista.setItems(FXCollections.observableArrayList(correntistaDAO.listarTodas()));
	}

	private void configuraTabela() {
		colNome.setCellValueFactory(cellData -> cellData.getValue().nomeProperty());
		colNascimento.setCellValueFactory(cellData -> cellData.getValue().nascimentoProperty());
	}

	private Correntista tela4correntista() {
		Correntista c = new Correntista();
		c.setNome(txtNome.getText());
		c.setNascimento(txtNascimento.getText().substring(0,2)+"/"+txtNascimento.getText().substring(2,4)+"/"+txtNascimento.getText().substring(4,8));
		return c;
	}

	private void correntista4tela(Correntista correntista) {
		txtNome.setText(correntista.getNome());
		txtNascimento.setText(correntista.getNascimento());
	}
}
