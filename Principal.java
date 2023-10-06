/* ***************************************************************
* Autor............: Caíque Santos Santana
* Matricula........: 202010643
* Inicio...........: 28/09/2023
* Ultima alteracao.: 05/10/2023
* Nome.............: Classe Main do Problema dos Trens
* Funcao...........: Importa o que é necessario para iniciar e
cria a interface gráfica.
****************************************************************/

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import control.controle;

public class Principal extends Application{

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/principalfxml.fxml"));
        AnchorPane root = loader.load();
		Scene cena = new Scene(root, 800, 579);
		primaryStage.setScene(cena);
		primaryStage.setTitle("202010643 - HORIZONTAL");
		primaryStage.setResizable(false);	
		primaryStage.show();
	}
	
}

