package application;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.Produto;

public class SampleController {
	
	@FXML
	private TextField txtDescricao;
	@FXML
	private TextField txtQtd;
	@FXML
	private TextField txtValor;
	@FXML
	private TextField txtSubTot;
	@FXML
	private TextField txtTot;
	@FXML
	private TableView<Produto> tblProdutos;
	@FXML
	private TableColumn<Produto, String> colDesc;
	@FXML
	private TableColumn<Produto, Number> colQtd;
	@FXML
	private TableColumn<Produto, Number> colValor;
	@FXML
	private TableColumn<Produto, Number> colSubTot;

	@FXML
	private ArrayList<Produto> lista = new ArrayList<Produto>();

	@FXML
	public void initialize() {
		colQtd.setCellValueFactory(celldata -> celldata.getValue().qtdProperty());
		colSubTot.setCellValueFactory(celldata -> celldata.getValue().subtotProperty());
		colValor.setCellValueFactory(celldata -> celldata.getValue().valorProperty());
		colDesc.setCellValueFactory(celldata -> celldata.getValue().descricaoProperty());
	}

	@FXML
	public void cadastra() {
		Produto p = new Produto();
		double total = 0;
		p.setDescricao(txtDescricao.getText());
		p.setQtd(Integer.parseInt(txtQtd.getText()));
		p.setValor(Double.parseDouble(txtValor.getText()));
		p.setSubTot(p.getValor() * p.getQtd());
		lista.add(p);
		limpaTela();
		tblProdutos.setItems(FXCollections.observableArrayList(lista));
		for (Produto produto : lista) {
			total += produto.getsubTot();
		}
		txtTot.setText(total + "");
	}

	private void limpaTela() {
		txtDescricao.setText("");
		txtQtd.setText("");
		txtValor.setText("");
	}

}

}
