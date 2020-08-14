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
	TextField studentId = new TextField();// 学号
	TextField college = new TextField();// 学院
	TextField classNum = new TextField();// 班级
	TextField name = new TextField();// 学生姓名
	TextField departments = new TextField();// 专业
	TextField bedNum = new TextField();// 床号
	TextField ft[] = {studentId, college, classNum, name, departments, bedNum};
	Button bed1 = new Button("1");
	Button bed2 = new Button("2");
	Button bed3 = new Button("3");
	Button bed4 = new Button("4");
	int chosenBedNum = 0; // 定义一个全局变量以存放登记时选中的床位号，0表示未选中床位
	String chosenRoom;// 当前被选中的寝室号
	Button checked = null;// 用于存放上次选择内容，保证每次点击选择只有一个床位被选中
	Label remainBedNum = new Label();// 设置一个显示当前空余床位数的标签
	boolean isSelectedBed1 = false;
	boolean isSelectedBed2 = false;
	boolean isSelectedBed3 = false;
	boolean isSelectedBed4 = false;
	boolean isSelectedBedNum[] = {isSelectedBed1, isSelectedBed2, isSelectedBed3, isSelectedBed4};
	// 判断床位是否有人入住
	
	public checkIn(BorderPane bp,String cr) {
		this.bp = bp;
		this.chosenRoom = cr;
	}
	// 该类的构造函数，用于从上一页面传输被选中的寝室号及BorderPane

	public VBox getCheckIn() {
		VBox vbox = new VBox();
		vbox.getChildren().add(getCkinTitle());
		vbox.getChildren().add(getMiddlePane());
		vbox.getChildren().add(getBottonText());
		return vbox;
	}
//  整个界面的容器
	
	public HBox getMiddlePane() {
		HBox hbox = new HBox();
		hbox.setPadding(new Insets(35,0,0,0));
		hbox.setAlignment(Pos.CENTER);
		hbox.getChildren().add(getInformation());
		hbox.getChildren().add(getGraph());
		return hbox;
	}
//  除了标题和底部文字的中间部分
	
	public HBox getCkinTitle(){
		HBox hbox = new HBox(15);
		hbox.setPadding(new Insets(30,15,30,15));
		hbox.setAlignment(Pos.CENTER);
		
		Label title = new Label("入住登记");
		title.setFont(Font.font("Timer New Roman",FontWeight.BOLD, FontPosture.ITALIC, 25));
		title.setAlignment(Pos.CENTER);
		title.setTextFill(Color.POWDERBLUE);
		hbox.getChildren().add(title);
		
		return hbox;
	}
//  入住标题
	
	public GridPane getInformation() {
		GridPane pane = new GridPane();
		pane.setAlignment(Pos.BASELINE_LEFT);
		pane.setPadding(new Insets(0,30,0,30));
		pane.setHgap(20);
		pane.setVgap(20);
		
		pane.add(new Label("学号"), 0, 0);
		pane.add(new Label("学院"), 0, 1);
		pane.add(new Label("班级"), 0, 2);
		
		pane.add(studentId, 1, 0);
		pane.add(college, 1, 1);
		pane.add(classNum, 1, 2);
		
		pane.add(new Label("姓名"), 2, 0);
		pane.add(new Label("系别"), 2, 1);
		pane.add(new Label("床号"), 2, 2);
		
		pane.add(name, 3, 0);
		pane.add(departments, 3, 1);
		pane.add(bedNum, 3, 2);
		
		for(int i = 0;i < 6; i++) {
			ft[i].setPrefWidth(120);
			// 给每个文本框设置同样的宽度
			
			if(i != 0) {
				ft[i].setText("");
				ft[i].setEditable(false);
			}
			// 给除了学号以外的文本框都设置不可更改属性
		}
		
		pane.add(getReturnButton(), 3, 3);
		return pane;
	}
