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

    //定义标签和输入框
    Label stuld = new Label("学号");
    TextField Stuld = new TextField();
    Label stuName = new Label("姓名");
    TextField StuName = new TextField();
    Label college = new Label("学院");
    TextField College = new TextField();
    Label major = new Label("专业");
    TextField Major = new TextField();
    Label theclass = new Label("班级");
    TextField Theclass = new TextField();
    Label isCheckIn = new Label("是否入住");
    TextField CheckIn = new TextField();
    
	public Add(String name) {
		this.admin = name;
	}
	
	//输入信息面板
	public GridPane addinfo() {
		GridPane gridpane = new GridPane();
		
		//将各个标签和输入框加入面板
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

	//添加标题
	public HBox getTitle(){
		HBox hbox = new HBox(15);
		hbox.setPadding(new Insets(30,15,30,15));
		hbox.setAlignment(Pos.CENTER);
			
			
		Label title = new Label("增加学生信息");
		title.setFont(Font.font("Timer New Roman",FontWeight.BOLD, FontPosture.ITALIC, 25));
		title.setAlignment(Pos.CENTER);
		title.setTextFill(Color.POWDERBLUE);
		hbox.getChildren().add(title);	
		return hbox;
	}
	
	//确定、退出按钮
	public HBox getOption() {
		HBox hbox = new HBox(10);
		hbox.setAlignment(Pos.CENTER);
		hbox.setPadding(new Insets(20,0,10,0));

		Button exit = new Button("退出");
		Button confirm = new Button("确定");
						
		//点击确定连接数据库进行查询（查询结果通过DataBase传入）
		confirm.setOnAction(e->{
			if(Stuld.getText().isEmpty() || StuName.getText().isEmpty() || College.getText().isEmpty() || Major.getText().isEmpty() || Theclass.getText().isEmpty() || CheckIn.getText().isEmpty()) {
				Alert warningWindows = new Alert(AlertType.ERROR,"输入不能为空！");
				warningWindows.show();
			}
			else {
				try {
				    mydata.Insert(Stuld.getText(), StuName.getText(), College.getText(), Major.getText(), Theclass.getText(), Integer.parseInt(CheckIn.getText()));
					Alert infoWindows = new Alert(AlertType.INFORMATION, "添加成功！");
					infoWindows.show();
				}
				catch(Exception ex) {
			    	ex.printStackTrace();
			    	Alert warningWindows = new Alert(AlertType.INFORMATION, "添加失败！");
					warningWindows.show();
			    }
			}
			
		});
			
		
		//点击exit按钮退出该界面
		exit.setOnAction(e->currentStage.close());
		
		hbox.getChildren().add(confirm);
		hbox.getChildren().add(exit);
			
		return hbox;
	}

	//将所有面板汇集到一起
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
		primaryStage.setTitle("增加学生信息");
		currentStage = primaryStage;
		primaryStage.show();
	}
}

