/**
 * 
 */
package com.neighborlib.network;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.MediaStore.Images;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * <pre>
 *  1. 기능 : 이미지 캐싱 클래스
 *  2. 처리 개요 : 
 *     - 이미지 캐싱 클래스
 *  3. 주의사항 : 
 *  4. 작성자/작성일 : 이동식 / 2013. 9. 11.
 * ===================================
 *  5. 수정사항
 *  5.1 요구사항 ID :
 *     - 수정자/수정일 : 이동식 / 2013. 9. 16.
 *     - 수정사유/내역 : 신규 메소드 추가 / findBestSampleSize() 추가
 *  5.2 요구사항 ID :
 *     - 수정자/수정일 : 이동식 / 2013. 9. 25.
 *     - 수정사유/내역 : 신규 인터페이스 추가 / removeImageData() 추가
 * ===================================
 * </pre>
 * 
 * @author : 이동식
 * @version : v1.0.0
 * @see : 참조
 * @since : J2SE 6.0
 **/
public class ImageManager {
//	/** 이미지 캐쉬 폴더명 */
//	private static final String IMAGE_CACHE_DIRECTORY_NAME = "image";
//	/** 최대 이미지 캐쉬 사이즈 */
//	private static long MAX_RAW_SIZE = 1024l * 1024l * 10l;

	/** 카메라 촬영 */
	public static final int TAKE_CAMERA = 1;
	/** 갤러리 선택 */
	public static final int TAKE_GALLERY = 2;

	/** 이미지 업로드 최대 가로 크기 */
	private static final int MAX_UPLOAD_IMAGE_WIDTH = 1280;
	/** 이미지 업로드 최대 세로 크기 */
	private static final int MAX_UPLOAD_IMAGE_HEIGHT = 1280;
	/** 이미지 임시 저장 폴더명 */
	private static final String TEMP_DIRECTORY_NAME = "temp";

//	/** 캐쉬 이미지 매칭 해쉬맵 */
//	private HashMap<String, String> cacheFileMap = new HashMap<String, String>();
	/** 두자리 넘버 포맷 */
	private NumberFormat numberFormat = new DecimalFormat("00");
	/** 이미지 경로 uri */
	private Uri imageUri;

	/** 싱글튼 객체 */
	private static ImageManager instance;

	/** Exception TAG 설정 */
	private String TAG = "ImageManager";

//	/** Byte 데이터 캐싱 파일 리스트 */
//	private ArrayList<String> savedFileList;
//	/** 현재 Byte 데이터 캐싱 크기 */
//	private long currentSavedFileSize;
//	/** Context 객체 */
//	private Context context;
//	/** 외부 저장소 파일 객체 */
//	private File externalDirectory;
//	/** 화면 크기 객체 */
//	private Point displaySize;

	/**
	 * <pre>
	 * 1. 기능 : ImageManager 클래스 생성자
	 * 2. 처리개요 : 
	 *     - ImageManager 클래스 생성자로 변수초기화를 수행한다.
	 * 3. 주의사항 : 
	 * ===================================
	 * 4. 작성자/작성일 : 이동식 / 2013. 9. 11.
	 * ===================================
	 * 5. 수정사항
	 * 5.1 요구사항 ID :
	 *     - 수정자/수정일 :
	 *     - 수정사유/내역 :
	 * ===================================
	 * </pre>
	 * 
	 * @param context
	 *            Context 객체
	 */
	private ImageManager(Context context) {
//		this.context = context.getApplicationContext();
//		externalDirectory = context.getExternalFilesDir(IMAGE_CACHE_DIRECTORY_NAME);
//		clearCache();
//		getSavedFileList();
//		displaySize = UICommonUtil.getInstance(context).getDisplaySize();
	}

