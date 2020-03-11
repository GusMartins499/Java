package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.Produto;

public class PrincipalController {
	@FXML
	TextField txtNome;
	@FXML
	TextField txtEstoque;
	@FXML
	TextField txtValor;
	@FXML
	ComboBox<String> CbUn;
	@FXML
	TableView<Produto> tblProdutos;
	@FXML
	TableColumn<Produto, String> colNome;
	@FXML
	TableColumn<Produto, Number> colEstoque;
	@FXML
	TableColumn<Produto, Number> colValor;
	@FXML
	TableColumn<Produto, String> colUnidade;

	@FXML
	public void initialize() {
		preencherComboUnidades();
		configuraTabela();
		File file = new File("produtos.txt");
		if (file.exists()) {
			lerArquivo();
		}
	}

	private void configuraTabela() {
		colNome.setCellValueFactory(cellData -> cellData.getValue().nomeProperty());
		colEstoque.setCellValueFactory(cellData -> cellData.getValue().estoqueProperty());
		colValor.setCellValueFactory(cellData -> cellData.getValue().valorProperty());
		colUnidade.setCellValueFactory(cellData -> cellData.getValue().unidadeProperty());

	}

	private void preencherComboUnidades() {
		String[] unidades = { "CX", "LT", "KG", "UN", "MT" };
		CbUn.setItems(FXCollections.observableArrayList(unidades));

	}

	@FXML
	public void adicionaArquivo() {
		try {
			FileWriter fw = new FileWriter("produtos.txt", true);
			BufferedWriter bf = new BufferedWriter(fw);
			String linha = txtNome.getText() + "," + txtEstoque.getText() + "," + txtValor.getText() + ","
					+ CbUn.getSelectionModel().getSelectedItem() + "\n";
			bf.append(linha);
			bf.close();
			fw.close();
			lerArquivo();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void lerArquivo() {
		ArrayList<Produto> listaProduto = new ArrayList<>();
		try {
			FileReader fr = new FileReader("produtos.txt");
			BufferedReader br = new BufferedReader(fr);
			String linha = "";
			while ((linha = br.readLine()) != null) {
				String[] v = linha.split(",");
				Produto p = new Produto();
				p.setNome(v[0]);
				p.setEstoque(Integer.parseInt(v[1]));
				p.setValor(Double.parseDouble(v[2]));
				p.setUnidade(v[3]);
				listaProduto.add(p);
				tblProdutos.setItems(FXCollections.observableArrayList(listaProduto));
			}
			br.close();
			fr.close();
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

}
