package com.neighborlib.common;

/**
 * <pre>
 *  1. 기능 : 공통 태그변수 관리 클래스
 *  2. 처리 개요 :  
 *     - 공통 태그변수 관리 클래스
 *  3. 주의사항 : 
 *  4. 작성자/작성일 : 최형길 / 2013. 7. 8.
 * ===================================
 *  5. 수정사항
 *  5.1 요구사항 ID :
 *     - 수정자/수정일 : 이동식 / 2015. 11. 24
 *     - 수정사유/내역 : 신규 API 파라매터 추가
 * ===================================
 * </pre>
 * 
 * @author : 최형길
 * @version : v1.0.0
 * @see : kr.co.lwt.sts.app.parser 패키지 참조
 * @since : J2SE 6.0
 **/
public class ParameterDef {

	public static final String COMMON_PARAMETER_ID = "id";
	public static final String COMMON_PARAMETER_TRID = "trId";
	public static final String COMMON_PARAMETER_ERRCD = "errCd";
	public static final String COMMON_PARAMETER_ERRMSG = "errMsg";
	public static final String COMMON_PARAMETER_RESULT = "result";

	public static final String VALUE_IF_CLFS_NEWLIST = "NEWLIST";
	public static final String VALUE_IF_CLFS_BRANDLIST = "BRANDLIST";
	public static final String VALUE_IF_CLFS_EVENT = "EVENT";
	public static final String VALUE_IF_CLFS_COUP = "COUP";
	public static final String VALUE_IF_CLFS_SHOW = "SHOW";
	public static final String VALUE_IF_CLFS_NEW = "NEW";
	public static final String VALUE_IF_CLFS_RECMND = "RECMND";
	public static final String VALUE_IF_CLFS_FAC = "FAC";
	public static final String VALUE_IF_CLFS_CHKIN = "CHKIN";
	public static final String VALUE_IF_CLFS_NOTI = "NOTI";
	public static final String VALUE_IF_CLFS_PKRES = "PKRES";
	public static final String VALUE_IF_CLFS_FAQ = "FAQ";
	public static final String VALUE_IF_CLFS_LAW = "LAW";
	public static final String VALUE_IF_CLFS_LAW2 = "LAW2"; //20150130 added by soogi
	public static final String VALUE_IF_CLFS_PRIVE = "PRIVE";
	public static final String VALUE_TOTSRCH_FLAG_UNIFY = "T";
	public static final String VALUE_TOTSRCH_FLAG_ROUTE = "A";
	public static final String VALUE_YN_FLAG_Y = "Y";
	public static final String VALUE_YN_FLAG_N = "N";
	//2015.01.30 새로운 약관 동의를 위해 추가 son
	public static final String VALUE_YN_FLAG_YES = "YES";
	public static final String VALUE_YN_FLAG_NO = "NO";
	public static final String VALUE_INTEREST_CLFS_FAC = "FAC";
	public static final String VALUE_INTEREST_CLFS_CTGR = "CTGR";
	public static final String VALUE_RECMND_Y = "Y";
	public static final String VALUE_ENC = "ENC";
	public static final String VALUE_DEC = "DEC";
	public static final String VALUE_DEVICE_CD_ADR = "ADR";
	public static final String VALUE_API_OPTION_MOD = "MOD";
	public static final String VALUE_FLOOR_SUBSIDIARY_BUILDCD_ALL = "ALL";
	public static final String VALUE_NO_TIME_LIMIT = "9999-12-31";

	public static final String VALUE_USER_GUIDE_TYPE_GROUP = "group";
	public static final String VALUE_USER_GUIDE_TYPE_MAIN = "main";

	public static final String VALUE_APP_MENU_ID_MAIN_MAIN = "main_main";

	public static final String VALUE_LOC_NUM_TYPE_NONE = "N";
	public static final String VALUE_LOC_NUM_TYPE_ONE = "O";
	public static final String VALUE_LOC_NUM_TYPE_MULTI = "M";

	public static final String VALUE_MAIN_ARTSHOW_FLAG = "A";
	public static final String VALUE_EVENT_FORM_HTML = "html";
	public static final String VALUE_EVENT_FORM_IMG = "img";

	public static final String VALUE_VERSION = "2";
	public static final String VALUE_MENU_VERSION = "V2";
	public static final String VALUE_MENU_VERSION_TOWER = "V3";

	public static final String PARAMETER_CHECKIN_IMGS = "CHECKIN_IMGS";
	public static final String PARAMETER_SCORE = "SCORE";

