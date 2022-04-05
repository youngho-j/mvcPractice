# 쇼핑몰 관리자 페이지 구현

<img src=""></img>

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

## Reference  
- [JUnit docs](http://junit.sourceforge.net/javadoc/org/junit/Assert.html)    
- [튜나 개발일기 JUnit 단위 테스트, assert 메서드 정리](https://devuna.tistory.com/39)  
- [플람 JUnit4](https://flamme1004.tistory.com/80?category=847154)  
