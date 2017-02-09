package com.neighborlib.network;

import android.content.Context;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.neighborlib.common.APIDef;
import com.neighborlib.common.MessageDef;
import com.neighborlib.common.ParameterDef;
import com.neighborlib.network.request.FileRequest;
import com.neighborlib.network.request.JSONObjectRequest;
import com.neighborlib.network.request.MultiPartRequest;
import com.neighborlib.network.request.StringRequest;


import org.apache.http.entity.mime.MultipartEntity;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


/**
 * <pre>
 *  1. 기능 : 통신 인터페이스 구현 클래스
 *  2. 처리 개요 :
 *     - 통신 인터페이스 구현 클래스
 *  3. 주의사항 :
 *  4. 작성자/작성일 : 최형길 / 2013. 7. 1.
 * ===================================
 *  5. 수정사항
 *  5.1 요구사항 ID :
 *     - 수정자/수정일 : 이동식 / 2013. 9. 6.
 *     - 수정사유/내역 : 신규 인터페이스 추가 / sendMultipart() 추가
 *  5.2 요구사항 ID :
 *     - 수정자/수정일 : 이동식 / 2013. 9. 11.
 *     - 수정사유/내역 : 신규 인터페이스 추가 / requestImage() 추가
 *  5.3 요구사항 ID :
 *     - 수정자/수정일 : 이동식 / 2013. 9. 26.
 *     - 수정사유/내역 : 신규 인터페이스 추가 / updateImage(), stopAll() 추가
 * ===================================
 * </pre>
 *
 * @author : 최형길
 * @version : v1.0.0
 * @see : AbsNetwork 참조
 * @since : J2SE 6.0
 **/
public class NetworkManager extends AbsNetworkManager {

    /**
     * HTTP 헤더 언어셋 태그
     */
    private static final String HTTP_HEADER_ACCEPT_LANGUAGE = "Accept-Language";
    /**
     * HTTP 헤더 Personal id 태그
     */
    private static final String HTTP_HEADER_PERSONAL_ID = "personal_id";

    /**
     * NetworkManager 싱글턴 객체
     */
    private static NetworkManager instance = null;
    /**
     * Personal ID
     */
    private String personalId = null;
    /**
     * 맥주소
     */
    private String macAddress = null;
    /**
     * 뒤로가기 막는 변수
     */
    private boolean backable = true;
    /**
     * 스와이프 레이아웃
     */
    private SwipeRefreshLayout swipeRefreshLayout;

    /**
     * 파싱 현재 상태
     */
    private Boolean refreshState = false;
    /**
     * 파싱 중복 상태
     */
    private Boolean refreshOverlay = false;

    /**
     * <pre>
     * 1. 기능 : NetworkManager 클래스 생성자
     * 2. 처리개요 :
     *     - NetworkManager 클래스 생성자로 변수 초기화를 수행한다.
     * 3. 주의사항 :
     * ===================================
     * 4. 작성자/작성일 : 이동식 / 2013. 7. 1.
     * ===================================
     * 5. 수정사항
     * 5.1 요구사항 ID :
     *     - 수정자/수정일 :
     *     - 수정사유/내역 :
     * ===================================
     * </pre>
     *
     * @param context context 객체
     */
    private NetworkManager(Context context) {
        super.init(context);
    }

    /**
     * <pre>
     * 1. 기능 : NetworkManager 싱글턴 객체 반환
     * 2. 처리개요 :
     *     - NetworkManager 싱글턴 객체 반환 기능을 수행한다.
     * 3. 주의사항 :
     * ===================================
     * 4. 작성자/작성일 : 이동식 / 2013. 7. 8.
     * ===================================
     * 5. 수정사항
     * 5.1 요구사항 ID :
     *     - 수정자/수정일 :
     *     - 수정사유/내역 :
     * ===================================
     * </pre>
     *
     * @param context context 객체
     * @return NetworkManager 객체
     */
    public static NetworkManager getInstance(Context context) {
        if (instance == null) {
            instance = new NetworkManager(context);
        }
        return instance;
    }

//	/**
//	 * <pre>
//	 * 1. 기능 : 이미지 반환
//	 * 2. 처리개요 :
//	 *     - 이미지 반환 기능을 수행한다.
//	 * 3. 주의사항 :
//	 * ===================================
//	 * 4. 작성자/작성일 : 이동식 / 2013. 10. 31.
//	 * ===================================
//	 * 5. 수정사항
//	 * 5.1 요구사항 ID :
//	 *     - 수정자/수정일 :
//	 *     - 수정사유/내역 :
//	 * ===================================
//	 * </pre>
//	 *
//	 * @param url
//	 *            이미지 url
//	 * @return 이미지 객체
//	 */
//	public Bitmap getImage(String url) {
//		return getImage(url, 0, 0);
//
//	}

//	/**
//	 * <pre>
//	 * 1. 기능 : 이미지 반환
//	 * 2. 처리개요 :
//	 *     - 이미지 반환 기능을 수행한다.
//	 * 3. 주의사항 :
//	 * ===================================
//	 * 4. 작성자/작성일 : 이동식 / 2013. 11. 6.
//	 * ===================================
//	 * 5. 수정사항
//	 * 5.1 요구사항 ID :
//	 *     - 수정자/수정일 :
//	 *     - 수정사유/내역 :
//	 * ===================================
//	 * </pre>
//	 *
//	 * @param url
//	 *            이미지 경로
//	 * @param width
//	 *            이미지 가로 크기
//	 * @param height
//	 *            이미지 세로 크기
//	 * @return 이미지 객체
//	 */
//	public Bitmap getImage(String url, int width, int height) {
//		return imageManager.getImage(url, width, height);
//	}

//	/**
//	 * <pre>
//	 * 1. 기능 : 캐시 이미지 파일 경로 반환
//	 * 2. 처리개요 :
//	 *     - 캐시 이미지 파일 경로 반환 기능을 수행한다.
//	 * 3. 주의사항 :
//	 * ===================================
//	 * 4. 작성자/작성일 : 이동식 / 2014. 11. 11.
//	 * ===================================
//	 * 5. 수정사항
//	 * 5.1 요구사항 ID :
//	 *     - 수정자/수정일 :
//	 *     - 수정사유/내역 :
//	 * ===================================
//	 * </pre>
//	 *
//	 * @param url
//	 *            이미지 url
//	 * @return 캐시 이미지 경로
//	 */
//	public String getImageFilePath(String url) {
//		return imageManager.getSavedFilePath(url);
//	}