//  获取学生入住登记所需的各种信息
	
    public Pane getGraph() {
    	Pane balconyAndBed = new Pane();
    	
    	
    	
    	Label text = new Label("房间布局示意图");
    	text.setLayoutX(12.5);
    	text.setLayoutY(150);
    	
    	Label balcony = new Label("阳台");
    	balcony.setStyle("-fx-background-color:#66ccff");
    	balcony.setTextFill(Color.BLACK);
    	balcony.setAlignment(Pos.CENTER);
    	balcony.setPrefHeight(25);
    	balcony.setPrefWidth(107);
    	balcony.setLayoutX(0);
    	balcony.setLayoutY(0);
    	
    	// 绘制的时候判断该床是否有人入住
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
    		// 获取该床位是否有人入住
    		chosenBedNum = 1;
    		if(!isSelectedBed1) {
    		// 如果当前床位无人入住
    			if (checked == null) {
    			// 如果之前未点击过床位
    				
    				bed1.setStyle("-fx-background-color:LIGHTBLUE;-fx-border-color:YELLOW; -fx-border-width:3");
    				checked = bed1;
    				bedNum.setText(String.valueOf(chosenBedNum));
    			}
    			else {
				// 如果之前点击过床位
    				
    				if (checked.getText().equals(bed1.getText())) {
    					bed1.setStyle("-fx-background-color:LIGHTBLUE;-fx-border-color:LIGHTBLUE; -fx-border-width:3");
    					bedNum.setText("");
    					checked = null;
    				}
    				// 如果之前点击的床位和当前一致
    				
    				else {
    					if(isSelectedBedNum[Integer.parseInt(checked.getText()) - 1]) {
    						// 如果之前点击的床位有人入住
    						checked.setStyle("-fx-background-color:LIGHTPINK;-fx-border-color:LIGHTPINK; -fx-border-width:3");
    						bed1.setStyle("-fx-background-color:LIGHTBLUE;-fx-border-color:YELLOW;-fx-border-width:3");
    						checked = bed1;
    						bedNum.setText(String.valueOf(chosenBedNum));
    						
    					}else {
    						// 如果之前点击的床位无人入住
    						checked.setStyle("-fx-background-color:LIGHTBLUE;-fx-border-color:LIGHTBLUE; -fx-border-width:3");
    						bed1.setStyle("-fx-background-color:LIGHTBLUE;-fx-border-color:YELLOW;-fx-border-width:3");
    						checked = bed1;
    						bedNum.setText(String.valueOf(chosenBedNum));
    					}
    				}// 如果之前点击的床位和当前不一致
    			}
    		}
    		else {
    		// 如果当前床位有人入住
    			if (checked == null) {
    			// 如果之前未点击过床位
    				bed1.setStyle("-fx-background-color:LIGHTPINK;-fx-border-color:YELLOW; -fx-border-width:3");
    				checked = bed1;
    				bedNum.setText(String.valueOf(chosenBedNum));
    			}
    			else {
				// 如果之前点击过床位
    				if (checked.getText().equals(bed1.getText())) {
    					bed1.setStyle("-fx-background-color:LIGHTPINK;-fx-border-color:LIGHTPINK; -fx-border-width:3");
    					bedNum.setText("");
    					checked = null;
    				}
    				// 如果之前点击的床位和当前一致
    				else {
    					if(isSelectedBedNum[Integer.parseInt(checked.getText()) - 1]) {
    					// 如果之前点击的床位有人入住
    						checked.setStyle("-fx-background-color:LIGHTPINK;-fx-border-color:LIGHTPINK; -fx-border-width:3");
    						bed1.setStyle("-fx-background-color:LIGHTPINK;-fx-border-color:YELLOW;-fx-border-width:3");
    						checked = bed1;
    						bedNum.setText(String.valueOf(chosenBedNum));
    					}else {
    					// 如果之前点击的床位无人入住
    						
    						checked.setStyle("-fx-background-color:LIGHTBLUE;-fx-border-color:LIGHTBLUE; -fx-border-width:3");
    						bed1.setStyle("-fx-background-color:LIGHTPINK;-fx-border-color:YELLOW;-fx-border-width:3");
    						checked = bed1;
    						bedNum.setText(String.valueOf(chosenBedNum));
    					}
    				}
    				// 如果之前点击的床位和当前不一致
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
        		// 如果当前床位无人入住
        			if (checked == null) {
        			// 如果之前未点击过床位
        				bed2.setStyle("-fx-background-color:LIGHTBLUE;-fx-border-color:YELLOW; -fx-border-width:3");
        				checked = bed2;
        				bedNum.setText(String.valueOf(chosenBedNum));
        			}
        			else {
    				// 如果之前点击过床位
        				if (checked.getText().equals(bed2.getText())) {
        					bed2.setStyle("-fx-background-color:LIGHTBLUE;-fx-border-color:LIGHTBLUE; -fx-border-width:3");
        					bedNum.setText("");
        					checked = null;
        				}
        				// 如果之前点击的床位和当前一致
        				else {
        					if(isSelectedBedNum[Integer.parseInt(checked.getText()) - 1]) {
        						// 如果之前点击的床位有人入住
        						checked.setStyle("-fx-background-color:LIGHTPINK;-fx-border-color:LIGHTPINK; -fx-border-width:3");
        						bed2.setStyle("-fx-background-color:LIGHTBLUE;-fx-border-color:YELLOW;-fx-border-width:3");
        						checked = bed2;
        						bedNum.setText(String.valueOf(chosenBedNum));
        						
        					}else {
        						// 如果之前点击的床位无人入住
        						checked.setStyle("-fx-background-color:LIGHTBLUE;-fx-border-color:LIGHTBLUE; -fx-border-width:3");
        						bed2.setStyle("-fx-background-color:LIGHTBLUE;-fx-border-color:YELLOW;-fx-border-width:3");
        						checked = bed2;
        						bedNum.setText(String.valueOf(chosenBedNum));
        					}
        				}
        				// 如果之前点击的床位和当前不一致
        			}
        		}
        		else {
        		// 如果当前床位有人入住
        			if (checked == null) {
        			// 如果之前未点击过床位
        				bed2.setStyle("-fx-background-color:LIGHTPINK;-fx-border-color:YELLOW; -fx-border-width:3");
        				checked = bed2;
        				bedNum.setText(String.valueOf(chosenBedNum));
        			}
        			else {
    				// 如果之前点击过床位
        				if (checked.getText().equals(bed2.getText())) {
        					bed2.setStyle("-fx-background-color:LIGHTPINK;-fx-border-color:LIGHTPINK; -fx-border-width:3");
        					bedNum.setText("");
        					checked = null;
        				}
        				// 如果之前点击的床位和当前一致
        				else {
        					if(isSelectedBedNum[Integer.parseInt(checked.getText()) - 1]) {
            					// 如果之前点击的床位有人入住
            						checked.setStyle("-fx-background-color:LIGHTPINK;-fx-border-color:LIGHTPINK; -fx-border-width:3");
            						bed2.setStyle("-fx-background-color:LIGHTPINK;-fx-border-color:YELLOW;-fx-border-width:3");
            						checked = bed2;
            						bedNum.setText(String.valueOf(chosenBedNum));
            					}else {
            					// 如果之前点击的床位无人入住
            						
            						checked.setStyle("-fx-background-color:LIGHTBLUE;-fx-border-color:LIGHTBLUE; -fx-border-width:3");
            						bed2.setStyle("-fx-background-color:LIGHTPINK;-fx-border-color:YELLOW;-fx-border-width:3");
            						checked = bed2;
            						bedNum.setText(String.valueOf(chosenBedNum));
            					}
        				}
        				// 如果之前点击的床位和当前不一致
        			}
        		}
    	});
    	
    	// 绘制的时候判断该床是否有人入住
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
        		// 如果当前床位无人入住
        			if (checked == null) {
        			// 如果之前未点击过床位
        				bed3.setStyle("-fx-background-color:LIGHTBLUE;-fx-border-color:YELLOW; -fx-border-width:3");
        				checked = bed3;
        				bedNum.setText(String.valueOf(chosenBedNum));
        			}
        			else {
    				// 如果之前点击过床位
        				if (checked.getText().equals(bed3.getText())) {
        					bed3.setStyle("-fx-background-color:LIGHTBLUE;-fx-border-color:LIGHTBLUE; -fx-border-width:3");
        					bedNum.setText("");
        					checked = null;
        				}
        				// 如果之前点击的床位和当前一致
        				else {
        					if(isSelectedBedNum[Integer.parseInt(checked.getText()) - 1]) {
        						// 如果之前点击的床位有人入住
        						checked.setStyle("-fx-background-color:LIGHTPINK;-fx-border-color:LIGHTPINK; -fx-border-width:3");
        						bed3.setStyle("-fx-background-color:LIGHTBLUE;-fx-border-color:YELLOW;-fx-border-width:3");
        						checked = bed3;
        						bedNum.setText(String.valueOf(chosenBedNum));
        						
        					}else {
        						// 如果之前点击的床位无人入住
        						checked.setStyle("-fx-background-color:LIGHTBLUE;-fx-border-color:LIGHTBLUE; -fx-border-width:3");
        						bed3.setStyle("-fx-background-color:LIGHTBLUE;-fx-border-color:YELLOW;-fx-border-width:3");
        						checked = bed3;
        						bedNum.setText(String.valueOf(chosenBedNum));
        					}
        				}
        				// 如果之前点击的床位和当前不一致
        			}
        		}
        		else {
        		// 如果当前床位有人入住
        			if (checked == null) {
        			// 如果之前未点击过床位
        				bed3.setStyle("-fx-background-color:LIGHTPINK;-fx-border-color:YELLOW; -fx-border-width:3");
        				checked = bed3;
        				bedNum.setText(String.valueOf(chosenBedNum));
        			}
        			else {
    				// 如果之前点击过床位
        				if (checked.getText().equals(bed3.getText())) {
        					bed3.setStyle("-fx-background-color:LIGHTPINK;-fx-border-color:LIGHTPINK; -fx-border-width:3");
        					bedNum.setText("");
        					checked = null;
        				}
        				// 如果之前点击的床位和当前一致
        				else {
        					if(isSelectedBedNum[Integer.parseInt(checked.getText()) - 1]) {
            					// 如果之前点击的床位有人入住
            						checked.setStyle("-fx-background-color:LIGHTPINK;-fx-border-color:LIGHTPINK; -fx-border-width:3");
            						bed3.setStyle("-fx-background-color:LIGHTPINK;-fx-border-color:YELLOW;-fx-border-width:3");
            						checked = bed3;
            						bedNum.setText(String.valueOf(chosenBedNum));
            					}else {
            					// 如果之前点击的床位无人入住
            						
            						checked.setStyle("-fx-background-color:LIGHTBLUE;-fx-border-color:LIGHTBLUE; -fx-border-width:3");
            						bed3.setStyle("-fx-background-color:LIGHTPINK;-fx-border-color:YELLOW;-fx-border-width:3");
            						checked = bed3;
            						bedNum.setText(String.valueOf(chosenBedNum));
            					}
        				}
        				// 如果之前点击的床位和当前不一致
        			}
        		}
    	});
    	
    	// 绘制的时候判断该床是否有人入住
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
        		// 如果当前床位无人入住
        			if (checked == null) {
        			// 如果之前未点击过床位
        				bed4.setStyle("-fx-background-color:LIGHTBLUE;-fx-border-color:YELLOW; -fx-border-width:3");
        				checked = bed4;
        				bedNum.setText(String.valueOf(chosenBedNum));
        			}
        			else {
    				// 如果之前点击过床位
        				if (checked.getText().equals(bed4.getText())) {
        					bed4.setStyle("-fx-background-color:LIGHTBLUE;-fx-border-color:LIGHTBLUE; -fx-border-width:3");
        					bedNum.setText("");
        					checked = null;
        				}
        				// 如果之前点击的床位和当前一致
        				else {
        					if(isSelectedBedNum[Integer.parseInt(checked.getText()) - 1]) {
        						// 如果之前点击的床位有人入住
        						checked.setStyle("-fx-background-color:LIGHTPINK;-fx-border-color:LIGHTPINK; -fx-border-width:3");
        						bed4.setStyle("-fx-background-color:LIGHTBLUE;-fx-border-color:YELLOW;-fx-border-width:3");
        						checked = bed4;
        						bedNum.setText(String.valueOf(chosenBedNum));
        						
        					}else {
        						// 如果之前点击的床位无人入住
        						checked.setStyle("-fx-background-color:LIGHTBLUE;-fx-border-color:LIGHTBLUE; -fx-border-width:3");
        						bed4.setStyle("-fx-background-color:LIGHTBLUE;-fx-border-color:YELLOW;-fx-border-width:3");
        						checked = bed4;
        						bedNum.setText(String.valueOf(chosenBedNum));
        					}
        				}
        				// 如果之前点击的床位和当前不一致
        			}
        		}
        		else {
        		// 如果当前床位有人入住
        			if (checked == null) {
        			// 如果之前未点击过床位
        				bed4.setStyle("-fx-background-color:LIGHTPINK;-fx-border-color:YELLOW; -fx-border-width:3");
        				checked = bed4;
        				bedNum.setText(String.valueOf(chosenBedNum));
        			}
        			else {
    				// 如果之前点击过床位
        				if (checked.getText().equals(bed4.getText())) {
        					bed4.setStyle("-fx-background-color:LIGHTPINK;-fx-border-color:LIGHTPINK; -fx-border-width:3");
        					bedNum.setText("");
        					checked = null;
        				}
        				// 如果之前点击的床位和当前一致
        				else {
        					if(isSelectedBedNum[Integer.parseInt(checked.getText()) - 1]) {
            					// 如果之前点击的床位有人入住
            						checked.setStyle("-fx-background-color:LIGHTPINK;-fx-border-color:LIGHTPINK; -fx-border-width:3");
            						bed4.setStyle("-fx-background-color:LIGHTPINK;-fx-border-color:YELLOW;-fx-border-width:3");
            						checked = bed4;
            						bedNum.setText(String.valueOf(chosenBedNum));
            					}else {
            					// 如果之前点击的床位无人入住
            						
            						checked.setStyle("-fx-background-color:LIGHTBLUE;-fx-border-color:LIGHTBLUE; -fx-border-width:3");
            						bed4.setStyle("-fx-background-color:LIGHTPINK;-fx-border-color:YELLOW;-fx-border-width:3");
            						checked = bed4;
            						bedNum.setText(String.valueOf(chosenBedNum));
            					}
        				}
        				// 如果之前点击的床位和当前不一致
        			}
        		}
    	});
    	
    	Rectangle isNotUsedRec = new Rectangle(0,0,15,10);
    	isNotUsedRec.setFill(Color.LIGHTBLUE);
    	isNotUsedRec.setLayoutX(117);
    	isNotUsedRec.setLayoutY(0);
		
    	Label isNotUsedText = new Label("未使用");
    	isNotUsedText.setLayoutX(133);
        isNotUsedText.setLayoutY(0);
    
		Rectangle isUsedRec = new Rectangle(0,0,15,10);
		isUsedRec.setFill(Color.LIGHTPINK);
		isUsedRec.setLayoutX(117);
		isUsedRec.setLayoutY(20);
		
    	Label isUsedText = new Label("已使用");
    	isUsedText.setLayoutX(133);
    	isUsedText.setLayoutY(20);
		
    	balconyAndBed.getChildren().addAll(balcony,bed1,bed2,bed3,bed4,
    			text,isNotUsedRec,isUsedRec,isNotUsedText,isUsedText);
       
    	
    	
    	return balconyAndBed;
    }