	public static final String PARAMETER_ESTIMATED_TIME = "ESTIMATED_TIME";
	public static final String PARAMETER_TOT_DISTANCE = "TOT_DISTANCE";
	public static final String PARAMETER_ROUTE_LIST = "ROUTE_LIST";
	public static final String PARAMETER_ROUTE_INFO = "ROUTE_INFO";
	public static final String PARAMETER_COORD_CNT = "COORD_CNT";
	public static final String PARAMETER_COORD_LIST = "COORD_LIST";
	public static final String PARAMETER_COORD_INFO = "COORD_INFO";
	public static final String PARAMETER_COORD_IDX = "COORD_IDX";
	public static final String PARAMETER_X_COORD = "X_COORD"; //삭제 예정
	public static final String PARAMETER_Y_COORD = "Y_COORD"; //삭제 예정
	public static final String PARAMETER_COORD = "COORD";
	public static final String PARAMETER_FL_IF = "FL_IF";
	public static final String PARAMETER_JUNCTION_LIST = "JUNCTION_LIST";
	public static final String PARAMETER_JUNCTION_INFO = "JUNCTION_INFO";
	public static final String PARAMETER_JUNCTION_CNT = "JUNCTION_CNT";
	public static final String PARAMETER_DIRECTION = "DIRECTION";
	public static final String PARAMETER_SUMUP_LIST = "SUMUP_LIST";
	public static final String PARAMETER_OFFICE_HOURS = "OFFICE_HOURS";
	public static final String PARAMETER_CONTENT_IDX = "CONTENT_IDX";
	public static final String PARAMETER_CONTENT = "CONTENT";
	public static final String PARAMETER_CONTENT_COORD = "CONTENT_COORD";
	public static final String PARAMETER_VR_POINT_CNT = "VR_POINT_CNT";
	public static final String PARAMETER_VR_INFO_LIST = "VR_INFO_LIST";
	public static final String PARAMETER_VR_INFO = "VR_INFO";
	public static final String PARAMETER_VR_POT_IF_NO = "VR_POT_IF_NO";
	public static final String PARAMETER_VR_POT_COORD = "VR_POT_COORD";
	public static final String PARAMETER_VR_IMAGE_URL = "VR_IMAGE_URL";
	public static final String PARAMETER_VR_POT_NM = "VR_POT_NM";
	public static final String PARAMETER_FAC_LIST = "FAC_LIST";
	public static final String PARAMETER_FAC_INFO = "FAC_INFO";
	public static final String PARAMETER_FAC_IF_NO = "FAC_IF_NO";
	public static final String PARAMETER_FAC_NM = "FAC_NM";
	public static final String PARAMETER_CTGR_CD_LIST = "CTGR_CD_LIST";
	public static final String PARAMETER_CTGR_CD = "CTGR_CD";
	public static final String PARAMETER_BLD_NM = "BLD_NM";
	public static final String PARAMETER_BLD_CD = "BLD_CD";
	public static final String PARAMETER_SUBSID_NM = "SUBSID_NM";
	public static final String PARAMETER_SUBSID_NM_ENG = "SUBSID_NM_ENG";
	public static final String PARAMETER_AVR_SCORE = "AVR_SCORE";
	public static final String PARAMETER_SCORE_CNT = "SCORE_CNT";
	public static final String PARAMETER_RECMND_YN = "RECMND_YN";
	public static final String PARAMETER_EVTCP_LIST = "EVTCP_LIST";
	public static final String PARAMETER_EVTCP_INFO = "EVTCP_INFO";
	public static final String PARAMETER_EVTCP_IF_NO = "EVTCP_IF_NO";
	public static final String PARAMETER_EVTCP_IF_CLSF = "EVTCP_IF_CLSF";
	public static final String PARAMETER_FAC_COORD = "FAC_COORD";
	public static final String PARAMETER_FAC_OFFICE_HOUSE = "OFFICE_HOUSE";
	public static final String PARAMETER_FAC_TEL_NO = "FAC_TEL_NO";
	public static final String PARAMETER_FAC_DES = "FAC_DES";
	public static final String PARAMETER_FAC_MAIN_MANNM = "FAC_MAIN_MANNM";
	public static final String PARAMETER_FAC_IMG_LIST = "FAC_IMG_LIST";
	public static final String PARAMETER_FAC_IMG_URL = "FAC_IMG_URL";
	public static final String PARAMETER_FAC_IMAGE_LIST = "FAC_IMAGE_LIST";
	public static final String PARAMETER_FAC_IMAGE_URL = "FAC_IMAGE_URL";
	public static final String PARAMETER_FAC_TEMP_IMAGE_LIST = "FAC_TEMP_IMAGE_LIST";
	public static final String PARAMETER_FAC_TEMP_IMAGE_URL = "FAC_TEMP_IMAGE_URL";
	public static final String PARAMETER_REMOVE_TEMP_IMG_LIST = "REMOVE_TEMP_IMG_LIST";
	public static final String PARAMETER_LOGO_IMG = "LOGO_IMG";
	public static final String PARAMETER_URL_PATH = "URL_PATH";
	public static final String PARAMETER_INSTRST_YN = "INTRST_YN";
	public static final String PARAMETER_MY_SCORE = "MY_SCORE";
	public static final String PARAMETER_UPDATE_LOCK = "UPDATE_LOCK";
	public static final String PARAMETER_CTNT = "CTNT";
	public static final String PARAMETER_LSC = "LSC";
	public static final String PARAMETER_MSC = "MSC";
	public static final String PARAMETER_SSC = "SSC";
	public static final String PARAMETER_SUBSID_NO = "SUBSID_NO";
	public static final String PARAMETER_PPS_SUBSID_NO = "PPS_SUBSID_NO";
	public static final String PARAMETER_SRVC_PRV_YN = "SRVC_PRV_YN";
	public static final String PARAMETER_EVTCPIF_LIST = "EVTCPIF_LIST";
	public static final String PARAMETER_EVTCPIF_INFO = "EVTCPIF_INFO";
	public static final String PARAMETER_IF_NO = "IF_NO";
	public static final String PARAMETER_IF_CLSF = "IF_CLSF";
	public static final String PARAMETER_IMG_PATH = "IMG_PATH";
	public static final String PARAMETER_IF_NM = "IF_NM";
	public static final String PARAMETER_START_TIME = "START_TIME";
	public static final String PARAMETER_END_TIME = "END_TIME";
	public static final String PARAMETER_SUB_IMG_URL = "SUB_IMG_URL";
	public static final String PARAMETER_APP_INST_PATH = "APP_INST_PATH";
	public static final String PARAMETER_APP_EXEC_PATH = "APP_EXEC_PATH";
	public static final String PARAMETER_DES = "DES";
	public static final String PARAMETER_CPON_AVLSHP = "CPON_AVLSHP";
	public static final String PARAMETER_MOVIE_LIST = "MOVIE_LIST";
	public static final String PARAMETER_MOVIE_INFO = "MOVIE_INFO";
	public static final String PARAMETER_NAME = "NAME";
	public static final String PARAMETER_SYNOPSIS = "SYNOPSIS";
	public static final String PARAMETER_POSTER_URL = "POSTER_URL";
	public static final String PARAMETER_SCREEN_LIST = "SCREEN_LIST";
	public static final String PARAMETER_SCREEN_INFO = "SCREEN_INFO";
	public static final String PARAMETER_DATE = "DATE";
	public static final String PARAMETER_THEATER_LIST = "THEATER_LIST";
	public static final String PARAMETER_THEATER_INFO = "THEATER_INFO";
	public static final String PARAMETER_SCREEN_NM = "SCREEN_NM";
	public static final String PARAMETER_SCREEN_IDX = "SCREEN_IDX";
	public static final String PARAMETER_SCREEN_TM = "SCREEN_TM";
	public static final String PARAMETER_SCREEN_DAY = "SCREEN_DAY";
	public static final String PARAMETER_TOT_SEAT = "TOT_SEAT";
	public static final String PARAMETER_FREE_SEAT = "FREE_SEAT";
	public static final String PARAMETER_VEHICLE_LIST = "VEHICLE_LIST";
	public static final String PARAMETER_VEHICLE_INFO = "VEHICLE_INFO";
	public static final String PARAMETER_VEHICLE_NO = "VEHICLE_NO";
	public static final String PARAMETER_VEHICLE_NO_IMG_URL = "VEHICLE_NO_IMG_URL";
	public static final String PARAMETER_AREA_NO = "AREA_NO";
	public static final String PARAMETER_CAR_IN_TM = "CAR_IN_TM";
	public static final String PARAMETER_VEHICLE_IN_TM = "VEHICLE_IN_TM";
	public static final String PARAMETER_CAR_OUT_TM = "CAR_OUT_TM";
	public static final String PARAMETER_PARKING_FEE = "PARKING_FEE";
	public static final String PARAMETER_DISCOUNT_INFO = "DISCOUNT_INFO";
	public static final String PARAMETER_PRK_STATUS_LIST = "PRK_STATUS_LIST";
	public static final String PARAMETER_PRK_STATUS_INFO = "PRK_STATUS_INFO";
	public static final String PARAMETER_PRK_GUIDE_LIST = "PRK_GUIDE_LIST";
	public static final String PARAMETER_PRK_FL_CLSF = "PRK_FL_CLSF";
	public static final String PARAMETER_DISCOUNT_TYPE = "DISCOUNT_TYPE";
	public static final String PARAMETER_STATUS = "STATUS";