    /**
     * <pre>
     * 1. 기능 : Personal ID 설정
     * 2. 처리개요 :
     *     - Personal ID 설정 기능을 수행한다.
     * 3. 주의사항 :
     * ===================================
     * 4. 작성자/작성일 : 이동식 / 2014. 11. 11.
     * ===================================
     * 5. 수정사항
     * 5.1 요구사항 ID :
     *     - 수정자/수정일 :
     *     - 수정사유/내역 :
     * ===================================
     * </pre>
     *
     * @param personalId Personal ID
     */
    public void setPersonalId(String personalId) {
        this.personalId = personalId;
    }

    /**
     * <pre>
     * 1. 기능 : Personal ID 반환
     * 2. 처리개요 :
     *     - Personal ID 반환 기능을 수행한다.
     * 3. 주의사항 :
     * ===================================
     * 4. 작성자/작성일 : 이동식 / 2014. 11. 11.
     * ===================================
     * 5. 수정사항
     * 5.1 요구사항 ID :
     *     - 수정자/수정일 :
     *     - 수정사유/내역 :
     * ===================================
     * </pre>
     *
     * @return Personal ID
     */
    public String getPersonalId() {
        return personalId;
    }

    /**
     * <pre>
     * 1. 기능 : 맥 주소 설정
     * 2. 처리개요 :
     *     - 맥 주소 설정 기능을 수행한다.
     * 3. 주의사항 :
     * ===================================
     * 4. 작성자/작성일 : 이동식 / 2014. 11. 11.
     * ===================================
     * 5. 수정사항
     * 5.1 요구사항 ID :
     *     - 수정자/수정일 :
     *     - 수정사유/내역 :
     * ===================================
     * </pre>
     *
     * @param macAddress 맥 주소
     */
    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    /**
     * <pre>
     * 1. 기능 : 맥 주소 반환
     * 2. 처리개요 :
     *     - 맥 주소 반환 기능을 수행한다.
     * 3. 주의사항 :
     * ===================================
     * 4. 작성자/작성일 : 이동식 / 2014. 11. 11.
     * ===================================
     * 5. 수정사항
     * 5.1 요구사항 ID :
     *     - 수정자/수정일 :
     *     - 수정사유/내역 :
     * ===================================
     * </pre>
     *
     * @return 맥 주소
     */
    public String getMacAddress() {
        return macAddress;
    }

    /**
     * <pre>
     * 1. 기능 : 메뉴 통계 업데이트 요청
     * 2. 처리개요 :
     *     - 메뉴 통계 업테이트 요청 기능을 수행한다.
     * 3. 주의사항 :
     * ===================================
     * 4. 작성자/작성일 : 이동식 / 2015. 12. 4.
     * ===================================
     * 5. 수정사항
     * 5.1 요구사항 ID :
     *     - 수정자/수정일 :
     *     - 수정사유/내역 :
     * ===================================
     * </pre>
     *
     * @param menuId 메뉴 아이디
     */
    public void requestMenuStatsUpdate(String menuId) {
        String url = APIDef.VALUE_TYPE_MENU_STATS;
        String parameter = "?" + ParameterDef.PARAMETER_MENU_ID + "=" + menuId;
        requestJsonObject(url + parameter, Method.POST, null, MessageDef.API_TYPE_MENU_STATS_SUCCESS, MessageDef.API_TYPE_MENU_STATS_FAIL);
    }

