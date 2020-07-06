package application;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class PrincipalController {

	@FXML
	TextArea txtArea;
	@FXML
	Button btn;

	ArrayList<String> teste = new ArrayList<String>();

	@FXML public void addTexto() {
		teste.add(txtArea.getText());
		System.out.println(teste);
	}
}
