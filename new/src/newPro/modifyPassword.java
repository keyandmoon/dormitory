package newPro;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class modifyPassword {
	Stage currentStage;
	PasswordField formerPassword = new PasswordField();
	PasswordField newPassword = new PasswordField();
	String admin;
	
	public modifyPassword(String name) {
		this.admin = name;
	}
	// 从Login界面传输过来的当前用户名
	
	public void start(Stage primaryStage){
		Scene scene = new Scene(getAllInfo(),300,150);
		primaryStage.setScene(scene);
		primaryStage.setTitle("修改密码");
		currentStage = primaryStage;
		primaryStage.show();
	}
	// 创建一个新stage，完成操作后再关闭
	
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
		
		hbox.getChildren().add(new Label("请输入原密码："));
		hbox.getChildren().add(formerPassword);
		//加入原密码输入框
	
		return hbox;
	}
	
	public HBox getNew() {
		HBox hbox = new HBox();
		
		hbox.setAlignment(Pos.CENTER);
		hbox.setPadding(new Insets(10,10,10,10));
		
		hbox.getChildren().add(new Label("请输入新密码："));
		hbox.getChildren().add(newPassword);
		//加入新密码输入框
		
		return hbox;
	}
	
	public HBox getFix() {
		HBox hbox = new HBox(10);
		
		hbox.setAlignment(Pos.CENTER);
		hbox.setPadding(new Insets(10,0,10,0));
		
		Button fix = new Button("确认修改");
		Button exit = new Button("退出");
		
		fix.setOnAction(e -> {
			if(formerPassword.getText().isEmpty() || newPassword.getText().isEmpty()) {
				Alert warning = new Alert(AlertType.WARNING,"密码输入不能为空！");
 				warning.show();
			} else {
			// 如果输入的密码不为空
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/dormitory?useSSL=false","root","123456");
					Statement sta = con.createStatement();
					String s = "select password from admininfo WHERE name='" + admin + "'";
					// 获取当前用户的原密码
					ResultSet rs = sta.executeQuery(s);
					if(rs.next()) {
						if(rs.getString("password").equals(formerPassword.getText())) {
							// 若当前用户输入的原密码与数据库的原密码一致
	     				
							if(formerPassword.getText().equals(newPassword.getText())) {
								// 若当前用户输入的原密码与新密码一致
								
								Alert warning = new Alert(AlertType.WARNING,"原密码不能与新密码相同！");
								warning.show();
								// 弹窗警告原密码与新密码不能一致
		     				
								formerPassword.setText("");
								newPassword.setText("");
								//清空文本域让用户重新输入
		     				
							}else {
								// 若当前用户输入的原密码与新密码不同
	     					
								sta.executeUpdate("UPDATE admininfo SET password='" + newPassword.getText() 
								+"' WHERE name='" + admin + "'");
								// 则修改原密码
	     					
								Alert alert = new Alert(AlertType.INFORMATION,"修改成功！");
								alert.show();
								// 弹窗提示密码修改成功
	     					
								currentStage.close();
								//修改成功后退出该界面
							}
						}
						else {
							Alert error = new Alert(AlertType.WARNING,"请输入正确原密码！");
							error.show();
						}
					}
					rs.close();
					sta.close();
					con.close();
				} catch (SQLException | ClassNotFoundException ex) {
					ex.printStackTrace();
				}
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
