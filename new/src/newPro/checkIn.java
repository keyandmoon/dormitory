package newPro;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class checkIn {
	BorderPane bp;
	TextField studentId = new TextField();// ѧ��
	TextField college = new TextField();// ѧԺ
	TextField classNum = new TextField();// �༶
	TextField name = new TextField();// ѧ������
	TextField departments = new TextField();// רҵ
	TextField bedNum = new TextField();// ����
	TextField ft[] = {studentId, college, classNum, name, departments, bedNum};
	Button bed1 = new Button("1");
	Button bed2 = new Button("2");
	Button bed3 = new Button("3");
	Button bed4 = new Button("4");
	int chosenBedNum = 0; // ����һ��ȫ�ֱ����Դ�ŵǼ�ʱѡ�еĴ�λ�ţ�0��ʾδѡ�д�λ
	String chosenRoom;// ��ǰ��ѡ�е����Һ�
	Button checked = null;// ���ڴ���ϴ�ѡ�����ݣ���֤ÿ�ε��ѡ��ֻ��һ����λ��ѡ��
	Label remainBedNum = new Label();// ����һ����ʾ��ǰ���ലλ���ı�ǩ
	boolean isSelectedBed1 = false;
	boolean isSelectedBed2 = false;
	boolean isSelectedBed3 = false;
	boolean isSelectedBed4 = false;
	boolean isSelectedBedNum[] = {isSelectedBed1, isSelectedBed2, isSelectedBed3, isSelectedBed4};
	// �жϴ�λ�Ƿ�������ס
	
	public checkIn(BorderPane bp,String cr) {
		this.bp = bp;
		this.chosenRoom = cr;
	}
	// ����Ĺ��캯�������ڴ���һҳ�洫�䱻ѡ�е����Һż�BorderPane

	public VBox getCheckIn() {
		VBox vbox = new VBox();
		vbox.getChildren().add(getCkinTitle());
		vbox.getChildren().add(getMiddlePane());
		vbox.getChildren().add(getBottonText());
		return vbox;
	}
//  �������������
	
	public HBox getMiddlePane() {
		HBox hbox = new HBox();
		hbox.setPadding(new Insets(35,0,0,0));
		hbox.setAlignment(Pos.CENTER);
		hbox.getChildren().add(getInformation());
		hbox.getChildren().add(getGraph());
		return hbox;
	}
//  ���˱���͵ײ����ֵ��м䲿��
	
	public HBox getCkinTitle(){
		HBox hbox = new HBox(15);
		hbox.setPadding(new Insets(30,15,30,15));
		hbox.setAlignment(Pos.CENTER);
		
		Label title = new Label("��ס�Ǽ�");
		title.setFont(Font.font("Timer New Roman",FontWeight.BOLD, FontPosture.ITALIC, 25));
		title.setAlignment(Pos.CENTER);
		title.setTextFill(Color.POWDERBLUE);
		hbox.getChildren().add(title);
		
		return hbox;
	}
//  ��ס����
	
	public GridPane getInformation() {
		GridPane pane = new GridPane();
		pane.setAlignment(Pos.BASELINE_LEFT);
		pane.setPadding(new Insets(0,30,0,30));
		pane.setHgap(20);
		pane.setVgap(20);
		
		pane.add(new Label("ѧ��"), 0, 0);
		pane.add(new Label("ѧԺ"), 0, 1);
		pane.add(new Label("�༶"), 0, 2);
		
		pane.add(studentId, 1, 0);
		pane.add(college, 1, 1);
		pane.add(classNum, 1, 2);
		
		pane.add(new Label("����"), 2, 0);
		pane.add(new Label("ϵ��"), 2, 1);
		pane.add(new Label("����"), 2, 2);
		
		pane.add(name, 3, 0);
		pane.add(departments, 3, 1);
		pane.add(bedNum, 3, 2);
		
		for(int i = 0;i < 6; i++) {
			ft[i].setPrefWidth(120);
			// ��ÿ���ı�������ͬ���Ŀ��
			
			if(i != 0) {
				ft[i].setText("");
				ft[i].setEditable(false);
			}
			// ������ѧ��������ı������ò��ɸ�������
		}
		
		pane.add(getReturnButton(), 3, 3);
		return pane;
	}
//  ��ȡѧ����ס�Ǽ�����ĸ�����Ϣ
	
    public Pane getGraph() {
    	Pane balconyAndBed = new Pane();
    	
    	
    	
    	Label text = new Label("���䲼��ʾ��ͼ");
    	text.setLayoutX(12.5);
    	text.setLayoutY(150);
    	
    	Label balcony = new Label("��̨");
    	balcony.setStyle("-fx-background-color:#66ccff");
    	balcony.setTextFill(Color.BLACK);
    	balcony.setAlignment(Pos.CENTER);
    	balcony.setPrefHeight(25);
    	balcony.setPrefWidth(107);
    	balcony.setLayoutX(0);
    	balcony.setLayoutY(0);
    	
    	// ���Ƶ�ʱ���жϸô��Ƿ�������ס
    	isSeleBed(chosenRoom);
    	if(isSelectedBed1)
    		bed1.setStyle("-fx-background-color:LIGHTPINK;-fx-border-color:LIGHTPINK; -fx-border-width:3");
    	else
    		bed1.setStyle("-fx-background-color:LIGHTBLUE;-fx-border-color:LIGHTBLUE; -fx-border-width:3");
    	
    	bed1.setPrefHeight(57);
    	bed1.setPrefWidth(50);
    	bed1.setLayoutX(0);
    	bed1.setLayoutY(30);
    	bed1.setOnAction(e->{
    		isSeleBed(chosenRoom);
    		// ��ȡ�ô�λ�Ƿ�������ס
    		chosenBedNum = 1;
    		if(!isSelectedBed1) {
    		// �����ǰ��λ������ס
    			if (checked == null) {
    			// ���֮ǰδ�������λ
    				
    				bed1.setStyle("-fx-background-color:LIGHTBLUE;-fx-border-color:YELLOW; -fx-border-width:3");
    				checked = bed1;
    				bedNum.setText(String.valueOf(chosenBedNum));
    			}
    			else {
				// ���֮ǰ�������λ
    				
    				if (checked.getText().equals(bed1.getText())) {
    					bed1.setStyle("-fx-background-color:LIGHTBLUE;-fx-border-color:LIGHTBLUE; -fx-border-width:3");
    					bedNum.setText("");
    					checked = null;
    				}
    				// ���֮ǰ����Ĵ�λ�͵�ǰһ��
    				
    				else {
    					if(isSelectedBedNum[Integer.parseInt(checked.getText()) - 1]) {
    						// ���֮ǰ����Ĵ�λ������ס
    						checked.setStyle("-fx-background-color:LIGHTPINK;-fx-border-color:LIGHTPINK; -fx-border-width:3");
    						bed1.setStyle("-fx-background-color:LIGHTBLUE;-fx-border-color:YELLOW;-fx-border-width:3");
    						checked = bed1;
    						bedNum.setText(String.valueOf(chosenBedNum));
    						
    					}else {
    						// ���֮ǰ����Ĵ�λ������ס
    						checked.setStyle("-fx-background-color:LIGHTBLUE;-fx-border-color:LIGHTBLUE; -fx-border-width:3");
    						bed1.setStyle("-fx-background-color:LIGHTBLUE;-fx-border-color:YELLOW;-fx-border-width:3");
    						checked = bed1;
    						bedNum.setText(String.valueOf(chosenBedNum));
    					}
    				}// ���֮ǰ����Ĵ�λ�͵�ǰ��һ��
    			}
    		}
    		else {
    		// �����ǰ��λ������ס
    			if (checked == null) {
    			// ���֮ǰδ�������λ
    				bed1.setStyle("-fx-background-color:LIGHTPINK;-fx-border-color:YELLOW; -fx-border-width:3");
    				checked = bed1;
    				bedNum.setText(String.valueOf(chosenBedNum));
    			}
    			else {
				// ���֮ǰ�������λ
    				if (checked.getText().equals(bed1.getText())) {
    					bed1.setStyle("-fx-background-color:LIGHTPINK;-fx-border-color:LIGHTPINK; -fx-border-width:3");
    					bedNum.setText("");
    					checked = null;
    				}
    				// ���֮ǰ����Ĵ�λ�͵�ǰһ��
    				else {
    					if(isSelectedBedNum[Integer.parseInt(checked.getText()) - 1]) {
    					// ���֮ǰ����Ĵ�λ������ס
    						checked.setStyle("-fx-background-color:LIGHTPINK;-fx-border-color:LIGHTPINK; -fx-border-width:3");
    						bed1.setStyle("-fx-background-color:LIGHTPINK;-fx-border-color:YELLOW;-fx-border-width:3");
    						checked = bed1;
    						bedNum.setText(String.valueOf(chosenBedNum));
    					}else {
    					// ���֮ǰ����Ĵ�λ������ס
    						
    						checked.setStyle("-fx-background-color:LIGHTBLUE;-fx-border-color:LIGHTBLUE; -fx-border-width:3");
    						bed1.setStyle("-fx-background-color:LIGHTPINK;-fx-border-color:YELLOW;-fx-border-width:3");
    						checked = bed1;
    						bedNum.setText(String.valueOf(chosenBedNum));
    					}
    				}
    				// ���֮ǰ����Ĵ�λ�͵�ǰ��һ��
    			}
    		}
    	});
    	
    	isSeleBed(chosenRoom);
    	if(isSelectedBed2)
    		bed2.setStyle("-fx-background-color:LIGHTPINK;-fx-border-color:LIGHTPINK; -fx-border-width:3");
    	else
    		bed2.setStyle("-fx-background-color:LIGHTBLUE;-fx-border-color:LIGHTBLUE; -fx-border-width:3");
    	
    	bed2.setPrefHeight(57);
    	bed2.setPrefWidth(50);
    	bed2.setLayoutX(0);
    	bed2.setLayoutY(90);
    	bed2.setOnAction(e->{
    		chosenBedNum = 2;
    		isSeleBed(chosenRoom);
    		if(!isSelectedBed2) {
        		// �����ǰ��λ������ס
        			if (checked == null) {
        			// ���֮ǰδ�������λ
        				bed2.setStyle("-fx-background-color:LIGHTBLUE;-fx-border-color:YELLOW; -fx-border-width:3");
        				checked = bed2;
        				bedNum.setText(String.valueOf(chosenBedNum));
        			}
        			else {
    				// ���֮ǰ�������λ
        				if (checked.getText().equals(bed2.getText())) {
        					bed2.setStyle("-fx-background-color:LIGHTBLUE;-fx-border-color:LIGHTBLUE; -fx-border-width:3");
        					bedNum.setText("");
        					checked = null;
        				}
        				// ���֮ǰ����Ĵ�λ�͵�ǰһ��
        				else {
        					if(isSelectedBedNum[Integer.parseInt(checked.getText()) - 1]) {
        						// ���֮ǰ����Ĵ�λ������ס
        						checked.setStyle("-fx-background-color:LIGHTPINK;-fx-border-color:LIGHTPINK; -fx-border-width:3");
        						bed2.setStyle("-fx-background-color:LIGHTBLUE;-fx-border-color:YELLOW;-fx-border-width:3");
        						checked = bed2;
        						bedNum.setText(String.valueOf(chosenBedNum));
        						
        					}else {
        						// ���֮ǰ����Ĵ�λ������ס
        						checked.setStyle("-fx-background-color:LIGHTBLUE;-fx-border-color:LIGHTBLUE; -fx-border-width:3");
        						bed2.setStyle("-fx-background-color:LIGHTBLUE;-fx-border-color:YELLOW;-fx-border-width:3");
        						checked = bed2;
        						bedNum.setText(String.valueOf(chosenBedNum));
        					}
        				}
        				// ���֮ǰ����Ĵ�λ�͵�ǰ��һ��
        			}
        		}
        		else {
        		// �����ǰ��λ������ס
        			if (checked == null) {
        			// ���֮ǰδ�������λ
        				bed2.setStyle("-fx-background-color:LIGHTPINK;-fx-border-color:YELLOW; -fx-border-width:3");
        				checked = bed2;
        				bedNum.setText(String.valueOf(chosenBedNum));
        			}
        			else {
    				// ���֮ǰ�������λ
        				if (checked.getText().equals(bed2.getText())) {
        					bed2.setStyle("-fx-background-color:LIGHTPINK;-fx-border-color:LIGHTPINK; -fx-border-width:3");
        					bedNum.setText("");
        					checked = null;
        				}
        				// ���֮ǰ����Ĵ�λ�͵�ǰһ��
        				else {
        					if(isSelectedBedNum[Integer.parseInt(checked.getText()) - 1]) {
            					// ���֮ǰ����Ĵ�λ������ס
            						checked.setStyle("-fx-background-color:LIGHTPINK;-fx-border-color:LIGHTPINK; -fx-border-width:3");
            						bed2.setStyle("-fx-background-color:LIGHTPINK;-fx-border-color:YELLOW;-fx-border-width:3");
            						checked = bed2;
            						bedNum.setText(String.valueOf(chosenBedNum));
            					}else {
            					// ���֮ǰ����Ĵ�λ������ס
            						
            						checked.setStyle("-fx-background-color:LIGHTBLUE;-fx-border-color:LIGHTBLUE; -fx-border-width:3");
            						bed2.setStyle("-fx-background-color:LIGHTPINK;-fx-border-color:YELLOW;-fx-border-width:3");
            						checked = bed2;
            						bedNum.setText(String.valueOf(chosenBedNum));
            					}
        				}
        				// ���֮ǰ����Ĵ�λ�͵�ǰ��һ��
        			}
        		}
    	});
    	
    	// ���Ƶ�ʱ���жϸô��Ƿ�������ס
    	isSeleBed(chosenRoom);
    	if(isSelectedBed3)
    		bed3.setStyle("-fx-background-color:LIGHTPINK;-fx-border-color:LIGHTPINK; -fx-border-width:3");
    	else
    		bed3.setStyle("-fx-background-color:LIGHTBLUE;-fx-border-color:LIGHTBLUE; -fx-border-width:3");
    	
    	bed3.setPrefHeight(57);
    	bed3.setPrefWidth(50);
    	bed3.setLayoutX(57);
    	bed3.setLayoutY(30);
    	bed3.setOnAction(e->{
    		chosenBedNum = 3;
    		isSeleBed(chosenRoom);
    		if(!isSelectedBed3) {
        		// �����ǰ��λ������ס
        			if (checked == null) {
        			// ���֮ǰδ�������λ
        				bed3.setStyle("-fx-background-color:LIGHTBLUE;-fx-border-color:YELLOW; -fx-border-width:3");
        				checked = bed3;
        				bedNum.setText(String.valueOf(chosenBedNum));
        			}
        			else {
    				// ���֮ǰ�������λ
        				if (checked.getText().equals(bed3.getText())) {
        					bed3.setStyle("-fx-background-color:LIGHTBLUE;-fx-border-color:LIGHTBLUE; -fx-border-width:3");
        					bedNum.setText("");
        					checked = null;
        				}
        				// ���֮ǰ����Ĵ�λ�͵�ǰһ��
        				else {
        					if(isSelectedBedNum[Integer.parseInt(checked.getText()) - 1]) {
        						// ���֮ǰ����Ĵ�λ������ס
        						checked.setStyle("-fx-background-color:LIGHTPINK;-fx-border-color:LIGHTPINK; -fx-border-width:3");
        						bed3.setStyle("-fx-background-color:LIGHTBLUE;-fx-border-color:YELLOW;-fx-border-width:3");
        						checked = bed3;
        						bedNum.setText(String.valueOf(chosenBedNum));
        						
        					}else {
        						// ���֮ǰ����Ĵ�λ������ס
        						checked.setStyle("-fx-background-color:LIGHTBLUE;-fx-border-color:LIGHTBLUE; -fx-border-width:3");
        						bed3.setStyle("-fx-background-color:LIGHTBLUE;-fx-border-color:YELLOW;-fx-border-width:3");
        						checked = bed3;
        						bedNum.setText(String.valueOf(chosenBedNum));
        					}
        				}
        				// ���֮ǰ����Ĵ�λ�͵�ǰ��һ��
        			}
        		}
        		else {
        		// �����ǰ��λ������ס
        			if (checked == null) {
        			// ���֮ǰδ�������λ
        				bed3.setStyle("-fx-background-color:LIGHTPINK;-fx-border-color:YELLOW; -fx-border-width:3");
        				checked = bed3;
        				bedNum.setText(String.valueOf(chosenBedNum));
        			}
        			else {
    				// ���֮ǰ�������λ
        				if (checked.getText().equals(bed3.getText())) {
        					bed3.setStyle("-fx-background-color:LIGHTPINK;-fx-border-color:LIGHTPINK; -fx-border-width:3");
        					bedNum.setText("");
        					checked = null;
        				}
        				// ���֮ǰ����Ĵ�λ�͵�ǰһ��
        				else {
        					if(isSelectedBedNum[Integer.parseInt(checked.getText()) - 1]) {
            					// ���֮ǰ����Ĵ�λ������ס
            						checked.setStyle("-fx-background-color:LIGHTPINK;-fx-border-color:LIGHTPINK; -fx-border-width:3");
            						bed3.setStyle("-fx-background-color:LIGHTPINK;-fx-border-color:YELLOW;-fx-border-width:3");
            						checked = bed3;
            						bedNum.setText(String.valueOf(chosenBedNum));
            					}else {
            					// ���֮ǰ����Ĵ�λ������ס
            						
            						checked.setStyle("-fx-background-color:LIGHTBLUE;-fx-border-color:LIGHTBLUE; -fx-border-width:3");
            						bed3.setStyle("-fx-background-color:LIGHTPINK;-fx-border-color:YELLOW;-fx-border-width:3");
            						checked = bed3;
            						bedNum.setText(String.valueOf(chosenBedNum));
            					}
        				}
        				// ���֮ǰ����Ĵ�λ�͵�ǰ��һ��
        			}
        		}
    	});
    	
    	// ���Ƶ�ʱ���жϸô��Ƿ�������ס
    	isSeleBed(chosenRoom);
    	if(isSelectedBed4)
    		bed4.setStyle("-fx-background-color:LIGHTPINK;-fx-border-color:LIGHTPINK; -fx-border-width:3");
    	else
    		bed4.setStyle("-fx-background-color:LIGHTBLUE;-fx-border-color:LIGHTBLUE; -fx-border-width:3");
    	
    	bed4.setPrefHeight(57);
    	bed4.setPrefWidth(50);
    	bed4.setLayoutX(57);
    	bed4.setLayoutY(90);
    	bed4.setOnAction(e->{
    		chosenBedNum = 4;
    		isSeleBed(chosenRoom);
    		if(!isSelectedBed4) {
        		// �����ǰ��λ������ס
        			if (checked == null) {
        			// ���֮ǰδ�������λ
        				bed4.setStyle("-fx-background-color:LIGHTBLUE;-fx-border-color:YELLOW; -fx-border-width:3");
        				checked = bed4;
        				bedNum.setText(String.valueOf(chosenBedNum));
        			}
        			else {
    				// ���֮ǰ�������λ
        				if (checked.getText().equals(bed4.getText())) {
        					bed4.setStyle("-fx-background-color:LIGHTBLUE;-fx-border-color:LIGHTBLUE; -fx-border-width:3");
        					bedNum.setText("");
        					checked = null;
        				}
        				// ���֮ǰ����Ĵ�λ�͵�ǰһ��
        				else {
        					if(isSelectedBedNum[Integer.parseInt(checked.getText()) - 1]) {
        						// ���֮ǰ����Ĵ�λ������ס
        						checked.setStyle("-fx-background-color:LIGHTPINK;-fx-border-color:LIGHTPINK; -fx-border-width:3");
        						bed4.setStyle("-fx-background-color:LIGHTBLUE;-fx-border-color:YELLOW;-fx-border-width:3");
        						checked = bed4;
        						bedNum.setText(String.valueOf(chosenBedNum));
        						
        					}else {
        						// ���֮ǰ����Ĵ�λ������ס
        						checked.setStyle("-fx-background-color:LIGHTBLUE;-fx-border-color:LIGHTBLUE; -fx-border-width:3");
        						bed4.setStyle("-fx-background-color:LIGHTBLUE;-fx-border-color:YELLOW;-fx-border-width:3");
        						checked = bed4;
        						bedNum.setText(String.valueOf(chosenBedNum));
        					}
        				}
        				// ���֮ǰ����Ĵ�λ�͵�ǰ��һ��
        			}
        		}
        		else {
        		// �����ǰ��λ������ס
        			if (checked == null) {
        			// ���֮ǰδ�������λ
        				bed4.setStyle("-fx-background-color:LIGHTPINK;-fx-border-color:YELLOW; -fx-border-width:3");
        				checked = bed4;
        				bedNum.setText(String.valueOf(chosenBedNum));
        			}
        			else {
    				// ���֮ǰ�������λ
        				if (checked.getText().equals(bed4.getText())) {
        					bed4.setStyle("-fx-background-color:LIGHTPINK;-fx-border-color:LIGHTPINK; -fx-border-width:3");
        					bedNum.setText("");
        					checked = null;
        				}
        				// ���֮ǰ����Ĵ�λ�͵�ǰһ��
        				else {
        					if(isSelectedBedNum[Integer.parseInt(checked.getText()) - 1]) {
            					// ���֮ǰ����Ĵ�λ������ס
            						checked.setStyle("-fx-background-color:LIGHTPINK;-fx-border-color:LIGHTPINK; -fx-border-width:3");
            						bed4.setStyle("-fx-background-color:LIGHTPINK;-fx-border-color:YELLOW;-fx-border-width:3");
            						checked = bed4;
            						bedNum.setText(String.valueOf(chosenBedNum));
            					}else {
            					// ���֮ǰ����Ĵ�λ������ס
            						
            						checked.setStyle("-fx-background-color:LIGHTBLUE;-fx-border-color:LIGHTBLUE; -fx-border-width:3");
            						bed4.setStyle("-fx-background-color:LIGHTPINK;-fx-border-color:YELLOW;-fx-border-width:3");
            						checked = bed4;
            						bedNum.setText(String.valueOf(chosenBedNum));
            					}
        				}
        				// ���֮ǰ����Ĵ�λ�͵�ǰ��һ��
        			}
        		}
    	});
    	
    	Rectangle isNotUsedRec = new Rectangle(0,0,15,10);
    	isNotUsedRec.setFill(Color.LIGHTBLUE);
    	isNotUsedRec.setLayoutX(117);
    	isNotUsedRec.setLayoutY(0);
		
    	Label isNotUsedText = new Label("δʹ��");
    	isNotUsedText.setLayoutX(133);
        isNotUsedText.setLayoutY(0);
    
		Rectangle isUsedRec = new Rectangle(0,0,15,10);
		isUsedRec.setFill(Color.LIGHTPINK);
		isUsedRec.setLayoutX(117);
		isUsedRec.setLayoutY(20);
		
    	Label isUsedText = new Label("��ʹ��");
    	isUsedText.setLayoutX(133);
    	isUsedText.setLayoutY(20);
		
    	balconyAndBed.getChildren().addAll(balcony,bed1,bed2,bed3,bed4,
    			text,isNotUsedRec,isUsedRec,isNotUsedText,isUsedText);
       
    	
    	
    	return balconyAndBed;
    }
//  ���䲼��ʾ��ͼ
	
	public HBox getReturnButton() {
		HBox hbox = new HBox(15);
		hbox.setAlignment(Pos.CENTER);
		
		Button DetermineTheCheckIn = new Button("ȷ����ס");
		DetermineTheCheckIn.setOnAction(e -> {
			if(studentId.getText().isEmpty()) {
				
				Alert warningWindows = new Alert(AlertType.ERROR,"ѧ�Ų���Ϊ�գ�");
				warningWindows.show();
				// ��ѧ������Ϊ�յ�����ʾ
				
				studentId.setText("");
				// �������ı���Ϣ������û���������
				
			}else if(bedNum.getText().isEmpty()) {
				
				Alert warning = new Alert(AlertType.ERROR,"��ѡ��λ��");
				warning.show();
				// ����λδѡ�񵯳���ʾ
				
			}else if(isSelectedBedNum[chosenBedNum - 1]) {
				
				isSeleBed(chosenRoom);
				Alert warning = new Alert(AlertType.ERROR,"�ô�λ��������ס��");
				warning.show();
				// ����λ��������ס�򾯸�
				
			}
			else {
				try {
					
					Class.forName("com.mysql.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/dormitory?useSSL=false","root","123456");
					Statement sta = con.createStatement();
					// �������ݿ�
					
					String allInfo = "select * from studentinfo where stuId=" + studentId.getText();
					ResultSet rs = sta.executeQuery(allInfo);
					// ��ȡstudentinfo���������н��������һ���������
					
					if(rs.next() && studentId.getText().equals(rs.getString("stuId"))) {
						if(rs.getString("isCheckIn").equals("0")) { 
							college.setText(rs.getString("colleget"));
							classNum.setText(rs.getString("class"));
							name.setText(rs.getString("stuName"));
							departments.setText(rs.getString("department"));
							// ��������ڸ��ˣ����Զ�����ѧ����Ϣ
							
							sta.executeUpdate("UPDATE studentinfo SET isCheckin=1 WHERE stuId=" + studentId.getText());
							sta.executeUpdate("UPDATE roominfo SET bed" + chosenBedNum + "= '"+ studentId.getText() +"'  WHERE roomID=" + chosenRoom);
							// ���µ��������
							
							Alert alert = new Alert(AlertType.INFORMATION,"��ס�ɹ���");
							alert.show();
							// ������ʾ��ס�ɹ�
							
							remainBedNum.setText("ʣ��Ĵ�λ�У�" + String.valueOf(getRemainBed(chosenRoom)));
							// �ǼǺ���������ʣ�ലλ
			  
							
						}else {
							Alert alert = new Alert(AlertType.WARNING,"��ѧ������ס��");
							alert.show();
							// ������ʾ
							
							for(int i = 0;i < 5;i++) ft[i].setText("");
							//��������Ϣ���
						}
						
						rs.close();
						sta.close();
						con.close();
					}else {
						Alert alert = new Alert(AlertType.ERROR,"��ѧ�������ڣ�����ѧ�Ų��������룡");
						alert.show();
						// ������ʾѧ���������
							
						for(int i = 0;i < 5;i++) ft[i].setText("");
						//��������Ϣ���
					}
					
					
					
				} catch (SQLException | ClassNotFoundException ex) {
					ex.printStackTrace();
				}
			}
		});
		hbox.getChildren().add(DetermineTheCheckIn);
		
		Button returnTheMain = new Button("������ҳ");
		returnTheMain.setOnAction(e -> {
			bp.setCenter(new chooseDormitory(bp).getChoDorInterface());
		});
		// ����ѡ�����ҵ�������
		
		hbox.getChildren().add(returnTheMain);
		return hbox;
	}
//  �ײ���ȷ����ס���롰������ҳ����������ť
	
	public HBox getBottonText() {
		HBox hbox = new HBox(15);
		hbox.setPadding(new Insets(35,15,30,15));
		hbox.setAlignment(Pos.CENTER);
		
		hbox.getChildren().add(new Label("ѡ��������ǣ�" + chosenRoom));
		remainBedNum.setText("ʣ��Ĵ�λ�У�" + String.valueOf(getRemainBed(chosenRoom)));
		hbox.getChildren().add(remainBedNum);
		
		return hbox;
    }
//  ҳ��ײ���ʾ��ѡ��������ല��

	public int getRemainBed(String chosenRoom) {
        int remainBed = 0;// ����һ�������Դ��ʣ�ലλ
 		try {
     		Class.forName("com.mysql.jdbc.Driver");
     		Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/dormitory?useSSL=false","root","123456");
     		Statement sta = con.createStatement();
     		String s = "select * from roominfo WHERE roomId =" + chosenRoom;// ��ȡ�����ҵ�ǰ��λʣ�����
     		ResultSet rs = sta.executeQuery(s);
     		if(rs.next()) {
     			if(rs.getString("bed1").equals("��")) 
     				remainBed ++; // ����ǰ��λ���ˣ���ʣ��������1
     			if(rs.getString("bed2").equals("��"))
     				remainBed ++;
     			if(rs.getString("bed3").equals("��"))
     				remainBed ++;
     			if(rs.getString("bed4").equals("��"))
     				remainBed ++;
     		}
     		rs.close();
     		sta.close();
     		con.close();
 		} catch (SQLException | ClassNotFoundException ex) {
     		ex.printStackTrace();
     	}
 		return remainBed;
    }
//  ��ȡ��ǰʣ�ലλ��
	
	public void isSeleBed(String chosenRoom) {
		try {
     		Class.forName("com.mysql.jdbc.Driver");
     		Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/dormitory?useSSL=false","root","123456");
     		Statement sta = con.createStatement();
     		String s = "select * from roominfo WHERE roomId =" + chosenRoom;// ��ȡ�����ҵ�ǰ��λʣ�����
     		ResultSet rs = sta.executeQuery(s);
     		if(rs.next()) {
     			if(!rs.getString("bed1").equals("��"))
     				isSelectedBed1 = true;// ����ǰ��λ���ˣ�����Ϊtrue��������Ϊfalse
     			else
     				isSelectedBed1 = false;
     			if(!rs.getString("bed2").equals("��"))
     				isSelectedBed2 = true;
     			else
     				isSelectedBed2 = false;
     			if(!rs.getString("bed3").equals("��"))
     				isSelectedBed3 = true;
     			else
     				isSelectedBed3 = false;
     			if(!rs.getString("bed4").equals("��"))
     				isSelectedBed4 = true;
     			else
     				isSelectedBed4 = false;
     		}
     		rs.close();
     		sta.close();
     		con.close();
 		} catch (SQLException | ClassNotFoundException ex) {
     		ex.printStackTrace();
     	}
	}
}