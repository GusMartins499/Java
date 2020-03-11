package application;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Produto;

public class PrincipalController {

	@FXML
	private TextField txtPath;
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
		colSubTot.setCellValueFactory(celldata -> celldata.getValue().subTotProperty());
		colValor.setCellValueFactory(celldata -> celldata.getValue().valorProperty());
		colDesc.setCellValueFactory(celldata -> celldata.getValue().descricaoProperty());
	}

	@FXML
	public void cadastra() {
		Produto p = new Produto();
		double total = 0;
		DecimalFormat df = new DecimalFormat("##.##");
		p.setDescricao(txtDescricao.getText());
		p.setUrlImg(txtPath.getText());
		p.setQtd(Integer.parseInt(txtQtd.getText()));
		p.setValor(Double.parseDouble(txtValor.getText()));
		p.setSubTot(p.getValor() * p.getQtd());
		lista.add(p);
		limpaTela();
		tblProdutos.setItems(FXCollections.observableArrayList(lista));
		for (Produto produto : lista) {
			total += produto.getSubTot();
		}
		
		txtTot.setText(df.format(total));
	}

	private void limpaTela() {
		txtDescricao.setText("");
		txtQtd.setText("");
		txtValor.setText("");
	}

	@FXML
	public void abreImagem() {
		File f = selecionaImagem();
		if (f != null) {
			txtPath.setText(f.toURI().toString());
		}
	}

	private File selecionaImagem() {
		FileChooser dialogo = new FileChooser();
		dialogo.getExtensionFilters().add(new FileChooser.ExtensionFilter("Imagens", "*.jpg", "*.JPG", "*.png", "*.PNG",
				"*.gif", "*.GIF", "*.bmp", "*.BMP"));
		dialogo.setInitialDirectory(new File("C:\\Users\\Gustavo\\Pictures"));
		return dialogo.showOpenDialog(null);
	}

	@FXML
	public void abreTela2() {
		try {
			Stage stageJanela2 = new Stage();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Tela2.fxml"));
			Parent root = loader.load();
			stageJanela2.setScene(new Scene(root));
			stageJanela2.show();
			Tela2Controller controller = loader.getController();
			Produto p = tblProdutos.getSelectionModel().getSelectedItem();
			controller.recebeProduto(p);
			

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
