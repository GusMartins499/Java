package application;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import java.util.Properties;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

public class PrincipalArquivo3Controller {

	@FXML
	private AnchorPane pane;
	String txtPath;
	@FXML
	private TextField txtAltura;
	@FXML
	private TextField txtLargura;
	@FXML
	private ImageView img;
	@FXML
	private TextField caminhoImg;

	@FXML
	public void initialize() {
		File file = new File("conf.properties");
		if (file.exists()) {
			lerArquivo();
		}
	}

	@FXML
	public void abreImagem() {
		File f = selecionaImagem();
		if (f != null) {
			txtPath = f.toURI().toString();
			Image i = new Image(f.toURI().toString());
			img.setImage(i);
			img.setFitWidth(i.getWidth());
			img.setFitHeight(i.getHeight());

		}
	}

	private File selecionaImagem() {
		FileChooser fc = new FileChooser();
		fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Imagens", "*.jpg", "*.JPG", "*.png", "*.PNG",
				"*.gif", "*.GIF", "*.bmp", "*.BMP"));
		fc.setInitialDirectory(new File("C:\\Users\\Gustavo\\Pictures"));
		return fc.showOpenDialog(null);
	}

	@FXML
	public void escreveProperties() {
		Properties properties = new Properties();
		properties.setProperty("Caminho", txtPath);
		properties.setProperty("Altura", txtAltura.getText());
		properties.setProperty("Largura", txtLargura.getText());
		try {
			FileWriter fw = new FileWriter("conf.properties");
			properties.store(fw, "ARQUIVO CONF PROPERTIES");
			fw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void lerArquivo() {
		Properties properties = new Properties();
		try {
			FileReader fis = new FileReader("conf.properties");
			properties.load(fis);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String p1 = properties.getProperty("Caminho");
		String p2 = properties.getProperty("Altura");
		String p3 = properties.getProperty("Largura");
		pane.setPrefSize(Integer.parseInt(p2), Integer.parseInt(p3));
		Image i = new Image(p1);
		img.setImage(i);
		img.setFitWidth(i.getWidth());
		img.setFitHeight(i.getHeight());
		//txtPath = p1;
		caminhoImg.setText(p1);
		txtAltura.setText(p2);
		txtLargura.setText(p3);
	}
}