	public static final String PARAMETER_PRK_AVAIL_CNT = "PRK_AVAIL_CNT";
	public static final String PARAMETER_MAX_PRK_CNT = "MAX_PRK_CNT";
	public static final String PARAMETER_ZONE_TYPE = "ZONE_TYPE";

	public static final String PARAMETER_PRK_DISTRICT_CLSF = "PRK_DISTRICT_CLSF";
	public static final String PARAMETER_FL_CNGES = "FL_CNGES";
	public static final String PARAMETER_ZONE_LIST = "ZONE_LIST";
	public static final String PARAMETER_ZONE_INFO = "ZONE_INFO";
	public static final String PARAMETER_ZONE_NM = "ZONE_NM";
	public static final String PARAMETER_ZONE_POLYGON = "ZONE_POLYGON";
	public static final String PARAMETER_ZONE_CNGES = "ZONE_CNGES";
	public static final String PARAMETER_GATE_NO = "GATE_NO";
	public static final String PARAMETER_GATE_NM = "GATE_NM";
	public static final String PARAMETER_AROUND_INFO_LIST = "AROUND_INFO_LIST";
	public static final String PARAMETER_AROUND_INFO = "AROUND_INFO";
	public static final String PARAMETER_ROAD_NM = "ROAD_NM";
	public static final String PARAMETER_FORWORD_GUIDE = "FORWORD_GUIDE";
	public static final String PARAMETER_START_ND_ID = "START_ND_ID";
	public static final String PARAMETER_END_ND_ID = "END_ND_ID";
	public static final String PARAMETER_AVG_SPD = "AVG_SPD";
	public static final String PARAMETER_TRAVL_TM = "TRAVL_TM";
	public static final String PARAMETER_REG_DTM = "REG_DTM";
	public static final String PARAMETER_ACCIF_LIST = "ACCIF_LIST";
	public static final String PARAMETER_ACC_INFO = "ACC_INFO";
	public static final String PARAMETER_ACC_COORD = "ACC_COORD";
	public static final String PARAMETER_ACCDNT_IF_CLSF_CD = "ACCDNT_IF_CLSF_CD";
	public static final String PARAMETER_MSG = "MSG";
	public static final String PARAMETER_NM = "NM";
	public static final String PARAMETER_NICNM = "NICNM";
	public static final String PARAMETER_BIRTH = "BIRTH";
	public static final String PARAMETER_SEX = "SEX";
	public static final String PARAMETER_DEV_TEL = "DEV_TEL";
	public static final String PARAMETER_DEV_UNIQ_NO = "DEV_UNIQ_NO";
	public static final String PARAMETER_MEMBER_CLSF_CD = "MEMBER_CLSF_CD";
	public static final String PARAMETER_MEMBER_DETAIL_CD = "MEMBER_DETAIL_CD";
	public static final String PARAMETER_LIP_YN = "LIP_YN";
	public static final String PARAMETER_PIP_YN = "PIP_YN";
	public static final String PARAMETER_NOTI_RECV_YN = "NOTI_RECV_YN";
	public static final String PARAMETER_MIP_YN = "MIP_YN";
	public static final String PARAMETER_DEV_CLSF = "DEV_CLSF";
	public static final String PARAMETER_PUSH_NOTI_ID = "PUSH_NOTI_ID";
	public static final String PARAMETER_GUEST_ID = "GUEST_ID";
	public static final String PARAMETER_APP_USE_YN = "APP_USE_YN";
	public static final String PARAMETER_MEMBER_IMG_PATH = "MEMBER_IMG_PATH";
	public static final String PARAMETER_MY_CTGR_LIST = "MY_CTGR_LIST";
	public static final String PARAMETER_CTGR_INFO = "CTGR_INFO";
	public static final String PARAMETER_MY_FAC_LIST = "MY_FAC_LIST";
	public static final String PARAMETER_MY_EVTCP_LIST = "MY_EVTCP_LIST";
	public static final String PARAMETER_MY_EVTCP_INFO = "EVTCP_INFO";
	public static final String PARAMETER_MY_MOVIE_LIST = "MY_MOVIE_LIST";
	public static final String PARAMETER_APP_USER_INFO = "APP_USER_INFO";
	public static final String PARAMETER_REPLY_CNT = "REPLY_CNT";
	public static final String PARAMETER_PROFILE_URL = "PROFILE_URL";
	public static final String PARAMETER_CHECKIN_TOTALCNT = "CHECKIN_TOTALCNT";
	public static final String PARAMETER_CHECKIN_LIST = "CHECKIN_LIST";
	public static final String PARAMETER_CHECKIN_IF_NO = "CHECKIN_IF_NO";
	public static final String PARAMETER_IMG_URL = "IMG_URL";
	public static final String PARAMETER_SHR_CHECKIN_YN = "SHR_CHECKIN_YN";
	public static final String PARAMETER_REPLY_LIST = "REPLY_LIST";
	public static final String PARAMETER_REPLY_INFO = "REPLY_INFO";
	public static final String PARAMETER_REPLY_SEQ = "REPLY_SEQ";
	public static final String PARAMETER_REPLY_WRITER_ID = "REPLY_WRITER_ID";
	public static final String PARAMETER_REPLY = "REPLY";
	public static final String PARAMETER_NOTICE_LIST = "NOTICE_LIST";
	public static final String PARAMETER_NOTICE_NO = "NOTICE_NO";
	public static final String PARAMETER_NOTICE_CLSF_CD = "NOTICE_CLSF_CD";
	public static final String PARAMETER_TITLE = "TITLE";
	public static final String PARAMETER_HITS = "HITS";
	public static final String PARAMETER_HELP_LIST = "HELP_LIST";
	public static final String PARAMETER_HELP_INFO = "HELP_INFO";
	public static final String PARAMETER_HELP_NO = "HELP_NO";
	public static final String PARAMETER_HELP_DIV = "HELP_DIV";
	public static final String PARAMETER_URL_INFO = "URL_INFO";
	public static final String PARAMETER_GUIDE_URL = "GUIDE_URL";
	public static final String PARAMETER_GUIDE_IMG_URL = "GUIDE_IMG_URL";
	public static final String PARAMETER_FAQ_LIST = "FAQ_LIST";
	public static final String PARAMETER_FAQ_SEQ = "FAQ_SEQ";
	public static final String PARAMETER_QUESTION = "QUESTION";
	public static final String PARAMETER_IMG_PATH_URL = "IMG_PATH_URL";
	public static final String PARAMETER_ANSWER = "ANSWER";
	public static final String PARAMETER_CENTER_TEL = "CENTER_TEL";
	public static final String PARAMETER_CENTER_EMAIL = "CENTER_EMAIL";
	public static final String PARAMETER_SHR_KEY = "SHR_KEY";
	public static final String PARAMETER_SHRKEY = "SHRKEY";
	public static final String PARAMETER_SHR_KEY_YN = "SHR_KEY_YN";
	public static final String PARAMETER_MENU_LIST = "MENU_LIST";
	public static final String PARAMETER_MENU_INFO = "MENU_INFO";
	public static final String PARAMETER_MENU_ID = "MENU_ID";
	public static final String PARAMETER_MENU_NAME = "MENU_NAME";
	public static final String PARAMETER_MANDATORY_YN = "MANDATORY_YN";
	public static final String PARAMETER_HIGH_MENUID = "HIGH_MENUID";
	public static final String PARAMETER_MENU_LEVEL = "MENU_LEVEL";
	public static final String PARAMETER_HIDDEN = "HIDDEN";
	public static final String PARAMETER_COMMON_CD_LIST = "COMMON_CD_LIST";
	public static final String PARAMETER_CD_LIST = "CD_LIST";
	public static final String PARAMETER_CD_INFO_LIST = "CD_INFO_LIST";
	public static final String PARAMETER_CD_CLSF_ID = "CD_CLSF_ID";
	public static final String PARAMETER_CD_ID = "CD_ID";
	public static final String PARAMETER_CD_CLSF_NM = "CD_CLSF_NM";
	public static final String PARAMETER_CD_NM = "CD_NM";
	public static final String PARAMETER_CD_DES = "CD_DES";
	public static final String PARAMETER_RESULT = "RESULT";
	public static final String PARAMETER_JJIM_YN = "JJIM_YN";
	public static final String PARAMETER_VER = "VER";
	public static final String PARAMETER_GENERAL_DATA = "GENERAL_DATA";
	public static final String PARAMETER_WELCOME_TEXT = "WELCOME_TEXT";
	public static final String PARAMETER_WELCOME_VOICE_URL = "WELCOME_VOICE_URL";
	public static final String PARAMETER_MAP_DATA = "MAP_DATA";
	public static final String PARAMETER_RASTER_MAP_URL = "RASTER_MAP_URL";
	public static final String PARAMETER_VECTOR_MAP_URL = "VECTOR_MAP_URL";
	public static final String PARAMETER_LBS_DATA = "LBS_DATA";
	public static final String PARAMETER_LBS_APDB_URL = "LBS_APDB_URL";
	public static final String PARAMETER_LBS_NETWORK_URL = "LBS_NETWORK_URL";

