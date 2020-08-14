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
	Label label = new Label("������ѧ��:");
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
		primaryStage.setTitle("ɾ��ѧ����Ϣ");
		currentStage = primaryStage;
		primaryStage.show();
	}

	//���������㼯һ��
	public VBox getResult() {
		VBox vbox = new VBox();
		vbox.getChildren().add(getStudentID());
		vbox.getChildren().add(getOption());
		vbox.getChildren().add(borderPane);
		return vbox;
	}
	
	//�������ֵı�ǩ���ı���
	public HBox getStudentID() {
		HBox hbox = new HBox(15);
		hbox.setPadding(new Insets(30,10,10,10));
		
		hbox.setAlignment(Pos.CENTER);
		hbox.getChildren().addAll(label, input);

		return hbox;
	}
	
	//ȷ�����˳���ť
	public HBox getOption() {
		HBox hbox = new HBox(10);
		hbox.setAlignment(Pos.CENTER);
		hbox.setPadding(new Insets(15,0,10,0));

		Button exit = new Button("�˳�");
		Button confirm = new Button("ȷ��");
		
		
		//���ȷ���������ݿ���в�ѯ����ѯ���ͨ��DataBase���룩
		confirm.setOnAction(e->{
			//��������Ϊ��ʱ������ʾ
			if(input.getText().isEmpty()) {
				Alert warningWindows = new Alert(AlertType.ERROR,"���벻��Ϊ�գ�");
				warningWindows.show();
			}
			else {
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/dormitory?useSSL=false","root","123456");
					Statement sta = con.createStatement();
					System.out.print("����ɹ���");
					
					//�Ȳ�ѯ����������Ƿ���������ݿ���
					String StuIdtQuery = "select * from studentinfo where stuId = '"+input.getText()+"'";
			    	ResultSet rs = sta.executeQuery(StuIdtQuery);
			    	//�������Ϊ�ձ�ʾ���ݴ������ݿ��У�����ɾ������
			    	if(rs.next()&&rs.getString(1) != null) {
			    		mydata.Delete(input.getText());
			    		Alert infoWindows = new Alert(AlertType.INFORMATION, "ɾ���ɹ���");
			    		infoWindows.show();
			    	}
			    	//�����Ϊ���������������������ݿ��в����ڣ���������
			    	else {
			    		Alert warningWindows = new Alert(AlertType.INFORMATION, "ɾ��ʧ�ܣ�");
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
	
		//���exit��ť�˳��ý���
		exit.setOnAction(e->currentStage.close());
		hbox.getChildren().add(confirm);
		hbox.getChildren().add(exit);
		
		return hbox;
	}
	

}
