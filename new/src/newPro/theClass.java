package newPro;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class theClass {
	Stage currentStage;
	Label label = new Label("������༶:");
	TextField input = new TextField();
	String admin;
    BorderPane borderPane = new BorderPane();
    DataBase mydata = new DataBase(); 
	
	public theClass(String name) {
		this.admin = name;
	}
	
	public void start(Stage primaryStage){
		Scene scene = new Scene(getResult(),500,400);
		primaryStage.setScene(scene);
		primaryStage.setTitle("���༶��ѯ");
		currentStage = primaryStage;
		primaryStage.show();
	}

	//���������㼯��һ��
	public VBox getResult() {
		VBox vbox = new VBox();
		vbox.getChildren().add(getStudentClass());
		vbox.getChildren().add(getOption());
		vbox.getChildren().add(borderPane);
		return vbox;
	}
	
	//������Ϣ�ı�ǩ���ı���
	public HBox getStudentClass() {
		HBox hbox = new HBox(15);
		hbox.setPadding(new Insets(30,10,10,10));
		
		hbox.setAlignment(Pos.CENTER);
		hbox.getChildren().addAll(label, input);	

		return hbox;
	}
	
	public HBox getOption() {
		HBox hbox = new HBox(10);
		hbox.setAlignment(Pos.CENTER);
		hbox.setPadding(new Insets(15,0,10,0));
		Button confirm = new Button("ȷ��");
		Button exit = new Button("�˳�");
		
		//���ȷ���������ݿ���в�ѯ����ѯ���ͨ��DataBase���룩
		confirm.setOnAction(e->{  
			if(input.getText().isEmpty()) {
				Alert error = new Alert(AlertType.WARNING,("���벻��Ϊ�գ�"));
				error.show();
			}else {
		    try {
		    	borderPane.setCenter(mydata.Class_Query(input.getText()));
		    }
		    catch(Exception ex) {
		    	ex.printStackTrace();
		        System.out.println("ERROR");
		    }
			}
		});
	
		//���exit��ť�˳��ý���
		exit.setOnAction(e->currentStage.close());
		hbox.getChildren().add(confirm);
		hbox.getChildren().add(exit);
		
		return hbox;
	}	
}
