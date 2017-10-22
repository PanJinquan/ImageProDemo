LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)
OPENCV_LIB_TYPE :=STATIC
ifdef OPENCV_ANDROID_SDK
  ifneq ("","$(wildcard $(OPENCV_ANDROID_SDK)/OpenCV.mk)")
    include ${OPENCV_ANDROID_SDK}/OpenCV.mk
  else
    include ${OPENCV_ANDROID_SDK}/sdk/native/jni/OpenCV.mk
  endif
else
  include D:\OpenCV-android-sdk-310\OpenCV-android-sdk\sdk\native\jni\OpenCV.mk
endif

LOCAL_SRC_FILES := ImageProcess.cpp
LOCAL_MODULE    := mixed_sample

LOCAL_LDLIBS +=  -llog -ldl

include $(BUILD_SHARED_LIBRARY)
