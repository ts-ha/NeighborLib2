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

package com.neighborlib.ui;

import android.appwidget.AppWidgetProviderInfo;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.util.DisplayMetrics;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by dg.kim on 2015-02-27.
 */
public class Utils {

  private static final String TAG = "Utils";

  public static int dp2px(Resources resources, float dp) {
    DisplayMetrics dm = resources.getDisplayMetrics();
    return (int) (dp * dm.density);
  }

    /*public static void moveTaskToFront(Context context, String className) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        int taskId = retrieveTaskId(am, context.getPackageName(), className);
        Logger.getLogger(TAG).error(className + ": taskId=" + taskId);
        if (taskId != -1) {
            am.moveTaskToFront(taskId, 0);
        }
    }

    public static int retrieveTaskId(ActivityManager am, String packageName, String className) {
        List<ActivityManager.RecentTaskInfo> tasks = am.getRecentTasks(Integer.MAX_VALUE, ActivityManager.RECENT_IGNORE_UNAVAILABLE);
        for (ActivityManager.RecentTaskInfo task : tasks) {
            Logger.getLogger(TAG).error("#A# " + task.baseIntent.getComponent());
            if (packageName.equals(task.baseIntent.getComponent().getPackageName())
                    && className.equals(task.baseIntent.getComponent().getClassName())) {
                return task.persistentId;
            }
        }
        return -1;
    }*/

  // Geni music
  public static final String GENIE_MUSIC_PACKAGE_NAME = "com.ktmusic.geniemusic";
  // 4x1 appwidget class name
  public static final String GENIE_APPWIDGET_CLASS_NAME = "com.ktmusic.geniemusic.widget.MediaWidgetProvider41";

  public static AppWidgetProviderInfo getGenieAppWidget(List<AppWidgetProviderInfo> widgets) {
    for (AppWidgetProviderInfo widget : widgets) {
      if (widget.provider != null
          && GENIE_APPWIDGET_CLASS_NAME.equals(widget.provider.getClassName())) {
        return widget;
      }
    }
    return null;
  }

  public static boolean isGenieMusicInstalled(Context context) {
    PackageManager pm = context.getPackageManager();
    Intent launch = pm.getLaunchIntentForPackage(GENIE_MUSIC_PACKAGE_NAME);
    return (launch != null);
  }

  public static void playGenieMusic(Context context) {
    String action = GENIE_MUSIC_PACKAGE_NAME + ".VOICE_PLAY_LINK";
    Intent intent = new Intent(action);
    intent.putExtra("type", "82");
    intent.putExtra("target", "play");
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1) {
      intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
    }
//    context.sendBroadcast(intent);
  }

  public static void stopGenieMusic(Context context) {
    String action = GENIE_MUSIC_PACKAGE_NAME + ".VOICE_PLAY_LINK";
    Intent intent = new Intent(action);
    intent.putExtra("type", "82");
    intent.putExtra("target", "stop");
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1) {
      intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
    }
//    context.sendBroadcast(intent);
  }

  public static void launchGenieMusicMyList(Context context) {
    Intent intent = new Intent(Intent.ACTION_VIEW);
    intent.setData(Uri.parse("igeniesns://detail?landingtype=110&landingtarget=1"));
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    try {
      context.startActivity(intent);
    } catch (Exception e) {
      Logger.getLogger(TAG).warn("failed to launch genie music:my list");
    }
  }

  public static void launchGenieMusicTop100(Context context) {
    Intent intent = new Intent(Intent.ACTION_VIEW);
    intent.setData(Uri.parse("igeniesns://detail?landingtype=110&landingtarget=2"));
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    try {
      context.startActivity(intent);
    } catch (Exception e) {
      Logger.getLogger(TAG).warn("failed to launch genie music:top100");
    }
  }

  public static void launchGenieMusicToday(Context context) {
    Intent intent = new Intent(Intent.ACTION_VIEW);
    intent.setData(Uri.parse("igeniesns://detail?landingtype=110&landingtarget=3"));
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    try {
      context.startActivity(intent);
    } catch (Exception e) {
      Logger.getLogger(TAG).warn("failed to launch genie music:today music");
    }
  }

  public static void launchGenieMusicMyAlbum(Context context) {
    Intent intent = new Intent(Intent.ACTION_VIEW);
    intent.setData(Uri.parse("igeniesns://detail?landingtype=110&landingtarget=4"));
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    try {
      context.startActivity(intent);
    } catch (Exception e) {
      Logger.getLogger(TAG).warn("failed to launch genie music:my album");
    }
  }

  public static void installGenieMusic(Context context) {
    Intent intent = new Intent(Intent.ACTION_VIEW);
    intent.setData(Uri.parse("market://details?id=" + Utils.GENIE_MUSIC_PACKAGE_NAME));
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    try {
//      context.startActivity(intent);
    } catch (Exception e) {
      Logger.getLogger(TAG).error("cannot start google play app");
    }
  }

  // Olleh navi
  public static final String OLLEH_NAVI_PACKAGE_NAME = "kt.navi";
  public static final String ACTION_OLLEH_NAVIGATION = "kt.navi.OLLEH_NAVIGATION";
  public static final String EXTRA_EXTERN_LINK_TYPE = "EXTERN_LINK_TYPE";
  public static final String EXTRA_CALLER_PACKAGE_NAME = "CALLER_PACKAGE_NAME";
  public static final int EXTERN_LINK_APP_KILL = 15;

  public static boolean isOllehNaviInstalled(Context context) {
    PackageManager pm = context.getPackageManager();
    Intent launch = pm.getLaunchIntentForPackage(OLLEH_NAVI_PACKAGE_NAME);
    return (launch != null);
  }

  public static boolean launchOllehNavi(Context context) {
    PackageManager pm = context.getPackageManager();
    Intent navigation = pm.getLaunchIntentForPackage(OLLEH_NAVI_PACKAGE_NAME);
    try {
      navigation.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
      context.startActivity(navigation);
      return true;
    } catch (Exception e) {
      Logger.getLogger(TAG).error("cannot start olleh navi");
    }
    return false;
  }

  public static void finishOllehNavi(Context context) {
    Intent intent = new Intent(ACTION_OLLEH_NAVIGATION);
    intent.putExtra(EXTRA_CALLER_PACKAGE_NAME, context.getPackageName());
    intent.putExtra(EXTRA_EXTERN_LINK_TYPE, EXTERN_LINK_APP_KILL);
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1) {
      intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
    }
