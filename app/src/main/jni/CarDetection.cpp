
#include "vd_impl.h"
#include "CarDetection.h"

#include <cassert>
#include <cmath>
#include <cstring>
#include <cstdio>


//#define N_STAGES			4
//#define WINDOW_WIDTH		30
//#define WINDOW_HEIGHT		30
//#define PATTERN_SIZE		511
//#define ANGLE_BIN			9
//#define BLOCK_SIZE			8
//#define SCALE_RATIO         0.85
//#define THRESHOLD_FACTOR    1.1
//
//unsigned int *classifier;               // [number of Stages][number of weak classifiers for each stages * 3(type, x, y)], elements of each classifiers
//float *weight;                          // [number of Stages][number of weak classifiers for each stages * 511], weight value of each patterns of each classifiers
//unsigned int nWeaks[N_STAGES];          // [number of Stages], number of weak classifiers for each stages
//float thresholdStage[N_STAGES];         // [number of Stages], threshold for each stages
//int orientation_map[512][512];   // [512][512], orientation index for each (dx, dy) (-255~255, -255~255)
//
//float arctan2(float y, float x)
//{
//    //float coeff_1 = M_PI / 4;
//    const float coeff_1 = 0.785398163397448309616;
//    const float coeff_2 = 3 * coeff_1;
//
//    float abs_y = fabsf(y) + 1e-10;      // kludge to prevent 0/0 condition
//    float angle;
//
//    if (x >= 0)
//    {
//        double r = (x - abs_y) / (x + abs_y);
//        angle = coeff_1 - coeff_1 * r;
//    }
//    else
//    {
//        float r = (x + abs_y) / (abs_y - x);
//        angle = coeff_2 - coeff_1 * r;
//    }
//
//    if (y < 0)
//        return(-angle);     // negate if in quad III or IV
//    else
//        return(angle);
//}
//
//Status initialize(int imageWidth, int imageHeight)
//{
//	char path[1024];
//    const int N_FEATURE_TYPES = 2;
//    const int ID_SIZE = WINDOW_WIDTH * WINDOW_HEIGHT * N_FEATURE_TYPES;
//    static double weightTable[N_STAGES][ID_SIZE][PATTERN_SIZE]; // [nStages][30*30*2][511], weight
//    static int weakClassifier[N_STAGES][ID_SIZE];               // [nStages][30*30*2(nWeak of each stage,...)], feature type * 30*30 + y * 30 + x
//    static double threshold[N_STAGES];
//
//	for (int i = 0; i < N_STAGES; i++)
//	{
//		sprintf(path, "./data/MCTAdaboost_%02d_%02d.dat", 0, i);
//		FILE* fp = fopen(path, "rb");
//		if (fp == NULL)
//		{
//			//release();
//			return STATUS_ERROR_FILE_OPEN;
//		}
//
//        fread(&weightTable[i], sizeof(double), ID_SIZE*PATTERN_SIZE, fp);
//        fread(&weakClassifier[i], sizeof(int), ID_SIZE, fp);
//		fread(&nWeaks[i], sizeof(int), 1, fp);
//        fread(&threshold[i], sizeof(double), 1, fp);
//
//		fclose(fp);
//	}
//
//    int totalWeaks = 0;
//    for (int i = 0; i < N_STAGES; i++)
//        totalWeaks += nWeaks[i];
//
//    classifier = new unsigned int[totalWeaks * 3];
//    weight = new float[totalWeaks * PATTERN_SIZE];
//
//    unsigned int *pc = classifier;
//    float *pw = weight;
//    for (int i = 0; i < N_STAGES; i++)
//	{
//        for (int j = 0; j < nWeaks[i]; j++)
//		{
//            pc[j * 3] = weakClassifier[i][j] / (WINDOW_WIDTH * WINDOW_HEIGHT);  // feature type
//            pc[j * 3 + 1] = (weakClassifier[i][j] % (WINDOW_WIDTH * WINDOW_HEIGHT)) / WINDOW_WIDTH; // y
//            pc[j * 3 + 2] = (weakClassifier[i][j] % (WINDOW_WIDTH * WINDOW_HEIGHT)) % WINDOW_WIDTH; // x
//			for (int p = 0; p < PATTERN_SIZE; p++)
//                pw[j * PATTERN_SIZE + p] = weightTable[i][weakClassifier[i][j]][p];
//		}
//        thresholdStage[i] = (float)threshold[i];
//        pc += nWeaks[i] * 3;
//        pw += nWeaks[i] * PATTERN_SIZE;
//	}
//
//    const double PI = 3.14159265358979323846;
//    for (int i = -255; i < 256; i++)
//    {
//        for (int j = -255; j < 256; j++)
//        {
//            float angle = arctan2(i, j);
//            angle += PI;
//
//            int orientation = int(angle*ANGLE_BIN / (2 * PI));
//
//            if (ANGLE_BIN <= orientation)
//                orientation = ANGLE_BIN - 1;
//            orientation_map[i + 255][j + 255] = orientation;
//        }
//    }
//
//	return STATUS_OK;
//}

