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
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import com.neighborlib.R;


import org.apache.log4j.Logger;

/**
 * Created by dg.kim on 2015-03-02.
 */
public class FloatingHeadWindow extends BaseFloatingHeadWindow {

    private static final int HEAD = 0x00000001;

    public static final int MODE_LOGO = 0x00000001;
    public static final int MODE_NORMAL = 0x00000002;
    public static final int MODE_MIL = 0x00000004;
    public static final int MODE_DRIVING = 0x00000008;
    public static final int MODE_TTS = 0x00000010;
    public static final int MODE_REJECT_CALL = 0x00000020;
    public static final int MODE_SPEAKERPHONE = 0x00000040;
    public static final int MODE_LOADING = 0x00000080;

    private FloatingHeadView mHeadView;
    private LogoView mLogoView;
    private NormalView mNormalView;
    private DrivingView mDrivingView;
    private TtsView mTtsView;
    private RejectCallView mRejectCallView;
    private SpeakerphoneView mSpeakerphoneView;
    private MilView mMilView;
    private LoadingView mLoadingView;
    private int mModeMask;

    private Rect mViewport;
    private boolean mHasPendingSettle;
    private boolean mPreventSettle;
    private boolean mPreventDrag;
    private boolean mPreventClick;
    private boolean mPreventDoubleClick;
    private boolean mPreventLongClick;

    private final LocalSavedState mLocalSavedState;
    private final Callbacks mCallbacks;

    public FloatingHeadWindow(Context context, Callbacks callbacks) {
        super(context, HEAD, new DecelerateInterpolator());
        mLocalSavedState = new LocalSavedState(context);
        mCallbacks = callbacks;
    }

    @Override
    protected View onCreateView(LayoutInflater inflater) {
        mHeadView = (FloatingHeadView) inflater.inflate(R.layout.floating_head, null, false);
        mHeadView.setCallbacks(new HeadViewCallback());
        mViewport = new Rect();
        updateViewport(mViewport);

        mLogoView = (LogoView) mHeadView.findViewById(R.id.logo);
        mNormalView = (NormalView) mHeadView.findViewById(R.id.normal);
        mDrivingView = (DrivingView) mHeadView.findViewById(R.id.driving);
        mTtsView = (TtsView) mHeadView.findViewById(R.id.tts);
        mRejectCallView = (RejectCallView) mHeadView.findViewById(R.id.incoming_call);
        mSpeakerphoneView = (SpeakerphoneView) mHeadView.findViewById(R.id.speakerphone);
        mMilView = (MilView) mHeadView.findViewById(R.id.mil);
        mLoadingView = (LoadingView) mHeadView.findViewById(R.id.loading);

        return mHeadView;
    }

    @Override
    public int getWidth() {
        return getPixelSize(R.dimen.floating_head_width);
    }

    @Override
    public int getHeight() {
        return getPixelSize(R.dimen.floating_head_height);
    }

    private void updateViewport(Rect viewport) {
        DisplayMetrics dm = getContext().getResources().getDisplayMetrics();
        int horizontalOffset = getPixelSize(R.dimen.floating_head_hide_horizontal_offset);
        viewport.left = -horizontalOffset;
        viewport.right = dm.widthPixels - getWidth() + horizontalOffset;
        int topOffset = getPixelSize(R.dimen.floating_head_hide_top_offset);
        viewport.top = topOffset;
        int bottomOffset = getPixelSize(R.dimen.floating_head_hide_bottom_offset);
        viewport.bottom = dm.heightPixels - getHeight() - bottomOffset;
        Logger.getLogger(TAG).debug("dm.widthPixels : " + dm.widthPixels);
        Logger.getLogger(TAG).debug("프로팅 버튼 getWidth : " + getWidth());
        Logger.getLogger(TAG).debug("프로팅 버튼 시작 값 : " + horizontalOffset);

    }

    @Override
    protected void onDestroyedView() {
        mHeadView = null;
    }

    public void onOrientationChanged(int orientation, Point... positions) {
        Rect oldViewport = new Rect(mViewport);
        updateViewport(mViewport);
        Rect newViewport = new Rect(mViewport);
        Point position = new Point(getX(), getY());
        calcNewPosition(oldViewport, newViewport, position);

        moveTo(position);
        settle();

        if (positions != null) {
            for (Point pos : positions) {
                if (pos != null) {
                    calcNewPosition(oldViewport, newViewport, pos);
                }
            }
        }
    }