	/**
	 * <pre>
	 * 1. 기능 : 싱글턴 객체 반환
	 * 2. 처리개요 : 
	 *     - 싱글턴 객체 반환 기능을 수행한다.
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
	 * @param context Context 객체
	 * @return 싱글턴 객체
	 */
	public static ImageManager getInstance(Context context) {
		if (instance == null) {
			instance = new ImageManager(context);
		}

		return instance;
	}

//	/**
//	 * <pre>
//	 *  1. 기능 : 파일 날짜 정렬 필터 클래스
//	 *  2. 처리 개요 :
//	 *     - 파일 날짜 정렬 필터 클래스
//	 *  3. 주의사항 :
//	 *  4. 작성자/작성일 : 이동식 / 2013. 11. 6.
//	 * ===================================
//	 *  5. 수정사항
//	 *  5.1 요구사항 ID :
//	 *     - 수정자/수정일 :
//	 *     - 수정사유/내역 :
//	 * ===================================
//	 * </pre>
//	 *
//	 * @author : 이동식
//	 * @version : v1.0.0
//	 * @see : 참조
//	 * @since : J2SE 6.0
//	 **/
//	private class FileDateCompare implements Comparator<File> {
//
//		@Override
//		public int compare(File lhs, File rhs) {
//			if (lhs.lastModified() > rhs.lastModified()) {
//				return -1;
//			} else if (lhs.lastModified() < rhs.lastModified()) {
//				return +1;
//			} else {
//				return 0;
//			}
//		}
//	}

//	/**
//	 * <pre>
//	 *  1. 기능 : 파일 최종 접근 날짜 필터 클래스
//	 *  2. 처리 개요 :
//	 *     - 파일 최종 접근 날짜 필터 클래스
//	 *  3. 주의사항 :
//	 *  4. 작성자/작성일 : 이동식 / 2013. 11. 6.
//	 * ===================================
//	 *  5. 수정사항
//	 *  5.1 요구사항 ID :
//	 *     - 수정자/수정일 :
//	 *     - 수정사유/내역 :
//	 * ===================================
//	 * </pre>
//	 *
//	 * @author : 이동식
//	 * @version : v1.0.0
//	 * @see : 참조
//	 * @since : J2SE 6.0
//	 **/
//	private class OldFileFilter implements FileFilter {
//
//		/** 현재 날짜 객체 */
//		private GregorianCalendar today;
//
//		/**
//		 * <pre>
//		 * 1. 기능 : OldFileFilter 클래스 생성자
//		 * 2. 처리개요 :
//		 *     - OldFileFilter 클래스 생성자로 변수 초기화 기능을 수행한다.
//		 * 3. 주의사항 :
//		 * ===================================
//		 * 4. 작성자/작성일 : 이동식 / 2013. 11. 6.
//		 * ===================================
//		 * 5. 수정사항
//		 * 5.1 요구사항 ID :
//		 *     - 수정자/수정일 :
//		 *     - 수정사유/내역 :
//		 * ===================================
//		 * </pre>
//		 */
//		public OldFileFilter() {
//			today = new GregorianCalendar();
//			today.set(Calendar.HOUR_OF_DAY, 0);
//			today.set(Calendar.MINUTE, 0);
//			today.set(Calendar.SECOND, 0);
//			today.set(Calendar.MILLISECOND, 0);
//		}
//
//		@Override
//		public boolean accept(File pathname) {
//			GregorianCalendar lastModified = new GregorianCalendar();
//			lastModified.setTimeInMillis(pathname.lastModified());
//			lastModified.set(Calendar.HOUR_OF_DAY, 0);
//			lastModified.set(Calendar.MINUTE, 0);
//			lastModified.set(Calendar.SECOND, 0);
//			lastModified.set(Calendar.MILLISECOND, 0);
//
//			return lastModified.before(today);
//
//		}
//
//	}

//	/**
//	 * <pre>
//	 * 1. 기능 : 캐시 이미지 전체 삭제
//	 * 2. 처리개요 :
//	 *     - 캐시 이미지 전체 삭제 기능을 수행한다.
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
//	 */
//	public void clearCache() {
//		File[] fileList = externalDirectory.listFiles(new OldFileFilter());
//		if (fileList != null && fileList.length > 0) {
//			for (int i = 0; i < fileList.length; i++) {
//				fileList[i].delete();
//			}
//		}
//	}

//	/**
//	 * <pre>
//	 * 1. 기능 : 캐시용량 초과 메모리 삭제
//	 * 2. 처리개요 :
//	 *     - 캐시용량 초과 메모리 삭제 기능을 수행한다.
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
//	 */
//	public void deleteCache() {
//		File savedFile = null;
//		ArrayList<String> deleteFileNameList = new ArrayList<String>();
//		String deleteFileName = null;
//		while (currentSavedFileSize >= MAX_RAW_SIZE) {
//			deleteFileName = savedFileList.remove(0);
//			deleteFileNameList.add(deleteFileName);
//			savedFile = new File(externalDirectory, deleteFileName);
//			currentSavedFileSize = currentSavedFileSize - savedFile.length();
//			savedFile.delete();
//		}
//
//		LocalDBManager.getInstance().deleteCacheFileInfo(context, deleteFileNameList);
//	}

//	/**
//	 * <pre>
//	 * 1. 기능 : 캐시 이미지 목록 반환
//	 * 2. 처리개요 :
//	 *     - 캐시 이미지 목록 반환 기능을 수행한다.
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
//	 */
//	public void getSavedFileList() {
//		File[] fileList = externalDirectory.listFiles();
//		Arrays.sort(fileList, new FileDateCompare());
//
//		currentSavedFileSize = 0l;
//		savedFileList = new ArrayList<String>();
//		for (int i = 0; i < fileList.length; i++) {
//			savedFileList.add(fileList[i].getName());
//			currentSavedFileSize = currentSavedFileSize + fileList[i].length();
//		}
//
//		cacheFileMap = LocalDBManager.getInstance().selectCacheFileInfo(context);
//
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
//	 * @param url 파일 url
//	 * @return 캐시 이미지 파일 경로
//	 */
//	public String getSavedFilePath(String url) {
//		String saveFileName = cacheFileMap.get(url);
//		if (saveFileName == null || saveFileName.length() == 0) { return null; }
//		File saveFile = new File(externalDirectory, saveFileName);
//		if (!saveFile.exists()) { return null; }
//		return saveFile.getPath();
//	}

//	/**
//	 * <pre>
//	 * 1. 기능 : 이미지 Byte 데이터 캐싱
//	 * 2. 처리개요 :
//	 *     - 이미지 Byte 데이터 캐싱 기능을 수행한다.
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
//	 *            이미지 경로
//	 * @param data
//	 *            이미지 데이터
//	 */
//	public void putImageData(String url, byte[] data) {
//		String saveFileName = cacheFileMap.get(url);
//		if (saveFileName == null || saveFileName.length() == 0) {
//			saveFileName = String.valueOf(System.currentTimeMillis());
//		}
//
//		File saveFile = new File(externalDirectory, saveFileName);
//		if (saveFile.exists()) {
//			saveFile.setLastModified(System.currentTimeMillis());
//			String lastestFileName = savedFileList.remove(savedFileList.indexOf(saveFileName));
//			savedFileList.add(lastestFileName);
//		} else {
//			FileOutputStream outputStream = null;
//			try {
//				savedFileList.add(saveFileName);
//				saveFile.createNewFile();
//				outputStream = new FileOutputStream(saveFile);
//				outputStream.write(data);
//
//				currentSavedFileSize = currentSavedFileSize + data.length;
//				cacheFileMap.put(url, saveFileName);
//				LocalDBManager.getInstance().insertCacheFileInfo(context, saveFileName, url);
//			} catch (FileNotFoundException e) {
//				e.printStackTrace();
//			} catch (IOException e) {
//				e.printStackTrace();
//			} finally {
//				if (outputStream != null) {
//					try {
//						outputStream.close();
//					} catch (IOException e) {
//						e.printStackTrace();
//					}
//				}
//			}
//		}
//
//		deleteCache();
//	}

//	/**
//	 * <pre>
//	 * 1. 기능 : 이미지 캐싱 여부 확인
//	 * 2. 처리개요 :
//	 *     - 이미지 캐싱 여부 확인 기능을 수행한다.
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
//	 *            이미지 경로
//	 * @return 캐싱 여부
//	 */
//	public boolean isContain(String url) {
//		if (url == null || url.equalsIgnoreCase("null")) { return false; }
//
//		String saveFileName = cacheFileMap.get(url);
//		if (saveFileName == null || saveFileName.length() == 0) { return false; }
//
//		File saveFile = new File(externalDirectory, saveFileName);
//		return saveFile.exists();
//	}

//	/**
//	 * <pre>
//	 * 1. 기능 : 이미지 Byte 데이터 반환
//	 * 2. 처리개요 :
//	 *     - 이미지 Byte 데이터 반환 기능을 수행한다.
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
//	 *            이미지 경로
//	 * @return 이미지 데이터
//	 */
//	public byte[] getImageData(String url) {
//
//		String saveFileName = cacheFileMap.get(url);
//		if (saveFileName == null || saveFileName.length() == 0) { return null; }
//
//		File savedFile = new File(externalDirectory, saveFileName);
//
//		if (!savedFile.exists()) { return null; }
//		byte[] saveData = new byte[(int) savedFile.length()];
//		FileInputStream inputStream = null;
//		try {
//			inputStream = new FileInputStream(savedFile);
//			inputStream.read(saveData);
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {
//			if (inputStream != null) {
//				try {
//					inputStream.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//
//		return saveData;
//	}

//	/**
//	 * <pre>
//	 * 1. 기능 : 이미지 Bitmap 반환
//	 * 2. 처리개요 :
//	 *     - 이미지 Bitmap 반환 기능을 수행한다.
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
//	 *            이미지 경로
//	 * @param viewWidth
//	 *            이미지 가로크기
//	 * @param viewHeight
//	 *            이미지 세로크기
//	 * @return 이미지
//	 */
//	public Bitmap getImage(String url, int viewWidth, int viewHeight) {
//
//		byte[] rawData = getImageData(url);
//		if (rawData == null) { return null; }
//
//		BitmapFactory.Options option = new BitmapFactory.Options();
//		option.inJustDecodeBounds = true;
//		Bitmap dummy = BitmapFactory.decodeByteArray(rawData, 0, rawData.length, option);
//		if (dummy != null) {
//			dummy.recycle();
//			dummy = null;
//		}
//
//		int imageWidth = option.outWidth;
//		int imageHeight = option.outHeight;
//		if (viewWidth <= 0) {
//			viewWidth = displaySize.x;
//		}
//		if (viewHeight <= 0) {
//			viewHeight = displaySize.y;
//		}
//
//		int inSampleSize = findBestSampleSizeFromViewSize(viewWidth, viewHeight, imageWidth, imageHeight);
//		option = new BitmapFactory.Options();
//		option.inSampleSize = inSampleSize;
//		option.inPurgeable = true;
//		Bitmap resizedBitmap = BitmapFactory.decodeByteArray(rawData, 0, rawData.length, option);
//		return resizedBitmap;
//	}

//	/**
//	 * <pre>
//	 * 1. 기능 : 캐시 이미지 삭제
//	 * 2. 처리개요 :
//	 *     - 캐시 이미지 삭제 기능을 수행한다.
//	 * 3. 주의사항 :
//	 * ===================================
//	 * 4. 작성자/작성일 : 이동식 / 2013. 11. 5.
//	 * ===================================
//	 * 5. 수정사항
//	 * 5.1 요구사항 ID :
//	 *     - 수정자/수정일 :
//	 *     - 수정사유/내역 :
//	 * ===================================
//	 * </pre>
//	 *
//	 * @param url
//	 *            삭제할 이미지 경로
//	 */
//	public void removeImageData(String url) {
//		if (url == null || url.equalsIgnoreCase("null")) { return; }
//
//		String saveFileName = cacheFileMap.get(url);
//		File saveFile = new File(externalDirectory, saveFileName);
//		saveFile.delete();
//		savedFileList.remove(saveFileName);
//		LocalDBManager.getInstance().deleteCacheFileInfo(context, saveFileName);
//	}

	/**
	 * <pre>
	 * 1. 기능 : 임시파일 삭제
	 * 2. 처리개요 : 
	 *     - 임시파일 삭제 기능을 수행한다.
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
	 * @param context Context 객체
	 */
	public void clearTempFile(Context context) {
		File rootFile = context.getExternalFilesDir(TEMP_DIRECTORY_NAME);
		File[] childList = rootFile.listFiles();
		for (int i = 0; i < childList.length; i++) {
			childList[i].delete();
		}
	}

	/**
	 * <pre>
	 * 1. 기능 : 이미지 로드
	 * 2. 처리개요 : 
	 *     - 이미지 로드 기능을 수행한다.
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
	 * @param context Context 객체
	 * @param uri 이미지 uri 정보
	 * @param maxWidth 최대 가로크기
	 * @param maxHeight 최대 세로 크기
	 * @return 이미지
	 */
	public Bitmap getImageFromUri(Context context, Uri uri, int maxWidth, int maxHeight) {
		InputStream input = null;
		BitmapFactory.Options onlyBoundsOptions = new BitmapFactory.Options();
		try {
			input = context.getContentResolver().openInputStream(uri);
			onlyBoundsOptions.inJustDecodeBounds = true;
			onlyBoundsOptions.inPreferredConfig = Bitmap.Config.ARGB_8888;//optional
			Bitmap dummy = BitmapFactory.decodeStream(input, null, onlyBoundsOptions);
			if (dummy != null) {
				dummy.recycle();
				dummy = null;
			}

			if ((onlyBoundsOptions.outWidth == -1) || (onlyBoundsOptions.outHeight == -1)) { return null; }

		} catch (Exception e) {
//			e.printStackTrace();
//			DLog.e(TAG, "getImageFromUri: "+e.getMessage() );
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
//					e.printStackTrace();
//					DLog.e(TAG, "getImageFromUri: "+e.getMessage() );
				}
			}
		}

		BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
		bitmapOptions.inSampleSize = findBestSampleSizeFromViewSize(maxWidth, maxHeight, onlyBoundsOptions.outWidth, onlyBoundsOptions.outHeight);
		bitmapOptions.inPreferredConfig = Bitmap.Config.ARGB_8888;//optional
		bitmapOptions.inPurgeable = true;

		Bitmap resizedBitmap = null; //리사이징
		Bitmap rotateBitmap = null; //리사이징 + 이미지 회전

		//이미지 회전 및 리사이징
		Matrix matrix = new Matrix();

		float rotation = rotationForImage(context, uri);

		try {
			input = context.getContentResolver().openInputStream(uri);
			resizedBitmap = BitmapFactory.decodeStream(input, null, bitmapOptions);
			input.close();

			if (rotation != 0f) {
				matrix.preRotate(rotation);
				rotateBitmap = Bitmap.createBitmap(resizedBitmap, 0, 0, resizedBitmap.getWidth(), resizedBitmap.getHeight(), matrix, true);
				resizedBitmap.recycle();
				resizedBitmap = null;
			} else {
				rotateBitmap = resizedBitmap;
			}
		} catch (Exception e) {
//			e.printStackTrace();
//			DLog.e(TAG, "getImageFromUri: "+ e.getMessage());
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
//					e.printStackTrace();
//					DLog.e(TAG, "getImageFromUri: "+e.getMessage() );
				}
			}
		}

		return rotateBitmap;
	}

