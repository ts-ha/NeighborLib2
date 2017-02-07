/**
 * 
 */
package com.neighborlib.network.request;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.neighborlib.network.NetworkManager;

import org.apache.http.entity.mime.MultipartEntity;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;


/**
 * <pre>
 *  1. 기능 : MultiPart 방식 전송 요청
 *  2. 처리 개요 : 
 *     - MultiPart 방식 전송 요청
 *     - Volley Library 사용
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
public class MultiPartRequest extends Request<JSONObject> {

	/** 성공 리스너 */
	private NetworkManager.MultipartSuccessListener successListener;
	/** 에러 리스너 */
	private NetworkManager.ErrorListener errorListener;

	/** 멀티파트 객체 */
	private MultipartEntity multipartEntity;

	/**
	 * <pre>
	 * 1. 기능 : MultiPartRequest 클래스 생성자
	 * 2. 처리개요 : 
	 *     - MultiPartRequest 클래스 생성자로 변수 초기화를 수행한다.
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
	 * @param url
	 *            요청 url
	 * @param method
	 *            요청 HTTP METHOD TYPE
	 * @param entity
	 *            전송 Multipart Entity
	 * @param successListener
	 *            성공 메시지 상수
	 * @param errorListener
	 *            실패 메시지 상수
	 */
	public MultiPartRequest(String url, int method, MultipartEntity entity, NetworkManager.MultipartSuccessListener successListener, NetworkManager.ErrorListener errorListener) {
		super(method, url, errorListener);
		this.successListener = successListener;
		this.errorListener = errorListener;
		this.multipartEntity = entity;
	}

	@Override
	public String getBodyContentType() {
		return multipartEntity.getContentType().getValue();

	}

	@Override
	public byte[] getBody() throws AuthFailureError {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		try {
			multipartEntity.writeTo(outputStream);
		} catch (IOException e) {
			VolleyLog.e("IOException writing to ByteArrayOutputStream");
		}
		return outputStream.toByteArray();
	}

	@Override
	protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
		try {
			String jsonString = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
			return Response.success(new JSONObject(jsonString), HttpHeaderParser.parseCacheHeaders(response));
		} catch (UnsupportedEncodingException e) {
			return Response.error(new ParseError(e));
		} catch (JSONException je) {
			return Response.error(new ParseError(je));
		}
	}

	@Override
	protected void deliverResponse(JSONObject response) {
		successListener.onResponse(response);

		successListener.clear();
		errorListener.clear();
	}

}
