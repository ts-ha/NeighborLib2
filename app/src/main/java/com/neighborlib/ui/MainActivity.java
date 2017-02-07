package com.neighborlib.ui;

import android.os.Bundle;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.android.volley.Request;
import com.neighborlib.R;
import com.neighborlib.common.APIDef;
import com.neighborlib.common.MessageDef;
import com.neighborlib.common.ParameterDef;
import com.neighborlib.network.NetworkManager;
import com.neighborlib.ui.SmartServiceMessageHandler.SmartServiceHandlerInterface;

import org.apache.log4j.Logger;

public class MainActivity extends AppCompatActivity implements SmartServiceHandlerInterface {

    static final String TAG = MainActivity.class.getSimpleName();
    private SmartServiceMessageHandler handler = new SmartServiceMessageHandler(this);

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
        Logger.getLogger(TAG).info("info");
        Logger.getLogger(TAG).debug("debug");
        Logger.getLogger(TAG).warn("warn");
        Logger.getLogger(TAG).error("error");


        requestAppMenuSearch();
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
                Logger.getLogger(TAG).debug("(String) message.obj : " + (String) message.obj);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
