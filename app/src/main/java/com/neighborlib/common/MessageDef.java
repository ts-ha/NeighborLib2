package com.neighborlib.common;

/**
 * <pre>
 *  1. 기능 : 이벤트 처리 공통변수 관리 클래스
 *  2. 처리 개요 :  
 *     - 이벤트 처리 공통변수 관리 클래스
 *  3. 주의사항 : 
 *  4. 작성자/작성일 : 이동식 / 2013. 7. 29.
 * ===================================
 *  5. 수정사항
 *  5.1 요구사항 ID :
 *     - 수정자/수정일 : 이동식 / 2015. 11. 24
 *     - 수정사유/내역 : 신규 API 추가
 * ===================================
 * </pre>
 * 
 * @author : 이동식
 * @version : v1.0.0
 * @see : kr.co.lwt.sts.app.ui 패키지 참조
 * @since : J2SE 6.0
 **/

/**
 * <pre>
 *  1. 기능 :
 *  2. 처리 개요 : 
 *     -
 *  3. 주의사항 : 
 *  4. 작성자/작성일 : 이동식 / 2015. 12. 16.
 * ===================================
 *  5. 수정사항
 *  5.1 요구사항 ID :
 *     - 수정자/수정일 : 김용희 / 2017. 1. 19.
 *     - 수정사유/내역 : 고도화 작업 및 운영건으로 인한 상수 삽입
 * ===================================
 * </pre>
 *  @author : 이동식
 *  @version : v1.0.0
 *  @see :  참조
 *  @since : J2SE 6.0
 **/
public class MessageDef {
	/** 요청 메시지 키 */
	/** 프래그먼트 타입 키 */
	public static final String KEY_FRAGMENT_TYPE = "fragment_type";
	/** 프래그먼트 파라매터 키 */
	public static final String KEY_FRAGMENT_PARAM = "fragment_param";
	/** 프래그먼트 타이틀 키 */
	public static final String KEY_FRAGMENT_TITLE = "fragment_title";
	/** 웹 URL 키 */
	public static final String KEY_FRAGMENT_WEB_URL = "fragment_web_url";
	/** 웹 URL List 키 */
	public static final String KEY_FRAGMENT_WEB_URL_LIST = "fragment_web_url_list";
	/** 웹 초기화 키 */
	public static final String KEY_FRAGMENT_WEB_INIT_TYPE = "fragment_web_init_type";
	/** 경로 선택 인덱스 키 */
	public static final String KEY_FRAGMENT_ROUTE_SELECT_INDEX = "fragment_route_select_index";
	/** 경로 목적지 정보 키 */
	public static final String KEY_FRAGMENT_ROUTE_ENDLOCATION = "fragment_route_endlocation";
	/** 화면 회전값 키 */
	public static final String KEY_ORIENTATION_TYPE = "orientation_type";
	/** 사용자 정보값 키 */
	public static final String KEY_MEMBER_INFO_DATA = "member_info_data";
	/** 사용자 프로필 이미지 키 */
	public static final String KEY_PROFILE_BITMAP = "profile_bitmap";
	/** 맵 초기화 타입 키 */
	public static final String KEY_MAP_INIT_TYPE = "map_init_type";
	/** 편의시설 코드 키 */
	public static final String KEY_MAP_AMENITIES_CD = "map_amenities_cd";
	/** 편의시설 코드 명칭 키 */
	public static final String KEY_MAP_AMENITIES_CD_NM = "map_amenities_cd_nm";

	/** 기본 메시지 코드 */
	/** 응답 성공메시지 코드 */
	public static final int RESPONSE_CODE_SUCCESS = 1;
	/** 응답 실패메시지 코드 */
	public static final int RESPONSE_CODE_FAIL = 2;
	/** 네트워크 에러메시지 코드 */
	public static final int ERROR_CODE_NETWORK_FAIL = 3;
	/** GCM 에러메시지 코드 */
	public static final int ERROR_CODE_GCM_FAIL = 4;
	/** 알수없는 에러메시지 코드 */
	public static final int ERROR_CODE_UNKNOWN_FAIL = 5;
	/** 지도파일 다운로드 에러메시지 코드 */
	public static final int ERROR_CODE_MAP_DOWNLOAD_FAIL = 6;
	/** 지도파일 다운로드 권한 에러메시지 코드 */
	public static final int ERROR_CODE_PERMISSION_DOWNLOAD_FAIL = 7;

	/** 액션바 제목설정메시지 코드 */
	public static final int ACTIVITY_ACTIONBAR_SET_TITLE = 101;
	/** 액션바 제목설정메시지 코드 */
	public static final int ACTIVITY_ACTIONBAR_SET_TITLE_KEEP_MENU = 102;
	/** 액션바 퀵메뉴초기화메시지 코드 */
	public static final int ACTIVITY_ACTIONBAR_OPTIONMENU_RESET = 103;
	/** 메뉴화면 알림 카운트 설정 메시지 코드 */
	public static final int ACTIVITY_MENUVIEW_NOTIFICATION_COUNT = 104;

	/** 프래그먼트 설정메시지 코드 (click) */
	public static final int ACTIVITY_SET_FRAGMENT = 201;
	/** 프래그먼트 추가메시지 코드 */
	public static final int ACTIVITY_ADD_FRAGMENT = 202;
	/** 프래그먼트 설정메시지 코드 */
	public static final int ACTIVITY_REPLACE_FRAGMENT = 203;