    @Override
    public void onOrientationChanged(int orientation) {
        Rect oldViewport = new Rect(mViewport);
        updateViewport(mViewport);
        Rect newViewport = new Rect(mViewport);
        Point position = new Point(getX(), getY());
        calcNewPosition(oldViewport, newViewport, position);

        moveTo(position);
        settle();
    }

    public void setVisibility(int visibility) {
        mHeadView.setVisibility(visibility);
    }

    private void calcNewPosition(Rect oldViewport, Rect newViewport, Point position) {
        int oldX = position.x;
        int oldY = position.y;
        float hr = (float) (oldX - oldViewport.left) / oldViewport.width();
        float vr = (float) (oldY - oldViewport.top) / oldViewport.height();
        position.x = newViewport.left + (int) (newViewport.width() * hr);
        position.y = newViewport.top + (int) (newViewport.height() * vr);
    }

    @Override
    protected void onAnimationEnd() {
        Logger.getLogger(TAG).debug("animation end");
        if (mHasPendingSettle) {
            mHasPendingSettle = false;
            settle();
        } else {
            savePosition();
            mCallbacks.onSettled();
        }
    }

    public void setSettleEnabled(boolean enabled) {
        mPreventSettle = !enabled;
    }

    public void setDragEnabled(boolean enabled) {
        mPreventDrag = !enabled;
    }

    public void setClickEnabled(boolean enabled) {
        mPreventClick = !enabled;
    }

    public void setDoubleClickEnabled(boolean enabled) {
        mPreventDoubleClick = !enabled;
    }

    public void setLongClickEnabled(boolean enabled) {
        mPreventLongClick = !enabled;
    }

    @Override
    public void moveTo(int x, int y) {
        super.moveTo(x, y);

        mCallbacks.onPositionChanged();

        savePosition();
    }

    @Override
    public void moveBy(int dx, int dy) {
        super.moveBy(dx, dy);

        mCallbacks.onPositionChanged();

        savePosition();
    }

    public boolean isOnParkingPosition() {
        int x = getX();
        if (isOnLeftSide()) {
            return (x + getHorizontalMagOffset() < 0);
        } else {
            DisplayMetrics dm = getContext().getResources().getDisplayMetrics();
            return (x + getWidth() - getHorizontalMagOffset() > dm.widthPixels);
        }
    }

    public void moveToParkingPosition() {
        Point pos = new Point(getX(), getY());
        if (isOnLeftSide()) {
            pos.x = 0 - (getWidth() / 2);
        } else {
            DisplayMetrics dm = getContext().getResources().getDisplayMetrics();
            pos.x = dm.widthPixels - (getWidth() / 2);
        }
        animateTo(pos);
    }

    public Point getActivePosition() {
        Point pos = new Point(getX(), getY());
        if (isOnLeftSide()) {
            pos.x = 0;
        } else {
            DisplayMetrics dm = getContext().getResources().getDisplayMetrics();
            pos.x = dm.widthPixels - getWidth();
        }
        return pos;
    }

    private boolean isOnLeftSide() {
        int x = getX();
        DisplayMetrics dm = getContext().getResources().getDisplayMetrics();
        return (x < (dm.widthPixels - getWidth()) / 2);
    }

    private void settle() {
        if (mPreventSettle) return;

        if (!isOnAnimating()) {
            if (mHeadView != null) {
                mHeadView.removeCallbacks(mClearPendingSettleRunnable);
            }
            Logger.getLogger(TAG).debug("settle...");
            int x = magHorizontal(getX());
            int y = magVertical(getY());
            animateTo(x, y);
        } else {
            Logger.getLogger(TAG).debug("request pending settle");
            if (mHeadView != null) {
                mHasPendingSettle = true;
                mHeadView.postDelayed(mClearPendingSettleRunnable, 1000);
            } else {
                cancelAnimation();
                onAnimationEnd();
            }
        }
    }

    private Runnable mClearPendingSettleRunnable = new Runnable() {
        @Override
        public void run() {
            Logger.getLogger(TAG).debug("clear pending settle");
            cancelAnimation();
            onAnimationEnd();
        }
    };

    private int getHorizontalMagOffset() {
        return getWidth() / 10;
    }

