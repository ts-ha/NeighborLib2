package com.neighborlib.ui;

import android.os.Bundle;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SoundEffectConstants;
import android.view.View;

import com.android.volley.Request;
import com.google.android.gms.analytics.Tracker;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.crash.FirebaseCrash;
import com.neighborlib.R;
import com.neighborlib.common.APIDef;
import com.neighborlib.common.MessageDef;
import com.neighborlib.common.ParameterDef;
import com.neighborlib.network.NetworkManager;
import com.neighborlib.ui.SmartServiceMessageHandler.SmartServiceHandlerInterface;
import com.neighborlib.ui.view.FloatingHeadWindow;

import org.apache.log4j.Logger;


public class MainActivity extends AppCompatActivity implements SmartServiceHandlerInterface {

    static final String TAG = MainActivity.class.getSimpleName();
    private SmartServiceMessageHandler handler = new SmartServiceMessageHandler(this);
    private String searchFileUrl;
    private String searchPath;
    private Tracker mTracker;
    private FirebaseAnalytics mFirebaseAnalytics;
    private FloatingHeadCallbacks mHeadCallbacks;
    private static final int OVERLAY_PERMISSION = 12;
    private FloatingHeadWindow mHeadWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
//        Logger.getLogger(TAG).info("info");
//        Logger.getLogger(TAG).debug("debug");
//        Logger.getLogger(TAG).warn("warn");
//        Logger.getLogger(TAG).error("error");

//        Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getPackageName()));
//        startActivityForResult(intent, OVERLAY_PERMISSION);

//        requestAppMenuSearch();

        mHeadCallbacks = new FloatingHeadCallbacks();
        mHeadWindow = new FloatingHeadWindow(this, mHeadCallbacks);
        mHeadWindow.setMode(FloatingHeadWindow.MODE_LOGO);
        mHeadWindow.show();

        mHeadWindow.moveTo(mHeadWindow.getLastPosition());

