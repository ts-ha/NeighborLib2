package com.neighborlib.setting;

import com.neighborlib.BuildConfig;

public class ServerUrl {

    public static final String API_KEY = "AIzaSyDioHkZSpxDUXfj6WEiLqm5H4aV95syF2w"; // SHORT URL API KEY (테스트 서버, 상용 서버 공통 사용)
    public static final String SERVER_BASE_URL; // 서버 주소
    public static final String SERVER_MOV_URL = "https://goo.gl/gM91xc"; // 이노카 OBD 진단 가이드 동영상
    public static final String SERVER_GEOFENCE_API; // 지오펜스 API 주소

    static {

        if (BuildConfig.BUILD_TYPE.equals("debug")) {

             // 네이버시스템 테스트 서버
             SERVER_BASE_URL = "http://211.41.186.133:8080/innocar";

             // kt 테스트 서버
             // SERVER_BASE_URL = "https://www.tb.innocar.co.kr";

             // 상용 서버
             // SERVER_BASE_URL = "https://www.innocar.co.kr";

             if (SERVER_BASE_URL.equals("https://www.innocar.co.kr")) {

                SERVER_GEOFENCE_API = SERVER_BASE_URL + "/innocar-fms/ServiceAPI/geofence/inOutGeofence.json";

             } else {

                SERVER_GEOFENCE_API = SERVER_BASE_URL + "-fms/ServiceAPI/geofence/inOutGeofence.json";
             }

        } else {

            // 네이버시스템 테스트 서버
            // SERVER_BASE_URL = "http://211.41.186.133:8080/innocar";

            // kt 테스트 서버
            // SERVER_BASE_URL = "https://www.tb.innocar.co.kr";

            // 상용 서버
            SERVER_BASE_URL = "https://www.innocar.co.kr";

            if (SERVER_BASE_URL.equals("https://www.innocar.co.kr")) {

                SERVER_GEOFENCE_API = SERVER_BASE_URL + "/innocar-fms/ServiceAPI/geofence/inOutGeofence.json";

            } else {

                SERVER_GEOFENCE_API = SERVER_BASE_URL + "-fms/ServiceAPI/geofence/inOutGeofence.json";
            }
        }
    }

//  /**
//   * 시도 평균
//   */
//  public static final String OIL_SIDO_AVG_PRICE_API = "http://api.openapi.io/opinetbasic/Oil/OilSidoAvgPrice.jsp?OutPut=Json";
//  /**
//   * 전국 평균
//   */
//  public static final String OIL_PRICE_ALL = "http://api.openapi.io/opinetbasic/Oil/OilPriceAll.jsp?OutPut=Json";
    /**
     * Data API URL
     */
    public static final String SERVER_DATA_API = SERVER_BASE_URL + "/smart.json";
    /**
     * Consumables API URL
     */
    public static final String SERVER_CONSUMABLES_API = SERVER_BASE_URL + "/smartConsumable.json";

    /**
     * Login API URL
     */
    public static final String SERVER_LOGIN_API = SERVER_BASE_URL + "/mbrLogin.do";
    /**
     * Rank API URL
     */
    public static final String SERVER_RANK_API = SERVER_BASE_URL + "/smartRank.json";
    /**
     * Diary API URL
     */
    public static final String SERVER_DIARY_API = SERVER_BASE_URL + "/smartDiary.json";
    /**
     * SNS API URL
     */
    public static final String SERVER_SNS_API = SERVER_BASE_URL + "/smartSns.json";
//  /**
//   * SNS API URL
//   */
//  public static final String SERVER_GEOFENCE_API = SERVER_BASE_URL + "/ServiceAPI/geofence/getGeofencePinList.json";


