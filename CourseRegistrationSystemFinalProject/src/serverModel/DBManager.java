package serverModel;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * The class DBManager is simulating a database for our program. The data base is a SQL database.
 * 
 * @author Aidan Johnson and Michele Piperni
 * @version 1.0
 * @since April 15, 2020
 */
public class DBManager implements Credentials
{
	/**
	 * The ArrayList of type Course holds all the course objects in the course registration application.
	 */
	private ArrayList<Course> courseList;
	
	/**
	 * The ArrayList of type Student holds the student objects in the course registration application.
	 */
	private ArrayList<Student> studentList;
	
	/**
	 * The Connection object conn deals with the connection to the data base.
	 */
	private Connection conn;
	
	/**
	 * The Statement object stmt enables the creation of Query statements.
	 */
	private Statement stmt;
	
	/**
	 * The ResultSet object rs stores the results of the query.
	 */
	private ResultSet rs;

	/**
	 * Constructs a DBManager object by creating a new array list of courses and students. It also initializes the connection
	 * with the SQL database.
	 */
	public DBManager() 
	{
		courseList = new ArrayList<Course>();
		studentList = new ArrayList<Student>();
		initializeConnection();
	}

	/**
	 * Initializes the connection for the driver and opens the connection.
	 */
	public void initializeConnection() 
	{
		try 
		{
			//Register JDBC driver
			Driver driver = new com.mysql.cj.jdbc.Driver();
			DriverManager.registerDriver(driver);
			
			//Open a connection
			conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Closes the connection and the resultSet object.
	 */
	public void close()
	{
		try
		{
			rs.close();
			conn.close();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Inserts a student using a prepared statement into the Student table in the database.
	 * 
	 * @param id the id of the student being added.
	 * @param name the name of the student being added.
	 * @param password the password of the student being added.
	 */
	public void insertStudentPreparedStatement(int id, String name, String password)
	{
		try
		{
			String query = "INSERT INTO STUDENT (ID, name, password) values(?,?,?)";
			PreparedStatement pStat = conn.prepareStatement(query);
			pStat.setInt(1, id);
			pStat.setString(2, name);
			pStat.setString(3, password);
			pStat.executeUpdate();
			pStat.close();
		}
		catch (SQLException e)
		{
			System.out.println("Problem inserting Student!");
			e.printStackTrace();
		}
	}
	
	/**
	 * Inserts an admin using a prepared statement into the Admin table in the database.
	 * 
	 * @param id the id of the admin being added.
	 * @param name the name of the admin being added.
	 * @param password the password of the admin being added.
	 */
	public void insertAdminPreparedStatement(int id, String name, String password)
	{
		try
		{
			String query = "INSERT INTO ADMIN (ID, name, password) values(?,?,?)";
			PreparedStatement pStat = conn.prepareStatement(query);
			pStat.setInt(1, id);
			pStat.setString(2, name);
			pStat.setString(3, password);
			pStat.executeUpdate();
			pStat.close();
		}
		catch (SQLException e)
		{
			System.out.println("Problem inserting Admin!");
			e.printStackTrace();
		}
	}
	
	/**
	 * Inserts a course using a prepared statement into the Course table in the database.
	 * 
	 * @param id the id of the course being added.
	 * @param name the name of the course being added.
	 */
	public void insertCoursePreparedStatement(int id, String name)
	{
		try
		{
			String query = "INSERT INTO COURSE (ID, courseName) values(?,?)";
			PreparedStatement pStat = conn.prepareStatement(query);
			pStat.setInt(1, id);
			pStat.setString(2, name);
			pStat.executeUpdate();
			pStat.close();
		}
		catch (SQLException e)
		{
			System.out.println("Problem inserting course!");
			e.printStackTrace();
		}
	}

	/**
	 * Inserts a course using a prepared statement into the Course table in the database. This function is for the admin
	 * inserting a course and it returns a string displaying if the course was added successfully or not in the application.
	 * 
	 * @param id the id of the course being added.
	 * @param name the name of the course being added.
	 * @returns a String displaying if the course was added to the application successfully or not.
	 */
	public String insertCourse(int id, String name)
	{
		try
		{
			String query = "INSERT INTO COURSE (ID, courseName) values(?,?)";
			PreparedStatement pStat = conn.prepareStatement(query);
			pStat.setInt(1, id);
			pStat.setString(2, name);
			pStat.executeUpdate();
			pStat.close();
			return name + id + " was successfully added to the database.";
		}
		catch (SQLException e)
		{
			System.out.println("Problem inserting course!");
			e.printStackTrace();
		}
		
		//Returns null if the course was unable to be added to the course table in the SQL database
		return null;
	}
	
	/**
	 * Creates the Student table in the SQL database. The student table has 
	 */
	public void createStudentTable()
	{
		String sql = "CREATE TABLE STUDENT " + "(id INTEGER not NULL, " + " name VARCHAR(255), " + " password VARCHAR(255), " + " PRIMARY KEY (id))";
		
		try
		{
			stmt = conn.createStatement(); // construct a statement
			stmt.executeUpdate(sql); // execute my query (i.e. sql)
			stmt.close();
			System.out.println("Created Student table in the data base!");
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			System.out.println("Student table can NOT be created!");
		}
	}
	
	/**
	 * Creates the course table in the SQL database.
	 * 
	 */
	public void createCourseTable()
	{
		String sql = "CREATE TABLE COURSE " + "(id INTEGER not NULL, " + " courseName VARCHAR(255), " + " PRIMARY KEY (id))";
		
		try 
		{
			stmt = conn.createStatement(); // construct a statement
			stmt.executeUpdate(sql); // execute my query (i.e. sql)
			stmt.close();
			System.out.println("Created Course table in the database!");
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			System.out.println("Course table can NOT be created!");
		}
	}
	
	/**
	 * Creates the admin table in the SQL database.
	 */
	public void createAdminTable()
	{
		String sql = "CREATE TABLE ADMIN " + "(id INTEGER not NULL, " + " name VARCHAR(255), " + " password VARCHAR(255), " + " PRIMARY KEY (id))";
		
		try
		{
			stmt = conn.createStatement(); // construct a statement
			stmt.executeUpdate(sql); // execute my query (i.e. sql)
			stmt.close();
			System.out.println("Created Student table in the data base!");
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			System.out.println("Student table can NOT be created!");
		}
	}
	
	/**
	 * Validates the student login by checking if the id and password entered by the student matches what is in the database.
	 * If the id is valid and a matching password was entered it returns a string holding "true" otherwise it returns "false".
	 * 
	 * @param id the id of the student entered being checked to see if the login attempt is valid.
	 * @param password the password of the student entered being checked to see if the login attempt is valid.
	 * @returns the message "true" if the login attempt was valid otherwise it returns "false".
	 */
	public String validateStudentLogin(int id, String password)
	{
		try
		{
			stmt = conn.createStatement();
			String query = "SELECT * FROM STUDENT WHERE id = '" + id + "' and password ='" + password + "'";
			rs = stmt.executeQuery(query);
			if(rs.next()) 
			{
				System.out.println("Student logged in");
				String studentName = rs.getString("name");
				return studentName;
			}
			else
			{
				System.out.println("Invalid id or password");
				return "false";
			}
		}
		catch(SQLException error)
		{
			System.err.println("Error in validating login.");
		}
		return "false";
	}
	
	/**
	 * Validates the admin login by checking if the id and password entered by the admin matches what is in the database.
	 * If the id is valid and a matching password was entered it returns a string holding "true" otherwise it returns "false".
	 * 
	 * @param id the id of the admin entered being checked to see if the login attempt is valid.
	 * @param password the password of the admin entered being checked to see if the login attempt is valid.
	 * @returns the admin name if the login attempt was valid otherwise it returns "false".
	 */
	public String validateAdminLogin(int id, String password)
	{
		try
		{
			stmt = conn.createStatement();
			String query = "SELECT * FROM ADMIN WHERE id = '" + id + "' and password ='" + password + "'";
			rs = stmt.executeQuery(query);
			if(rs.next()) 
			{
				System.out.println("Admin logged in");
				String adminName = rs.getString("name");
				return adminName;
			}
			else
			{
				System.out.println("Invalid id or password");
				return "false";
			}
		}
		catch(SQLException error)
		{
			System.err.println("Error in validating login.");
		}
		return "false";
	}
	
	/**
	 * 
	 */
	public void readStudentsFromFile()
	{
		try 
		{
			//Note: StudentList.txt needs to be in the project folder
			FileReader fileRead = new FileReader("StudentList.txt");
			BufferedReader bufferRead = new BufferedReader(fileRead);
			
			String line = "";
			while((line = bufferRead.readLine()) != null)
			{
				String[] temp = line.split(" ");
				insertStudentPreparedStatement(Integer.parseInt(temp[0]), temp[1], temp[2]);
			}
		}
		catch(Exception e)
		{
			System.err.println(e.getMessage());
		}
	}
	
	/**
	 * 
	 */
	public void readAdminsFromFile()
	{
		try 
		{
			//Note: Admins.txt needs to be in the project folder
			FileReader fileRead = new FileReader("Admins.txt");
			BufferedReader bufferRead = new BufferedReader(fileRead);
			
			String line = "";
			while((line = bufferRead.readLine()) != null)
			{
				String[] temp = line.split(" ");
				insertAdminPreparedStatement(Integer.parseInt(temp[0]), temp[1], temp[2]);
			}
		}
		catch(Exception e)
		{
			System.err.println(e.getMessage());
		}
	}
	
	/**
	 * 
	 */
	public void readCoursesFromFile()
	{
		try 
		{
			//Note: courses.txt needs to be in the project folder
			FileReader fileRead = new FileReader("courses.txt");
			BufferedReader bufferRead = new BufferedReader(fileRead);
			
			String line = "";
			while((line = bufferRead.readLine()) != null)
			{
				String[] temp = line.split(" ");
				insertCoursePreparedStatement(Integer.parseInt(temp[1]), temp[0]);
			}
		}
		catch(Exception e)
		{
			System.err.println(e.getMessage());
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public ArrayList<Course> readCoursesFromDB() 
	{
		String query = "SELECT * FROM course";
		courseList = new ArrayList<Course>();
		
		try
		{
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			
			while(rs.next())
			{
				int courseID = rs.getInt("id");
				String courseName = rs.getString("courseName");
				courseList.add(new Course(courseName, courseID));
			}
		}
		catch(SQLException error)
		{
			error.printStackTrace();
		}
		
		return courseList;
	}
	
	/**
	 * 
	 * @return
	 */
	public ArrayList<Student> readStudentsFromDB()
	{
		String query = "SELECT * FROM student";
		studentList = new ArrayList<Student>();
		
		try
		{
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			
			while(rs.next())
			{
				int studentID = rs.getInt("id");
				String studentName = rs.getString("name");
				studentList.add(new Student(studentName, studentID));
			}
		}
		catch(SQLException error)
		{
			error.printStackTrace();
		}
		
		return studentList;
	}

	/**
	 * Only run this function once before using the course registration application. The function called in this main generate
	 * the SQL database with the student, course and admin tables as well as populates them from the text files.
	 * 
	 * @param args Default.
	 */
	public static void main(String[] args)
	{
		DBManager myApp = new DBManager();
		
		//Creating the three different database tables
		myApp.createStudentTable();
		myApp.createCourseTable();
		myApp.createAdminTable();
		
		//Reading from three different text files to populate the tables
		myApp.readStudentsFromFile();
		myApp.readCoursesFromFile();
		myApp.readAdminsFromFile();
		
		//Properly closing the Database
		myApp.close();
	}
}