	public static final String PARAMETER_MEMBER_ID = "MEMBER_ID";
	public static final String PARAMETER_SRT_IDX = "SRT_IDX";
	public static final String PARAMETER_END_IDX = "END_IDX";
	public static final String PARAMETER_INDEX_NO = "INDEX_NO";
	public static final String PARAMETER_CHECKIN_DETAIL_LIST = "CHECKIN_DETAIL_LIST";

	public static final String PARAMETER_DEVICE_CD = "DEVICE_CD";
	public static final String PARAMETER_RPT_CONTENT = "RPT_CONTENT";
	public static final String PARAMETER_INFO_TIT = "INFO_TIT";
	public static final String PARAMETER_OCC_DTIME = "OCC_DTIME";
	public static final String PARAMETER_END_DTIME = "END_DTIME";
	public static final String PARAMETER_REG_DTIME = "REG_DTIME";

	public static final String PARAMETER_FRIEND_LIST = "FRIEND_LIST";
	public static final String PARAMETER_NUMBERS = "NUMBERS";
	public static final String PARAMETER_PHONE_NUM = "PHONE_NUM";
	public static final String PARAMETER_ALIAS = "ALIAS";

	public static final String PARAMETER_MOVI_S_TM = "MOVI_S_TM";
	public static final String PARAMETER_MOVI_E_TM = "MOVI_E_TM";

