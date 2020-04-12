package gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;

public class MainViewController implements Initializable{
	
	@FXML
	private MenuItem menuItemCondominio;
	
	@FXML
	private MenuItem menuItemUnidade;
	
	@FXML
	private MenuItem menuItemSobre;
	
	@FXML
	public void onMenuItemCondominioAction() {
		System.out.println("onMenuItemCondominioAction");
	}
	
	@FXML
	public void onMenuItemUnidadeAction() {
		System.out.println("onMenuItemUnidadeAction");
	}
	@FXML
	public void onMenuItemSobreAction() {
		System.out.println("onMenuItemSobreAction");
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {		
	}

}
