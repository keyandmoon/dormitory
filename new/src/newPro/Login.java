package newPro;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.*;

public class Login {
	BorderPane pane = new BorderPane();
	public TextField userInput = new TextField();
	public PasswordField passwordInput = new PasswordField();
	
	
	public BorderPane getMain() {
		pane.setCenter(getLoginInterface());
		return pane;
	}
	
	public VBox getLoginInterface(){
	    VBox vbox = new VBox(15);
	    vbox.setPadding(new Insets(50,15,5,15));
	    vbox.setAlignment(Pos.CENTER);
	    
	    vbox.getChildren().add(getTitle());
	    vbox.getChildren().add(getUserInput());
	    vbox.getChildren().add(getPasswordInput());
	    vbox.getChildren().add(getLoginAndExitButton());
	    return vbox;
	}
// 登陆页面的布局容器
	
	public Label getTitle(){
		Label title = new Label("寝室管理系统");
		title.setFont(Font.font("Timer New Roman",FontWeight.BOLD, FontPosture.ITALIC, 25));
		title.setAlignment(Pos.CENTER);
		title.setTextFill(Color.POWDERBLUE);
		return title;
	} 
// “寝室管理系统”标题字体的美化
	
	public HBox getUserInput(){
		HBox hbox = new HBox(15);
		hbox.setPadding(new Insets(15,15,0,15));
		hbox.getChildren().add(new Label("用户:"));
		userInput.setPrefWidth(200);
		hbox.getChildren().add(userInput);
		hbox.setAlignment(Pos.CENTER);
		return hbox;
	}
	
	public HBox getPasswordInput(){
		HBox hbox = new HBox(15);
		hbox.setPadding(new Insets(20,15,25,15));
		hbox.getChildren().add(new Label("密码:"));
		passwordInput.setPrefWidth(200);
		hbox.getChildren().add(passwordInput);
		hbox.setAlignment(Pos.CENTER);
		return hbox;
	}
	
	public HBox getLoginAndExitButton(){
		HBox hbox = new HBox(15);
		hbox.setPadding(new Insets(20,15,25,15));
		Button loginButton = new Button("登陆");
		loginButton.setOnAction(e ->{
		if(userInput.getText().isEmpty() || passwordInput.getText().isEmpty()) {
			Alert warningWindows = new Alert(AlertType.ERROR,"输入不能为空！");
			warningWindows.show();
		}else {
		// 若输入不为空
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/dormitory?useSSL=false","root","123456");
				Statement sta = con.createStatement();
				System.out.print("登入成功！");
				// 获得连接对象，用户连接进入数据库
				
				String nameAndPassword = "select * from admininfo where name='" + userInput.getText() + "'";
				ResultSet rs = sta.executeQuery(nameAndPassword);
				if(rs.next() && passwordInput.getText().equals(rs.getString("password"))) {
				// 判断用户输入的密码是否正确
					pane.setCenter(new chooseDormitory(pane).getChoDorInterface());
					// 跳转到选择寝室的页面
					pane.setTop(new MenuStage(pane,userInput.getText()).getMenu());
					// 设置顶部菜单栏
				}
				else {
					Alert warningWindows = new Alert(AlertType.ERROR,"请输入正确信息！");
					warningWindows.show();
					userInput.setText("");
					passwordInput.setText("");
					// 清空文本域让用户重新输入
				}
				// 判断用户输入的用户名与密码是否正确
				rs.close();
				sta.close();
				con.close();
			} catch (SQLException | ClassNotFoundException ex) {
				ex.printStackTrace();
			}
			// 抛出异常
		}
		});
		
		Button exitButton = new Button("退出");
		exitButton.setOnAction(e ->{
			System.exit(0);
		});
		// 用户退出寝室管理系统
		
		hbox.getChildren().add(loginButton);
		hbox.getChildren().add(exitButton);
		hbox.setAlignment(Pos.CENTER);
		return hbox;
	}
}