int carDetection(unsigned char* grayimage, int width, int height, xRect* objects)
{
	int nDetected = 0;
    const int MAX_OBJECTS_PER_SCALE = 100;

    unsigned char resizeImgBuffer[MAX_ROI_SIZE];

	float ratio = 1.0;
    int resizedWidth = width;
    int resizedHeight = height;
	while (true)
	{
        resize(grayimage, width, height, resizeImgBuffer, resizedWidth, resizedHeight);
        nDetected += cascadeDetector(resizeImgBuffer, resizedWidth, resizedHeight, width, height, MAX_OBJECTS_PER_SCALE, objects + nDetected);
        ratio = ratio * SCALE_RATIO;
        resizedHeight = (int)(height*ratio + 0.5);
        resizedWidth = (int)(width*ratio + 0.5);
        if (resizedWidth < WINDOW_HEIGHT || resizedHeight < WINDOW_HEIGHT)
            break;
    }
    const int ID_DETECT_LIMIT = 1600;
    bool m_bObjectValid[ID_DETECT_LIMIT];

    //nDetected = objAccumulate(nDetected, objects);

    objAccumulate(nDetected, objects, m_bObjectValid);
	int nFinalCount = 0;
	for (int i = 0; i < nDetected; i++)
	{
		if (m_bObjectValid[i])
		{
			objects[nFinalCount++] = objects[i];
		}
	}
	nDetected = nFinalCount;

	return nDetected;
}

int cascadeDetector(unsigned char* image, int width, int height, int orgImageWidth, int orgImageHeight, int maxNObjects, xRect* objects)
{

//LOGI("JNI log : %d",orgImageWidth);
	int object_cnt = 0;
    float ratiox = (float)orgImageWidth / (float)width;
    float ratioy = (float)orgImageHeight / (float)height;
    int PyramidSize = width * height;
    static unsigned short feature[MAX_ROI_SIZE * 2]; // [height*width*2]
    
    image2BHoG(image, width, height, feature);

    image2MCT(image, width, height, feature + width*height);

    for (int y = 0; y < height - WINDOW_HEIGHT; y+=2)
	{
        for (int x = 0; x < width - WINDOW_WIDTH; x++)
		{
            int ltP = y * width + x;
            int c = 0;
            const unsigned char *pc = CLASSIFIER;
            const float *pw = WEIGHT;
            for (; c < N_STAGES; c++)
			{
                float h_value = 0.0;
                for (int t = 0; t < N_WEAKS[c]; t++)
				{
                    int tt = t * 3;
                    int global_p = PyramidSize * pc[tt] + (ltP + pc[tt + 1] * width + pc[tt + 2]);
                    h_value += pw[t*PATTERN_SIZE + feature[global_p]];
                }
                pc += N_WEAKS[c] * 3;
                pw += N_WEAKS[c] * PATTERN_SIZE;
                if (h_value >= STAGE_THRESHOLD[c] * THRESHOLD_FACTOR)
                    break;

			}
            if (c == N_STAGES && object_cnt < maxNObjects)
                objects[object_cnt++] = xRect(x * ratiox, y * ratioy, (x + WINDOW_WIDTH) * ratiox, (y + WINDOW_HEIGHT) * ratioy);
		}
	}

	return object_cnt;
}

