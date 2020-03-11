package MultiTela;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class PrincipalController {

	@FXML
	private TabPane pane;
	@FXML
	public void abreTela1() {
		abreTab("Busca Imagem", "Tela1.fxml");
	}
	@FXML
	public void abreTela2() {
		abreTab("Seleciona Cidade", "Tela2.fxml");
	}
	@FXML
	public void abreTela3() {
		abreTab("Data Nascimento", "Tela3.fxml");
	}

	private void abreTab(String titulo, String path) {
		try {
			Tab tab = tabAberta(titulo);
			if (tab == null) {
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
			if (tb.getText().equals(titulo))
				return tb;
		}
		return null;
	}

	private void selecionaTab(Tab tab) {
		pane.getSelectionModel().select(tab);
	}
}
