package T0929;

public class Tmain {

	public static void main(String[] args) {
		Tmanager manager = new Tmanager();
		boolean run = true;

		while (run) {
			int st = manager.menu();
			if (manager.getLoginck() == null) {
				switch (st) {
				case 0:
					System.out.println("프로그램 종료");
					run = false;
					break;
				case 1:
					manager.memberJoin();
					break;
				case 2:
					manager.login();
					break;
				default:
					System.out.println("메뉴를 확인해주세요.");
					break;
				}
			} else if (manager.getLoginck().equals("admin")) {
				switch (st) {
				case 0:
					manager.logout();
					break;
				case 1:
					manager.pr();
					break;
				case 2:
					manager.dt();
					break;
				case 3:
					System.out.println("[3]상품리스트");
					manager.list();
					break;

				default:
					System.out.println("메뉴를 확인해주세요.");
					break;
				}
			} else {
				switch (st) {
				case 0:
					manager.logout();
					break;
				case 1:
					manager.order();
					break;
				case 2:
					manager.orderlist();
					break;
				case 3:
					System.out.println("[3]상품리스트");
					manager.list();
					break;
				case 4:
					manager.info();
					break;
				case 5:
					manager.withdrawal();
					break;
				default:
					System.out.println("메뉴를 확인해주세요.");
					break;
				}
			}
		}

	}

}
