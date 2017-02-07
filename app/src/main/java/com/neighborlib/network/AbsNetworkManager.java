package com.neighborlib.network;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * <pre>
 *  1. 기능 : 통신 공통 기능구현 클래스
 *  2. 처리 개요 :  
 *     - 통신 공통 기능구현 클래스
 *  3. 주의사항 : 
 *  4. 작성자/작성일 : 최형길 / 2013. 7. 1.
 * ===================================
 *  5. 수정사항
 *  5.1 요구사항 ID :
 *     - 수정자/수정일 :
 *     - 수정사유/내역 :
 * ===================================
 * </pre>
 * 
 * @author : 최형길
 * @version : v1.0.0
 * @see : NetworkManager 참조
 * @since : J2SE 6.0
 **/
public abstract class AbsNetworkManager {
	public static final String NETWORK_REQUEST_TAG = "network_request_tag";
	/** 네트워크 요청 큐 */
	protected RequestQueue queue;
	/** 이미지 캐싱 맵 */
	protected ImageManager imageManager;
	/** Context 객체 */
	protected Context networkManagerContext;
	/**
	 * <pre>
	 * 1. 기능 : 내부 초기화
	 * 2. 처리개요 : 
	 *     - 내부 초기화 기능을 수행한다.
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
	 * @param context
	 *            context 객체
	 */
	protected void init(Context context) {
		if (context == null) { return; }
		networkManagerContext = context.getApplicationContext();
		queue = Volley.newRequestQueue(networkManagerContext);
		imageManager = ImageManager.getInstance(networkManagerContext);
	}

	/**
	 * <pre>
	 * 1. 기능 : 모든 네트워크 요청 취소
	 * 2. 처리개요 : 
	 *     - 모든 네트워크 요청 취소 기능을 수행한다.
	 * 3. 주의사항 : 
	 * ===================================
	 * 4. 작성자/작성일 : 이동식 / 2013. 9. 26.
	 * ===================================
	 * 5. 수정사항
	 * 5.1 요구사항 ID :
	 *     - 수정자/수정일 :
	 *     - 수정사유/내역 :
	 * ===================================
	 * </pre>
	 */
	public void stopAll() {
		queue.cancelAll(NETWORK_REQUEST_TAG);
	}
	
	
}
