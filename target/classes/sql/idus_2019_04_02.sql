drop database if exists idus;
-- DB생성
create database idus default character set utf8mb4 collate utf8mb4_unicode_ci;
use idus;

-- [ 사용자 관련 테이블 ]
-- member : 회원 기본 정보 저장 테이블
-- follow : 회원의 팔로우 기록 저장 테이블
-- logInSession : 자동 로그인용 세션 저장 테이블

-- 회원 테이블
create table member(
    memberNo int auto_increment,  -- primary key
    email varchar(50) unique,  -- unique
    password varchar(100),
    name varchar(50),
    nickName varchar(50) unique,
    phone varchar(50),
    introduction varchar(100),
    zoneCode varchar(100),
    address varchar(100),
    detailAddress varchar(100),
    thumbnail varchar(500),
    point int,
    follower int,
    isSocial boolean,
    constraint member_pk primary key(memberNo)
);


-- 팔로우 기록 테이블
create table follow(
	followNo int auto_increment,  -- primary key
    followerNo int,  -- foreign key
    followeeNo int,  -- foreign key
    constraint follow_pk primary key(followNo),
    constraint follow_fk1 foreign key(followerNo) references member(memberNo) on delete cascade,
    constraint follow_fk2 foreign key(followeeNo) references member(memberNo) on delete cascade
);


-- 팔로우 증가용 트리거 -> 팔로우 테이블에 입력 시 자동으로 멤버 테이블 업데이트
delimiter //
create trigger follow_plus_trg
after insert on follow for each row
	begin
		update member set follower = follower + 1 where member.memberNo = new.followeeNo;
    end //
delimiter ;


-- 팔로우 감소용 트리거 -> 팔로우 테이블에 입력 시 자동으로 멤버 테이블 업데이트
delimiter //
create trigger follow_minus_trg
after delete on follow for each row
	begin
		update member set follower = follower - 1 where member.memberNo = old.followeeNo;
    end //
delimiter ;


-- 자동 로그인용 세션 저장 테이블
create table logInSession(
	sessionId varchar(100),  -- primary key
    memberNo int,  -- foreign key
	constraint logInSession_pk primary key(sessionId),
    constraint logInSession_fk foreign key(memberNo) references member(memberNo) on delete cascade
);


-- [ 작품(상품) 관련 테이블 ]
-- piece : 작품(상품) 정보 저장 테이블
-- pieceImage : 작품의 썸네일 이미지 url 저장 테이블
-- options : 작품(상품) 옵션 저장 테이블
-- likePiece : 작품(상품) 좋아요 기록 저장 테이블

-- 작품(상품) 테이블
create table piece(
	pieceNo int auto_increment,  -- primary key
    artistNo int,  -- foreign key
    title varchar(50),
    pieceName varchar(50),
    price int,
    deliveryCharge int,
    discount int,
    description text,
    createdDate timestamp,
    modifiedDate timestamp,
    likeIt int,
    star int,
    constraint piece_pk primary key(pieceNo),
    constraint piece_fk foreign key(artistNo) references member(memberNo) on delete cascade
);


-- 작품(상품)의 썸네일 이미지 url 테이블
create table pieceImage(
	imageNo int auto_increment,  -- primary key
    pieceNo int,  -- foreign key
    url varchar(500),
    isThumbnail boolean,
    constraint pieceImage_pk primary key(imageNo),
    constraint pieceImage_fk foreign key(pieceNo) references piece(pieceNo) on delete cascade
);


-- 상품의 옵션 테이블
create table options(
	optionNo int auto_increment,  -- primary key
    pieceNo int,
    options varchar(100),
    constraint options_pk primary key(optionNo),
    constraint options_fk foreign key(pieceNo) references piece(pieceNo) on delete cascade
);


-- 작품(상품)에 대한 좋아요 기록 테이블
create table likePiece(
	likeNo int auto_increment,  -- primary key
    pieceNo int,  -- foreign key
    memberNo int,  -- foreign key
    constraint likePiece_pk primary key(likeNo),
    constraint likePiece_fk1 foreign key(pieceNo) references piece(pieceNo) on delete cascade,
    constraint likePiece_fk2 foreign key(memberNo) references member(memberNo) on delete set null
);