    /**
     * <pre>
     * 1. 기능 : JSONObject 반환 타입 API 요청
     * 2. 처리개요 :
     *     - JSONObject 반환 타입 API 요청 기능을 수행한다.
     *     - 프로그래스 다이얼로그가 표시된다.
     * 3. 주의사항 :
     * ===================================
     * 4. 작성자/작성일 : 이동식 / 2013. 7. 1.
     * ===================================
     * 5. 수정사항
     * 5.1 요구사항 ID :
     *     - 수정자/수정일 :
     *     - 수정사유/내역 :
     * ===================================
     * </pre>
     *
     * @param context     Context 객체
     * @param url         요청 URL
     * @param requestType 요청 HTTP METHOD TYPE
     * @param handler     요청결과 처리 핸들러
     * @param succMsg     성공 메시지 상수
     * @param failMsg     실패 메시지 상수
     */
    public void requestJsonObject(Context context, String url, int requestType, Handler handler, int succMsg, int failMsg) {
        boolean isDismissDialog = false;
        if (context != null) {
            //UICommonUtil.getInstance(context).showProgressDialog(context);
            isDismissDialog = true;
        }
        JSONObjectRequest jsObjRequest = new JSONObjectRequest(requestType, url, new JsonObjectSuccessListener(handler, isDismissDialog, succMsg), new ErrorListener(handler,
                isDismissDialog, failMsg)) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<String, String>();
                headers.put(HTTP_HEADER_ACCEPT_LANGUAGE, Locale.getDefault().getLanguage());
                if (personalId != null && personalId.length() > 0) {
                    headers.put(HTTP_HEADER_PERSONAL_ID, personalId);
                }
                return headers;
            }
        };

        jsObjRequest.setRetryPolicy(new DefaultRetryPolicy(60000, 0, 0));
        jsObjRequest.setTag(NETWORK_REQUEST_TAG);

        queue.add(jsObjRequest);
    }

    /**
     * <pre>
     * 1. 기능 : JSONObject 반환 타입 API 요청
     * 2. 처리개요 :
     *     - JSONObject 반환 타입 API 요청 기능을 수행한다.
     *     - swiperefresh 중이므로 반환하지 않는다.
     * 3. 주의사항 :
     * ===================================
     * 4. 작성자/작성일 : 이재우 / 2016. 10. 6.
     * ===================================
     * 5. 수정사항
     * 5.1 요구사항 ID :
     *     - 수정자/수정일 : 김용희 / 2016. 11. 4
     *     - 수정사유/내역 : 한 화면에서 파싱이 2번 일어날 때 Refresh 보안
     * ===================================
     * </pre>
     *
     * @param url         요청 URL
     * @param requestType 요청 HTTP METHOD TYPE
     * @param handler     요청결과 처리 핸들러
     * @param succMsg     성공 메시지 상수
     * @param failMsg     실패 메시지 상수
     */
    public void requestJsonObject(SwipeRefreshLayout mSwipeRefreshLayout, String url, int requestType, Handler handler, int succMsg, int failMsg, boolean refreshable) {
        requestJsonObject(mSwipeRefreshLayout, url, requestType, handler, succMsg, failMsg, refreshable, true);
    }

    public void requestJsonObject(SwipeRefreshLayout mSwipeRefreshLayout, String url, int requestType, Handler handler, int succMsg, int failMsg, boolean refreshable, boolean overlap) {
        this.swipeRefreshLayout = mSwipeRefreshLayout;
//		SmartServiceActivity.getInstance().JsonBackPressedListener(this);
//		swipeRefreshLayout.setEnabled(refreshable);
//		swipeRefreshLayout.setColorSchemeColors(SmartServiceActivity.getInstance().getResources().getColor(R.color.common_primary_red_color));
//		if(overlap && refreshable) {
//			swipeRefreshLayout.post(new Runnable() {
//				@Override
//				public void run() {
//					swipeRefreshLayout.setRefreshing(true);
//				}
//			});
//		}

        JSONObjectRequest jsObjRequest = new JSONObjectRequest(requestType, url, new JsonObjectSuccessListener(handler, swipeRefreshLayout, succMsg, overlap), new ErrorListener(handler,
                swipeRefreshLayout, failMsg, overlap)) {


            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<String, String>();

                headers.put(HTTP_HEADER_ACCEPT_LANGUAGE, Locale.getDefault().getLanguage());
                if (personalId != null && personalId.length() > 0) {
                    headers.put(HTTP_HEADER_PERSONAL_ID, personalId);
                }
                return headers;
            }
        };
        jsObjRequest.setRetryPolicy(new DefaultRetryPolicy(60000, 0, 0));
        jsObjRequest.setTag(NETWORK_REQUEST_TAG);

        queue.add(jsObjRequest);
    }



    /**
     * <pre>
     * 1. 기능 : JSONObject 반환 타입 API 요청
     * 2. 처리개요 :
     *     - JSONObject 반환 타입 API 요청 기능을 수행한다.
     * 3. 주의사항 :
     * ===================================
     * 4. 작성자/작성일 : 이동식 / 2013. 7. 1.
     * ===================================
     * 5. 수정사항
     * 5.1 요구사항 ID :
     *     - 수정자/수정일 :
     *     - 수정사유/내역 :
     * ===================================
     * </pre>
     *
     * @param url         요청 URL
     * @param requestType 요청 HTTP METHOD TYPE
     * @param handler     요청결과 처리 핸들러
     * @param succMsg     성공 메시지 상수
     * @param failMsg     실패 메시지 상수
     */
    public void requestJsonObject(String url, int requestType, Handler handler, int succMsg, int failMsg) {
        requestJsonObject(null, url, requestType, handler, succMsg, failMsg);
    }

    /**
     * <pre>
     * 1. 기능 : String 반환 타입 API 요청
     * 2. 처리개요 :
     *     - String 반환 타입 API 요청 기능을 수행한다.
     * 3. 주의사항 :
     * ===================================
     * 4. 작성자/작성일 : 이동식 / 2014. 11. 11.
     * ===================================
     * 5. 수정사항
     * 5.1 요구사항 ID :
     *     - 수정자/수정일 :
     *     - 수정사유/내역 :
     * ===================================
     * </pre>
     *
     * @param context     Context 객체
     * @param url         요청 URL
     * @param requestType 요청 HTTP METHOD TYPE
     * @param handler     요청결과 처리 핸들러
     * @param succMsg     성공 메시지 상수
     * @param failMsg     실패 메시지 상수
     */
    public void requestString(Context context, String url, int requestType, Handler handler, int succMsg, int failMsg) {
        boolean isDismissDialog = false;
        if (context != null) {
//            UICommonUtil.getInstance(context).showProgressDialog(context);
            isDismissDialog = true;
        }

        StringRequest stringRequest = new StringRequest(requestType, url, new StringSuccessListener(handler, isDismissDialog, succMsg), new ErrorListener(handler, isDismissDialog,
                failMsg)) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<String, String>();

                headers.put(HTTP_HEADER_ACCEPT_LANGUAGE, Locale.getDefault().getLanguage());
                if (personalId != null && personalId.length() > 0) {
                    headers.put(HTTP_HEADER_PERSONAL_ID, personalId);
                }
                return headers;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(60000, 0, 0));
        stringRequest.setTag(NETWORK_REQUEST_TAG);
        queue.add(stringRequest);
    }

    /**
     * <pre>
     * 1. 기능 : String 반환 타입 API 요청
     * 2. 처리개요 :
     *     - String 반환 타입 API 요청 기능을 수행한다.
     * 3. 주의사항 :
     * ===================================
     * 4. 작성자/작성일 : 이동식 / 2014. 11. 11.
     * ===================================
     * 5. 수정사항
     * 5.1 요구사항 ID :
     *     - 수정자/수정일 :
     *     - 수정사유/내역 :
     * ===================================
     * </pre>
     *
     * @param url         요청 URL
     * @param requestType 요청 HTTP METHOD TYPE
     * @param handler     요청결과 처리 핸들러
     * @param succMsg     성공 메시지 상수
     * @param failMsg     실패 메시지 상수
     */
    public void requestString(String url, int requestType, Handler handler, int succMsg, int failMsg) {
        requestString(null, url, requestType, handler, succMsg, failMsg);
    }

    /**
     * <pre>
     * 1. 기능 : 파일 다운로드 요청
     * 2. 처리개요 :
     *     - 파일 다운로드 요청 기능을 수행한다.
     * 3. 주의사항 :
     * ===================================
     * 4. 작성자/작성일 : 이동식 / 2013. 11. 13.
     * ===================================
     * 5. 수정사항
     * 5.1 요구사항 ID :
     *     - 수정자/수정일 :
     *     - 수정사유/내역 :
     * ===================================
     * </pre>
     *
     * @param context  Context 객체
     * @param fileUrl  파일 URL
     * @param savePath 파일 저장 경로
     * @param handler  요청결과 처리 핸들러
     * @param succMsg  성공 메시지 상수
     * @param failMsg  실패 메시지 상수
     */
    public void requestFile(Context context, String fileUrl, String savePath, Handler handler, int succMsg, int failMsg) {
        boolean isDismissDialog = false;
        if (context != null) {
//            UICommonUtil.getInstance(context).showProgressDialog(context);
            isDismissDialog = true;
        }
        FileRequest fileRequest = new FileRequest(fileUrl, savePath, new FileSuccessListener(handler, isDismissDialog, succMsg), new ErrorListener(handler, isDismissDialog,
                failMsg));
        fileRequest.setRetryPolicy(new DefaultRetryPolicy(60000, 0, 0));
        fileRequest.setTag(NETWORK_REQUEST_TAG);
        queue.add(fileRequest);
    }

    /**
     * <pre>
     * 1. 기능 : 파일 다운로드 요청
     * 2. 처리개요 :
     *     - 파일 다운로드 요청 기능을 수행한다.
     * 3. 주의사항 :
     * ===================================
     * 4. 작성자/작성일 : 이동식 / 2013. 11. 13.
     * ===================================
     * 5. 수정사항
     * 5.1 요구사항 ID :
     *     - 수정자/수정일 :
     *     - 수정사유/내역 :
     * ===================================
     * </pre>
     *
     * @param fileUrl  파일 URL
     * @param savePath 파일 저장 경로
     * @param handler  요청결과 처리 핸들러
     * @param succMsg  성공 메시지 상수
     * @param failMsg  실패 메시지 상수
     */
    public void requestFile(String fileUrl, String savePath, Handler handler, int succMsg, int failMsg) {
        requestFile(null, fileUrl, savePath, handler, succMsg, failMsg);
    }

