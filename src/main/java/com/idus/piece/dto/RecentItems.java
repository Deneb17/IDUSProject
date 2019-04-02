package com.idus.piece.dto;

/**
 * 1. myBatis <select id="" ~~> select * from itemListRecent #{}; </select>
 * 
 * 2. dao public List<ItemRow> methodName()
 * 
 * public Piece methodName()
 * 
 * 3. service dao 부르는 메소드 2 +++
 * 
 * 4. controller
 * 
 */

public class RecentItems {

	private int pieceNo;
	private String nickName;
	private String title;
	private String pieceName;
	private int star;
	private String url;

	public int getPieceNo() {
		return pieceNo;
	}

	public void setPieceNo(int pieceNo) {
		this.pieceNo = pieceNo;
	}
	
	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPieceName() {
		return pieceName;
	}

	public void setPieceName(String pieceName) {
		this.pieceName = pieceName;
	}

	public int getStar() {
		return star;
	}

	public void setStar(int star) {
		this.star = star;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