	/** BEACON 메시지 코드*/
	/** API 실행 요청이 실패할 경우 메시지 코드*/
	public static final int ERROR_REQUEST_FAILED = 1101;
	/** API실행 요청시간이 초과된 경우 메시지 코드*/
	public static final int ERROR_REQUEST_TIMEOUT = 1102;
	/** API의 파라미터의 값을 잘못 입력한 경우 메시지 코드*/
	public static final int ERROR_WRONG_PARAMETER = 1103;
	/** API의 사용 권한이 없는 경우 메시지 코드*/
	public static final int ERROR_UNAUTHORIZED = 1104;
	/** 이미 사용된 경우 메시지 코드*/
	public static final int ERROR_COUPON_ALREADY_USED = 1201;
	/**외부 쿠폰이 매진 되었을 경우 메시지 코드*/
	public static final int ERROR_EXTERNAL_COUPON_SOLDOUT = 1301;
	/**외부 쿠폰이 이미 발행되었을 경우 메시지 코드*/
	public static final int ERROR_EXTERNAL_COUPON_ALREADY_PUBLISH = 1302;
	/**클라이언트의 네트워크가 사용 불가능일 경우 메시지 코드*/
	public static final int ERROR_CLIENT_NETWORK_UNAVAILABLE = 1401;
	/** 서버에 에러가 발생한 경우. API 요청은 처리되었으나, 서버 내부에서 오류가 발생한 경우 메시지 코드 */
	public static final int ERROR_SERVER_PROBLEM = 1501;
	/**API호출 파라미터가 NULL이거나 오류인 경우 메시지 코드*/
	public static final int ERROR_WRONG_CALL_PARAMETER = 1601;
	/** API요청결과를 받은 JSON PARSING을 해당키에 대한 값이 없는 경우 메시지 코드*/
	public static final int ERROR_JSON_PARSING_NO_VALUE = 1701;
	/** Beacon Error Log Tag*/
	public static final String ERROR_BEACON_LOG_TAG = "Beacon Error : ";
	/** Beacon Error Log Content*/
	public static final String ERROR_BEACON_LOG_CONTENT = "Beacon Error Code ";

	/** API REQUEST 메시지 코드 */
	/** 멤버 초기화 성공메시지 상수 */
	public static final int API_MEMBER_INIT_SUCCESS = 10001;
	/** 멤버 초기화 실패메시지 상수 */
	public static final int API_MEMBER_INIT_FAIL = 10002;
	/** 멤버 등록 성공메시지 상수 */
	public static final int API_MEMBER_REGIST_SUCCESS = 10003;
	/** 멤버 등록 실패메시지 상수 */
	public static final int API_MEMBER_REGIST_FAIL = 10004;
	/** 롯데패밀리 로그인 성공메시지 상수 */
	public static final int API_LOTTE_LOGIN_SUCCESS = 10005;
	/** 롯데패밀리 로그인 실패메시지 상수 */
	public static final int API_LOTTE_LOGIN_FAIL = 10006;
	/** GCM regist 성공메시지 상수 */
	public static final int API_ANDROID_REGISTER_ID_UPDATE_SUCCESS = 10007;
	/** GCM regist 실패메시지 상수 */
	public static final int API_ANDROID_REGISTER_ID_UPDATE_FAIL = 10008;