//	/**
//	 * <pre>
//	 * 1. 기능 : 이미지 로드
//	 * 2. 처리개요 :
//	 *     - 이미지 로드 기능을 수행한다.
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
//	 * @param context Context 객체
//	 * @param uri 이미지 uri 정보
//	 * @return 이미지
//	 */
//	public Bitmap getImageFromUri(Context context, Uri uri) {
//		Point windowSize = UICommonUtil.getInstance(context).getDisplaySize();
//		return getImageFromUri(context, uri, windowSize.x, windowSize.y);
//
//	}

	/**
	 * <pre>
	 * 1. 기능 : 이미지 로드
	 * 2. 처리개요 : 
	 *     - 이미지 로드 기능을 수행한다.
	 *     - 웹 업로드용으로 리사이징을 수행한다.
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
	 * @param context Context 객체
	 * @param uri 이미지 uri 정보
	 * @return 이미지 경로
	 */
	public String getUploadImageFromUri(Context context, Uri uri) {

		InputStream input = null;
		BitmapFactory.Options onlyBoundsOptions = new BitmapFactory.Options();
		try {
			input = context.getContentResolver().openInputStream(uri);
			onlyBoundsOptions.inJustDecodeBounds = true;
			onlyBoundsOptions.inPreferredConfig = Bitmap.Config.ARGB_8888;//optional
			Bitmap dummy = BitmapFactory.decodeStream(input, null, onlyBoundsOptions);
			if (dummy != null) {
				dummy.recycle();
				dummy = null;
			}

			if ((onlyBoundsOptions.outWidth == -1) || (onlyBoundsOptions.outHeight == -1)) { return null; }

		} catch (Exception e) {
//			e.printStackTrace();
//			DLog.e(TAG, "getUploadImageFromUri: "+e.getMessage() );
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
//					e.printStackTrace();
//					DLog.e(TAG, "getUploadImageFromUri: "+e.getMessage() );
				}
			}
		}

		float originalSize = (onlyBoundsOptions.outHeight > onlyBoundsOptions.outWidth) ? onlyBoundsOptions.outHeight : onlyBoundsOptions.outWidth;
		float uploadImageSize = (MAX_UPLOAD_IMAGE_WIDTH > MAX_UPLOAD_IMAGE_HEIGHT) ? MAX_UPLOAD_IMAGE_WIDTH : MAX_UPLOAD_IMAGE_HEIGHT;
		double ratio = (originalSize > uploadImageSize) ? (originalSize / uploadImageSize) : 1.0;
		double scaleRatio = (originalSize > uploadImageSize) ? (uploadImageSize / originalSize) : 1.0;

		BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
		bitmapOptions.inSampleSize = findBestSampleSizeFromRatio(ratio);
		bitmapOptions.inPurgeable = true;
		bitmapOptions.inPreferredConfig = Bitmap.Config.ARGB_8888;//optional

		Bitmap defaultBitmap = null; //기본
		Bitmap saveBitmap = null; //이미지 회전 (재 저장용)

		//이미지 회전 및 리사이징
		Matrix matrix = new Matrix();
		String imagePath = getRealPathFromURI(context, uri);
		float rotation = rotationForImage(context, uri);

		File file = new File(context.getExternalFilesDir(TEMP_DIRECTORY_NAME), System.currentTimeMillis() + ".jpg");
		OutputStream out = null;
		try {
			input = context.getContentResolver().openInputStream(uri);
			defaultBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeStream(input, null, bitmapOptions), (int) (onlyBoundsOptions.outWidth * scaleRatio),
							(int) (onlyBoundsOptions.outHeight * scaleRatio), true);

			input.close();
			if (ratio != 1.0 || rotation != 0) {
				matrix.preRotate(rotation);
				saveBitmap = Bitmap.createBitmap(defaultBitmap, 0, 0, (int) defaultBitmap.getWidth(), defaultBitmap.getHeight(), matrix, true);
			} else {
				saveBitmap = defaultBitmap;
			}

			file.createNewFile();
			out = new FileOutputStream(file);
			saveBitmap.compress(CompressFormat.JPEG, 100, out);
			saveBitmap.recycle();
			defaultBitmap.recycle();
			out.close();

			imagePath = file.getPath();
		} catch (Exception e) {
//			DLog.e(TAG, "getUploadImageFromUri: "+e.getMessage() );
//			e.printStackTrace();
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
//					e.printStackTrace();
//					DLog.e(TAG, "getUploadImageFromUri: "+e.getMessage() );
				}
			}
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
//					e.printStackTrace();
//					DLog.e(TAG, "getUploadImageFromUri: "+e.getMessage() );
				}
			}
		}

		return imagePath;
	}

	/**
	 * <pre>
	 * 1. 기능 : Uri정보 실제 경로 반환
	 * 2. 처리개요 : 
	 *     - Uri정보 실제 경로 반환 기능을 수행한다.
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
	 * @param context Context 객체
	 * @param contentUri uri 정보
	 * @return 실제 경로
	 */
	public String getRealPathFromURI(Context context, Uri contentUri) {
		String path = null;
		if (contentUri.getScheme().equals("content")) {
			String[] proj = { MediaStore.Images.Media.DATA };
			Cursor cursor = context.getContentResolver().query(contentUri, proj, null, null, null);
			int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
			cursor.moveToFirst();
			path = cursor.getString(column_index);
		} else if (contentUri.getScheme().equals("file")) {
			path = contentUri.getPath();
		}
		return path;
	}

	/**
	 * <pre>
	 * 1. 기능 : 이미지 최적 샘플링 사이즈 반환
	 * 2. 처리개요 : 
	 *     - 이미지 최적 샘플링 사이즈 반환 기능을 수행한다.
	 * 3. 주의사항 : 
	 * ===================================
	 * 4. 작성자/작성일 : 이동식 / 2013. 9. 16.
	 * ===================================
	 * 5. 수정사항
	 * 5.1 요구사항 ID :
	 *     - 수정자/수정일 :
	 *     - 수정사유/내역 :
	 * ===================================
	 * </pre>
	 * 
	 * @param viewWidth
	 *            이미지 뷰 가로크기
	 * @param viewHeight
	 *            이미지 뷰 세로크기
	 * @param imageWidth
	 *            이미지 실제 가로크기
	 * @param imageHeight
	 *            이미지 실제 세로크기
	 * @return 최적 샘플링 사이즈
	 */
	public int findBestSampleSizeFromViewSize(int viewWidth, int viewHeight, int imageWidth, int imageHeight) {

		if ((viewWidth == imageWidth) && (viewHeight == imageHeight)) { return 1; }

		double widthRatio = 1.0;
		double heightRatio = 1.0;

		if (viewWidth < imageWidth) {
			widthRatio = (double) imageWidth / (double) viewWidth;
		}

		if (viewHeight < imageHeight) {
			heightRatio = (double) imageHeight / (double) viewHeight;
		}

		return findBestSampleSizeFromRatio(widthRatio);
	}

	/**
	 * <pre>
	 * 1. 기능 : 이미지 최적 샘플링 값 반환
	 * 2. 처리개요 : 
	 *     - 이미지 최적 샘플링 값 반환 기능을 수행한다.
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
	 * @param ratio 대상 비율
	 * @return 샘플링 값
	 */
	public int findBestSampleSizeFromRatio(double ratio) {
		float value = 1.0f;
		while ((value * 2.0f) <= ratio) {
			value = value * 2.0f;
		}

		return (int) value;
	}

	/**
	 * <pre>
	 * 1. 기능 : 이미지 회전값 반환
	 * 2. 처리개요 : 
	 *     - 이미지 회전값 반환 기능을 수행한다.
	 *     - 저장된 이미지의 회전값을 단말에 화면방향에 맞도록 회전
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
	 * @param context Context 객체
	 * @param uri 이미지 uri 정보
	 * @return 회전값
	 */
	private float rotationForImage(Context context, Uri uri) {
		if (uri.getScheme().equals("content")) {
			String[] projection = { Images.ImageColumns.ORIENTATION };
			Cursor c = context.getContentResolver().query(uri, projection, null, null, null);
			if (c.moveToFirst()) { return c.getInt(0); }
		} else if (uri.getScheme().equals("file")) {
			try {
				ExifInterface exif = new ExifInterface(uri.getPath());
//				int rotation = (int) exifOrientationToDegrees(exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL));
				return exifOrientationToDegrees(exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL));
			} catch (IOException e) {
//				DLog.e(TAG, "rotationForImage: "+e.getMessage() );
			}
		}
		return 0f;
	}

	/**
	 * <pre>
	 * 1. 기능 : 이미지 회전값 반환
	 * 2. 처리개요 : 
	 *     - 이미지 회전값 반환 기능을 수행한다.
	 *     - exif 값을 이용한 회전값 계산
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
	 * @param exifOrientation exif 회전값
	 * @return 이미지 회전값
	 */
	private int exifOrientationToDegrees(int exifOrientation) {

		if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_90) {
			return 90;
		} else if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_180) {
			return 180;
		} else if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_270) { return 270; }
		return 0;
	}

	/**
	 * <pre>
	 * 1. 기능 : 카메라 동작
	 * 2. 처리개요 : 
	 *     - 카메라 동작 기능을 수행한다.
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
	 * @param activity Activity 객체
	 */
	public void startCamera(Activity activity) {
		Intent intent = new Intent();
		intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);

		GregorianCalendar calendar = new GregorianCalendar();
		String filename = String.format("%s%s%s_%s%s%s", calendar.get(Calendar.YEAR), numberFormat.format((calendar.get(Calendar.MONTH) + 1)),
						numberFormat.format(calendar.get(Calendar.DAY_OF_MONTH)), numberFormat.format(calendar.get(Calendar.HOUR)),
						numberFormat.format(calendar.get(Calendar.MINUTE)), numberFormat.format(calendar.get(Calendar.SECOND)));

		File imageRoot = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), "SmartService");
		imageRoot.mkdirs();
		File imageFile = new File(imageRoot, "IMG_" + filename + ".jpg");
		try {
			imageFile.createNewFile();
		} catch (IOException e) {
//			e.printStackTrace();
//			DLog.e(TAG, "startCamera: "+e.getMessage() );
		}
		imageUri = Uri.fromFile(imageFile);
		intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);

		activity.startActivityForResult(intent, TAKE_CAMERA);
	}

	/**
	 * <pre>
	 * 1. 기능 : 갤러리 동작
	 * 2. 처리개요 : 
	 *     - 갤러리 동작 기능을 수행한다.
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
	 * @param activity Activity 객체
	 */
	public void startGallery(Activity activity) {
		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_GET_CONTENT);
		intent.setType("image/*");
		intent.addCategory(Intent.CATEGORY_OPENABLE);

		activity.startActivityForResult(intent, TAKE_GALLERY);
	}

	/**
	 * <pre>
	 * 1. 기능 : onActivityResult 처리
	 * 2. 처리개요 : 
	 *     - onActivityResult 처리 기능을 수행한다.
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
	 * @param context Context 객체
	 * @param requestCode 요청코드
	 * @param resultCode 결과 코드
	 * @param intent Intent 객체
	 */
