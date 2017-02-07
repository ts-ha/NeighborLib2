package com.neighborlib.common;

import com.neighborlib.BuildConfig;

/**
 * <pre>
 *  1. 기능 : 데이터 요청 공통변수 관리 클래스
 *  2. 처리 개요 :
 *     - 데이터 요청 공통변수 관리 클래스
 *  3. 주의사항 :
 *  4. 작성자/작성일 : 이동식 / 2013. 7. 1.
 * ===================================
 *  5. 수정사항
 *  5.1 요구사항 ID :
 *     - 수정자/수정일 : 김용희 / 2017. 1. 19
 *     - 수정사유/내역 : 고도화 작업 API 추가(V3) 및 변경
 * ===================================
 * </pre>
 *
 * @author : 이동식
 * @version : v1.0.0
 * @see : kr.co.lwt.sts.app.network 패키지 참조
 * @since : J2SE 6.0
 **/
public class APIDef {
	/** 개발 서버 URL */
	//서버 이전 전 ( Before 2015.1 )
//	public static final String SERVER_URL_DEFAULT = "http://c2tft.iptime.org:8086";
	//서버 이전 후 ( After 2015.1~)
//	public static final String SERVER_URL_DEFAULT = "http://124.243.43.139:8086";
//	public static final String SERVER_URL_WEBPAGE = "http://124.243.43.139";
	//2016.10 테스트 URL
//	public static final String SERVER_URL_DEFAULT_JIYEON = "http://211.41.186.117:8086";
//	public static final String SERVER_URL_DEFAULT_JIYEON = "http://211.41.186.117:8086";
//	public static final String SERVER_URL_JIYEON = SERVER_URL_DEFAULT_JIYEON+"/lwt";
//	public static final String API_REQUEST_URL_JIYEON = SERVER_URL_JIYEON + "/api/";
//	public static final String API_REQUEST_URL = SERVER_URL_JIYEON + "/api/";
//	public static final String SERVER_URL_DEFAULT = "http://211.41.186.130:8086";
//	public static final String SERVER_URL = SERVER_URL_DEFAULT+"/lwt";
//	public static final String API_REQUEST_URL = SERVER_URL + "/api/";
//	public static final String SERVER_URL_DEFAULT = "http://211.41.186.117:8086";

    /** 서버 URL */
    public static final String SERVER_URL_DEFAULT;
    public static final String SERVER_URL_WEBPAGE;
    static {
        if (BuildConfig.BUILD_TYPE.equals("debug")) {
            /*테스트 서버 URL*/
            SERVER_URL_DEFAULT = "http://124.243.43.139:8086";
            SERVER_URL_WEBPAGE = "http://124.243.43.139";
//			SERVER_URL_DEFAULT = "https://stsgw.lwt.co.kr";
//			SERVER_URL_WEBPAGE = "http://lwt.co.kr/";
        } else {
            /*상용 서버 URL*/
            SERVER_URL_DEFAULT = "https://stsgw.lwt.co.kr";
            SERVER_URL_WEBPAGE = "http://lwt.co.kr/";
        }
    }

	public static final String SERVER_URL = SERVER_URL_DEFAULT + "/lwt";
	/** API 서버 URL */
	public static final String API_REQUEST_URL = SERVER_URL + "/api/";

	public static final String API_VERSION_V1 = "v1/";
	public static final String API_VERSION_V2 = "v2/";
	public static final String API_VERSION_V3 = "v3/";
	/** Information API 경로 */
//	public static final String API_TYPE_INFORMATION = "inf/";
	/** Parking API 경로 */
//	public static final String API_TYPE_PARKING = "prk/";
	/** Common API 경로 */
//	public static final String API_TYPE_COMMON = "com/";

