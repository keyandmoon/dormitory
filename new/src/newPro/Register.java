package newPro;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Register {
	Stage currentStage;
	String admin;
	TextField newName = new TextField();// 新用户的名字
	PasswordField newPw = new PasswordField();// 新用户的密码
	
	public Register(String admin) {
		this.admin = admin;
	}
	
	public void start(Stage primaryStage){
		Scene scene = new Scene(getAllInfo(),300,150);
		primaryStage.setScene(scene);
		primaryStage.setTitle("注册用户");
		currentStage = primaryStage;
		primaryStage.show();
	}
	
	public VBox getAllInfo() {
		VBox vb = new VBox();
		vb.setAlignment(Pos.CENTER);
		vb.getChildren().addAll(getNewName(),getNewPw(),getRegi());
		
		return vb;
	}
	
	public HBox getNewName() {
		HBox hbox = new HBox();
		
		hbox.setAlignment(Pos.CENTER);
		hbox.setPadding(new Insets(10,10,10,10));
		
		hbox.getChildren().add(new Label("请输入新用户名："));
		hbox.getChildren().add(newName);
		//加入新用户名输入框
	
		return hbox;
	}
	
	public HBox getNewPw() {
		HBox hbox = new HBox();
		
		hbox.setAlignment(Pos.CENTER);
		hbox.setPadding(new Insets(10,10,10,10));
		
		hbox.getChildren().add(new Label("请输入新的密码："));
		hbox.getChildren().add(newPw);
		//加入新密码输入框
		
		return hbox;
	}
	
	public HBox getRegi() {
		HBox hbox = new HBox(10);
		
		hbox.setAlignment(Pos.CENTER);
		hbox.setPadding(new Insets(10,0,10,0));
		
		Button regi = new Button("注册");
		Button exit = new Button("退出");
		
		regi.setOnAction(e -> {
			try {
	    		Class.forName("com.mysql.jdbc.Driver");
	    		Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/dormitory?useSSL=false","root","123456");
	    		Statement sta = con.createStatement();
	    		if(newName.getText().isEmpty() || newPw.getText().isEmpty()) {
	    		// 若填写的用户名为空
	    			Alert alert = new Alert(AlertType.WARNING,"输入不能为空！");
    				alert.show();
    				// 弹窗指示注册失败
	    		}else {
	    				sta.executeUpdate("insert into admininfo (name, password) values "
	    						+ "('"+ newName.getText() +"','" + newPw.getText() +"')");
	    				// 增加新用户
	    			
	    				Alert alert = new Alert(AlertType.INFORMATION,"注册成功！");
	    				alert.show();
	    				// 弹窗指示注册成功
 				}
	    		sta.close();
	    		con.close();
	    	} catch (SQLException | ClassNotFoundException ex) {
	    		ex.printStackTrace();
	    	}
		});
		
		hbox.getChildren().add(regi);
		//加入修改按钮
		
		exit.setOnAction(e->currentStage.close());
		//点击exit按钮退出该界面
		
		hbox.getChildren().add(exit);
		//加入退出当前界面的按钮
		
		return hbox;
	}
	
}
