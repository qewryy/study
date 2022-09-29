package t0927;

public class Tdto_2 {

	private int num;
	private String name;
	private int qty;
	private int price;

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "상품번호 : [" + num + "]번 | 상품명 : " + name + " | 주문수량 : " + qty + "개 | 총 결재금액 : " + price + "원";
	}

}
