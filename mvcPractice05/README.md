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
<details>
<summary>AJAX</summary>

 - AJAX : 비동기 자바스크립트와 XML(Asynchronous JavaScript And XML)  
   `서버와의 통신을 위해 XMLHttpRequest 객체를 사용하는 것`  
   
   - XMLHttpRequest : 서버와 상호작용하기 위해 사용되는 객체  
     전체 페이지의 새로고침 없이도 URL 로부터 데이터를 받을 수 있음  
     모든 종류의 데이터를 받을 수 있고, HTTP 이외의 프로토콜도 지원(file, ftp 포함)    
     [XMLHttpRequest 참고](https://developer.mozilla.org/ko/docs/Web/API/XMLHttpRequest)  
   - AJAX 특징  
     ```
     페이지 전체를 리프레쉬 하지 않아도 수행 가능 -> `비동기성` 
     ```
   - 위와 같은 특징을 이용하여 백그라운드 영역에서 서버와 통신하여 받아온 결과를 웹 페이지 일부분에 표시 가능  
   - 동작원리  
     <img src="http://www.tcpschool.com/lectures/img_ajax_ajax_application.png"></img>  
     [출처 - TCP School](http://www.tcpschool.com/ajax/ajax_intro_works)  
   - Property
     |key|설명|
     |:-:|:-:|
     |type|Http 요청 방식 [Get, post]|
     |url|Http 요청이 전송되는 URL이 포함된 문자열|
     |data|서버로 보낼 데이터|
     |success|Http 요청 성공시 호출되는 함수|
     |error|Http 요청 실패시 호출되는 함수|
     |dataType|서버 응답시 예상되는 데이터타입|
     |contentType|서버에 데이터를 보낼 때 사용할 콘텐츠 유형|
     |xhr|XMLHttpRequest 객체 생성을 위한 콜백|
     |xhrFields|XHR 객체에 설정할 fieldName:fieldValue 형식으로 이루어진 값|
     
     [jQuery.ajax() 참고](https://api.jquery.com/jquery.ajax/)    
</details>
<details>
<summary>Promise function</summary>

 - Promise : 자바스크립트 비동기 처리에 사용되는 객체  
   비동기 연산이 종료 된 이후 결과 값과 실패 사유를 처리할 수 있음 즉, 동기 메서드 처럼 값을 반환 할 수 있다는 것  
   단, 결과를 반환하는 것이 아니고 미래의 어떤 시점에 결과를 제공하겠다는 `약속(프로미스) 반환`  
   
   참고! 비동기 처리? `특정 코드의 실행이 완료될 때까지 기다리지 않고 다음 코드를 먼저 수행하는 자바스크립트의 특성`  
   
   - 상태(Producer - 함수를 선언한다)  
     - 대기(pending) : 초기 - 비동기 처리 로직이 아직 완료되지 않은 상태  
       ```javascript
       // 예시
       new Promise();
    
       // new Promise() 호출시 콜백 함수 선언 가능하며, 인자는 resolve, reject
       new Promise(function(resolve, reject) {
         // ...
       });
       ```
   	 - 이행(fulfilled) : 연산 성공(resolve) - 비동기 처리가 완료되어 프로미스가 결과 값을 반환해준 상태  
       ```javascript
       // 예시
       new Promise(function(resolve, reject) {
         resolve();
       });
       ```
     - 거부(reject) : 실패(오류발생) - 비동기 처리가 실패하거나 오류가 발생한 상태  
       ```javascript
       // 예시
       new Promise(function(resolve, reject) {
         reject();
       });
       ```
   - Consumer - 함수를 사용한다  
     - then : Promise의 resolve or reject 된 결과를 리턴  
       ```javascript
       // 예시
       function getData() {
         return new Promise(function(resolve, reject) {
           var data = 100;
           resolve(data);
         });
       }

       // resolve()의 결과 값 data를 resolvedData로 받음
       getData().then(function(resolvedData) {
         console.log(resolvedData); // 100
       });
       ```
     - catch : 에러 핸들링  
       ```javascript
       // 예시
       function getData() {
         return new Promise(function(resolve, reject) {
           reject(new Error("Request is failed"));
         });
       }

       // reject()의 결과 값 Error를 err에 받음
       getData().then().catch(function(err) {
         console.log(err); // Error: Request is failed
       });
       ```
     - finally : 성공 실패 상관없이 마지막에 호출
    
   - 처리 흐름  
     <img src="https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Promise/promises.png"></img>  
     [출처 - mdn web docs](https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Global_Objects/Promise)  
</details>
<details>
<summary>ResponseEntity? ResponseBody?</summary>
  
 - HTTP : HyperText Transfer Protocol, 클라이언트와 서버의 `요청과 응답을 처리하기 위한 규약`  
   - HTTP 요청 요소  
     1. Start Line : method, URL, version 으로 구성, 서버에서 요청을 받아들이는 첫 줄  
     2. Headers : 요청에 대한 접속 운영체제, 브라우저, 인증 정보와 같은 부가적인 정보  
     3. Body : 요청에 관련된 json, html과 같은 구체적인 내용  
   - HTTP 응답 요소
     1. Status Line : HTTP 버전과 함께 헤딩 요청에 대한 처리 상태(StatusCode)를 나타냄  
     2. Headers : 응답에 대한 접속 운영체제, 브라우저, 인증 정보와 같은 부가적인 정보    
     3. Body : 응답에 관련된 json, html과 같은 구체적인 내용  
  
 - HTTP Response를 만드는 방법  
   1. @ResponseBody  
      - 반환된 객체가 HttpMessageConverter를 통해 JSON으로 직렬화 되고 `HttpResponse 객체`(body)로 다시 전달됨(바인딩)을 알려줌  
      - 자바 객체를 HTTP 응답 본문의 객체로 변환하여 클라이언트로 전송  
      - 사용시 별도의 뷰를 제공하지 않고, 데이터만 전송  
      - 장점
        ```
        Annotation 추가만으로 간단하게 처리 가능

        @RestController Annotation 사용시 @ResponseBody Annotation 생략 가능
        ```
      - 단점
        ```
        Header에 대해 유연한 설정을 할 수 없음

        Status도 메서드 밖에서 Annotation을 사용하여 따로 설정해야함 Ex)@ResponseStatus
        ```
   2. ResponseEntity 
      - 전체 HTTP 응답(상태 코드, 헤더 및 본문)을 나타내며, 이를 사용하여 HTTP 응답을 완전하게 구성 가능
      - 응답으로 변환될 정보를 모두 담은 요소들을 객체로 만들어서 반환  
      - HttpMessageConverter를 통해 JSON으로 직렬화 되고, 응답이 되는 본문을 처리 한 뒤, StatusCode를 전달  
      - Builder 활용 권장 - 상태 코드 작성시 잘못된 코드를 넣게 될 수 있으므로..  
      - 구조
        ```java
        //HttpEntity 선언 구조
        public class HttpEntity<T> {
            public static final HttpEntity<?> EMPTY = new HttpEntity<>();
            
            private final HttpHeaders headers;

            @Nullable
            private final T body; 
        }

        //ResponseEntity 선언 구조 - HttpEntity를 상속하여 구현되어 headers, body, status 값을 모두 가질 수 있음
        public class ResponseEntity extends HttpEntity {
          private final Object status;
        }
        ```
</details>  
  
## Reference  
- [Jsoup 웹 크롤링](https://learntutorials.net/ko/jsoup/topic/319/jsoup%EB%A1%9C-%EC%9B%B9-%ED%81%AC%EB%A1%A4%EB%A7%81)  
- [HTTP Reference](https://developer.mozilla.org/en-US/docs/Web/HTTP#reference)  
- [Jsoup.org Docs](https://jsoup.org/apidocs/org/jsoup/Jsoup.html#connect(java.lang.String))  
- [Jsoup 튜토리얼](https://www.javacodeexamples.com/jsoup-tutorial-with-examples/1628)  
- [jQuery api](https://api.jquery.com/)  
- [mdn web docs Promise](https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Global_Objects/Promise)  
- [captain pangyo Promise 쉽게 이해하기](https://joshua1988.github.io/web-development/javascript/promise-for-beginners/)   
- [Tecoble ResponseEntity](https://tecoble.techcourse.co.kr/post/2021-05-10-response-entity/)    
- [Spring docs](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/http/ResponseEntity.html)   
- [Baeldung spring ResponseEntity](https://www.baeldung.com/spring-response-entity)  
- [bepoz HttpMessageConverter 적용시점](https://bepoz-study-diary.tistory.com/374)  