	/** 시설 목록정보 성공메시지 상수 */
	public static final int API_FAC_LIST_SUCCESS = 10101;
	/** 시설 목록정보 실패메시지 상수 */
	public static final int API_FAC_LIST_FAIL = 10102;
	/** 시설 상세정보 성공메시지 상수 */
	public static final int API_FAC_INFO_SUCCESS = 10103;
	/** 시설 상세정보 실패메시지 상수 */
	public static final int API_FAC_INFO_FAIL = 10104;
	/** 시설 상세정보 수정 성공메시지 상수 */
	public static final int API_FAC_INFO_UPDATE_SUCCESS = 10105;
	/** 시설 상세정보 수정 실패메시지 상수 */
	public static final int API_FAC_INFO_UPDATE_FAIL = 10106;
	/** 시설 별점정보 등록 성공메시지 상수 */
	public static final int API_STAR_POINT_REGISTER_SUCCESS = 10107;
	/** 시설 별점정보 등록 실패메시지 상수 */
	public static final int API_STAR_POINT_REGISTER_FAIL = 10108;
	/** 카테고리정보 조회 성공메시지 상수 */
	public static final int API_CATEGORY_INFO_SUCCESS = 10109;
	/** 카테고리정보 조회 실패메시지 상수 */
	public static final int API_CATEGORY_INFO_FAIL = 10110;
	/** 계열사정보 조회 성공메시지 상수 */
	public static final int API_SUBSIDIARY_INFO_SUCCESS = 10111;
	/** 계열사정보 조회 실패메시지 상수 */
	public static final int API_SUBSIDIARY_INFO_FAIL = 10112;
	/** 층별 시설 목록정보 성공메시지 상수 */
	public static final int API_FAC_FL_LIST_SUCCESS = 10113;
	/** 층별 시설 목록정보 실패메시지 상수 */
	public static final int API_FAC_FL_LIST_FAIL = 10114;
	/** 층별 핫존 목록정보 성공메시지 상수 */
	public static final int API_HOT_FL_LIST_SUCCESS = 10115;
	/** 층별 핫존 목록정보 실패메시지 상수 */
	public static final int API_HOT_FL_LIST_FAIL = 10116;
	/** 시설 등록대기 이미지 수정 성공메시지 상수 */
	public static final int API_FAC_IMAGE_CANCEL_SUCCESS = 10117;
	/** 시설 등록대기 이미지 수정 실패메시지 상수 */
	public static final int API_FAC_IMAGE_CANCEL_FAIL = 10118;
	/** 스토어뷰 목록정보 성공메시지 상수 */
	public static final int API_VR_STORE_VIEW_SUCCESS = 10123;
	/** 스토어뷰 목록정보 실패메시지 상수 */
	public static final int API_VR_STORE_VIEW_FAIL = 10124;
	/** 시설 상세정보 (POI ID이용) 성공메시지 상수 */
	public static final int API_POI_FAC_INFO_SUCCESS = 10125;
	/** 시설 상세정보 (POI ID이용) 실패메시지 상수 */
	public static final int API_POI_FAC_INFO_FAIL = 10126;
	/** 맛집 목록정보 성공메시지 상수 */
	public static final int API_FNB_LIST_SUCCESS = 10127;
	/** 맛집 목록정보 실패메시지 상수 */
	public static final int API_FNB_LIST_FAIL = 10128;
	/** 맛집 상세정보 성공메시지  */
	public static final int API_FNB_INFO_SUCCESS = 10129;
	/** 맛집 상세정보 실패메시지 */
	public static final int API_FNB_INFO_FAIL = 10130;
	/** 시설 상세정보 (POI ID이용) V2 성공메시지 상수 */
	public static final int API_POI_FAC_INFO_OVERLAY_SUCCESS = 10131;
	/** 시설 상세정보 (POI ID이용) V2 실패메시지 상수 */
	public static final int API_POI_FAC_INFO_OVERLAY_FAIL = 10132;
	/** 편의시설 목록정보(지도) 성공메시지 상수 */
	public static final int API_MAP_AMENITIES_SEARCH_SUCCESS = 10133;
	/** 편의시설 목록정보(지도) 실패메시지 상수 */
	public static final int API_MAP_AMENITIES_SEARCH_FAIL = 10134;
	/** 편의시설 코드 목록정보(지도) 성공메시지 상수 */
	public static final int API_AMENITIES_CODE_LIST_SUCCESS = 10135;
	/** 편의시설 코드 목록정보(지도) 실패메시지 상수 */
	public static final int API_AMENITIES_CODE_LIST_FAIL = 10136;
	/** 지도층 목록 조회 성공메세지 상수 */
	public static final int API_BLD_CD_CODE_LIST_SUCCESS = 10137;
	/** 지도층 목록 조회 실패메세지 상수 */
	public static final int API_BLD_CD_CODE_LIST_FAIL = 10138;
	/** 지도 타워 층 목록 조회 성공메세지 상수 */
	public static final int API_BLD_TWER_CODE_LIST_SUCCESS = 10139;
	/** 지도 타워 층 목록 조회 실패메세지 상수 */
	public static final int API_BLD_TWER_CODE_LIST_FAIL = 10140;
	/** 지도 쇼핑몰 층 목록 조회 성공메세지 상수 */
	public static final int API_BLD_SHPM_CODE_LIST_SUCCESS = 10141;
	/** 지도 쇼핑몰 층 목록 조회 실패메세지 상수 */
	public static final int API_BLD_SHPM_CODE_LIST_FAIL = 10142;

	/** 경로 정보 조회 성공메시지 상수 */
	public static final int API_ROUTE_GUIDE_INFO_SUCCESS = 20001;
	/** 경로 정보 조회 실패메시지 상수 */
	public static final int API_ROUTE_GUIDE_INFO_FAIL = 20002;
	/** VR 정보 조회 성공메시지 상수 */
	public static final int API_VR_BASED_FAC_INFO_SUCCESS = 20003;
	/** VR 정보 조회 실패메시지 상수 */
	public static final int API_VR_BASED_FAC_INFO_FAIL = 20004;

	/** 상세정보 공유 성공메시지 상수 */
	public static final int API_DETAIL_INFO_SHARE_SUCCESS = 30021;
	/** 상세정보 공유 실패메시지 상수 */
	public static final int API_DETAIL_INFO_SHARE_FAIL = 30022;
	/** 위치 공유 성공메시지 상수 */
	public static final int API_POSITION_INFO_SHARE_SUCCESS = 30023;
	/** 위치 공유 실패메시지 상수 */
	public static final int API_POSITION_INFO_SHARE_FAIL = 30024;
	/** 쇼핑/컬쳐 목록정보 성공메시지 상수 */
	public static final int API_NEWS_LIST_SUCCESS = 30025;
	/** 쇼핑/컬쳐 목록정보 실패메시지 상수 */
	public static final int API_NEWS_LIST_FAIL = 30026;
	/** 쇼핑/컬쳐 상세정보 성공메시지 상수 */
	public static final int API_NEWS_DETAIL_SUCCESS = 30027;
	/** 쇼핑/컬쳐 상세정보 실패메시지 상수 */
	public static final int API_NEWS_DETAIL_FAIL = 30028;
	/** 이벤트 목록정보(지도) 성공메시지 상수 */
	public static final int API_EVENT_LIST_MAP_SUCCESS = 30029;
	/** 이벤트 목록정보(지도) 실패메시지 상수 */
	public static final int API_EVENT_LIST_MAP_FAIL = 30030;