int objAccumulate(int nObjects, xRect* object)
{
    const int ID_DETECT_LIMIT = 1600;
    int overlap[ID_DETECT_LIMIT];
    int nFinal = 0;

    for (int i = 0; i < nObjects; i++)
        overlap[i] = i;

    // checking overlaps
    for (int i = 0; i < nObjects; i++)
    {
        if (overlap[i] != i) continue;  // already checked
        for (int j = i + 1; j < nObjects; j++)
        {
            if (overlap[j] != j) continue;  // already checked
            if (getOverlap(object[i], object[j]))
            {
                overlap[j] = i;
            }
        }
    }

    // merging to average rectangle
    for (int i = 0; i < nObjects; i++)
    {
        if (overlap[i] != i) continue;
        int nOverlap = 1;
        int left = object[i].left;
        int top = object[i].top;
        int right = object[i].right;
        int bottom = object[i].bottom;
        for (int j = i + 1; j < nObjects; j++)
        {
            if (overlap[j] == i)
            {
                nOverlap++;
                left += object[j].left;
                top += object[j].top;
                right += object[j].right;
                bottom += object[j].bottom;
            }
        }
        if (nOverlap < 11) continue;
        object[nFinal].left = int((left / (float)nOverlap) + 0.5f);
        object[nFinal].top = int((top / (float)nOverlap) + 0.5f);
        object[nFinal].right = int((right / (float)nOverlap) + 0.5f);
        object[nFinal].bottom = int((bottom / (float)nOverlap) + 0.5f);
        overlap[nFinal++] = nOverlap;
    }

    // sort

    return nFinal;
}

void objAccumulate(int nObjects, xRect* object, bool *m_bObjectValid)
{
    const int ID_DETECT_LIMIT = 1600;
    int Accumulated[ID_DETECT_LIMIT];
	int SortedAccumulated[ID_DETECT_LIMIT];

    for (int i = 0; i < nObjects; i++)
	{
		m_bObjectValid[i] = true;

		Accumulated[i] = 1;
		SortedAccumulated[i] = 1;
	}

	// Calculate Accumulated
    for (int i = 0; i < nObjects; i++)
	{
        for (int j = i+1; j < nObjects; j++)
		{
            if (getOverlap(object[i], object[j]))
            {
                Accumulated[i]++;
                Accumulated[j]++;
            }
		}
	}

	xRect _validObj[1000];
	int _validObjCount = 0;

	while (true)
	{
		// Find Maximum Accumulation
		int maximumIndex = 0;
		int maximumAccum = 0;

        for (int i = 0; i < nObjects; i++)
		{
			if (!m_bObjectValid[i])
				continue;

			if (maximumAccum < Accumulated[i])
			{
				maximumAccum = Accumulated[i];
				maximumIndex = i;
			}
		}

		bool validBox = (maximumAccum >= 20) ? true : false;

		if (validBox)
		{
			double left = 0;
			double right = 0;
			double top = 0;
			double bottom = 0;

			int accCount = 0;

            for (int j = 0; j < nObjects; j++)
			{
				if (getOverlap(object[maximumIndex], object[j]) == false) 
					continue;

				left += object[j].left;
				right += object[j].right;
				top += object[j].top;
				bottom += object[j].bottom;

				// 
				m_bObjectValid[j] = false;

                for (int i = 0; i < nObjects; i++)
				{
					if (m_bObjectValid[i])
						if (getOverlap(object[j], object[i])) 
							Accumulated[i]--;
				}

				accCount++;
			}

			_validObj[_validObjCount].left = left / accCount;
			_validObj[_validObjCount].right = right / accCount;
			_validObj[_validObjCount].top = top / accCount;
			_validObj[_validObjCount++].bottom = bottom / accCount;
		}
		else
			break;

		bool finishFlag = true;

        for (int i = 0; i < nObjects; i++)
		{
			if (m_bObjectValid[i])
				finishFlag = false;
		}

		if (finishFlag)
			break;
	}

    for (int i = 0; i < nObjects; i++)
		m_bObjectValid[i] = false;

	for (int i = 0; i < _validObjCount; i++)
	{
		m_bObjectValid[i] = true;
		object[i] = _validObj[i];
	}
}

bool getOverlap(xRect rc1, xRect rc2)
{
    bool boIntersect = false;

    boIntersect = (rc1.left <= rc2.right) && (rc2.left <= rc1.right)
        && (rc1.top <= rc2.bottom) && (rc2.top <= rc1.bottom);

    if (boIntersect)
    {
        xRect _intersectedRect = xRect(
            MAX(rc1.left, rc2.left),
            MAX(rc1.top, rc2.top),
            MIN(rc1.right, rc2.right),
            MIN(rc1.bottom, rc2.bottom));

        int area1 = rc1.Width() * rc1.Height();
        int area2 = rc2.Width() * rc2.Height();
        int areaInter = _intersectedRect.Width() * _intersectedRect.Height();

        double pascalMeasure = areaInter / (double)(area1 + area2 - areaInter + 1e-5);

        if (pascalMeasure > 0.1)
            return true;
    }

    return false;
}

