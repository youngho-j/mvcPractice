- # 쇼핑몰 관리자 페이지 구현

  <p align="center">
    <img src="https://user-images.githubusercontent.com/65080004/162396503-e94707ae-ef47-4612-a394-9e1d73a3c61f.png" width="80%" height="80%"></img>
  </p>

  - 쇼핑몰 관리자 페이지 구현

    Spring Security, Junit 학습을 목적으로 만든 토이 프로젝트입니다.

  ## 환경

  - Java 11
  - Spring 5.2.6

  ## 구현하고자한 기능

  - 상품 정보, 작가 정보 등록, 수정, 삭제
  - 상품 이미지 등록 및 출력
  - Spring Security 회원가입, 로그인, 로그아웃
  - 상품 검색

  ## 학습 내용 정리

  <details>
  <summary>JUnit</summary>

  - JUnit?  
    - 자바용 `단위 테스트` 도구  
      단위 테스트?  
      ```
      - 소스코드의 특정 모듈이 의도된 대로 정확히 작동하는지 검증하는 절차
    
      - 모든 함수와 메소드에 대한 테스트 케이스(Test case)를 작성하는 절차
      ```
    - 특징
      - 단정(assert) 메서드로 테스트 케이스의 수행 결과를 판별  
        Ex) assertEquals(예상값, 실제값)  
    
      - jUnit4부터는 테스트를 지원하는 어노테이션을 제공  
        Ex) @Test, @Before, @After  
      
      - @Test가 적용된 메서드를 호출할 때마다 새로운 인스턴스를 생성하여 독립적인 테스트가 이루어지도록 함  
    - 어노테이션  
      `@Test`  
       - 메서드 위에 해당 어노테이션을 선언하여 테스트 대상 메소드임을 지정할 수 있음  
         ```java
         @Test 
         public void testMethod() {
         }
         ```
      `@Test(timeout=밀리초)`  
       - 테스트 메서드의 수행 시간을 제한할 수 있음   
       - 테스트 메서드가 리턴 값 반환시 소요되는 시간이 지정한 밀리초를 넘긴다면 해당 테스트는 실패로 판별  
         ```java
         @Test(timeout=5000) 
         public void testMethod() {
         }
         ```
    
      `@Test(expected=예외)`  
       - 해당 테스트 메소드 예외 발생 여부에 따라 성공/실패를 판별할 수 있음  
       - `expected=` 에 지정된 예외가 발생해야 테스트가 성공한 것으로 판별  
         ```java
         @Test(expected = NullPointerException.class)  
         public void testMethod() {
         }
         ```
    
      `@Ignore`  
       - 해당 어노테이션이 선언된 테스트 메소드를 실행하지 않도록 지정  
         ```java
         @Ignore 
         @Test 
         public void testMethod() {
         }
         ```
         
      
      `@Before (Junit5 - @BeforeEach)`  
       - 모든 @Test메소드가 실행되기 전에 실행되는 메소드를 지정하는 어노테이션  
       - 각 테스트 시작 전에 각각 호출  
       - @Test가 적용된 메서드에서 공통으로 사용되는 코드를 @Before 선언해 사용하면 좋음   
       - 테스트마다 공통으로 쓰이면서, 테스트 전에 리셋되어야 할 항목이 들어간다  
         ```java
         @Before 
         public void setUp() throws Exception {
         }
         ```
       ```
      
      `@After(Junit5 - @AfterEach)`  
       - 모든 @Test메소드의 실행이 끝난 뒤에 실행되는 메소드를 지정하는 어노테이션  
       - 각 테스트가 끝나고 각각 호출  
         ```java  
         @After 
         public void tearDown() throws Exception {
         }
       ```
      
      `@BeforeClass(Junit5 - @BeforeAll)`  
       - 해당 테스트 클래스가 실행될 때 딱 한 번만 수행되는 테스트 메소드를 지정하는 어노테이션  
         ```java  
         @BeforeClass 
         public static void setUpBeforeClass() throws Exception {
         }
         ```
       ```
      
      `@AfterClass(Junit5 - @AfterAll)`  
       - 해당 테스트 클래스가 실행이 끝난 뒤에 딱 한 번만 수행되는 테스트 메소드를 지정하는 어노테이션  
       - 테스트 클래스의 모든 테스트가 완료된 뒤 한 번 호출  
         ```java  
         @AfterClass 
         public static void tearDownAfterClass() throws Exception {
         }
       ```
      
    - assert 메서드(검증)
      ```java
      public static void assertArrayEquals(java.lang.Object[] expecteds, java.lang.Object[] actuals){}
      // 배열 expecteds 와 actuals가 일치함을 확인
      
      public static void assertEquals(java.lang.Object expected, java.lang.Object actual){}  
      // 객체 expected 와 actual가 값이 일치함을 확인  
      
      public static void assertSame(java.lang.Object expected, java.lang.Object actual){}  
    // 객체 expected 와 actual가 같은 객체임을 확인(같은 주소값을 참조하는지)
    
      public static void assertTrue(boolean condition){}
      // 조건이 참인지 확인
      
      public static void assertNotNull(java.lang.Object object){}
    // 객채가 null이 아님을 확인
    
      public static <T> void assertThat(T actual, org.hamcrest.Matcher<T> matcher){}  
      /* T actual : 비교 대상 값(검증대상)
       * Matcher<? super T> matcher : 비교로직(matcher에 의해 지정된 조건)  
       * (hamcrest에 구현된 matcher 사용하도록 강제됨)
       * */
      ```

  </details>
  <details>
  <summary>Spring Security</summary>

   - Spring Security?
     - Spring 기반의 어플리케이션의 `보안(인증과 권한, 인가 등)을 담당`하는 스프링 하위 프레임워크  
     - `'인증'과 '권한'에 대한 부분을 Filter 흐름에 따라 처리`  
       서블릿 Filter를 기반으로 서블릿을 지원
     - 기본적으로 Session & Cookie 방식으로 인증  
       ```
       참고. 단일 HTTP 요청 예시
       1. Client --- request --> application
       2. application 내 컨테이너가 'FilterChain'을 만들어 요청 URI path 기반으로 HttpServletReqest 처리
          
       FilterChain?
       Servlet과 여러 개의 Filter로 구성된 것
       주의! 필터 실행순서 중요!

       Servlet이 한 개인 이유
        - 단일 HttpServletRequest와 HttpServletResponse 처리는 최대 한 개의 Servlet이 담당하므로
       ```
     
     - 보안 관련 용어  
       ```
       접근 주체(Principal) 
        - 보호받는 대상(Resource)에 접근하는 대상
       
       증명서(Credential)
        - 보호받는 대상(Resource)에 접근하는 대상을 확인하기 위한 증명서(Password, 공인인증서 등..)
   
       인증(Authenticate)
        - 해당 사용자가 본인이 맞는지를 확인하는 절차
        - 애플리케이션의 작업을 수행할 수 있는 주체임을 확인
      
       인가(Authorization) 
        - 인증된 사용자가 요청한 자원에 접근 가능한지 여부를 결정하는 절차
        - 인가 과정에서 해당 리소스 접근에 대한 최소한의 권한을 가지고 있는지를 확인
        - 모든 리소스는 접근 제어 권한이 걸려있음.
       ```

     - 흐름  
       <img src="https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FoMnop%2FbtqEJh4jxCX%2FPhClhzrEpVOCRK7wYM5R4k%2Fimg.png">  
  [이미지 출처](https://mangkyu.tistory.com/76)</img>  
       
       - 인증과 인가를 위해 `Credential 기반의 인증 방식` 사용  
         Principal을 아이디로, Credential을 비밀번호로 사용

     - work flow  
       <img src="https://miro.medium.com/max/1400/1*oVUa7n_m5LkLRzcE-jN_EQ.png">
  [이미지 출처](https://medium.com/@satyakm.dev/understanding-spring-security-internals-with-code-walkthrough-850d5749252c)</img>  
       ```
       1. 클라이언트(브라우저)의 요청(Request)
       
       2. Web Server 요청 검색 후, 존재하지 않을 경우 웹 컨테이너(ServletContext)에게 요청 이관 
       
       3. web.xml 설정에 등록해둔 springSecurityFilterChain 필터(DelegatingFilterProxy class)가  
          요청을 가로 챔  
       
       4. 여러 필터 리스트(AuthenticationFilter)를 순회하면서 필터링 실시
       
       5. UsernamePasswordAuthenticationFilter 클래스의 attemptAuthentication(request, response)  
          메서드를 통해 username과 password를 얻어온 뒤 Authentication 인터페이스의 구현체인  
          UsernamePasswordAuthenticationToken(Authentication)을 생성  
       
       6. AuthenticationManager(Interface)을 구현한 ProviderManager(class)에 token 객체를 보냄
       
       7. ProviderManager는 받은 token을 멤버변수로 가지고 있는  
          AuthenticationProvider(Interface) 구현한 class들에게 처리할 수 있는지 물어봄(supports() 메서드)  
       
       8. 처리해줄 객체를 찾았을 떄 해당 인증과정을 처리해달라고 위임(authenticate() 메서드)
       
       9. 객체에서 UserDetailsService(Interface)의 loadUserByUsername(String username)를 통해  
          UserDetails 값을 얻어와 리턴
       
       10. 인증 성공시 ProviderManager는 요청이 인증되었다고 알리고,  
           인증이 성공한(isAuthenticated=true) 객체를 생성하여 Security Context에 저장,  
           인증 상태를 유지하기 위해 정보를 세션에 보관  
           인증 실패시 AuthenticationException 발생  

       ```
       - `DelegatingFilterProxy?`  
         Filter 구현체, 내부에 위임대상(FilterChainProxy)을 가지고 있음   
         서블릿 컨테이너와 Spring IOC 컨테이너를 연결해주는 역할  
       
       - `FilterChainProxy?`  
         서블릿 지원의 시작점이자 중심점  
         특별한 Filter, SecurityFilterChain을 통해 여러 Filter 인스턴스로 위임 가능  
         사용할 SecurityFilterChain 결정  
       
       - `SecurityFilterChain?`  
         Security Filter 묶음, 체인마다 고유한 각각의 설정을 가질 수 있음  
       
       - `SecurityContextHolder?`  
         보안 주체의 세부 정보를 포함하여 응용프로그램의 현재 보안 컨텍스트에 대한 세부 정보가 저장  
       
       - `SecurityContext?`  
         Authentication을 보관하는 역할, Authentication 객체를 꺼낼 수도 있음  
       
       - `Authentication?`  
         현재 접근하는 주체의 정보와 권한을 담는 인터페이스이며, Security Context에 저장  
         Authentication 객체를 가져오기 위해 SecurityContextHolder를 통해 SecurityContext에 접근한 뒤 Authentication에 접근할 수 있음  
  </details>

  ## Reference  
  - [JUnit docs](http://junit.sourceforge.net/javadoc/org/junit/Assert.html)    
  - [튜나 개발일기 JUnit 단위 테스트, assert 메서드 정리](https://devuna.tistory.com/39)  
  - [플람 JUnit4](https://flamme1004.tistory.com/80?category=847154)  
  - [토리맘의 한글라이즈 프로젝트 security](https://godekdls.github.io/Spring%20Security/servletsecuritythebigpicture/) 
  - [spring.io spring security](https://spring.io/guides/topicals/spring-security-architecture)  
  - [Mohapatra Easy way to learn Spring Security 5](https://medium.com/@satyakm.dev/understanding-spring-security-internals-with-code-walkthrough-850d5749252c)  
  - [도전하는 개발자 Spring Security 기초](https://minkukjo.github.io/framework/2021/01/10/Spring-Security-01/)  
  - [정아마추어 로그인 과정으로 살펴보는 스프링 시큐리티 아키텍처](https://jeong-pro.tistory.com/205)   
  - [망나니 개발자 Spring Security란?](https://mangkyu.tistory.com/76)  
  - [INCHEOL's log DelegatingFilterProxy](https://velog.io/@yaho1024/spring-security-delegatingFilterProxy)  
  - [Crucian Carp Spring MVC 동작 원리와 처리 흐름](https://aaronryu.github.io/2021/02/14/a-tutorial-for-spring-mvc-and-security/)  