	/** 메인화면배너 목록정보 성공메시지 상수 */
	public static final int API_MAIN_VIEW_LIST_SUCCESS = 30031;
	/** 메인화면배너 목록정보 실패메시지 상수 */
	public static final int API_MAIN_VIEW_LIST_FAIL = 30032;
	/** 아트쇼 정보 성공메시지 상수 */
	public static final int API_ART_SHOW_SUCCESS = 30033;
	/** 아트쇼 정보 실패메시지 상수 */
	public static final int API_ART_SHOW_FAIL = 30034;
	/** 공연스케줄 날짜 조회 성공메시지 상수 */
	public static final int API_EXHBT_DATE_LIST_SUCCESS = 30035;
	/** 공연스케줄 날짜 조회 실패메시지 상수 */
	public static final int API_EXHBT_DATE_LIST_FAIL = 30036;
	/** 공연스케줄 조회 성공메시지 상수 */
	public static final int API_EXHBT_LIST_SUCCESS = 30037;
	/** 공연스케줄 조회 실패메시지 상수 */
	public static final int API_EXHBT_LIST_FAIL = 30038;
	/** 쇼핑/컬쳐 뉴스 리스트 조회 성공메시지 상수 */
	public static final int API_SHOPPING_CULTURE_NEWS_LIST_SUCCESS = 30039;
	/** 쇼핑/컬쳐 뉴스 리스트 조회 실패메시지 상수 */
	public static final int API_SHOPPING_CULTURE_NEWS_LIST_FAIL = 30040;
	/** 이벤트 상세정보(지도) 성공메시지 상수 */
	public static final int API_EVENT_DETAIL_MAP_SUCCESS = 30041;
	/** 이벤트 상세정보(지도) 실패메시지 상수 */
	public static final int API_EVENT_DETAIL_MAP_FAIL = 30042;
	/** 체크인 목록정보 성공메시지 상수 */
	public static final int API_CHECKIN_LIST_SUCCESS = 40001;
	/** 체크인 목록정보 실패메시지 상수 */
	public static final int API_CHECKIN_LIST_FAIL = 40002;
	/** 체크인 상세정보 성공메시지 상수 */
	public static final int API_CHECKIN_DETAIL_INFO_SUCCESS = 40003;
	/** 체크인 상세정보 실패메시지 상수 */
	public static final int API_CHECKIN_DETAIL_INFO_FAIL = 40004;
	/** 체크인 댓글 등록 성공메시지 상수 */
	public static final int API_CHECKIN_ADD_COMMENT_SUCCESS = 40005;
	/** 체크인 댓글 등록 실패메시지 상수 */
	public static final int API_CHECKIN_ADD_COMMENT_FAIL = 40006;
	/** 체크인 댓글 삭제 성공메시지 상수 */
	public static final int API_CHECKIN_REMOVE_COMMENT_SUCCESS = 40007;
	/** 체크인 댓글 삭제 실패메시지 상수 */
	public static final int API_CHECKIN_REMOVE_COMMENT_FAIL = 40008;
	/** 체크인 등록 성공메시지 상수 */
	public static final int API_CHECKIN_REGIST_SUCCESS = 40009;
	/** 체크인 등록 실패메시지 상수 */
	public static final int API_CHECKIN_REGIST_FAIL = 40010;
	/** 체크인 삭제 성공메시지 상수 */
	public static final int API_CHECKIN_DELETE_SUCCESS = 40011;
	/** 체크인 삭제 실패메시지 상수 */
	public static final int API_CHECKIN_DELETE_FAIL = 40012;
	/** 체크인 공유 성공메시지 상수 */
	public static final int API_CHECKIN_SHARE_SUCCESS = 40013;
	/** 체크인 공유 실패메시지 상수 */
	public static final int API_CHECKIN_SHARE_FAIL = 40014;
	/** 체크인 삭제 성공메시지 상수 */
	public static final int API_CHECKIN_REMOVE_SUCCESS = 40015;
	/** 체크인 삭제 실패메시지 상수 */
	public static final int API_CHECKIN_REMOVE_FAIL = 40016;

	/** 차량목록조회 성공메시지 상수 */
	public static final int API_VEHICLE_LIST_SUCCESS = 50001;
	/** 차량목록조회 실패메시지 상수 */
	public static final int API_VEHICLE_LIST_FAIL = 50002;
	/** 주차정보조회 성공메시지 상수 */
	public static final int API_PARKING_LOC_INFO_SUCCESS = 50101;
	/** 주차정보조회 실패메시지 상수 */
	public static final int API_PARKING_LOC_INFO_FAIL = 50102;
	/** 주차결제내역 조회 성공메시지 상수 */
	public static final int API_BREAKDOWN_LIST_SUCCESS = 50301;
	/** 주차결제내역 조회 실패메시지 상수 */
	public static final int API_BREAKDOWN_LIST_FAIL = 50302;
	/** 주변교통정보 성공메시지 상수 */
	public static final int API_AROUND_TRAFFIC_INFO_SUCCESS = 50401;
	/** 주변교통정보 실패메시지 상수 */
	public static final int API_AROUND_TRAFFIC_INFO_FAIL = 50402;
	/** 주차추천정보조회 성공메시지 상수 */
	public static final int API_PARKING_RECOMMEND_INFO_SUCCESS = 50501;
	/** 주차추천정보조회 실패메시지 상수 */
	public static final int API_PARKING_RECOMMEND_INFO_FAIL = 50502;
	/** 주차현황정보조회 성공메시지 상수 */
	public static final int API_PARKING_CONDITION_INFO_SUCCESS = 50601;
	/** 주차현황정보조회 실패메시지 상수 */
	public static final int API_PARKING_CONDITION_INFO_FAIL = 50602;

