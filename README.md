# 🥛MilkyWay Android : Milkyroid🥛

<img src = "https://user-images.githubusercontent.com/55903679/103667439-47f95a00-4fb9-11eb-994d-648e7de9f05c.png" />

> SOPT 27기 17TH APPJAM - TEAM MilkyWay 🤍  
2020.12.26 ~ 2021.01.16 


<br/>

## 🌌Work Flow

<img src ="https://user-images.githubusercontent.com/63637706/104713874-d0b98800-5767-11eb-8bd8-2aa110f883a5.jpg"/>


## 🤳Simulation

<div align="center">
<h4>Lottie</h4> 
<img src="https://user-images.githubusercontent.com/63637706/104729264-86420680-577b-11eb-8681-38698ad11816.gif" width ="300px" heingt="600px"/>
<h4>Naver Map</h4> 
<img src="https://user-images.githubusercontent.com/63637706/104727887-70334680-5779-11eb-8534-a91034052552.gif" width ="300px" height="600px"/>
<h4>Universe</h4> 
<img src="https://user-images.githubusercontent.com/63637706/104730600-b1c5f080-577d-11eb-9f61-33dbd223a660.gif" width ="300px" height="600px"/>
<h4>Report Menu</h4> 
<img src="https://user-images.githubusercontent.com/63637706/104727919-7cb79f00-5779-11eb-97e2-72eb4263d35e.gif" width ="300px" height="600px"/>
<h4>Edit</h4> 
<img src="https://user-images.githubusercontent.com/63637706/104727926-7e816280-5779-11eb-9c2a-9ec8a72d4202.gif" width ="300px" heignt="600px"/> 
<h4>Delete</h4> 
<img src="https://user-images.githubusercontent.com/63637706/104727932-7f19f900-5779-11eb-831a-36a0189c6f0a.gif" width ="300px" height="600px"/>
<h4>Search</h4> 
<img src="https://user-images.githubusercontent.com/63637706/104728560-6a8a3080-577a-11eb-938f-7cfcf2d7b1f2.gif" width ="300px" height="600px"/> 
</div>

<br/>

## 🤹‍♀️Role

회진

```
- 나의 제보
- 카페 상세 정보
- 닉네임 변경
- 설정
```

은이

```
- 바텀 네비게이션
- 홈 바텀 시트
- 유니버스 바텀 시트
- 카페 정보 수정
- 카페 정보 삭제
```

수정

```
- 카페 제보
- 홈 검색
- 카페 검색
- 메뉴 추가
```

영민

```
- 스플래시
- 회원가입
- 로그인
- 홈 필터
- 검색 결과
- 네이버 지도
```


<br/>

## 🎫Meeting Log

### [Milkyroid -  Meeting wiki](https://github.com/MilkyOnOurWay/Milkyroid/wiki)

> 일시 : 화 목 토 전체 회의 이후

1주차 

