package com.kh.cook.member.dao;

import static com.kh.cook.common.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.kh.cook.member.vo.MemberVo;

public class MemberDao {
	// 회원가입
	public int join(Connection conn, MemberVo vo) {
		String sql = "INSERT INTO MEMBER (NO, ID, PWD, EMAIL, NAME, PHONE, NICK, ADDR, ADMIN_YN)VALUES(SEQ_MEMBER_NO.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, 'N')";
		
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPwd());
			pstmt.setString(3, vo.getEmail());
			pstmt.setString(4, vo.getName());
			pstmt.setString(5, vo.getPhone());
			pstmt.setString(6, vo.getNick());
			pstmt.setString(7, vo.getAddr());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			
		}
		return result;
	}
	
	public MemberVo selectOne(MemberVo vo, Connection conn) {
		String sql = "SELECT M.NO ,G.NAME AS GRADE ,ID ,PWD ,EMAIL ,M.NAME ,PHONE ,NICK ,ADDR ,PROFILE,ENROLL_DATE ,MODIFY_DATE ,QUIT_YN ,POINT ,ADMIN_YN FROM MEMBER M JOIN GRADE G ON M.GRADE = G.NO WHERE ID = ? AND PWD = ? AND QUIT_YN = 'N'";
	
		PreparedStatement pstmt = null;
		MemberVo loginMember = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPwd());
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String no = rs.getString("NO");
				String grade = rs.getString("GRADE");
				String id = rs.getString("ID");
				String pwd = rs.getString("PWD");
				String email = rs.getString("EMAIL");
				String name = rs.getString("NAME");
				String phone = rs.getString("PHONE");
				String nick = rs.getString("NICK");
				String dataAddr = rs.getString("ADDR");
				String profile = rs.getString("PROFILE");
				String enroll_date = rs.getString("ENROLL_DATE");
				String modify_date = rs.getString("MODIFY_DATE");
				String quitYn = rs.getString("QUIT_YN");
				int point = rs.getInt("POINT");
				String adminYn = rs.getString("ADMIN_YN");
				
				int idx = dataAddr.indexOf(",");
				
				String addr = dataAddr.substring(0, idx);
				String detailAddr = dataAddr.substring(idx+1);
				
				
				loginMember = new MemberVo();
				
				loginMember.setNo(no);
				loginMember.setGrade(grade);
				loginMember.setId(id);
				loginMember.setPwd(pwd);
				loginMember.setEmail(email);
				loginMember.setName(name);
				loginMember.setPhone(phone);
				loginMember.setNick(nick);
				loginMember.setAddr(addr);
				loginMember.setDetailAddr(detailAddr);
				loginMember.setProfile(profile);
				loginMember.setEnrollDate(enroll_date);
				loginMember.setModifyDate(modify_date);
				loginMember.setQuitYn(quitYn);
				loginMember.setPoint(point);
				loginMember.setAdminYn(adminYn);
				

				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		
		return loginMember;
	}
	// 수정
	public int updateOneByNo(MemberVo vo, Connection conn) {
		String sql = "UPDATE MEMBER SET PWD = ? ,EMAIL = ? ,PHONE = ? ,NICK = ? ,ADDR = ?,PROFILE = ? WHERE NO = ?";
		
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getPwd());
			pstmt.setString(2, vo.getEmail());
			pstmt.setString(3, vo.getPhone());
			pstmt.setString(4, vo.getNick());
			pstmt.setString(5, vo.getAddr());
			pstmt.setString(6, vo.getProfile());
			pstmt.setString(7, vo.getNo());
			
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	// 아이디 찾기
	public MemberVo findId(MemberVo vo, Connection conn) {
		String sql = "SELECT ID, PROFILE, ENROLL_DATE FROM MEMBER WHERE NAME = ? AND EMAIL = ? AND QUIT_YN = 'N'";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberVo findMember = null;
		try {
			pstmt = conn.prepareStatement(sql);
		
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getEmail());
			
			
			rs = pstmt.executeQuery();
			
			
			if(rs.next()) {
				String id = rs.getString("ID");
				String profile = rs.getString("PROFILE");
				String enrollDate =  rs.getString("ENROLL_DATE").substring(0, 11);
				
				findMember = new MemberVo();
				
				findMember.setId(id);
				findMember.setProfile(profile);
				findMember.setEnrollDate(enrollDate);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return findMember;
	}
	// 아이디 중복 검사
	public int dupCheckId(String id, Connection conn) {
		
		String sql = "SELECT ID FROM MEMBER WHERE ID = ?";
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			System.out.println(id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = 1; 
			} 
			
		} catch (SQLException e) {
			e.printStackTrace();
		
		} finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}
	// 닉네임 중복검사
	public int dupCheckNick(String nick, Connection conn) {
		String sql = "SELECT NICK FROM MEMBER WHERE NICK = ?";
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, nick);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = 1;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}

	public int dupCheckEmail(String email, Connection conn) {
		String sql = "SELECT EMAIL FROM MEMBER WHERE EMAIL = ?";
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = 1;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}

	public String findPwd(MemberVo vo, Connection conn) {
		String sql = "SELECT NO FROM MEMBER WHERE ID = ? AND PHONE = ? AND EMAIL = ? AND QUIT_YN = 'N'";
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		String no = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPhone());
			pstmt.setString(3, vo.getEmail());
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				no = rs.getString("NO");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		
		return no;
		
	}

	public int modifyPwd(MemberVo vo, Connection conn) {
		String sql = "UPDATE MEMBER SET PWD = ? WHERE NO = ? AND QUIT_YN = 'N'";
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getPwd());
			pstmt.setString(2, vo.getNo());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int quit(Connection conn, MemberVo vo) {
		String sql = "UPDATE MEMBER SET QUIT_YN = 'Y' WHERE NO = ? AND QUIT_YN = 'N'";
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getNo());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
		
	}

	public int updateGrade(MemberVo vo, Connection conn) {
		String sql = "UPDATE MEMBER SET GRADE = ? WHERE NO = ? AND QUIT_YN = 'N'";
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getGrade());
			pstmt.setString(2, vo.getNo());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
		
	}

