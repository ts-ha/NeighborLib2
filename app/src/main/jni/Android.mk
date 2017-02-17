LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)
LOCAL_CPPFLAGS  += -std=c++11
LOCAL_MODULE    := tensorflow_demo
MY_CPP_LIST := $(wildcard $(LOCAL_PATH)/*.cpp)
LOCAL_SRC_FILES := $(MY_CPP_LIST:$(LOCAL_PATH)/%=%)

#LOCAL_SRC_FILES := $(PREBUILT_SHARED_LIBRARY)
LOCAL_LDLIBS    += -llog
include $(BUILD_SHARED_LIBRARY)

