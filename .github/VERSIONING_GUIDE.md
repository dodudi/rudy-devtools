# Docker 이미지 버전 관리 가이드

이 프로젝트는 Git Tag를 사용하여 Docker 이미지 버전을 관리합니다.

## 버전 관리 방식

### Semantic Versioning 사용
버전 형식: `v{major}.{minor}.{patch}`

- **major**: 호환되지 않는 API 변경
- **minor**: 하위 호환되는 기능 추가
- **patch**: 하위 호환되는 버그 수정

예: `v1.0.0`, `v1.2.3`, `v2.0.0`

## 릴리즈 방법

### 1. 새 버전 릴리즈

```bash
# 1. 모든 변경사항 커밋
git add .
git commit -m "Release v1.0.0"

# 2. 버전 태그 생성
git tag v1.0.0

# 3. 태그를 GitHub에 푸시
git push origin v1.0.0
```

### 2. 자동 빌드 시작
- Git tag push → GitHub Actions 자동 트리거
- 약 7-8분 후 Docker Hub에 이미지 업로드 완료

### 3. 생성되는 이미지 태그

#### 예: `v1.2.3` 태그 push 시
```
didadu/rudy-devtools:1.2.3
didadu/rudy-devtools:1.2
didadu/rudy-devtools:1
didadu/rudy-devtools:latest
```

## 사용 예시

### Patch 버전 업데이트 (버그 수정)
```bash
# 현재: v1.0.0
git tag v1.0.1
git push origin v1.0.1

# 생성되는 이미지:
# - didadu/rudy-devtools:1.0.1
# - didadu/rudy-devtools:1.0 (업데이트)
# - didadu/rudy-devtools:1 (업데이트)
# - didadu/rudy-devtools:latest (업데이트)
```

### Minor 버전 업데이트 (새 기능)
```bash
# 현재: v1.0.1
git tag v1.1.0
git push origin v1.1.0

# 생성되는 이미지:
# - didadu/rudy-devtools:1.1.0
# - didadu/rudy-devtools:1.1 (신규)
# - didadu/rudy-devtools:1 (업데이트)
# - didadu/rudy-devtools:latest (업데이트)
```

### Major 버전 업데이트 (Breaking Changes)
```bash
# 현재: v1.1.0
git tag v2.0.0
git push origin v2.0.0

# 생성되는 이미지:
# - didadu/rudy-devtools:2.0.0
# - didadu/rudy-devtools:2.0 (신규)
# - didadu/rudy-devtools:2 (신규)
# - didadu/rudy-devtools:latest (업데이트)
```

## Docker Compose에서 사용

### 특정 버전 고정
```yaml
services:
  rudy-devtools:
    image: didadu/rudy-devtools:1.2.3  # 정확한 버전
```

### Minor 버전 범위
```yaml
services:
  rudy-devtools:
    image: didadu/rudy-devtools:1.2  # 1.2.x 최신
```

### Major 버전 범위
```yaml
services:
  rudy-devtools:
    image: didadu/rudy-devtools:1  # 1.x.x 최신
```

### 최신 버전
```yaml
services:
  rudy-devtools:
    image: didadu/rudy-devtools:latest  # 항상 최신
```

## main 브랜치 push (태그 없이)

main 브랜치에 직접 push하면:
```yaml
# 생성되는 이미지:
# - didadu/rudy-devtools:main
# - didadu/rudy-devtools:latest
```

**권장**: 개발 중에는 main에 직접 push, 릴리즈 시에만 태그 사용

## 태그 관리

### 기존 태그 확인
```bash
git tag
```

### 태그 삭제 (로컬)
```bash
git tag -d v1.0.0
```

### 태그 삭제 (원격)
```bash
git push --delete origin v1.0.0
```

### 태그 덮어쓰기 (권장 안함)
```bash
git tag -f v1.0.0
git push -f origin v1.0.0
```

## 버전 전략 권장사항

### 개발 단계
- main 브랜치에 직접 커밋
- 태그 없이 push
- `latest` 태그로 테스트

### 릴리즈 준비
- 기능 완성 확인
- CHANGELOG 업데이트
- 버전 태그 생성 및 push

### 프로덕션 배포
- 특정 버전 태그 사용 (예: `1.2.3`)
- `latest` 사용 지양 (예상치 못한 업데이트 방지)

## 문제 해결

### 태그를 잘못 push했을 때
```bash
# 1. GitHub Actions에서 빌드 취소
# Repository → Actions → 실행 중인 워크플로우 → Cancel

# 2. 태그 삭제
git push --delete origin v1.0.0
git tag -d v1.0.0

# 3. 올바른 태그 다시 생성
git tag v1.0.1
git push origin v1.0.1
```

### Docker Hub에서 이미지 삭제
1. [Docker Hub](https://hub.docker.com/) 로그인
2. Repositories → didadu/rudy-devtools
3. Tags → 삭제할 태그 선택 → Delete

## 참고

- [Semantic Versioning](https://semver.org/)
- [Docker Metadata Action](https://github.com/docker/metadata-action)