-- 좋아요 증가용 트리거
delimiter //
create trigger like_plus_trg
after insert on likePiece for each row
	begin
		update piece set likeIt = likeIt + 1 where piece.pieceNo = new.pieceNo;
    end //
delimiter ;


-- 좋아요 감소용 트리거
delimiter //
create trigger like_minus_trg
after delete on likePiece for each row
	begin
		update piece set likeIt = likeIt - 1 where piece.pieceNo = old.pieceNo;
    end //
delimiter ; 


-- 작품(상품)에 대한 구매 후기 저장 테이블
create table review(
	reviewNo int auto_increment,  -- primary key
    pieceNo int,  -- foreign key
    optionNo int,  -- foreign key
    memberNo int,  -- foreign key
    content varchar(500),
    createdDate timestamp,
    modifiedDate timestamp,
    star int,  -- check constraint
    constraint review_pk primary key(reviewNo),
    constraint review_fk1 foreign key(pieceNo) references piece(pieceNo) on delete cascade,
    constraint review_fk2 foreign key(optionNo) references options(optionNo) on delete cascade,
    constraint review_fk3 foreign key(memberNo) references member(memberNo) on delete cascade,
    constraint review_chk check(star >= 0 and star <= 5)
);


-- [ 작가 블로그 관련 테이블 ]
-- post : 작가의 포스팅 저장 테이블
-- postComment : 포스팅에 달린 댓글 저장 테이블


-- 포스팅 저장 테이블
create table post(
	postNo int auto_increment,  -- primary key
    memberNo int,  -- foreign key
    title varchar(100),
    content text,
    createdDate timestamp,
    modifiedDate timestamp,
    constraint post_pk primary key(postNo),
    constraint post_fk foreign key(memberNo) references member(memberNo) on delete cascade
);


-- 포스팅에 달린 댓글 저장 테이블
create table postComment(
	comNo int auto_increment,  -- primary key
    postNo int,  -- foreign key
    memberNo int,  -- foreign key
    content varchar(500),
    createdDate timestamp,
    modifiedDate timestamp,
    constraint postComment_pk primary key(comNo),
    constraint postComment_fk1 foreign key(postNo) references post(postNo) on delete cascade,
    constraint postComment_fk2 foreign key(memberNo) references member(memberNo) on delete cascade
);


-- [ 구매, 환불 관련 테이블 ]
-- shoppingBag : 장바구니 저장 테이블
-- orders : 주문 테이블
-- refund : 환불 테이블
-- buy : 구매 테이블
-- income : 수익 테이블

-- 장바구니 테이블 
create table shoppingBag(
	spbNo int auto_increment,  -- primary key
    customerNo int,  -- foreign key
    artistNo int,  -- foreign key
    pieceNo int,  -- foreign key
    optionNo int,  -- foreign key
    amount int,
    price int,
    constraint spb_pk primary key(spbNo),
    constraint spb_fk1 foreign key(customerNo) references member(memberNo) on delete cascade,
    constraint spb_fk2 foreign key(artistNo) references member(memberNo) on delete cascade,
    constraint spb_fk3 foreign key(pieceNo) references piece(pieceNo) on delete cascade,
    constraint spb_fk4 foreign key(optionNo) references options(optionNo) on delete cascade
);


-- 주문 상태 테이블
create table orderStatus(
	statusNo int auto_increment,  -- primary key
    orderStatus varchar(50),
    constraint orderStatus_pk primary key(statusNo)
);


-- 주문 테이블
create table orders(
	orderNo int auto_increment,  -- primary key
    artistNo int,  -- foreign key
    customerNo int,  -- foreign key
    pieceNo int, -- foreign key
    optionNo int,  -- foreign key
    statusNo int,  -- foreign key
    amount int,
    price int,
	orderDate timestamp,
    refundRequest boolean default false,
    confirm boolean default false,
    confirmDate timestamp,
    constraint orders_pk primary key(orderNo),
    constraint orders_fk1 foreign key(customerNo) references member(memberNo) on delete no action,
    constraint orders_fk2 foreign key(pieceNo) references piece(pieceNo) on delete no action,
    constraint orders_fk3 foreign key(artistNo) references member(memberNo) on delete no action,
    constraint orders_fk4 foreign key(optionNo) references options(optionNo) on delete no action,
    constraint orders_fk5 foreign key(statusNo) references orderStatus(statusNo) on delete no action
);

