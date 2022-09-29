package T0929;

import java.util.ArrayList;
import java.util.Scanner;

public class Tmanager {
	private Scanner s = new Scanner(System.in);
	private String loginck = null;

	private Util u = new Util();
	private Tdao dao = new Tdao();

	public String getLoginck() {
		return loginck;
	}

	public int menu() {
		if (loginck == null) {
			System.out.println("\n============================");
			System.out.println("[0]종료 | [1]회원가입 | [2]로그인");
			System.out.println("============================");
		} else if (loginck.equals("admin")) {
			System.out.println("\n============================================");
			System.out.println("[0]로그아웃 | [1]상품등록 | [2]상품삭제 | [3]상품리스트");
			System.out.println("============================================");
		} else {
			System.out.println("\n====================================================================");
			System.out.println("[0]로그아웃 | [1]상품주문 | [2]주문내역 | [3]상품리스트 | [4]내정보확인 | [5]회원탈퇴");
			System.out.println("====================================================================");
		}
		System.out.println("메뉴선택 >>");
		return s.nextInt();
	}

	private int check(String id) {
		int ck = -1;
		for (int i = 0; i < dao.Mlist.size(); i++) {
			if (dao.Mlist.get(i).getId().equals(id)) {
				ck = i;
			}
		}
		return ck;
	}

	// 회원가입 메소드
	public void memberJoin() {
		System.out.println("[1]회원가입\n");
		listset();
		System.out.println("가입할 아이디 >>");
		String id = s.next();
		int ck = check(id);
		if (ck != -1) {
			System.out.println("이미 존재하는 아이디 입니다.");
		} else {
			System.out.println("비밀번호 >>");
			String pw = s.next();
			System.out.println("회원 이름 >>");
			String name = s.next();
			System.out.println("나이 >>");
			int age = u.Intck();
			System.out.println("생년월일(YYYY-MM-DD) >>");
			String birth = s.next();
			memberDto member = new memberDto();
			member.setId(id);
			member.setPw(pw);
			member.setName(name);
			member.setAge(age);
			member.setBirth(birth);
			int result = dao.Join(member);
			if (result == 0) {
				System.out.println("회원가입에 실패했습니다.");
			} else {
				System.out.println("정상적으로 회원가입 되었습니다.");
				System.out.println("아이디 : " + member.getId());
				System.out.println("비밀번호 : " + member.getPw());
				System.out.println("회원이름 : " + member.getName());
				System.out.println("나이 : " + member.getAge());
				System.out.println("생년월일 : " + member.getBirth());
			}
		}
	}

	// 로그인 메소드
	public void login() {
		System.out.println("[2]로그인");
		listset();
		System.out.println("아이디를 입력하세요 >>");
		String id = s.next();
		System.out.println("비밀번호를 입력하세요 >>");
		String pw = s.next();
		int ck = check(id);
		if (ck == -1) {
			System.out.println("아이디 또는 비밀번호가 틀렸습니다.");
		} else {
			if (dao.Mlist.get(ck).getPw().equals(pw) && dao.Mlist.get(ck).getId().equals(id)) {
				System.out.println("\n로그인에 성공했습니다.");
				loginck = id;
			} else {
				System.out.println("아이디 또는 비밀번호가 틀렸습니다.");
			}
		}

	}

	// 로그아웃 메소드
	public void logout() {
		System.out.println("[0]로그아웃");
		loginck = null;
		System.out.println("로그아웃 되었습니다.");
	}

	// 상품등록 메소드
	public void pr() {
		System.out.println("[1]상품등록");
		listset();
		while (true) {

			System.out.println("등록하실 상품의 코드(4글자) >>");
			String code = "P" + s.next();
			if (code.length() == 5) {
				while (true) {
					System.out.println("등록하실 상품의 이름(1~30글자) >>");
					String name = s.next();
					if (name.length() > 0) {
						System.out.println("등록하실 상품의 가격 >>");
						int price = u.Intck();
						System.out.println("등록하실 상품의 수량 >>");
						int amount = u.Intck();
						System.out.println("등록하실 상품의 종류 >>");
						String type = s.next();
						ListupDto List = new ListupDto();
						List.setListset(code, name, price, amount, type);
						dao.list.add(List);
						int rs = dao.pr(List);
						if (rs == 1) {
							System.out.println("상품이 정상적으로 등록되었습니다.");
							break;
						} else {
							System.out.println("상품 등록에 실패했습니다.");
						}
					} else if (name.length() > 30) {
						System.out.println("상품명은 1 ~ 30글자 사이로 입력바랍니다.");
						continue;
					} else {
						System.out.println("상품명을 입력바랍니다.");
						continue;
					}
				}
				break;
			} else {
				System.out.println("코드는 4개의 숫자만 입력이 가능합니다.");
				continue;
			}
		}
	}

