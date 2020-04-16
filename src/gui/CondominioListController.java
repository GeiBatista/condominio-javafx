package gui;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import gui.util.Alerts;
import gui.util.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
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
	
	//objeto é associado a TableView para exibir oss condominios.
	private ObservableList<Condominio> obsList;
	
	
	@FXML
	public void onBtNovoAction(ActionEvent event) {
		Stage parentStage = Utils.currentStage(event);
		createDialogForm("/gui/CondominioForm.fxml", parentStage);
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
		
		//código para tabela iniciar preenchendo toda janela pai.
		Stage stage = (Stage)Main.getMainScene().getWindow();
		tableViewCondominio.prefHeightProperty().bind(stage.heightProperty());
	}
	
	//Método responsável por acessar os serviços, carregar os condominios e jogar na ObservableList.
	public void updateTableView() {
		if(service == null) {
			throw new IllegalStateException("Service was null");
		}
		List<Condominio> list = service.findAll();
		obsList = FXCollections.observableArrayList(list);
		tableViewCondominio.setItems(obsList);
	}
	
	private void createDialogForm(String absoluteName, Stage parentStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			Pane pane = loader.load();
			
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Inserir dados do departamento");
			dialogStage.setScene(new Scene(pane));
			dialogStage.setResizable(false);
			dialogStage.initOwner(parentStage);
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.showAndWait();
		} catch (IOException e) {
			Alerts.showAlert("IO Exception", "Error loading view", e.getMessage(), AlertType.ERROR);
		}
	}
}