        FirebaseCrash.logcat(Log.ERROR, TAG, "NPE caught");
        FirebaseCrash.report(new Exception("My first Android non-fatal error"));
    }

    @Override
    protected void onDestroy() {
        destroyWindows();
        super.onDestroy();

    }

    private void destroyWindows() {
        if (mHeadWindow != null) {
            mHeadWindow.destroy();
            mHeadWindow = null;
        }
    }

    /**
     * <pre>
     * 1. 기능 : 앱 메뉴 리스트 요청
     * 2. 처리개요 :
     *     - 앱 메뉴 리스트 요청 기능을 수행한다.
     * 3. 주의사항 :
     * ===================================
     * 4. 작성자/작성일 : 이동식
     * ===================================
     * 5. 수정사항
     * 5.1 요구사항 ID :
     *     - 수정자/수정일 : 김용희 / 2016. 10. 25
     *     - 수정사유/내역 : Tower 버전 API 변경
     * ===================================
     * </pre>
     */
    private void requestAppMenuSearch() {
        String url = APIDef.VALUE_TYPE_APP_MENU_SEARCH_TOWER;
        String parameter = "?" + ParameterDef.PARAMETER_VERSION_NO + "=" + ParameterDef.VALUE_MENU_VERSION_TOWER;
//        UICommonUtil.getInstance(this).showProgressDialog(this);

        NetworkManager.getInstance(this).requestJsonObject(url + parameter, Request.Method.POST, handler, MessageDef.API_APP_MENU_SEARCH_SUCCESS, MessageDef.API_APP_MENU_SEARCH_FAIL);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void handleServiceMessage(Message message) {
        int messageWhat = message.what;
        if (messageWhat == MessageDef.API_APP_MENU_SEARCH_SUCCESS) {
            try {
                Logger.getLogger(TAG).debug("(String) message.obj : " + message.obj);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private class FloatingHeadCallbacks implements FloatingHeadWindow.Callbacks {
        @Override
        public void onClick() {
            if (mHeadWindow == null) return;

            mHeadWindow.playSoundEffect(SoundEffectConstants.CLICK);

            int modeMask = mHeadWindow.getModeMask();
            if ((modeMask & FloatingHeadWindow.MODE_SPEAKERPHONE) != 0) {
                Logger.getLogger(TAG).info("speaker phone click");
                onSpeakerphoneClick();
            } else if ((modeMask & FloatingHeadWindow.MODE_REJECT_CALL) != 0) {
                Logger.getLogger(TAG).info("reject call click");
                onRejectCallClick();
            } else if ((modeMask & FloatingHeadWindow.MODE_TTS) != 0) {
                Logger.getLogger(TAG).info("tts click");
                onTtsClick();
            } else if ((modeMask & FloatingHeadWindow.MODE_DRIVING) != 0) {
                Logger.getLogger(TAG).info("driving click");
                onDrivingClick();
            } else if ((modeMask & FloatingHeadWindow.MODE_NORMAL) != 0) {
                Logger.getLogger(TAG).info("normal click");
                onNormalClick();
            } else if ((modeMask & FloatingHeadWindow.MODE_LOGO) != 0) {
                Logger.getLogger(TAG).info("logo click");
                onLogoClick();
            }
        }

        private void onSpeakerphoneClick() {
//            boolean on = !mCallHandler.isSpeakerphoneOn();
//            mCallHandler.setSpeakerphoneOn(on);
//            if (mHeadWindow != null) {
//                mHeadWindow.setSpeakerphoneOn(on);

        }

        private void onRejectCallClick() {
//            mCallHandler.endCall();
//            if (mIncomingNumber != null) {
//                String message = mSettingsStore.getRejectCallMessage();
//                if (!TextUtils.isEmpty(message)) {
//                    mSmsHandler.sendSms(mIncomingNumber, message);
//                    if (BuildConfig.DEBUG) {
//                        Toast.makeText(getApplicationContext(), "reject call message: " + message, Toast.LENGTH_LONG).show();
//                    }
//                }
//            }
//            if (mHeadWindow != null) {
//                mHeadWindow.clearMode(FloatingHeadWindow.MODE_REJECT_CALL);
//            }
        }

        private void onTtsClick() {
//            if (mSmsText != null) {
//                mHandler.removeCallbacks(mClearSmsRunnable);
//                if (mTtsStart) {
//                    mTtsHandler.stopSpeak();
//                    mTtsStart = false;
//                } else {
//                    mTtsHandler.speak(mSmsText);
//                }
//            }
        }

        private void onDrivingClick() {
            onLogoClick();
        }

        private void onNormalClick() {
            onLogoClick();
        }

        private void onLogoClick() {
//      Logger.getLogger(TAG).debug("mExpanded : " + mExpanded);
//      if (mExpanded && !mWaitExpandedCallback) {
//        finishFloatingActivity();
//        onCollapsed();
//      } else if (!mExpanded && !mWaitExpandedCallback) {
//            mExpanded = true;
//            startSmartDrivingActivity();
//      }
        }

        @Override
        public void onDoubleClick() {
            mHeadWindow.playSoundEffect(SoundEffectConstants.CLICK);
            Logger.getLogger(TAG).debug("onDoubleClick : ");
            // switch parking/active position
//        if (mHeadWindow.isOnParkingPosition()) {
//            if (mActivePosition == null) {
//                mActivePosition = mHeadWindow.getActivePosition();
//            }
//            mHeadWindow.animateTo(mActivePosition);
//        } else {
//            mActivePosition = new Point(mHeadWindow.getX(), mHeadWindow.getY());
//            mHeadWindow.moveToParkingPosition();
//        }
        }

        @Override
        public void onLongClock() {
            //Toast.makeText(getApplicationContext(), "Long click", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onPositionChanged() {
        }

        @Override
        public void onSettled() {
        }
    }
}
