package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class browserController implements Initializable{
	
	@FXML
	private TextField urlTextField;
	
		
	@FXML
	private WebView browserWebView;
	
	private WebEngine webEngine;
	
	@FXML
	private void onButtonCLick() {
		
		if(webEngine==null) {
			webEngine= browserWebView.getEngine();
		}
		
		if(!urlTextField.getText().isEmpty()) {
		webEngine.load(urlTextField.getText());
		}
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		urlTextField.setText("https://github.com/");
		
		urlTextField.setOnKeyPressed(e->{
			if(e.getCode()==KeyCode.ENTER) {
				onButtonCLick();
			}
		});
	}
	
}
