package principal;

import model.Atleta;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Tela1Controller {

	@FXML
	TextField txtDistancia1;
	@FXML
	TextField txtDistancia2;
	@FXML
	TextField txtTempo1;
	@FXML
	TextField txtTempo2;
	@FXML
	ProgressBar prog1;
	@FXML
	ProgressBar prog2;

	private Atleta a1;
	private Atleta a2;

	@FXML
	public void iniciarComThread() {
		instanciaObjs();
		new Thread(a1).start();
		new Thread(a2).start();
	}

	private void instanciaObjs() {
		try {
			if (Integer.parseInt(txtDistancia1.getText()) < 0 || (Integer.parseInt(txtDistancia2.getText()) < 0)) {
				throw new NullPointerException();
			}
			a1 = new Atleta(Integer.parseInt(txtDistancia1.getText()), Long.parseLong(txtTempo1.getText()), prog1);
			a2 = new Atleta(Integer.parseInt(txtDistancia2.getText()), Long.parseLong(txtTempo2.getText()), prog2);

		} catch (NumberFormatException e) {
			mensagemErroValidacao("Erro de validação, todos os campos devem ser preenchidos: " + "\n"
					+ "Preenchimento obrigatório !");
		} catch (NullPointerException e) {
			mensagemErroValidacao("Distancia inválida !");
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

	@FXML
	Button btnFechar;

	@FXML
	public void fechaTela() {
		Stage stage = (Stage) btnFechar.getScene().getWindow();
		stage.close();
	}

}