//VD_RECT getOverlap(VD_RECT rc1, VD_RECT rc2)
//{
//    VD_RECT r = {0, 0, 0, 0};
//
//    int left = MAX(rc1.x, rc2.x);
//    int top = MAX(rc1.y, rc2.y);
//    int right = MIN(rc1.x + rc1.width, rc2.x + rc2.width);
//    int bottom = MIN(rc1.y + rc1.height, rc2.y + rc2.height);
//    if (left < right && top < bottom)
//    {
//        int width = right - left;
//        int height = bottom - top;
//        int area1 = rc1.width * rc1.height;
//        int area2 = rc2.width * rc2.height;
//        int areaInter = width * height;
//        double pascalMeasure = areaInter / (double)(area1 + area2 - areaInter + 1e-5);
//        if (pascalMeasure > 0.3)
//        {
//            r.x = left;
//            r.y = top;
//            r.width = width;
//            r.height = height;
//        }
//        return r;
//    }
//    return r;
//}

void resize(const unsigned char* image, int width, int height, unsigned char* resizeimage, int resizeWidth, int resizeHeight)
{
	/* < 10 */
	int udtx, udty;
	/* < 10 */
	int uda_11;
	/* < 10 */
	int uda_22;
	int i, j;
	int utx, uty;
	int locgray4 = 0;
	int ucgray1 = 0, ucgray2 = 0, ucgray3 = 0, ucgray4 = 0;
	unsigned int aa, ab, ac, ad;

	const unsigned char *pucsrcp = 0;
	unsigned char *pucdstp = resizeimage;

	/* Affine transformation */

	uda_11 = (width << 10) / resizeWidth;
	uda_22 = (height << 10) / resizeHeight;

	for (j = 0; j < resizeHeight; j++) {
		for (i = 0; i < resizeWidth; i++) {
			locgray4 = 0;

			udtx = uda_11 * i;
			udty = uda_22 * j;

			utx = udtx >> 10;
			uty = udty >> 10;
			udtx -= (utx << 10);
			udty -= (uty << 10);
			pucsrcp = image + (uty * width) + utx;

			ucgray1 = *pucsrcp;

			if (i >= resizeWidth - 1) {
				ucgray2 = *pucsrcp;
				udtx = 1 << 10;
			}
			else {
				ucgray2 = *(pucsrcp + 1);
				locgray4 += 1;
			}

			if (j >= resizeHeight - 1) {
				ucgray3 = *pucsrcp;
				udty = 1 << 10;
			}
			else {
				ucgray3 = *(pucsrcp + width);
				locgray4 += width;
			}

			ucgray4 = *(pucsrcp + locgray4);

			aa = ucgray1 << 20;
			ab = ((ucgray2 - ucgray1) * (udtx << 10));
			ac = ((ucgray3 - ucgray1) * (udty << 10));
			ad = (ucgray1 + ucgray4 - ucgray3 - ucgray2) * udtx * udty;

			*(pucdstp + i) = (unsigned char)((aa + ab + ac + ad) >> 20);
		}

		pucdstp += resizeWidth;
	}
}

