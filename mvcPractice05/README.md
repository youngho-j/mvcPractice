# 웹 크롤러 만들기

<img src="https://user-images.githubusercontent.com/65080004/158995227-ca963f3b-69b8-4674-9aad-ff2d70b52dc0.png"></img>

- Jsoup을 활용하여 뉴스 기사 크롤러 구현

  웹 크롤링 학습을 목적으로 Jsoup, JSON, bootstrap을 활용하여 만든 토이 프로젝트입니다.

## 환경

- Java 8
- Spring 5.2.6

## 구현하고자한 기능

- 크롤링 데이터(뉴스)를 출력
- 최신 기사 , 관련 기사  구분
- 크롤링한 데이터 다운로드(추가)

## 학습 내용 정리

<details>
<summary>Jsoup class</summary>

- method  
  - public static Connection connect(String url)    
    정의된 요청 URL을 사용하여 새 연결을 만듦  
    웹 URL(http 및 https 프로토콜)만 지원  
  
- Connection Interface  
  - Connection header(String name, String value)  
    요청 헤더 설정  
    
    > 헤더 속성  
    - 참고  
       - ;q= (인자 가중치) : 표현된 선호도 순서로 배치  
         기본 값 1(인자가중치 표현이 없을 경우), 소수점 이하 3자리까지 나타냄, 높은 값 우선  
         [인자 가중치 참고](https://developer.mozilla.org/en-US/docs/Glossary/Quality_values)  
       - Content negotiation : 동일한 URI에서 리소스의 서로 다른 버전을 서브하기 위해 사용되는 메커니즘  
         요청을 받은 서버 측에서 콘텐츠 협상을 통해 header 값에 저장된 요청을 읽어 클라이언트에게 반환  
         [Content negotiation 참고](https://developer.mozilla.org/ko/docs/Web/HTTP/Content_negotiation)  
  
    - Accept : 클라이언트가 이해 할 수 있는 콘텐츠 유형(MIME 유형 Ex> image/jpg)을 나타냄  
      [Accept 기본값 참고](https://developer.mozilla.org/en-US/docs/Web/HTTP/Content_negotiation/List_of_default_Accept_values)   
    - Accept-Encoding : 클라이언트가 이해 할 수 있는 컨텐츠 인코딩이 무엇인지 나타냄  
      ```
      gzip  
      32비트 CRC와 함께 Lempel-Ziv coding (LZ77)를 사용하는 압축 포맷  
      
      deflate
      deflate 압축 알고리즘과 함께 zlib 구조를 사용하는 압축 포맷
      ```
    - Accept-language : 어떤 언어를 클라이언트가 이해할 수 있는지, 그리고 지역 설정 중 어떤 것이 더 선호되는지를 알려줌 
      ```
      표기 형식
      
      language
      Ex> en 
  
      language-locale
      Ex> en-US 
      ```
      [ISO Language Code 참고](http://www.lingoes.net/en/translator/langcode.htm)  
    - connection : 현재의 전송이 완료된 후 네트워크 접속을 유지할지 말지를 제어  
      기본값 : close  
      keep-alive 설정시 연결이 지속되고, 동일 서버에 대한 후속 요청이 가능해짐  
    - get : GET 요청을 실행하고 결과를 파싱(return Document)  
      
  - Connection timeout(int millis)  
    시간 초과 범위를 설정할 수 있음  
    기본 값 : 30초(30000 millis)   
    타임아웃 발생시 SocketTimeoutException 발생  
  - Connection userAgent(String userAgent)  
    요청 헤더에 사용자 에이전트 정보 설정  
    String userAgent - 서버와 네트워크 피어가 요청한 사용자 에이전트의 정보(어플리케이션, 운영 체제, 버전 등)를 식별할 수 있도록 하는 특성 문자열  
    [user-Agent 참고](https://developer.mozilla.org/en-US/docs/Web/HTTP/Headers/User-Agent)    
  - Connection ignoreContentType(boolean ignoreContentType)  
    요청에 따른 응답을 파싱할때 document content-type 무시  
    기본값 : false  
    인식할 수 없는 content-type은 IOException을 발생시킬 수 있음  
</details>

## Reference
- [Jsoup 웹 크롤링](https://learntutorials.net/ko/jsoup/topic/319/jsoup%EB%A1%9C-%EC%9B%B9-%ED%81%AC%EB%A1%A4%EB%A7%81)  
- [HTTP Reference](https://developer.mozilla.org/en-US/docs/Web/HTTP#reference)  
- [Jsoup.org Docs](https://jsoup.org/apidocs/org/jsoup/Jsoup.html#connect(java.lang.String))  
- [Jsoup 튜토리얼](https://www.javacodeexamples.com/jsoup-tutorial-with-examples/1628)  
