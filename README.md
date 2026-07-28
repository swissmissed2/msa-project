# msa-project

Spring Cloud 기반 MSA의 핵심 구성요소(서비스 디스커버리, 중앙 설정 관리, API 게이트웨이)를 직접 구축해보며 각 컴포넌트가 실제로 어떻게 맞물려 동작하는지 확인해본 개인 학습 프로젝트입니다.

## 구성

| 모듈 | 역할 | 포트 |
|---|---|---|
| `server` | Eureka Server — 서비스 등록/탐색 | 19090 |
| `config` | Spring Cloud Config Server — 프로필별(dev/prod) 설정 중앙 관리 | 19092 |
| `gateway` | API Gateway (WebFlux 기반) — 경로 기반 라우팅 + 서비스 디스커버리 연동 | 19091 |
| `demo` | Eureka/Config에 등록되는 예시 서비스 | - |

## 확인해본 것

- **서비스 디스커버리**: `demo` 서비스가 Eureka Server에 등록되고, Gateway가 `lb://demo-service`로 로드밸런싱 라우팅
- **동적 라우팅**: Gateway의 `discovery.locator.enabled`로 등록된 서비스를 자동 라우팅 경로로 노출, `Path=/demo/**` predicate로 명시적 라우팅도 함께 구성
- **중앙 설정 관리**: Config Server가 `config-repo/`에서 프로필별(dev/prod) yml을 서비스별로 분리 제공
- **설정 동적 반영**: `demo` 서비스 컨트롤러에 `@RefreshScope`를 적용해, 코드 재배포 없이 설정값(`message`)이 갱신되는지 확인

## 실행 순서

```
1. server   (Eureka)   → 19090
2. config   (Config)   → 19092
3. gateway  (Gateway)  → 19091
4. demo     (Client)   → Eureka에 자동 등록
```

## 배경

B2B 물류 관리 플랫폼 프로젝트에서 Eureka/Config Server/Gateway 기반 분산 시스템에 참여하기 전, 각 컴포넌트를 최소 구성으로 직접 만들어보며 동작 원리를 확인하기 위해 진행했습니다.