-- 환불 테이블
create table refund(
	refundNo int auto_increment,  -- primary key
    memberNo int,  -- foreign key
    orderNo int,  -- foreign key
    refundDate timestamp,
    constraint refund_pk primary key(refundNo),
    constraint refund_fk1 foreign key(memberNo) references member(memberNo),
    constraint refund_fk2 foreign key(orderNo) references orders(orderNo)
);

-- 구매 테이블
create table buy(
	buyNo int auto_increment,  -- primary key
    memberNo int,  -- foreign key
    orderNo int,
    constraint buy_pk primary key(buyNo),
    constraint buy_fk foreign key(memberNo) references member(memberNo) on delete cascade
);

-- 수익 테이블
create table income(
	incomeNo int auto_increment,  -- primary key
    memberNo int,  -- foreign key
    orderNo int,  -- foreign key
    sellDate timestamp,
    constraint income_pk primary key(incomeNo),
    constraint income_fk1 foreign key(memberNo) references member(memberNo) on delete cascade,
    constraint income_fk2 foreign key(orderNo) references orders(orderNo) on delete cascade
);


-- view 가 필요한 테이블 : 
-- 댓글 : 댓글번호, 글번호, 작성자 닉네임, 내용, 등록일, 수정일
create or replace view postComment_view as 
	select postComment.comNo, postComment.postNo, member.nickName, member.thumbnail, postComment.content, postComment.createdDate, postComment.modifiedDate 
    from member, postComment where member.memberNo = postComment.memberNo;


-- 포스트 : 글번호, 작성자 회원 번호, 작성자 닉네임, 작성자 썸네일, 제목, 내용, 등록일, 수정일, 댓글수
create or replace view post_view as
	select post.postNo, member.memberNo, member.nickName, member.thumbnail, post.title, post.content, post.createdDate, post.modifiedDate
    from member, post where post.memberNo = member.memberNo;

create or replace view post_comCount_view as 
	select post.postNo, count(postComment.comNo) as comCount
    from post, postComment
    where post.postNo = postComment.postNo;
    
-- 환불 : 환불번호, 구매자 회원번호, 구매자 이름, 판매자 회원번호, 판매자 닉네임, 상품이름, 옵션, 수량, 전체가격, 환불일
create or replace view refund_view as
	select refund.refundNo, c.memberNo as customerNo, c.name customerName, a.memberNo as artistNo, 
    a.nickName as artistNickName,
    piece.pieceName, options.options, orders.amount, orders.price, refund.refundDate
    from refund, member c, orders, options, piece, member a 
    where refund.memberNo = c.memberNo and refund.orderNo = orders.orderNo and orders.optionNo = options.optionNo
    and piece.pieceNo = orders.pieceNo and piece.artistNo = a.memberNo;
-- from ((refund inner join member) inner join (orders inner join options)) inner join piece;



-- -----------------------------------------------------------------------------------------------------------



-- 구매 : 구매 번호, 구매자 번호, 구매자 이름, 상품이름, 옵션, 수량, 전체가격
create or replace view buy_view as
	select buy.buyNo, buy.memberNo, member.name, piece.pieceName, options.options, orders.amount, orders.price
    from buy, member, orders, options, piece
    where buy.memberNo = member.memberNo and buy.orderNo = orders.orderNo and orders.optionNo = options.optionNo and
    piece.pieceNo = orders.pieceNo;
    -- from ((buy inner join member) inner join (orders inner join options)) inner join piece;


