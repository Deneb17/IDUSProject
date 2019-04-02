package com.idus.blog.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.idus.blog.dto.ArtistInfo;
import com.idus.blog.dto.Options;
import com.idus.blog.dto.Piece;
import com.idus.blog.dto.PieceImage;
import com.idus.blog.dto.PieceInfo;
import com.idus.blog.dto.Post;
import com.idus.blog.dto.PostInfo;

@Repository
public class BlogDao {
	
	@Autowired
	private SqlSession sqlSession;

	public int insertPiece(Piece piece) {
		return sqlSession.insert("blog.insertPiece", piece);
	}

	public int insertPieceImage(PieceImage pieceImage) {
		return sqlSession.insert("blog.insertPieceImage", pieceImage);
	}

	public ArtistInfo selectArtistByMemberNo(int memberNo) {
		return sqlSession.selectOne("blog.selectArtistByMemberNo", memberNo);
	}

	public List<PieceInfo> selectRecentPieces(int memberNo) {
		return sqlSession.selectList("blog.selectRecentPieces", memberNo);
	}

	public List<PostInfo> selectRecentPosts(int memberNo) {
		return sqlSession.selectList("blog.selectRecentPosts", memberNo);
	}

	public int insertOptions(Options option) {
		return sqlSession.insert("blog.insertOptions", option);
	}

	public int insertPost(Post post) {
		return sqlSession.insert("blog.insertPost", post);
	}

	public List<PieceInfo> selectMorePieces(Map<String, Integer> param) {
		return sqlSession.selectList("blog.selectMorePieces", param);
	}

	
}
