# 🥛MilkyWay Android : Milkyroid🥛

<img src = "https://user-images.githubusercontent.com/55903679/103667439-47f95a00-4fb9-11eb-994d-648e7de9f05c.png" />

> SOPT 27Th APPJAM - TEAM MilkyWay  
2020.12.26 ~ ing 🤍



## 🤹‍♀️Role

회진

```
- 나의 제보
- 카페 상세 정보
- 설정
```

은이

```
- 바텀 네비게이션
- 홈 바텀 시트
- 카페 정보 수정/삭제/추가
- 유니버스
```

수정

```
- 카페 제보
- 카카오톡 공유
- 홈, 카페제보 검색
```

영민

```
- 홈 지도
- 홈 필터
- 로그인
- 회원가입
```



## 🎫Meeting Log

### [Milkyroid -  Meeting wiki](https://github.com/MilkyOnOurWay/Milkyroid/wiki)

> 일시 : 화 목 토 전체 회의 이후

1주차 

- [20.12.29 1차 회의 - 뷰 분배&규칙 정리](https://github.com/MilkyOnOurWay/Milkyroid/wiki/%5B20201229%5D-Milkyroid-1%EC%B0%A8-%ED%9A%8C%EC%9D%98)
- [20.12.31 2차 회의 - 앱잼 목표&디자인 패턴](https://github.com/MilkyOnOurWay/Milkyroid/wiki/%5B20201231%5D-Milkyroid-2%EC%B0%A8-%ED%9A%8C%EC%9D%98)
- [21.01.02 3차 회의 - 스프린트 재조정](https://github.com/MilkyOnOurWay/Milkyroid/wiki/%5B20210102%5D-Milkyroid-3%EC%B0%A8-%ED%9A%8C%EC%9D%98)

2주차

- [21.01.05 4차 회의 - 진행상황 공유, pull request 사용](https://github.com/MilkyOnOurWay/Milkyroid/wiki/%5B20210105%5D-Milkyroid-4%EC%B0%A8-%ED%9A%8C%EC%9D%98)

3주차



## 👪 Communication

### [Milkyroid - Kanban board](https://github.com/MilkyOnOurWay/Milkyroid/projects/1)



## 🔧TOOLS

- Android Studio 4.1.1
- Zeplin



## 🎠ID Naming

### **color**

- **colorname_16진수**
    - 반복되는 16진수면, 앞에 두 글자만 쓴다.

    ex) gray_f8   gray_f8f54f6    pink_f0

**DRAWABLES**

- **border_colorname_shape(line or fill)_radiusnumber.xml**

    ex) border_lightgray_line_32.xml

- ic_name
- selector_where_description.xml

### view

- **elementtype_where_action**
  
    - btn_home_login
    - img
    - tv
    - et
    - tab
    - vp
    - rv
- item
  
        ex) item_home_title



## 🧶Branching

- GitFlow
- 브랜치

    ```
    master : 제품으로 출시될 수 있는 브랜치
    develop : 다음 출시 버전을 개발하는 브랜치
    feature : 기능을 개발하는 브랜치
    release : 이번 출시 버전을 준비하는 브랜치
    hotfix : 출시 버전에서 발생한 버그를 수정하는 브랜치
    ```

- 하위 브랜치
    - **브랜치/이슈번호\_내용_이슈카테고리**
    
      ex) feature/36_home_layout



## 💫Issue Naming

- 이슈 카테고리
    - **[layout] : 뷰**
    - **[network] : 서버**
    - **[feature] :  기능**
    - **[refactor] : 코드 개선**
    - **[fix] : 버그 수정**
    
    ex) [layout] 마이 유니버스 등록



## 💬Commit Message

- [    ] 카테고리 5개 중 하나 쓰기.
- 깃 이슈 번호 붙이기
- **[이슈카테고리] #이슈번호 뒤에 내용**

    ex)  [feature] #1  Add feature 



## 🎪Project Structure

```
📦milkyway
 ┣ 📂data
 ┃ ┣ 📂model
 ┃ ┗ 📂remote
 ┣ 📂ui
 ┃ ┣ 📂home
 ┃ ┃ ┗ 📜HomeFragment.kt
 ┃ ┣ 📂main
 ┃ ┃ ┗ 📜MainActivity.kt
 ┃ ┣ 📂report
 ┃ ┃ ┗ 📜ReportFragment.kt
 ┃ ┗ 📂setting
 ┃ ┃ ┗ 📜SettingFragment.kt
 ┗ 📂util
```



## 🎢Dependency

```kotlin
    // Activity-Ktx
    implementation "androidx.activity:activity-ktx:$activity_version"

    // Fragment-Ktx
    implementation "androidx.fragment:fragment-ktx:$fragment_version"

    // Navigation-Ktx
    implementation "android.arch.navigation:navigation-fragment-ktx:1.0.0"
    implementation "android.arch.navigation:navigation-ui-ktx:1.0.0"

    // Material
    implementation "com.google.android.material:material:1.2.1"

    // Retrofit
    implementation 'com.google.code.gson:gson:2.8.6'
    implementation 'com.squareup.retrofit2:retrofit:2.9.0'
    implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
    
    // Naver map
    implementation 'com.naver.maps:map-sdk:3.10.1'
    // Google play service - location
    implementation("com.google.android.gms:play-services-location:16.0.0")
```



## 👊Milkyroid

> 💻 Milkyway's Android Developer

|          **🙋 [김영민](https://github.com/kym1924)**          |        **🙋‍ [김회진](https://github.com/bluelemon9)**         |          **🙋‍ [이수정](https://github.com/doodung)**          |        **🙋‍ [정은이](https://github.com/EuneeChung)**         |
| :----------------------------------------------------------: | :----------------------------------------------------------: | :----------------------------------------------------------: | :----------------------------------------------------------: |
| <img src="https://user-images.githubusercontent.com/55903679/103664267-6d846480-4fb5-11eb-8d26-361ee5bb01fe.jpg" width="200" height="200" /> | <img src = "https://user-images.githubusercontent.com/55903679/103664259-6bbaa100-4fb5-11eb-97e9-46178dfcb96e.jpg" width="200" height="200" /> | <img src = "https://user-images.githubusercontent.com/55903679/103664250-68bfb080-4fb5-11eb-80c2-f1a4ee334dcb.png" width="200" height="200" /> | <img src = "https://user-images.githubusercontent.com/55903679/103664254-6a897400-4fb5-11eb-98e1-1a3773023af4.png" width="200" height="200" /> |
|                    안드로이드 리드 개발자                    |                      안드로이드 개발자                       |                      안드로이드 개발자                       |                      안드로이드 개발자                       |
