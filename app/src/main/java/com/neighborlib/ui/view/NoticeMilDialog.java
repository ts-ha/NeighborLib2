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

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.neighborlib.R;
//import com.kt.smartdriving.ui.common.CustomPredictAlertDialog;

/**
 * Created by dg.kim on 2015-05-06.
 */
public class NoticeMilDialog {

  private Dialog mDialog;

  private View.OnClickListener mClickListener;
  private DialogInterface.OnCancelListener mCancelListener;
  private DialogInterface.OnDismissListener mDismissListener;

  public NoticeMilDialog(Context context, int message, Drawable iconId) {
//    View v = LayoutInflater.from(context).inflate(R.layout.dialog_notice_perdict, null);
//    ((ImageView) v.findViewById(R.id.imagePredict)).setImageDrawable(iconId);
//    ((TextView) v.findViewById(R.id.mil)).setText(message);
//    v.findViewById(R.id.positive).setOnClickListener(new View.OnClickListener() {
//      @Override
//      public void onClick(View v) {
//        mDialog.dismiss();
//        if (mClickListener != null) {
//          mClickListener.onClick(v);
//        }
//      }
//    });
//
//    mDialog = new CustomPredictAlertDialog.Builder(context, R.style.InnoCar_CustomDialog)
////    mDialog = new CustomPredictAlertDialog.Builder(context, R.style.AppTheme_Dialog_Guide)
//        .setTitle(R.string.dialog_title_notice_mil, true)
//        .setView(v)
//        .create();

    mDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
      @Override
      public void onCancel(DialogInterface dialog) {
        if (mCancelListener != null) {
          mCancelListener.onCancel(mDialog);
        }
      }
    });
    mDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
      @Override
      public void onDismiss(DialogInterface dialog) {
        if (mDismissListener != null) {
          mDismissListener.onDismiss(mDialog);
        }
      }
    });
    mDialog.setCancelable(false);

    mDialog.getWindow().setAttributes(createLayoutParams());
  }

  public NoticeMilDialog(Context context) {
//    View v = LayoutInflater.from(context).inflate(R.layout.dialog_notice_mil, null);
//    v.findViewById(R.id.positive).setOnClickListener(new View.OnClickListener() {
//      @Override
//      public void onClick(View v) {
//        mDialog.dismiss();
//        if (mClickListener != null) {
//          mClickListener.onClick(v);
//        }
//      }
//    });

//    mDialog = new CustomPredictAlertDialog.Builder(context, R.style.InnoCar_CustomDialog)
//        .setTitle(R.string.dialog_title_notice_mil, true)
//        .setView(v)
//        .create();
    mDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
      @Override
      public void onCancel(DialogInterface dialog) {
        if (mCancelListener != null) {
          mCancelListener.onCancel(mDialog);
        }
      }
    });
    mDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
      @Override
      public void onDismiss(DialogInterface dialog) {
        if (mDismissListener != null) {
          mDismissListener.onDismiss(mDialog);
        }
      }
    });
    mDialog.setCancelable(false);

    mDialog.getWindow().setAttributes(createLayoutParams());
  }

  private WindowManager.LayoutParams createLayoutParams() {
    return new WindowManager.LayoutParams(
        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 0, 0,
        WindowManager.LayoutParams.TYPE_SYSTEM_ERROR,
        WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
            | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
            | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
        PixelFormat.RGBA_8888);
  }

  public void setClickListener(View.OnClickListener listener) {
    mClickListener = listener;
  }

  public void setCancelListener(DialogInterface.OnCancelListener listener) {
    mCancelListener = listener;
  }

  public void setDismissListener(DialogInterface.OnDismissListener listener) {
    mDismissListener = listener;
  }

  public void show() {
    mDialog.show();
  }

  public void dismiss() {
    mDialog.dismiss();
  }
}