	/**
	 * 시설 상세정보 API 경로
	 * # 김용희 / 2017. 1. 16.
	 * # 고도화 작업으로 인한 버전 변경
	 **/
//	public static final String VALUE_TYPE_FAC_INFO = API_REQUEST_URL + API_VERSION_V1 + "inf/fac/FacInfoImpl.json";
	public static final String VALUE_TYPE_FAC_INFO = API_REQUEST_URL + API_VERSION_V2 + "fac/FacInfoImpl.json";
	/** 시설 상세정보 수정 API 경로 */
	public static final String VALUE_TYPE_FAC_INFO_UPDATE = API_REQUEST_URL + API_VERSION_V1 + "inf/fac/FacInfoUpdateImpl.json";
	/** 시설 등록대기 이미지 수정 API 경로 */
	public static final String VALUE_TYPE_FAC_IMAGE_CANCEL = API_REQUEST_URL + API_VERSION_V1 + "inf/fac/FacImageCancelImpl.json";
	/** 시설 목록정보 API 경로 */
	public static final String VALUE_TYPE_FAC_LIST = API_REQUEST_URL + API_VERSION_V1 + "inf/fac/FacListImpl.json";
	public static final String VALUE_TYPE_FAC_LIST_V2 = API_REQUEST_URL + API_VERSION_V2 + "fac/FacListImpl.json";
	/** 층별 시설 목록정보 API 경로 */
	public static final String VALUE_TYPE_FAC_FL_LIST = API_REQUEST_URL + API_VERSION_V1 + "inf/fac/FacFlListImpl.json";
	/** 시설 별점정보 등록 API 경로 */
	public static final String VALUE_TYPE_STAR_POINT_REGISTER = API_REQUEST_URL + API_VERSION_V1 + "inf/personal/StarPointRegisterImpl.json";
	/** 카테고리정보 조회 API 경로 */
	public static final String VALUE_TYPE_CATEGORY_INFO = API_VERSION_V1 + "fac/CategoryInfoImpl.json";
	/** 계열사정보 조회 API 경로 */
	public static final String VALUE_TYPE_SUBSIDIARY_INFO = API_REQUEST_URL + API_VERSION_V1 + "inf/fac/KeyTenantInfoImpl.json";
	/** 추천시설 목록 API 경로 */
	public static final String VALUE_TYPE_RECOMMNAD_FAC_LIST = API_REQUEST_URL + API_VERSION_V1 + "inf/fac/RecmndFacListImpl.json";
	/** 편의시설 목록 API 경로 */
	public static final String VALUE_TYPE_AMENITIES_LIST = API_REQUEST_URL + API_VERSION_V2 + "map/AmenitiesList.json";
	/** 스토어뷰 목록 API 경로 */
	public static final String VALUE_TYPE_VR_STORE_VIEW = API_REQUEST_URL + API_VERSION_V1 + "inf/fac/VrStoreViewImpl.json";
	/** 시설 상세정보 API 경로 (POI ID이용) */
	public static final String VALUE_TYPE_POI_FAC_INFO = API_REQUEST_URL + API_VERSION_V2 + "fac/PoiFacInfoImpl.json";
	/** 맛집목록 조회 API 경로 */
	public static final String VALUE_TYPE_FNB_LIST = API_REQUEST_URL + API_VERSION_V2 + "fac/FnbListImpl.json";
	/** 맛집 상세정보 API 경로 */
	public static final String VALUE_TYPE_FNB_INFO = API_REQUEST_URL + API_VERSION_V2 + "fac/FnbInfoImpl.json";
	/** 지도 편의시설 목록 조회 API 경로 */
	public static final String VALUE_TYPE_MAP_AMENITIES_SEARCH = API_REQUEST_URL + API_VERSION_V2 + "map/AmenitiesSearchImpl.json";
	/** 지도 층 정보 및 상세정보 API 경로*/
	public static final String VALUE_TYPE_MAP_FLOOR_BUILDING = API_REQUEST_URL + API_VERSION_V3 + "map/FloorListImpl.json";

	/** 경로 조회 API 경로 */
	public static final String VALUE_TYPE_ROUTE_GUIDE_INFO = API_REQUEST_URL + API_VERSION_V1 + "inf/map/RouteGuideInfoImpl.json";
	/** VR 조회 API 경로 */
	public static final String VALUE_TYPE_VR_BASED_FAC_INFO = API_REQUEST_URL + API_VERSION_V1 + "inf/map/VrbasedFacInfoImpl.json";

