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

public class theStudentID {
	Stage currentStage;
	Label label = new Label("请输入学号:");
	TextField input = new TextField();
	String admin;
    BorderPane borderPane = new BorderPane();
    DataBase mydata = new DataBase(); 
	
	public theStudentID(String name) {
		this.admin = name;
	}
	
	public void start(Stage primaryStage){
		Scene scene = new Scene(getResult(),500,400);
		primaryStage.setScene(scene);
		primaryStage.setTitle("按学号查询");
		currentStage = primaryStage;
		primaryStage.show();
	}

	//将各个面板汇集到一起
	public VBox getResult() {
		VBox vbox = new VBox();
		vbox.getChildren().add(getStudentID());
		vbox.getChildren().add(getOption());
		vbox.getChildren().add(borderPane);
		return vbox;
	}
	
	//输入信息的标签和文本框
	public HBox getStudentID() {
		HBox hbox = new HBox(15);
		hbox.setPadding(new Insets(30,10,10,10));
		
		hbox.setAlignment(Pos.CENTER);
		hbox.getChildren().addAll(label, input);	

		return hbox;
	}
	
	//确定、退出按钮
	public HBox getOption() {
		HBox hbox = new HBox(10);
		hbox.setAlignment(Pos.CENTER);
		hbox.setPadding(new Insets(15,0,10,0));
		Button confirm = new Button("确定");
		Button exit = new Button("退出");
		
		//点击确定连接数据库进行查询（查询结果通过DataBase传入）
		confirm.setOnAction(e->{
			if(input.getText().isEmpty()) {
				Alert error = new Alert(AlertType.WARNING,("输入不能为空！"));
				error.show();
			}else {
			try {
				borderPane.setCenter(mydata.StuId_Query(input.getText()));
			}
			catch(Exception ex) {
				ex.printStackTrace();
				System.out.println("ERROR");
			}
			}
		});
	
		//点击exit按钮退出该界面
		exit.setOnAction(e->currentStage.close());
		hbox.getChildren().add(confirm);
		hbox.getChildren().add(exit);
		
		return hbox;
	}
	
}
