package exemplo_javafx;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class ExemploController {

	@FXML
	TextField txtNr1;
	@FXML
	TextField txtNr2;
	@FXML
	TextField txtResultado;

	@FXML
	public void somar() {
		int n1 = Integer.parseInt(txtNr1.getText());
		int n2 = Integer.parseInt(txtNr2.getText());

		txtResultado.setText((n1 + n2) + "");

	}

	@FXML
	public void subtracao() {
		int n1 = Integer.parseInt(txtNr1.getText());
		int n2 = Integer.parseInt(txtNr2.getText());

		txtResultado.setText((n1 - n2) + "");

	}

	@FXML
	public void multiplicacao() {
		int n1 = Integer.parseInt(txtNr1.getText());
		int n2 = Integer.parseInt(txtNr2.getText());

		txtResultado.setText((n1 * n2) + "");

	}

	@FXML
	public void divisao() {
		int n1 = Integer.parseInt(txtNr1.getText());
		int n2 = Integer.parseInt(txtNr2.getText());

		txtResultado.setText((n1 / n2) + "");

	}

}
