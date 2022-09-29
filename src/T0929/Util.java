package T0929;

import java.util.Scanner;

public class Util {
	private Scanner s = new Scanner(System.in);
	private Tdao dao = null;
	
	// nextInt 오류 확인 메소드
	int Intck() {
		int rs = 0;
		while (true) {
			if (!s.hasNextInt()) {
				System.out.println("숫자를 입력해주세요.");
				s.nextLine();
				continue;
			} else {
				rs = s.nextInt();
				break;
			}
		}
		return rs;
	}


}