//  房间布局示意图
	
	public HBox getReturnButton() {
		HBox hbox = new HBox(15);
		hbox.setAlignment(Pos.CENTER);
		
		Button DetermineTheCheckIn = new Button("确定入住");
		DetermineTheCheckIn.setOnAction(e -> {
			if(studentId.getText().isEmpty()) {
				
				Alert warningWindows = new Alert(AlertType.ERROR,"学号不能为空！");
				warningWindows.show();
				// 若学号输入为空弹出提示
				
				studentId.setText("");
				// 将所有文本信息清空另用户重新输入
				
			}else if(bedNum.getText().isEmpty()) {
				
				Alert warning = new Alert(AlertType.ERROR,"请选择床位！");
				warning.show();
				// 若床位未选择弹出提示
				
			}else if(isSelectedBedNum[chosenBedNum - 1]) {
				
				isSeleBed(chosenRoom);
				Alert warning = new Alert(AlertType.ERROR,"该床位已有人入住！");
				warning.show();
				// 若床位已有人入住则警告
				
			}
			else {
				try {
					
					Class.forName("com.mysql.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/dormitory?useSSL=false","root","123456");
					Statement sta = con.createStatement();
					// 连接数据库
					
					String allInfo = "select * from studentinfo where stuId=" + studentId.getText();
					ResultSet rs = sta.executeQuery(allInfo);
					// 获取studentinfo这个表的所有结果，放入一个结果集中
					
					if(rs.next() && studentId.getText().equals(rs.getString("stuId"))) {
						if(rs.getString("isCheckIn").equals("0")) { 
							college.setText(rs.getString("colleget"));
							classNum.setText(rs.getString("class"));
							name.setText(rs.getString("stuName"));
							departments.setText(rs.getString("department"));
							// 若表里存在该人，则自动填充该学生信息
							
							sta.executeUpdate("UPDATE studentinfo SET isCheckin=1 WHERE stuId=" + studentId.getText());
							sta.executeUpdate("UPDATE roominfo SET bed" + chosenBedNum + "= '"+ studentId.getText() +"'  WHERE roomID=" + chosenRoom);
							// 更新登入的数据
							
							Alert alert = new Alert(AlertType.INFORMATION,"入住成功！");
							alert.show();
							// 弹窗提示入住成功
							
							remainBedNum.setText("剩余的床位有：" + String.valueOf(getRemainBed(chosenRoom)));
							// 登记后立即更新剩余床位
			  
							
						}else {
							Alert alert = new Alert(AlertType.WARNING,"此学生已入住！");
							alert.show();
							// 弹窗提示
							
							for(int i = 0;i < 5;i++) ft[i].setText("");
							//将所有信息清空
						}
						
						rs.close();
						sta.close();
						con.close();
					}else {
						Alert alert = new Alert(AlertType.ERROR,"此学生不存在，请检查学号并重新输入！");
						alert.show();
						// 弹窗提示学号输入错误
							
						for(int i = 0;i < 5;i++) ft[i].setText("");
						//将所有信息清空
					}
					
					
					
				} catch (SQLException | ClassNotFoundException ex) {
					ex.printStackTrace();
				}
			}
		});
		hbox.getChildren().add(DetermineTheCheckIn);
		
		Button returnTheMain = new Button("返回主页");
		returnTheMain.setOnAction(e -> {
			bp.setCenter(new chooseDormitory(bp).getChoDorInterface());
		});
		// 返回选择寝室的主界面
		
		hbox.getChildren().add(returnTheMain);
		return hbox;
	}