    public int magHorizontal(int x) {
        DisplayMetrics dm = getContext().getResources().getDisplayMetrics();
        if (x + getHorizontalMagOffset() < 0) {
            x = mViewport.left;
        } else if (x + getWidth() - getHorizontalMagOffset() > dm.widthPixels) {
            x = mViewport.right;
        }
        return x;
    }

    public int magVertical(int y) {
        if (y < mViewport.top) {
            y = mViewport.top;
        } else if (y > mViewport.bottom) {
            y = mViewport.bottom;
        }
        return y;
    }

    public Point getLastPosition() {
        Configuration configuration = getContext().getResources().getConfiguration();

        int orientation = mLocalSavedState.getHeadOrientation();
        if (orientation != Configuration.ORIENTATION_UNDEFINED) {
            Point position = mLocalSavedState.getHeadPosition();
            if (orientation != configuration.orientation) {
                Rect oldViewport = mLocalSavedState.getHeadViewport();
                Rect newViewport = new Rect();
                updateViewport(newViewport);
                calcNewPosition(oldViewport, newViewport, position);
            }
            Logger.getLogger(TAG).trace("get last position: " + position);
            return position;
        } else {
            return getInitialPosition();
        }
    }

    private Point getInitialPosition() {
        int x = 0;
        int y = mViewport.bottom;
        Point position = new Point(x, y);
        Logger.getLogger(TAG).debug("get initial position: " + position);
        return position;
    }

    private void savePosition() {
        mLocalSavedState.putHeadViewport(mViewport);
        Configuration configuration = getContext().getResources().getConfiguration();
        mLocalSavedState.putHeadOrientation(configuration.orientation);
        Point position = new Point(getX(), getY());
        Logger.getLogger(TAG).trace("save last position: " + position);
        mLocalSavedState.putHeadPosition(position);
    }

    public void playSoundEffect(int effect) {
        if (mHeadView != null) {
            mHeadView.playSoundEffect(effect);
        }
    }

    public void performHapticFeedback(int feedfack) {
        if (mHeadView != null) {
            mHeadView.performHapticFeedback(feedfack);
        }
    }

    public int getModeMask() {
        return mModeMask;
    }

    public void setMode(int mode) {
        mModeMask |= mode;
//        Logger.getLogger(TAG).trace("set mode=" + Integer.toHexString(mode)
//                + " | mask=" + Integer.toHexString(mModeMask));
        Logger.getLogger(TAG).trace("mode : " + mode);
        Logger.getLogger(TAG).trace("mModeMask : " + mModeMask);
        if (mode == MODE_LOADING) {
            mLoadingView.setVisibility(View.VISIBLE);
        } else if (mode == MODE_SPEAKERPHONE) {
            mSpeakerphoneView.setVisibility(View.VISIBLE);
        } else if (mode == MODE_REJECT_CALL) {
            mRejectCallView.setVisibility(View.VISIBLE);
        } else if (mode == MODE_TTS) {
            mTtsView.cancelAnimation();
            mTtsView.setVisibility(View.VISIBLE);
        } else if (mode == MODE_DRIVING) {
            mDrivingView.setVisibility(View.VISIBLE);
        } else if (mode == MODE_MIL) {
            mMilView.setVisibility(View.VISIBLE);
        } else if (mode == MODE_NORMAL) {
            mNormalView.setVisibility(View.VISIBLE);
        } else if (mode == MODE_LOGO) {
            mLogoView.setVisibility(View.VISIBLE);
        }
    }

    public void clearMode(int mode) {
        mModeMask &= ~mode;
        Logger.getLogger(TAG).trace("clear mode=" + Integer.toHexString(mode)
                + " | mask=" + Integer.toHexString(mModeMask));

        if (mode == MODE_LOADING) {
            mLoadingView.setVisibility(View.GONE);
        } else if (mode == MODE_SPEAKERPHONE) {
            mSpeakerphoneView.setVisibility(View.GONE);
        } else if (mode == MODE_REJECT_CALL) {
            mRejectCallView.setVisibility(View.GONE);
        } else if (mode == MODE_TTS) {
            mTtsView.setVisibility(View.GONE);
        } else if (mode == MODE_DRIVING) {
            mDrivingView.setVisibility(View.GONE);
        } else if (mode == MODE_MIL) {
            mMilView.setVisibility(View.GONE);
        } else if (mode == MODE_NORMAL) {
            mNormalView.setVisibility(View.GONE);
        } else if (mode == MODE_LOGO) {
            mLogoView.setVisibility(View.GONE);
        }
    }

//  public void onObdDataChanged(ObdData data) {
//    mNormalView.onObdDataChanged(data);
//  }

