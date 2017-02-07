/**
 * 
 */
package com.neighborlib.network.request;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.neighborlib.network.NetworkManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


/**
 * <pre>
 *  1. 기능 : 파일 저장 요청 클래스
 *  2. 처리 개요 : 
 *     - 파일 저장 요청 클래스
 *     - Volley Library 사용
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
public class FileRequest extends Request<byte[]> {

	/** 성공 리스너 */
	private NetworkManager.FileSuccessListener successListener;
	/** 에러 리스너 */
	private NetworkManager.ErrorListener errorListener;
	/** 파일저장 경로 */
	private String filePath;
	/** Exception TAG 설정 */
	private String TAG = "FileRequest";
	/**
	 * <pre>
	 * 1. 기능 : FileRequest 생성자
	 * 2. 처리개요 : 
	 *     - FileRequest 생성자로 초기화를 수행한다.
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
	 * @param url 파일 url
	 * @param filePath 파일 저장 경로
	 * @param successListener 성공 리스너
	 * @param listener 에러 리스너
	 */
	public FileRequest(String url, String filePath, NetworkManager.FileSuccessListener successListener, NetworkManager.ErrorListener listener) {
		super(Method.GET, url, listener);
		this.filePath = filePath;
		this.successListener = successListener;
		this.errorListener = listener;
	}

	@Override
	protected Response<byte[]> parseNetworkResponse(NetworkResponse paramNetworkResponse) {
		return Response.success(paramNetworkResponse.data, HttpHeaderParser.parseCacheHeaders(paramNetworkResponse));

	}

	@Override
	protected void deliverResponse(byte[] arg0) {

		if (isCanceled()) { return; }

		boolean isSuccess = false;
		FileOutputStream fileOutputStream = null;
		try {
			File targetFile = new File(filePath);
			targetFile.mkdirs();
			if (targetFile.exists()) {
				targetFile.delete();
			}
			targetFile.createNewFile();

			fileOutputStream = new FileOutputStream(targetFile);
			fileOutputStream.write(arg0);
			isSuccess = true;
		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//			DLog.e(TAG, "deliverResponse: " + e.getCause());
		} catch (IOException e) {
//			e.printStackTrace();
//			DLog.e(TAG, "deliverResponse: "+ e.getMessage());
		} finally {
			if (fileOutputStream != null) {
				try {
					fileOutputStream.close();
				} catch (IOException e) {
//					e.printStackTrace();
//					DLog.e(TAG, "deliverResponse: "+e.getMessage());
				}
			}

			if (isSuccess) {
				successListener.onResponse(filePath);
			}
			successListener.clear();
			errorListener.clear();
		}
	}
}
