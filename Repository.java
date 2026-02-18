import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Repository {


    private final String DB_URL = "jdbc:mysql://lucy_local:3306/cc3_task3";
    private final String USER = "root";
    private final String PASS = "";
    private Connection conn;

    public Repository() throws Exception {
        connect();
    }

    private void connect() throws Exception {
        conn = DriverManager.getConnection(DB_URL, USER, PASS);
    }

    public void save(Student s) throws Exception {
        String sql = "INSERT INTO students VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
       
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, s.getStudentID());
        ps.setString(2, s.getFirstName());
        ps.setString(3, s.getMiddleName());
        ps.setString(4, s.getLastName());
        ps.setInt(5, s.getAge());
        ps.setInt(6, s.getYearLevel());
        ps.setString(7, s.getProgram());
        ps.setString(8, s.getContactNo());
        ps.setString(9, s.getBarangay());
        ps.setString(10, s.getEmail());

        ps.executeUpdate();
    }

    public List<Student> findAll() throws Exception {
        List<Student> list = new ArrayList<>();
        String sql = "SELECT * FROM students";

        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(sql);

        while (rs.next()) {
            Student s = new Student.Builder()
                    .setStudentID(rs.getInt("student_id"))
                    .setFirstName(rs.getString("first_name"))
                    .setMiddleName(rs.getString("middle_name"))
                    .setLastName(rs.getString("last_name"))
                    .setAge(rs.getInt("age"))
                    .setYearLevel(rs.getInt("year_level"))
                    .setProgram(rs.getString("program"))
                    .setContactNo(rs.getString("contact_no"))
                    .setBarangay(rs.getString("barangay"))
                    .setEmail(rs.getString("email"))
                    .build();

            list.add(s);
        }
        return list;
    }

    public void close() throws Exception {
        conn.close();
    }
}