void image2MCT(unsigned char* image, int width, int height, unsigned short* destData)
{
	int x, y, w = width, h = height - 1, src_size = width*height;
	int m1, m2, m3, m4, m5, m6, m7, m8, m9;
	int mu1, mu2, mu3, mu4, mu5, mu6, mu7, mu8, mu9;
	int data_mean;
	unsigned short data_mct;
	unsigned char *pt1, *pt2 = image + width, *pt3;

	memset(destData, 0, sizeof(unsigned short)*src_size);
	unsigned short *pmct = destData + width + 1;

	pt1 = pt2 - width;
	pt3 = pt2 + width;
    for (y = 1; y < h; y++) {
		m1 = *pt1++;
		m4 = *pt2++;
		m7 = *pt3++;
		m2 = *pt1++;
		m5 = *pt2++;
		m8 = *pt3++;
		for (x = 2; x < w; x++) {
			m3 = *pt1++;
			m6 = *pt2++;
			m9 = *pt3++;
			data_mean = ((m1 + m2 + m3 + m4 + m5 + m6 + m7 + m8 + m9));

			mu1 = data_mean - ((m1 << 3) + m1);
			mu2 = data_mean - ((m2 << 3) + m2);
			mu3 = data_mean - ((m3 << 3) + m3);
			mu4 = data_mean - ((m4 << 3) + m4);
			mu5 = data_mean - ((m5 << 3) + m5);
			mu6 = data_mean - ((m6 << 3) + m6);
			mu7 = data_mean - ((m7 << 3) + m7);
			mu8 = data_mean - ((m8 << 3) + m8);
			mu9 = data_mean - ((m9 << 3) + m9);

			data_mct = ((mu1 >> 31) & 1) + (((mu2 >> 31) & 1) << 1) + (((mu3 >> 31) & 1) << 2) +
				(((mu4 >> 31) & 1) << 3) + (((mu5 >> 31) & 1) << 4) + (((mu6 >> 31) & 1) << 5) +
				(((mu7 >> 31) & 1) << 6) + (((mu8 >> 31) & 1) << 7) + (((mu9 >> 31) & 1) << 8);

			m1 = m2;
			m4 = m5;
			m7 = m8;
			m2 = m3;
			m5 = m6;
			m8 = m9;

			*pmct++ = data_mct;
		}
		pmct++;
		pmct++;
	}
}

void image2BHoG(unsigned char* image, int width, int height, unsigned short* destData)
{
    const int MAX_SIZE = 720 * 700; // 
    static int o_data[MAX_SIZE];
    static float m_data[MAX_SIZE];
	memset(o_data, 0, sizeof(int)*width*height);
	memset(m_data, 0, sizeof(float)*width*height);

    for (int y = 1; y < height - 1; y++)
	{
        for (int x = 1; x < width - 1; x++)
		{
            int dx = image[y*width + x + 1] - image[y*width + x - 1];
            int dy = image[(y + 1)*width + x] - image[(y - 1)*width + x];
            m_data[y*width + x] = dx * dx + dy * dy;
            o_data[y*width + x] = ORIENTATION[dy + 255][dx + 255];
		}
	}

	memset(destData, 0, sizeof(unsigned short)*width*height);
	unsigned short bhog_v;

    for (int y = 0; y < height - BLOCK_SIZE; y++)
    {
        float hist[9];
        float hist_mean;
        int x = 0;
        memset(hist, 0, sizeof(float)*N_ANGLE_BIN);
        for (int by = y; by < y + BLOCK_SIZE; by++)
        {
            //for (int bx = x; bx < x + BLOCK_SIZE; bx++)
            for (int bx = x; bx < x + BLOCK_SIZE - 1; bx++)
            {
                int p = by*width + bx;
                hist[o_data[p]] += m_data[p];
            }
        }
        for (int x = 0; x < width - BLOCK_SIZE; x++)
        {
            for (int by = y; by < y + BLOCK_SIZE; by++)
            {
                int p = by*width + x;
                hist[o_data[p]] -= m_data[p];
                p = by*width + x + BLOCK_SIZE - 1;
                hist[o_data[p]] += m_data[p];
            }
            hist_mean = (hist[0] + hist[1] + hist[2] + hist[3] +
                hist[4] + hist[5] + hist[6] + hist[7] + hist[8]) / 9;
            bhog_v = ((hist_mean < hist[0]) ? 1 : 0) |
                ((hist_mean < hist[1]) ? 2 : 0) |
                ((hist_mean < hist[2]) ? 4 : 0) |
                ((hist_mean < hist[3]) ? 8 : 0) |
                ((hist_mean < hist[4]) ? 16 : 0) |
                ((hist_mean < hist[5]) ? 32 : 0) |
                ((hist_mean < hist[6]) ? 64 : 0) |
                ((hist_mean < hist[7]) ? 128 : 0) |
                ((hist_mean < hist[8]) ? 256 : 0);
            destData[y * width + x] = bhog_v;
        }
    }

    //static float hist[MAX_SIZE][9];
    //memset(hist, 0, sizeof(float)*width*height * 9);
    //for (int y = 1; y < height; y++)
    //{
    //    for (int x = 1; x < width; x++)
    //    {
    //        int p = y*width + x;
    //        int u = p - width;
    //        int l = p - 1;
    //        int ul = u - 1;
    //        for (int k = 0; k < 9; k++)
    //            hist[p][k] = hist[u][k] + hist[l][k] - hist[ul][k];
    //        hist[p][o_data[p]] += m_data[p];
    //    }
    //}
    //for (int y = 0; y < height - BLOCK_SIZE; y++)
    //{
    //    for (int x = 0; x < width - BLOCK_SIZE; x++)
    //    {
    //        int p = (y + BLOCK_SIZE - 1)*width + x + BLOCK_SIZE - 1;
    //        int u = p - width*BLOCK_SIZE;
    //        int l = p - BLOCK_SIZE;
    //        int ul = u - BLOCK_SIZE;
    //        float h[9];
    //        float hMean = 0;
    //        for (int k = 0; k < 9; k++)
    //        {
    //            if (x == 0 && y == 0)
    //                h[k] = hist[p][k];
    //            else if (x == 0)
    //                h[k] = hist[p][k] - hist[u][k];
    //            else if (y == 0)
    //                h[k] = hist[p][k] - hist[l][k];
    //            else
    //                h[k] = hist[p][k] - hist[u][k] - hist[l][k] + hist[ul][k];
    //            hMean += h[k];
    //        }
    //        bhog_v = ((hMean < h[0]) ? 1 : 0) |
    //            ((hMean < h[1]) ? 2 : 0) |
    //            ((hMean < h[2]) ? 4 : 0) |
    //            ((hMean < h[3]) ? 8 : 0) |
    //            ((hMean < h[4]) ? 16 : 0) |
    //            ((hMean < h[5]) ? 32 : 0) |
    //            ((hMean < h[6]) ? 64 : 0) |
    //            ((hMean < h[7]) ? 128 : 0) |
    //            ((hMean < h[8]) ? 256 : 0);
    //        destData[y * width + x] = bhog_v;
    //    }
    //}

    //for (int y = 0; y < height - BLOCK_SIZE; y++)
    //{
    //    for (int x = 0; x < width - BLOCK_SIZE; x++)
    //    {
    //        float hist_mean;
    //        float hist[ANGLE_BIN] = {0};
    //        for (int by = y; by < y + BLOCK_SIZE; by++)
    //        {
    //            for (int bx = x; bx < x + BLOCK_SIZE; bx++)
    //            {
    //                int p = by*width + bx;
    //                hist[o_data[p]] += m_data[p];
    //            }
    //        }
    //        hist_mean = (hist[0] + hist[1] + hist[2] + hist[3] +
    //            hist[4] + hist[5] + hist[6] + hist[7] + hist[8]) / 9;
    //        bhog_v = ((hist_mean < hist[0]) ? 1 : 0) |
    //            ((hist_mean < hist[1]) ? 2 : 0) |
    //            ((hist_mean < hist[2]) ? 4 : 0) |
    //            ((hist_mean < hist[3]) ? 8 : 0) |
    //            ((hist_mean < hist[4]) ? 16 : 0) |
    //            ((hist_mean < hist[5]) ? 32 : 0) |
    //            ((hist_mean < hist[6]) ? 64 : 0) |
    //            ((hist_mean < hist[7]) ? 128 : 0) |
    //            ((hist_mean < hist[8]) ? 256 : 0);
    //        destData[y * width + x] = bhog_v;
    //    }
    //}

	//delete[] o_data;
	//delete[] m_data;
}

