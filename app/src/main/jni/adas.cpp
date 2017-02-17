#include <jni.h>
#include <stdio.h>
#include "vd.h"

#include <android/log.h>
#define LOGI(...) \
  ((void)__android_log_print(ANDROID_LOG_INFO, "Camera2VideoFragment", __VA_ARGS__))


extern "C" {

/**
 *  vehicle detector 전역 객체
 */
vd::VehicleDetector *g_detector = NULL;


/**
 *  vehicle detector 생성 함수
 *  파라미터로 입력되는 preview 영상의 가로, 세로 크기와 회전 여부를 전달
 *  참고!! 안드로이드에서 카메라 preview 이미지 데이타는 항상 landscape 형태의 byte order 이므로,
 *        카메라를 portrait 모드로 세워서 촬영할 경우 rotated 값이 true로 전달되어야 함
 */
JNIEXPORT void JNICALL
Java_example_chatea_servicecamera_TensorflowClassifier_adasInit(JNIEnv *env, jclass type, jint width, jint height, jboolean rotated)
{
    if (!g_detector)
    {
        g_detector = new vd::VehicleDetector(width, height, rotated);
    }
}


/**
 *  vehicle detection 함수
 *  파라미터로 preview 이미지 데이터의 포인터, 차량을 검출할 roi 영역(x, y, width, height)을 전달
 *  검출된 사각 영역들을 배열로 반환
 *  ex) 아래와 같이 두 개의 객체가 검출된 경우,
 *      (x:200, y:200, width:110, height:110), (x:400, y:150, width:70, height:70),
 *      반환 되는 배열은 크기 8(4x2)이고, [200, 200, 110, 110, 400, 150, 70, 70]의 순으로 값이 저장됨
 */
JNIEXPORT jintArray JNICALL
Java_example_chatea_servicecamera_TensorflowClassifier_adasDetect(JNIEnv *env, jclass type, jbyteArray data_, jint roiX, jint roiY, jint roiWidth, jint roiHeight)
{
    jsize length = env->GetArrayLength(data_);
    jbyte *data = env->GetByteArrayElements(data_, 0);
    std::vector<vd::VObject> result;
    g_detector->Detect((unsigned char *)data, vd::Rect(roiX, roiY, roiWidth, roiHeight), result);
//OGI("test"); 이런식으로 찍어보셔~ 됩니다 돼요~ ㅋ
    jint *buffer = new jint[result.size()*4];
    for (int i = 0; i < result.size(); i++)
    {
        buffer[i*4] = result[i].rect.x;
        buffer[i*4+1] = result[i].rect.y;
        buffer[i*4+2] = result[i].rect.width;
        buffer[i*4+3] = result[i].rect.height;
    }

    jintArray array = env->NewIntArray(4*result.size());
    env->SetIntArrayRegion(array, 0, 4*result.size(), buffer);
    env->ReleaseByteArrayElements(data_, data, 0);

    delete [] buffer;
    return array;
}


/**
 *  vehicle detector 제거 함수
 */
JNIEXPORT void JNICALL
Java_com_example_android_camera2video_Camera2VideoFragment_adasShutdown(JNIEnv *env, jclass type)
{
    delete g_detector;
    g_detector = NULL;
}


}
