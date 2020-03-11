package principal;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Janela1Controller {

	@FXML
	Button btnFechar;

	@FXML
	public void fechaJanela() {
		Stage stage = (Stage) btnFechar.getScene().getWindow();
		stage.close();
	}
}
