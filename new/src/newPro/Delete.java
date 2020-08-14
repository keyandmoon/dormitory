package newPro;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

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

public class Delete {
	Stage currentStage;
	Label label = new Label("请输入学号:");
	TextField input = new TextField();
	String admin;
    BorderPane borderPane = new BorderPane();
    DataBase mydata = new DataBase(); 
	
	public Delete(String name) {
		this.admin = name;
	}
	
	public void start(Stage primaryStage){
		Scene scene = new Scene(getResult(),300,150);
		primaryStage.setScene(scene);
		primaryStage.setTitle("删除学生信息");
		currentStage = primaryStage;
		primaryStage.show();
	}

	//将所有面板汇集一起
	public VBox getResult() {
		VBox vbox = new VBox();
		vbox.getChildren().add(getStudentID());
		vbox.getChildren().add(getOption());
		vbox.getChildren().add(borderPane);
		return vbox;
	}
	
	//输入名字的标签和文本框
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

		Button exit = new Button("退出");
		Button confirm = new Button("确定");
		
		
		//点击确定连接数据库进行查询（查询结果通过DataBase传入）
		confirm.setOnAction(e->{
			//输入数据为空时弹出提示
			if(input.getText().isEmpty()) {
				Alert warningWindows = new Alert(AlertType.ERROR,"输入不能为空！");
				warningWindows.show();
			}
			else {
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/dormitory?useSSL=false","root","123456");
					Statement sta = con.createStatement();
					System.out.print("登入成功！");
					
					//先查询输入的数字是否存在于数据库中
					String StuIdtQuery = "select * from studentinfo where stuId = '"+input.getText()+"'";
			    	ResultSet rs = sta.executeQuery(StuIdtQuery);
			    	//结果集不为空表示数据存在数据库中，进行删除操作
			    	if(rs.next()&&rs.getString(1) != null) {
			    		mydata.Delete(input.getText());
			    		Alert infoWindows = new Alert(AlertType.INFORMATION, "删除成功！");
			    		infoWindows.show();
			    	}
			    	//结果集为空则表明输入的数据在数据库中不存在，重新输入
			    	else {
			    		Alert warningWindows = new Alert(AlertType.INFORMATION, "删除失败！");
			    		warningWindows.show();
			    		input.setText("");
			    	}

					sta.close();
			    	con.close();
				}
				catch(Exception ex) {
			    	ex.printStackTrace();
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
