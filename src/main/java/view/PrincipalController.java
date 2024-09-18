package view;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;

public class PrincipalController implements Initializable {
    
    @FXML
    private Label lblValor1;

    @FXML
    private Label lblValor2;
    
    @FXML
    private Button btnDividir;

    @FXML
    private Button btnLimpar;

    @FXML
    private Button btnSomar;

    @FXML
    private Button btnSubtrair;
    
    @FXML
    private Button btnMultiplicar;
    
    @FXML
    private Label lblResultado;

    @FXML
    private TextField txtFldValor1;

    @FXML
    private TextField txtFldValor2;

    @FXML
    void btnLimpar(ActionEvent event) {
        lblResultado.setText(" ");
        txtFldValor1.setText("0");
        txtFldValor2.setText("0");
    }
    
    @FXML
    void btnSair(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmação de Saída");
        alert.setHeaderText("Você tem certeza que deseja sair?");
        
        ButtonType buttonTypeSair = new ButtonType("Sair");
        ButtonType buttonTypeVoltar = new ButtonType("Voltar");
        alert.getButtonTypes().setAll(buttonTypeSair, buttonTypeVoltar);
        
        // Mostra a caixa 
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == buttonTypeSair) {
            Platform.exit();
        }
    }

    @FXML
    void btnMultiplicar(ActionEvent event) {
        float valor1 = Float.parseFloat(txtFldValor1.getText());
        float valor2 = Float.parseFloat(txtFldValor2.getText());
        
        float multiplicacao = valor1*valor2;
        lblResultado.setText(Float.toString(multiplicacao));
    }
    
    @FXML
    void btnDividir(ActionEvent event) {
        float valor1 = Float.parseFloat(txtFldValor1.getText());
        float valor2 = Float.parseFloat(txtFldValor2.getText());
        
        float divisao = valor1/valor2;
        lblResultado.setText(String.valueOf(divisao));
    }

    @FXML
    void btnSomar(ActionEvent event) {
        float valor1 = Float.parseFloat(txtFldValor1.getText());
        float valor2 = Float.parseFloat(txtFldValor2.getText());
        
        float soma = valor1 + valor2;
        lblResultado.setText(Float.toString(soma));
    }

    @FXML
    void btnSubtrair(ActionEvent event) {
        float valor1 = Float.parseFloat(txtFldValor1.getText());
        float valor2 = Float.parseFloat(txtFldValor2.getText());
        
        float subtracao = valor1/valor2;
        lblResultado.setText(Float.toString(subtracao));
    }
    
    public void DesabilitarBotão(){
        btnSomar.setDisable(txtFldValor1.getText().isEmpty() || txtFldValor2.getText().isEmpty());
        btnSubtrair.setDisable(txtFldValor1.getText().isEmpty() || txtFldValor2.getText().isEmpty());
        btnMultiplicar.setDisable(txtFldValor1.getText().isEmpty() || txtFldValor2.getText().isEmpty());
        btnDividir.setDisable(txtFldValor1.getText().isEmpty() || txtFldValor2.getText().isEmpty());
        btnLimpar.setDisable(txtFldValor1.getText().isEmpty() || txtFldValor2.getText().isEmpty());
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Platform.runLater(()->{
            txtFldValor1.requestFocus(); /*o campo de texto se torna o foco ativo para o usuário, 
            permitindo que ele comece a digitar imediatamente ou interaja com o campo sem 
            precisar clicar nele primeiro.*/
            DesabilitarBotão();
        });
        
        /*aparecem quando o usuário passa o cursor sobre um componente*/
        lblValor1.setTooltip(new Tooltip("Digite aqui o valor 1"));
        lblValor2.setTooltip(new Tooltip("Digite aqui o valor 2"));
        
        String ds = ",";
        txtFldValor1.textProperty().addListener((observable,oldValue,newValue) -> {
            if(!newValue.matches("(\\-)?\\d*(\\" + ds + "\\d*)?") && !newValue.isEmpty()){
                txtFldValor1.setText(oldValue);
            }
            DesabilitarBotão();
        });
        
        txtFldValor2.textProperty().addListener((observable,oldValue,newValue) -> {
            if(!newValue.matches("(\\-)?\\d*(\\" + ds + "\\d*)?") && !newValue.isEmpty()){
                txtFldValor2.setText(oldValue);
            }
            DesabilitarBotão();
        });
    }    
}
