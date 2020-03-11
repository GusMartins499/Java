package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.StageStyle;
import model.Produto;

public class PrincipalTesteArquivoController {

	@FXML
	private TextField txtNome;
	@FXML
	private TextField txtEstoque;
	@FXML
	private TextField txtValor;
	@FXML
	private TableView<Produto> tblProduto;
	@FXML
	private TableColumn<Produto, String> colNome;
	@FXML
	private TableColumn<Produto, Number> colEstoque;
	@FXML
	private TableColumn<Produto, Number> colValor;

	private void configuraTabela() {
		colNome.setCellValueFactory(cellData -> cellData.getValue().nomeProperty());
		colEstoque.setCellValueFactory(cellData -> cellData.getValue().estoqueProperty());
		colValor.setCellValueFactory(cellData -> cellData.getValue().valorProperty());
	}

	@FXML
	public void initialize() {
		configuraTabela();
		File file = new File("C:\\Users\\Gustavo\\Desktop\\produtos.txt");
		if (file.exists()) {
			leArquivo();
		}
	}

	@FXML
	public void CriaArquivo() {
		String linha;
		//File f = new File("D:\\Users\\Gustavo\\Desktop\\produtos.txt");
		//File f = new File("C:\\Users\\Gustavo\\Desktop\\produtos.txt");
		try {
			if (txtNome.getText().equalsIgnoreCase("") || txtEstoque.getText().equalsIgnoreCase("")
					|| txtValor.getText().equalsIgnoreCase("")) {
				throw new ArrayIndexOutOfBoundsException();
			}
			if (Integer.parseInt(txtEstoque.getText()) < 0 || (Double.parseDouble(txtValor.getText()) < 0)) {
				throw new NullPointerException();
			}
			//if (f.exists()) {
				//FileWriter fw = new FileWriter("D:\\Users\\Gustavo\\Desktop\\produtos.txt", true);
				FileWriter fw = new FileWriter("C:\\Users\\Gustavo\\Desktop\\produtos.txt", true);
				BufferedWriter bw = new BufferedWriter(fw);
				linha = txtNome.getText() + "," + txtEstoque.getText() + "," + txtValor.getText() + "\n";
				bw.append(linha);
				bw.close();
				fw.close();
				leArquivo();
			//}
			//throw new FileNotFoundException();
		} catch (ArrayIndexOutOfBoundsException e) {
			mensagemErroValidacao("Erro de validação, todos os campos devem ser preenchidos: " + "\n"
					+ "Preenchimento obrigatório !");
		} catch (NullPointerException e) {
			mensagemErroValidacao("Estoque do produto ou valor menor que zero !" + "\n" + "Valor ou estoque negativos !");
//		} catch (FileNotFoundException e) {
//			mensagemErroValidacao("Arquivo não encontrado !" + "\n" + "Caminho não encontrado");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void leArquivo() {
		ArrayList<Produto> lista = new ArrayList<>();
		try {
			FileReader filereader = new FileReader("C:\\Users\\Gustavo\\Desktop\\produtos.txt");
			BufferedReader bufferedreader = new BufferedReader(filereader);
			String linha = "";
			while ((linha = bufferedreader.readLine()) != null) {
				String[] v = linha.split(",");
				Produto p = new Produto();
				p.setNome(v[0]);
				p.setEstoque(Integer.parseInt(v[1]));
				p.setValor(Double.parseDouble(v[2]));
				lista.add(p);
				tblProduto.setItems(FXCollections.observableArrayList(lista));
			}
			bufferedreader.close();
			filereader.close();

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	private void mensagemErroValidacao(String erro) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setHeaderText("Erro de validação !");
		alert.setContentText(erro);
		alert.initStyle(StageStyle.UNDECORATED);
		alert.getDialogPane().setStyle("-fx-border-color: black); -fx-border-widht: 3;");
		alert.show();
	}

}
