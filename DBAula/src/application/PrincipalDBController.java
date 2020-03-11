package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import util.ConexaoDataBase;
import util.Constants;

public class PrincipalDBController {
	
	@FXML
	private TabPane pane;
	@FXML
	public void abreTela1() {
		abreTab("Agência", "TelaAgencia.fxml");
	}
	@FXML
	public void abreTela2() {
		abreTab("Banco", "TelaBanco.fxml");
	}
	@FXML
	public void abreTela3() {
		abreTab("Correntista", "TelaCorrentista.fxml");
	}
	@FXML
	public void abreTela4() {
		abreTab("Operações", "TelaOperacoes.fxml");
	}
	@FXML
	public void abreTela5() {
		abreTab("Conta", "TelaConta.fxml");
	}
	private void abreTab(String titulo, String path) {
		try {
			Tab tab = tabAberta(titulo);
			if(tab == null) {
				tab = new Tab(titulo);
				tab.setClosable(true);
				pane.getTabs().add(tab);
				tab.setContent((Node) FXMLLoader.load(getClass().getResource(path)));
			}
			selecionaTab(tab);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private Tab tabAberta(String titulo) {
		for(Tab tb : pane.getTabs()) {
			if(tb.getText().equals(titulo))
				return tb;
		}
		return null;
	}
	private void selecionaTab(Tab tab) {
		pane.getSelectionModel().select(tab);
	}
	
	@FXML
	public void initialize() {
		Constants.conn = ConexaoDataBase.conectaBd();
	}
}
