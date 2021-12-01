# Java Convention

### Author
- Gillog
- Sora

### Updated
- 2021.12.02

### Description
- Java 관련 Code Convention

---

# Package
### 패키지 명은 Lower Case 작성을 원칙으로 한다.
### 숫자 사용을 금지하고 영문으로만 표기한다.

## Example
```java
package com.buglifer.yagola.common; 
package com.buglifer.yagola.controller;
```

---

# Variable

### 언더스코어(_) 없이 Lower Camel Case 작성을 원칙으로 한다.
### 변수명이 길어져도 약어 사용을 금지한다.
- ID 등의 대표적인 약어 명사들은 허용
- 위 약어 사용 시 대문자 형태로 표기
- Java 예약어 등은 약어 사용을 허용
### 숫자 사용을 금지하고 영문으로만 표기한다.
### 상수형 변수는 대문자로 표기하고 언더스코어를 사용한다.
### 명사형태를 사용한다.

## Example

```java
// O
int menuCount = 0;

// X
int menu_cnt2 = 0;

// O
static final String CONST_VARIABLE = "Constants";

// X
static final String const_variable2 = "Constants";

// O
String userID = "";

// X
String userId = "";

// O
String str = "";

// X
String string = "";
```

---

# Class

### 언더스코어(_) 없이 Upper Camel Case 작성을 원칙으로 한다.

### Class 명이 길어져도 약어 사용을 금지한다.

### 숫자 사용을 금지하고 영문으로만 표기한다.

### 명사형태를 사용한다.


## Example

```java

// O
public class TestClass { }

// X
public class Test_Class { }

// O
public class UserLike { }

// X
public class LikeUser { }
```


---

# Interface

### 언더스코어(_) 없이 Upper Camel Case 작성을 원칙으로 한다.

### 형용사, 형용사절 형태로 작성한다.

### 숫자 사용을 금지하고 영문으로만 표기한다.

```java
// O
public interface Likeable {}

// X
public interface LikeInterface {}
```


---

# Method

### 동사형, 전치사형으로 시작한다.
### 언더스코어(_) 없이 Lower Camel Case 작성을 원칙으로 한다.
### 약어사용을 철저히 금지한다.
### 네 개이상의 파라미터 사용을 금지한다.


## Example

```java
// O
public void getUserLikeStatus(String userID) {}

// X
public void userLikeStatus(String userID) {}

// X
public void setUserStatus(String userID, booelan isAllowed, String message, int userCount) {}
```

---

# Declaration

### 선언은 최상단에 선언한다.


```java
// O
public void getUserStatus(String userID) {
    int userCount = 0;
    boolean isAllowed = false;
    String result = "";
        ...(Logic)
}

// X
public void getUserStatus(String userID) {
    int userCount = 0;
    boolean isAllowed = false;
        ...(Logic)
    String result = "";
        ...(Logic)
}
```

---

# Line-Wrapping

### 한 줄의 최대 너비는 100자를 원칙으로한다.
- 100자 이내에서 줄 바꿈 가능
- 100자 초과시 반드시 줄 바꿈

### 줄바꿈 이후 최초 시작 한줄은 1단계 들여쓰기를 추가한다.

### 가독성을 위한 줄바꿈을 원칙으로 한다.
- `제어문`
- `throws`

### `,` 줄 바꿈 시 줄 바꿈 이후 `,` 사용을 원칙으로 한다.

## Example
```java
// O
StringBuilder strBuilder = new Builder()
                                    .append("!!!!")
                                    .append("!!!!!!")
                                    .build();

// X
StringBuilder strBuilder = new Builder().append("!!!!").append("!!!!!!").build();

// O
List<> userSet = new LinkedList<String>({
        "Gillog"
        , "Sora"
        , "Jeje"
        , "BJ"
});

// O
List<> userSet = new LinkedList<String>({
        "Gillog",
        "Sora",
        "Jeje",
        "BJ"
});
```
---

# Indentation

### 들여쓰기는 Tab 사용을 원칙으로한다.
### 스페이스 4개 들여쓰기를 금지한다.
### Class, Method, 제어문 등 Code Block 생성 시 마다 1단계 씩 들여쓰기 한다.

## Example

```java
// O
public void getUserStatus(String userID) {
    int userCount = 0;
    if(userID == null) {
        ...(logic)
        return;
    }
}

// X
public void getUserStatus(String userID) {
    int userCount = 0;
    if(userID == null) {
    ...(logic)
    return;
    }
}
```