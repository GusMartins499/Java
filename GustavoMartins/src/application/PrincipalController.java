package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Properties;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.StageStyle;
import model.Aluno;

public class PrincipalController {

	@FXML
	private TextField txtNome;
	@FXML
	private TextField txtNota1;
	@FXML
	private TextField txtNota2;
	@FXML
	private TextField txtNota3;
	@FXML
	private TextField txtDiciplina;
	@FXML
	private TableView<Aluno> tblAluno;
	@FXML
	private TableColumn<Aluno, String> colNome;
	@FXML
	private TableColumn<Aluno, Number> colMedia;
	@FXML
	private TableColumn<Aluno, String> colDiciplina;
	@FXML
	private TableColumn<Aluno, String> colSituacao;
	@FXML
	private Label lblNomeInstituicao;
	@FXML
	private Label lblMediaA;
	@FXML
	private Label lblMediaR;
	String nomeInstituicao;
	Double mediaA = 7.0, mediaR = 4.0;

	private void configuraTabela() {
		colNome.setCellValueFactory(cellData -> cellData.getValue().nomeProperty());
		colMedia.setCellValueFactory(cellData -> cellData.getValue().mediaProperty());
		colDiciplina.setCellValueFactory(cellData -> cellData.getValue().diciplinaProperty());
		colSituacao.setCellValueFactory(cellData -> cellData.getValue().situacaoProperty());
	}

	public void escreveProperties() {
		Properties properties = new Properties();
		properties.setProperty("NomeInstituicao", "Instituição Modelo");
		properties.setProperty("MediaA", mediaA.toString());
		properties.setProperty("MediaR", mediaR.toString());

		try {
			FileWriter fw = new FileWriter("conf.properties");
			properties.store(fw, "ARQUIVO CONF PROPERTIES");
			fw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void lerArquivoProperties() {
		Properties properties = new Properties();
		try {
			FileReader fis = new FileReader("conf.properties");
			properties.load(fis);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String p1 = properties.getProperty("NomeInstituicao");
		String p2 = properties.getProperty("MediaA");
		String p3 = properties.getProperty("MediaR");
		lblNomeInstituicao.setText(p1);
		lblMediaA.setText(p2);
		lblMediaR.setText(p3);
	}

	@FXML
	public void initialize() {
		configuraTabela();
		File fileDados = new File("dados.txt");
		escreveProperties();
		lerArquivoProperties();
		if (fileDados.exists()) {
			lerArquivo();
		}
	}

	@FXML
	public void inserir() {
		String linha;
		try {
			if (txtNome.getText().equals("") || txtDiciplina.getText().equals("") || txtNota1.getText().equals("")
					|| txtNota2.getText().equals("") || txtNota3.getText().equals("")) {
				
				throw new NumberFormatException();
			}
			if (Double.parseDouble(txtNota1.getText()) < 0 || (Double.parseDouble(txtNota2.getText()) < 0)
					|| (Double.parseDouble(txtNota3.getText()) < 0)) {
				throw new NullPointerException();
			}
//			if (txtNome.getText().contains(",") || txtDiciplina.getText().contains(",")
//					|| txtNota1.getText().contains("") || txtNota2.getText().contains(",")
//					|| txtNota3.getText().contains(",")) {
//					throw new NullPointerException();
//			}

			FileWriter fw = new FileWriter("dados.txt", true);
			BufferedWriter bw = new BufferedWriter(fw);
			linha = txtNome.getText() + "," + txtDiciplina.getText() + "," + txtNota1.getText() + ","
					+ txtNota2.getText() + "," + txtNota3.getText() + "\n";
			bw.append(linha);
			bw.close();
			fw.close();
			lerArquivo();
		} catch (NullPointerException e) {
			mensagemErroValidacao("Notas não podem ser menor que zero !" + "\n" + "Nota com valores negativos !");
		} catch (NumberFormatException e) {
			mensagemErroValidacao("Erro de validação, todos os campos devem ser preenchidos: " + "\n"
					+ "Preenchimento obrigatório !");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void lerArquivo() {
		ArrayList<Aluno> alunos = new ArrayList<>();
		try {
			FileReader filereader = new FileReader("dados.txt");
			BufferedReader bufferedreader = new BufferedReader(filereader);
			String linha = "";
			while ((linha = bufferedreader.readLine()) != null) {
				String[] v = linha.split(",");
				Aluno a = new Aluno();
				a.setNome(v[0]);
				a.setDiciplina(v[1]);
				a.setNota1(Double.parseDouble(v[2]));
				System.out.println(v[2]);
				a.setNota2(Double.parseDouble(v[3]));
				System.out.println(v[3]);
				a.setNota3(Double.parseDouble(v[4]));
				System.out.println(v[4]);
				//a.setMediaAluno(Double.parseDouble(  v[2]+v[3]+v[4]   ) /3);
				System.out.println(a.getMediaAluno());
				a.setMedia(a.getMediaAluno());
				System.out.println(a.getMediaAluno());

				if (a.getMediaAluno() >= Double.parseDouble(lblMediaA.getText())) {
					a.setSituacao("APROVADO");
					System.out.println((a.getMediaAluno() + "APROVADO"));
				} else if (a.getMediaAluno() < Double.parseDouble(lblMediaR.getText())) {
					a.setSituacao("REPROVADO");
					System.out.println((a.getMediaAluno() + "REPROVADO"));
				} else {
					a.setSituacao("RECUPERAÇÃO");
				}
				alunos.add(a);
				tblAluno.setItems(FXCollections.observableArrayList(alunos));
			}

			bufferedreader.close();
			filereader.close();

		} catch (Exception e) {
			e.printStackTrace();
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
