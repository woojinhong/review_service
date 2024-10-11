# review_service
---
## 1 프로젝트 개요

### 1.1 소개
해당 프로젝트는 기업 과제 풀이 연습용으로 서버로만 구현된 어플리케이션 입니다. 상품에 대한 리뷰를 작성할 수 있습니다. 유저는 각 상품에 하나의 리뷰만 작성할 수 있으며, 리뷰에는 1~5점 사이의 점수와 선택적으로 이미지를 첨부할 수 있습니다. 또한, 리뷰는 최신순으로 조회 하는 구조를 띄고 있습니다.

### 1.2 비지니스 요구사항
- 리뷰는 존재하는 상품에만 작성할 수 있습니다.
- 유저는 하나의 상품에 대해 하나의 리뷰만 작성 가능합니다.
- 유저는 1~5점 사이의 점수와 리뷰를 남길 수 있습니다.
- 사진은 선택적으로 업로드 가능합니다.
    - 사진은 S3 에 저장된다고 가정하고, S3 적재 부분은 dummy 구현체를 생성합니다. 
    (실제 S3 연동을 할 필요는 없습니다.)
- 리뷰는 '가장 최근에 작성된 리뷰' 순서대로 조회합니다.

### 1.3 기술적 요구 사항
- Mysql 조회 시 인덱스를 잘 탈 수 있게 설계해야 합니다.
- 상품 테이블에 reviewCount 와 score 가 잘 반영되어야 한다.
- (Optional) 동시성을 고려한 설계를 해주세요. 많은 유저들이 동시에 리뷰를 작성할 때, 발생할 수 있는 문제를 고려해보세요.
- (Optional) 테스트 코드를 작성하면 좋습니다.


## 2 시스템 아키텍처
![architechture](https://github.com/user-attachments/assets/662bacc7-e765-4772-a15c-271d7b051f22)


## 3 챌린지

### 3.1 인덱스 최적화
- 프로젝트 기술적 요구사항중 MySql 조회 시 인덱스 설계부분에 조회 성능을 최적화 하기 위한 고민
- 대용량 데이터 기준 효율적인 검색을 위한 **풀 스캔** -> **인덱스 기반 조회**가 필요하다 판단 **(복합 인덱스 도입)**
- 조회 시 요구되는 필드 인덱스 설정 -> 리뷰 테이블의 **(product_id, created_at, id)**를 인덱스로 설정

- 인덱스 설정 순서가 조회 성능에 영향
  - 해당 프로젝트 요구사항 예시:
    - product_id가 정확한 검색 조건이라면, 인덱스에서 앞쪽에 위치
    - id 필드는 범위 검색, 뒤쪽에 위치

- **고민 사항**
  - 만약 다른 쿼리 조회 조건이 추가된다면?
- **답변**
  -  하나의 복합 인덱스가 모든 쿼리에 최적화될 수는 없다 -> 다양한 쿼리 패턴에 맞게 추가적인 인덱스를 설정   

### 3.2 동시성 제어
- 
