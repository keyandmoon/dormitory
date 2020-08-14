package newPro;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MenuStage{
	
	private MenuBar menuBar;
	private Menu fileMenu, locateMenu, actionMenu;
	String admin;
	BorderPane bp;
	
	public MenuStage(BorderPane bp, String name) {
		this.admin = name;
		this.bp = bp;
	}
	// 从Login界面传输过来的当前用户名
	
	public Menu buildFileMenu() {
		//创建"文件"菜单项
		Menu fileMenu = new Menu("用户");
		
		//创建退出选项
		MenuItem exitItem = new MenuItem("退出系统");
		exitItem.setOnAction(e->System.exit(0));
		
		//创建修改密码选项
		MenuItem modifyPasswordItem = new MenuItem("修改密码");
		modifyPasswordItem.setOnAction(e->{
			new modifyPassword(admin).start(new Stage());
		});
		
		//创建修改用户名选项
		MenuItem modifyAdminItem = new MenuItem("修改用户名");
		modifyAdminItem.setOnAction(e->{
			new modifyAdmin(admin).start(new Stage());
		});
		
		//创捷注册用户选项
		MenuItem registerItem = new MenuItem("注册用户");
		registerItem.setOnAction(e->{
			new Register(admin).start(new Stage());
		});
		
		//将退出、修改密码、修改用户名、注册用户选项加入File菜单项
		fileMenu.getItems().addAll(exitItem,modifyPasswordItem, modifyAdminItem, registerItem);
		
		return fileMenu;
	}

	public Menu buildLocateMenu() {
		// 创建查询菜单，菜单标题为 "查询"
		Menu locateMenu = new Menu("查询");

		// 创建系别, 班级, 学号, 姓名, 寝室单选菜单项
		RadioMenuItem majorItem = new RadioMenuItem("按专业查询");
		//点击按钮触发输入事件
		majorItem.setOnAction(e->{
			new theMajor(admin).start(new Stage());
		});
		
		RadioMenuItem classItem = new RadioMenuItem("按班级查询");
		//点击按钮触发输入事件
		classItem.setOnAction(e->{
			new theClass(admin).start(new Stage());
		});
		
		RadioMenuItem studentIDItem = new RadioMenuItem("按学号查询");
		//点击按钮触发输入事件
		studentIDItem.setOnAction(e->{
			new theStudentID(admin).start(new Stage());
		});

		RadioMenuItem nameItem = new RadioMenuItem("按姓名查询");
		//点击按钮触发输入事件
		nameItem.setOnAction(e->{
			new theName(admin).start(new Stage());
		});
		
		RadioMenuItem dormitoryIDItem = new RadioMenuItem("按寝室号查询");
		//点击按钮触发输入事件
		dormitoryIDItem.setOnAction(e->{
			new theDorID(admin).start(new Stage());
		});
		
		// 对单选菜单选项进行分组
		ToggleGroup group = new ToggleGroup();
		group.getToggles().addAll(majorItem, classItem, studentIDItem, nameItem, dormitoryIDItem);

		// 把单选菜单项添加到locate子菜单中
		locateMenu.getItems().addAll(majorItem, classItem, studentIDItem, nameItem, dormitoryIDItem);
		
		return locateMenu;
	}
	
	public Menu buildActionMenu() {
		//创建"功能"菜单项
		Menu actionMenu = new Menu("功能");
		
		//创建增加信息选项
		MenuItem addItem = new MenuItem("增加信息");
		addItem.setOnAction(e->{
			new Add(admin).start(new Stage());
		});
		
		//创建删除信息选项
		MenuItem deleteItem = new MenuItem("删除信息");
		deleteItem.setOnAction(e->{
			new Delete(admin).start(new Stage());
		});
		
		//将退出、修改密码、修改用户名、注册用户选项加入File菜单项
		actionMenu.getItems().addAll(addItem,deleteItem);
		
		return actionMenu;
	}


 
	 public MenuBar getMenu(){
		
		//创建菜单
		menuBar = new MenuBar();
		fileMenu = buildFileMenu();
		locateMenu = buildLocateMenu();
		actionMenu = buildActionMenu();
		
		menuBar.getMenus().add(fileMenu);
		menuBar.getMenus().add(locateMenu);
		menuBar.getMenus().add(actionMenu);
		
		return menuBar;
	} 
}

