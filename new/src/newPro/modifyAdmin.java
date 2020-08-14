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
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class modifyAdmin {
	Stage currentStage;
	String admin;
	TextField former = new TextField();// 原来的用户名
	TextField newAd = new TextField();// 新用户名
	
	public modifyAdmin (String admin) {
		this.admin = admin;
	}
	
	public void start(Stage primaryStage){
		Scene scene = new Scene(getAllInfo(),300,150);
		primaryStage.setScene(scene);
		primaryStage.setTitle("修改用户名");
		currentStage = primaryStage;
		primaryStage.show();
	}
	
	public VBox getAllInfo() {
		VBox vbox = new VBox();
		vbox.setAlignment(Pos.CENTER);
		vbox.getChildren().addAll(getFormer(),getNew(),getFix());
		return vbox;
	}
	// 汇总所有控件的页面
	
	public HBox getFormer() {
		HBox hbox = new HBox();
		
		hbox.setAlignment(Pos.CENTER);
		hbox.setPadding(new Insets(10,10,10,10));
		
		hbox.getChildren().add(new Label("请输入原用户名："));
		hbox.getChildren().add(former);
		//加入原用户名输入框
	
		return hbox;
	}
	
	public HBox getNew() {
		HBox hbox = new HBox();
		
		hbox.setAlignment(Pos.CENTER);
		hbox.setPadding(new Insets(10,10,10,10));
		
		hbox.getChildren().add(new Label("请输入新用户名："));
		hbox.getChildren().add(newAd);
		//加入新用户名输入框
		
		return hbox;
	}
	
	public HBox getFix() {
		HBox hbox = new HBox(10);
		
		hbox.setAlignment(Pos.CENTER);
		hbox.setPadding(new Insets(10,0,10,0));
		
		Button fix = new Button("修改");
		Button exit = new Button("退出");
		
		fix.setOnAction(e -> {
			try {
	    		Class.forName("com.mysql.jdbc.Driver");
	    		Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/dormitory?useSSL=false","root","123456");
	    		Statement sta = con.createStatement();
	    		if(former.getText().isEmpty() || newAd.getText().isEmpty()) {
	    		// 若填写的用户名为空
	    			Alert alert = new Alert(AlertType.WARNING,"用户名不能为空！");
    				alert.show();
    				// 弹窗指示修改失败
	    		}else {
	    			if(admin.equals(former.getText())) {
	    				// 若原用户名与当前用户名一致
	    				
	    				sta.executeUpdate("UPDATE admininfo SET name='" + newAd.getText() 
	    				+"' WHERE name='" + admin + "'");
	    				// 更改当前用户名
	    			
	    				Alert alert = new Alert(AlertType.INFORMATION,"修改成功，请重新登陆！");
	    				alert.show();
	    				// 弹窗指示修改成功，重新登陆
 					
	    				System.exit(0);
	    				// 重新登陆
	    			}else {
	    				Alert alert = new Alert(AlertType.ERROR,"用户名错误，修改失败！");
	    				alert.show();
	    				// 弹窗指示修改失败
	    				
	    				former.setText("");
	    				newAd.setText("");
	    				// 清空文本域
	    			}
	    		}
	    		sta.close();
	    		con.close();
	    	} catch (SQLException | ClassNotFoundException ex) {
	    		ex.printStackTrace();
	    	}
		});
		
		hbox.getChildren().add(fix);
		//加入修改按钮
		
		exit.setOnAction(e->currentStage.close());
		//点击exit按钮退出该界面
		
		hbox.getChildren().add(exit);
		//加入退出当前界面的按钮
		
		return hbox;
	}
	
}
