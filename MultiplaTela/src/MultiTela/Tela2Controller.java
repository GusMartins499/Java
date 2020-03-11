package MultiTela;

import java.util.ArrayList;

import domain.Cidade;
import domain.Uf;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

public class Tela2Controller {

	private ArrayList<Uf> listaUf = new ArrayList<Uf>();
	private ArrayList<Cidade> listaCidade = new ArrayList<Cidade>();

	@FXML
	ComboBox<Uf> cbUf;
	@FXML
	ComboBox<Cidade> cbCidade;

	private void criaUfs() {
		listaUf.add(new Uf("SC"));
		listaUf.add(new Uf("RS"));
	}

	private void criaCids() {
		listaCidade.add(new Cidade("Tubarão", listaUf.get(0)));
		listaCidade.add(new Cidade("Laguna", listaUf.get(0)));
		listaCidade.add(new Cidade("Gravatal", listaUf.get(0)));
		listaCidade.add(new Cidade("Porto Alegre", listaUf.get(1)));
		listaCidade.add(new Cidade("Guaíba", listaUf.get(1)));

	}

	public void initialize() {
		criaUfs();
		criaCids();
		cbUf.setItems(FXCollections.observableArrayList(listaUf));
		eventoChangeComboUF();
		cbUf.getSelectionModel().select(0);
		filtraCidades();
	}

	private void filtraCidades() {
		Uf ufSelecionada = cbUf.getSelectionModel().getSelectedItem();
		ArrayList<Cidade> nova = new ArrayList<Cidade>();
		for (Cidade c : listaCidade) {
			if(c.getUf().getNome().equals(ufSelecionada.getNome()))
				nova.add(c);
		}
		cbCidade.setItems(FXCollections.observableArrayList(nova));
	}
	private void eventoChangeComboUF() {
		cbUf.valueProperty().addListener(new ChangeListener<Uf>() {
			public void changed(javafx.beans.value.ObservableValue<? extends Uf> observable, Uf oldValue, Uf newValue) {
				filtraCidades();
			};
		});
	}
}
