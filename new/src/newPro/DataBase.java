package newPro;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class DataBase {
	
    private TableView<Person> tView;
	private ObservableList<Person> obsList;
	String bed1,bed2,bed3,bed4;
	  
	//��רҵ��ѯ
		public TableView<Person> Major_Query(String Major) throws SQLException {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/dormitory?useSSL=false","root","123456");
				Statement sta = con.createStatement();
				System.out.print("����ɹ���");
		    	ResultSet rs = sta.executeQuery("SELECT * from studentinfo where department = '"+Major+"'");
		    	
				//���������б�
				obsList = FXCollections.observableArrayList();
				//�������
				while(rs.next()) {
					obsList.add(new Person(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)));
		        }
		    	rs.close();
		    	sta.close();
		    	con.close();
			} 
			catch (Exception ex) {
				ex.printStackTrace();
			}  
			
			//���ر�
	    	return buildTableView(obsList);
	    }
		
		//���༶��ѯ
		public TableView<Person> Class_Query(String theClass) throws SQLException {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/dormitory?useSSL=false","root","123456");
				Statement sta = con.createStatement();
				System.out.print("����ɹ���");
		    	String theClassQuery = "select * from studentinfo where class = '"+theClass+"'";
		    	ResultSet rs = sta.executeQuery(theClassQuery);
		    	
				//���������б�
				obsList = FXCollections.observableArrayList();
				//�������
				while(rs.next()) {
					obsList.add(new Person(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)));
		        }
		    	rs.close();
		    	sta.close();
		    	con.close();
			} 
			catch (Exception ex) {
				ex.printStackTrace();
			}  
		
			// ���ر�
	    	return buildTableView(obsList);
	    }
	
    
    //��ѧ�Ų�ѯ
	public TableView<Person> StuId_Query(String StuId) throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/dormitory?useSSL=false","root","123456");
			Statement sta = con.createStatement();
			System.out.print("����ɹ���");
			String StuIdtQuery = "select * from studentinfo where stuId = '"+StuId+"'";
	    	ResultSet rs = sta.executeQuery(StuIdtQuery);
	    	
			//���������б�
			obsList = FXCollections.observableArrayList();
			//�������
			while(rs.next()) {
				obsList.add(new Person(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)));
	        }
	    	rs.close();
	    	sta.close();
	    	con.close();
		} 
		catch (Exception ex) {
			ex.printStackTrace();
		}   	

		// ���ر�
    	return buildTableView(obsList);
    }
    
    //��������ѯ
	public TableView<Person> StuName_Query(String StuName) throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/dormitory?useSSL=false","root","123456");
			Statement sta = con.createStatement();
			System.out.print("����ɹ���");
			String StuNameQuery = "select * from studentinfo where stuName = '"+StuName+"'";
	    	ResultSet rs = sta.executeQuery(StuNameQuery);
			//���������б�
			obsList = FXCollections.observableArrayList();
			//�������
			while(rs.next()) {
				obsList.add(new Person(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)));
	        }
	    	rs.close();
	    	sta.close();
	    	con.close();
		} 
		catch (Exception ex) {
			ex.printStackTrace();
		}   

    	
		// ���ر�
    	return buildTableView(obsList);
    }
	
	//�����ҺŲ�ѯ
	public TableView<Person> DorID_Query(String DorID) throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/dormitory?useSSL=false","root","123456");
			Statement sta = con.createStatement();
			System.out.print("����ɹ���");
	    	String DorIDQuery = "select * from roominfo where roomId = '"+DorID+"'";
	    	ResultSet rs = sta.executeQuery(DorIDQuery);
	    	
	    	//��ȡ��ͬ���Ŷ�Ӧ��ѧ��
	    	while(rs.next()) {
		    	bed1 = rs.getString("bed1");
		    	bed2 = rs.getString("bed2");
		    	bed3 = rs.getString("bed3");
		    	bed4 = rs.getString("bed4");
	        }

	    	
			//���������б�
			obsList = FXCollections.observableArrayList();
	    	while(rs.next()) {
	    		obsList.add(new Person(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)));
	        }
	    	rs.close();
	    	sta.close();
	    	
	    	//���ݲ�ͬѧ�Ų��Ҷ�Ӧѧ������Ϣ
	    	Statement sta1 = con.createStatement();
	    	String bed1Query = "select * from studentinfo where stuId = '"+bed1+"'";
	    	ResultSet bd1St = sta1.executeQuery(bed1Query);
	    	while(bd1St.next()) {
	    		obsList.add(new Person(bd1St.getString(1), bd1St.getString(2), bd1St.getString(3), bd1St.getString(4), bd1St.getString(5), bd1St.getString(6)));
	        }
	    	bd1St.close();
	    	sta1.close();
	    	
	    	//��ѯ��ر�statement���¿�һ��
	    	Statement sta11 = con.createStatement();
	    	String bed2Query = "select * from studentinfo where stuId = '"+bed2+"'";
	    	ResultSet bd2St = sta11.executeQuery(bed2Query);
	    	while(bd2St.next()) {
	    		obsList.add(new Person(bd2St.getString(1), bd2St.getString(2), bd2St.getString(3), bd2St.getString(4), bd2St.getString(5), bd2St.getString(6)));
	        }
	    	bd2St.close();
	    	sta11.close();
	    	
	    	//��ѯ��ر�statement���¿�һ��
	    	Statement sta111 = con.createStatement();
	    	String bed3Query = "select * from studentinfo where stuId = '"+bed3+"'";
	    	ResultSet bd3St = sta111.executeQuery(bed3Query);
	    	while(bd3St.next()) {
	    		obsList.add(new Person(bd3St.getString(1), bd3St.getString(2), bd3St.getString(3), bd3St.getString(4), bd3St.getString(5), bd3St.getString(6)));
	        }
	    	bd3St.close();
	    	sta111.close();
	    	
	    	//��ѯ��ر�statement���¿�һ��
	    	Statement sta1111 = con.createStatement();
	    	String bed4Query = "select * from studentinfo where stuId = '"+bed4+"'";
	    	ResultSet bd4St = sta1111.executeQuery(bed4Query);
	    	while(bd4St.next()) {
	    		obsList.add(new Person(bd4St.getString(1), bd4St.getString(2), bd4St.getString(3), bd4St.getString(4), bd4St.getString(5), bd4St.getString(6)));
	        }
	    	bd4St.close();
	    	sta1111.close();
	    	con.close();

		} 
		catch (Exception ex) {
			ex.printStackTrace();
		}   
    	
		// ���ر�
    	return buildTableView(obsList); 	
    }
	//���������������������������������������������������������������������������������������� �� �� �� �� ������������������������������������������������������������
	
    //���Ӳ���
    public void Insert(String StuId, String StudName, String College, String Major, String TheClass, int CheckIn) throws SQLException {
    	try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/dormitory?useSSL=false","root","123456");
			Statement sta = con.createStatement();
			System.out.print("����ɹ���");
			
			String Insert = "insert into studentinfo values('"+StuId+"', '"+StudName+"', '"+College+"', '"+Major+"', '"+TheClass+"', "+CheckIn+")";
	    	sta.executeUpdate(Insert);

	    	sta.close();
	    	con.close();
		} 
		catch (Exception ex) {
			ex.printStackTrace();
		}   	
    }
    
    //ɾ������
    public void Delete(String StuId) throws SQLException {
    	try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/dormitory?useSSL=false","root","123456");
			Statement sta = con.createStatement();
			System.out.print("����ɹ���");
			
			String Delete = "delete from studentinfo where stuId = '"+StuId+"'";
	    	sta.executeUpdate(Delete);

	    	sta.close();
	    	con.close();
		} 
		catch (Exception ex) {
			ex.printStackTrace();
		}   
    	
    }
       
    //����TableView
    @SuppressWarnings("unchecked")
	public TableView<Person> buildTableView(ObservableList<Person> obsList) {
        
    	//��������ͼ
    	tView = new TableView<Person>();
		
		//�����ж���
    	TableColumn<Person, String> tColId = new TableColumn<Person, String>("ѧ��");
		TableColumn<Person, String> tColName = new TableColumn<Person, String>("����");
		TableColumn<Person, String> tColCollege = new TableColumn<Person, String>("ѧԺ");
		TableColumn<Person, String> tColMajor = new TableColumn<Person, String>("רҵ");
		TableColumn<Person, String> tColClass = new TableColumn<Person, String>("�༶");
		TableColumn<Person, String> tColCheckIn = new TableColumn<Person, String>("�Ƿ���ס");
		
		//���ж�����ӵ�����ͼ
		tView.getColumns().addAll(tColId, tColName, tColCollege, tColMajor, tColClass, tColCheckIn);
		
		//�������б�ͱ���ͼ����
    	tView.setItems(obsList);
		
		//�������б���ж���������
    	tColId.setCellValueFactory(new PropertyValueFactory<Person, String>("StuId"));
		tColName.setCellValueFactory(new PropertyValueFactory<Person, String>("StuName")); 
		tColCollege.setCellValueFactory(new PropertyValueFactory<Person, String>("College"));
		tColMajor.setCellValueFactory(new PropertyValueFactory<Person, String>("Major"));
		tColClass.setCellValueFactory(new PropertyValueFactory<Person, String>("TheClass"));
		tColCheckIn.setCellValueFactory(new PropertyValueFactory<Person, String>("CheckIn"));
    	
		// ���ر�
    	return tView;
    }
    
    //����person��
    public class Person{
    	private String StuId;
    	private String StuName;
    	private String College;
    	private String Major;
    	private String TheClass;
        private String CheckIn;
    	
    	Person(String StuId, String StuName, String College, String Major, String TheClass, String CheckIn){
    		this.StuId = StuId;
    		this.StuName = StuName;
    		this.College = College;
    		this.Major = Major;
    		this.TheClass = TheClass;
    		this.CheckIn = CheckIn;
    	}
       
    	public String getStuId(){
    		return StuId;
    	}
    	
    	public void setStuId(String StuId){
    		this.StuId = StuId;
    	}
    	
    	public String getStuName(){
    		return StuName;
    	}
    	
    	public void setStuName(String StuName){
    		this.StuName = StuName;
    	}
    	
    	public String getCollege(){
    		return College;
    	}
    	
    	public void setCollege(String College){
    		this.College = College;
    	}
    	
    	public String getMajor(){
    		return Major;
    	}
    	
    	public void setMajor(String Major){
    		this.Major = Major;
    	}
    	
    	public String getTheClass(){
    		return TheClass;
    	}
    	
    	public void setTheClass(String TheClass){
    		this.TheClass = TheClass;
    	}
    	
    	public String getCheckIn(){
    		return CheckIn;
    	}
    	
    	public void setCheckIn(String CheckIn){
    		this.CheckIn = CheckIn;
    	}
    }
}
