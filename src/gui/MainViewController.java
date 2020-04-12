package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;

public class MainViewController implements Initializable {

	@FXML
	private MenuItem menuItemCondominio;

	@FXML
	private MenuItem menuItemUnidade;

	@FXML
	private MenuItem menuItemSobre;

	@FXML
	public void onMenuItemCondominioAction() {
		carregarTela("/gui/CondominioList.fxml");
	}

	@FXML
	public void onMenuItemUnidadeAction() {
		System.out.println("onMenuItemUnidadeAction");
	}

	@FXML
	public void onMenuItemSobreAction() {
		carregarTela("/gui/Sobre.fxml");
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
	}

	public synchronized void carregarTela(String nomeAbsoluto) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(nomeAbsoluto));
			VBox newVbox = loader.load();

			Scene mainScene = Main.getMainScene();
			VBox mainVBox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent();

			Node mainMenu = mainVBox.getChildren().get(0);
			mainVBox.getChildren().clear();
			mainVBox.getChildren().add(mainMenu);
			mainVBox.getChildren().addAll(newVbox.getChildren());
		
		} catch (IOException e) {
			Alerts.showAlert("IO Exception", "Erro ao carregar Tela", e.getMessage(), AlertType.ERROR);
		}
	}
}
