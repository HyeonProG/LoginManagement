package ver1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class StudentManagementSystem {

	private static final Logger LOGGER = Logger.getLogger(StudentManagementSystem.class.getName());

	public static void main(String[] args) {
		StudentRepositoryImpl studentRepositoryImpl = new StudentRepositoryImpl();
		StudentDTO student = new StudentDTO();

		try {
			Connection conn = DBConnectionManager.getConnection();
			Scanner scanner = new Scanner(System.in);
			while (true) {
				System.out.println();
				System.out.println("1. 로그인 하기");
				System.out.println("2. 아이디 생성");
				System.out.println("3. 학생 정보 수정 하기");
				System.out.println("4. 학생 전체 정보 보기");
				System.out.println("5. 학생 정보 삭제");
				System.out.println("6. 종료");
				System.out.print("옵션을 선택하세요 : ");

				int choice = scanner.nextInt();
				scanner.nextLine();
				if (choice == 1) {
					System.out.println("로그인");
					System.out.print("이름을 입력하세요 : ");
					String name = scanner.next();

					System.out.print("이메일을 입력하세요 : ");
					String email = scanner.next();

					if (authenticateStudent(conn, name, email)) {
						System.out.println("로그인 성공!");
					} else {
						System.out.println("로그인 실패 - 이름과 이메일을 확인하세요.");
					}

				} else if (choice == 2) {
					System.out.println("아이디 생성");
					System.out.print("이름을 입력하세요 : ");
					String name = scanner.next();

					System.out.print("나이를 입력하세요 : ");
					int age = scanner.nextInt();

					System.out.print("이메일을 입력하세요 : ");
					String email = scanner.next();

					studentRepositoryImpl.createStudent(name, age, email);

					System.out.println("아이디가 등록되었습니다.");

				} else if (choice == 3) {
					System.out.println("학생 정보 수정 하기");
					System.out.println("수정하고 싶은 이름을 입력하세요.");
					String newName = scanner.next();

					System.out.println("기존 이름을 입력하세요.");
					String name = scanner.next();

					int result = studentRepositoryImpl.updateStudent(newName, name);
					if (result > 0) {
						System.out.println("수정이 완료되었습니다.");
					} else {
						System.out.println("잘못된 입력입니다 다시 입력해주세요.");
						break;
					}

				} else if (choice == 4) {
					System.out.println("학생 전체 정보 보기");
					List<StudentDTO> dto = studentRepositoryImpl.viewAllStudent();
					for (StudentDTO studentDTO : dto) {
						System.out.println(studentDTO);
					}
				} else if (choice == 5) {
					System.out.println("학생 정보 삭제 하기");
					System.out.print("삭제할 이메일을 입력하세요 : ");
					String email = scanner.nextLine();
					
					int result = studentRepositoryImpl.deleteStudent(email);
					if (result > 0) {
						System.out.println("아이디가 삭제되었습니다.");						
					} else {
						System.out.println("잘못된 입력입니다 다시 입력해주세요.");
						break;
					}

				} else if (choice == 6) {

					System.out.println("종료합니다.");
					break;
				} else {
					System.out.println("다시 선택해 주세요.");
				}

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	} // end of main

	private static boolean authenticateStudent(Connection conn, String name, String email) {
		String query1 = " select * from students where name = ? and email = ? ";
		boolean result = false;

		try {
			PreparedStatement pstmt1 = conn.prepareStatement(query1);
			pstmt1.setString(1, name);
			pstmt1.setString(2, email);
			ResultSet rs1 = pstmt1.executeQuery();

			result = rs1.next();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

} // end of class
