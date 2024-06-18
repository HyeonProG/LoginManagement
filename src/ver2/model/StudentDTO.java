package ver2.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

// DTO의 기능은 단지 데이터만 담는 역할을 하는것은 아니다.
// 기능도 추가 가능하다.
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class StudentDTO {

	private int id;
	private String name;
	private int age;
	private String email;
	
}