	/** 행사/이벤트 조회 API 경로 */
	public static final String VALUE_TYPE_EVENT_LIST = API_REQUEST_URL + API_VERSION_V1 + "inf/event/EventListImpl.json";
	/** 행사/이벤트 상세조회 API 경로 */
	public static final String VALUE_TYPE_EVENT_DETAIL = API_REQUEST_URL + API_VERSION_V1 + "inf/event/EventDetailInfoImpl.json";
	/** 공연 조회 API 경로 */
	public static final String VALUE_TYPE_PERFORMANCE_LIST = API_VERSION_V1 + "event/ShowListImpl.json";
	/** 공연 상세조회 API 경로 */
	public static final String VALUE_TYPE_PERFORMANCE_DETAIL = API_VERSION_V1 + "event/ShowDetailInfoImpl.json";
	/** 쿠폰 조회 API 경로 */
	public static final String VALUE_TYPE_COUPON_LIST = API_VERSION_V1 + "event/CouponListImpl.json";
	/** 쿠폰 상세조회 API 경로 */
	public static final String VALUE_TYPE_COUPON_DETAIL = API_VERSION_V1 + "event/CouponDetailInfoImpl.json";
	/** 신상품 조회 API 경로 */
	public static final String VALUE_TYPE_NEW_PRODUCT_LIST = API_VERSION_V1 + "event/NewProductListImpl.json";
	/** 신상품 상세조회 API 경로 */
	public static final String VALUE_TYPE_NEW_PRODUCT_DETAIL = API_VERSION_V1 + "event/NewProductDetailInfoImpl.json";
	/** 상세정보 공유 API 경로 */
	public static final String VALUE_TYPE_DETAIL_INFO_SHARE = API_VERSION_V1 + "share/DetailInfoShareImpl.json";
	/** 위치 공유 API 경로 */
	public static final String VALUE_TYPE_POSITION_INFO_SHARE = API_VERSION_V1 + "share/PositionInfoShareImpl.json";
	/** 추천 이벤트 조회 API 경로 */
	public static final String VALUE_TYPE_EVENT_RECOMMEND_INFO_LIST = API_REQUEST_URL + API_VERSION_V2 + "main/event/RecmndEventListImpl.json";
	/** 쇼핑뉴스 이벤트 조회 API 경로 */
	public static final String VALUE_TYPE_SHOPPING_NEWS_LIST = API_REQUEST_URL + API_VERSION_V2 + "shoppingnews/ShoppingNewsListImpl.json";
	/** 쇼핑뉴스 이벤트 상세조회 API 경로 */
	public static final String VALUE_TYPE_SHOPPING_NEWS_DETAIL = API_REQUEST_URL + API_VERSION_V2 + "shoppingnews/ShoppingNewsContentImpl.json";
	/** 컬쳐뉴스 이벤트 조회 API 경로 */
	public static final String VALUE_TYPE_CULTURE_NEWS_LIST = API_REQUEST_URL + API_VERSION_V2 + "culturenews/CultureNewsListImpl.json";
	/** 컬쳐뉴스 이벤트 상세조회 API 경로 */
	public static final String VALUE_TYPE_CULTURE_NEWS_DETAIL = API_REQUEST_URL + API_VERSION_V2 + "culturenews/CultureNewsContentImpl.json";
	/** 지도 이벤트 조회 API 경로 */
	public static final String VALUE_TYPE_EVENT_LIST_MAP = API_REQUEST_URL + API_VERSION_V2 + "map/EventListImpl.json";
	/** 메인화면 이벤트 목록 조회 API 경로 */
	public static final String VALUE_TYPE_MAIN_VIEW_LIST = API_REQUEST_URL + API_VERSION_V2 + "main/MainViewImpl.json";
	/** 아트쇼 이벤트 조회 API 경로 */
	public static final String VALUE_TYPE_ART_SHOW = API_REQUEST_URL + API_VERSION_V2 + "artshow/ArtShowImpl.json";
	/** 공연스케줄 날짜 조회 API 경로 */
	public static final String VALUE_TYPE_EXHBT_DATE_LIST = API_REQUEST_URL + API_VERSION_V2 + "culturenews/ExhbtDateListImpl.json";
	/** 공연스케줄 조회 API 경로 */
	public static final String VALUE_TYPE_EXHBT_LIST = API_REQUEST_URL + API_VERSION_V2 + "culturenews/ExhbtListImpl.json";
	/** 쇼핑뉴스 카테고리 리스트 조회 API 경로 */
	public static final String VALUE_TYPE_SHOPPING_NEWS_CATEGORY_LIST_DETAIL = API_REQUEST_URL + API_VERSION_V3 + "fac/EventTenantImpl.json";

