# AGENTS.md — joutio-social

Abonnements **unidirectionnels** aux sujets (`TopicFollower`) et aux joueurs (`UserFollower`), et **blocages**
entre joueurs (`UserBlock`). Pas d'amitié, de demande ni de favori. Package `com.joutio.social`, base `joutio_social`, port local
8087. Service headless.

Lire : `../../AGENTS.md`, [`docs/backend/`](../../docs/backend/hexagonal.md), exposition dans
[`docs/api/joueurs.md`](../../docs/api/joueurs.md#social).

## Règles

- Suivre ou ne plus suivre, idempotent. Le désabonnement vérifie que l'acteur possède le suivi
  (`UnfollowUserCommand(followId, actorId)`, `NotFollowOwnerProblem`, catégorie `PERMISSION`).
- L'existence de la cible n'est pas vérifiée : un sujet ou un joueur inconnu n'a pas de projection
  et reste invisible. Aucune I/O dans les handlers.
- **Blocage** (`UserBlockAggregate`, id `blockerId:blockedId`, non destructif) : `BlockUserCommand`,
  `UnblockUserCommand(blockId, actorId)` réservé à l'auteur (`NotBlockOwnerProblem`). L'effet est symétrique :
  `UserBlockFollowHandler` retire les abonnements dans les deux sens ; le BFF refuse défi, abonnement et salon
  partagé entre les deux, et exclut la paire de l'appariement.
- Aucune dépendance sortante.

## Lectures fournies (`joutio-social-domain`)

- `UserFollowerQuery` :
  - `GetUserFollowCountsQuery(userId)` ;
  - `GetUserFollowsQuery(userId, direction, limit)`, consommée aussi par leaderboard pour la
    portée « abonnements » ;
  - `ExistsUserFollowerQuery(followerId, followedId)`.
- `TopicFollowerQuery` : `GetTopicFollowsQuery(userId, limit)`,
  `ExistsTopicFollowerQuery(topicId, userId)`.
- `UserBlockQuery` : `GetBlockedUsersQuery(userId)` (joueurs bloqués par lui),
  `GetBlockRelationsQuery(userId)` (joueurs en relation de blocage, dans un sens ou dans l'autre).
- `Search*Query` : surfaces d'administration seulement.

Événements consommés ailleurs :
- `UserFollowedEvent` → notification (`FOLLOW`), profile (badge Social), BFF (purge du cache des
  compteurs) ;
- `UserUnfollowedEvent` → BFF ;
- `UserBlockedEvent` → matchmaking (annule les défis en attente entre les deux), BFF.

## Commandes

```bash
mvn -q -pl joutio-social-domain test
mvn -pl joutio-social-infrastructure test
```
