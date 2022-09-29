package T0929;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Tdao {
	public ArrayList<memberDto> Mlist = new ArrayList<>();
	public ArrayList<OrderDto> Olist = new ArrayList<>();
	public ArrayList<ListupDto> list = new ArrayList<>();

	private int z = 1;
	private Connection con = null;

	public static Connection getConnection() throws Exception {
		Class.forName("oracle.jdbc.OracleDriver");
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/xe", "JJH_DBA", "1111");
		return con;
	}
//			con = getConnection();

	// 회원가입 메소드
	public int Join(memberDto member) {
		String sql = "INSERT INTO MEMBER VALUES(?,?,?,?,TO_DATE(?,'YYYY-MM-DD'))";

		int ir = 0;

		try {
			con = getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPw());
			pstmt.setString(3, member.getName());
			pstmt.setInt(4, member.getAge());
			pstmt.setString(5, member.getBirth());
			ir = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return ir;
	}

	// 상품등록 메소드
	public int pr(ListupDto list) {
		String sql = "INSERT INTO LISTUP VALUES(?,?,?,?,?)";

		int ir = 0;

		try {
			con = getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, list.getLtcode());
			pstmt.setString(2, list.getLtname());
			pstmt.setInt(3, list.getLtprice());
			pstmt.setInt(4, list.getLtamount());
			pstmt.setString(5, list.getLttype());
			ir = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return ir;

	}

	// LISTUP 업데이트 메소드
	public void listset() {
		String sql = "SELECT * FROM LISTUP";
		ListupDto dto = null;
		try {
			con = getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				dto = new ListupDto();
				dto.setNum(z++);
				dto.setLtcode(rs.getString(1));
				dto.setLtname(rs.getString(2));
				dto.setLtprice(rs.getInt(3));
				dto.setLtamount(rs.getInt(4));
				dto.setLttype(rs.getString(5));
				list.add(dto);
			}
			z = 1;
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// MEMBER 업데이트 메소드
	public void Mlistset() {
		String sql = "SELECT * FROM MEMBER";
		memberDto dto = null;
		try {
			con = getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				dto = new memberDto();
				dto.setId(rs.getString(1));
				dto.setPw(rs.getString(2));
				dto.setName(rs.getString(3));
				dto.setAge(rs.getInt(4));
				dto.setBirth(rs.getString(5));
				Mlist.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// ORDERLIST 업데이트 메소드
	public void Olistset() {
		String sql = "SELECT * FROM ORDERLIST";
		OrderDto dto = null;
		try {
			con = getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				dto = new OrderDto();
				dto.setOdnum(rs.getInt(1));
				dto.setOdmid(rs.getString(2));
				dto.setOddate(rs.getString(3));
				dto.setOdltcode(rs.getString(4));
				dto.setOdqty(rs.getInt(5));
				Olist.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 상품삭제 메소드
	public int Pddt(String code) {
		String sql = "DELETE FROM LISTUP WHERE LTCODE = ?";

		int ir = 0;
		int index = 0;
		try {
			con = getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getLtcode().equals(code)) {
					index = i;
					break;
				}
			}
			pstmt.setString(1, list.get(index).getLtcode());
			ir = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return ir;
	}

	// 상품주문 메소드
	public int order(String loginck, String str, int num) {
		String sql = "INSERT INTO ORDERLIST VALUES((SELECT NVL(MAX(ODNUM),0)+1 FROM ORDERLIST),?,SYSDATE,?,?)";

		int ir = 0;

		try {
			con = getConnection();
			con.setAutoCommit(false);
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, loginck);
			pstmt.setString(2, str);
			pstmt.setInt(3, num);
			ir = pstmt.executeUpdate();

			int rs = orderby(-num, str);
			if (rs == 0) {
				try {
					con.rollback();
					con.setAutoCommit(true);
					ir = 0;
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} else {
				try {
					con.commit();
					con.setAutoCommit(true);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ir;
	}

	
	// 주문 후 업데이트 메소드
	private int orderby(int num, String str) {
		String sql = "UPDATE LISTUP SET LTAMOUNT = LTAMOUNT + ? WHERE LTCODE = ?";
		int ir = 0;
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, str);
			ir = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ir;
	}

	// 회원탈퇴 메소드
	public int usdelete(String id) {
		String sql = "DELETE FROM MEMBER WHERE ID = ?";

		int ir = 0;
		try {
			con = getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			ir = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return ir;
	}

	// 주문취소 메소드
	public int ordercancle(String id) {
		String sql = "DELETE FROM ORDERLIST WHERE ODMID = ?";
		int ir = 0;
		try {
			con = getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			ir = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return ir;
	}

	// 주문취소 업데이트 메소드
	public int fix(int num) {
		String sql = "UPDATE LISTUP SET LTAMOUNT = LTAMOUNT + (SELECT ODQTY FROM ORDERLIST WHERE ODNUM = ?)"
				+ " WHERE LTCODE = (SELECT OPLTCODE FROM ORDERLIST WHERE ODNUM = ?)";
		int ir = 0;
		try {
			con = getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setInt(2, num);
			ir = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ir;
	}

	// 주문취소 반복 메소드
	public void loop(String id) {
		String sql = "SELECT * FROM ORDERLIST WHERE ODMID = ?";
		int num = 0;
		try {
			con = getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				for (int i = 0; i < Olist.size(); i++) {
					if (Olist.get(i).getOdmid().equals(id)) {
						num = Olist.get(i).getOdnum();
						break;
					}
				}
				int r = fix(num);
				if (r == 0) {
					System.out.println("오류가 발생했습니다.");
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
