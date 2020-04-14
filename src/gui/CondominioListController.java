package gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.entities.Condominio;
import model.services.CondominioService;

public class CondominioListController implements Initializable{

	private CondominioService service;
	
	@FXML
	private TableView<Condominio> tableViewCondominio;
	
	@FXML
	private TableColumn<Condominio, Integer> tableColumnIdCodigo;
	
	@FXML
	private TableColumn<Condominio, String> tableColumnRazaoNome;
	
	@FXML
	private Button btNovo;	
	
	//objeto � associado a TableView para exibir oss condominios.
	private ObservableList<Condominio> obsList;
	
	
	@FXML
	public void onBtNovoAction() {
		System.out.println("onBtNovoAction");
	}
	 
	public void setCondominioService(CondominioService service ) {
		this.service = service;
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {	
		initializeNodes();
	}

	private void initializeNodes() {
		tableColumnIdCodigo.setCellValueFactory(new PropertyValueFactory<>("idCondominio"));
		tableColumnRazaoNome.setCellValueFactory(new PropertyValueFactory<>("razaoSocial"));
		
		//c�digo para tabela iniciar preenchendo toda janela pai.
		Stage stage = (Stage)Main.getMainScene().getWindow();
		tableViewCondominio.prefHeightProperty().bind(stage.heightProperty());
	}
	
	//M�todo respons�vel por acessar os servi�os, carregar os condominios e jogar na ObservableList.
	public void updateTableView() {
		if(service == null) {
			throw new IllegalStateException("Service was null");
		}
		List<Condominio> list = service.findAll();
		obsList = FXCollections.observableArrayList(list);
		tableViewCondominio.setItems(obsList);
	}
}