void imresize(const unsigned char *src, int width, int height, int step, int resized_width, int resized_height, unsigned char *dst)
{
    /* < 10 */
    int udtx, udty;
    /* < 10 */
    int uda_11;
    /* < 10 */
    int uda_22;
    int i, j;
    int utx, uty;
    int locgray4 = 0;
    int ucgray1 = 0, ucgray2 = 0, ucgray3 = 0, ucgray4 = 0;
    unsigned int aa, ab, ac, ad;

    const unsigned char *pucsrcp = 0;
    unsigned char *pucdstp = dst;

    /* Affine transformation */

    uda_11 = (width << 10) / resized_width;
    uda_22 = (height << 10) / resized_height;

    for (j = 0; j < resized_height; j++) {
        for (i = 0; i < resized_width; i++) {
            locgray4 = 0;

            udtx = uda_11 * i;
            udty = uda_22 * j;

            utx = udtx >> 10;
            uty = udty >> 10;
            udtx -= (utx << 10);
            udty -= (uty << 10);
            pucsrcp = src + (uty * step) + utx;

            ucgray1 = *pucsrcp;

            if (i >= resized_width - 1) {
                ucgray2 = *pucsrcp;
                udtx = 1 << 10;
            }
            else {
                ucgray2 = *(pucsrcp + 1);
                locgray4 += 1;
            }

            if (j >= resized_height - 1) {
                ucgray3 = *pucsrcp;
                udty = 1 << 10;
            }
            else {
                ucgray3 = *(pucsrcp + step);
                locgray4 += step;
            }

            ucgray4 = *(pucsrcp + locgray4);

            aa = ucgray1 << 20;
            ab = ((ucgray2 - ucgray1) * (udtx << 10));
            ac = ((ucgray3 - ucgray1) * (udty << 10));
            ad = (ucgray1 + ucgray4 - ucgray3 - ucgray2) * udtx * udty;

            *(pucdstp + i) = (unsigned char)((aa + ab + ac + ad) >> 20);
        }

        pucdstp += resized_width;
    }
}

