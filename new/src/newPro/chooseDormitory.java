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
	Button checked;// 用于存放上次选择内容，保证每次点击选择只有一个寝室被选中
	BorderPane bp;
    
    public chooseDormitory(BorderPane bp) {
    	this.bp = bp;
    }
    //有参构造
        
    public VBox getChoDorInterface(){
    	VBox vbox = new VBox();
    	vbox.getChildren().add(getDorTitle());
    	vbox.getChildren().add(getDormitory());
    	vbox.getChildren().add(isFullRec());
    	vbox.getChildren().add(getTheBottom());
    	return vbox;
    }
//  选择寝室的主界面
    
    public HBox getDorTitle(){
		HBox hbox = new HBox(15);
		hbox.setPadding(new Insets(30,15,30,15));
		hbox.setAlignment(Pos.CENTER);
		
		Label title = new Label("寝室列表");
		title.setFont(Font.font("Timer New Roman",FontWeight.BOLD, FontPosture.ITALIC, 25));
		title.setAlignment(Pos.CENTER);
		title.setTextFill(Color.POWDERBLUE);
		hbox.getChildren().add(title);
		
		return hbox;
	}
//  获取本页标题
    
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
				// 静态寝室颜色
				
				pane.add(number, j, i);
				number.setOnAction(e->{
					if (checked == null) {
						// 之前未点击过寝室
						if(new checkIn(bp,number.getText()).getRemainBed(number.getText()) == 0) {
							number.setStyle("-fx-background-color:LIGHTPINK;-fx-border-color:white;-fx-border-width:3");
							checked = number;
							chosenDormitory.setText(number.getText());
						}
						// 如果当前所选寝室已满，按钮颜色设置为粉色
						else {
							number.setStyle("-fx-background-color:LIGHTBLUE;-fx-border-color:white; -fx-border-width:3");
							checked = number;
							chosenDormitory.setText(number.getText());
						}
						// 如果当前所选寝室未满，按钮颜色设置为蓝色
					}
					else {
						// 之前点击过寝室按钮
						if (checked.getText().equals(number.getText())) {
							// 之前点击的寝室和当次点击的寝室为同一个
							if(new checkIn(bp,number.getText()).getRemainBed(number.getText()) == 0) {
								number.setStyle("-fx-background-color:LIGHTPINK;-fx-border-color:LIGHTPINK; -fx-border-width:3");
								chosenDormitory.setText("");
								checked = null;
							}
							// 如果当前寝室已满
							else {
								number.setStyle("-fx-background-color:LIGHTBLUE;-fx-border-color:LIGHTBLUE; -fx-border-width:3");
								chosenDormitory.setText("");
								checked = null;
							}
							// 如果当前寝室未满
						}
						else {
							// 之前点击过的寝室和当前点击的寝室不是同一个
							if(new checkIn(bp,number.getText()).getRemainBed(number.getText()) == 0) {
								if(new checkIn(bp,checked.getText()).getRemainBed(checked.getText()) == 0)
									checked.setStyle("-fx-background-color:LIGHTPINK;-fx-border-color:LIGHTPINK; -fx-border-width:3");
								// 如果之前点击过的寝室已满
								else 
									checked.setStyle("-fx-background-color:LIGHTBLUE;-fx-border-color:LIGHTBLUE; -fx-border-width:3");
								// 如果之前点击过的寝室未满
								number.setStyle("-fx-background-color:LIGHTPINK;-fx-border-color:white;-fx-border-width:3");
								checked = number;
								chosenDormitory.setText(number.getText());
							}
							// 如果当前寝室已满
							else {
								if(new checkIn(bp,checked.getText()).getRemainBed(checked.getText()) == 0)
									checked.setStyle("-fx-background-color:LIGHTPINK;-fx-border-color:LIGHTPINK; -fx-border-width:3");
								// 如果之前点击过的寝室已满
								else 
									checked.setStyle("-fx-background-color:LIGHTBLUE;-fx-border-color:LIGHTBLUE; -fx-border-width:3");
								// 如果之前点击过的寝室未满
								number.setStyle("-fx-background-color:LIGHTBLUE;-fx-border-color:white;-fx-border-width:3");
								checked = number;
								chosenDormitory.setText(number.getText());
							}
							// 如果当前寝室未满
						}
					}
					// 动态寝室颜色
				});
			}
		}
	    return pane;
	}
//  寝室的排列   
    
    public HBox isFullRec(){
		HBox hbox = new HBox(15);
		hbox.setPadding(new Insets(15,15,30,15));
		hbox.setAlignment(Pos.CENTER);
	  
		Rectangle rt1 = new Rectangle(0,0,20,15);
		rt1.setFill(Color.LIGHTBLUE);
		hbox.getChildren().add(rt1);
		hbox.getChildren().add(new Label("未满"));
		
		Rectangle rt2 = new Rectangle(0,0,20,15);
		rt2.setFill(Color.LIGHTPINK);
		hbox.getChildren().add(rt2);
		hbox.getChildren().add(new Label("已满"));
		
		return hbox;
	}
//  显示已满和未满不同颜色标识的小方块
    
    public HBox getTheBottom(){
		HBox hbox = new HBox(15);
		hbox.setPadding(new Insets(15,15,30,15));
		hbox.setAlignment(Pos.CENTER);
//      设置hbox的位置
		
		hbox.getChildren().add(new Label("选择的寝室是:"));
//      设置文字标签		
		
		hbox.getChildren().add(chosenDormitory);
		chosenDormitory.setEditable(false);
//      设置显示寝室号的不可修改文本域
		
		Button bt1 = new Button("办理入住");
		bt1.setOnAction(e -> {
			if(chosenDormitory.getText().isEmpty()) {
				Alert warning = new Alert(AlertType.WARNING,"请选择寝室！");
				warning.show();
				// 若未选择寝室，则弹出提示框
			} else {
			    bp.setCenter(new checkIn(bp, chosenDormitory.getText()).getCheckIn() );
			}
		});
//      “办理入住”按钮的点击事件
		
		Button bt2 = new Button("办理退房");
		bt2.setOnAction(e -> {
			if(chosenDormitory.getText().isEmpty()) {
				Alert warning = new Alert(AlertType.WARNING,"请选择寝室！");
				warning.show();
				// 若未选择寝室，则弹出提示框
			}else {
				bp.setCenter(new checkout(bp,chosenDormitory.getText()).getCheckout());
			}
		});
//      “办理退房”按钮的点击事件
		
		hbox.getChildren().addAll(bt1, bt2);
		return hbox;
	}
}

