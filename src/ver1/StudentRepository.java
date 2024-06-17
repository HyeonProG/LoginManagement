package ver1;

import java.sql.SQLException;
import java.util.List;

public interface StudentRepository {

	int createStudent(String name, int age, String email) throws SQLException;
	
	List<StudentDTO> viewAllStudent() throws SQLException;
	
	int deleteStudent(String email) throws SQLException;
	
	int updateStudent(String newName, String name) throws SQLException;
	
}
