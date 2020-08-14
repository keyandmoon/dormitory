package newPro;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Add {
	Stage currentStage;
	String admin;
    DataBase mydata = new DataBase(); 

    //�����ǩ�������
    Label stuld = new Label("ѧ��");
    TextField Stuld = new TextField();
    Label stuName = new Label("����");
    TextField StuName = new TextField();
    Label college = new Label("ѧԺ");
    TextField College = new TextField();
    Label major = new Label("רҵ");
    TextField Major = new TextField();
    Label theclass = new Label("�༶");
    TextField Theclass = new TextField();
    Label isCheckIn = new Label("�Ƿ���ס");
    TextField CheckIn = new TextField();
    
	public Add(String name) {
		this.admin = name;
	}
	
	//������Ϣ���
	public GridPane addinfo() {
		GridPane gridpane = new GridPane();
		
		//��������ǩ�������������
	    gridpane.add(stuld, 0, 0);
	    gridpane.add(Stuld, 1, 0);
	    gridpane.add(stuName, 2, 0);
	    gridpane.add(StuName, 3, 0);
	    
	    gridpane.add(college, 0, 1);
	    gridpane.add(College, 1, 1);
	    gridpane.add(major, 2, 1);
	    gridpane.add(Major, 3, 1);
	    
	    gridpane.add(theclass, 0, 2);
	    gridpane.add(Theclass, 1, 2);
	    gridpane.add(isCheckIn, 2, 2);
	    gridpane.add(CheckIn, 3, 2);
	    

	    gridpane.setPadding(new Insets(15, 30, 30, 30));
	    gridpane.setVgap(30);
	    gridpane.setHgap(30);
	    
	    return gridpane;
	}

	//��ӱ���
	public HBox getTitle(){
		HBox hbox = new HBox(15);
		hbox.setPadding(new Insets(30,15,30,15));
		hbox.setAlignment(Pos.CENTER);
			
			
		Label title = new Label("����ѧ����Ϣ");
		title.setFont(Font.font("Timer New Roman",FontWeight.BOLD, FontPosture.ITALIC, 25));
		title.setAlignment(Pos.CENTER);
		title.setTextFill(Color.POWDERBLUE);
		hbox.getChildren().add(title);	
		return hbox;
	}
	
	//ȷ�����˳���ť
	public HBox getOption() {
		HBox hbox = new HBox(10);
		hbox.setAlignment(Pos.CENTER);
		hbox.setPadding(new Insets(20,0,10,0));

		Button exit = new Button("�˳�");
		Button confirm = new Button("ȷ��");
						
		//���ȷ���������ݿ���в�ѯ����ѯ���ͨ��DataBase���룩
		confirm.setOnAction(e->{
			if(Stuld.getText().isEmpty() || StuName.getText().isEmpty() || College.getText().isEmpty() || Major.getText().isEmpty() || Theclass.getText().isEmpty() || CheckIn.getText().isEmpty()) {
				Alert warningWindows = new Alert(AlertType.ERROR,"���벻��Ϊ�գ�");
				warningWindows.show();
			}
			else {
				try {
				    mydata.Insert(Stuld.getText(), StuName.getText(), College.getText(), Major.getText(), Theclass.getText(), Integer.parseInt(CheckIn.getText()));
					Alert infoWindows = new Alert(AlertType.INFORMATION, "��ӳɹ���");
					infoWindows.show();
				}
				catch(Exception ex) {
			    	ex.printStackTrace();
			    	Alert warningWindows = new Alert(AlertType.INFORMATION, "���ʧ�ܣ�");
					warningWindows.show();
			    }
			}
			
		});
			
		
		//���exit��ť�˳��ý���
		exit.setOnAction(e->currentStage.close());
		
		hbox.getChildren().add(confirm);
		hbox.getChildren().add(exit);
			
		return hbox;
	}

	//���������㼯��һ��
	public VBox getAddInterface(){
		VBox vbox = new VBox();
		vbox.getChildren().add(getTitle()); 	
		vbox.getChildren().add(addinfo());
		vbox.getChildren().add(getOption());

    	return vbox;
    }
	
	public void start(Stage primaryStage){
		Scene scene = new Scene(getAddInterface(),600,350);
		primaryStage.setScene(scene);
		primaryStage.setTitle("����ѧ����Ϣ");
		currentStage = primaryStage;
		primaryStage.show();
	}
}

