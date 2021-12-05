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
  - Branch 구분 기준
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

개발 단계의 `Commit` 들을 `git commit -m "[#이슈번호] 커밋메시지"` 형태로 취합한 후,

개발 완료시에 `GitHub` 에서 `