    // for web page
    public static final String SIGN_IN_URL = SERVER_BASE_URL + "/mbrLogin.do";
    public static final String TRIP_DASHBOARD_URL = SERVER_BASE_URL + "/m/view.do?cmd=vDashboard";
    public static final String CAR_CENTER_SEARCH_URL = SERVER_BASE_URL + "/m/view.do?cmd=vCarCenterSearch";
    public static final String OPTIMUM_FUEL_SPEED_URL = SERVER_BASE_URL + "/m/view.do?cmd=vOptimumFuelSpeed";//최적연비
    public static final String TRIP_DASHBOARD_HABIT_URL = SERVER_BASE_URL + "/m/view.do?cmd=vDrivingHabitView";
    public static final String GET_DRIVING_DATE_URL = SERVER_BASE_URL + "/m/trip.do?cmd=qSelectUserDrivingTripDate";
    public static final String TRIP_LIST_URL = SERVER_BASE_URL + "/m/view.do?cmd=vDrivingList";
    public static final String TRIP_SUMMARY_URL = SERVER_BASE_URL + "/m/view.do?cmd=vDrivingSummary";
    public static final String NOTICE_CATEGORY_LIST_URL = SERVER_BASE_URL + "/m/board.do?cmd=qSelectCategoryList";
    public static final String NOTICE_LIST_URL = SERVER_BASE_URL + "/m/board.do?cmd=vBoard";
    public static final String EVENT_LIST_URL = SERVER_BASE_URL + "/m/board.do?cmd=vBoard";
    public static final String CAR_CENTER_URL = SERVER_BASE_URL + "/m/view.do?cmd=vCarCenterRequest";
    public static final String CAR_CENTER_NEAR_URL = SERVER_BASE_URL + "/m/view.do?cmd=vAroundCarcenterList";
    //정비소 추천
    public static final String FAQ_URL = SERVER_BASE_URL + "DrivingStyleMapActivity"; //FIXME
    //  public static final String REMOTE_DIAGNOSTICS_URL = SERVER_BASE_URL + "/m/view.do?cmd=vRemoteDiagnosticsView";
    public static final String REMOTE_DIAGNOSTICS_URL = SERVER_BASE_URL + "/m/view.do?cmd=vDrivingAnalysisView";
    public static final String DISCLAIMER_URL = SERVER_BASE_URL + "/m/view.do?cmd=contractViewConfirm";

    public static final String UNREACHABLE_URL = "file:///android_asset/unreachable.html";
    public static final String DISCLAIMER_INDIVIDUAL_INFO_URL = "file:///android_asset/disclaimer/individual_info.html";
    public static final String DISCLAIMER_INNOCAR_SERVICE_URL = "file:///android_asset/disclaimer/innocar_service.html";
    public static final String DISCLAIMER_LOCATION_BASED_SERVICE_URL = "file:///android_asset/disclaimer/location_based_service.html";
    public static final String DISCLAIMER_THIRD_PARTY_URL = "file:///android_asset/disclaimer/third_party.html";
    public static final String PRIVACY_POLICY_URL = SERVER_BASE_URL + "/m/view.do?cmd=vPrivacyPolicy";
    public static final String OPTIMUM_FUEL_REAL = SERVER_BASE_URL + "/m/view.do?cmd=vDrivingHabitOptimumFuelReal";
    public static final String RECORD_MONTHLY = SERVER_BASE_URL + "/m/view.do?cmd=vDrivingRecordMonthly";


    //  public static final String DISCLAIMER_JOIN_URL = SERVER_BASE_URL + "/m/contract.do";
    public static final String DISCLAIMER_JOIN_URL = SERVER_BASE_URL + "/m/contract.do";
    public static final String LICENSE_URL = "file:///android_asset/license.html";


    public static final String OUTPUT_PREDICT_URL = SERVER_BASE_URL + "/m/view.do?predict=output"; //출력
    public static final String INHALATION_PREDICT_URL = SERVER_BASE_URL + "/m/view.do?predict=inhalation"; // 흡기
    public static final String IGNITION_CONTROL_PREDICT_URL = SERVER_BASE_URL + "/m/view.do?predict=ignitionControl"; //점화제어
    public static final String EXHAUST_PREDICT_URL = SERVER_BASE_URL + "/m/view.do?predict=exhaust";          //배기
    public static final String FUEL_PREDICT_URL = SERVER_BASE_URL + "/m/view.do?predict=fuel";  // 연료 계통
    public static final String BATTERY_PREDICT_URL = SERVER_BASE_URL + "/m/view.do?predict=battery"; // 배터리
    public static final String COOLANT_PREDICT_URL = SERVER_BASE_URL + "/m/view.do?predict=coolant"; // 냉각수
    public static final String DTC_PREDICT_URL = SERVER_BASE_URL + "/m/view.do?predict=dtcOccur";

}
