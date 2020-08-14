package newPro;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class Main extends Application{

	public void start(Stage primaryStage){
		Login login = new Login();
		Scene scene = new Scene(login.getMain(),650,400);
		primaryStage.setScene(scene);
		primaryStage.setTitle("寝室管理系统");
		primaryStage.show();
	}
	
	public static void main(String args[]){
		launch(args);
	}
}

