/******************************************************************************

Vehicle Detector

Copyright © 2016 kt Inc. All rights reserved.

This is a proprietary software of kt Inc. and you may not use this file except
in compliance with license agreement with kt Inc. Any redistribution or use of
this software, with or without modification shall be strictly prohibited
without prior written approval of kt Inc. and the copyright notice above does
not evidence any actual or intended publication of such software.

Author: Byungmin Kim, Ilhyeon Mun
Email: {byungmin.kim, ilhyeon.mun}@kt.com

******************************************************************************/

#pragma once

#include <vector>

/**
    C++ namespace for vehicle detector 
*/
namespace vd
{
    /**
        검출된 객체 타입 정의
        현재는 객체 타입은 지원하지 않음
        모든 검출된 객체는 UNKNOWN 타입으로 가정
    */
    typedef enum
    {
        VD_OBJECT_UNKNOWN/*,
        VD_OBJECT_CAR,
        VD_OBJECT_SUV,
        VD_OBJECT_BUS,
        VD_OBJECT_TRUCK,
        VD_OBJECT_COMMERCIAL,
        VD_OBJECT_BICYCLE,
        VD_OBJECT_MOTORCYCLE,
        VD_OBJECT_HUMAN,
        VD_OBJECT_ANIMAL,
        VD_OBJECT_TRAFFIC_SIGN*/
    } OBJECT_TYPE;

    /**
        2차원 사각형 클래스
        opencv cv::Rect와 호환
    */
    class Rect
    {
    public:
        Rect() : x(0), y(0), width(0), height(0) {}
        Rect(const Rect &r) : x(r.x), y(r.y), width(r.width), height(r.height) {}
        Rect(int _x, int _y, int _width, int _height) : x(_x), y(_y), width(_width), height(_height) {}
        int top() { return y; }
        int bottom() { return y + height; }
        int left() { return x; }
        int right() { return x + width; }
        int area() { return width * height; }
        int x, y, width, height;
    };

    /**
        Vehicle 객체 클래스
        검출된 객체 정보를 포함
        id는 각 객체별 구분되는 번호이지만 현재는 지원안함, 추후 트래킹 기능 구현시 적용
        type은 객체 타입이지만 현재는 지원안함, Unknown으로 가정
        rect는 입력 영상내에서 검출된 객체가 존재하는 사각영역임
    */
    class VObject
    {
    public:
        VObject() : id(0), type(VD_OBJECT_UNKNOWN) {}
        VObject(const VObject &o) : id(o.id), type(o.type), rect(o.rect) {}
        VObject(int id, OBJECT_TYPE type, Rect rect) : id(id), type(type), rect(rect) {}
        int id;
        OBJECT_TYPE type;
        Rect rect;
    };

    /**
        Vehicle 검출기 클래스
    */
    class VehicleDetector
    {
    public:

        /**
            생성자

            @param [in] frameWidth  입력 영상의 가로 크기
            @param [in] frameHeight  입력 영상의 세로 크기
            @param [in] rotated  입력 영상이 회전된 상태 여부
                                 ex)portrait 모드에서 영상의 가로x세로가 1280x720인 경우 true
                                                720x1280인 경우 false
        */
        VehicleDetector(int frameWidth, int frameHeight, bool rotated);
        ~VehicleDetector();

        /**
            객체 검출 함수

            @param [in] frame  입력 영상 데이터 포인터(8bit gray)
            @param [in] roi  입력 영상에서 객체 검출할 영역
            @param [out] result  객체 검출 결과
        */
        void Detect(const unsigned char *frame, const Rect &roi, std::vector<VObject> &result);

    private:
        void *impl;
    };
}