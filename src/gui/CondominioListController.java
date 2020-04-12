package gui;

import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.entities.Condominio;

public class CondominioListController implements Initializable{

	@FXML
	private TableView<Condominio> tableViewCondominio;
	
	@FXML
	private TableColumn<Condominio, Integer> tableColumnIdCodigo;
	
	@FXML
	private TableColumn<Condominio, String> tableColumnRazaoNome;
	
	@FXML
	private Button btNovo;
	
	public void onBtNovoAction() {
		System.out.println("onBtNovoAction");
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {	
		initializeNodes();
	}

	private void initializeNodes() {
		tableColumnIdCodigo.setCellValueFactory(new PropertyValueFactory<>("Código"));
		tableColumnRazaoNome.setCellValueFactory(new PropertyValueFactory<>("Nome"));
		
		//código para tabela iniciar preenchendo toda janela pai.
		Stage stage = (Stage)Main.getMainScene().getWindow();
		tableViewCondominio.prefHeightProperty().bind(stage.heightProperty());
	}
}
