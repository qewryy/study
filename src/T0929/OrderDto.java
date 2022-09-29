package T0929;

public class OrderDto {
	private int odnum;		// 주문번호
	private String odmid;	// 주문자 아이디
	private String oddate; 	// 주문일자
	private String odltcode;// 주문자 코드
	private int odqty;		// 주문수량

	public int getOdnum() {
		return odnum;
	}
	public void setOdnum(int odnum) {
		this.odnum = odnum;
	}
	public String getOdmid() {
		return odmid;
	}
	public void setOdmid(String odmid) {
		this.odmid = odmid;
	}
	public String getOddate() {
		return oddate;
	}
	public void setOddate(String oddate) {
		this.oddate = oddate;
	}
	public String getOdltcode() {
		return odltcode;
	}
	public void setOdltcode(String odltcode) {
		this.odltcode = odltcode;
	}
	public int getOdqty() {
		return odqty;
	}
	public void setOdqty(int odqty) {
		this.odqty = odqty;
	}
	
	@Override
	public String toString() {
		return odnum + "번\t" + odmid + "\t" + odqty + "개\t" + odltcode
				+ "\t" + oddate;
	}

}
