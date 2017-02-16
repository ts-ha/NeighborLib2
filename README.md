# NeighborLib2
  volley lib 적용
  통신 라이브러 
  firebase crash reporing 추가
  app이 죽으면 firebase로 로그를 확인할 수 있음
  https://firebase.google.com/docs/crash/android/start/
  log4j 적용
  해당 라인을 출력하기 때문에 찾아 로그 확인이 쉬움


안드로이드 해상도 dim
https://docs.google.com/spreadsheets/d/1wvq3cTVkDMZeyniygKUoknrRofjMXmsT4wwJyE66rSU/edit

![패키지이미지](https://github.com/ts-ha/NeighborLib2/blob/master/app/captures/pakage.PNG)

### Java 패키지 설계

Android 애플리케이션을 위한 Java 설계 간단히 [Model-View-Controller](http://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93controller) 간략화할 수 있다. Android에서는, [Fragment and Activity are actually controller classes](http://www.informit.com/articles/article.aspx?p=2126865) 라고 설명되는데, 다른 면에서 Fragment와 Activity들은 명시적으로 사용자 인터페이스, 즉 View 이기도 하다.

그렇기 때문에, Fragment(혹은 Activity)를 정확히 Conteroller나 View 구분할 수 없다. 그래서 그에 해당하는 `fragments` 패키지에 두는 것이 낫다. Activity는 이전 섹션에서의 조언에 따라 최상위 패키지에 둘 수 있다. 2, 3개 이상의 Activity들을 계획하고 있다면, `activities` 패키지를 만들어 두자.

다른 경우에는, API 응답에 대한 JSON 파서에 의해 채워진 POJO들을 담는 `models` 패키지, 커스텀 View, Notification, Action bar view, Widget 등을 담는 `views` 패키지를 두어 설계가 일반적인 MVC로 표현될 수 있다. Adapter들은 데이터와 View들 사이에 존재하는 gray matter라고 할 수 있지만, 일반적으로 `getView()`를 통해 View들을 추출하는 데에 필요하기 때문에 `views` 패키지 안에 `adapters`라는 서브패키지로 둘 수 있다.

Controller 클래스들은 애플리케이션 전체에, Android 시스템에 가깝게 존재한다. 이들은 `managers` 패키지에 둘 수 있다. "DateUtils"와 같은 다양한 데이터 처리 클래스들은 `utils` 패키지에, 백엔드와 인터랙션하는 역할을 맡는 클래스들은 `network` 패키지에 두자.

종합적으로, 백엔드와 가까운 것부터 유저와 가까운 순서대로 정렬해보면 이렇다:

```
com.futurice.project
├─ network
├─ models
├─ managers
├─ utils
├─ fragments
└─ views
   ├─ adapters
   ├─ actionbar
   ├─ widgets
   └─ notifications