//	public void onActivityResult(Context context, int requestCode, int resultCode, Intent intent) {
//		if (requestCode == TAKE_CAMERA) {
//			if (imageUri != null) {
//				if (resultCode != Activity.RESULT_OK) {
//					File file = new File(imageUri.getPath());
//					file.delete();
//					return;
//				}
//
//				MediaScannerConnection.scanFile(context, new String[] { imageUri.getPath() }, null, null);
//
//				if (imageUri != null) {
//					getImageFromUri(context, imageUri);
//				}
//			}
//
//		} else if (requestCode == TAKE_GALLERY) {
//			if (intent != null) {
//				if (resultCode != Activity.RESULT_OK) { return; }
//				imageUri = intent.getData();
//				if (imageUri != null) {
//					getImageFromUri(context, imageUri);
//				}
//			}
//		}
//	}

	/**
	 * <pre>
	 * 1. 기능 : 이미지 uri 정보 반환
	 * 2. 처리개요 : 
	 *     - 이미지 uri 정보 반환 기능을 수행한다.
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
	 * @return 이미지 uri
	 */ 
	public Uri getImageUri() {
		return imageUri;

	}

	/**
	 * <pre>
	 * 1. 기능 : 싱글턴 객체 제거
	 * 2. 처리개요 : 
	 *     - 싱글턴 객체 제거 기능을 수행한다.
	 *     - 변수 초기화 수행
	 * 3. 주의사항 : 
	 * ===================================
	 * 4. 작성자/작성일 : 이동식 / 2014. 7. 14.
	 * ===================================
	 * 5. 수정사항
	 * 5.1 요구사항 ID :
	 *     - 수정자/수정일 :
	 *     - 수정사유/내역 :
	 * ===================================
	 * </pre>
	 */
	public void destroy() {
//		if (cacheFileMap != null) {
//			cacheFileMap.clear();
//			cacheFileMap = null;
//		}
//		if (savedFileList != null) {
//			savedFileList.clear();
//			savedFileList = null;
//		}
		imageUri = null;
//		externalDirectory = null;
//		context = null;
		instance = null;
	}
}
