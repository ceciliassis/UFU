package Account;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Vicente
 */
public class PaginaExtratoController implements Initializable, ControlledScreen {

    @FXML
    private Button voltarMenu;

    ScreensController myController;

    @FXML
    private Button removerGasto;

    @FXML
    private ChoiceBox cbCategoria;

    @FXML
    private TableView<TableModel> tvExtrato;

    @FXML
    private TextField tfValor;

    @FXML
    private TextField tfDescricao;

    @FXML
    private Button addGasto;

    @FXML
    private TextField dpData;

    @FXML
    private TableColumn tcValor;

    @FXML
    private TableColumn tcDescricao;

    @FXML
    private TableColumn tcCategoria;

    @FXML
    private TableColumn tcData;
    
    ObservableList<TableModel> dados = FXCollections.observableArrayList();
   
    ArrayList<String> coisas = new ArrayList<>();
 
    ArrayList<String> salvadora = new ArrayList<>();
    
    @FXML
    private Label campoExtrato;
    
    @FXML
    private Button carregarMovs;
    
    //Criar botão que salve os dados e todos os outros dados inseridos
    
    @Override
    public void initialize(URL url, ResourceBundle rb)  {
        
        tcData.setCellValueFactory(new PropertyValueFactory("tabData"));
        tcDescricao.setCellValueFactory(new PropertyValueFactory("tabDescricao"));
        tcCategoria.setCellValueFactory(new PropertyValueFactory("tabCategoria"));
        tcValor.setCellValueFactory(new PropertyValueFactory("tabValor"));

        tvExtrato.setItems( dados);
        
        carregarMovs.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
   
                        FileReader fileReader = new FileReader("gastos.txt");
                        BufferedReader reader = new BufferedReader(fileReader);
                        String line = null;
                        while ((line = reader.readLine()) != null) {
                            String [] t = line.split("##");
                            dados.add( new TableModel(t[0],t[1],t[2],t[3]) );
                            salvadora.add(t[0] + "##" + t[1] + "##" + t[2] + "##" + t[3]);
                        }
                        reader.close();
                        fileReader.close();            

                    } catch (FileNotFoundException ex) {
                        campoExtrato.setText("Banco de Gastos não encontrado! ");
                    } catch (IOException ex) {
                        campoExtrato.setText("Erro ao carregar banco de Gastos! IOException ");
                        ex.printStackTrace();
                    }
            }
        });  
            
        addGasto.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String s;
                s = Moeda.formatarMoeda(Double.parseDouble(tfValor.getText()));
                dados.add(new TableModel(dpData.getText(), tfDescricao.getText(), (String) cbCategoria.getValue(), s));
                coisas.add(dpData.getText()+"##"+ tfDescricao.getText()+"##"+ (String) cbCategoria.getValue()+"##"+ s);
            }
        });  

        removerGasto.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dados.removeAll(tvExtrato.getSelectionModel().getSelectedItems());
            }
        });
        
    }
    
    @Override
    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }

    @FXML
    private void irParaPagina1(ActionEvent event) {
        salvarGastos();
        myController.setScreen(Main.telaPagina1);
    }
    
    public void salvarGastos(){
        
        try {
           
            FileWriter file = new FileWriter("gastos.txt");
            BufferedWriter writer = new BufferedWriter(file);
            for (String info : coisas) {
                writer.write(info);
                writer.newLine();
            }
            writer.close();
            file.close();
            
        } catch (Exception ex) {         
            System.out.println("Erro ao salvar banco de Gastos! ");
            ex.printStackTrace();
        }
    }
}