//  底部“确定入住”与“返回主页”的两个按钮
	
	public HBox getBottonText() {
		HBox hbox = new HBox(15);
		hbox.setPadding(new Insets(35,15,30,15));
		hbox.setAlignment(Pos.CENTER);
		
		hbox.getChildren().add(new Label("选择的寝室是：" + chosenRoom));
		remainBedNum.setText("剩余的床位有：" + String.valueOf(getRemainBed(chosenRoom)));
		hbox.getChildren().add(remainBedNum);
		
		return hbox;
    }
//  页面底部显示所选寝室与空余床数

	public int getRemainBed(String chosenRoom) {
        int remainBed = 0;// 定义一个变量以存放剩余床位
 		try {
     		Class.forName("com.mysql.jdbc.Driver");
     		Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/dormitory?useSSL=false","root","123456");
     		Statement sta = con.createStatement();
     		String s = "select * from roominfo WHERE roomId =" + chosenRoom;// 获取该寝室当前床位剩余情况
     		ResultSet rs = sta.executeQuery(s);
     		if(rs.next()) {
     			if(rs.getString("bed1").equals("无")) 
     				remainBed ++; // 若当前床位无人，则剩余数量加1
     			if(rs.getString("bed2").equals("无"))
     				remainBed ++;
     			if(rs.getString("bed3").equals("无"))
     				remainBed ++;
     			if(rs.getString("bed4").equals("无"))
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
//  获取当前剩余床位数
	
	public void isSeleBed(String chosenRoom) {
		try {
     		Class.forName("com.mysql.jdbc.Driver");
     		Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/dormitory?useSSL=false","root","123456");
     		Statement sta = con.createStatement();
     		String s = "select * from roominfo WHERE roomId =" + chosenRoom;// 获取该寝室当前床位剩余情况
     		ResultSet rs = sta.executeQuery(s);
     		if(rs.next()) {
     			if(!rs.getString("bed1").equals("无"))
     				isSelectedBed1 = true;// 若当前床位有人，则标记为true，否则标记为false
     			else
     				isSelectedBed1 = false;
     			if(!rs.getString("bed2").equals("无"))
     				isSelectedBed2 = true;
     			else
     				isSelectedBed2 = false;
     			if(!rs.getString("bed3").equals("无"))
     				isSelectedBed3 = true;
     			else
     				isSelectedBed3 = false;
     			if(!rs.getString("bed4").equals("无"))
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