- [20.12.29 1차 회의 - 뷰 분배&규칙 정리](https://github.com/MilkyOnOurWay/Milkyroid/wiki/%5B20201229%5D-Milkyroid-1%EC%B0%A8-%ED%9A%8C%EC%9D%98)
- [20.12.31 2차 회의 - 앱잼 목표&디자인 패턴](https://github.com/MilkyOnOurWay/Milkyroid/wiki/%5B20201231%5D-Milkyroid-2%EC%B0%A8-%ED%9A%8C%EC%9D%98)
- [21.01.02 3차 회의 - 스프린트 재조정](https://github.com/MilkyOnOurWay/Milkyroid/wiki/%5B20210102%5D-Milkyroid-3%EC%B0%A8-%ED%9A%8C%EC%9D%98)

2주차

- [21.01.05 4차 회의 - 진행상황 공유, pull request 사용](https://github.com/MilkyOnOurWay/Milkyroid/wiki/%5B20210105%5D-Milkyroid-4%EC%B0%A8-%ED%9A%8C%EC%9D%98)
- [20210107 5차 회의 - BindAdapter](https://github.com/MilkyOnOurWay/Milkyroid/wiki/[20210107]-Milkyroid-5차-회의)
- [20210109 6차 회의 - 진행상황 공유](https://github.com/MilkyOnOurWay/Milkyroid/wiki/[20210107]-Milkyroid-6차-회의)

3주차

- [20210112 7차 회의 - 진행상황 공유](https://github.com/MilkyOnOurWay/Milkyroid/wiki/[20210112]-Milkyroid-7차-회의)
- [20210114 8차 회의 - 진행상황 공유](https://github.com/MilkyOnOurWay/Milkyroid/wiki/[20210112]-Milkyroid-8차-회의)
- [20210115 9차 회의 - 진행상황 공유](https://github.com/MilkyOnOurWay/Milkyroid/wiki/[20210112]-Milkyroid-9차-회의)


<br/>

## 👪 Communication

#### [Milkyroid - Kanban board](https://github.com/MilkyOnOurWay/Milkyroid/projects/1)

<br/>

## 🔧TOOLS

- Android Studio 4.1.1
- Zeplin/Figma
- Postman

<br/>

## 🎸Design Pattern

- DataBinding
- ViewModel
- Data Store
- Coroutine


<br/>

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

<br/>


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

<br/>


## 💫Issue Naming

- 이슈 카테고리
    - **[layout] : 뷰**
    - **[network] : 서버**
    - **[feature] :  기능**
    - **[refactor] : 코드 개선**
    - **[fix] : 버그 수정**
    
    ex) [layout] 마이 유니버스 등록

<br/>

## 💬Commit Message

- [    ] 카테고리 5개 중 하나 쓰기.
- 깃 이슈 번호 붙이기
- **[이슈카테고리] #이슈번호 뒤에 내용**

    ex)  [feature] #1  Add feature 

<br/>


## 🎪Project Structure

```
​```
📦milkyway
 ┗ 📂milkyway
 ┃ ┣ 📂data
 ┃ ┃ ┣ 📂model
 ┃ ┃ ┃ ┣ 📜CafeReportMenuData.kt
 ┃ ┃ ┃ ┣ 📜CafeSearchData.kt
 ┃ ┃ ┃ ┣ 📜MyUniverse.kt
 ┃ ┃ ┃ ┗ 📜PlaceSearchData.kt
 ┃ ┃ ┗ 📂remote
 ┃ ┃ ┃ ┣ 📂request
 ┃ ┃ ┃ ┃ ┣ 📜RequestCafeId.kt
 ┃ ┃ ┃ ┃ ┣ 📜RequestChangeNickname.kt
 ┃ ┃ ┃ ┃ ┣ 📜RequestModify.kt
 ┃ ┃ ┃ ┃ ┣ 📜RequestReport.kt
 ┃ ┃ ┃ ┃ ┗ 📜RequestSign.kt
 ┃ ┃ ┃ ┣ 📂response
 ┃ ┃ ┃ ┃ ┣ 📜BaseResponse.kt
 ┃ ┃ ┃ ┃ ┣ 📜ReponseToken.kt
 ┃ ┃ ┃ ┃ ┣ 📜ResponseAddUniverse.kt
 ┃ ┃ ┃ ┃ ┣ 📜ResponseCafeDetail.kt
 ┃ ┃ ┃ ┃ ┣ 📜ResponseCafeSearch.kt
 ┃ ┃ ┃ ┃ ┣ 📜ResponseChangeNickname.kt
 ┃ ┃ ┃ ┃ ┣ 📜ResponseDeleteCancel.kt
 ┃ ┃ ┃ ┃ ┣ 📜ResponseDeleteUniverse.kt
 ┃ ┃ ┃ ┃ ┣ 📜ResponseHome.kt
 ┃ ┃ ┃ ┃ ┣ 📜ResponseModify.kt
 ┃ ┃ ┃ ┃ ┣ 📜ResponseMyReport.kt
 ┃ ┃ ┃ ┃ ┣ 📜ResponsePlaceSearch.kt
 ┃ ┃ ┃ ┃ ┗ 📜ResponseUniverse.kt
 ┃ ┃ ┃ ┣ 📜MilkyWayService.kt
 ┃ ┃ ┃ ┗ 📜RetrofitBuilder.kt
 ┃ ┣ 📂ui
 ┃ ┃ ┣ 📂detail
 ┃ ┃ ┃ ┣ 📜CafeDetailActivity.kt
 ┃ ┃ ┃ ┣ 📜CafeDetailViewModel.kt
 ┃ ┃ ┃ ┗ 📜MenuAdapter.kt
 ┃ ┃ ┣ 📂home
 ┃ ┃ ┃ ┣ 📜HomeBinding.kt
 ┃ ┃ ┃ ┣ 📜HomeFragment.kt
 ┃ ┃ ┃ ┣ 📜HomeResultActivity.kt
 ┃ ┃ ┃ ┗ 📜HomeViewModel.kt
 ┃ ┃ ┣ 📂main
 ┃ ┃ ┃ ┗ 📜MainActivity.kt
 ┃ ┃ ┣ 📂modify
 ┃ ┃ ┃ ┣ 📂dialog
 ┃ ┃ ┃ ┃ ┗ 📜DeleteFragmentDialog.kt
 ┃ ┃ ┃ ┣ 📂request
 ┃ ┃ ┃ ┃ ┣ 📜RequestModificationsActivity.kt
 ┃ ┃ ┃ ┃ ┗ 📜RequestModificationsViewModel.kt
 ┃ ┃ ┃ ┣ 📜ModifyActivity.kt
 ┃ ┃ ┃ ┣ 📜ModifyBindingAdapter.kt
 ┃ ┃ ┃ ┗ 📜ModifyDialogViewModel.kt
 ┃ ┃ ┣ 📂nickname
 ┃ ┃ ┃ ┣ 📜NicknameActivity.kt
 ┃ ┃ ┃ ┣ 📜NicknameBinding.kt
 ┃ ┃ ┃ ┗ 📜NicknameViewModel.kt
 ┃ ┃ ┣ 📂report
 ┃ ┃ ┃ ┣ 📂cafereport
 ┃ ┃ ┃ ┃ ┣ 📜CafeReportFragment.kt
 ┃ ┃ ┃ ┃ ┣ 📜CafeReportMenuActivity.kt
 ┃ ┃ ┃ ┃ ┣ 📜CafeReportMenuAdapter.kt
 ┃ ┃ ┃ ┃ ┗ 📜CafeReportMenuViewHolder.kt
 ┃ ┃ ┃ ┣ 📂dialog
 ┃ ┃ ┃ ┃ ┗ 📜ShowReportOkDialog.kt
 ┃ ┃ ┃ ┣ 📂myreport
 ┃ ┃ ┃ ┃ ┣ 📜CancelAdapter.kt
 ┃ ┃ ┃ ┃ ┣ 📜CancelViewModel.kt
 ┃ ┃ ┃ ┃ ┣ 📜DoneAdapter.kt
 ┃ ┃ ┃ ┃ ┣ 📜IngAdapter.kt
 ┃ ┃ ┃ ┃ ┣ 📜MyReportFragment.kt
 ┃ ┃ ┃ ┃ ┗ 📜MyReportViewModel.kt
 ┃ ┃ ┃ ┣ 📜ReportFragment.kt
 ┃ ┃ ┃ ┗ 📜ReportViewPagerAdapter.kt
 ┃ ┃ ┣ 📂search
 ┃ ┃ ┃ ┣ 📂homesearch
 ┃ ┃ ┃ ┃ ┣ 📜CafeSearchActivity.kt
 ┃ ┃ ┃ ┃ ┣ 📜CafeSearchAdapter.kt
 ┃ ┃ ┃ ┃ ┗ 📜CafeSearchViewModel.kt
 ┃ ┃ ┃ ┗ 📂reportsearch
 ┃ ┃ ┃ ┃ ┣ 📜PlaceSearchActivity.kt
 ┃ ┃ ┃ ┃ ┣ 📜PlaceSearchActivityViewModel.kt
 ┃ ┃ ┃ ┃ ┗ 📜PlaceSearchAdapter.kt
 ┃ ┃ ┣ 📂setting
 ┃ ┃ ┃ ┗ 📜SettingFragment.kt
 ┃ ┃ ┣ 📂splash
 ┃ ┃ ┃ ┣ 📜SplashActivity.kt
 ┃ ┃ ┃ ┗ 📜SplashViewModel.kt
 ┃ ┃ ┗ 📂universe
 ┃ ┃ ┃ ┣ 📜UniverseAdapter.kt
 ┃ ┃ ┃ ┣ 📜UniverseBinding.kt
 ┃ ┃ ┃ ┣ 📜UniverseBottomSheetViewModel.kt
 ┃ ┃ ┃ ┣ 📜UniverseFragment.kt
 ┃ ┃ ┃ ┗ 📜UniverseViewModel.kt
 ┃ ┗ 📂util
 ┃ ┃ ┣ 📜ConfirmAlertDialog.kt
 ┃ ┃ ┣ 📜DataStore.kt
 ┃ ┃ ┣ 📜Font.kt
 ┃ ┃ ┣ 📜Location.kt
 ┃ ┃ ┣ 📜MarkerDrawer.kt
 ┃ ┃ ┣ 📜startActivity.kt
 ┃ ┃ ┣ 📜Toast.kt
 ┃ ┃ ┣ 📜UniverseMarkerDrawer.kt
 ┃ ┃ ┗ 📜UUID.kt
​```
```


<br/>

## 🎢Dependency

```kotlin
ext {
    kotlin_version = "1.3.72"
    activity_version = "1.1.0"
    fragment_version = "1.2.5"
}

implementation "org.jetbrains.kotlin:kotlin-stdlib:$kotlin_version"
implementation 'androidx.core:core-ktx:1.3.2'
implementation 'androidx.appcompat:appcompat:1.2.0'
implementation 'com.google.android.material:material:1.2.1'
implementation 'androidx.constraintlayout:constraintlayout:2.0.4'
implementation 'androidx.legacy:legacy-support-v4:1.0.0'
implementation 'androidx.wear:wear:1.1.0'
testImplementation 'junit:junit:4.+'
androidTestImplementation 'androidx.test.ext:junit:1.1.2'
androidTestImplementation 'androidx.test.espresso:espresso-core:3.3.0'

// Activity-Ktx
implementation "androidx.activity:activity-ktx:$activity_version"

// Fragment-Ktx -> For ViewModels
implementation "androidx.fragment:fragment-ktx:$fragment_version"

// Navigation-Ktx -> Fragment Navigtion for BottomNavigationView
implementation "android.arch.navigation:navigation-fragment-ktx:1.0.0"
implementation "android.arch.navigation:navigation-ui-ktx:1.0.0"

// Material -> Google Material Design for Chip & BottomNavigationView
implementation "com.google.android.material:material:1.2.1"

// Retrofit -> Retrofit for Server
implementation 'com.google.code.gson:gson:2.8.6'
implementation 'com.squareup.retrofit2:retrofit:2.9.0'
implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
implementation "com.squareup.retrofit2:adapter-rxjava2:2.5.0"

// naver map SDK -> NaverMap for HomeFragment and UniverseFragment
implementation 'com.naver.maps:map-sdk:3.10.0'

// Google Location -> Google Location Permission FusedLocationSource
implementation 'com.google.android.gms:play-services-location:17.1.0'

// Lottie -> Lottie for Splash & Loading Image
implementation 'com.airbnb.android:lottie:3.0.7'

// Preferences DataStore -> DataStore for Saving Data
implementation "androidx.datastore:datastore-preferences:1.0.0-alpha05"
```

<br/>


## **🌈 Asset **

- 리사이클러뷰 배경을 그림자를 넣어 커스텀 했었는데 디자인 상 주어진 이미지를 사용하는 것이 더 좋을 것이라고 판단하여 이미지를 받아 적용.

  - [item_report_cancel.xml](https://github.com/MilkyOnOurWay/Milkyroid/blob/develop/app/src/main/res/layout/item_myreport_cancel.xml)

  - [item_report_ing.xml](https://github.com/MilkyOnOurWay/Milkyroid/blob/develop/app/src/main/res/layout/item_myreport_ing.xml)

```
<androidx.constraintlayout.widget.ConstraintLayout
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:background="@drawable/myreport_cancelled_list"
            app:layout_constraintBottom_toBottomOf="parent"
            app:layout_constraintEnd_toEndOf="parent"
            app:layout_constraintStart_toStartOf="parent"
            app:layout_constraintTop_toTopOf="parent">
```


- 나의제보뷰 - 우주느낌의 배경이 주어져서 이미지로 적용.
  - [fragment_my_report.xml](https://github.com/MilkyOnOurWay/Milkyroid/blob/develop/app/src/main/res/layout/item_myreport_ing.xml)

```xml
<ImageView
             android:id="@+id/img_myreport_sky"
             android:layout_width="match_parent"
             android:layout_height="wrap_content"
             android:background="@drawable/img_reportsky"
             android:scaleType="fitCenter"
             app:layout_constraintEnd_toEndOf="parent"
             app:layout_constraintStart_toStartOf="parent"
             app:layout_constraintTop_toTopOf="parent" 
```


- 마이유니버스 추가/삭제 부분에 옵저버를 사용하면서 selector를 쓰는 것보다 그냥 이미지를 직접 변경해주는 것이 더 편리할 것 같아서 이미지 자체를 줌.
  - [CafeDetailActivity](https://github.com/MilkyOnOurWay/Milkyroid/blob/develop/app/src/main/java/com/milkyway/milkyway/ui/detail/CafeDetailActivity.kt)

```kotlin
cafedetailViewModel.isSelected.observe(this, Observer { isSelected ->
            isSelected?.let {
                if (isSelected) {
                    num += 1
                    binding.btnDetailUniverse.setBackgroundResource(R.drawable.btn_universe_added_detail)
                    binding.tvDetailUniverseCount.setTextColor(getColor(R.color.blue_3320a6))
```






## **📝 ConstraintLayout**

- 절대 크기 지정

  - [activity_main.xml](https://github.com/MilkyOnOurWay/Milkyroid/blob/develop/app/src/main/res/layout/activity_main.xml) : BottomNavigation 높이 고정
  - [activity_request_modifications.xml](https://github.com/MilkyOnOurWay/Milkyroid/blob/develop/app/src/main/res/layout/activity_request_modifications.xml) : editTedxt 창 높이 고정
  - [bottom_sheet_home.xml](https://github.com/MilkyOnOurWay/Milkyroid/blob/develop/app/src/main/res/layout/bottom_sheet_home.xml) : BottomSheet 높이 고정
  - [bottom_sheet_universe.xml](https://github.com/MilkyOnOurWay/Milkyroid/blob/develop/app/src/main/res/layout/bottom_sheet_universe.xml) : BottomSheet 높이 고정
  - [dialog xml들] : dialog Button 크기를 고정
    - [dailog_report_ok.xml](https://github.com/MilkyOnOurWay/Milkyroid/blob/develop/app/src/main/res/layout/dailog_report_ok.xml)
    - [dialog_alert_with_title.xml](https://github.com/MilkyOnOurWay/Milkyroid/blob/develop/app/src/main/res/layout/dialog_alert_with_title.xml)
    - [dialog_alert_without_title.xml](https://github.com/MilkyOnOurWay/Milkyroid/blob/develop/app/src/main/res/layout/dialog_alert_without_title.xml)
    - [dialog_delete_reasons.xml](https://github.com/MilkyOnOurWay/Milkyroid/blob/develop/app/src/main/res/layout/dialog_delete_reasons.xml)
    - [dialog_delete_universe.xml](https://github.com/MilkyOnOurWay/Milkyroid/blob/develop/app/src/main/res/layout/dialog_delete_universe.xml)
    - [dialog_showcancel_myreport.xml](https://github.com/MilkyOnOurWay/Milkyroid/blob/develop/app/src/main/res/layout/dialog_showcancel_myreport.xml)
  - [fragment_report](https://github.com/MilkyOnOurWay/Milkyroid/blob/develop/app/src/main/res/layout/fragment_report.xml) : TabLayout 높이 고정
  - [item_myuniverse_bottomsheet.xml](https://github.com/MilkyOnOurWay/Milkyroid/blob/develop/app/src/main/res/layout/item_myuniverse_bottomsheet.xml) : BottpmSheet 높이 고정
  - [topbar_with_back_btn.xml](https://github.com/MilkyOnOurWay/Milkyroid/blob/develop/app/src/main/res/layout/topbar_with_back_btn.xml) : TobBar 높이 고정

  

- 터치 영역을 주기 위해 height 속성 지정

  - [fragment_cafe_report.xml](https://github.com/MilkyOnOurWay/Milkyroid/blob/develop/app/src/main/res/layout/topbar_with_back_btn.xml) 

  

- 바텀시트와의 동작때문에 ContsraintLayout가 아닌 CoordinatorLayout 사용

  - [fragment_home.xml](https://github.com/MilkyOnOurWay/Milkyroid/blob/develop/app/src/main/res/layout/fragment_home.xml)
  - [fragment_universe.xml](https://github.com/MilkyOnOurWay/Milkyroid/blob/develop/app/src/main/res/layout/fragment_universe.xml)

  이외에는 모두 ConstraintLayout을 부모 Layout으로 사용하였습니다.



- 디자인된 line을 넣기 위해 height 속성 지정
  - [fragment_setting.xml](https://github.com/MilkyOnOurWay/Milkyroid/blob/develop/app/src/main/res/layout/fragment_setting.xml)
  - [activity_cafe_search.xml](https://github.com/MilkyOnOurWay/Milkyroid/blob/develop/app/src/main/res/layout/activity_cafe_search.xml)
  - [activity_place_search.xml](https://github.com/MilkyOnOurWay/Milkyroid/blob/develop/app/src/main/res/layout/activity_place_search.xml)



<br/>

## ✨Core Implementation Code

> In MilkyWay




#### 1. Naver 지도에 Marker 그리기

**🍯solution**

- [Naver Map Setting](https://github.com/MilkyOnOurWay/Milkyroid/blob/develop/app/src/main/java/com/milkyway/milkyway/ui/home/HomeFragment.kt)

```kotlin
// Naver Map Setting In Fragment

private fun setMap() {
    val fm = childFragmentManager
    val mapFragment = fm.findFragmentById(R.id.map) as MapFragment?
        ?: MapFragment.newInstance().also {
            fm.beginTransaction().add(R.id.map, it).commit()
        }
    mapFragment.getMapAsync(this@HomeFragment)
}
```

```kotlin
// Request Cafe List for TransForming Maker List

private fun setMarkerData() {
    viewLifecycleOwner.lifecycleScope.launch {
        whenResumed {
            DataStore(requireContext()).getToken.collect {
                homeViewModel.requestHomeData(it!!)
            }
        }
    }
}

fun requestHomeData(token : String) = viewModelScope.launch(Dispatchers.IO) {
    try {
        val home = RetrofitBuilder.service.home(token)
        _loading.postValue(false)
        _markers.postValue(home.data.result)
    } catch (e: HttpException) {
        Log.d("request", e.toString())
    }
}
```

```kotlin
// Markers(CafeList) In ViewModel Observer for TransForming

private fun drawMarkers(p0 : NaverMap) {
    homeViewModel.markers.observe(this, Observer { markers->
        markers?.let {
            viewLifecycleOwner.lifecycleScope.launch {
                whenResumed {
                    DataStore(requireContext()).getToken.collect {
                        MarkerDrawer.apply {
                            ...
                                                  
```

- [MarkerDrawer](https://github.com/MilkyOnOurWay/Milkyroid/blob/develop/app/src/main/java/com/milkyway/milkyway/util/MarkerDrawer.kt)


```kotlin
// From CafeList To MarkerList
// Setting Position, Icon, ClickListener, CardData

object MarkerDrawer {
    ...
    private fun clear() {
        for (marker in markers) marker.map = null
        markers.clear()
    }

    fun setMarkers() {
        for (i in cafeList.indices) {
            val marker = Marker()
            marker.position = LatLng(cafeList[i].latitude, cafeList[i].longitude)
            markers.add(marker)
        }
    }

    fun setIcon() {
        for (i in cafeList.indices) {
            if (cafeList[i].isUniversed) markers[i].icon =
                OverlayImage.fromResource(R.drawable.ic_marker_universe)
            else markers[i].icon = OverlayImage.fromResource(R.drawable.ic_marker)
        }
    }

    fun setClickListener(onClick: () -> Unit) {
        for (i in 0 until markers.size) {
            markers[i].setOnClickListener {
                markerClick(i)
                cardData(i)
                onClick()
                true
            }
        }
    }

    fun findData(position: LatLng): AroundCafe? {
        for (i in cafeList.indices) {
            if (position.latitude == cafeList[i].latitude && position.longitude == cafeList[i].longitude)
                return cafeList[i]
        }
        return null
    }

    fun findIndex(position: LatLng): Int? {
        for (i in cafeList.indices) {
            if (position.latitude == cafeList[i].latitude && position.longitude == cafeList[i].longitude)
                return i
        }
        return null
    }

    fun updateData(index: Int) {
        cafeList[index].isUniversed = !cafeList[index].isUniversed
        if (cafeList[index].isUniversed) cafeList[index].universeCount += 1
        else cafeList[index].universeCount -= 1
    }

    private fun markerClick(index: Int) {
        setIcon()
        if (cafeList[index].isUniversed)
            markers[index].icon = OverlayImage.fromResource(R.drawable.ic_marker_universe_selected)
        else
            markers[index].icon = OverlayImage.fromResource(R.drawable.ic_marker_selected)
    }

    private fun cardData(index: Int) {
        binding.tvCafeName.text = cafeList[index].cafeName
        binding.tvAddress.text = cafeList[index].cafeAddress
        binding.tvCafeHour.text = String.format(
            binding.tvCafeHour.context.getString(R.string.home_cafe_hour),
            cafeList[index].businessHours
        )
        if (cafeList[index].businessHours != null) binding.tvCafeHour.visibility = View.VISIBLE
        else binding.tvCafeHour.visibility = View.GONE
        if (cafeList[index].isUniversed) {
            binding.btnAddUniverse.setBackgroundResource(R.drawable.btn_universe_added)
            binding.btnAddUniverse.setOnClickListener {
                deleteUniverse(index)
            }
            binding.tvLikeCount.setTextColor(
                getColor(
                    binding.tvLikeCount.context,
                    R.color.blue_3320a6
                )
            )
            binding.tvLikeCount.typeface =
                ResourcesCompat.getFont(binding.tvLikeCount.context, R.font.roboto_bold)
        } else {
            binding.btnAddUniverse.setBackgroundResource(R.drawable.btn_universe)
            binding.btnAddUniverse.setOnClickListener {
                addUniverse(index)
            }
            binding.tvLikeCount.setTextColor(getColor(binding.tvLikeCount.context, R.color.gray_97))
            binding.tvLikeCount.typeface =
                ResourcesCompat.getFont(binding.tvLikeCount.context, R.font.roboto_regular)
        }
        binding.tvLikeCount.text = cafeList[index].universeCount.toString()
        binding.layoutHomeCard.setOnClickListener {
            val intent = Intent(context, CafeDetailActivity::class.java)
            intent.putExtra("cafeId", cafeList[index].id)
            context.startActivity(intent)
        }
    }

    fun drawMarkers(map: NaverMap) {
        for (marker in markers) {
            marker.map = map
        }
    }
    ...
}
```

- [CameraMove When Bottom Sheet Click](https://github.com/MilkyOnOurWay/Milkyroid/blob/develop/app/src/main/java/com/milkyway/milkyway/util/Location.kt)

```kotlin
// CameraMove When Bottom Sheet Click

object Location {
    fun cameraMove(p0 : NaverMap, location : Int) {
        var move = LatLng(0.0, 0.0)
        when(location) {
            0 -> move = LatLng(37.557852, 126.907507) //mangwondong
            1 -> move = LatLng(37.562680, 126.921358) //yeonnamdong
            2 -> move = LatLng(37.536427, 127.005132) //hannamdong
            3 -> move = LatLng(37.523733, 127.021235) //sinsadong
            4 -> move = LatLng(37.500667, 127.038390) //yeoksamdong
        }
        val cameraUpdate = CameraUpdate.scrollTo(move)
            .animate(CameraAnimation.Linear)
        p0.defaultCameraAnimationDuration = 1000
        p0.moveCamera(cameraUpdate)
    }
}
```

- [Switch Icon](https://github.com/MilkyOnOurWay/Milkyroid/blob/develop/app/src/main/java/com/milkyway/milkyway/ui/home/HomeViewModel.kt)

```kotlin
// Switch Icon
// Current Location <-> Compass

homeViewModel.compass.observe(this, Observer { compass->
    compass?.let {
        if(compass) {
            p0.locationTrackingMode = LocationTrackingMode.Face
            locationOverlay.subIcon = OverlayImage.fromResource(R.drawable.ic_location_face)
        }
        else {
            p0.locationTrackingMode = LocationTrackingMode.NoFollow
            locationOverlay.subIcon = OverlayImage.fromResource(R.drawable.ic_location_no_follow)
        }
    }
})

p0.addOnCameraChangeListener { _, _ ->
    if(p0.locationTrackingMode == LocationTrackingMode.NoFollow) homeViewModel.notCompassIcon()
}

// In ViewModel

fun compassIcon() {
    _compass.value = !_compass.value!!
}

fun notCompassIcon() {
    _compass.value = false
}
```


<br/>

#### 2. Nickname

**🍯solution**

- [BindingAdapter in NicknameActivity for Checking Condition](https://github.com/MilkyOnOurWay/Milkyroid/blob/develop/app/src/main/java/com/milkyway/milkyway/ui/nickname/NicknameBinding.kt)

```kotlin
// BindingAdapter in NicknameActivity for Checking Condition

object NicknameBinding {
    @BindingAdapter("setFilter")
    @JvmStatic
    fun setFilter(textView : TextView, nickname : String) {
        if (nickname.isEmpty()) textView.visibility = View.INVISIBLE
        else {
            if (Pattern.matches("^[0-9가-힣ㄱ-ㅎㅏ-ㅣ\u318D\u119E\u11A2\u2022\u2025a\u00B7\uFE55]+$", nickname))
                textView.visibility = View.INVISIBLE
            else
                textView.visibility = View.VISIBLE
        }
    }

    @BindingAdapter("setEditTextBackground")
    @JvmStatic
    fun setEditTextBackground(editText : EditText, nickname : String) {
        if (nickname.isEmpty()) editText.setBackgroundResource(R.drawable.border_gray_line_round_8)
        else {
            if (Pattern.matches("^[0-9가-힣ㄱ-ㅎㅏ-ㅣ\u318D\u119E\u11A2\u2022\u2025a\u00B7\uFE55]+$", nickname))
                editText.setBackgroundResource(R.drawable.border_navy_line_round_8)
            else
                editText.setBackgroundResource(R.drawable.border_red_line_round_8)
        }
    }

    @BindingAdapter("setLengthText")
    @JvmStatic
    fun setLengthText(textView : TextView, nickname : String) {
        textView.text = nickname.length.toString()
    }

    @BindingAdapter("setBtnActive")
    @JvmStatic
    fun setBtnActive(button : Button, nickname : String) {
        if (Pattern.matches("^[0-9가-힣ㄱ-ㅎㅏ-ㅣ\u318D\u119E\u11A2\u2022\u2025a\u00B7\uFE55]+$", nickname))
            button.setBackgroundResource(R.drawable.border_navy_fill_round_40)
        else button.setBackgroundResource(R.drawable.border_gray_fill_round_40)
    }

    @BindingAdapter("setBtnCheck")
    @JvmStatic
    fun setBtnCheck(imageButton : ImageButton, nickname : String) {
        if (Pattern.matches("^[0-9가-힣ㄱ-ㅎㅏ-ㅣ\u318D\u119E\u11A2\u2022\u2025a\u00B7\uFE55]+$", nickname)) imageButton.visibility = View.VISIBLE
        else imageButton.visibility = View.INVISIBLE
    }
}
```


<br/>

#### 3. DataStore

**🍯solution**

- [DataStore](https://github.com/MilkyOnOurWay/Milkyroid/blob/develop/app/src/main/java/com/milkyway/milkyway/util/DataStore.kt)

```kotlin
// DataStore for saving Token & Nickname

class DataStore(context: Context) {
    private val dataStore: DataStore<Preferences> = context.createDataStore(
        name = "data_store"
    )    
		val getNickname: Flow<String?> = dataStore.data.map { preferences ->
        preferences[nicknameKey]?:""
    }   suspend fun setNickname(nickname: String) {
        dataStore.edit { preferences ->
            preferences[nicknameKey] = nickname
        }
    }    

		val getToken: Flow<String?> = dataStore.data.map { preferences ->
        preferences[tokenKey]?:""
    }   suspend fun setToken(token: String) {
        dataStore.edit { preferences ->
            preferences[tokenKey] = token
        }
    }    

		companion object {
        val nicknameKey = preferencesKey<String>("nickname")
        val tokenKey = preferencesKey<String>("token")
    }
}
```


<br/>

#### 4. Keyboard

**🌋issue**

검색 뷰에서 키보드가 시야를 방해하고, 사용성을 고려했을 때 키보드를 숨기는 기능이 필요했다.

**🍯[solution](https://github.com/MilkyOnOurWay/Milkyroid/blob/develop/app/src/main/java/com/milkyway/milkyway/ui/search/homesearch/CafeSearchActivity.kt)**

키보드를 숨기는 함수를 만들었고 빈 화면이 터치되었을 때, 검색결과 리스트를 스크롤 시(터치) 그 외의 화면이 터치되었을 때에 조건을 걸어 사용성을 높였다.

```kotlin
private fun Context.hideKeyboard(view: View) {
        val inputMethodManager =
            getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }


rv.addOnItemTouchListener(object : RecyclerView.OnItemTouchListener {
            override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
                hideKeyboard()
                return false
            }
            override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {
            }
            override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {
            }
        })

clCafeSearch.setOnClickListener {
            hideKeyboard()
        }
emptyView.setOnClickListener {
            hideKeyboard()
```

<br/>


#### 5. RecyclerView

**🌋issue**

리사이클러뷰의 추가, 수정, 삭제 : 새로운 액티비티에서 수정하여 프래그먼트로 결과를 가져오는 부분에 있어서 onActivityResult가 해당 프래그먼트에 오지 않았다.

**🍯[solution](https://github.com/MilkyOnOurWay/Milkyroid/blob/develop/app/src/main/java/com/milkyway/milkyway/ui/report/cafereport/CafeReportFragment.kt)**

어댑터에는 버튼 클릭 리스너를 두고, 팝업 클릭 리스너와 메뉴 클릭 리스너를 프래그먼트로 가져옴으로써 해결할 수 있었다. 

```kotlin
//recyclerview adapter 내에 구현
override fun onBindViewHolder(holder: CafeReportMenuViewHolder, position: Int) {
        holder.onBind(data[position])
        if (itemClick != null) {
            holder.btnViewOption.setOnClickListener { v ->
                itemClick?.onClick(v, position)
            }
        }
    }

//recyclerview가 있는 fragment에 구현
cafeReportMenuAdapter.itemClick = object : CafeReportMenuAdapter.ItemClick {
     override fun onClick(view: View, position: Int) {
          val popupMenu: PopupMenu = PopupMenu(
                ContextThemeWrapper(
                    context,
                    R.style.PopupMenuStyle
                  ), view
               )
               popupMenu.inflate(R.menu.option_menu)
               popupMenu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener() {
                  onMenuItemClick(context as MainActivity, it, position)
              })
               popupMenu.show()
           }
       }

    private fun onMenuItemClick(
        context: Context,
        item: MenuItem,
        position: Int
    ): Boolean {
        when (item.itemId) {
            R.id.edit -> {
                val editMenuIntent =
                ....
                startActivityForResult(editMenuIntent, CafeReportMenuActivity.EDIT_CODE)
            }
            R.id.delete -> {
                cafeReportMenuAdapter.removeItem(position)
                buttonActive()
            }
        }
        return false
    }
```

<br/>


#### 6. Custom Dialog

**🌋issue**

- 다이얼로그 radius
- 다이얼로그의 클릭 이벤트를 밖에서 제어해야한다.
- custom view

**🍯solution**

- [radius - 스타일 조정](https://github.com/MilkyOnOurWay/Milkyroid/blob/develop/app/src/main/res/values/themes.xml)

```xml
<style name="AlertDialogRound8" parent="Theme.AppCompat.Light.Dialog.Alert">
        <item name="dialogCornerRadius">8dp</item>
        <item name="android:layout_width">320dp</item>
        <item name="windowFixedWidthMajor">320dp</item>
</style>
```

- [custom dialog class 구현](https://github.com/MilkyOnOurWay/Milkyroid/blob/develop/app/src/main/java/com/milkyway/milkyway/util/ConfirmAlertDialog.kt)

  4가지 타입으로 custom 진행

```kotlin
class ConfirmAlertDialog(context: Context, val type:Int) {

    private lateinit var bindingWithTitle: DialogAlertWithTitleBinding
    private lateinit var bindingWithoutTitle: DialogAlertWithoutTitleBinding
    private lateinit var bindingDeleteUniverse: DialogDeleteUniverseBinding
    private lateinit var bindingDeleteCancel: DialogShowcancelMyreportBinding
...
    companion object {
        private const val MODIFY_CONFIRM = 1
        private const val UNIVERSE_CONFIRM = 2
        private const val UNIVERSE_DELETE = 3
        private const val CANCEL_DELETE = 4
    }

}
```

- dialog view 비율에 맞게 하기 위해 setWindow() 를 builder.create() 하기 전에 실행

```kotlin
		private fun setWindow() {
        dialog?.window?.apply {
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            requestFeature(Window.FEATURE_NO_TITLE)
        }
    }

    fun create():ConfirmAlertDialog {
        setWindow()
        dialog = builder.create()
        return this
    }
```

- 클릭이벤트를 함수형으로 인자를 받아 실행

```kotlin
fun show(listener: ()->Unit):ConfirmAlertDialog{

        dialog?.setCancelable(false)
        dialog?.setCanceledOnTouchOutside(false)
        setPositiveButton(listener)
        dialog?.show()
        return this
    }
```

- [in ModifyActivity](https://github.com/MilkyOnOurWay/Milkyroid/blob/develop/app/src/main/java/com/milkyway/milkyway/ui/modify/ModifyActivity.kt)

```kotlin
private fun observeDeleteClick(cafeId:Int){
    modifyViewModel.isDeleteClick.observe(this, Observer{ isDeleteClick->
        Log.e("isDeleteClick",isDeleteClick.toString())
        if(isDeleteClick) {
             **ConfirmAlertDialog(this,1).create().show{ deleteLocation(cafeId)}**
        }
    })
}
```

<br/>


#### 7. Recyclerview Delete

**🌋issue**

프래그먼트에서 cancelViewModel.notifyItemRemoved()를 사용했을 때, 리사이클러뷰 아이템이 한개씩 밀려서 삭제되는 문제가 있었다.

**🍯[solution](https://github.com/MilkyOnOurWay/Milkyroid/blob/develop/app/src/main/java/com/milkyway/milkyway/ui/report/myreport/MyReportFragment.kt )**

```kotlin
cancelAdapter.onClickListener = {
    cancelViewModel.setDeleteCancelClick()
    cancelViewModel.clickCafeId.value = cancelAdapter.clickItemCafeId
    Log.e("삭제아이템클릭", cancelViewModel.deleteCancel.value.toString())
}
```

여기서 리사이클러뷰의 어떤 아이템이 클릭 되었는지 확인

```kotlin
// 아이템 누르면 선택(true)되어 서버통신하고 다이얼로그 띄움
    private fun observeItemClickDelete() {
        deleteCancelDialog = ConfirmAlertDialog(requireContext(), 4).create()
        cancelViewModel.deleteCancel.observe(
            viewLifecycleOwner, Observer { deleteCancel ->
                if (deleteCancel) {
                    requestDeleteCancelData()
                    deleteCancelDialog.show {
                        cancelViewModel.setDeleteCancelClick()
                        cancelAdapter.deleteItem()}
                } else {
                    deleteCancelDialog.dismiss()
                }
            })
```

notifyItemRemoved()를 쓰는 대신 어댑터에서 datas.removeAt(clickItemPosition), notifyDataSetChanged()를 호출하는 함수→ setDeleteCancelClick를 만들어 서버통신이 되면 각 id를 통해 선택한 아이템만 삭제되도록 구현.


<br/>



## 👊Milkyroid

> 💻 Milkyway's Android Developer

|          **🙋 [김영민](https://github.com/kym1924)**          |        **🙋‍ [김회진](https://github.com/bluelemon9)**         |          **🙋‍ [이수정](https://github.com/doodung)**          |        **🙋‍ [정은이](https://github.com/EuneeChung)**         |
| :----------------------------------------------------------: | :----------------------------------------------------------: | :----------------------------------------------------------: | :----------------------------------------------------------: |
| <img src="https://user-images.githubusercontent.com/63637706/104727150-34e44800-5778-11eb-8de1-9247bf2022f6.png" width="200" height="200" /> | <img src = "https://user-images.githubusercontent.com/63637706/104727153-36157500-5778-11eb-92c6-aaef151438f3.png" width="200" height="200" /> | <img src = "https://user-images.githubusercontent.com/55903679/103664250-68bfb080-4fb5-11eb-80c2-f1a4ee334dcb.png" width="200" height="200" /> | <img src = "https://user-images.githubusercontent.com/55903679/103664254-6a897400-4fb5-11eb-98e1-1a3773023af4.png" width="200" height="200" /> |
|                    Android Lead Developer                    |                      Android Developer                       |                      Android Developer                       |                      Android Developer                       |