//void CCarDetection::image2BHoG(unsigned char* image, int width, int height, unsigned short* destData)
//{
//    memset(destData, 0, sizeof(unsigned short)*width*height);
//
//    const int MAX_SIZE = 640 * 180; // 
//    static int histSum[MAX_SIZE][ANGLE_BIN];
//    for (int i = 0; i < width; i++)
//        memset(histSum[i], 0, sizeof(int)* ANGLE_BIN);
//    for (int i = 0; i < height; i++)
//        memset(histSum[i*width], 0, sizeof(int)* ANGLE_BIN);
//
//    for (int y = 1; y < height - 1; y++)
//    {
//        for (int x = 1; x < width - 1; x++)
//        {
//            int p = y*width + x;
//            int u = p - width;
//            int d = p + width;
//            int l = p - 1;
//            int r = p + 1;
//            int dx = image[r] - image[l];
//            int dy = image[d] - image[u];
//            int magnitude = dx * dx + dy * dy;
//            int orientation = orientation_map[dy + 255][dx + 255];
//            int ul = u - 1;
//            for (int k = 0; k < ANGLE_BIN; k++)
//                histSum[p][k] = histSum[u][k] + histSum[l][k] - histSum[ul][k];
//            histSum[p][orientation] += magnitude;
//        }
//    }
//    float hist_mean;
//    float hist[ANGLE_BIN] = { 0 };
//    for (int k = 0; k < ANGLE_BIN; k++)
//        hist[k] = histSum[0][k];
//    hist_mean = (hist[0] + hist[1] + hist[2] + hist[3] +
//        hist[4] + hist[5] + hist[6] + hist[7] + hist[8]) / ANGLE_BIN;
//    unsigned short bhog_v = ((hist_mean < hist[0]) ? 1 : 0) |
//        ((hist_mean < hist[1]) ? 2 : 0) |
//        ((hist_mean < hist[2]) ? 4 : 0) |
//        ((hist_mean < hist[3]) ? 8 : 0) |
//        ((hist_mean < hist[4]) ? 16 : 0) |
//        ((hist_mean < hist[5]) ? 32 : 0) |
//        ((hist_mean < hist[6]) ? 64 : 0) |
//        ((hist_mean < hist[7]) ? 128 : 0) |
//        ((hist_mean < hist[8]) ? 256 : 0);
//    destData[0] = bhog_v;
//
//    for (int x = 1; x < width - BLOCK_SIZE; x++)
//    {
//        float hist_mean;
//        float hist[ANGLE_BIN] = { 0 };
//        int p = x + BLOCK_SIZE - 1;
//        int l = p - BLOCK_SIZE;
//        for (int k = 0; k < ANGLE_BIN; k++)
//            hist[k] = histSum[p][k] - histSum[l][k];
//
//        hist_mean = (hist[0] + hist[1] + hist[2] + hist[3] +
//            hist[4] + hist[5] + hist[6] + hist[7] + hist[8]) / ANGLE_BIN;
//        unsigned short bhog_v = ((hist_mean < hist[0]) ? 1 : 0) |
//            ((hist_mean < hist[1]) ? 2 : 0) |
//            ((hist_mean < hist[2]) ? 4 : 0) |
//            ((hist_mean < hist[3]) ? 8 : 0) |
//            ((hist_mean < hist[4]) ? 16 : 0) |
//            ((hist_mean < hist[5]) ? 32 : 0) |
//            ((hist_mean < hist[6]) ? 64 : 0) |
//            ((hist_mean < hist[7]) ? 128 : 0) |
//            ((hist_mean < hist[8]) ? 256 : 0);
//        destData[x] = bhog_v;
//    }
//    for (int y = 1; y < height - BLOCK_SIZE; y++)
//    {
//        float hist_mean;
//        float hist[ANGLE_BIN] = { 0 };
//        int p = y*width + BLOCK_SIZE - 1;
//        int u = p - BLOCK_SIZE*width;
//        for (int k = 0; k < ANGLE_BIN; k++)
//            hist[k] = histSum[p][k] - histSum[u][k];
//
//        hist_mean = (hist[0] + hist[1] + hist[2] + hist[3] +
//            hist[4] + hist[5] + hist[6] + hist[7] + hist[8]) / ANGLE_BIN;
//        unsigned short bhog_v = ((hist_mean < hist[0]) ? 1 : 0) |
//            ((hist_mean < hist[1]) ? 2 : 0) |
//            ((hist_mean < hist[2]) ? 4 : 0) |
//            ((hist_mean < hist[3]) ? 8 : 0) |
//            ((hist_mean < hist[4]) ? 16 : 0) |
//            ((hist_mean < hist[5]) ? 32 : 0) |
//            ((hist_mean < hist[6]) ? 64 : 0) |
//            ((hist_mean < hist[7]) ? 128 : 0) |
//            ((hist_mean < hist[8]) ? 256 : 0);
//        destData[y * width] = bhog_v;
//
//        for (int x = 1; x < width - BLOCK_SIZE; x++)
//        {
//            float hist_mean;
//            float hist[ANGLE_BIN] = { 0 };
//            int p = y*width + x + BLOCK_SIZE - 1;
//            int u = p - BLOCK_SIZE*width;
//            int l = p - BLOCK_SIZE;
//            int ul = u - BLOCK_SIZE;
//            for (int k = 0; k < ANGLE_BIN; k++)
//                hist[k] = histSum[p][k] - histSum[u][k] - histSum[l][k] + histSum[ul][k];
//
//            hist_mean = (hist[0] + hist[1] + hist[2] + hist[3] +
//                hist[4] + hist[5] + hist[6] + hist[7] + hist[8]) / ANGLE_BIN;
//            unsigned short bhog_v = ((hist_mean < hist[0]) ? 1 : 0) |
//                ((hist_mean < hist[1]) ? 2 : 0) |
//                ((hist_mean < hist[2]) ? 4 : 0) |
//                ((hist_mean < hist[3]) ? 8 : 0) |
//                ((hist_mean < hist[4]) ? 16 : 0) |
//                ((hist_mean < hist[5]) ? 32 : 0) |
//                ((hist_mean < hist[6]) ? 64 : 0) |
//                ((hist_mean < hist[7]) ? 128 : 0) |
//                ((hist_mean < hist[8]) ? 256 : 0);
//            destData[y * width + x] = bhog_v;
//        }
//    }
//
//    //for (int y = 0; y < height - BLOCK_SIZE; y++)
//    //{
//    //    for (int x = 0; x < width - BLOCK_SIZE; x++)
//    //    {
//    //        float hist_mean;
//    //        float hist[ANGLE_BIN] = { 0 };
//    //        for (int by = y; by < y + BLOCK_SIZE; by++)
//    //        {
//    //            for (int bx = x; bx < x + BLOCK_SIZE; bx++)
//    //            {
//    //                int p = by*width + bx;
//    //                hist[o_data[p]] += m_data[p];
//    //            }
//    //        }
//    //        hist_mean = (hist[0] + hist[1] + hist[2] + hist[3] +
//    //            hist[4] + hist[5] + hist[6] + hist[7] + hist[8]) / 9;
//    //        unsigned short bhog_v = ((hist_mean < hist[0]) ? 1 : 0) |
//    //            ((hist_mean < hist[1]) ? 2 : 0) |
//    //            ((hist_mean < hist[2]) ? 4 : 0) |
//    //            ((hist_mean < hist[3]) ? 8 : 0) |
//    //            ((hist_mean < hist[4]) ? 16 : 0) |
//    //            ((hist_mean < hist[5]) ? 32 : 0) |
//    //            ((hist_mean < hist[6]) ? 64 : 0) |
//    //            ((hist_mean < hist[7]) ? 128 : 0) |
//    //            ((hist_mean < hist[8]) ? 256 : 0);
//    //        destData[y * width + x] = bhog_v;
//    //    }
//    //}
//
//    //delete [] histSum[0];
//    //delete [] histSum;
//
//}