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
	TextField former = new TextField();// ԭ�����û���
	TextField newAd = new TextField();// ���û���
	
	public modifyAdmin (String admin) {
		this.admin = admin;
	}
	
	public void start(Stage primaryStage){
		Scene scene = new Scene(getAllInfo(),300,150);
		primaryStage.setScene(scene);
		primaryStage.setTitle("�޸��û���");
		currentStage = primaryStage;
		primaryStage.show();
	}
	
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
		
		hbox.getChildren().add(new Label("������ԭ�û�����"));
		hbox.getChildren().add(former);
		//����ԭ�û��������
	
		return hbox;
	}
	
	public HBox getNew() {
		HBox hbox = new HBox();
		
		hbox.setAlignment(Pos.CENTER);
		hbox.setPadding(new Insets(10,10,10,10));
		
		hbox.getChildren().add(new Label("���������û�����"));
		hbox.getChildren().add(newAd);
		//�������û��������
		
		return hbox;
	}
	
	public HBox getFix() {
		HBox hbox = new HBox(10);
		
		hbox.setAlignment(Pos.CENTER);
		hbox.setPadding(new Insets(10,0,10,0));
		
		Button fix = new Button("�޸�");
		Button exit = new Button("�˳�");
		
		fix.setOnAction(e -> {
			try {
	    		Class.forName("com.mysql.jdbc.Driver");
	    		Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/dormitory?useSSL=false","root","123456");
	    		Statement sta = con.createStatement();
	    		if(former.getText().isEmpty() || newAd.getText().isEmpty()) {
	    		// ����д���û���Ϊ��
	    			Alert alert = new Alert(AlertType.WARNING,"�û�������Ϊ�գ�");
    				alert.show();
    				// ����ָʾ�޸�ʧ��
	    		}else {
	    			if(admin.equals(former.getText())) {
	    				// ��ԭ�û����뵱ǰ�û���һ��
	    				
	    				sta.executeUpdate("UPDATE admininfo SET name='" + newAd.getText() 
	    				+"' WHERE name='" + admin + "'");
	    				// ���ĵ�ǰ�û���
	    			
	    				Alert alert = new Alert(AlertType.INFORMATION,"�޸ĳɹ��������µ�½��");
	    				alert.show();
	    				// ����ָʾ�޸ĳɹ������µ�½
 					
	    				System.exit(0);
	    				// ���µ�½
	    			}else {
	    				Alert alert = new Alert(AlertType.ERROR,"�û��������޸�ʧ�ܣ�");
	    				alert.show();
	    				// ����ָʾ�޸�ʧ��
	    				
	    				former.setText("");
	    				newAd.setText("");
	    				// ����ı���
	    			}
	    		}
	    		sta.close();
	    		con.close();
	    	} catch (SQLException | ClassNotFoundException ex) {
	    		ex.printStackTrace();
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
