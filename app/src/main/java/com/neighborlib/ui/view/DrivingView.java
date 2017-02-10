/*
 * Copyright (c) 2015. Pokevian Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.neighborlib.ui.view;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.neighborlib.R;

/**
 * Created by dg.kim on 2015-04-15.
 */
public class DrivingView extends FrameLayout {

    private View mEcoSafePointPane;
    private TextView mEcoSafePointText;
    private ImageView mEcoSafePointCalcImage;

    public DrivingView(Context context) {
        super(context);
    }

    public DrivingView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DrivingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        mEcoSafePointPane = findViewById(R.id.eco_safe_point_pane);
        mEcoSafePointText = (TextView) findViewById(R.id.eco_safe_point);
        mEcoSafePointCalcImage = (ImageView) findViewById(R.id.eco_safe_point_calc);
        AnimationDrawable drawable = (AnimationDrawable) mEcoSafePointCalcImage.getDrawable();
        drawable.start();
    }

    public void setEcoSafePoint(int point) {
        if (mEcoSafePointText != null) {
            mEcoSafePointText.setText(String.valueOf(point));
            mEcoSafePointPane.setVisibility(View.VISIBLE);
            mEcoSafePointCalcImage.setVisibility(View.GONE);
        }
    }
}
