## [2.0.1](https://github.com/joutio/joutio-social/compare/v2.0.0...v2.0.1) (2026-11-25)

### Bug Fixes

* **deps:** joutio-parent 3.1.0 with the gRPC bus foundation ([cdb5dbf](https://github.com/joutio/joutio-social/commit/cdb5dbf2b6b977320f90d6df062ac593aa9efb3b))

## [2.0.0](https://github.com/joutio/joutio-social/compare/v1.2.0...v2.0.0) (2026-11-20)

### ⚠ BREAKING CHANGES

* **block:** PlayerBlockedEvent/PlayerUnblockedEvent, BlockPlayerCommand,
UnblockPlayerCommand and GetBlockedPlayersQuery are renamed UserBlocked/
UserUnblockedEvent, BlockUserCommand, UnblockUserCommand, GetBlockedUsersQuery.

### Code Refactoring

* **block:** name blocks after users, like follows ([f46f64c](https://github.com/joutio/joutio-social/commit/f46f64c96255b1c70a53341bffd43d8ed56a363d))

## [1.2.0](https://github.com/joutio/joutio-social/compare/v1.1.12...v1.2.0) (2026-11-20)

### Features

* **block:** block and unblock a player ([c578da4](https://github.com/joutio/joutio-social/commit/c578da45b678d0c39c5565645822b7cad59ee3b7))

## [1.1.12](https://github.com/joutio/joutio-social/compare/v1.1.11...v1.1.12) (2026-11-20)

### Bug Fixes

* **deps:** joutio-parent 2.4.1 with patched dependencies ([6b39cbb](https://github.com/joutio/joutio-social/commit/6b39cbb0ad1fac42c3dc917a361dbbb8ebc94971))

## [1.1.11](https://github.com/joutio/joutio-social/compare/v1.1.10...v1.1.11) (2026-11-20)

### Performance Improvements

* **docker:** JDK 25 AOT cache at startup ([39517cd](https://github.com/joutio/joutio-social/commit/39517cda28da5d3983471b3a4aca6f97ba3ce5ea))

## [1.1.10](https://github.com/joutio/joutio-social/compare/v1.1.9...v1.1.10) (2026-11-20)

### Bug Fixes

* **docker:** rebuild on patched base images ([11eba22](https://github.com/joutio/joutio-social/commit/11eba22f0cce3cb4624c1aed598965ad944b82eb))

## [1.1.9](https://github.com/joutio/joutio-social/compare/v1.1.8...v1.1.9) (2026-11-20)

### Bug Fixes

* **deps:** joutio-parent 2.4.0, instance-scoped groups on one Kafka consumer per pod ([d20cf1d](https://github.com/joutio/joutio-social/commit/d20cf1da8835f0b73d2e86528a9f21e89cd044dd))

## [1.1.8](https://github.com/joutio/joutio-social/compare/v1.1.7...v1.1.8) (2026-11-20)

### Bug Fixes

* **deps:** joutio-parent 2.3.0, readiness waits for instance-scoped processors ([214e259](https://github.com/joutio/joutio-social/commit/214e259004b8d8cbe413f8c81d41f7d1914d72cc))

## [1.1.7](https://github.com/joutio/joutio-social/compare/v1.1.6...v1.1.7) (2026-11-20)

### Bug Fixes

* **deps:** joutio-parent 2.2.1, distinct Kafka consumer metrics ([026233d](https://github.com/joutio/joutio-social/commit/026233d719a261273aa7ab998273fddd4066e878))

## [1.1.6](https://github.com/joutio/joutio-social/compare/v1.1.5...v1.1.6) (2026-11-20)

### Bug Fixes

* **deps:** joutio-parent 2.2.0, kafka consumer, segment and rebalancer metrics ([974ed4b](https://github.com/joutio/joutio-social/commit/974ed4bd0f33746678ddf14d96747e3615769220))

## [1.1.5](https://github.com/joutio/joutio-social/compare/v1.1.4...v1.1.5) (2026-11-19)

### Bug Fixes

* **deps:** joutio-parent 2.1.0, consistent command routing across replicas ([ef088e9](https://github.com/joutio/joutio-social/commit/ef088e90408621647b8d6231ad8058960b54334b))

## [1.1.4](https://github.com/joutio/joutio-social/compare/v1.1.3...v1.1.4) (2026-11-18)

### Bug Fixes

* **deps:** joutio-parent 2.0.0, idle-friendly processors and bytea Axon schema ([117fc05](https://github.com/joutio/joutio-social/commit/117fc0578daeeb9c9fd8f86bcc25a1e41eacd7f9))

## [1.1.3](https://github.com/joutio/joutio-social/compare/v1.1.2...v1.1.3) (2026-11-17)

### Bug Fixes

* **deps:** pin joutio-identity-domain 2.4.2, 1.3.0 is no longer published ([3fb4cbd](https://github.com/joutio/joutio-social/commit/3fb4cbdd1e0bddf8e6b56fddc0603814413ba6d1))

## [1.1.2](https://github.com/joutio/joutio-social/compare/v1.1.1...v1.1.2) (2026-11-17)

### Bug Fixes

* **deps:** joutio-parent 1.2.0, segmented processing groups and single-partition Axon topic ([5811ebf](https://github.com/joutio/joutio-social/commit/5811ebfbc035fa0c3588bb299e6a336fed2083c1))

## [1.1.1](https://github.com/joutio/joutio-social/compare/v1.1.0...v1.1.1) (2026-11-17)

### Bug Fixes

* **deps:** joutio-parent 1.1.0, unknown enum constants tolerated on the Axon transport ([84ff302](https://github.com/joutio/joutio-social/commit/84ff3025910d3a97e88f0d37c0abe03fac44dd1c))

## [1.1.0](https://github.com/joutio/joutio-social/compare/v1.0.0...v1.1.0) (2026-11-13)

### Features

* **social:** referred players follow their sponsor ([7af1d5e](https://github.com/joutio/joutio-social/commit/7af1d5edea68a9ed2a35a6809db5fe56e7abb16f))

## 1.0.0 (2026-10-27)

### Features

* release Joutio social 1.0.0 ([ba42c33](https://github.com/joutio/joutio-social/commit/ba42c3325dc8fd30ad192c858e662e35c52c82eb))
* release Joutio social 1.0.0 ([987bb43](https://github.com/joutio/joutio-social/commit/987bb43b6d7aeacc54d9d36e65892a8d137078bb))

## [5.4.0](https://github.com/joutio/joutio-social/compare/v5.3.1...v5.4.0) (2026-10-22)

### Features

* **account:** efface les abonnements du compte supprimé ([f2463ba](https://github.com/joutio/joutio-social/commit/f2463ba1428df31d6492b4d6fda17fe7ede202b9))

## [5.3.1](https://github.com/joutio/joutio-social/compare/v5.3.0...v5.3.1) (2026-10-12)

### Bug Fixes

* **social:** SDK joutio-parent 4.7.1 (cycle de beans Configuration/bus distribué au démarrage) ([bf02bad](https://github.com/joutio/joutio-social/commit/bf02bad8370c0331a93add87d05d39f04516dae8))

## [5.3.0](https://github.com/joutio/joutio-social/compare/v5.2.0...v5.3.0) (2026-10-12)

### Features

* **console:** consume joutio-sdk 4.7.0 (lag, commandes en vol, snapshots, scheduled) ([d37f88d](https://github.com/joutio/joutio-social/commit/d37f88d1a97e5a8f68153432d6e80d2a0e586196))

## [5.2.0](https://github.com/joutio/joutio-social/compare/v5.1.0...v5.2.0) (2026-10-12)

### Features

* **console:** consume joutio-sdk 4.6.0 (build-info + API interne event store/scheduled) ([920a8ff](https://github.com/joutio/joutio-social/commit/920a8ff8e6fd3570e143ed5965eeeb2e65d24bd5))

## [5.1.0](https://github.com/joutio/joutio-social/compare/v5.0.0...v5.1.0) (2026-10-12)

### Features

* **console:** consume joutio-sdk 4.5.2 (DLQ Axon par défaut + agent console) ([c0d2d61](https://github.com/joutio/joutio-social/commit/c0d2d61f0e92a06a0cb7a275d97668dbe4626ea5))

## [5.0.0](https://github.com/joutio/joutio-social/compare/v4.0.0...v5.0.0) (2026-10-12)

### ⚠ BREAKING CHANGES

* **social:** ProfileRepositoryPort retiré du domaine social.

### Code Refactoring

* **social:** supprime la vérification d'existence du profil (agrégat sans I/O) ([d366931](https://github.com/joutio/joutio-social/commit/d366931957046a12b59ab5438def62b8a1c99862))

## [4.0.0](https://github.com/joutio/joutio-social/compare/v3.0.1...v4.0.0) (2026-10-02)

### ⚠ BREAKING CHANGES

* **social:** remove challenges bounded context

### Features

* **social:** remove challenges bounded context ([3abab9f](https://github.com/joutio/joutio-social/commit/3abab9fc9fc814d16a8eaca9eb19fa58c70542b6))

## [3.0.1](https://github.com/joutio/joutio-social/compare/v3.0.0...v3.0.1) (2026-09-30)

### Bug Fixes

* **social:** guard challenge languages at creation/acceptance and fail challenged games ([dbca2b1](https://github.com/joutio/joutio-social/commit/dbca2b11090e3557c68ff542ab3254fd882f101a))

## [3.0.0](https://github.com/joutio/joutio-social/compare/v2.1.0...v3.0.0) (2026-09-30)

### ⚠ BREAKING CHANGES

* **social:** ChallengeProfile carries the player language; accept is guarded by topic language availability (TopicAvailabilityPort); ChallengeSaga forwards required languages to CreateGameCommand (pins SDK 4.1.0, profile 3.0.0, game 4.0.0, theme 4.0.0).

### Features

* **social:** language-aware challenge acceptance and game creation ([e57cd7f](https://github.com/joutio/joutio-social/commit/e57cd7f809a69d29fbbfab9d98c0b0cf060e2087))

## [2.1.0](https://github.com/joutio/joutio-social/compare/v2.0.0...v2.1.0) (2026-09-27)

### Features

* **social:** challenge completion, dedicated box/follow queries and actor-checked unfollows ([bdfc635](https://github.com/joutio/joutio-social/commit/bdfc635f48604e67ccdf70e2e0715f75d364437f))

## [2.0.0](https://github.com/joutio/joutio-social/compare/v1.7.3...v2.0.0) (2026-09-25)

### ⚠ BREAKING CHANGES

* **social:** the service no longer exposes its REST API nor WebSocket
(the BFF is the sole surface); search use cases now use the SDK SearchRequest/
SearchResponse DTOs.

### Features

* **social:** headless service (REST + WS removed) + SDK search contracts ([5168e4c](https://github.com/joutio/joutio-social/commit/5168e4c6b83a3f60d5cf2d69cad4bba11d4b0f8e))

## [1.7.3](https://github.com/joutio/joutio-social/compare/v1.7.2...v1.7.3) (2026-09-24)

### Bug Fixes

* **deps:** bump joutio-parent to 2.4.4 (typed PageResult over query transport) ([b1ad3ca](https://github.com/joutio/joutio-social/commit/b1ad3caca91df5f09242187c5f3ad678155894cf))

## [1.7.2](https://github.com/joutio/joutio-social/compare/v1.7.1...v1.7.2) (2026-09-24)

### Bug Fixes

* **deps:** bump joutio-parent to 2.4.3 (bus-only search criteria type info) ([b400084](https://github.com/joutio/joutio-social/commit/b4000847da93f9de9b8a243843857066125651a9))

## [1.7.1](https://github.com/joutio/joutio-social/compare/v1.7.0...v1.7.1) (2026-09-24)

### Bug Fixes

* **deps:** bump joutio-parent to 2.4.2 (search criteria type info) ([734e523](https://github.com/joutio/joutio-social/commit/734e523a13c23f526368e72f4bf0b96aa859f18f))

## [1.7.0](https://github.com/joutio/joutio-social/compare/v1.6.4...v1.7.0) (2026-09-23)

### Features

* **social:** REST alignment (follows GET by-id, unfollow 204, cancel as action) and bump SDK to 2.4.1 ([b24c9e9](https://github.com/joutio/joutio-social/commit/b24c9e9fbc64f97bcca0276bb05169fb58eebef6))

## [1.6.4](https://github.com/joutio/joutio-social/compare/v1.6.3...v1.6.4) (2026-09-22)

### Bug Fixes

* **joutio-social:** upgrade joutio-parent to 2.3.4 ([b0e5d86](https://github.com/joutio/joutio-social/commit/b0e5d86e0ea674a236d0875f4d212c1931d6cc11))

## [1.6.3](https://github.com/joutio/joutio-social/compare/v1.6.2...v1.6.3) (2026-09-22)

### Bug Fixes

* **joutio-social:** upgrade joutio-parent to 2.3.3 ([7702041](https://github.com/joutio/joutio-social/commit/7702041076d65218d141ab93e25f8381657cbe07))

## [1.6.2](https://github.com/joutio/joutio-social/compare/v1.6.1...v1.6.2) (2026-09-22)

### Bug Fixes

* **joutio-social:** upgrade joutio-parent to 2.3.2 ([5ee95e7](https://github.com/joutio/joutio-social/commit/5ee95e77278a18c4068acfc5aea197641388bae4))

## [1.6.1](https://github.com/joutio/joutio-social/compare/v1.6.0...v1.6.1) (2026-09-22)

### Bug Fixes

* **social:** upgrade joutio-parent to 2.3.1 (registration address fix) ([8749772](https://github.com/joutio/joutio-social/commit/87497728827a1050ce402f1290e634c4742dd41d))

## [1.6.0](https://github.com/joutio/joutio-social/compare/v1.5.0...v1.6.0) (2026-09-22)

### Features

* **social:** explicit Axon processing groups ([e631e94](https://github.com/joutio/joutio-social/commit/e631e9414b96d35cac0f0ca79b0d5ea8d84141ac))

## [1.5.0](https://github.com/joutio/joutio-social/compare/v1.4.5...v1.5.0) (2026-09-21)

### Features

* **follow:** deterministic ids and idempotent non-destructive aggregates ([3825660](https://github.com/joutio/joutio-social/commit/38256600e63565b09ced764c572326420aeeb746))

### Bug Fixes

* **observability:** keep readable console logs in local ([6c17f06](https://github.com/joutio/joutio-social/commit/6c17f06c429875d20ee84ee0060111d8f9ce9d3d))

## [1.4.5](https://github.com/joutio/joutio-social/compare/v1.4.4...v1.4.5) (2026-09-20)

### Bug Fixes

* **deps:** bump joutio-sdk to 2.1.1 ([d978a1e](https://github.com/joutio/joutio-social/commit/d978a1e94dd79a7d07d0dee7bfa0cf4ec8c5ddfb))

## [1.4.4](https://github.com/joutio/joutio-social/compare/v1.4.3...v1.4.4) (2026-09-20)

### Bug Fixes

* **config:** align prod service URLs with joutio-* names ([67ff650](https://github.com/joutio/joutio-social/commit/67ff650f3c775570f8046f191719f78344053bea))

## [1.4.3](https://github.com/joutio/joutio-social/compare/v1.4.2...v1.4.3) (2026-09-20)

### Bug Fixes

* **deps:** bump joutio-sdk to 2.1.0 ([d03b206](https://github.com/joutio/joutio-social/commit/d03b2068cc5b683d937b954d4471675bc6cb895a))

## [1.4.2](https://github.com/joutio/joutio-social/compare/v1.4.1...v1.4.2) (2026-09-20)

### Bug Fixes

* **observability:** remove WebSocket and business KPI metrics (consume joutio-sdk 1.4.3) ([736b987](https://github.com/joutio/joutio-social/commit/736b98797a498547e602599055d813ad04cece2a))

## [1.4.1](https://github.com/joutio/joutio-social/compare/v1.4.0...v1.4.1) (2026-09-20)

### Bug Fixes

* **observability:** consume joutio-sdk 1.4.2 (Axon activity metrics fix + Swagger server URL) ([d8d3e9d](https://github.com/joutio/joutio-social/commit/d8d3e9dfb1b7747aec295c6639e5d1e3010716a8))

## [1.4.0](https://github.com/joutio/joutio-social/compare/v1.3.0...v1.4.0) (2026-09-20)

### Features

* **observability:** consume joutio-sdk 1.4.0 (Axon activity metrics) ([f57a712](https://github.com/joutio/joutio-social/commit/f57a712e87f998c401400f7d23100629f382fb95))

## [1.3.0](https://github.com/joutio/joutio-social/compare/v1.2.0...v1.3.0) (2026-09-20)

### Features

* **observability:** social KPIs (follows, challenges) + consume joutio-sdk 1.3.0 ([7d0260a](https://github.com/joutio/joutio-social/commit/7d0260aa8c332e019ad20219a73408d3e3577011))

## [1.2.0](https://github.com/joutio/joutio-social/compare/v1.1.0...v1.2.0) (2026-09-20)

### Features

* **observability:** consume joutio-sdk 1.2.0 (structured logs + tracing) ([167a8e6](https://github.com/joutio/joutio-social/commit/167a8e62fd22aee6b317619e40c13e25fc499b6e))

## [1.1.0](https://github.com/joutio/joutio-social/compare/v1.0.0...v1.1.0) (2026-09-20)

### Features

* **observability:** consume joutio-sdk 1.1.0 (Prometheus metrics) ([51f141a](https://github.com/joutio/joutio-social/commit/51f141aff654ec50109b5040c9f6fa3c95072602))

## 1.0.0 (2026-09-19)

### Features

* first commit ([82fa176](https://github.com/joutio/joutio-social/commit/82fa176ec3518a494285a5f91a631d335f3032a4))

### Bug Fixes

* **maven:** pin inter-service domains to 0.0.1; align parent to joutio-parent 1.0.0 ([12c08a9](https://github.com/joutio/joutio-social/commit/12c08a9d1aed95c7305050cc9292fb8608f7f385))
