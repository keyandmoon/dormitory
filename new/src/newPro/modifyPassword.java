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
	// ��Login���洫������ĵ�ǰ�û���
	
	public void start(Stage primaryStage){
		Scene scene = new Scene(getAllInfo(),300,150);
		primaryStage.setScene(scene);
		primaryStage.setTitle("�޸�����");
		currentStage = primaryStage;
		primaryStage.show();
	}
	// ����һ����stage����ɲ������ٹر�
	
	public VBox getAllInfo() {
		VBox vbox = new VBox();
		vbox.setAlignment(Pos.CENTER);
		vbox.getChildren().addAll(getFormer(),getNew(),getFix());
		return vbox;
	}
	// �������пؼ���ҳ��
	
	public HBox getFormer() {
		HBox hbox = new HBox();
		
		hbox.setAlignment(Pos.CENTER);
		hbox.setPadding(new Insets(10,10,10,10));
		
		hbox.getChildren().add(new Label("������ԭ���룺"));
		hbox.getChildren().add(formerPassword);
		//����ԭ���������
	
		return hbox;
	}
	
	public HBox getNew() {
		HBox hbox = new HBox();
		
		hbox.setAlignment(Pos.CENTER);
		hbox.setPadding(new Insets(10,10,10,10));
		
		hbox.getChildren().add(new Label("�����������룺"));
		hbox.getChildren().add(newPassword);
		//���������������
		
		return hbox;
	}
	
	public HBox getFix() {
		HBox hbox = new HBox(10);
		
		hbox.setAlignment(Pos.CENTER);
		hbox.setPadding(new Insets(10,0,10,0));
		
		Button fix = new Button("ȷ���޸�");
		Button exit = new Button("�˳�");
		
		fix.setOnAction(e -> {
			if(formerPassword.getText().isEmpty() || newPassword.getText().isEmpty()) {
				Alert warning = new Alert(AlertType.WARNING,"�������벻��Ϊ�գ�");
 				warning.show();
			} else {
			// �����������벻Ϊ��
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/dormitory?useSSL=false","root","123456");
					Statement sta = con.createStatement();
					String s = "select password from admininfo WHERE name='" + admin + "'";
					// ��ȡ��ǰ�û���ԭ����
					ResultSet rs = sta.executeQuery(s);
					if(rs.next()) {
						if(rs.getString("password").equals(formerPassword.getText())) {
							// ����ǰ�û������ԭ���������ݿ��ԭ����һ��
	     				
							if(formerPassword.getText().equals(newPassword.getText())) {
								// ����ǰ�û������ԭ������������һ��
								
								Alert warning = new Alert(AlertType.WARNING,"ԭ���벻������������ͬ��");
								warning.show();
								// ��������ԭ�����������벻��һ��
		     				
								formerPassword.setText("");
								newPassword.setText("");
								//����ı������û���������
		     				
							}else {
								// ����ǰ�û������ԭ�����������벻ͬ
	     					
								sta.executeUpdate("UPDATE admininfo SET password='" + newPassword.getText() 
								+"' WHERE name='" + admin + "'");
								// ���޸�ԭ����
	     					
								Alert alert = new Alert(AlertType.INFORMATION,"�޸ĳɹ���");
								alert.show();
								// ������ʾ�����޸ĳɹ�
	     					
								currentStage.close();
								//�޸ĳɹ����˳��ý���
							}
						}
						else {
							Alert error = new Alert(AlertType.WARNING,"��������ȷԭ���룡");
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
		//�����޸İ�ť
		
		exit.setOnAction(e->currentStage.close());
		//���exit��ť�˳��ý���
		
		hbox.getChildren().add(exit);
		//�����˳���ǰ����İ�ť
		
		return hbox;
	}
}
