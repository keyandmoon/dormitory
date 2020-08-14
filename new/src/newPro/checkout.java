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
	// 构造函数的参数，为上一页面传输的BorderPane和选择的寝室号
	TextField studentId = new TextField();
	// 将要退房的学号
	ChoiceBox<String> chooseBed = new ChoiceBox<>();
	// 用于选择床号的下拉菜单
	String[] BedNum = {"bed1","bed2","bed3","bed4"};
	// 四个床号
	
	public checkout(BorderPane bp,String cr) {
		this.bp = bp;
		this.chosenRoom = cr;
	}
	// 该类的构造函数，接收上一页面传输的寝室号
	
	public VBox getCheckout() {
		VBox vbox = new VBox(15);
		vbox.setPadding(new Insets(30,15,30,15));
		// 设置上下间距30，左右15
		
		vbox.getChildren().addAll(getCkouTitle(), getInputInfo(),
				getChoiceBox(), checkButton());
		return vbox;
	}
	// 获取整个界面
	
	public HBox getCkouTitle(){
		HBox hbox = new HBox(15);
		hbox.setPadding(new Insets(30,15,20,15));
		hbox.setAlignment(Pos.CENTER);
		
		Label title = new Label("退房登记");
		title.setFont(Font.font("Timer New Roman",FontWeight.BOLD, FontPosture.ITALIC, 25));
		title.setAlignment(Pos.CENTER);
		title.setTextFill(Color.POWDERBLUE);
		hbox.getChildren().add(title);
		
		return hbox;
	}
//  退房标题
	
	
	public HBox getInputInfo() {
		HBox hbox = new HBox(15);
		
		hbox.setPadding(new Insets(15,15,15,15));
		//设置上右下左间距
		
		hbox.setAlignment(Pos.CENTER);
		// 设置输入文本居中
		
		Label student = new Label("请输入学号：");
		studentId.setPrefWidth(75);
		hbox.getChildren().addAll(student,studentId);
		// 将文本域与标签加入hbox
		
		return hbox;
	}
	// 获取学号的输入信息
	
	public ChoiceBox<String> getBedNum() {
		chooseBed.setTooltip(new Tooltip("请选择您将要退选的床位"));
		// 设置鼠标移动到此处的提示
		chooseBed.setItems(FXCollections.observableArrayList("bed1","bed2","bed3","bed4"));
		//设置下拉选项为四个床号
		return chooseBed;
	}
	// 下拉菜单的获取
	
	public HBox getChoiceBox() {
		HBox hbox = new HBox(15);
		
		hbox.setAlignment(Pos.CENTER);
		// 设置居中
		
		hbox.setPadding(new Insets(5,15,15,15));
		
		hbox.getChildren().addAll(new Label("请选择床位号："),getBedNum());
		return hbox;
	}
	// 获取菜单和提示标签
	
	public HBox checkButton() {
		HBox hbox = new HBox(15);
		
		hbox.setAlignment(Pos.CENTER);
		// 设置居中
		
		hbox.setPadding(new Insets(30,15,30,15));
		
		Button DetermineTheCheckout = new Button("确定退房");
		DetermineTheCheckout.setOnAction(e -> {
		if(studentId.getText().isEmpty()) {
			Alert alert = new Alert(AlertType.WARNING,"学号不能为空！");
			alert.show();
		}else {
		// 若学号不为空
	    	try {
	    		Class.forName("com.mysql.jdbc.Driver");
	    		Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/dormitory?useSSL=false","root","123456");
	    		Statement sta = con.createStatement();
	    		// 连接数据库
	    		
	    		String s = "select * from roominfo WHERE roomId='" + chosenRoom + "'";
	    		// 获取当前寝室所选床位
	    		ResultSet rs = sta.executeQuery(s);
	    		// 获取结果集
	    		
	    		if(rs.next()) {
	    			if(!rs.getString(chooseBed.getValue()).equals("无")) {
	    			// 若该床位有人入住
	    				
	    				if(studentId.getText().equals(rs.getString(chooseBed.getValue()))){
	    				// 如果该床位存储信息获取的字符串与当前输入的学号相同
	    					sta.executeUpdate("UPDATE studentinfo SET isCheckin=0 WHERE stuId=" + studentId.getText());
	    					sta.executeUpdate("UPDATE roominfo SET " + chooseBed.getValue() + " = '无' WHERE roomID=" + chosenRoom);
	    					Alert alert = new Alert(AlertType.CONFIRMATION,"退房成功！");
		    				alert.show();
	    				}
	    				else {
	    					Alert alert = new Alert(AlertType.WARNING,"请填写正确信息！");
		    				alert.show();
		    				// 若输入的学号和该床存储的不一样，弹窗提示退房失败
		    				
		    				studentId.setText("");
		    				// 清空文本域，让用户重新输入
	    				}
	    			}else {
	    				Alert alert = new Alert(AlertType.ERROR,"该床位为空，无法退房，请重新选择！");
	    				alert.show();
	    				// 弹窗提示退房失败
	    				
	    				studentId.setText("");
	    				// 清空文本域，让用户重新输入
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
		
		Button back = new Button("返回主页");
		back.setOnAction(e ->{
			bp.setCenter(new chooseDormitory(bp).getChoDorInterface());
		});
		// 返回选择寝室的主界面
		
		hbox.getChildren().addAll(DetermineTheCheckout,back);
		// 插入两个按钮
		
		return hbox;
	}
	
	
	
}