	/** 사용자 개인정보 조회 성공메시지 상수 */
	public static final int API_MEMBER_INFO_SEARCH_SUCCESS = 60001;
	/** 사용자 개인정보 조회 실패메시지 상수 */
	public static final int API_MEMBER_INFO_SEARCH_FAIL = 60002;
	/** 사용자 개인정보 수정 성공메시지 상수 */
	public static final int API_MEMBER_INFO_UPDATE_SUCCESS = 60101;
	/** 사용자 개인정보 수정 실패메시지 상수 */
	public static final int API_MEMBER_INFO_UPDATE_FAIL = 60102;
	/** 사용자 개인정보 삭제 성공메시지 상수 */
	public static final int API_MEMBER_INFO_DELETE_SUCCESS = 60103;
	/** 사용자 개인정보 삭제 실패메시지 상수 */
	public static final int API_MEMBER_INFO_DELETE_FAIL = 60104;
	/** 사용자 관심정보 조회 성공메시지 상수 */
	public static final int API_INTEREST_INFO_SEARCH_SUCCESS = 60105;
	/** 사용자 관심정보 조회 실패메시지 상수 */
	public static final int API_INTEREST_INFO_SEARCH_FAIL = 60106;
	/** 사용자 관심정보 등록 성공메시지 상수 */
	public static final int API_INTEREST_INFO_REGIST_SUCCESS = 60101;
	/** 사용자 관심정보 등록 실패메시지 상수 */
	public static final int API_INTEREST_INFO_REGIST_FAIL = 60108;
	/** 사용자 관심정보 삭제 성공메시지 상수 */
	public static final int API_INTEREST_INFO_DELETE_SUCCESS = 60109;
	/** 사용자 관심정보 삭제 실패메시지 상수 */
	public static final int API_INTEREST_INFO_DELETE_FAIL = 60110;
	/** 스마트서비스 사용친구 조회 성공메시지 상수 */
	public static final int API_APP_USER_SEARCH_SUCCESS = 60111;
	/** 스마트서비스 사용친구 조회 실패메시지 상수 */
	public static final int API_APP_USER_SEARCH_FAIL = 60112;
	/** 차단전화번호 조회 성공메시지 상수 */
	public static final int API_BLOCK_LIST_SEARCH_SUCCESS = 60113;
	/** 차단전화번호 조회 실패메시지 상수 */
	public static final int API_BLOCK_LIST_SEARCH_FAIL = 60114;
	/** 차단전화번호 등록 성공메시지 상수 */
	public static final int API_BLOCK_LIST_REGIST_SUCCESS = 60115;
	/** 차단전화번호 등록 실패메시지 상수 */
	public static final int API_BLOCK_LIST_REGIST_FAIL = 60116;
	/** 차단전화번호 취소 성공메시지 상수 */
	public static final int API_BLOCK_LIST_UNREGIST_SUCCESS = 60117;
	/** 차단전화번호 취소 실패메시지 상수 */
	public static final int API_BLOCK_LIST_UNREGIST_FAIL = 60118;
	/** 단말 푸시설정 요청 성공메시지 상수 */
	public static final int API_PUSH_RECEIVE_SET_SUCCESS = 60119;
	/** 단말 푸시설정 요청 실패메시지 상수 */
	public static final int API_PUSH_RECEIVE_SET_FAIL = 60120;
	/** 사용자 관심정보 조회 실패메시지 상수 */
	public static final int API_INTEREST_FAC_LIST_SUCCESS = 60121;
	/** 사용자 관심정보 등록 성공메시지 상수 */
	public static final int API_INTEREST_FAC_LIST_FAIL = 60122;

	/** 모바일용 웹 URL 조회 성공메시지 상수 */
	public static final int API_GUIDE_URL_INFO_SUCCESS = 70001;
	/** 모바일용 웹 URL 조회 성공메시지 상수 */
	public static final int API_GUIDE_URL_INFO_FAIL = 70002;
	/** 시설이용안내 URL 조회 성공메시지 상수 */
	public static final int API_INFORMATION_WEB_URL_SUCCESS = 70003;
	/** 시설이용안내 URL 조회 성공메시지 상수 */
	public static final int API_INFORMATION_WEB_URL_FAIL = 70004;

	/** 앱 메뉴 검색 성공메시지 상수 */
	public static final int API_APP_MENU_SEARCH_SUCCESS = 90001;
	/** 앱 메뉴 검색 실패메시지 상수 */
	public static final int API_APP_MENU_SEARCH_FAIL = 90002;
	/** 앱 버전 검색 성공메시지 상수 */
	public static final int API_NEW_APP_VERSION_SEARCH_SUCCESS = 90003;
	/** 앱 버전 검색 실패메시지 상수 */
	public static final int API_NEW_APP_VERSION_SEARCH_FAIL = 90004;
	/** 앱 무결성 체크 성공메시지 상수 */
	public static final int API_INTEGRITY_CHECK_SUCCESS = 90005;
	/** 앱 무결성 체크 실패메시지 상수 */
	public static final int API_INTEGRITY_CHECK_FAIL = 90006;

