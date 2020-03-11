package application;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class PrincipalController {
	@FXML
	private TabPane TabPane;
	@FXML
	private Button btnLancamentos;
	@FXML
	private Button btnCadConta;
	@FXML
	private Button btnRelatorio;
	@FXML
	private Button btnSair;
	@FXML
	private BorderPane pane;
	@FXML
	private Button btnLivroCaixa;
	
	

	@FXML
	public void initialize() {
		abreTab("Livro Caixa", "TelaLivroCaixa.fxml");
		
	}

	@FXML
	public void abreTelaLancamentos() {
		try {
			Stage TelaLancamentos = new Stage();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("CadLancamento.fxml"));
			Parent root = loader.load();
			TelaLancamentos.setScene(new Scene(root));
			TelaLancamentos.setResizable(true);
			TelaLancamentos.show();
			

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void abreLivroCaixa() {
		try {
			Stage TelaLivroCaixa = new Stage();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("TelaLivroCaixa.fxml"));
			Parent root = loader.load();
			TelaLivroCaixa.setScene(new Scene(root));
			TelaLivroCaixa.setResizable(true);
			TelaLivroCaixa.show();
			Stage stage = (Stage) pane.getScene().getWindow();
			stage.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void abreCadConta() {
		try {
			Stage TelaCadConta = new Stage();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("CadPlanoDeConta.fxml"));
			Parent root = loader.load();
			TelaCadConta.setScene(new Scene(root));
			TelaCadConta.setResizable(true);
			TelaCadConta.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void sairDoSistema() {
		Stage stage = (Stage) pane.getScene().getWindow();
		stage.close();
	}

	@FXML
	public void abreTela1() {
		abreTab("Livro Caixa", "TelaLivroCaixa.fxml");
	}

	private void abreTab(String titulo, String path) {
		try {
			Tab tab = tabAberta(titulo);
			if (tab == null) {
				tab = new Tab(titulo);
				tab.setClosable(true);
				TabPane.getTabs().add(tab);
				tab.setContent((Node) FXMLLoader.load(getClass().getResource(path)));
			}
			selecionaTab(tab);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Tab tabAberta(String titulo) {
		for (Tab tb : TabPane.getTabs()) {
			if (tb.getText().equals(titulo))
				return tb;
		}
		return null;
	}

	private void selecionaTab(Tab tab) {
		TabPane.getSelectionModel().select(tab);
	}

	@FXML
	private void mostraLivroCaixa() {
		abreTab("Livro Caixa", "TelaLivroCaixa.fxml");
	}

}
