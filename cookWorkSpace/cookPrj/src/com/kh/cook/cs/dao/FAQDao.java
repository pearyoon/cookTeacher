package com.kh.cook.cs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kh.cook.common.JDBCTemplate;
import com.kh.cook.common.PageVo;
import com.kh.cook.cs.vo.CSVo;

public class FAQDao {

	//FAQ 리스트 조회
	public List<CSVo> selectFAQList(Connection conn, PageVo pv) {
		
		String sql = "SELECT * FROM ( SELECT ROWNUM AS RNUM , T.* FROM (SELECT Q.QNA_NO ,M.NICK ,Q.TITLE ,Q.CONT ,Q.Q_DATE ,Q.DELETE_YN ,Q.EDIT_DATE ,Q.QNA_CATE FROM QNA Q JOIN MEMBER M ON Q.NO = M.NO WHERE Q.DELETE_YN = 'N' AND Q.QNA_CATE = 'F' ORDER BY Q.QNA_NO ASC)T ) WHERE RNUM BETWEEN ? AND ?";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<CSVo> FAQList = new ArrayList<CSVo>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			int start = (pv.getCurrentPage() -1 ) * pv.getBoardLimit() +1;
			int end = start + pv.getBoardLimit() -1;
			
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String qnaNo = rs.getString("QNA_NO");
				String writer = rs.getString("NICK");
				String title = rs.getString("TITLE");
				String content = rs.getString("CONT");
				String qnaDate = rs.getString("Q_DATE").substring(0,10);
				String deleteYN = rs.getString("DELETE_YN");
				String editDate = rs.getString("EDIT_DATE");
				String qnaCategory = rs.getString("QNA_CATE");
				
				CSVo vo = new CSVo();
				vo.setQnaNo(qnaNo);
				vo.setWriter(writer);
				vo.setTitle(title);
				vo.setContent(content);
				vo.setQnaDate(qnaDate);
				vo.setDeleteYN(deleteYN);
				vo.setEditDate(editDate);
				vo.setQnaCategory(qnaCategory);
				
				FAQList.add(vo); 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rs);
		}
		
		return FAQList;
	}

	//페이지카운트
	public int pageSelectCount(Connection conn) {
		
		String sql = "SELECT COUNT(*) AS CNT FROM QNA WHERE QNA_CATE = 'F'";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt("CNT");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public int insertFAQ(Connection conn, CSVo vo) {
		
		String sql = "INSERT INTO QNA VALUES(SEQ_QNA_NO.NEXTVAL, ? , ?, ?, SYSDATE,'N',SYSDATE,'F')";
		
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, vo.getNo());
			pstmt.setString(2, vo.getTitle());
			pstmt.setString(3, vo.getContent());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	//FAQ 상세글조회
	public CSVo selectFAQOne(Connection conn, String Fno) {
		String sql = "SELECT Q.QNA_NO,Q.NO, Q.TITLE, Q.CONT, Q.Q_DATE, Q.EDIT_DATE, Q.DELETE_YN, Q.QNA_CATE, M.NICK AS WRITER FROM QNA Q JOIN MEMBER M ON Q.NO = M.NO WHERE QNA_CATE = 'F' AND Q.QNA_NO = ? AND Q.DELETE_YN = 'N'";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CSVo vo = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, Fno);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String qnaNo = rs.getString("QNA_NO");
				String no = rs.getString("NO");
				String writer = rs.getString("WRITER");
				String title = rs.getString("TITLE");
				String content = rs.getString("CONT");
				String qnaDate = rs.getString("Q_DATE").substring(0,10);
				String deleteYN = rs.getString("DELETE_YN");
				String editDate = rs.getString("EDIT_DATE");
				String qnaCategory = rs.getString("QNA_CATE");
				
				vo = new CSVo();
				vo.setQnaNo(qnaNo);
				vo.setNo(no);
				vo.setWriter(writer);
				vo.setTitle(title);
				vo.setContent(content);
				vo.setQnaDate(qnaDate);
				vo.setDeleteYN(deleteYN);
				vo.setEditDate(editDate);
				vo.setQnaCategory(qnaCategory);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rs);
		}
		
		return vo;
	}

	public int updateFAQone(Connection conn, CSVo FAQvo) {
		
		String sql = "UPDATE QNA SET TITLE = ?, CONT = ?, EDIT_DATE = SYSDATE WHERE QNA_NO = ?";
		
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, FAQvo.getTitle());
			pstmt.setString(2, FAQvo.getContent());
			pstmt.setString(3, FAQvo.getQnaNo());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		
		return result;
		
	}

	//글 삭제
	public int delete(Connection conn, String fno) {
		
		String sql = "UPDATE QNA SET DELETE_YN = 'Y' WHERE QNA_NO = ?";
		
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, fno);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}
	

}
