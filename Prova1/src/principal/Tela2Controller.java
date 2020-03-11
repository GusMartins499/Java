package principal;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.Competicao;

public class Tela2Controller {

	@FXML
	private TextField txtNome;
	@FXML
	private DatePicker data;
	@FXML
	private TextField txtDistancia;
	@FXML
	private TextField txtColocacao;
	@FXML
	private TableView<Competicao> tblCompeticoes;
	@FXML
	private TableColumn<Competicao, String> colNome;
	@FXML
	private TableColumn<Competicao, String> colData;
	@FXML
	private TableColumn<Competicao, Number> colDistancia;
	@FXML
	private TableColumn<Competicao, Number> colcolocacao;
	@FXML
	private TextField aux;
	@FXML
	private TextField melhorColocacao;
	
	private ArrayList<Competicao> lista = new ArrayList<Competicao>();
	
	@FXML
	public void initialize() {
		colNome.setCellValueFactory(cellData -> cellData.getValue().nomeProperty());
		colDistancia.setCellValueFactory(cellData -> cellData.getValue().distanciaProperty());
		colData.setCellValueFactory(cellData -> cellData.getValue().dataProperty());
		colcolocacao.setCellValueFactory(cellData -> cellData.getValue().colocacaoProperty());
	}
	
	@FXML
	public void cadastra() {
		Competicao c = new Competicao();
		c.setNome(txtNome.getText());
		
		c.setDistancia(Integer.parseInt(txtDistancia.getText()));
		c.setColocacao(Integer.parseInt(txtColocacao.getText()));
		lista.add(c);
		limpaTela();
//		for(Competicao colocacao : lista) {
//			//aux = colocacao.get
//			
//		}
		tblCompeticoes.setItems(FXCollections.observableArrayList(lista));
	}
	private void limpaTela() {
		txtNome.setText("");
		txtDistancia.setText("");
		//data.set
		txtDistancia.setText("");
		txtColocacao.setText("");
	}
	
}
