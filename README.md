# ArchitectureStudy
### 키워드
- Cache Invalidation [[1]](https://medium.com/coupang-engineering/%EB%8C%80%EC%9A%A9%EB%9F%89-%ED%8A%B8%EB%9E%98%ED%94%BD-%EC%B2%98%EB%A6%AC%EB%A5%BC-%EC%9C%84%ED%95%9C-%EC%BF%A0%ED%8C%A1%EC%9D%98-%EB%B0%B1%EC%97%94%EB%93%9C-%EC%A0%84%EB%9E%B5-184f7fdb1367)
  - 데이터 write: 백엔드 → 큐 → NoSQL DB공용 스토리지 →(CI)→ 캐시
    - Cache Invalidation 적용: 공용스토리지 데이터 업데이트시마다 캐시레이어의 오래된 데이터를 최신 데이터로 교체 ∴ 데이터와 캐시 레이어의 데이터가 분단위로 99.99% 동일하게 유지됨
  - 데이터 read: 캐시 ← 사용자
- 로컬 캐시 [[2]](https://medium.com/coupang-engineering/%EC%BA%90%EC%8B%9C%EB%A5%BC-%ED%99%9C%EC%9A%A9%ED%95%9C-%EB%8C%80%EC%9A%A9%EB%9F%89-%ED%8A%B8%EB%9E%98%ED%94%BD-%EC%B2%98%EB%A6%AC-%EC%84%B1%EB%8A%A5-%ED%96%A5%EC%83%81-a274f4731d07)
  - Redis, Memcached 사용
  - 사용자 로컬의 캐시가 아니라 서버 로컬의 캐시라는 뜻 -_-;
