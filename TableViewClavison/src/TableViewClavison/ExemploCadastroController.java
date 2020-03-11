package TableViewClavison;


import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.StageStyle;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.Aluno;

public class ExemploCadastroController {

	@FXML
	private TextField txtNome;
	@FXML
	private TextField txtIdade;
	@FXML
	private RadioButton rdMasculino;
	@FXML
	private RadioButton rdFeminino;
	@FXML
	private TableView<Aluno> tblAlunos;
	@FXML
	private TableColumn<Aluno, String> colNome;
	@FXML
	private TableColumn<Aluno, Number> colIdade;
	@FXML
	private TableColumn<Aluno, String> colSexo;

	private ArrayList<Aluno> lista = new ArrayList<Aluno>();

	@FXML
	public void initialize() {
		colNome.setCellValueFactory(cellData -> cellData.getValue().nomeProperty());
		colIdade.setCellValueFactory(cellData -> cellData.getValue().idadeProperty());
		colSexo.setCellValueFactory(cellData -> cellData.getValue().sexoProperty());
	}
	
	@FXML
	public void cadastra() {
		if(valida()) {
			Aluno a = new Aluno();
			a.setNome(txtNome.getText());
			a.setIdade(Integer.parseInt(txtIdade.getText()));
			a.setSexo(rdMasculino.isSelected()?"Masculino":"Feminino"); //if ternário
			lista.add(a); // add objeto aluno no array
			limpaTela(); // limpa a tela
			tblAlunos.setItems(FXCollections.observableArrayList(lista)); //
		}
	}

	private void limpaTela() {
		txtNome.setText("");
		txtIdade.setText("");
		rdMasculino.setSelected(false);
		rdFeminino.setSelected(false);
		txtNome.requestFocus();
	}

	private boolean valida() {
		if (txtNome.getText().equals("")) {
			mensagemErroValidacao("NOME");
			return false;
		}
		if (txtIdade.getText().equals("")) {
			mensagemErroValidacao("IDADE");
			return false;
		}
		if (!rdMasculino.isSelected() && !rdFeminino.isSelected()) {
			mensagemErroValidacao("SEXO");
			return false;
		}
		return true;

	}

	private void mensagemErroValidacao(String erro) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setHeaderText("Erro de validação !");
		//alert.setContentText("Erro de validação no campo: " + erro + "\n" + "Preenchimento obrigatório !");
		alert.initStyle(StageStyle.UNDECORATED);
		alert.getDialogPane().setStyle("-fx-border-color: black); -fx-border-widht: 3;");
		alert.show();
	}

}