	public static final String PARAMETER_CATEGORY_LIST = "CATEGORY_LIST";
	public static final String PARAMETER_KEYTENANT_LIST = "KEYTENANT_LIST";
	public static final String PARAMETER_TOB_CLSF = "TOB_CLSF";
	public static final String PARAMETER_CPON_COST = "CPON_COST";
	public static final String PARAMETER_PAYMENT_DTM = "PAYMENT_DTM";
	public static final String PARAMETER_PAYMENT_COST = "PAYMENT_COST";
	public static final String PARAMETER_CHARGE_COST = "CHARGE_COST";
	public static final String PARAMETER_DISCOUNT_COST = "DISCOUNT_COST";
	public static final String PARAMETER_PAYMENT_METHOD_CD = "PAYMENT_METHOD_CD";
	public static final String PARAMETER_BREAKDOWN_LIST = "BREAKDOWN_LIST";
	public static final String PARAMETER_SEQ = "SEQ";
	public static final String PARAMETER_DETAIL_MENU_LIST = "DETAIL_MENU_LIST";
	public static final String PARAMETER_APPMENU_LIST = "APPMENU_LIST";
	public static final String PARAMETER_APP_VER = "APP_VER";

	public static final String PARAMETER_API_OPTION = "API_OPTION";

	public static final String PARAMETER_SUB_IMG_ID = "SUB_IMG_ID";
	public static final String PARAMETER_SUB_IMG = "SUB_IMG";

