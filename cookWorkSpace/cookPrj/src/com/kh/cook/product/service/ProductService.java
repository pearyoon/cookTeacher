package com.kh.cook.product.service;

import java.sql.Connection;
import java.util.List;

import static com.kh.cook.common.JDBCTemplate.*;

import com.kh.cook.bobstory.vo.AttachmentVo;
import com.kh.cook.common.JDBCTemplate;
import com.kh.cook.product.dao.ProductDao;
import com.kh.cook.product.vo.PageVo;
import com.kh.cook.product.vo.ProductVo;
import com.kh.cook.product.vo.ReviewVo;

public class ProductService  {
	
	private final ProductDao dao = new ProductDao(); //new ProductDao() 대신 dao 로 


	//식재료 목록 조회
	public List<ProductVo> selectProductList(PageVo pv) {

		//커넥션 준비
		//SQL
		//트랜잭션 처리, 자원반납
		
		Connection conn = getConnection();
		
		List<ProductVo> voList = dao.selectProductList(conn , pv);
		
		close(conn);
		
		
		return voList;
		
		
	}


	//식재료 상세 조회
	public ProductVo selectProductOne(String no) {
		
		Connection conn = getConnection();
		
		ProductVo vo = new ProductDao().selectProductOne(conn, no);

		close(conn);
		
		return vo;
	
		
	}


	//식재료 갯수 조회
	public int selectCount() {
		
		Connection conn = getConnection();

		int result = dao.selectCount(conn);
		
		close(conn);
		
		return result;
	}


	//식재료 리뷰 작성
	public int write(ReviewVo rvo) {

		Connection conn = getConnection(); 
		
		int result = dao.insertReview(conn, rvo);
		
		if(result ==1) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}


	//과일채소 목록 조회
	public List<ProductVo> selectFruitVegeList(PageVo pv) {

		Connection conn = getConnection();
		
		List<ProductVo> voList = dao.selectFruitVegeList(conn , pv);
		
		close(conn);
		
		return voList;
	
	}

	
	//해산물 목록 조회
	public List<ProductVo> selectseafoodList(PageVo pv) {
		
		Connection conn = getConnection();
		
		List<ProductVo> voList = dao.selectSeafoodList(conn , pv);
		
		close(conn);
		
		return voList;
	}


	//기타 목록 조회
	public List<ProductVo> selectEtcList(PageVo pv) {
		
		Connection conn = getConnection();
		
		List<ProductVo> voList = dao.selectEtcList(conn , pv);
		
		close(conn);
		
		return voList;
			
	}

	//육류계란 목록 조회
	public List<ProductVo> selectMeatEggList(PageVo pv) {

		Connection conn = getConnection();
		
		List<ProductVo> voList = dao.selectMeatEggList(conn , pv);
		
		close(conn);
		
		return voList;
	}


	//면류빵류 목록 조회
	public List<ProductVo> selectNoodleBreadList(PageVo pv) {

		Connection conn = getConnection();
		
		List<ProductVo> voList = dao.selectNoodleBreadList(conn , pv);
		
		close(conn);
		
		return voList;
	}


	//유제품 목록 조회
	public List<ProductVo> selectDairyList(PageVo pv) {

		Connection conn = getConnection();
		
		List<ProductVo> voList = dao.selectDairyList(conn , pv);
		
		close(conn);
		
		return voList;
	}

	//음료 목록 조회
	public List<ProductVo> selectDrinkList(PageVo pv) {

		Connection conn = getConnection();
		
		List<ProductVo> voList = dao.selectDrinkList(conn , pv);
		
		close(conn);
		
		return voList;
	}

	
	//리뷰 조회
	public List<ReviewVo> selectReview(String no) {

		Connection conn = getConnection();
		
		List<ReviewVo> rvoList = dao.selectReviewList(conn, no);
		
		close(conn);
		
		return rvoList;
	}


	//내가 쓴 리뷰 조회
	public List<ReviewVo> selectMyReview(String no, PageVo pv) {

		Connection conn = getConnection();
		
		List<ReviewVo> rvoList = dao.selectMyReviewList(conn, no, pv);
		
		close(conn);
		
		return rvoList;
	}

	//내가 쓴 리뷰 갯수 조회
	public int selectMyReviewCount(String no) {

		Connection conn = getConnection();

		int result = dao.selecMyReviewCount(conn, no);
		
		close(conn);
		
		return result;
	}


	//관리자 식재료 등록
	public int insertProduct(ProductVo vo, AttachmentVo avo) {
		Connection conn = getConnection(); 
		
		System.out.println(vo.getCateNo());
		
		//식재료 등록
		int result = dao.insertProduct(conn, vo);
		
		//첨부파일 insert
		int result2 = 1;
		if(avo != null) {
			result2 = dao.insertAttachment(conn, avo);
		}
		
		
		if(result * result2 == 1) {
			//성공
			commit(conn);
		}else {
			//실패
			rollback(conn);
		}
		close(conn);
		
		return result * result2 ;

	}



}
