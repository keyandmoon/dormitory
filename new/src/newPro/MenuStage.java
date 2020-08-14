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
	// ��Login���洫������ĵ�ǰ�û���
	
	public Menu buildFileMenu() {
		//����"�ļ�"�˵���
		Menu fileMenu = new Menu("�û�");
		
		//�����˳�ѡ��
		MenuItem exitItem = new MenuItem("�˳�ϵͳ");
		exitItem.setOnAction(e->System.exit(0));
		
		//�����޸�����ѡ��
		MenuItem modifyPasswordItem = new MenuItem("�޸�����");
		modifyPasswordItem.setOnAction(e->{
			new modifyPassword(admin).start(new Stage());
		});
		
		//�����޸��û���ѡ��
		MenuItem modifyAdminItem = new MenuItem("�޸��û���");
		modifyAdminItem.setOnAction(e->{
			new modifyAdmin(admin).start(new Stage());
		});
		
		//����ע���û�ѡ��
		MenuItem registerItem = new MenuItem("ע���û�");
		registerItem.setOnAction(e->{
			new Register(admin).start(new Stage());
		});
		
		//���˳����޸����롢�޸��û�����ע���û�ѡ�����File�˵���
		fileMenu.getItems().addAll(exitItem,modifyPasswordItem, modifyAdminItem, registerItem);
		
		return fileMenu;
	}

	public Menu buildLocateMenu() {
		// ������ѯ�˵����˵�����Ϊ "��ѯ"
		Menu locateMenu = new Menu("��ѯ");

		// ����ϵ��, �༶, ѧ��, ����, ���ҵ�ѡ�˵���
		RadioMenuItem majorItem = new RadioMenuItem("��רҵ��ѯ");
		//�����ť���������¼�
		majorItem.setOnAction(e->{
			new theMajor(admin).start(new Stage());
		});
		
		RadioMenuItem classItem = new RadioMenuItem("���༶��ѯ");
		//�����ť���������¼�
		classItem.setOnAction(e->{
			new theClass(admin).start(new Stage());
		});
		
		RadioMenuItem studentIDItem = new RadioMenuItem("��ѧ�Ų�ѯ");
		//�����ť���������¼�
		studentIDItem.setOnAction(e->{
			new theStudentID(admin).start(new Stage());
		});

		RadioMenuItem nameItem = new RadioMenuItem("��������ѯ");
		//�����ť���������¼�
		nameItem.setOnAction(e->{
			new theName(admin).start(new Stage());
		});
		
		RadioMenuItem dormitoryIDItem = new RadioMenuItem("�����ҺŲ�ѯ");
		//�����ť���������¼�
		dormitoryIDItem.setOnAction(e->{
			new theDorID(admin).start(new Stage());
		});
		
		// �Ե�ѡ�˵�ѡ����з���
		ToggleGroup group = new ToggleGroup();
		group.getToggles().addAll(majorItem, classItem, studentIDItem, nameItem, dormitoryIDItem);

		// �ѵ�ѡ�˵�����ӵ�locate�Ӳ˵���
		locateMenu.getItems().addAll(majorItem, classItem, studentIDItem, nameItem, dormitoryIDItem);
		
		return locateMenu;
	}
	
	public Menu buildActionMenu() {
		//����"����"�˵���
		Menu actionMenu = new Menu("����");
		
		//����������Ϣѡ��
		MenuItem addItem = new MenuItem("������Ϣ");
		addItem.setOnAction(e->{
			new Add(admin).start(new Stage());
		});
		
		//����ɾ����Ϣѡ��
		MenuItem deleteItem = new MenuItem("ɾ����Ϣ");
		deleteItem.setOnAction(e->{
			new Delete(admin).start(new Stage());
		});
		
		//���˳����޸����롢�޸��û�����ע���û�ѡ�����File�˵���
		actionMenu.getItems().addAll(addItem,deleteItem);
		
		return actionMenu;
	}


 
	 public MenuBar getMenu(){
		
		//�����˵�
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