//	/**
//	 * <pre>
//	 * 1. 기능 : 이미지 파일 요청
//	 * 2. 처리개요 :
//	 *     - 이미지 파일 요청 기능을 수행한다.
//	 * 3. 주의사항 :
//	 * ===================================
//	 * 4. 작성자/작성일 : 이동식 / 2013. 9. 11.
//	 * ===================================
//	 * 5. 수정사항
//	 * 5.1 요구사항 ID :
//	 *     - 수정자/수정일 :
//	 *     - 수정사유/내역 :
//	 * ===================================
//	 * </pre>
//	 *
//	 * @param url
//	 *            이미지 경로 url
//	 * @param handler
//	 *            요청결과 처리 핸들러
//	 * @param imageView
//	 *            비동기 이미지 뷰 객체
//	 * @param failMsg
//	 *            실패 메시지 상수
//	 */
//	public void requestImage(String url, Handler handler, AsyncImageView imageView, int failMsg) {
//		if (imageManager.isContain(url)) {
//			imageView.setImageUrl(url, imageManager);
//			return;
//		}
//		ImgRequest imageRequest = new ImgRequest(imageManager, imageView, url, new ErrorListener(handler, false, failMsg));
//		imageView.setImageRequest(imageRequest);
//		imageRequest.setRetryPolicy(new DefaultRetryPolicy(60000, 0, 0));
//		imageRequest.setTag(NETWORK_REQUEST_TAG);
//		queue.add(imageRequest);
//	}

