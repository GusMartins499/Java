package principal;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;

public class PrincipalController {

	@FXML
	private void abreTela1() {
		try {
			Stage stageTela1 = new Stage();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Tela1.fxml"));
			Parent root = loader.load();
			stageTela1.setScene(new Scene(root));
			stageTela1.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void abreTela2() {
		try {
			Stage stageTela2 = new Stage();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Tela2.fxml"));
			Parent root = loader.load();
			stageTela2.setScene(new Scene(root));
			stageTela2.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
