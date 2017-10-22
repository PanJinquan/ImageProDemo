#include <opencv2/core/core.hpp>
#include <opencv2/imgproc/imgproc.hpp>
#include <opencv2/features2d/features2d.hpp>
#include "com_android_ndk_detectFaceFeatureClass.h"
using namespace cv;
JNIEXPORT void JNICALL Java_com_android_ndk_detectFaceFeatureClass_ImageBlur(JNIEnv *, jobject, jlong matAddrSrcImage, jlong matAddrDestImage)
{
    Mat& srcImage  = *(Mat*)matAddrSrcImage;
    Mat& destImage = *(Mat*)matAddrDestImage;
    blur(srcImage,destImage,Size(20,20));

}
