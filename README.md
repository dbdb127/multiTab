# Project Title / MultiTabs

3개의 탭에서 각각 연락처, 갤러리, 지도 기능을 동작하는 안드로이드 애플리케이션

## Team Mates / 김경하 최지호

### Main Tabs / Navigation Bar

* ViewPager2를 이용하여 TabLayout을 갖는 3개의 탭을 생성
* TabLayout에는 아이콘을 추가하여 탭의 기능을 한눈에 알아볼 수 있도록 하였으며
* action bar는 제거하고 status bar와 전체적인 앱 색상을 임의적으로 설정

## Tab1 - Contacts

Contact(연락처)

* SimpleCursorAdapter를 사용하여 기기의 저장소에서 연락처를 불러와 ListView에 데이터를 출력하여 이름과 전화번호를 보여주도록 하였으며
* 짧게 터치시 다이얼에 번호를 자동 입력
* 길게 터치시 바로 전화 연결 기능

## Tab2 - Gallary

Gallery(카메라 앨범)

* cursor를 사용하여 기기의 사진들을 불러왔으며 recyclerView에 출력
* 사진을 클릭하면 해당 사진을 확대해서 보여주고, 파일명을 보여주도록 구현
* 확대된 사진에서 줌 인, 줌 아웃 기능을 구현
* 최대로 줌 아웃하거나 두 번 터치할 시 사진 크기가 원래대로 돌아감
* Floating Action Button을 추가하여 카메라 아이콘을 클릭할 시 카메라가 켜지도록 하였으며 촬영 후에는 Pictures파일에 찍은 사진이 저장됨
* 새로고침 Floating Action Button을 누르면 앨범이 갱신됨
* AlertDialog를 사용하여 사진을 길게 누르면 삭제 문구가 뜨도록 하였으며
* Yes를 누르면 "Deleted" Toast 문구가 뜨면서 사진이 삭제됨.
* 이 때에도 역시 새로고침 Floating Action Button을 누르면 갤러리가 갱신됨

* 사진 삭제는 지금까지 삭제한 파일의 경로를 기억하는 deletedFile ArrayList를 통해 구현함.
* 만일 불러오려는 파일의 경로가 deletedFile에 있다면 갤러리에 사진을 표시하지 않음.

## Tab3 - Map

Map(지도)
```
TBD
```

### 테스트는 이런 식으로 작성하시면 됩니다

```
예시
```

## Deployment / 배포

Add additional notes about how to deploy this on a live system / 라이브 시스템을 배포하는 방법