	// 상품삭제 메소드
	public void dt() {
		System.out.println("[2]상품삭제");
		listset();
		boolean ck = false;
		if (dao.list.size() == 0) {
			System.out.println("상품 정보가 없습니다.");
		} else {
			while (true) {
				list();
				System.out.println("삭제하실 상품의 코드를 입력해주세요 >>");
				String code = "P" + s.next();
				for (int i = 0; i < dao.list.size(); i++) {
					if (dao.list.get(i).getLtcode().equals(code)) {
						int rs = dao.Pddt(code);
						if (rs != 0) {
							System.out.println("정상적으로 삭제되었습니다.");
							ck = true;
							break;
						} else {
							System.out.println("삭제에 실패했습니다.");
						}
					}
				}
				if (!ck) {
					System.out.println("상품 코드가 존재하지 않습니다.");
					continue;
				} else {
					break;
				}
			}
		}

	}

	// 상품리스트 메소드
	public void list() {
		listset();
		if (dao.list.size() == 0) {
			System.out.println("\n상품 정보가 없습니다.");
		} else {
			System.out.println("\n================================================");
			System.out.println("상품번호\t상품코드\t상품명\t상품가격\t상품수량\t상품종류");
			System.out.println("================================================");
			for (ListupDto sc : dao.list) {
				System.out.println(sc.toString());
			}
			System.out.println("================================================");
		}
	}

	private void listset() {
		if (dao.list.size() != 0) {
			dao.list.clear();
			dao.listset();
		} else {
			dao.listset();
		}

		if (dao.Olist.size() != 0) {
			dao.Olist.clear();
			dao.Olistset();
		} else {
			dao.Olistset();
		}

		if (dao.Mlist.size() != 0) {
			dao.Mlist.clear();
			dao.Mlistset();
		} else {
			dao.Mlistset();
		}
	}

	// 상품주문 메소드
	public void order() {
		System.out.println("[1]상품주문");
		listset();
		if (dao.list.size() == 0) {
			System.out.println("상품이 존재하지 않습니다.");
		} else {
			while (true) {
				list();
				System.out.println("주문하실 상품 번호를 입력하세요.");
				int num = u.Intck();
				int rs1 = listnumck(num);
				if (rs1 != -1) {
					while (true) {
						System.out.println("주문할 수량 >>");
						int amount = u.Intck();
						Boolean rs2 = listatck(rs1, amount);
						if (rs2) {
							int rs3 = dao.order(loginck, dao.list.get(rs1).getLtcode(), amount);
							if (rs3 != 0) {
								System.out.println("주문이 완료되었습니다.");
								break;
							} else {
								System.out.println("주문에 실패했습니다.");
							}
						} else {
							System.out.println("수량이 부족합니다.");
							continue;
						}
					}
					break;
				} else {
					System.out.println("존재하지 않는 상품번호 입니다.");
					continue;
				}
			}
		}

	}

	// 상품수량 확인 메소드
	public boolean listatck(int i, int amount) {
		boolean rs = false;
		if (dao.list.get(i).getLtamount() >= amount) {
			rs = true;
		}
		return rs;
	}

	// 상품번호 확인 메소드
	public int listnumck(int num) {
		int rs = -1;
		for (int i = 0; i < dao.list.size(); i++) {
			if (dao.list.get(i).getNum() == num) {
				rs = i;
				break;
			}
		}
		return rs;
	}

	// 주문내역 메소드
	public void orderlist() {
		System.out.println("[2]주문내역");
		listset();
		if (dao.Olist.size() == 0) {
			System.out.println("\n주문 정보가 없습니다.");
		} else {
			System.out.println("\n========================================================");
			System.out.println("주문번호\t주문자ID\t주문수량\t주문코드\t\t주문일자");
			System.out.println("========================================================");
			for (OrderDto sc : dao.Olist) {
				System.out.println(sc.toString());
			}
			System.out.println("========================================================\n");
		}
	}

	// 내 정보확인 메소드
	public void info() {
		System.out.println("[4]내정보확인\n");
		listset();
		for (int i = 0; i < dao.Mlist.size(); i++) {
			if (dao.Mlist.get(i).getId().equals(loginck)) {
				System.out.println("아이디 : " + dao.Mlist.get(i).getId());
				System.out.println("비밀번호 : " + dao.Mlist.get(i).getPw());
				System.out.println("회원이름 : " + dao.Mlist.get(i).getName());
				System.out.println("나이 : " + dao.Mlist.get(i).getAge());
				System.out.println("생년월일 : " + dao.Mlist.get(i).getBirth());
				break;
			}
		}
	}

	// 회원탈퇴 메소드
	public void withdrawal() {
		System.out.println("[5]회원탈퇴\n");
		listset();
		System.out.println("고객님의 모든 주문내역이 삭제됩니다.");
		dao.loop(loginck);
		int dr = dao.ordercancle(loginck);
		if (dr != 0) {
			int rs = dao.usdelete(loginck);
			if (rs != 0) {
				System.out.println(loginck + "님 계정이 탈퇴되었습니다.");
				loginck = null;
			} else {
				System.out.println("회원탈퇴에 실패했습니다.");
			}
		} else {
			System.out.println("주문내역 삭제가 정상적으로 이뤄지지 않았습니다.");
		}
	}

}
