# Frontend Convention

### Author
- Gillog
- Sora

### Updated
- 2021.12.03

### Description
- Front 관련 Code Convention
  - HTML
  - Script
  - CSS
---

# HTML

## id

- ID는 Lower Camel Case 표기법을 사용한다.
- 숫자 사용을 금지하고, 영문으로 표기한다.

## class

- Class는 하이픈 표기법을 사용한다.

## Indentation

- body Tag 안의 모든 attribute는 중첩 깊이에 따라 1 tab 들여쓰기를 한다.


## attribute

- Tag 안에 들어가는 attribute의 순서를 통일
- type | name | id | class | value | maxlength | size

```html
<input type="text" name="" id="" class="" value="" maxlength="64" size="28" />
```



---


# Script

## 변수 선언

- 식별자 이름 ( 변수 및 함수) 에는 camelCase를 사용합니다.
- 모든 이름은 문자로 시작합니다.
- var 를 이용하지 말고 const, let 을 이용합니다.
    - let을 사용할 이유가 없으면 최대한 const를 이용합니다.


## 연산자 주변 공백

- 연산자 ( = + - * / ) 와 쉼표(,) 뒤에는 공백을 넣습니다.

```jsx
var x = y + z;
var values = ["Volvo", "Saab", "Fiat"];
```


## 코드 들여 쓰기

- 코드 블록 들여쓰기시에 항상 탭을 사용 합니다.


## 구문 규칙

- 항상 세미콜론으로 간단한 문장을 끝냅니다.
- 복합문에 대한 일반 규칙
    - 첫번째 줄 끝에 여는 괄호를 넣습니다.
    - 여는 괄호 앞에 하나의 공백을 사용합니다.
    - 공백 없이 새 줄에 닫는 괄호를 넣습니다.

```jsx
if (time < 20) {
	greeting = "Good day";
} else {
	greeting = "Good evening";
}
```


## 객체 규칙

- 객체 이름과 같은 줄에 여는 대괄호를 배치합니다.
- 복수 요소를 나열할 때는 줄바꿈 이후 `,`와 1칸의 공백을 사용합니다.
- 각 속성뒤의 콜론(:)과 값 사이에 공백을 사용합니다.
- 숫자 값이 아닌 문자열 값은 따옴표("")로 묶습니다.
- 마지막 속성(값)뒤에 쉽표와, 세미콜론을 추가하면 안됩니다.
- 객체 선언을 끝낼때, 선행 공백없이 새줄에 닫는 대괄호를 배치합니다.
- 세미콜론으로 객체 정의를 끝냅니다.
- 짧은 객체는 속성사이에만 공백을 사용해서 한줄로 압축하여 작성합니다.

```jsx
var person = {
	firstName: "John"
	, lastName: "Doe"
	, age: 50
};
var person = {firstName:"John", lastName:"Doe", age:50};
```


## 라인 길이< 100

- 가독성을 위해 라인의 길이는 100줄 미만으로 합니다.
- 자바스크립트문이 한줄에 맞지 않는 경우, 연산자 위치 또는 쉼표 뒤를 기준으로 나눕니다.

## HTML에서 자바스크립트 로드

- 외부 스크립트를 로드하기 위해서 간단한 구문을 사용합니다.
- <script type="text/javascript" src="myscript.js"></script>


## 파일 확장자

- 자바스크립트 파일에는 .js 확장자를 적습니다.


## camelCase 파일 이름 사용

- 자바스크립트 파일은 소문자 파일이름을 사용해야합니다.

## Performance

- 개발중인 코드의 경우 가독성을 선호합니다.


## Modal 에서의 Javascript

- Modal 에서는 javascript를 쓰지 않고, Modal을 호출하는 Parent 에서만 Javascript를 사용한다.
- Modal 안에서 Document Ready Function에서 사용하는 로직,
  Element Event에 Function을 설정하는 코드들은 callback 함수를 선언하여,
  common.ajaxModal(, , callBack) 형식으로 Modal을 호출할 때 미리 선언한다.
- Modal안에서 불변하게 사용되는 변수, 객체, 함수들은 script로 분리하여,
  Modal 호출내부에 포함시켜 사용한다.


## 변수 및 함수 선언

- var 를 사용하지 말고, let, const 를 이용한다.
- 꼭 필요한 경우가 아니라면 가급적 const 를 이용한다.
- 함수는 화살표 함수를 쓰지말고 함수 표현식을 이용해서 선언한다.

---

# CSS

## HTML CSS

- HTML내부의 CSS 선언을 금지한다.
- CSS Package를 import하는 방식으로 사용한다.

## Inline CSS

- HTML Tag Attribute내의 Style 속성으로 CSS 선언을 금지한다.

## pacakge

- 공통 요소로 사용될 css는 공통 css 패키지에 작성을 원칙으로 한다.
- 각 페이지별 css는 css 패키지 내에 폴더 계층 구조로 css 파일을 생성한다.

## Common CSS 작성 우선

- 전역으로 사용할 수 있을 css 작성을 우선으로 한다.
  - 특정 상황에서만 쓰이는 css는 최대한 지양한다.
- 약어 사용을 원칙으로 한다.

## Line-Wrapping, Indentation
- `:` 작성전은 공백을 사용하지 않고, 작성 후 공백을 사용한다.
- css 본문이 1줄일 경우, 줄바꿈을 하지않고 1줄로 작성한다.
  - 2줄 이상일 경우 각 줄마다 줄바꿈을 한다.
- 시작 중괄호 이 후 한칸 공백, 종료 중괄호 전 한칸 공백을 사용한다.
  - 줄바꿈시에는 공백을 사용하지 않는다.

```css
// O
.p15 { padding: 15px; }
.fs20 { font-size: 20px; }

// X
.main-modal-font {
    padding: 15px;
    font-size: 20px;
}
```