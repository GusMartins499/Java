package application;

import domain.Trabalhador;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.StageStyle;

public class PrincipalController {

	@FXML
	TextField txtQt1;
	@FXML
	TextField txtQt2;
	@FXML
	TextField txtTempo1;
	@FXML
	TextField txtTempo2;
	@FXML
	ProgressBar prog1;
	@FXML
	ProgressBar prog2;
	@FXML
	ProgressBar progTot;

	private Trabalhador t1;
	private Trabalhador t2;

	@FXML
	public void inciarComThred() {
		instanciaObjs();
		new Thread(t1).start();
		new Thread(t2).start();
	}

	@FXML
	public void inciarSemThred() {
		instanciaObjs();
		t1.inicia();
		t2.inicia();
	}

	private void instanciaObjs() {
		try {
			if (Integer.parseInt(txtQt1.getText()) < 0 || (Integer.parseInt(txtQt2.getText()) < 0)) {
				throw new NullPointerException();
			}
			t1 = new Trabalhador(Integer.parseInt(txtQt1.getText()), Long.parseLong(txtTempo1.getText()), prog1);
			t2 = new Trabalhador(Integer.parseInt(txtQt2.getText()), Long.parseLong(txtTempo2.getText()), prog2);

		} catch (NumberFormatException e) {
			mensagemErroValidacao("Erro de validação, todos os campos devem ser preenchidos: " + "\n"
					+ "Preenchimento obrigatório !");
		} catch (NullPointerException e) {
			mensagemErroValidacao("Quantidade inválida !");
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
