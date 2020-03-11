package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class TelaConfiguracaoController {

	@FXML
	Pane pane;
	@FXML
	TabPane tabPane;

	@FXML
	public void fechaJanela() {
		Stage stage = (Stage) pane.getScene().getWindow();
		stage.close();
		try {
			Stage Principal = new Stage();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Principal.fxml"));
			Parent root = loader.load();
			Principal.setScene(new Scene(root));
			Principal.setResizable(false);
			Principal.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void abreTab(String titulo, String path) {
		try {
			Tab tab = tabAberta(titulo);
			if (tab == null) {
				tab = new Tab(titulo);
				tab.setClosable(true);
				tabPane.getTabs().add(tab);
				tab.setContent((Node) FXMLLoader.load(getClass().getResource(path)));
			}
			selecionaTab(tab);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void selecionaTab(Tab tab) {
		tabPane.getSelectionModel().select(tab);
	}
	private Tab tabAberta(String titulo) {
		for(Tab tb : tabPane.getTabs()) {
			if (tb.getText().equals(titulo))
				return tb;
		}
		return null;
	}
	@FXML
	public void abreTela1() {
		abreTab("Cadastro Questão", "TelaCadastroQuestao.fxml");
	}
	
}