	/** 공통코드 검색 성공메시지 상수 */
	public static final int API_COMMON_CODE_SUCCESS = 100001;
	/** 공통코드 검색 실패메시지 상수 */
	public static final int API_COMMON_CODE_FAIL = 100002;
	/** 파일 다운로드 성공메시지 상수 */
	public static final int API_FILE_DOWNLOAD_SUCCESS = 100003;
	/** 파일 다운로드 실패메시지 상수 */
	public static final int API_FILE_DOWNLOAD_FAIL = 100004;
	/** 데이터버전 조회 성공메시지 상수 */
	public static final int API_DATA_VER_INFO_SUCCESS = 100005;
	/** 데이터버전 조회 실패메시지 상수 */
	public static final int API_DATA_VER_INFO_FAIL = 100006;
	/** 단축 url 요청 성공메시지 상수 */
	public static final int API_SHORTEN_URL_SUCCESS = 100007;
	/** 단축 url 요청 실패메시지 상수 */
	public static final int API_SHORTEN_URL_FAIL = 100008;
	/** SEED 암호/복호 요청 성공메시지 상수 */
	public static final int API_SEED_INFO_SUCCESS = 100009;
	/** SEED 암호/복호 요청 실패메시지 상수 */
	public static final int API_SEED_INFO_FAIL = 100010;
	/** 사용자 mac address 등록 요청 성공메시지 상수 */
	public static final int API_MAC_ADDRESS_REGIST_SUCCESS = 100011;
	/** 사용자 mac address 등록 요청 실패메시지 상수 */
	public static final int API_MAC_ADDRESS_REGIST_FAIL = 100012;
	/** 프로모션 날짜정보 성공메시지 상수 */
	public static final int API_PROMOTION_DATE_CHECK_SUCCESS = 100013;
	/** 프로모션 날짜정보 실패메시지 상수 */
	public static final int API_PROMOTION_DATE_CHECK_FAIL = 100014;
	
	/** 앱 메뉴 통계 성공메시지 상수 */
	public static final int API_TYPE_MENU_STATS_SUCCESS = 110001;
	/** 앱 메뉴 통계 실패메시지 상수 */
	public static final int API_TYPE_MENU_STATS_FAIL = 110002;
	
	/** FRAGMENT 메시지 코드 */
	/** FRAGMENT 미정의 상수 */
	public static final int FRAGMENT_TYPE_NONE = 200001;
	/** FRAGMENT 미정의 상수 */
	public static final int FRAGMENT_TYPE_WEP_SAMPLE = 200002;
	/** 메인화면 호출메시지 상수 */
	public static final int FRAGMENT_TYPE_MAIN_PAGE = 200101;
	/** 아트쇼 호출메시지 상수 */
	public static final int FRAGMENT_TYPE_ART_SHOW = 200102;

	/** 매장안내 호출메시지 상수 */
	public static final int FRAGMENT_TYPE_STORE_GUIDE = 200201;
	/** 매장검색 호출메시지 상수 */
	public static final int FRAGMENT_TYPE_STORE_SEARCH = 200202;
	/** 파노라마 호출메시지 상수 */
	public static final int FRAGMENT_TYPE_VR_LIST = 200205;
	/** 매장정보 호출메시지 상수 */
	public static final int FRAGMENT_TYPE_STORE_DETAIL = 200206;
	/** 매장정보 호출메시지 상수 (공유불가) */
	public static final int FRAGMENT_TYPE_STORE_DETAIL_NONE_SHARE = 200207;
	/** 맛집검색 호출메시지 상수 */
	public static final int FRAGMENT_TYPE_RESTAURANT_LIST = 200208;

	/** 지도 호출메시지 상수 */
	public static final int FRAGMENT_TYPE_DEFAULT_MAP = 200301;
	/** 지도 호출메시지 상수 (공유불가) */
	public static final int FRAGMENT_TYPE_DEFAULT_MAP_NONE_SHARE = 200302;
	/** 지도 호출메시지 상수 (내위치 표시용, 상단바 퀵메뉴 표시안함) */
	public static final int FRAGMENT_TYPE_DEFAULT_MAP_LOCATION = 200303;
	/** 주차예약 호출메시지 상수 */
	public static final int FRAGMENT_TYPE_PARKING_RESERVATION = 200304;
	/** 주차현황 호출메시지 상수 (주차장 지도표시) */
	public static final int FRAGMENT_TYPE_PARKING_CONDITION_MAP = 200305;
	/** 길찾기 호출메시지 상수 (지도화면에서 길찾기UI 표시) */
	public static final int FRAGMENT_TYPE_ROUTE_SEARCH = 200306;
	/** 경로선택 호출메시지 상수 */
	public static final int FRAGMENT_TYPE_ROUTE_SELECT = 200307;
	/** 경로안내 호출메시지 상수 */
	public static final int FRAGMENT_TYPE_ROUTE_GUIDE = 200308;

