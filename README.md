# Project Title / MultiTabs

3개의 탭에서 각각 연락처, 갤러리, 지도 기능을 동작하는 안드로이드 애플리케이션

## Team Mates : 김경하 최지호

## Main Tabs / Navigation Bar

* ViewPager2를 이용하여 TabLayout을 갖는 3개의 탭을 생성
* TabLayout에는 아이콘을 추가하여 탭의 기능을 한눈에 알아볼 수 있도록 하였으며
* action bar는 제거하고 status bar와 전체적인 앱 색상을 임의적으로 설정

## Tab1 - Contacts (연락처)

* cursor를 사용하여 기기의 저장소에서 연락처를 불러와 ListView에 데이터(이름, 전화번호)를 출력하였으며
* 짧게 터치시 다이얼에 번호를 자동 입력
* 길게 터치시 바로 전화 연결 기능
* 이름 또는 전화번호로 검색 가능

## Tab2 - Gallary (카메라 앨범)

* cursor를 사용하여 기기의 사진들을 불러왔으며 recyclerView에 출력
* 사진을 클릭하면 해당 사진을 확대해서 보여주고, 파일명을 보여주도록 구현
* 확대된 사진에서 줌 인, 줌 아웃, 원래대로 기능을 구현
* Floating Action Button을 추가하여 카메라 아이콘을 클릭할 시 카메라가 켜지도록 하였으며 촬영 후에는 Pictures파일에 찍은 사진이 저장됨
* AlertDialog를 사용하여 사진을 길게 누르면 삭제 문구가 뜨도록 하였으며
* Yes를 누르면 "Deleted" Toast 문구가 뜨면서 사진이 삭제됨.
* 새로고침 Floating Action Button을 누르면 갤러리가 갱신됨

* 사진 삭제는 지금까지 삭제한 파일의 경로를 기억하는 deletedFile ArrayList를 통해 구현함.
* 만일 불러오려는 파일의 경로가 deletedFile에 있다면 갤러리에 사진을 표시하지 않음.

## Tab3 - Map (지도)

* Google Maps API를 활용하여 지도 구현
* 지도 내에서 해당 지점 클릭시 클릭 지점 좌표로 이동
* 일정 반경내의 주변 건물을 확인 할 수 있게끔 원 형태로 3km 내의 반경표시
* <del> GPS를 기반으로 현재 위치 정보를 받는 기능도 구현하였으나 클릭 지점 좌표 이동 기능과 충돌하여 현재 보류중 </del>
 
### ScreenShots

```
예시
```

