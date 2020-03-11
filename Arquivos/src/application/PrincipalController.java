package application;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import model.Arquivo;

public class PrincipalController {
	@FXML
	TextField txtpath;
	@FXML
	TableView<Arquivo> tblArquivos;
	@FXML
	TableColumn<Arquivo, String> colPath;
	@FXML
	TableColumn<Arquivo, String> colData;
	@FXML
	TableColumn<Arquivo, String> colTamanho;

	private ArrayList<Arquivo> lista = new ArrayList<Arquivo>();
	private File[] files;
	private File diretorio;

	@FXML
	public void initialize() {
		colPath.setCellValueFactory(cellData -> cellData.getValue().nomeProperty());
		colData.setCellValueFactory(cellData -> cellData.getValue().dataProperty());
		colTamanho.setCellValueFactory(cellData -> cellData.getValue().tamanhoProperty());
	}

	@FXML
	public void abreDiretorio() {
		DirectoryChooser directoryChooser = new DirectoryChooser();
		directoryChooser.setTitle("Seleciona Diretório");
		File file = directoryChooser.showDialog(null);
		if (file != null) {
			txtpath.setText(file.getPath());

			diretorio = new File(txtpath.getText());
			if (diretorio.exists() && diretorio.isDirectory()) {
				files = diretorio.listFiles();
				compoeTabela(files);

			}
		}
	}

	private void compoeTabela(File[] files) {
		lista.clear();
		DateFormat ds = new SimpleDateFormat("dd/MM/yyyy");
		for (File f : files) {
			Arquivo a = new Arquivo();
			a.setNome(f.getName());
			a.setTamanho(f.length() + "");
			a.setData(ds.format(f.lastModified()));
			lista.add(a);
		}
		tblArquivos.setItems(FXCollections.observableArrayList(lista));
	}

	@FXML
	public void apagueSelecionado() {
		int indice = tblArquivos.getSelectionModel().getSelectedIndex();
		files[indice].delete();
		files = diretorio.listFiles();
		compoeTabela(files);
	}

	@FXML
	public void apagaTodos() {
		for (File file : files) {
			file.delete();
		}
		files = new File[0];
		compoeTabela(files);
	}
}