	/** 이벤트/신상품 호출메시지 상수 (매장에 종속된 목록 표출) */
	public static final int FRAGMENT_TYPE_EVENT_NEW_STORE = 200402;
	/** 이벤트 정보 호출메시지 상수 */
	public static final int FRAGMENT_TYPE_EVENT_NEW_DETAIL = 200403;
	/** 이벤트 정보 호출메시지 상수 (공유불가) */
	public static final int FRAGMENT_TYPE_EVENT_NEW_DETAIL_NONE_SHARE = 200404;
	/** 이벤트 정보 호출메시지 상수 (상세 웹페이지) */
	public static final int FRAGMENT_TYPE_EVENT_NEW_DETAIL_WEB = 200405;
	/** 쇼핑뉴스 목록 호출메시지 상수 */
	public static final int FRAGMENT_TYPE_NEWS_SHOPPING_LIST = 200406;
	/** 컬쳐뉴스 목록 호출메시지 상수 */
	public static final int FRAGMENT_TYPE_NEWS_CULTURE_LIST = 200407;
	/** 쇼핑/컬쳐뉴스 상세 호출메시지 상수 */
	public static final int FRAGMENT_TYPE_NEWS_SHOPPING_CULTURE_DETAIL = 200408;
	/** 쇼핑/컬쳐뉴스 상세 호출메시지 상수 (공유불가) */
	public static final int FRAGMENT_TYPE_NEWS_SHOPPING_CULTURE_DETAIL_NONE_SHARE = 200409;
	/** 공연 스케줄 목록 호출 메시지 상수 */
	public static final int FRAGMENT_TYPE_EXHIBITION_SCHEDULE = 200410;

	/** 체크인 호출메시지 상수 */
	public static final int FRAGMENT_TYPE_CHECK_IN_LIST = 200501;
	/** 체크인 작성 호출메시지 상수 */
	public static final int FRAGMENT_TYPE_CHECK_IN_NEW = 200502;
	/** 체크인 정보 호출메시지 상수 */
	public static final int FRAGMENT_TYPE_CHECK_IN_DETAIL = 200503;
	/** 체크인 정보 호출메시지 상수 (공유불가) */
	public static final int FRAGMENT_TYPE_CHECK_IN_DETAIL_NONE_SHARE = 200504;

	/** 내차찾기 호출메시지 상수 */
	public static final int FRAGMENT_TYPE_SEARCH_MY_CAR_LIST = 200601;
	/** 주차정보 호출메시지 상수 */
	public static final int FRAGMENT_TYPE_SEARCH_MY_CAR_DETAIL = 200602;
	/** 영수증스캔 호출메시지 상수 */
	public static final int FRAGMENT_TYPE_SEARCH_MY_CAR_PAYMENT = 200603;
	/** 주차결제내역 호출메시지 상수 */
	public static final int FRAGMENT_TYPE_BREAKDOWN_LIST = 200604;
	/** 주변교통정보 호출메시지 상수 */
	public static final int FRAGMENT_TYPE_AROUND_TRAFFIC_INFO = 200606;
	/** 주변교통정보 호출메시지 상수 (하위 메뉴) */
	public static final int FRAGMENT_TYPE_AROUND_TRAFFIC_INFO_SUB_MENU = 200607;
	/** 주차추천위치 호출메시지 상수 */
	public static final int FRAGMENT_TYPE_RECOMMEND_PARKING = 200608;
	/** 주차비 결제 호출메시지 상수 */
	public static final int FRAGMENT_TYPE_PARKING_PAYMENT = 200609;

	/** 개인정보 호출메시지 상수 */
	public static final int FRAGMENT_TYPE_MEMBER_INFO = 200701;
	/** 개인정보관리 호출메시지 상수 */
		public static final int FRAGMENT_TYPE_MEMBER_INFO_DETAIL = 200702;
	/** 관심이벤트 호출메시지 상수 */
	public static final int FRAGMENT_TYPE_MEMBER_INTEREST_EVENT = 200703;
	/** 관심카테고리 호출메시지 상수 */
	public static final int FRAGMENT_TYPE_MEMBER_INTEREST_CATEGORY = 200704;
	/** 관심시설 호출메시지 상수 */
	public static final int FRAGMENT_TYPE_MEMBER_INTEREST_FACILITY = 200705;
	/** 친구목록 호출메시지 상수 */
	public static final int FRAGMENT_TYPE_FRIENDS_LIST = 200706;
	/** 친구선택 호출메시지 상수 */
	public static final int FRAGMENT_TYPE_FRIEND_SELECT = 200707;

	/** 공지사항 호출메시지 상수 */
	public static final int FRAGMENT_TYPE_NOTICE = 200801;
	/** 도움말 호출메시지 상수 */
	public static final int FRAGMENT_TYPE_GUIDE_ADV = 200802;
	/** 자주묻는질문 호출메시지 상수 */
	public static final int FRAGMENT_TYPE_FAQ = 200803;
	/** 시설이용안내 호출메시지 상수 */
	public static final int FRAGMENT_TYPE_INTRO_USE = 200804;
	/** 시설이용안내 상세 호출메시지 상수 */
	public static final int FRAGMENT_TYPE_GUIDE_DETAIL = 200805;
	/** 파노라마 뷰어 호출메시지 상수 */
	public static final int FRAGMENT_TYPE_PANORAMA_VIEWER = 200806;
	/** 비상대피로안내 호출메시지 상수 */
	public static final int FRAGMENT_TYPE_INTRO_EVAC = 200807;
	/** 공지사항 호출메시지 상수 (관리자) */
	public static final int FRAGMENT_TYPE_NOTICE_ADMIN = 200808;
	/** 웹뷰화면 호출메시지 상수 */
	public static final int FRAGMENT_TYPE_WEBVIEW_DEFAULT = 200809;
	/** SNS 채널 호출 메시지 상수 */
	public static final int FRAGMENT_TYPE_SNS_CHANEL = 200411;

