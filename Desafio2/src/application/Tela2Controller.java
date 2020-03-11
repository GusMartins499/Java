package application;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Produto;


public class Tela2Controller {

	@FXML
	Label lblDesc;
	@FXML
	Label lblValor;
	@FXML
	Label lblQtd;
	@FXML
	ImageView img;
	@FXML
	private Produto produto;

	// public void trocaNome(String d,String v,String q) {
	// desc.setText(d);
	// vl.setText(v);
	// qtde.setText(q);
	// }
//	public void trocaNome(String d) {
//		lblDesc.setText(d);
//		
//	}
//
//	public void trocaValor(String v) {
//		lblValor.setText(v);
//	}
//
//	public void trocaQtd(String q) {
//		lblQtd.setText(q);
//	}
	
	public void recebeProduto(Produto produto) {
		this.produto = produto;
		Image i = new Image(produto.getUrlImg());
		img.setImage(i);
		img.setFitWidth(200);
		img.setFitHeight(200);
		lblDesc.setText(produto.getDescricao());
		lblQtd.setText(produto.getQtd()+"");
		lblValor.setText(produto.getValor()+"");
		
		
	}
	
}
