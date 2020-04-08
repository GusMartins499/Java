package util;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Mensagem {

	public static void exibeMsg(String titulo, String cabecalho, String msg, AlertType tipo) {
		Alert alert = new Alert(tipo);
		alert.setTitle(titulo);
		alert.setHeaderText(cabecalho);
		alert.setContentText(msg);
		alert.showAndWait();
	}
}