//	/**
//	 * <pre>
//	 * 1. 기능 : 이미지 업데이트 요청
//	 * 2. 처리개요 :
//	 *     - 이미지 업데이트 요청 기능을 수행한다.
//	 * 3. 주의사항 :
//	 * ===================================
//	 * 4. 작성자/작성일 : 이동식 / 2013. 9. 26.
//	 * ===================================
//	 * 5. 수정사항
//	 * 5.1 요구사항 ID :
//	 *     - 수정자/수정일 :
//	 *     - 수정사유/내역 :
//	 * ===================================
//	 * </pre>
//	 *
//	 * @param url
//	 *            이미지 경로 url
//	 * @param handler
//	 *            요청결과 처리 핸들러
//	 * @param imageView
//	 *            비동기 이미지 뷰 객체
//	 * @param failMsg
//	 *            실패 메시지 상수
//	 */
//	public void updateImage(String url, Handler handler, AsyncImageView imageView, int failMsg) {
//		if (imageManager.isContain(url)) {
//			imageManager.removeImageData(url);
//		}
//		ImgRequest imageRequest = new ImgRequest(imageManager, imageView, url, new ErrorListener(handler, false, failMsg));
//		imageView.setImageRequest(imageRequest);
//		imageRequest.setRetryPolicy(new DefaultRetryPolicy(60000, 0, 0));
//		imageRequest.setTag(NETWORK_REQUEST_TAG);
//		queue.add(imageRequest);
//	}

    /**
     * <pre>
     * 1. 기능 : MultiPart 방식 전송
     * 2. 처리개요 :
     *     - MultiPart 방식 전송 기능을 수행한다.
     *     - 프로그레스 다이얼로그가 표시된다.
     * 3. 주의사항 :
     * ===================================
     * 4. 작성자/작성일 : 이동식 / 2013. 9. 6.
     * ===================================
     * 5. 수정사항
     * 5.1 요구사항 ID :
     *     - 수정자/수정일 :
     *     - 수정사유/내역 :
     * ===================================
     * </pre>
     *
     * @param context     Context 객체
     * @param url         요청 url
     * @param requestType 요청 HTTP METHOD TYPE
     * @param entity      전송 Multipart Entity
     * @param handler     요청결과 처리 핸들러
     * @param succMsg     성공 메시지 상수
     * @param failMsg     실패 메시지 상수
     */
    public void sendMultipart(Context context, String url, int requestType, MultipartEntity entity, Handler handler, int succMsg, int failMsg) {

        boolean isDismissDialog = false;
        if (context != null) {

//            UICommonUtil.getInstance(context).showProgressDialog(context);
            isDismissDialog = true;
        }
        MultiPartRequest multipartRequest = new MultiPartRequest(url, requestType, entity, new MultipartSuccessListener(handler, isDismissDialog, succMsg), new ErrorListener(
                handler, isDismissDialog, failMsg)) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<String, String>();

                headers.put(HTTP_HEADER_ACCEPT_LANGUAGE, Locale.getDefault().getLanguage());
                if (personalId != null && personalId.length() > 0) {
                    headers.put(HTTP_HEADER_PERSONAL_ID, personalId);
                }
                return headers;
            }
        };
        multipartRequest.setRetryPolicy(new DefaultRetryPolicy(60000, 0, 0));
        multipartRequest.setTag(NETWORK_REQUEST_TAG);
        queue.add(multipartRequest);
    }

    /**
     * <pre>
     * 1. 기능 : MultiPart 방식 전송
     * 2. 처리개요 :
     *     - MultiPart 방식 전송 기능을 수행한다.
     * 3. 주의사항 :
     * ===================================
     * 4. 작성자/작성일 : 이동식 / 2013. 9. 6.
     * ===================================
     * 5. 수정사항
     * 5.1 요구사항 ID :
     *     - 수정자/수정일 :
     *     - 수정사유/내역 :
     * ===================================
     * </pre>
     *
     * @param url         요청 url
     * @param requestType 요청 HTTP METHOD TYPE
     * @param entity      전송 Multipart Entity
     * @param handler     요청결과 처리 핸들러
     * @param succMsg     성공 메시지 상수
     * @param failMsg     실패 메시지 상수
     */
    public void sendMultipart(String url, int requestType, MultipartEntity entity, Handler handler, int succMsg, int failMsg) {
        sendMultipart(null, url, requestType, entity, handler, succMsg, failMsg);
    }


    /**
     * <pre>
     *  1. 기능 : String 타입 요청 성공 리스너 클래스
     *  2. 처리 개요 :
     *     - String 타입 요청 성공 리스너 클래스
     *  3. 주의사항 :
     *  4. 작성자/작성일 : 이동식 / 2014. 11. 11.
     * ===================================
     *  5. 수정사항
     *  5.1 요구사항 ID :
     *     - 수정자/수정일 :
     *     - 수정사유/내역 :
     * ===================================
     * </pre>
     *
     * @author : 이동식
     * @version : v1.0.0
     * @see : 참조
     * @since : J2SE 6.0
     **/
    public class StringSuccessListener implements Response.Listener<String> {

        /**
         * 요청 처리 핸들러
         */
        private Handler handler;
        /**
         * 성공 메시지 상수
         */
        private int succMsg;
        /**
         * 프로그래스 팝업 종료 여부
         */
        private boolean isDismissDialog;

        /**
         * <pre>
         * 1. 기능 : StringSuccessListener 생성자
         * 2. 처리개요 :
         *     - StringSuccessListener 생성자로 초기화를 수행한다.
         * 3. 주의사항 :
         * ===================================
         * 4. 작성자/작성일 : 이동식 / 2014. 11. 11.
         * ===================================
         * 5. 수정사항
         * 5.1 요구사항 ID :
         *     - 수정자/수정일 :
         *     - 수정사유/내역 :
         * ===================================
         * </pre>
         *
         * @param handler         요청 처리 핸들러
         * @param isDismissDialog 프로그래스 팝업 종료 여부
         * @param succMsg         성공 메시지 코드
         */
        StringSuccessListener(Handler handler, boolean isDismissDialog, int succMsg) {
            this.isDismissDialog = isDismissDialog;
            this.succMsg = succMsg;
            this.handler = handler;
        }

        @Override
        public void onResponse(String response) {
            if (handler != null) {
                handler.sendMessage(handler.obtainMessage(succMsg, response));
            }
            if (isDismissDialog == true) {
//                UICommonUtil.getInstance(networkManagerContext).dismissDialog();
            }
        }

        /**
         * <pre>
         * 1. 기능 : 요청 핸들러 제거
         * 2. 처리개요 :
         *     - 요청 핸들러 제거 기능을 수행한다.
         * 3. 주의사항 :
         * ===================================
         * 4. 작성자/작성일 : 이동식 / 2014. 11. 11.
         * ===================================
         * 5. 수정사항
         * 5.1 요구사항 ID :
         *     - 수정자/수정일 :
         *     - 수정사유/내역 :
         * ===================================
         * </pre>
         */
        public void clear() {
            handler = null;
        }
    }

    /**
     * <pre>
     *  1. 기능 : JSONObject 요청 성공 리스너
     *  2. 처리 개요 :
     *     - JSONObject 요청 성공 리스너 기능을 수행한다.
     *  3. 주의사항 :
     *  4. 작성자/작성일 : 이동식 / 2013. 7. 8.
     * ===================================
     *  5. 수정사항
     *  5.1 요구사항 ID :
     *     - 수정자/수정일 : 김용희 / 2016. 11. 4
     *     - 수정사유/내역 : 한 화면에서 파싱이 2번 일어날 때 Refresh 보안
     * ===================================
     * </pre>
     *
     * @author : 이동식
     * @version : v1.0.0
     * @see : 참조
     * @since : J2SE 6.0
     **/
    public class JsonObjectSuccessListener implements Response.Listener<JSONObject> {

        /**
         * 요청 핸들러
         */
        private Handler handler;
        /**
         * 성공 메시지 상수
         */
        private int succMsg;
        /**
         * 프로그래스 팝업 종료 여부
         */
        private boolean isDismissDialog;

        private SwipeRefreshLayout swipeRefreshLayout;

        private boolean isOverlapRefresh;

        /**
         * <pre>
         * 1. 기능 : ObjectSuccessListener 클래스 생성자
         * 2. 처리개요 :
         *     - ObjectSuccessListener 클래스 생성자로 변수 초기화를 수행한다.
         * 3. 주의사항 :
         * ===================================
         * 4. 작성자/작성일 : 이동식 / 2013. 7. 8.
         * ===================================
         * 5. 수정사항
         * 5.1 요구사항 ID :
         *     - 수정자/수정일 :
         *     - 수정사유/내역 :
         * ===================================
         * </pre>
         *
         * @param handler          요청 핸들러
         * @param isDissmissDialog 프로그래스 팝업 종료 여부
         * @param succMsg          성공 메시지 상수
         */
        JsonObjectSuccessListener(Handler handler, boolean isDissmissDialog, int succMsg) {
            this.isDismissDialog = isDissmissDialog;
            this.succMsg = succMsg;
            this.handler = handler;
        }

        JsonObjectSuccessListener(Handler handler, SwipeRefreshLayout swipeRefreshLayout, int succMsg, boolean isOverlapRefresh) {
            this.swipeRefreshLayout = swipeRefreshLayout;
            this.succMsg = succMsg;
            this.handler = handler;
            this.isOverlapRefresh = isOverlapRefresh;
        }


        @Override
        public void onResponse(JSONObject response) {
            if (handler != null) {
                handler.sendMessage(handler.obtainMessage(succMsg, response.toString()));
            }
            if (isDismissDialog == true) {
//                UICommonUtil.getInstance(networkManagerContext).dismissDialog();
            }
            if (swipeRefreshLayout != null && isOverlapRefresh) {
                swipeRefreshLayout.setRefreshing(false);
            }
        }

        /**
         * <pre>
         * 1. 기능 : 요청 핸들러 제거
         * 2. 처리개요 :
         *     - 요청 핸들러 제거 기능을 수행한다.
         * 3. 주의사항 :
         * ===================================
         * 4. 작성자/작성일 : 이동식 / 2014. 11. 11.
         * ===================================
         * 5. 수정사항
         * 5.1 요구사항 ID :
         *     - 수정자/수정일 :
         *     - 수정사유/내역 :
         * ===================================
         * </pre>
         */
        public void clear() {
            handler = null;
        }
    }

    /**
     * <pre>
     *  1. 기능 : Multipart 전송 성공 리스너
     *  2. 처리 개요 :
     *     - Multipart 전송 성공 리스너 기능을 수행한다.
     *  3. 주의사항 :
     *  4. 작성자/작성일 : 이동식 / 2013. 9. 6.
     * ===================================
     *  5. 수정사항
     *  5.1 요구사항 ID :
     *     - 수정자/수정일 :
     *     - 수정사유/내역 :
     * ===================================
     * </pre>
     *
     * @author : 이동식
     * @version : v1.0.0
     * @see : 참조
     * @since : J2SE 6.0
     **/
    public class MultipartSuccessListener implements Response.Listener<JSONObject> {

        /**
         * 요청 핸들러
         */
        private Handler handler;
        /**
         * 성공 메시지 상수
         */
        private int succMsg;
        /**
         * 프로그래스 팝업 종료 여부
         */
        private boolean isDismissDialog;

        /**
         * <pre>
         * 1. 기능 : MultipartSuccessListener 클래스 생성자
         * 2. 처리개요 :
         *     - MultipartSuccessListener 클래스 생성자로 변수 초기화를 수행한다.
         * 3. 주의사항 :
         * ===================================
         * 4. 작성자/작성일 : 이동식 / 2013. 9. 6.
         * ===================================
         * 5. 수정사항
         * 5.1 요구사항 ID :
         *     - 수정자/수정일 :
         *     - 수정사유/내역 :
         * ===================================
         * </pre>
         *
         * @param handler         요청 핸들러
         * @param isDismissDialog 프로그래스 팝업 종료 여부
         * @param succMsg         성공 메시지 상수
         */
        MultipartSuccessListener(Handler handler, boolean isDismissDialog, int succMsg) {
            this.isDismissDialog = isDismissDialog;
            this.succMsg = succMsg;
            this.handler = handler;
        }

        @Override
        public void onResponse(JSONObject response) {
            if (handler != null) {
                handler.sendMessage(handler.obtainMessage(succMsg, response.toString()));
            }
            if (isDismissDialog == true) {
//                UICommonUtil.getInstance(networkManagerContext).dismissDialog();
            }
        }

        /**
         * <pre>
         * 1. 기능 : 요청 핸들러 제거
         * 2. 처리개요 :
         *     - 요청 핸들러 제거 기능을 수행한다.
         * 3. 주의사항 :
         * ===================================
         * 4. 작성자/작성일 : 이동식 / 2014. 11. 11.
         * ===================================
         * 5. 수정사항
         * 5.1 요구사항 ID :
         *     - 수정자/수정일 :
         *     - 수정사유/내역 :
         * ===================================
         * </pre>
         */
        public void clear() {
            handler = null;
        }
    }

    /**
     * <pre>
     *  1. 기능 : 파일 다운로드 성공 리스너
     *  2. 처리 개요 :
     *     - 파일 다운로드 성공 리스너 기능을 수행한다.
     *  3. 주의사항 :
     *  4. 작성자/작성일 : 이동식 / 2013. 11. 13.
     * ===================================
     *  5. 수정사항
     *  5.1 요구사항 ID :
     *     - 수정자/수정일 :
     *     - 수정사유/내역 :
     * ===================================
     * </pre>
     *
     * @author : 이동식
     * @version : v1.0.0
     * @see : 참조
     * @since : J2SE 6.0
     **/
    public class FileSuccessListener implements Response.Listener<String> {

        /**
         * 요청 핸들러
         */
        private Handler handler;
        /**
         * 성공 메시지 상수
         */
        private int succMsg;
        /**
         * 프로그래스 팝업 종료 여부
         */
        private boolean isDismissDialog;

        /**
         * <pre>
         * 1. 기능 : FileSuccessListener 클래스 생성자
         * 2. 처리개요 :
         *     - FileSuccessListener 클래스 생성자로 변수 초기화 기능을 수행한다.
         * 3. 주의사항 :
         * ===================================
         * 4. 작성자/작성일 : 이동식 / 2013. 11. 13.
         * ===================================
         * 5. 수정사항
         * 5.1 요구사항 ID :
         *     - 수정자/수정일 :
         *     - 수정사유/내역 :
         * ===================================
         * </pre>
         *
         * @param handler         요청 핸들러
         * @param isDismissDialog 프로그래스 팝업 종료 여부
         * @param succMsg         성공 메시지 상수
         */
        FileSuccessListener(Handler handler, boolean isDismissDialog, int succMsg) {
            this.isDismissDialog = isDismissDialog;
            this.succMsg = succMsg;
            this.handler = handler;
        }

        @Override
        public void onResponse(String response) {
            if (handler != null) {
                handler.sendMessage(handler.obtainMessage(succMsg, response));
            }
            if (isDismissDialog == true) {
//                UICommonUtil.getInstance(networkManagerContext).dismissDialog();
            }
        }

        /**
         * <pre>
         * 1. 기능 : 요청 핸들러 제거
         * 2. 처리개요 :
         *     - 요청 핸들러 제거 기능을 수행한다.
         * 3. 주의사항 :
         * ===================================
         * 4. 작성자/작성일 : 이동식 / 2014. 11. 11.
         * ===================================
         * 5. 수정사항
         * 5.1 요구사항 ID :
         *     - 수정자/수정일 :
         *     - 수정사유/내역 :
         * ===================================
         * </pre>
         */
        public void clear() {
            handler = null;
        }
    }

    /**
     * <pre>
     *  1. 기능 : API 요청 실패 리스너
     *  2. 처리 개요 :
     *     - API 요청 실패 리스너 기능을 수행한다.
     *  3. 주의사항 :
     *  4. 작성자/작성일 : 이동식 / 2013. 7. 8.
     * ===================================
     *  5. 수정사항
     *  5.1 요구사항 ID :
     *     - 수정자/수정일 :
     *     - 수정사유/내역 :
     * ===================================
     * </pre>
     *
     * @author : 이동식
     * @version : v1.0.0
     * @see : 참조
     * @since : J2SE 6.0
     **/
    public class ErrorListener implements Response.ErrorListener {

        /**
         * 요청 핸들러
         */
        private Handler handler;
        /**
         * 실패 메시지 상수
         */
        private int errorMsg;
        /**
         * 프로그래스 팝업 종료여부
         */
        private boolean isDismissDialog;

        private SwipeRefreshLayout swipeRefreshLayout;

        private boolean isOverlapRefresh;

        /**
         * <pre>
         * 1. 기능 : ErrorListener 클래스 생성자
         * 2. 처리개요 :
         *     - ErrorListener 클래스 생성자로 변수 초기화를 수행한다.
         * 3. 주의사항 :
         * ===================================
         * 4. 작성자/작성일 : 이동식 / 2013. 7. 8.
         * ===================================
         * 5. 수정사항
         * 5.1 요구사항 ID :
         *     - 수정자/수정일 :
         *     - 수정사유/내역 :
         * ===================================
         * </pre>
         *
         * @param handler         요청 핸들러
         * @param isDismissDialog 프로그래스 팝업 종료 여부
         * @param errorMsg        실패 메시지 상수
         */
        ErrorListener(Handler handler, boolean isDismissDialog, int errorMsg) {
            this.handler = handler;
            this.isDismissDialog = isDismissDialog;
            this.errorMsg = errorMsg;
        }

        ErrorListener(Handler handler, SwipeRefreshLayout swipeRefreshLayout, int errorMsg, boolean isOverlapRefresh) {
            this.handler = handler;
            this.swipeRefreshLayout = swipeRefreshLayout;
            this.errorMsg = errorMsg;
            this.isOverlapRefresh = isOverlapRefresh;
        }


        @Override
        public void onErrorResponse(VolleyError error) {
            if (handler != null) {
                handler.sendMessage(handler.obtainMessage(errorMsg, error.toString()));
            }
            if (isDismissDialog) {
//                UICommonUtil.getInstance(networkManagerContext).dismissDialog();
            }
            if (swipeRefreshLayout != null && isOverlapRefresh) {
                swipeRefreshLayout.setRefreshing(false);
            }
        }

        /**
         * <pre>
         * 1. 기능 : 요청 핸들러 제거
         * 2. 처리개요 :
         *     - 요청 핸들러 제거 기능을 수행한다.
         * 3. 주의사항 :
         * ===================================
         * 4. 작성자/작성일 : 이동식 / 2014. 11. 11.
         * ===================================
         * 5. 수정사항
         * 5.1 요구사항 ID :
         *     - 수정자/수정일 :
         *     - 수정사유/내역 :
         * ===================================
         * </pre>
         */
        public void clear() {
            handler = null;
        }
    }

    /**
     * <pre>
     * 1. 기능 : 싱글턴 객체 제거
     * 2. 처리개요 :
     *     - 싱글턴 객체 제거 기능을 수행한다.
     *     - 변수 초기화를 수행한다.
     * 3. 주의사항 :
     * ===================================
     * 4. 작성자/작성일 : 이동식 / 2014. 11. 11.
     * ===================================
     * 5. 수정사항
     * 5.1 요구사항 ID :
     *     - 수정자/수정일 :
     *     - 수정사유/내역 :
     * ===================================
     * </pre>
     */
    public void destroy() {
        queue.cancelAll(NETWORK_REQUEST_TAG);
        queue.getCache().clear();
        queue.stop();
        queue = null;
        imageManager.destroy();
        networkManagerContext = null;
        instance = null;
    }

    /**
     * <pre>
     * 1. 기능 : 중복상태 설정
     * 2. 처리개요 :
     *     - 한 페이지에서 두개 이상의 요청이 있을 때
     *     - refreshOverlay 설정
     * 3. 주의사항 :
     * ===================================
     * 4. 작성자/작성일 : 김용희 / 2016. 11. 3.
     * ===================================
     * 5. 수정사항
     * 5.1 요구사항 ID :
     *     - 수정자/수정일 :
     *     - 수정사유/내역 :
     * ===================================
     * </pre>
     */
    public void setRefreshOverlay(boolean overlay) {
        refreshOverlay = overlay;
    }
}
