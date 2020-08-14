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
	TextField newName = new TextField();// ���û�������
	PasswordField newPw = new PasswordField();// ���û�������
	
	public Register(String admin) {
		this.admin = admin;
	}
	
	public void start(Stage primaryStage){
		Scene scene = new Scene(getAllInfo(),300,150);
		primaryStage.setScene(scene);
		primaryStage.setTitle("ע���û�");
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
		
		hbox.getChildren().add(new Label("���������û�����"));
		hbox.getChildren().add(newName);
		//�������û��������
	
		return hbox;
	}
	
	public HBox getNewPw() {
		HBox hbox = new HBox();
		
		hbox.setAlignment(Pos.CENTER);
		hbox.setPadding(new Insets(10,10,10,10));
		
		hbox.getChildren().add(new Label("�������µ����룺"));
		hbox.getChildren().add(newPw);
		//���������������
		
		return hbox;
	}
	
	public HBox getRegi() {
		HBox hbox = new HBox(10);
		
		hbox.setAlignment(Pos.CENTER);
		hbox.setPadding(new Insets(10,0,10,0));
		
		Button regi = new Button("ע��");
		Button exit = new Button("�˳�");
		
		regi.setOnAction(e -> {
			try {
	    		Class.forName("com.mysql.jdbc.Driver");
	    		Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/dormitory?useSSL=false","root","123456");
	    		Statement sta = con.createStatement();
	    		if(newName.getText().isEmpty() || newPw.getText().isEmpty()) {
	    		// ����д���û���Ϊ��
	    			Alert alert = new Alert(AlertType.WARNING,"���벻��Ϊ�գ�");
    				alert.show();
    				// ����ָʾע��ʧ��
	    		}else {
	    				sta.executeUpdate("insert into admininfo (name, password) values "
	    						+ "('"+ newName.getText() +"','" + newPw.getText() +"')");
	    				// �������û�
	    			
	    				Alert alert = new Alert(AlertType.INFORMATION,"ע��ɹ���");
	    				alert.show();
	    				// ����ָʾע��ɹ�
 				}
	    		sta.close();
	    		con.close();
	    	} catch (SQLException | ClassNotFoundException ex) {
	    		ex.printStackTrace();
	    	}
		});
		
		hbox.getChildren().add(regi);
		//�����޸İ�ť
		
		exit.setOnAction(e->currentStage.close());
		//���exit��ť�˳��ý���
		
		hbox.getChildren().add(exit);
		//�����˳���ǰ����İ�ť
		
		return hbox;
	}
	
}