	/** 설정 호출메시지 상수 */
	public static final int FRAGMENT_TYPE_SETTINGS = 200901;

	/** 푸시알림내역 호출메시지 상수 */
	public static final int FRAGMENT_TYPE_NOTIFICATION_LIST = 201001;

	/** 비콘 호출메시지 상수 */
	public static final int FRAGMENT_TYPE_BEACON = 201101;

	/** 쿠폰상세페이지 호출메시지 상수 */
	public static final int FRAGMENT_TYPE_BEACON_COUPON_DETAIL = 201102;
	/** 기업쿠폰 상세페이지 호출메시지 상수 */
	public static final int FRAGMENT_TYPE_BEACON_COMPANY_COUPON_DETAIL = 201103;
	/** 쿠폰상세페이지 호출메시지 상수 */
	public static final int FRAGMENT_TYPE_BEACON_USED_COUPON_DETAIL = 201104;
	/** 직원 비밀 번호 확인 성공 메시지 추가 */
	public static final int MESSAGE_EMPLOYEE_PASSWORD_SUCCESS = 201105;
	/** 쿠폰 다운로드 성공/실패 메시지 */
	public static final int MESSAGE_COUPON_DOWNLOAD_SUCCESS = 201106;
	public static final int MESSAGE_COUPON_DOWNLOAD_FAIL = 201107;

	/** 쿠폰 사용 성공/실패 메시지 */
	public static final int MESSAGE_COUPON_USE_SUCCESS = 201108;
	public static final int MESSAGE_COUPON_USE_FAIL = 201109;

	/** 사용 완료 쿠폰 삭제 메시지 */
	public static final int MESSAGE_USED_COUPON_DELETE_SUCCESS = 201110;

	/** 카운트 업데이트 */
	public static final int MESSAGE_DOWNLOAD_COUPON_COUNT_UPDATE = 201111;
	public static final int MESSAGE_USED_COUPON_COUNT_UPDATE = 201112;

	/** FRAGMENT 업데이트 상수 */
	/** 메인 이벤트 업데이트메시지 상수 */
	public static final int FRAGMENT_UPDATE_MAIN_EVENT_INFO = 300001;
	/** 지도 방향 업데이트메시지 상수 */
	public static final int FRAGMENT_UPDATE_MAP_ORIENTATION = 300004;
	/** 화면초기화 업데이트메시지 상수 */
	public static final int FRAGMENT_UPDATE_INIT_FRAGMENT = 300005;
	/** 화면데이터 업데이트메시지 상수 */
	public static final int FRAGMENT_UPDATE_REFRESH_DATA = 300006;
	/** 로그인 정보 업데이트메시지 상수 */
	public static final int FRAGMENT_UPDATE_LOGIN_STATE = 300007;
	/** 메뉴 명칭 정보 업데이트메시지 상수 */
	public static final int FRAGMENT_UPDATE_MAIN_MENU_INFO = 300008;
	
	/** 전체 권한 Request 상수*/
	public static final int PERMISSION_REQUEST_ALL = 51;
	/** 카메라 권한 Request 상수*/
	public static final int PERMISSION_REQUEST_CAMERA = 52;
	/** 주소록 권한 Request 상수*/
	public static final int PERMISSION_REQUEST_CONTACTS = 53;
	/** */
	public static final int PERMISSION_REQUEST_COARSE_LOCATION = 54;
	public static final int PERMISSION_REQUEST_FINE_LOCATION = 55;
	/** 저장소 권한 Request 상수*/
	public static final int PERMISSION_REQUEST_EXTERNAL_STORAGE = 56;
	/** 길찾기 권한 Request 상수*/
	public static final int PERMISSION_REQUEST_ROADSEARCH = 57;
	/** 위치 찾기 권한 Request 상수*/
	public static final int PERMISSION_REQUEST_FIND_MY_LOCATION = 58;
	/** Beacon 검색 권한 Request 상수*/
	public static final int PERMISSION_REQUEST_BEACON_SEARCH = 59;
	/** L.Point Beacon 권한 Request 상수*/
	public static final int PERMISSION_REQUEST_BEACON_POINT = 60;
	/** Download를 위한 저장소 권한 Request 상수*/
	public static final int PERMISSION_REQUEST_DOWNLOAD_STORAGE = 61;
	/** Beacon Service를 ON/OFF 상수*/
	public static final int BEACON_STATE = 1;

	/**SNS 설정*/
	/** Facebook SNS 타입 상수 */
	public static final int SNS_TYPE_FACEBOOK = 80;
	/** Tower Blog SNS 타입 상수 */
	public static final int SNS_TYPE_TOWER_BLOG = 81;
	/** Mall Blog SNS 타입 상수 */
	public static final int SNS_TYPE_MALL_BLOG = 82;
	/** Youtube SNS 타입 상수 */
	public static final int SNS_TYPE_YUTUBE = 83;
	/** Instagram SNS 타입 상수 */
	public static final int SNS_TYPE_INSTAGRAM = 84;

}