	/** 체크인 목록정보 API 경로 */
	public static final String VALUE_TYPE_CHECKIN_LIST = API_REQUEST_URL + API_VERSION_V1 + "inf/checkin/CheckInListImpl.json";
	/** 체크인 상세정보 API 경로 */
	public static final String VALUE_TYPE_CHECKIN_DETAIL_INFO = API_REQUEST_URL + API_VERSION_V1 + "inf/checkin/CheckInDetailInfoImpl.json";
	/** 체크인 댓글 등록 API 경로 */
	public static final String VALUE_TYPE_CHECKIN_ADD_COMMENT = API_REQUEST_URL + API_VERSION_V1 + "inf/checkin/CommentAddImpl.json";
	/** 체크인 댓글 삭제 API 경로 */
	public static final String VALUE_TYPE_CHECKIN_REMOVE_COMMENT = API_REQUEST_URL + API_VERSION_V1 + "inf/checkin/CommentRemoveImpl.json";
	/** 체크인 등록 API 경로 */
	public static final String VALUE_TYPE_CHECKIN_REGIST = API_REQUEST_URL + API_VERSION_V1 + "inf/checkin/CheckInRegisterImpl.json";
	/** 체크인 삭제 API 경로 */
	public static final String VALUE_TYPE_CHECKIN_DELETE = API_REQUEST_URL + API_VERSION_V1 + "inf/checkin/CheckInDeleteImpl.json";
	/** 체크인 공유 API 경로 */
	public static final String VALUE_TYPE_CHECKIN_SHARE = API_REQUEST_URL + API_VERSION_V1 + "inf/checkin/CheckInInfoShareImpl.json";

	/** 주변교통정보 API 경로 */
	public static final String VALUE_TYPE_AROUND_TRAFFIC_INFO = API_REQUEST_URL + API_VERSION_V1 + "prk/AroundTrafficInfoImpl.json";
	/** 차량목록조회 API 경로 */
	public static final String VALUE_TYPE_VEHICLE_LIST = API_REQUEST_URL + API_VERSION_V1 + "prk/VehicleListImpl.json";
	/** 주차정보조회 API 경로 */
	public static final String VALUE_TYPE_PARKING_LOC_INFO = API_REQUEST_URL + API_VERSION_V1 + "prk/ParkingLocInfoTestImpl.json";
	/** 주차추천정보조회 API 경로 */
	public static final String VALUE_TYPE_PARKING_LOC_RECOMMEND_INFO = API_REQUEST_URL + API_VERSION_V1 + "prk/ParkingLocRecommendInfoImpl.json";
	/** 주차결제내역 조회 API 경로 */
	public static final String VALUE_TYPE_BREAKDOWN_LIST = API_REQUEST_URL + API_VERSION_V1 + "prk/BreakdownSearchImpl.json";
	/** 주차현황 조회 API 경로 */
	public static final String VALUE_TYPE_PARKING_CONDITION_INFO = API_REQUEST_URL + API_VERSION_V1 + "prk/ParkingConditionInfoImpl.json";

