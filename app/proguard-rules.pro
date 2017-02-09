# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in E:\workspace\android\sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

###############################################################################
# Google API Client ProGuard Configuration file
#
# See http://proguard.sourceforge.net/index.html#manual/usage.html

# Needed to keep generic types and @Key annotations accessed via reflection



-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
-dontskipnonpubliclibraryclassmembers
-dontpreverify
-verbose

-assumenosideeffects class android.util.Log {
    public static *** d(...);
    public static *** v(...);
    public static *** i(...);
  }

-keepclassmembers class * {
  @com.google.api.client.util.Key <fields>;
}

# Needed by google-http-client-android when linking against an older platform version

-dontwarn com.google.api.client.extensions.android.**

# Needed by google-api-client-android when linking against an older platform version

-dontwarn com.google.api.client.googleapis.extensions.android.**


-keep class * extends android.webkit.WebViewClient { *; }
-keep class * extends android.webkit.WebChromeClient { *; }

# Warnings to be removed.
-dontwarn org.apache.**
-dontwarn org.springframework.**
-dontwarn org.codehaus.jackson.**
-dontwarn javax.servlet.http.**
-dontwarn com.ning.http.**
-dontwarn org.jboss.netty.**



###############################################################################
# log4j
-dontwarn org.slf4j.**
-dontnote org.apache.log4j.**
-dontwarn org.apache.log4j.**

###############################################################################



-renamesourcefileattribute ProGuard
-keepattributes Signature,LineNumberTable,RuntimeVisibleAnnotations,AnnotationDefault





