#include "vd_impl.h"
#include "CarDetection.h"
#include <cassert>


namespace vd
{

    VehicleDetector::VehicleDetector(int frameWidth, int frameHeight, bool rotated)
        : impl(new _VehicleDetector(frameWidth, frameHeight, rotated))
    {
    }

    VehicleDetector::~VehicleDetector()
    {
        delete (_VehicleDetector*)impl;
    }

    void VehicleDetector::Detect(const unsigned char *frame, const Rect &roi, std::vector<VObject> &result)
    {
        ((_VehicleDetector*)impl)->Detect(frame, roi, result);
    }

    _VehicleDetector::_VehicleDetector(int frameWidth, int frameHeight, bool rotated)
    {
        m_frameWidth = frameWidth;
        m_frameHeight = frameHeight;
        m_rotated = rotated;
        m_detectedResult = new xRect[MAX_N_DETECTED];
    }

    _VehicleDetector::~_VehicleDetector()
    {
        delete[] m_detectedResult;
    }

    void _VehicleDetector::Detect(const unsigned char *frame, const Rect &roi, std::vector<VObject> &result)
    {
        unsigned char resized_roi_image[MAX_ROI_WIDTH*MAX_ROI_HEIGHT];
        double rescale_ratio = MIN(MAX_ROI_WIDTH / (double)roi.width, MAX_ROI_HEIGHT / (double)roi.height);
        Rect resized_roi = { (int)(roi.x * rescale_ratio), (int)(roi.y * rescale_ratio), (int)(roi.width * rescale_ratio), (int)(roi.height * rescale_ratio) };
        const unsigned char *roi_image;

        if (m_rotated)
        {
            unsigned char *rotated = new unsigned char[m_frameHeight*m_frameWidth];
            int p = 0;
            for (int j = 0; j < m_frameWidth; j++) {
                for (int i = m_frameHeight - 1; i >= 0; i--) {
                    rotated[p] = frame[i*m_frameWidth + j];
                    p++;
                }
            }
            roi_image = rotated + m_frameHeight * roi.y + roi.x;
            imresize(roi_image, roi.width, roi.height, m_frameHeight, resized_roi.width, resized_roi.height, resized_roi_image);
            delete[] rotated;
        }
        else
        {
            roi_image = frame + m_frameWidth * roi.y + roi.x;
            imresize(roi_image, roi.width, roi.height, m_frameWidth, resized_roi.width, resized_roi.height, resized_roi_image);
        }


        int n = carDetection(resized_roi_image, resized_roi.width, resized_roi.height, m_detectedResult);
        
        result.resize(n);
        for (int i = 0; i < n; i++)
        {
            result[i].id = 0;
            result[i].type = OBJECT_TYPE::VD_OBJECT_UNKNOWN;
            result[i].rect = Rect((m_detectedResult[i].left / rescale_ratio) + roi.x,
                (m_detectedResult[i].top / rescale_ratio) + roi.y,
                m_detectedResult[i].Width() / rescale_ratio,
                m_detectedResult[i].Height() / rescale_ratio);
        }
    }

}