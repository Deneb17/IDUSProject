// 서비스 하는 웹 페이지의 도메인
var domain = "http://localhost:8080/ch22_socialLogin/";
// 네이버로 부터 발급 받은 로그인 API의 clientID
var clientId = "lrTa2NvPev7CUD6_wzDG";
// 로그인 처리 후 돌아갈 url
var callBackUrl = "http://localhost:8080/idus/login/naver";

var naverLogin = new naver_id_login(clientId, callBackUrl);
var state = naverLogin.getUniqState();

// 로그인 설정
naverLogin.setButton("green", 2, 50);
naverLogin.setDomain(domain);
naverLogin.setState(state);
// naverLogin.setPopup(); -> 팝업 창으로 처리
naverLogin.init_naver_id_login();