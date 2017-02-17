#pragma once

#include "vd.h"
#include "model.h"


const float SCALE_RATIO = 0.850000;
const float THRESHOLD_FACTOR = 1.100000;
const int MAX_ROI_WIDTH = 360;
const int MAX_ROI_HEIGHT = 360;
const int MAX_ROI_SIZE = 360 * 360;
const int MAX_N_DETECTED = 1024;

#ifndef MIN
#  define MIN(a,b)  ((a) > (b) ? (b) : (a))
#endif

#ifndef MAX
#  define MAX(a,b)  ((a) < (b) ? (b) : (a))
#endif


class xRect
{
public:
    int left, top, right, bottom;

    xRect() { left = top = right = bottom = 0; }
    xRect(int left, int top, int right, int bottom) {
        this->left = left;		this->top = top;
        this->right = right;	this->bottom = bottom;
    }
    xRect(const xRect& rc) {
        left = rc.left;		top = rc.top;
        right = rc.right;	bottom = rc.bottom;
    }
    xRect& operator=(const xRect& rc) {
        left = rc.left;		top = rc.top;
        right = rc.right;	bottom = rc.bottom;
        return *this;
    }

    int Width() { return right - left; }
    int Height() { return bottom - top; }
};


namespace vd
{
    class _VehicleDetector
    {
    public:
        _VehicleDetector(int frameWidth, int frameHeight, bool rotated);
        ~_VehicleDetector();
        void Detect(const unsigned char *frame, const Rect &roi, std::vector<VObject> &result);

    private:
        int m_frameWidth;
        int m_frameHeight;
        bool m_rotated;
        xRect *m_detectedResult;
    };

}