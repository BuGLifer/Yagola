# Branch Strategy

### Author
- Gillog
- Sora
- Jeje
- BJ

### Updated
- 2021.12.05

### Description
- GitHub Repository Branch 전략 관련
  - Branch 종류
  - 소스 코드 최신화
  - 소스 코드 병합
---

# Branch

## 종류

- `Master` : 최종 Source Code 반영 본이 통합되어 운영에 사용되는 Branch
- `Develope` : 개발 단계 Source Code들이 `Pull Request`를 통해 병합되어 병합본이 저장되는 Branch
- `개인 Branch` : 각자 자신의 개인 Branch에 `Commit`들을,<br>`Issue` 별로 구분 지어 개발 Source Code를 저장하는 Branch

---

## Source Code 최신화

신규 기능 개발에 들어가기 앞서, `Origin/Develope` Branch를 자신의 `Local/Develope` Branch와

Sync를 맞춘 후, `Local/Develope` Branch를 `Local/개인 Branch`에 병합 하여 최신화 한다.

```bash
// Local Develope Branch 체크아웃
git checkout Develope

// Origin Develope Source Code 가져오기
git pull

// Local 개인 Branch 체크아웃
git checkout 개인Branch명

// Local/Develope Branch Source Code를 
// Local/개인 Branch 에 병합
git merge Develope

// Origin/개인 Branch에 최신화 Commit Push
git push
```

---

## Source Code 병합

Source Code 병합은 `GitHub`의 `PullRequest`를 활용한다.

### Pull Request 생성

개발 단계의 `Commit` 들을 `git commit -m "[#이슈번호] 커밋메시지"` 형태로 취합한 후,

개발 완료시에 `GitHub` 에서 `New pull request`를 생성한다.

![img.png](https://images.velog.io/images/gillog/post/dce33157-7fd3-42ba-84f3-9346ba76a1a3/img.png)


`One a pull request` 창에서 `Tilte` 부분에는 아래 양식대로 제목을 작성한다.

```
[#이슈번호] Complete 개발 기능명 
```
![img_3.png](https://images.velog.io/images/gillog/post/c8d074eb-64a1-410d-b7ba-6784196765f0/img_3.png)

이때 `base:Develope`, `compare:개인 Branch`를 확인한다.


해당 완료된 기능 개발 관련 첨부 Comment를 작성한 후,

우 측 `Reviewers`, `Assignees`, `Labels`, `Projects`, `Milestone` 부분도 작성한다.

- `Reviewers` : Review 담당, `Master` 선택
- `Assignees` : 개발 담당, 개발에 참여한 자신 혹은 추가 인원 선택
- `Labels` : 해당 기능 개발 관련 `Label` 선택
- `Projects` : `Develope` Project 선택
- `Milestone` : `Develope` Milestone 선택


![img_2.png](https://images.velog.io/images/gillog/post/73db6b88-99d8-42a9-adb9-be2f4079ece5/img_2.png)


작성을 완료한 후 `Create pull request`로 `Pull Request`를 생성한다.



<br>

### Pull Request Review

`Review` 담당자는 해당 `Pull Request`의 `Commit`들과 `File chnages`를 살펴보며,

이상이 없을 경우 `Confirm merge`로 `Pull Request`를 병합한다.

![img_4.png](https://images.velog.io/images/gillog/post/0291680e-acbd-4936-bc78-9c0e959f798b/img_4.png)

<br>

수정 필요사항이나, 이상이 발견될 경우 `Review` 담당자는 `Close pull request`로

해당 `PullRequest`를 닫은 후 수정을 요청한다.

![img_5.png](https://images.velog.io/images/gillog/post/81df0aa1-8222-4394-a74f-a59ad2c843ac/img_5.png)


<br>

수정 완료 이후 `Pull Request` 요청자는 

다시 `PullRequest` 생성 단계를 진행한다.


---

`Pull Request`가 적용된 후에는 `Source Code` 최신화 단계를 진행한다.