	/** 사용자 개인정보 조회 API 경로 */
	public static final String VALUE_TYPE_MEMBER_INFO_SEARCH = API_REQUEST_URL + API_VERSION_V1 + "inf/personal/MemberInfoImpl.json";
	/** 사용자 개인정보 수정 API 경로 */
	public static final String VALUE_TYPE_MEMBER_INFO_UPDATE = API_REQUEST_URL + API_VERSION_V1 + "inf/personal/MemberInfoUpdateImpl.json";
	/** 사용자 푸시서비스 수신 설정 API 경로 */
	public static final String VAULE_TYPE_PUSH_RECEIVE_SET = API_REQUEST_URL + API_VERSION_V1 + "inf/personal/PushRecvSetImpl.json";
	/** 관심정보 조회 API 경로 */
	public static final String VALUE_TYPE_INTEREST_INFO_SEARCH = API_REQUEST_URL + API_VERSION_V1 + "inf/personal/InterestInfoImpl.json";
	/** 관심정보 등록 API 경로 */
	public static final String VALUE_TYPE_INTEREST_INFO_REGIST = API_REQUEST_URL + API_VERSION_V1 + "inf/personal/InterestInfoRegisterImpl.json";
	public static final String VALUE_TYPE_INTEREST_INFO_REGIST_V2 = API_REQUEST_URL + API_VERSION_V2 + "personal/InterestInfoRegisterImpl.json";
	/** 관심정보 삭제 API 경로 */
	public static final String VALUE_TYPE_INTEREST_INFO_DELETE = API_REQUEST_URL + API_VERSION_V1 + "inf/personal/InterestInfoDeleteImpl.json";
	public static final String VALUE_TYPE_INTEREST_INFO_DELETE_V2 = API_REQUEST_URL + API_VERSION_V2 + "personal/InterestInfoDeleteImpl.json";
	/** 사용자 검색 API 경로 */
	public static final String VALUE_TYPE_APP_USER_SEARCH = API_REQUEST_URL + API_VERSION_V1 + "inf/personal/AppUserSearchImpl.json";
	/** 차단전화번호 조회 검색 API 경로 */
	public static final String VALUE_TYPE_BLOCK_NUMBER_SEARCH = API_REQUEST_URL + API_VERSION_V1 + "inf/personal/BlockNumberListImpl.json";
	/** 차단전화번호 등록 검색 API 경로 */
	public static final String VALUE_TYPE_BLOCK_NUMBER_REGIST = API_REQUEST_URL + API_VERSION_V1 + "inf/personal/BlockNumberRegImpl.json";
	/** 차단전화번호 등록 검색 API 경로 */
	public static final String VALUE_TYPE_BLOCK_NUMBER_UNREGIST = API_REQUEST_URL + API_VERSION_V1 + "inf/personal/BlockNumberUnRegImpl.json";
	/** 앱 메뉴 조회 API 경로 */
	public static final String VALUE_TYPE_APP_MENU_SEARCH = API_REQUEST_URL + API_VERSION_V2 + "com/AppMenuSearchImpl.json";
	/** 앱 메뉴 조회 API 경로 */
	public static final String VALUE_TYPE_APP_MENU_SEARCH_TOWER = API_REQUEST_URL + API_VERSION_V3 + "com/AppMenuSearchImpl.json";
	/** 앱 버전 조회 API 경로 */
	public static final String VALUE_TYPE_NEW_APP_VERSION_SEARCH = API_REQUEST_URL + API_VERSION_V1 + "com/NewAppVerSearchImpl.json";
	/** 맵 무결성 조회 API 경로 */
	public static final String VALUE_TYPE_INTEGRITY_CHECK = API_REQUEST_URL + API_VERSION_V2 + "com/IntegrityCheckImpl.json";

	/** 쇼핑뉴스 관심목록 조회 API 경로 */
	public static final String VALUE_TYPE_INTEREST_SHOPPING_NEWS_LIST = API_REQUEST_URL + API_VERSION_V2 + "personal/ShoppingInterestList.json";
	/** 컬쳐뉴스 관심목록 조회 API 경로 */
	public static final String VALUE_TYPE_INTEREST_CULTURE_NEWS_LIST = API_REQUEST_URL + API_VERSION_V2 + "personal/CultureInterestList.json";
	/** 매장 관심목록 조회 API 경로 */
	public static final String VALUE_TYPE_INTEREST_FAC_LIST = API_REQUEST_URL + API_VERSION_V2 + "personal/FacInterestListImpl.json";

