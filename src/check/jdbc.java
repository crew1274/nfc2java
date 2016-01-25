package check;

import java.sql.Connection; 
import java.sql.DriverManager; 
import java.sql.PreparedStatement; 
import java.sql.ResultSet; 
import java.sql.SQLException; 
import java.sql.Statement; 
 
public class jdbc { 
  private Connection con = null; //Database objects 
  //�s��object 
  private Statement stat = null; 
  //����,�ǤJ��sql������r�� 
  private ResultSet rs = null; 
  //���G�� 
  private PreparedStatement pst = null; 
  //����,�ǤJ��sql���w�x���r��,�ݭn�ǤJ�ܼƤ���m 
  //���Q��?�Ӱ��Х� 
  
  private String dropdbSQL = "check"; 
  private String selectSQL = "select * from check ";
  private static String createdbSQL = "CREATE TABLE nfc.checkout(" + 
		    "    serial     VARCHAR(10) " + 
		    "  , year    INTEGER " + 
		    "  , month    INTEGER " + 
		    "  , day    INTEGER " + 
		    "  , hr    INTEGER " + 
		    "  , min    INTEGER " + 
		    "  , sec    INTEGER )" ; 
  
  private String insertdbSQL = "insert into checkout('serial','year','month','day','hr','min','sec')";
  
  //private static String selectSQL = "select * from User where id='546' "; 
  
  public jdbc(String dbname,String account,String password) 
  { 
    try { 
      Class.forName("com.mysql.jdbc.Driver"); 
      //���Udriver 
      con = DriverManager.getConnection( 
      "jdbc:mysql://localhost/"+dbname+"?useUnicode=true&characterEncoding=Big5", 
      account,password); 
//���oconnection
//jdbc:mysql://localhost/test?useUnicode=true&characterEncoding=Big5
//localhost�O�D���W,test�Odatabase�W
//useUnicode=true&characterEncoding=Big5�ϥΪ��s�X 
      
    } 
    catch(ClassNotFoundException e) 
    { 
      System.out.println("DriverClassNotFound :"+e.toString()); 
    }//���i��|����sqlexception 
    catch(SQLException x) { 
      System.out.println("Exception :"+x.toString()); 
    } 
    
  } 
  //�إ�table���覡 
  //�i�H�ݬ�Statement���ϥΤ覡 
  public void createTable(String c) 
  { 
    try 
    { 
      stat = con.createStatement(); 
      stat.executeUpdate(c); 
    } 
    catch(SQLException e) 
    { 
      System.out.println("CreateDB Exception :" + e.toString()); 
    } 
    finally 
    { 
      close(); 
    } 
  } 
  //�s�W��� 
  //�i�H�ݬ�PrepareStatement���ϥΤ覡 
  // private String insertdbSQL = "insert into User(id,day,passwd) " 
	      // "select ifNULL(max(id),0)+1,?,? FROM User"; 
  public void insertTable(String c,String serial,int y,int m,int day,int hr,int min,int sec) 
  { 
    try 
    { 
      pst = con.prepareStatement(c); 
      pst.setString(1, serial); 
      pst.setInt(2, y); 
      pst.setInt(3, m); 
      pst.setInt(4, day); 
      pst.setInt(5, hr); 
      pst.setInt(6, min); 
      pst.setInt(7, sec); 
      pst.executeUpdate(); 
    } 
    catch(SQLException e) 
    { 
    
      System.out.println("InsertDB Exception :" + e.toString()); 
    } 
    finally 
    { 
      close(); 
    } 
  } 
  //�d�߸�� 
  //�i�H�ݬݦ^�ǵ��G���Ψ��o��Ƥ覡 
  
  public String SelectTable(String  d,String  c) 
  { 
	  String b = "";
    try 
    { 
      stat = con.createStatement(); 
      rs = stat.executeQuery(d+"'"+c+"'"); 
      //System.out.println("ID\t\tName\t\tPASSWORD"); 
      while(rs.next()) 
      { 
        System.out.println(rs.getString("serial")+"\t\t"+rs.getString("year")+"�~"+rs.getString("month")+"��"+
        		rs.getString("day")+"��"+rs.getString("hr")+"��"+rs.getString("min")+"��"+rs.getString("sec")+"��"
        		); 
        b=b+rs.getString("year")+"�~"+rs.getString("month")+"��"+
        		rs.getString("day")+"��"+rs.getString("hr")+"��"+rs.getString("min")+"��"+
        		rs.getString("sec")+"��\n"
        		
        		; 
      } 
    } 
    catch(SQLException e) 
    { 
      System.out.println("DropDB Exception :" + e.toString()); 
    } 
    finally 
    { 
      close(); 
    }
	return b; 
  } 
  
  public void checkdb(String c) 
  { 
    try 
    { 
      stat = con.createStatement(); 
      rs = stat.executeQuery(c); 
      System.out.println("ID\t\tName\t\tPASSWORD"); 
      while(rs.next()) 
      { 
        System.out.println(rs.getInt("id")+"\t\t"+ 
            rs.getString("name")+"\t\t"+rs.getString("passwd")); 
      } 
    } 
    catch(SQLException e) 
    { 
      System.out.println("DropDB Exception :" + e.toString()); 
    } 
    finally 
    { 
      close(); 
    } 
  }
  //����ϥΧ���Ʈw��,�O�o�n�����Ҧ�Object 
  //�_�h�b����Timeout��,�i��|��Connection poor�����p 
  public void close() 
  { 
    try 
    { 
      if(rs!=null) 
      { 
        rs.close(); 
        rs = null; 
      } 
      if(stat!=null) 
      { 
        stat.close(); 
        stat = null; 
      } 
      if(pst!=null) 
      { 
        pst.close(); 
        pst = null; 
      } 
    } 
    catch(SQLException e) 
    { 
      System.out.println("Close Exception :" + e.toString()); 
    } 
  } 
  
 
  public static void main(String[] args) 
  { 
    //���ݬݬO�_���` 
    jdbc test = new jdbc("nfc","root","50150"); 
    //test.dropTable(); 
    test.createTable(createdbSQL); 
    //test.insertTable("yku", "12356"); 
    //test.insertTable("yku2", "7890"); 
    //test.SelectTable("741DC5BE"); 
  
  } 
}