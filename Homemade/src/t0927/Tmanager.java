package t0927;

import java.util.ArrayList;
import java.util.Scanner;

public class Tmanager {

	private Scanner s = new Scanner(System.in);
	private ArrayList<Tdto_1> list = new ArrayList<>();
	private ArrayList<Tdto_2> Odlist = new ArrayList<>();
	private int z = 1;
	private int od = 1;

	// 메뉴 메소드
	public int manu() {
		System.out.println("\n===============================================================");
		System.out.println("[0]종료 | [1]상품추가 | [2]상품리스트 | [3]주문 | [4]주문리스트 | [5]주문취소");
		System.out.println("===============================================================");

		System.out.println("메뉴선택 >>");
		return s.nextInt();
	}

	// 상품추가 메소드
	public void add() {
		System.out.println("\n[1]상품추가\n");
		System.out.println("이름을 입력해주세요 :");
		String name = s.next();
		System.out.println("수량을 입력해주세요 :");
		int qty = s.nextInt();
		System.out.println("가격을 입력해주세요 :");
		int price = s.nextInt();
		if (qty < 0 || price < 0) {
			System.out.println("0보다 작은 수를 넣을 수 없습니다.");
		} else {
			Tdto_1 dto = new Tdto_1();
			dto.setName(name);
			dto.setQty(qty);
			dto.setPrice(price);
			dto.setNum(z++);
			list.add(dto);
			System.out.println("정상적으로 추가되었습니다.");
		}
	}

	// 상품리스트 메소드
	public void list() {
		System.out.println("\n[2]상품리스트\n");
		if (list.size() == 0) {
			System.out.println("상품이 존재하지 않습니다.");
		} else {
			System.out.println("[번호]\t이름\t재고\t가격");
			System.out.println("=================================");
			for (int i = 0; i < list.size(); i++) {
				System.out.println("[" + list.get(i).getNum() + "]\t" + list.get(i).getName() + "\t"
						+ list.get(i).getQty() + "개\t" + list.get(i).getPrice() + "원");
			}
		}

	}

	// 주문 메소드
	public void order() {
		System.out.println("\n[3]주문\n");
		System.out.println("주문하실 제품번호을 입력하세요 :");
		int num = s.nextInt();
		boolean ck = false;
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getNum() == num) {
				ck = true;
				System.out.println(list.get(i).toString());
				System.out.println("주문하실 수량을 입력해주세요 :");
				int qty = s.nextInt();
				if (qty < 0) {
					System.out.println("0보다 작은 수는 입력할 수 없습니다.");
				} else {
					if (list.get(i).getQty() < qty) {
						System.out.println("상품 재고가 부족합니다.");
					} else {
						Tdto_2 dto = new Tdto_2();
						dto.setName(list.get(i).getName());
						dto.setQty(qty);
						dto.setPrice(list.get(i).getPrice() * qty);
						dto.setNum(od++);
						Odlist.add(dto);
						list.get(i).setQty(list.get(i).getQty() - qty);
						System.out.println("정상적으로 주문되었습니다.");
					}
				}
			}
		}
		if (!ck) {
			System.out.println("존재하지 않는 상품번호 입니다.");
		}
	}

	// 주문리스트 메소드
	public void orderlist() {
		System.out.println("\n[4]주문리스트\n");
		if (Odlist.size() == 0) {
			System.out.println("주문내역이 없습니다.");
		} else {
			System.out.println("[번호]\t이름\t재고\t총가격");
			System.out.println("=================================");
			for (int i = 0; i < Odlist.size(); i++) {
				System.out.println("[" + Odlist.get(i).getNum() + "]번\t" + Odlist.get(i).getName() + "\t"
						+ Odlist.get(i).getQty() + "개\t" + Odlist.get(i).getPrice() + "원");
			}
		}
	}

	// 주문취소 메소드
	public void ordercancel() {
		System.out.println("\n[5]주문취소\n");
		if (Odlist.size() == 0) {
			System.out.println("주문내역이 없습니다.");
		} else {
			Boolean c = false;
			System.out.println("취소하실 주문번호를 입력해주세요 :");
			int ck = s.nextInt();
			for (int i = 0; i < Odlist.size(); i++) {
				if (Odlist.get(i).getNum() == ck) {
					c = true;
					for (int b = 0; b < list.size(); b++) {
						if (list.get(b).getName().equals(Odlist.get(i).getName())) {
							list.get(b).setQty(Odlist.get(i).getQty() + list.get(b).getQty());
						}
					}
					Odlist.remove(i);
					System.out.println("주문이 취소되었습니다.");
				}
			}
			if (!c) {
				System.out.println("존재하지 않는 주문번호 입니다.");
			}
		}
	}

}