	/** 공통코드 조회 API 경로 */
	public static final String VALUE_TYPE_COMMON_CODE = API_REQUEST_URL + API_VERSION_V1 + "com/CommonCodeImpl.json";
	/** 데이터버전 조회 API 경로 */
	public static final String VALUE_TYPE_DATA_VER = API_REQUEST_URL + API_VERSION_V1 + "com/DataVerImpl.json";
	/** 프로모션 날짜 조회 API 경로 */
	public static final String VALUE_TYPE_PROMOTION_DATE_CHECK_IMPL = API_REQUEST_URL + API_VERSION_V2 + "com/PromotionDateCheckImpl.json";

	/** 단축 url 요청 API 경로 */
	public static final String VALUE_TYPE_SHORTEN_URL = API_VERSION_V1 + "ShortenUrlService.json";
	/** SEED 암호/복호 요청 API 경로 */
	public static final String VALUE_TYPE_SEED_INFO = API_REQUEST_URL + API_VERSION_V1 + "com/SeedInfoImpl.json";
	/** 사용자 mac address 등록 API 경로 */
	public static final String VALUE_TYPE_MAC_ADDRESS_REGIST = API_REQUEST_URL + API_VERSION_V1 + "inf/personal/MacAddressRegistImpl.json";

	/** 모바일 웹URL 조회 API 경로 */
	public static final String VALUE_TYPE_GUIDE_URL_INFO = API_REQUEST_URL + API_VERSION_V1 + "inf/guide/GuideURLInfoImpl.json";
	/** 시설이용안내 URL 조회 API 경로 */
	public static final String VALUE_TYPE_INFORMATION_WEB_URL = API_REQUEST_URL + API_VERSION_V1 + "inf/guide/InformationWebURLImpl.json";
	/** 멤버 초기화 API 경로 */
	public static final String VALUE_TYPE_MEMBER_INIT = API_REQUEST_URL+ API_VERSION_V1 + "inf/personal/MemberInitImpl.json";
	/** 멤버 등록 API 경로 */
	public static final String VALUE_TYPE_MEMBER_REGISTER = API_REQUEST_URL + API_VERSION_V1 + "inf/personal/MemberRegisterImpl.json";
	/** 안드로이드 gcm update API 경로 */
	public static final String VALUE_TYPE_ANDROID_REGISTER_ID_UPDATE = API_REQUEST_URL + API_VERSION_V1 + "inf/personal/AndroidRegisterIdUpdateImpl.json";
	/** 메뉴통계 API 경로 */
	public static final String VALUE_TYPE_MENU_STATS = API_REQUEST_URL + API_VERSION_V2 + "com/MenuStatsImpl.json";

	/** 구글 단축 URL API 경로 */
	public static final String GOOGLE_SHORT_URL = "https://www.googleapis.com/urlshortener/v1/url?key=";

	/** 롯데멤버스 서버 URL */
	public static final String LOTTE_MEMBERS_URL = "https://member.lpoint.com/";
//	public static final String LOTTE_MEMBERS_URL = "https://testmember.lpoint.com/";
	/** 롯데멤버스 로그인 API 경로 */
	public static final String EXTRA_URL_LOGIN = LOTTE_MEMBERS_URL + "door/sso/authMblUser.jsp";

	/** 롯데멤버스 스마트서비스 약관동의 페이지 경로 */
	public static final String EXTRA_URL_AUTH_AGREE = LOTTE_MEMBERS_URL + "view/door/authAgreeMbl.jsp";
	/** 롯데멤버스 휴면계정 활성화 페이지 경로 */
	public static final String EXTRA_URL_UPDATE_DMCY_MBI = LOTTE_MEMBERS_URL + "view/door/updateDmcyMbl.jsp";
	/** 롯데멤버스 개인정보 변경 페이지 경로 */
	public static final String EXTRA_URL_CHANGE_USER_INFO = LOTTE_MEMBERS_URL + "app/member/LSMB500102.do";
	/** 롯데멤버스 패스워드 변경 페이지 경로 */
	public static final String EXTRA_URL_CHANGE_PASSWORD = LOTTE_MEMBERS_URL + "door/user/mobile/change_user_info.jsp";
	/** 롯데멤버스 정보보정 페이지 경로 */
	public static final String EXTRA_URL_CLEANSING_MBI_USER = LOTTE_MEMBERS_URL + "door/sso/cleansingMblUser.jsp";