//	public MemberVo selectGrade(String no, Connection conn) {
//		String sql = "SELECT M.NICK ,G.NAME AS GRADE, G.SAVE FROM MEMBER M JOIN GRADE G ON M.GRADE = G.NO WHERE M.NO = ? AND QUIT_YN = 'N'";
//		PreparedStatement pstmt = null;
//		MemberVo vo  = null;
//		ResultSet rs = null;
//		
//		try {
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, no);
//		
//			rs = pstmt.executeQuery();
//			
//			if(rs.next()) {
//				String grade = rs.getString("GRADE");
//				String save = rs.getString("SAVE");
//				String nick = rs.getString("NICK");
//				vo = new MemberVo();
//				
//				vo.setNick(nick);
//				vo.setGrade(grade);
//				vo.setSave(save);
//				
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			close(pstmt);
//			close(rs);
//		}
//		return vo;
//	}

	public MemberVo selectOneByNo(String no, Connection conn) {
		
		String sql = "SELECT M.NO ,M.GRADE ,M.ID ,M.PWD ,M.EMAIL ,M.NAME ,M.PHONE ,M.NICK ,M.ADDR ,M.ENROLL_DATE ,M.MODIFY_DATE ,M.QUIT_YN ,M.POINT ,M.ADMIN_YN ,G.SAVE FROM MEMBER M JOIN GRADE G ON M.GRADE = G.NO WHERE M.NO = ?";
		PreparedStatement pstmt = null;
		MemberVo vo = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, no);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String dataNo = rs.getString("NO");
				String grade = rs.getString("GRADE");
				String id = rs.getString("ID");
				String pwd = rs.getString("PWD");
				String email = rs.getString("EMAIL");
				String name = rs.getString("NAME");
				String phone = rs.getString("PHONE");
				String nick = rs.getString("NICK");
				String addr = rs.getString("ADDR");
				String enrollDate = rs.getString("ENROLL_DATE").substring(0, 11);
				String modifyDate = rs.getString("MODIFY_DATE");
				String quitYn = rs.getString("QUIT_YN");
				int point = rs.getInt("POINT");
				String save = rs.getString("SAVE");
				
				vo = new MemberVo();
				
				vo.setNo(dataNo);
				vo.setGrade(grade);
				vo.setId(id);
				vo.setPwd(pwd);
				vo.setEmail(email);
				vo.setName(name);
				vo.setPhone(phone);
				vo.setNick(nick);
				vo.setAddr(addr);
				vo.setEnrollDate(enrollDate);
				vo.setModifyDate(modifyDate);
				vo.setQuitYn(quitYn);
				vo.setPoint(point);
				vo.setSave(save);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return vo;
	}

}
