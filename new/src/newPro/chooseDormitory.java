package newPro;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class chooseDormitory {
	TextField chosenDormitory = new TextField();
	Button checked;// ���ڴ���ϴ�ѡ�����ݣ���֤ÿ�ε��ѡ��ֻ��һ�����ұ�ѡ��
	BorderPane bp;
    
    public chooseDormitory(BorderPane bp) {
    	this.bp = bp;
    }
    //�вι���
        
    public VBox getChoDorInterface(){
    	VBox vbox = new VBox();
    	vbox.getChildren().add(getDorTitle());
    	vbox.getChildren().add(getDormitory());
    	vbox.getChildren().add(isFullRec());
    	vbox.getChildren().add(getTheBottom());
    	return vbox;
    }
//  ѡ�����ҵ�������
    
    public HBox getDorTitle(){
		HBox hbox = new HBox(15);
		hbox.setPadding(new Insets(30,15,30,15));
		hbox.setAlignment(Pos.CENTER);
		
		Label title = new Label("�����б�");
		title.setFont(Font.font("Timer New Roman",FontWeight.BOLD, FontPosture.ITALIC, 25));
		title.setAlignment(Pos.CENTER);
		title.setTextFill(Color.POWDERBLUE);
		hbox.getChildren().add(title);
		
		return hbox;
	}
//  ��ȡ��ҳ����
    
    public GridPane getDormitory() {
		GridPane pane = new GridPane();
		int i = 1,j = 1;
		pane.setAlignment(Pos.CENTER);
		pane.setPadding(new Insets(0,30,0,30));
		pane.setHgap(10);
		pane.setVgap(10);
		
		
		for(i = 1;i <= 3;i++) {
			for(j =  1;j <= 10;j++) {
				Button number = new Button(String.valueOf(i * 100 + j));
				number.setText(String.valueOf(i * 100 + j));
				if(new checkIn(bp,number.getText()).getRemainBed(number.getText()) == 0)
					number.setStyle("-fx-background-color:LIGHTPINK;-fx-border-color:LIGHTPINK; -fx-border-width:3");
				else
					number.setStyle("-fx-background-color:LIGHTBLUE;-fx-border-color:LIGHTBLUE; -fx-border-width:3");
				// ��̬������ɫ
				
				pane.add(number, j, i);
				number.setOnAction(e->{
					if (checked == null) {
						// ֮ǰδ���������
						if(new checkIn(bp,number.getText()).getRemainBed(number.getText()) == 0) {
							number.setStyle("-fx-background-color:LIGHTPINK;-fx-border-color:white;-fx-border-width:3");
							checked = number;
							chosenDormitory.setText(number.getText());
						}
						// �����ǰ��ѡ������������ť��ɫ����Ϊ��ɫ
						else {
							number.setStyle("-fx-background-color:LIGHTBLUE;-fx-border-color:white; -fx-border-width:3");
							checked = number;
							chosenDormitory.setText(number.getText());
						}
						// �����ǰ��ѡ����δ������ť��ɫ����Ϊ��ɫ
					}
					else {
						// ֮ǰ��������Ұ�ť
						if (checked.getText().equals(number.getText())) {
							// ֮ǰ��������Һ͵��ε��������Ϊͬһ��
							if(new checkIn(bp,number.getText()).getRemainBed(number.getText()) == 0) {
								number.setStyle("-fx-background-color:LIGHTPINK;-fx-border-color:LIGHTPINK; -fx-border-width:3");
								chosenDormitory.setText("");
								checked = null;
							}
							// �����ǰ��������
							else {
								number.setStyle("-fx-background-color:LIGHTBLUE;-fx-border-color:LIGHTBLUE; -fx-border-width:3");
								chosenDormitory.setText("");
								checked = null;
							}
							// �����ǰ����δ��
						}
						else {
							// ֮ǰ����������Һ͵�ǰ��������Ҳ���ͬһ��
							if(new checkIn(bp,number.getText()).getRemainBed(number.getText()) == 0) {
								if(new checkIn(bp,checked.getText()).getRemainBed(checked.getText()) == 0)
									checked.setStyle("-fx-background-color:LIGHTPINK;-fx-border-color:LIGHTPINK; -fx-border-width:3");
								// ���֮ǰ���������������
								else 
									checked.setStyle("-fx-background-color:LIGHTBLUE;-fx-border-color:LIGHTBLUE; -fx-border-width:3");
								// ���֮ǰ�����������δ��
								number.setStyle("-fx-background-color:LIGHTPINK;-fx-border-color:white;-fx-border-width:3");
								checked = number;
								chosenDormitory.setText(number.getText());
							}
							// �����ǰ��������
							else {
								if(new checkIn(bp,checked.getText()).getRemainBed(checked.getText()) == 0)
									checked.setStyle("-fx-background-color:LIGHTPINK;-fx-border-color:LIGHTPINK; -fx-border-width:3");
								// ���֮ǰ���������������
								else 
									checked.setStyle("-fx-background-color:LIGHTBLUE;-fx-border-color:LIGHTBLUE; -fx-border-width:3");
								// ���֮ǰ�����������δ��
								number.setStyle("-fx-background-color:LIGHTBLUE;-fx-border-color:white;-fx-border-width:3");
								checked = number;
								chosenDormitory.setText(number.getText());
							}
							// �����ǰ����δ��
						}
					}
					// ��̬������ɫ
				});
			}
		}
	    return pane;
	}
//  ���ҵ�����   
    
    public HBox isFullRec(){
		HBox hbox = new HBox(15);
		hbox.setPadding(new Insets(15,15,30,15));
		hbox.setAlignment(Pos.CENTER);
	  
		Rectangle rt1 = new Rectangle(0,0,20,15);
		rt1.setFill(Color.LIGHTBLUE);
		hbox.getChildren().add(rt1);
		hbox.getChildren().add(new Label("δ��"));
		
		Rectangle rt2 = new Rectangle(0,0,20,15);
		rt2.setFill(Color.LIGHTPINK);
		hbox.getChildren().add(rt2);
		hbox.getChildren().add(new Label("����"));
		
		return hbox;
	}
//  ��ʾ������δ����ͬ��ɫ��ʶ��С����
    
    public HBox getTheBottom(){
		HBox hbox = new HBox(15);
		hbox.setPadding(new Insets(15,15,30,15));
		hbox.setAlignment(Pos.CENTER);
//      ����hbox��λ��
		
		hbox.getChildren().add(new Label("ѡ���������:"));
//      �������ֱ�ǩ		
		
		hbox.getChildren().add(chosenDormitory);
		chosenDormitory.setEditable(false);
//      ������ʾ���ҺŵĲ����޸��ı���
		
		Button bt1 = new Button("������ס");
		bt1.setOnAction(e -> {
			if(chosenDormitory.getText().isEmpty()) {
				Alert warning = new Alert(AlertType.WARNING,"��ѡ�����ң�");
				warning.show();
				// ��δѡ�����ң��򵯳���ʾ��
			} else {
			    bp.setCenter(new checkIn(bp, chosenDormitory.getText()).getCheckIn() );
			}
		});
//      ��������ס����ť�ĵ���¼�
		
		Button bt2 = new Button("�����˷�");
		bt2.setOnAction(e -> {
			if(chosenDormitory.getText().isEmpty()) {
				Alert warning = new Alert(AlertType.WARNING,"��ѡ�����ң�");
				warning.show();
				// ��δѡ�����ң��򵯳���ʾ��
			}else {
				bp.setCenter(new checkout(bp,chosenDormitory.getText()).getCheckout());
			}
		});
//      �������˷�����ť�ĵ���¼�
		
		hbox.getChildren().addAll(bt1, bt2);
		return hbox;
	}
}