	public static final String PARAMETER_BLOCK_NUMBER_LIST = "BLOCK_NUMBER_LIST";
	public static final String PARAMETER_BLOCK_NUMBER = "BLOCK_NUMBER";

	public static final String PARAMETER_EVENT_YN = "EVENT_YN";
	public static final String PARAMETER_SHOW_YN = "SHOW_YN";
	public static final String PARAMETER_COUP_YN = "COUP_YN";
	public static final String PARAMETER_PARKING_TM = "PARKING_TM";
	public static final String PARAMETER_IMG_W = "IMG_W";
	public static final String PARAMETER_IMG_H = "IMG_H";

	public static final String PARAMETER_TOTAL_CNT = "TOTAL_CNT";

	public static final String PARAMETER_SHR_MEMBER_TEL = "SHR_MEMBER_TEL";
	public static final String PARAMETER_MSG_TYPE = "MSG_TYPE";

	public static final String PARAMETER_JSON_BODY = "JSON_BODY";
	public static final String PARAMETER_RG_DATA_URL = "rgDataUrl";

	public static final String PARAMETER_ADMIN_YN = "ADMIN_YN";

	public static final String PARAMETER_TOTSRCH_FLAG = "TOTSRCH_FLAG";
	public static final String PARAMETER_VR_POT_DES = "VR_POT_DES";

	public static final String PARAMETER_VEHICLE_COORD = "VEHICLE_COORD";
	public static final String PARAMETER_DISCOUNT_TM = "DISCOUNT_TM";

	public static final String PARAMETER_PERSONAL_ID = "PERSONAL_ID";

	public static final String PARAMETER_LONG_URL = "LONG_URL";
	public static final String PARAMETER_SHORT_URL = "SHORT_URL";

	public static final String PARAMETER_VR_LIST = "VR_LIST";
	public static final String PARAMETER_MAIN_IMG_URL = "MAIN_IMG_URL";

	public static final String PARAMETER_SEED_TYPE = "SEED_TYPE";
	public static final String PARAMETER_TEXT = "TEXT";
	public static final String PARAMETER_CONVERT_STRING = "CONVERT_STRING";

	public static final String PARAMETER_MEM_IMG = "MEM_IMG";

	public static final String PARAMETER_VR_URL = "VR_URL";
	public static final String PARAMETER_VR_COUNT = "VR_COUNT";
	public static final String PARAMETER_VR_URL_MULTI_LIST = "VR_URL_MULTI_LIST";

	public static final String PARAMETER_INFORMATION_URL = "INFORMATION_URL";
	public static final String PARAMETER_POI_ID = "POI_ID";
	public static final String PARAMETER_TYPE = "TYPE";

	public static final String PARAMETER_FLOOR_CNT = "FLOOR_CNT";
	public static final String PARAMETER_FLOOR_LIST = "FLOOR_LIST";
	public static final String PARAMETER_FL_CD = "FL_CD";
	public static final String PARAMETER_FL_NM = "FL_NM";



	public static final String PARAMETER_EMAIL = "EMAIL";
	public static final String PARAMETER_CI_HASH = "CI_HASH";
	public static final String PARAMETER_NATION_FG = "NATION_FG";
	public static final String PARAMETER_CUST_ID = "CUST_ID";

	public static final String PARAMETER_MAC_ADDRESS = "MAC_ADDRESS";

	public static final String PARAMETER_FAC_NOTI_RECV_YN = "FAC_NOTI_RECV_YN";
	public static final String PARAMETER_CTGR_NOTI_RECV_YN = "CTGR_NOTI_RECV_YN";

