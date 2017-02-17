#pragma once

class xRect;
#include <android/log.h>
#define  LOG_TAG    "NDK_TEST"
#define  LOGI(...)  __android_log_print(ANDROID_LOG_INFO,LOG_TAG,__VA_ARGS__)
/**
* �����ڵ�
*/
typedef enum
{
	STATUS_OK,
	STATUS_ERROR,
	STATUS_ERROR_FILE_OPEN,
	STATUS_ERROR_FEATURE_EXTRACTION,
} Status;


/**
	* @brief �ڵ��� ���⿡ �ʿ��� �޸� �Ҵ�
	*/
Status initialize(int imageWidth, int imageHeight);


/**
	* @brief �ڵ��� ����
	*/
int carDetection(unsigned char *grayImage, int srcW, int srcH, xRect* objects);

/**
* @brief Image resize(Bilinear interpolation)
*/
void resize(const unsigned char* image, int width, int height, unsigned char* resizeimage, int resizeWidth, int resizeHeight);

int cascadeDetector(unsigned char* image, int width, int height, int orgImageWidth, int orgImageHeight, int maxNObjects, xRect* objects);

void objAccumulate(int nObjects, xRect* object, bool *m_bObjectValid);
int objAccumulate(int nObjects, xRect* object);


/**
	* @brief Modified census transform
	*/
void image2MCT(unsigned char* image, int width, int height, unsigned short* destData);

/**
	* @brief Binary HoG
	*/
void image2BHoG(unsigned char* image, int width, int height, unsigned short* destData);

/**
	* @brief �� ���� Rect�� Over�� �ִ��� ������ üũ
	*/
bool getOverlap(xRect rc1, xRect rc2);

/**
	*/
float arctan2(float y, float x);


void imresize(const unsigned char *src, int width, int height, int step, int resized_width, int resized_height, unsigned char *dst);

//VD_RECT getOverlap(VD_RECT rc1, VD_RECT rc2);


void imrotate(const unsigned char *src, int width, int height, int step, unsigned char *dst);