    public void setEcoSafePoint(int point) {
        mDrivingView.setEcoSafePoint(point);
    }

    public void setSpeakerphoneOn(boolean on) {
        mSpeakerphoneView.setSpeakerphoneOn(on);
    }

    public void startTtsAnimation() {
        mTtsView.startAnimation();
    }

    public void cancelTtsAnimation() {
        mTtsView.cancelAnimation();
    }

    private final class HeadViewCallback implements FloatingHeadView.Callbacks {

        @Override
        public void onDragStart(int x, int y) {
            //Logger.getLogger(TAG).debug("drag start");
            cancelAnimation();
        }

        @Override
        public void onDrag(int dx, int dy) {
            //Logger.getLogger(TAG).debug("dragging...");
            if (!mPreventDrag) {
                moveBy(dx, dy);
            }
        }

        @Override
        public void onDragEnd(int x, int y) {
            //Logger.getLogger(TAG).debug("drag end");
            settle();
        }

        @Override
        public void onClick() {
            if (!mPreventClick) {
                mCallbacks.onClick();
            }
        }

        @Override
        public void onDoubleClick() {
            if (!mPreventDoubleClick) {
                mCallbacks.onDoubleClick();
            }
        }

        @Override
        public void onLongClick() {
            if (mPreventLongClick) {
                mCallbacks.onLongClock();
            }
        }
    }

    private static class LocalSavedState {

        private static final String HEAD_VIEWPORT_LEFT = "HEAD_VIEWPORT_LEFT";
        private static final String HEAD_VIEWPORT_TOP = "HEAD_VIEWPORT_TOP";
        private static final String HEAD_VIEWPORT_RIGHT = "HEAD_VIEWPORT_RIGHT";
        private static final String HEAD_VIEWPORT_BOTTOM = "HEAD_VIEWPORT_BOTTOM";
        private static final String HEAD_ORIENTATION = "HEAD_ORIENTATION";
        private static final String HEAD_POSITION_X = "HEAD_POSITION_X";
        private static final String HEAD_POSITION_Y = "HEAD_POSITION_Y";

        protected final SharedPreferences mPrefs;

        private LocalSavedState(Context context) {
            mPrefs = context.getSharedPreferences("head_window.prefs", Context.MODE_PRIVATE);
        }

        private void putHeadViewport(Rect viewport) {
            SharedPreferences.Editor editor = mPrefs.edit();
            editor.putInt(HEAD_VIEWPORT_LEFT, viewport.left);
            editor.putInt(HEAD_VIEWPORT_TOP, viewport.top);
            editor.putInt(HEAD_VIEWPORT_RIGHT, viewport.right);
            editor.putInt(HEAD_VIEWPORT_BOTTOM, viewport.bottom);
            editor.apply();
        }

        private Rect getHeadViewport() {
            Rect viewport = new Rect();
            viewport.left = mPrefs.getInt(HEAD_VIEWPORT_LEFT, 0);
            viewport.top = mPrefs.getInt(HEAD_VIEWPORT_TOP, 0);
            viewport.right = mPrefs.getInt(HEAD_VIEWPORT_RIGHT, 0);
            viewport.bottom = mPrefs.getInt(HEAD_VIEWPORT_BOTTOM, 0);
            return viewport;
        }

        private void putHeadOrientation(int orientation) {
            SharedPreferences.Editor editor = mPrefs.edit();
            editor.putInt(HEAD_ORIENTATION, orientation);
            editor.apply();
        }

        private int getHeadOrientation() {
            return mPrefs.getInt(HEAD_ORIENTATION, Configuration.ORIENTATION_UNDEFINED);
        }

        private void putHeadPosition(Point position) {
            if (position == null) return;

            SharedPreferences.Editor editor = mPrefs.edit();
            editor.putInt(HEAD_POSITION_X, position.x);
            editor.putInt(HEAD_POSITION_Y, position.y);
            editor.apply();
        }

        private Point getHeadPosition() {
            Point position = new Point();
            position.x = mPrefs.getInt(HEAD_POSITION_X, 0);
            position.y = mPrefs.getInt(HEAD_POSITION_Y, 0);
            return position;
        }
    }

    public interface Callbacks {
        void onClick();

        void onDoubleClick();

        void onLongClock();

        void onPositionChanged();

        void onSettled();
    }
}
