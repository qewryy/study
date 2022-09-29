package t0927;

public class Tmain {

	public static void main(String[] args) {
		Tmanager manager = new Tmanager();
		boolean run = true;

		while (run) {
			int st = manager.manu();

			switch (st) {
			case 0:
				System.out.println("\n[0]종료");
				run = false;
				break;
			case 1:
				manager.add();
				break;
			case 2:
				manager.list();
				break;
			case 3:
				manager.order();
				break;
			case 4:
				manager.orderlist();
				break;
			case 5:
				manager.ordercancel();
				break;

			default:
				System.out.println("메뉴를 다시 확인해주세요.");
				break;
			}
		}

	}

}
