package application;

import dao.ContaDAO;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Conta;

public class TelaOperacoesController {

	@FXML
	private TextField txtContaOrigem;
	@FXML
	private TextField txtContaDestino;
	@FXML
	private TextField txtValor;
	@FXML
	private Button btnConfirmar;
	@FXML
	private Button btnCancelar;
	@FXML
	private TableView<Conta> tblContas;
	@FXML
	private TableColumn<Conta, String> colCorrentista;
	@FXML
	private TableColumn<Conta, String> colAgencia;
	@FXML
	private TableColumn<Conta, Number> colSaldo;
	@FXML
	private RadioButton rbSaque;
	@FXML
	private RadioButton rbDeposito;
	@FXML
	private RadioButton rbTransferencia;
	@FXML
	private VBox vbOperacoes;
	@FXML
	private TextField txtFiltro;
	@FXML
	private HBox hbOperacoes;

	private ContaDAO contaDAO = new ContaDAO();
	private Conta cOrigem = null;
	private Conta cDestino = null;

	private void configuraTabela() {
		colCorrentista.setCellValueFactory(cellData -> cellData.getValue().getCorrentista().nomeProperty());
		// colAgencia.setCellValueFactory(cellData ->
		// cellData.getValue().getAgencia().cidadeProperty());
		colAgencia.setCellValueFactory(cellData -> cellData.getValue().getAgencia().strProperty());
		colSaldo.setCellValueFactory(cellData -> cellData.getValue().saldoProperty());
	}

	@FXML
	public void initialize() {
		configuraTabela();
		tblContas.setItems(FXCollections.observableArrayList(contaDAO.listarTodas()));
	}

	@FXML
	public void selecionaConta() {
		if (cOrigem == null) {
			cOrigem = tblContas.getSelectionModel().getSelectedItem();
			txtContaOrigem.setText(cOrigem.toString());
		} else {
			if (rbTransferencia.isSelected()) {
				if (cOrigem == null) {
					cOrigem = tblContas.getSelectionModel().getSelectedItem();
					txtContaOrigem.setText(cOrigem.toString());
				} else {
					cDestino = tblContas.getSelectionModel().getSelectedItem();
					txtContaDestino.setText(cDestino.toString());
				}
			} else {
				cOrigem = tblContas.getSelectionModel().getSelectedItem();
				txtContaOrigem.setText(cOrigem.toString());
				cDestino = null;
				txtContaDestino.setText("....................");
			}
		}
	}

	@FXML
	public void Operacao() {
		try {
			if (txtContaOrigem.getText().equals("") || txtContaDestino.getText().equals("")
					|| txtValor.getText().equals("")) {
				throw new NullPointerException();
			}
			if (Double.parseDouble(txtValor.getText()) <= 0) {
				throw new NumberFormatException();
			}
			double saldoOrigem = cOrigem.getSaldo();
			double vlOper = Double.parseDouble(txtValor.getText());
			if (rbDeposito.isSelected()) {
				contaDAO.atualizaSaldo(cOrigem, saldoOrigem + vlOper);
				cOrigem.setSaldo(saldoOrigem + vlOper);

			}
			if (rbSaque.isSelected()) {
				if (saldoOrigem >= vlOper) {
					contaDAO.atualizaSaldo(cOrigem, saldoOrigem - vlOper);
					cOrigem.setSaldo(saldoOrigem - vlOper);
				} else {
					mensagem("Saldo insuficiente");
				}
			}
			if (rbTransferencia.isSelected()) {
				if (cOrigem.getId() == cDestino.getId()) {
					mensagem("Contas não podem ser iguais !");
				} else {
					if (cOrigem.getAgencia().getId() != cDestino.getAgencia().getId()) {
						mensagem("Operação não permite contas com agência diferente");
					} else {
						if (saldoOrigem >= vlOper) {
							double saldoDestino = cDestino.getSaldo();
							contaDAO.atualizaSaldo(cOrigem, saldoOrigem - vlOper);
							contaDAO.atualizaSaldo(cDestino, saldoDestino + vlOper);
							cOrigem.setSaldo(saldoOrigem - vlOper);
							cDestino.setSaldo(saldoDestino + vlOper);
							txtContaDestino.setText(cDestino.toString());
						} else {
							mensagem("Saldo insuficiente");
						}
					}
				}
			}

			txtContaOrigem.setText(cOrigem.toString());
			tblContas.setItems(FXCollections.observableArrayList(contaDAO.listarTodas()));
		} catch (NullPointerException e) {
			mensagem("Conta origem" + "\n" + "Conta destino" + "\n" + "Valor da operação" + "\n"
					+ "PREENCHIMENTO OBRIGATÓRIO");
		} catch (NumberFormatException e) {
			mensagem("Valor da operação não pode ser R$ 0,00 ou negativo !");
		}

	}

	private void mensagem(String msg) {
		Alert a = new Alert(AlertType.INFORMATION);
		a.setTitle("Operação");
		a.setHeaderText("Resultado");
		a.setContentText(msg);
		a.show();
	}

	@FXML
	public void SelecionaOperacao() {
		if (rbSaque.isSelected()) {
			hbOperacoes.setDisable(false);
			vbOperacoes.setDisable(true);
			txtContaDestino.setDisable(true);
		}
		if (rbDeposito.isSelected()) {
			hbOperacoes.setDisable(false);
			vbOperacoes.setDisable(true);
			txtContaDestino.setDisable(true);
		}
		if (rbTransferencia.isSelected()) {
			hbOperacoes.setDisable(false);
			vbOperacoes.setDisable(true);
			txtContaDestino.setDisable(false);
		}
	}

	@FXML
	public void cancelaOperacao() {
		vbOperacoes.setDisable(false);
		hbOperacoes.setDisable(true);
		rbSaque.setSelected(false);
		rbDeposito.setSelected(false);
		rbTransferencia.setSelected(false);
		limpaTela();
	}

	@FXML
	public void filtraContas() {
		tblContas.setItems(FXCollections.observableArrayList(contaDAO.filtraPorCorrentista(txtFiltro.getText())));
	}

	public void limpaTela() {
		txtContaDestino.setText("");
		txtContaOrigem.setText("");
		txtValor.setText("");
	}
}
