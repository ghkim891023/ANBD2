package anbd;

import java.sql.SQLException;

public class NextDAO extends DbInfo{
	
	//이전글 
	public int beforeNo (int no) {
		int preNo=0;
		getConnection();
		String SQL= "select max(no) as no from board where no<"+no;
		prepareStatement(SQL);    //쿼리를 미리 준비해서 메모리에 저장시키는 거
		executeQuery(); 		  //쿼리 실행
		try {
			while(rs.next()){
				preNo = rs.getInt("no");
				System.out.println("preNo: "+preNo);
				return preNo;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		rsClose();
		pstateClose();
		conClose();
		return preNo;
	}



	// 다음글 
	public int afterNo (int no) {
		int afNo =0;
		getConnection();
		String SQL = "select min(no) as no from board where no >" + no;
		prepareStatement(SQL);
		executeQuery();
		try {
				while(rs.next()) {
					afNo =rs.getInt("no");
					System.out.println("agNo: " + afNo);
					return afNo;
				}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		rsClose();
		pstateClose();
		return afNo;
	
				}
		
	}