//    context.sendBroadcast(intent);
  }

  public static void installOllehNavi(Context context, boolean ktMember) {
    if (ktMember) {
      if (installOllehNaviViaOllehMarket(context)) {
        return;
      }
    }
    installOllehNaviViaGoogleMarket(context);
  }

  private static boolean installOllehNaviViaOllehMarket(Context context) {
    Intent intent = new Intent(Intent.ACTION_VIEW);
    intent.setType("vnd.kt.olleh.storefront/detail.kt.olleh.storefront");
    intent.putExtra("CONTENT_TYPE", "APPLICATION");
    intent.putExtra("P_TYPE", "c");
    //intent.putExtra("N_ID", "A009004");
    intent.putExtra("P_ID", "51200003003963");
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    try {
      context.startActivity(intent);
      return true;
    } catch (Exception e) {
      Logger.getLogger(TAG).setLevel(Level.OFF);
      Logger.getLogger(TAG).error("cannot start olleh market app");
      return false;
    }
  }

  private static boolean installOllehNaviViaGoogleMarket(Context context) {
    Intent intent = new Intent(Intent.ACTION_VIEW);
    intent.setData(Uri.parse("market://details?id=" + Utils.OLLEH_NAVI_PACKAGE_NAME));
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    try {
      context.startActivity(intent);
      return true;
    } catch (Exception e) {
      Logger.getLogger(TAG).setLevel(Level.OFF);
      Logger.getLogger(TAG).error("cannot start google play app");
      return false;
    }
  }

  private static boolean contains(List<ResolveInfo> apps, ResolveInfo item) {
    for (ResolveInfo app : apps) {
      if (app.activityInfo.packageName != null
          && app.activityInfo.packageName.equals(item.activityInfo.packageName)
          && app.activityInfo.name != null
          && app.activityInfo.name.equals(item.activityInfo.name)) {
        return true;
      }
    }
    return false;
  }

  private static boolean contains(List<ResolveInfo> apps, String packageName) {
    for (ResolveInfo app : apps) {
      if (app.activityInfo.packageName != null
          && app.activityInfo.packageName.equals(packageName)) {
        return true;
      }
    }
    return false;
  }



  private static void sortAppList(Context context, List<ResolveInfo> appList) {
    final PackageManager pm = context.getPackageManager();
    Collections.sort(appList, new Comparator<ResolveInfo>() {
      public int compare(ResolveInfo lhs, ResolveInfo rhs) {
        String lhsLabel = lhs.activityInfo.loadLabel(pm).toString();
        String rhsLabel = rhs.activityInfo.loadLabel(pm).toString();
        char lhsFirstChar = lhsLabel.charAt(0);
        char rhsFirstChar = rhsLabel.charAt(0);
        if (isAscii(lhsFirstChar) && !isAscii(rhsFirstChar)) {
          return 1;
        } else if (!isAscii(lhsFirstChar) && isAscii(rhsFirstChar)) {
          return -1;
        } else {
          return lhsLabel.compareToIgnoreCase(rhsLabel);
        }
      }
    });
  }

  private static boolean isAscii(char c) {
    return (0 < c && c < 128);
  }
}
