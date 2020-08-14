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
// ��½ҳ��Ĳ�������
	
	public Label getTitle(){
		Label title = new Label("���ҹ���ϵͳ");
		title.setFont(Font.font("Timer New Roman",FontWeight.BOLD, FontPosture.ITALIC, 25));
		title.setAlignment(Pos.CENTER);
		title.setTextFill(Color.POWDERBLUE);
		return title;
	} 
// �����ҹ���ϵͳ���������������
	
	public HBox getUserInput(){
		HBox hbox = new HBox(15);
		hbox.setPadding(new Insets(15,15,0,15));
		hbox.getChildren().add(new Label("�û�:"));
		userInput.setPrefWidth(200);
		hbox.getChildren().add(userInput);
		hbox.setAlignment(Pos.CENTER);
		return hbox;
	}
	
	public HBox getPasswordInput(){
		HBox hbox = new HBox(15);
		hbox.setPadding(new Insets(20,15,25,15));
		hbox.getChildren().add(new Label("����:"));
		passwordInput.setPrefWidth(200);
		hbox.getChildren().add(passwordInput);
		hbox.setAlignment(Pos.CENTER);
		return hbox;
	}
	
	public HBox getLoginAndExitButton(){
		HBox hbox = new HBox(15);
		hbox.setPadding(new Insets(20,15,25,15));
		Button loginButton = new Button("��½");
		loginButton.setOnAction(e ->{
		if(userInput.getText().isEmpty() || passwordInput.getText().isEmpty()) {
			Alert warningWindows = new Alert(AlertType.ERROR,"���벻��Ϊ�գ�");
			warningWindows.show();
		}else {
		// �����벻Ϊ��
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/dormitory?useSSL=false","root","123456");
				Statement sta = con.createStatement();
				System.out.print("����ɹ���");
				// ������Ӷ����û����ӽ������ݿ�
				
				String nameAndPassword = "select * from admininfo where name='" + userInput.getText() + "'";
				ResultSet rs = sta.executeQuery(nameAndPassword);
				if(rs.next() && passwordInput.getText().equals(rs.getString("password"))) {
				// �ж��û�����������Ƿ���ȷ
					pane.setCenter(new chooseDormitory(pane).getChoDorInterface());
					// ��ת��ѡ�����ҵ�ҳ��
					pane.setTop(new MenuStage(pane,userInput.getText()).getMenu());
					// ���ö����˵���
				}
				else {
					Alert warningWindows = new Alert(AlertType.ERROR,"��������ȷ��Ϣ��");
					warningWindows.show();
					userInput.setText("");
					passwordInput.setText("");
					// ����ı������û���������
				}
				// �ж��û�������û����������Ƿ���ȷ
				rs.close();
				sta.close();
				con.close();
			} catch (SQLException | ClassNotFoundException ex) {
				ex.printStackTrace();
			}
			// �׳��쳣
		}
		});
		
		Button exitButton = new Button("�˳�");
		exitButton.setOnAction(e ->{
			System.exit(0);
		});
		// �û��˳����ҹ���ϵͳ
		
		hbox.getChildren().add(loginButton);
		hbox.getChildren().add(exitButton);
		hbox.setAlignment(Pos.CENTER);
		return hbox;
	}
}