	/** 롯데멤버스 회원가입 페이지 경로 */
	public static final String EXTRA_URL_JOIN_MEMBER = LOTTE_MEMBERS_URL + "door/user/mobile/login_common.jsp";
	/** 롯데멤버스 회원탈퇴 페이지 경로 */
	public static final String EXTRA_URL_WITHDRAW = LOTTE_MEMBERS_URL + "door/user/mobile/withdrawl.jsp";
	/** 롯데멤버스 비밀번호찾기 페이지 경로 */
	public static final String EXTRA_URL_REQUEST_PASSWORD = LOTTE_MEMBERS_URL + "door/user/mobile/requestPasswd.jsp";
	/** 롯데멤버스 아이디찾기 페이지 경로 */
	public static final String EXTRA_URL_REQUEST_ID = LOTTE_MEMBERS_URL + "door/user/mobile/requestId.jsp";

	/** 스마트서비스 모바일 페이지 url */
	/** 이벤트 모바일페이지 url */
	public static String SMARTSERVICE_MOBILE_PAGE_EVENT = SERVER_URL + "/views/EventInfoView.do?IF_NO=";
	/** 시설 모바일페이지 url */
	public static String SMARTSERVICE_MOBILE_PAGE_FACILITY = SERVER_URL + "/views/facView.do?FAC_IF_NO=";
	/** 체크인 모바일페이지 url */
	public static String SMARTSERVICE_MOBILE_PAGE_CHECKIN = SERVER_URL + "/views/checkinDetailInfoView.do?CHECKIN_IF_NO=";
	/** 공지사항 모바일 페이지 url */
	public static String SMARTSERVICE_MOBILE_PAGE_NOTICE;
	/** 주차예약 모바일 페이지 url */
	public static String SMARTSERVICE_MOBILE_PAGE_PARKRES;
	/** 자주묻는 질문 모바일 페이지 url */
	public static String SMARTSERVICE_MOBILE_PAGE_FAQ;
	/** 법적고지(이용안내) 모바일 페이지 url */
	public static String SMARTSERVICE_MOBILE_PAGE_LAW;
	/** 법적고지 (위치서비스안내 ) 모바일 페이지 url */
	public static String SMARTSERVICE_MOBILE_PAGE_LAW2;
	/** 개인정보취급방침 모바일 페이지 url */
	public static String SMARTSERVICE_MOBILE_PAGE_PRIVACY;
	/** 공지사항 모바일페이지 url (관리자) */
	public static String SMARTSERVICE_MOBILE_PAGE_NOTICE_ADMIN = SERVER_URL + "/views/noticeList.do";

	/** 스마트서비스 도움말 웹페이지 */
	/** 체크인 상세페이지 도움말 url */
	public static String SMARTSERVICE_HELP_CHECKIN_DETAIL;
	/** 체크인 목록페이지 도움말 url */
	public static String SMARTSERVICE_HELP_CHECKIN_LIST;
	/** 체크인 작성페이지 도움말 url */
	public static String SMARTSERVICE_HELP_CHECKIN_WRITE;
	/** 이벤트 상세페이지 도움말 url */
	public static String SMARTSERVICE_HELP_EVENT_DETAIL;
	/** 이벤트 목록페이지 도움말 url */
	public static String SMARTSERVICE_HELP_EVENT_LIST;
	/** 친구 관리페이지 도움말 url */
	public static String SMARTSERVICE_HELP_FRIEND_LIST;
	/** 메인 페이지 도움말 url */
	public static String SMARTSERVICE_HELP_MAIN;
	/** 지도 길찾기 페이지 도움말 url */
	public static String SMARTSERVICE_HELP_MAP_ROUTE_SEARCH;
	/** 지도페이지 도움말 url */
	public static String SMARTSERVICE_HELP_MAP;
	/** 매장 상세페이지 도움말 url */
	public static String SMARTSERVICE_HELP_STORE_DETAIL;
	/** 매장 목록페이지 도움말 url */
	public static String SMARTSERVICE_HELP_STORE_LIST;
}
