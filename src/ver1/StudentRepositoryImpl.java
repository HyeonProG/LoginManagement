package ver1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentRepositoryImpl implements StudentRepository {

	public static final String ADD_STUDENT = " insert into students(name, age, email) values(?, ?, ?) ";
	public static final String VIEW_ALL_STUDENT = " select * from students ";
	public static final String DELETE_STUDENT = " delete from students where email =? "; 
	public static final String UPDATE_STUDENT = " update students set name = ? where name = ? ";

	@Override
	public int createStudent(String name, int age, String email) throws SQLException {

		int resultRowCount = 0;

		try (Connection conn = DBConnectionManager.getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement(ADD_STUDENT);

			pstmt.setString(1, name);
			pstmt.setInt(2, age);
			pstmt.setString(3, email);
			pstmt.executeUpdate();

		}
		return resultRowCount;
	}

	@Override
	public List<StudentDTO> viewAllStudent() throws SQLException {

		List<StudentDTO> list = new ArrayList<>();

		try (Connection conn = DBConnectionManager.getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement(VIEW_ALL_STUDENT);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int age = rs.getInt("age");
				String email = rs.getString("email");
				list.add(new StudentDTO(id, name, age, email));
			}
			return list;
		}

	}

	@Override
	public int deleteStudent(String email) throws SQLException {

		int resultRowCount = 0;

		try (Connection conn = DBConnectionManager.getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement(DELETE_STUDENT);

			pstmt.setString(1, email);
			resultRowCount = pstmt.executeUpdate();

		}
		return resultRowCount;

	}

	@Override
	public int updateStudent(String newName, String name) throws SQLException {
		int resultRowCount = 0;

		try (Connection conn = DBConnectionManager.getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement(UPDATE_STUDENT);

			pstmt.setString(1, newName);
			pstmt.setString(2, name);
			resultRowCount = pstmt.executeUpdate();

		}
		return resultRowCount;
	
	}

}
