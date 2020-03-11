package application;

import dao.UsuariosDAO;
import domain.Usuario;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class TelaRankController {

	@FXML
	private TableView<Usuario> tbl;
	@FXML
	private TableColumn<Usuario, String> colLogin;
	@FXML
	private TableColumn<Usuario, String> colTurma;
	@FXML
	private TableColumn<Usuario, Number> colPontuacao;
	@FXML
	private BorderPane pane;
	@FXML
	private Label lbl1;
	@FXML
	private Label lbl2;
	@FXML
	private Label lbl3;

	private UsuariosDAO dao = new UsuariosDAO();

	@FXML
	public void initialize() {
		tbl.setItems(FXCollections.observableArrayList(dao.listarTodas()));
		configuraTabela();
		//colocacao();
	}

	private void configuraTabela() {
		colLogin.setCellValueFactory(cellData -> cellData.getValue().loginProperty());
		colTurma.setCellValueFactory(cellData -> cellData.getValue().turmaProperty());
		colPontuacao.setCellValueFactory(cellData -> cellData.getValue().pontuacaoProperty());
	}

	@FXML
	public void fechaJanela() {
		Stage stage = (Stage) pane.getScene().getWindow();
		stage.close();
		try {
			Stage Principal = new Stage();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Principal.fxml"));
			Parent root = loader.load();
			Principal.setScene(new Scene(root));
			Principal.initModality(Modality.WINDOW_MODAL);
			Principal.initStyle(StageStyle.UNDECORATED);
			Principal.setResizable(false);
			Principal.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void colocacao() {
		int pos1 = dao.listarTodas().get(0).getPontuacao();
		int pos3 = dao.listarTodas().get(0).getPontuacao();
		for (int i = 0; i <dao.listarTodas().size(); i++) {
			if (dao.listarTodas().get(i).getPontuacao() > pos1) {
				pos1 = dao.listarTodas().get(i).getPontuacao();
			} else {
				if (dao.listarTodas().get(i).getPontuacao() < pos3) {
					pos3 = dao.listarTodas().get(i).getPontuacao();
				}
			}
		}
		System.out.println(pos1 +" - " + pos3);
	}
}
