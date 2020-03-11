package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

import dao.CarroDAO;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import model.Carro;
import model.Marca;

public class PrincipalController {

	@FXML
	private ComboBox<Marca> cbMarca;
	@FXML
	private TextField txtModelo;
	@FXML
	private TextField txtAno;
	@FXML
	private TextField txtValor;
	@FXML
	private CheckBox ckAr;
	@FXML
	private CheckBox ckDirecao;
	@FXML
	private CheckBox ckPiloto;
	@FXML
	private CheckBox ckAbs;
	@FXML
	private CheckBox ckOutro;
	@FXML
	private TextField txtOutro;
	@FXML
	private TextField lblSomaValor;
	@FXML
	private TableView<Carro> tblCarro;
	@FXML
	private TableColumn<Carro, String> colMarca;
	@FXML
	private TableColumn<Carro, String> colModelo;
	@FXML
	private TableColumn<Carro, Number> colAno;
	@FXML
	private TableColumn<Carro, Number> colValor;
	@FXML
	private TableColumn<Carro, String> colAr;
	@FXML
	private TableColumn<Carro, String> colDirecao;
	@FXML
	private TableColumn<Carro, String> colPiloto;
	@FXML
	private TableColumn<Carro, String> colAbs;
	@FXML
	private TableColumn<Carro, String> colOutro;

	private CarroDAO dao = new CarroDAO();
	private ArrayList<Marca> marcas = new ArrayList<Marca>();
	private ArrayList<Carro> carro = dao.lista();

	@FXML
	public void initialize() {
		File file = new File("carros.txt");
		if (file.exists()) {
			lerArquivo();
		}
		configuraTabela();
		tblCarro.setItems(FXCollections.observableArrayList(dao.lista()));
		configuraCbMarca();
		cbMarca.setItems(FXCollections.observableArrayList(marcas));
	}

	public void configuraTabela() {
		colMarca.setCellValueFactory(cellData -> cellData.getValue().marcaProperty());
		colModelo.setCellValueFactory(cellData -> cellData.getValue().modeloProperty());
		colAno.setCellValueFactory(cellData -> cellData.getValue().anoProperty());
		colValor.setCellValueFactory(cellData -> cellData.getValue().valorProperty());
		colAr.setCellValueFactory(cellData -> cellData.getValue().arProperty());
		colDirecao.setCellValueFactory(cellData -> cellData.getValue().direcaoProperty());
		colPiloto.setCellValueFactory(cellData -> cellData.getValue().pilotoProperty());
		colAbs.setCellValueFactory(cellData -> cellData.getValue().absProperty());
		colOutro.setCellValueFactory(cellData -> cellData.getValue().outroProperty());
	}

	public void configuraCbMarca() {
		marcas.add(new Marca("Fiat"));
		marcas.add(new Marca("BMW"));
		marcas.add(new Marca("Chevrolet"));
	}

	@FXML
	public void cadastrar() {
		Carro c = tela4carro();
		dao.inserir(c);
		tblCarro.setItems(FXCollections.observableArrayList(dao.lista()));
	}

	private Carro tela4carro() {
		Carro c = new Carro();
		c.setMarca(cbMarca.getSelectionModel().getSelectedItem().toString());
		c.setModelo(txtModelo.getText());
		c.setAno(Integer.parseInt(txtAno.getText()));
		c.setValor(Double.parseDouble(txtValor.getText()));
		if (ckAr.isSelected()) {
			c.setAr("S");
		} else {
			c.setAr("N");
		}
		if (ckDirecao.isSelected()) {
			c.setDirecao("S");
		} else {
			c.setDirecao("N");
		}
		if (ckPiloto.isSelected()) {
			c.setPiloto("S");
		} else {
			c.setPiloto("N");
		}
		if (ckAbs.isSelected()) {
			c.setAbs("S");
		} else {
			c.setAbs("N");
		}
		if (ckOutro.isSelected()) {
			c.setOutro(txtOutro.getText());
		} else {
			c.setOutro(null);
		}
		limpaTela();
		return c;
	}

	public void limpaTela() {
		cbMarca.setSelectionModel(null);
		txtModelo.setText("");
		txtAno.setText("");
		txtValor.setText("");
		ckAr.setSelected(false);
		ckDirecao.setSelected(false);
		ckPiloto.setSelected(false);
		ckAbs.setSelected(false);
		ckOutro.setSelected(false);
		txtOutro.setText("");
	}

	@FXML
	public void adicionaArquivo() {
		try {
			FileWriter fw = new FileWriter("carros.txt", true);
			BufferedWriter bf = new BufferedWriter(fw);
			for (int i = 0; i <= dao.lista().size(); i++) {
				String linha = carro.get(i).getId() + "," + carro.get(i).getMarca() + "," + carro.get(i).getModelo()
						+ "," + carro.get(i).getAno() + "," + carro.get(i).getValor() + "," + carro.get(i).getAr() + ","
						+ carro.get(i).getDirecao() + "," + carro.get(i).getPiloto() + "," + carro.get(i).getAbs() + ","
						+ carro.get(i).getOutro() + "\n";
				bf.append(linha);
			}
			bf.close();
			fw.close();
			lerArquivo();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void lerArquivo() {
		ArrayList<Carro> listaCarro = new ArrayList<>();
		try {
			FileReader fr = new FileReader("carros.txt");
			BufferedReader br = new BufferedReader(fr);
			String linha = "";
			while ((linha = br.readLine()) != null) {
				String[] v = linha.split(",");
				Carro c = new Carro();
				c.setId(Integer.parseInt(v[0]));
				c.setMarca(v[1]);
				c.setModelo(v[2]);
				c.setAno(Integer.parseInt(v[3]));
				c.setValor(Double.parseDouble(v[4]));
				c.setAr(v[5]);
				c.setDirecao(v[6]);
				c.setPiloto(v[7]);
				c.setAbs(v[8]);
				c.setOutro(v[9]);
				listaCarro.add(c);
			}
			br.close();
			fr.close();
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

}