	/** 롯데패밀리 사이트 관련 파라매터 및 값 */
	public static final String LOTTE_URL_PARAMETER_SID = "sid";
	public static final String LOTTE_URL_PARAMETER_LOGINID = "loginid";
	public static final String LOTTE_URL_PARAMETER_PASSWORD = "password";
	public static final String LOTTE_URL_PARAMETER_SCH = "sch";
	public static final String LOTTE_URL_PARAMETER_OPEN_TYPE = "opentype";
	public static final String LOTTE_URL_PARAMETER_RETURN_URL = "returnurl";
	public static final String LOTTE_URL_PARAMETER_CUST_ID = "custid";
	public static final String LOTTE_PARAMETER_RESPONSE_CODE = "RESPONSE_CODE";
	public static final String LOTTE_PARAMETER_ERR_RESULT_CODE = "ERR_RESULT_CODE";
	public static final String LOTTE_PARAMETER_ONL_CNO = "ONL_CNO";
	public static final String LOTTE_PARAMETER_ONL_ID = "ONL_ID";
	public static final String LOTTE_PARAMETER_CST_NM = "CST_NM";
	public static final String LOTTE_PARAMETER_ONL_CST_DC = "ONL_CST_DC";
	public static final String LOTTE_PARAMETER_MA_FEM_DV_C = "MA_FEM_DV_C";
	public static final String LOTTE_PARAMETER_FRN_YN = "FRN_YN";
	public static final String LOTTE_PARAMETER_ELC_ADD = "ELC_ADD";
	public static final String LOTTE_PARAMETER_TL_NO = "TL_NO";
	public static final String LOTTE_PARAMETER_MBL_NO = "MBL_NO";
	public static final String LOTTE_PARAMETER_FRN_TL_NO = "FRN_TL_NO";
	public static final String LOTTE_PARAMETER_FRN_MBL_NO = "FRN_MBL_NO";
	public static final String LOTTE_PARAMETER_BIRD = "BIRD";
	public static final String LOTTE_PARAMETER_CI_NO = "CI_NO";
	public static final String LOTTE_PARAMETER_CNO = "CNO";
	public static final String LOTTE_PARAMETER_CNO_CH_DTTI = "CNO_CH_DTTI";
	public static final String LOTTE_PARAMETER_BF_ONL_ID = "BF_ONL_ID";
	public static final String LOTTE_PARAMETER_BF_ONL_ID_CH_DTTI = "BF_ONL_ID_CH_DTTI";
	public static final String LOTTE_PARAMETER_USERNAME = "USERNAME";
	public static final String LOTTE_PARAMETER_BIRTH_DAY = "BIRTH_DAY";
	public static final String LOTTE_PARAMETER_INF_CLNG_YN = "INF_CLNG_YN";
	public static final String LOTTE_VALUE_SCH = "myapp://kr.co.LWTSmartService";
//	public static final String LOTTE_VALUE_SCH = "kr.co.lwt.sts";
	public static final String LOTTE_VALUE_SID = "MLWT";
	public static final String LOTTE_VALUE_OPEN_TYPE = "P";
	public static final String LOTTE_VALUE_RETURN_URL = "";
//	public static final String LOTTE_VALUE_RETURN_URL = "myapp://kr.co.LWTSmartService";

	/** 위치측위 관련 파라매터 및 값 */
	public static final String LBS_PARAMETER_DEVICEID = "deviceId";
	public static final String LBS_PARAMETER_MODE = "mode";
	public static final String LBS_PARAMETER_LANG = "lang";
	public static final String LBS_PARAMETER_RESOLUTION = "resolution";
	public static final String LBS_PARAMETER_BUILDINGID = "buildingId";
	public static final String LBS_PARAMETER_X = "x";
	public static final String LBS_PARAMETER_Y = "y";
	public static final String LBS_PARAMETER_FLOOR = "floor";
	public static final String LBS_PARAMETER_SPOSINFO = "sposInfo";
	public static final String LBS_PARAMETER_EPOSINFOLIST = "eposInfolist";
	public static final String LBS_PARAMETER_DEV_IP = "dev_ip";
	public static final String LBS_PARAMETER_AP_ID = "ap_id";

	public static final String LBS_VALUE_ROUTE_FILE_NAME = "route_guide.dat";

	public static final String PARAMETER_APP_GUIDE_LIST = "APP_GUIDE_LIST";
	public static final String PARAMETER_HELP_URL = "HELP_URL";
	public static final String PARAMETER_APP_MENU_ID = "APP_MENU_ID";
	public static final String PARAMETER_SHOPPINGNEWS_CNT = "SHOPPINGNEWS_CNT";
	public static final String PARAMETER_SHOPPINGNEWS_LIST = "SHOPPINGNEWS_LIST";
	public static final String PARAMETER_EVT_TERM = "EVT_TERM";
	public static final String PARAMETER_EVENT_FORM = "EVENT_FORM";
	public static final String PARAMETER_WEBVIEW_URL = "WEBVIEW_URL";
	public static final String PARAMETER_LOC_NUM_TYPE = "LOC_NUM_TYPE";
	public static final String PARAMETER_FAC_COORD_CNT = "FAC_COORD_CNT";
	public static final String PARAMETER_FAC_COORD_LIST = "FAC_COORD_LIST";
	public static final String PARAMETER_LINK_YN = "LINK_YN";
	public static final String PARAMETER_LOC_NUM = "LOC_NUM";
	public static final String PARAMETER_SHOPPINGLOC_LIST = "SHOPPINGLOC_LIST";
	public static final String PARAMETER_CULTURENEWS_CNT = "CULTURENEWS_CNT";
	public static final String PARAMETER_CULTURENEWS_LIST = "CULTURENEWS_LIST";
	public static final String PARAMETER_SHOPPINGNEWSITRST_CNT = "SHOPPINGNEWSITRST_CNT";
	public static final String PARAMETER_SHOPPINGNEWSITRST_LIST = "SHOPPINGNEWSITRST_LIST";
	public static final String PARAMETER_CULTURENEWSITRST_CNT = "CULTURENEWSITRST_CNT";
	public static final String PARAMETER_CULTURENEWSITRST_LIST = "CULTURENEWSITRST_LIST";
	public static final String PARAMETER_CINEMAINFO_LIST = "CINEMAINFO_LIST";
	public static final String PARAMETER_CINEMA_IF_NO = "CINEMA_IF_NO";
	public static final String PARAMETER_CINEMA_NM = "CINEMA_NM";
	public static final String PARAMETER_CINEMA_COORD = "CINEMA_COORD";
	public static final String PARAMETER_CINEMA_FL_IF = "CINEMA_FL_IF";
	public static final String PARAMETER_FAC_DISPLAY_NM = "FAC_DISPLAY_NM";
	public static final String PARAMETER_MOVIE_SCREEN = "MOVIE_SCREEN";
	public static final String PARAMETER_MOVIE_TIME = "MOVIE_TIME";
	public static final String PARAMETER_REL_IF_COUNT = "REL_IF_COUNT";
	public static final String PARAMETER_REL_IF_LIST = "REL_IF_LIST";
	public static final String PARAMETER_REL_IF_NO = "REL_IF_NO";
	public static final String PARAMETER_REL_IF_NM = "REL_IF_NM";
	public static final String PARAMETER_REL_CLSF = "REL_CLSF";
	public static final String PARAMETER_PROMOTION_YN = "PROMOTION_YN";
	public static final String PARAMETER_RECMNDEVENT_CNT = "RECMNDEVENT_CNT";
	public static final String PARAMETER_RECMNDEVENT_LIST = "RECMNDEVENT_LIST";
	public static final String PARAMETER_VERSION = "VERSION";