-- 수익 : 수익 번호, 판매자 번호, 판매자 이름, 판매자 닉네임, 판매된 상품 번호, 판매된 상품명, 판매된 상품의 옵션, 상품 전체 가격 금액
create or replace view income_view as
	select income.incomeNo, income.memberNo, member.name, member.nickName, orders.pieceNo, piece.pieceName, options.options, orders.price
    from income, member, orders, piece, options
    where income.memberNo = member.memberNo and income.memberNo = piece.artistNo 
    and orders.orderNo = income.memberNo and orders.optionNo = options.optionNo;


-- 상품별 수익 : 상품번호, 판매자 번호, 판매자 이름, 판매자 닉네임, 판매된 상품번호 , 가격
create or replace view group_income_view as
	select incomeNo, memberNo, name, pieceNo, pieceName, options, sum(price) as pByPIncome
    from income_view group by(pieceNo);


-- 주문 : 주문번호, 주문자 회원번호, 주문자 이름, 작가번호, 작품번호, 작품이름, 옵션, 주소, 수량, 전체가격, 주문일, 상태, 환불신청, 처리완료, 처리완료 일자
create or replace view orders_view as
	select o.orderNo, o.customerNo, m.name, a.memberNo, o.pieceNo, p.pieceName, op.options, m.zoneCode, m.address,
    m.detailAddress, o.amount, o.price, o.orderDate, os.orderStatus, o.refundRequest, o.confirm, o.confirmDate 
    from orders o, member m, member a, piece p, options op, orderStatus os
    where o.customerNo = m.memberNo and o.artistNo = a.memberNo and o.pieceNo = p.pieceNo and o.optionNo = op.optionNo
    and o.statusNo = os.statusNo;
    -- from (((orders inner join member) inner join piece) inner join options) inner join orderStatus;


-- 구매후기 : 후기번호, 작품번호, 작품이름, 옵션, 작성자 이메일, 닉네임, 내용, 등록일, 수정일, 별점
create or replace view review_view as
	select reviewNo, review.pieceNo, pieceName, options, review.memberNo, member.nickName, content, review.createdDate,
    review.modifiedDate, review.star
    from ((review inner join member) inner join options) inner join piece;


-- 작품 썸네일 검색용 view : 작품번호, 이미지 url
create or replace view thumbnail_view as 
	select pieceNo, url from pieceImage where isThumbnail = true;



-- 장바구니 : 장바구니 번호, 장바구니 소유자의 회원번호, 작가번호, 작가 닉네임, 작품번호, 작품명, 옵션번호, 옵션, 수량, 썸네일, 가격
create or replace view shoppingBag_view as
	select sb.spbNo, sb.customerNo, sb.artistNo, a.nickName, sb.pieceNo, p.pieceName, sb.optionNo, 
    o.options, sb.amount, sb.price, thv.url
    from shoppingBag sb, thumbnail_view thv, member m, piece p, options o, member a
    where sb.customerNo =  m.memberNo and sb.pieceNo = thv.pieceNo and sb.artistNo = a.memberNo 
    and sb.pieceNo = p.pieceNo and  sb.optionNo = o.optionNo;



-- 상품 리스트 뷰 : 상품 번호, 작가명, 상품 제목, 썸네일 이미지 url, 별점, 좋아요
create or replace view pieceList_view as
	select piece.pieceNo, piece.artistNo, member.nickName, piece.title, piece.pieceName, pieceImage.url, piece.discount, piece.price, piece.star, piece.likeIt
    from piece, member, pieceImage where piece.artistNo = member.memberNo and piece.pieceNo = pieceImage.pieceNo and pieceImage.isThumbnail = true;


insert into orderStatus values(1, '준비 중');
insert into orderStatus values(2, '제작 중');
insert into orderStatus values(3, '발송 준비 중');
insert into orderStatus values(4, '발송 완료');
insert into orderStatus values(5, '배송 완료');

select * from pieceList_view;


-- 상품 리스트 뷰 : 
create or replace view itemListRecent as 
	select piece.pieceNo, member.name, piece.title, piece.pieceName, piece.star, pieceImage.url
	from (piece inner join pieceImage on piece.pieceNo=pieceImage.pieceNo) inner join member on member.email = piece.artist
    where pieceImage.isThumbnail = true order by piece.pieceNo desc;