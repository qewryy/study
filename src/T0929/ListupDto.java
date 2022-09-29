package T0929;

public class ListupDto {
	private int num;
	private String ltcode;	// 상품코드
	private String ltname;	// 상품이름
	private int ltprice;	// 상품수량
	private int ltamount;	// 상품갯수
	private String lttype;	// 상품종류
	private String Listset;
	
	public void setListset(String code, String name, int price, int amount, String type) {
		setLtcode(code);
		setLtname(name);
		setLtprice(price);
		setLtamount(amount);
		setLttype(type);
	}
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getLtcode() {
		return ltcode;
	}
	public void setLtcode(String ltcode) {
		this.ltcode = ltcode;
	}
	public String getLtname() {
		return ltname;
	}
	public void setLtname(String ltname) {
		this.ltname = ltname;
	}
	public int getLtprice() {
		return ltprice;
	}
	public void setLtprice(int ltprice) {
		this.ltprice = ltprice;
	}
	public int getLtamount() {
		return ltamount;
	}
	public void setLtamount(int ltamount) {
		this.ltamount = ltamount;
	}
	public String getLttype() {
		return lttype;
	}
	public void setLttype(String lttype) {
		this.lttype = lttype;
	}
	@Override
	public String toString() {
		return num + "번\t" + ltcode + "\t" + ltname + "\t" + ltprice + "원\t" + ltamount
				+ "개\t" + lttype;
	}
	
}
