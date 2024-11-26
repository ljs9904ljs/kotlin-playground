# kotlin-playground
Kotlin 언어 자체에 대한 연습에 집중하기 위한 repository


- VSCode kotlin 개발 환경 설정
    - Code-Runner extension 설치 그리고 Code-Runner의 settings.json 수정
        - "kotlin": "C:\\kotlin\\kotlinc\\bin\\kotlinc $fileName -include-runtime -d $fileNameWithoutExt.jar && java -ea -jar $fileNameWithoutExt.jar && del $fileNameWithoutExt.jar"
            - java의 -ea 옵션은 Enabling Assertions 옵션이다. 이게 default로는 꺼져 있는데, 이렇게 되면 assert()로 발생시키는 예외가 동작하지 않는다. 그래서 켜준 것이다.