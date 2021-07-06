# multiTab

프로젝트 이름: 
팀원: 김경하, 최지호
프로젝트 설명:
1. 멀티탭
ViewPager2를 이용하여 TabLayout을 갖는 3개의 탭을 생성
TabLayout에는 아이콘을 추가하여 탭의 기능을 한눈에 알아볼 수 있도록 하였으며
action bar는 제거하고 status bar와 전체적인 앱 색상을 임의적으로 설정

2. Contact(연락처)
SimpleCursorAdapter를 사용하여 기기의 연락처를 불러와 ListView에 데이터를 출력
이름과 전화번호를 보여주도록 하였으며
짧게 터치하면 다이얼에 번호를 입력하도록,
길게 터치하면 바로 전화를 걸 수 있도록 구현

3. Gallery(카메라 앨범)
cursor를 사용하여 기기의 사진들을 불러왔으며 recyclerView에 출력
사진을 클릭하면 하나의 사진만을 확대해서 보여주며 사진 위에 사진 이름을 보여주도록 구현
오픈소스를 사용하여 확대된 사진에서 줌 인, 줌 아웃 기능을 구현하였으며 
최대로 줌 아웃하거나 두 번 터치할 시 사진 크기가 원래대로 돌아감
Floating Action Button을 추가하여 카메라 아이콘을 클릭할 시 카메라가 켜지도록 하였으며
촬영 후에는 Pictures파일에 찍은 사진이 저장됨
새로고침 Floating Action Button을 누르면 앨범이 갱신됨
AlertDialog를 사용하여 사진을 길게 누르면 삭제 문구가 뜨도록 하였으며
Yes를 누르면 "Deleted" Toast 문구가 뜨면서 사진이 삭제됨.
이 때에도 역시 새로고침 Floating Action Button을 누르면 갤러리가 갱신됨

사진 삭제는 지금까지 삭제한 파일의 경로를 기억하는 deletedFile ArrayList를 통해 구현함.
만일 불러오려는 파일의 경로가 deletedFile에 있다면 갤러리에 사진을 표시하지 않음.
