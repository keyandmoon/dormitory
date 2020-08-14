package newPro;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class checkout {
	BorderPane bp;
	String chosenRoom;
	// ���캯���Ĳ�����Ϊ��һҳ�洫���BorderPane��ѡ������Һ�
	TextField studentId = new TextField();
	// ��Ҫ�˷���ѧ��
	ChoiceBox<String> chooseBed = new ChoiceBox<>();
	// ����ѡ�񴲺ŵ������˵�
	String[] BedNum = {"bed1","bed2","bed3","bed4"};
	// �ĸ�����
	
	public checkout(BorderPane bp,String cr) {
		this.bp = bp;
		this.chosenRoom = cr;
	}
	// ����Ĺ��캯����������һҳ�洫������Һ�
	
	public VBox getCheckout() {
		VBox vbox = new VBox(15);
		vbox.setPadding(new Insets(30,15,30,15));
		// �������¼��30������15
		
		vbox.getChildren().addAll(getCkouTitle(), getInputInfo(),
				getChoiceBox(), checkButton());
		return vbox;
	}
	// ��ȡ��������
	
	public HBox getCkouTitle(){
		HBox hbox = new HBox(15);
		hbox.setPadding(new Insets(30,15,20,15));
		hbox.setAlignment(Pos.CENTER);
		
		Label title = new Label("�˷��Ǽ�");
		title.setFont(Font.font("Timer New Roman",FontWeight.BOLD, FontPosture.ITALIC, 25));
		title.setAlignment(Pos.CENTER);
		title.setTextFill(Color.POWDERBLUE);
		hbox.getChildren().add(title);
		
		return hbox;
	}
//  �˷�����
	
	
	public HBox getInputInfo() {
		HBox hbox = new HBox(15);
		
		hbox.setPadding(new Insets(15,15,15,15));
		//��������������
		
		hbox.setAlignment(Pos.CENTER);
		// ���������ı�����
		
		Label student = new Label("������ѧ�ţ�");
		studentId.setPrefWidth(75);
		hbox.getChildren().addAll(student,studentId);
		// ���ı������ǩ����hbox
		
		return hbox;
	}
	// ��ȡѧ�ŵ�������Ϣ
	
	public ChoiceBox<String> getBedNum() {
		chooseBed.setTooltip(new Tooltip("��ѡ������Ҫ��ѡ�Ĵ�λ"));
		// ��������ƶ����˴�����ʾ
		chooseBed.setItems(FXCollections.observableArrayList("bed1","bed2","bed3","bed4"));
		//��������ѡ��Ϊ�ĸ�����
		return chooseBed;
	}
	// �����˵��Ļ�ȡ
	
	public HBox getChoiceBox() {
		HBox hbox = new HBox(15);
		
		hbox.setAlignment(Pos.CENTER);
		// ���þ���
		
		hbox.setPadding(new Insets(5,15,15,15));
		
		hbox.getChildren().addAll(new Label("��ѡ��λ�ţ�"),getBedNum());
		return hbox;
	}
	// ��ȡ�˵�����ʾ��ǩ
	
	public HBox checkButton() {
		HBox hbox = new HBox(15);
		
		hbox.setAlignment(Pos.CENTER);
		// ���þ���
		
		hbox.setPadding(new Insets(30,15,30,15));
		
		Button DetermineTheCheckout = new Button("ȷ���˷�");
		DetermineTheCheckout.setOnAction(e -> {
		if(studentId.getText().isEmpty()) {
			Alert alert = new Alert(AlertType.WARNING,"ѧ�Ų���Ϊ�գ�");
			alert.show();
		}else {
		// ��ѧ�Ų�Ϊ��
	    	try {
	    		Class.forName("com.mysql.jdbc.Driver");
	    		Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/dormitory?useSSL=false","root","123456");
	    		Statement sta = con.createStatement();
	    		// �������ݿ�
	    		
	    		String s = "select * from roominfo WHERE roomId='" + chosenRoom + "'";
	    		// ��ȡ��ǰ������ѡ��λ
	    		ResultSet rs = sta.executeQuery(s);
	    		// ��ȡ�����
	    		
	    		if(rs.next()) {
	    			if(!rs.getString(chooseBed.getValue()).equals("��")) {
	    			// ���ô�λ������ס
	    				
	    				if(studentId.getText().equals(rs.getString(chooseBed.getValue()))){
	    				// ����ô�λ�洢��Ϣ��ȡ���ַ����뵱ǰ�����ѧ����ͬ
	    					sta.executeUpdate("UPDATE studentinfo SET isCheckin=0 WHERE stuId=" + studentId.getText());
	    					sta.executeUpdate("UPDATE roominfo SET " + chooseBed.getValue() + " = '��' WHERE roomID=" + chosenRoom);
	    					Alert alert = new Alert(AlertType.CONFIRMATION,"�˷��ɹ���");
		    				alert.show();
	    				}
	    				else {
	    					Alert alert = new Alert(AlertType.WARNING,"����д��ȷ��Ϣ��");
		    				alert.show();
		    				// �������ѧ�ź͸ô��洢�Ĳ�һ����������ʾ�˷�ʧ��
		    				
		    				studentId.setText("");
		    				// ����ı������û���������
	    				}
	    			}else {
	    				Alert alert = new Alert(AlertType.ERROR,"�ô�λΪ�գ��޷��˷���������ѡ��");
	    				alert.show();
	    				// ������ʾ�˷�ʧ��
	    				
	    				studentId.setText("");
	    				// ����ı������û���������
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
		
		Button back = new Button("������ҳ");
		back.setOnAction(e ->{
			bp.setCenter(new chooseDormitory(bp).getChoDorInterface());
		});
		// ����ѡ�����ҵ�������
		
		hbox.getChildren().addAll(DetermineTheCheckout,back);
		// ����������ť
		
		return hbox;
	}
	
	
	
}