	public static final String PARAMETER_SUB_CD_CLSF_ID = "SUB_CD_CLSF_ID";
	public static final String PARAMETER_FAC_CNT = "FAC_CNT";
	public static final String PARAMETER_CTGR_NM = "CTGR_NM";
	public static final String PARAMETER_FAC_KIND_NO = "FAC_KIND_NO";
	public static final String PARAMETER_FAC_THEME_NO = "FAC_THEME_NO";
	public static final String PARAMETER_FAC_MENU = "FAC_MENU";
	public static final String PARAMETER_DEVICE_COORD = "DEVICE_COORD";

	public static final String PARAMETER_EVT_CNT = "EVT_CNT";
	public static final String PARAMETER_EVT_LIST = "EVT_LIST";
	public static final String PARAMETER_EVT_NO = "EVT_NO";
	public static final String PARAMETER_EVT_NM = "EVT_NM";
	public static final String PARAMETER_POPUP_YN = "POPUP_YN";

	public static final String PARAMETER_MAINIMAGE_CNT = "MAINIMAGE_CNT";
	public static final String PARAMETER_MAINIMAGE_LIST = "MAINIMAGE_LIST";
	public static final String PARAMETER_APP_IMG_URL = "APP_IMG_URL";
	public static final String PARAMETER_NOTI_URL = "NOTI_URL";
	public static final String PARAMETER_EVENT_URL = "EVENT_URL";

	public static final String PARAMETER_FACITRST_CNT = "FACITRST_CNT";
	public static final String PARAMETER_FACITRST_LIST = "FACITRST_LIST";
	public static final String PARAMETER_WEBVIEW_YN = "WEBVIEW_YN";
	
	public static final String PARAMETER_VERSION_NO = "VERSION_NO";
	
	public static final String PARAMETER_FL_CNT = "FL_CNT";
	public static final String PARAMETER_FL_LIST = "FL_LIST";
	public static final String PARAMETER_TARGET_FL_IF = "TARGET_FL_IF";
	public static final String PARAMETER_TARGET_COORD = "TARGET_COORD";
	
	public static final String PARAMETER_BG_IMG_URL = "BG_IMG_URL";
	public static final String PARAMETER_PLAY_URL = "PLAY_URL";
	public static final String PARAMETER_SHOW_TIME = "SHOW_TIME";
	public static final String PARAMETER_PLAY_URL_00 = "PLAY_URL_00";
	public static final String PARAMETER_PLAY_URL_20 = "PLAY_URL_20";
	public static final String PARAMETER_PLAY_URL_40 = "PLAY_URL_40";
	public static final String PARAMETER_READY_BG_IMG_URL = "READY_BG_IMG_URL";
	public static final String PARAMETER_BASIC_BG_IMG_URL = "BASIC_BG_IMG_URL";
	public static final String PARAMETER_SHOW_00_BG_IMG_URL = "SHOW_00_BG_IMG_URL";
	public static final String PARAMETER_SHOW_20_BG_IMG_URL = "SHOW_20_BG_IMG_URL";
	public static final String PARAMETER_SHOW_40_BG_IMG_URL = "SHOW_40_BG_IMG_URL";
	public static final String PARAMETER_HMP_VIEW_YN = "HMP_VIEW_YN";
	
	public static final String PARAMETER_INTEGRITY_YN = "INTEGRITY_YN";
	public static final String PARAMETER_SIGNING_KEY = "SIGNING_KEY";
	
	public static final String PARAMETER_EXHBT_DATE_LIST = "EXHBT_DATE_LIST";
	public static final String PARAMETER_SRC_DATE = "SRC_DATE";
	public static final String PARAMETER_EXHBT_CNT = "EXHBT_CNT";
	public static final String PARAMETER_EXHBT_LIST = "EXHBT_LIST";
	public static final String PARAMETER_IF_TIME = "IF_TIME";
	public static final String PARAMETER_KIOSK = "KIOSK";
	public static final String PARAMETER_STSUBSIDNO = "STSUBSIDNO";
	public static final String PARAMETER_ARSUBSIDNO = "ARSUBSIDNO";


}
