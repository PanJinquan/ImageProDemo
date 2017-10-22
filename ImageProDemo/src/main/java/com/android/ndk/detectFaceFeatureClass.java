package com.android.ndk;

import android.graphics.Bitmap;
import android.util.Log;

import org.opencv.android.Utils;
import org.opencv.core.Mat;

/**
 * Created by panjq1 on 2017/9/4.
 */

public class detectFaceFeatureClass {
    private static final String    TAG = "OCVSample::";
    public Bitmap autoCutOut(Bitmap origImage, int[] faceRect, int[] landmarks) {
        int w=origImage.getWidth();
        int h=origImage.getHeight();
        Mat origMat = new Mat();
        Mat maskMat = new Mat();
        Utils.bitmapToMat(origImage, origMat);
        ImageBlur(origMat.getNativeObjAddr(), maskMat.getNativeObjAddr());
        Bitmap maskImage = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Utils.matToBitmap(maskMat, maskImage);
        Log.i(TAG, "ImageBlur called successfully");
        return maskImage;
    }
    public native void ImageBlur(long matAddrSrcImage, long matAddrDestImage);
}


