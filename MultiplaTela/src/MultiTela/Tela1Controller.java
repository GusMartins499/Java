package MultiTela;

import java.io.File;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

public class Tela1Controller {

	@FXML
	private ImageView img;

	@FXML
	public void abreImagem() {
		File f = selecionaImagem();
		if (f != null) {
			Image i = new Image(f.toURI().toString());
			img.setImage(i);
			img.setFitWidth(i.getWidth());
			img.setFitHeight(i.getHeight());
		}
	}

	private File selecionaImagem() {
		FileChooser dialogo = new FileChooser();
		dialogo.getExtensionFilters().add(new FileChooser.ExtensionFilter("Imagens", "*.jpg", "*.JPG", "*.png", "*.PNG",
				"*.gif", "*.GIF", "*.bmp", "*.BMP"));
		dialogo.setInitialDirectory(new File("C:\\Users\\Gustavo\\Pictures"));
		return dialogo.showOpenDialog(null);
	}
}
