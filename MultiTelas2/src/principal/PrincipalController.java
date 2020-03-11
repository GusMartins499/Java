package principal;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
public class PrincipalController {

	@FXML
	ImageView img;

	@FXML
	TextField txtNome;

	@FXML
	public void abreTela1() {
		try {
			Stage stageJanela1 = new Stage();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Janela2.fxml"));
			Parent root = loader.load();
			stageJanela1.setScene(new Scene(root));
			stageJanela1.initOwner(img.getScene().getWindow());
			stageJanela1.show();

			Janela2Controller controller = loader.getController();
			controller.trocaNome(txtNome.getText());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void abreJanela2() {
		try {
			Stage stageJanela2 = new Stage();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Janela2.fxml"));
			Parent root = loader.load();
			stageJanela2.setScene(new Scene(root));
			stageJanela2.initOwner(img.getScene().getWindow());
			stageJanela2.show();

			Janela2Controller controller = loader.getController();
			controller.trocaNome(txtNome.getText());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
