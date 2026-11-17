# AGENTS.md — joutio-social

Abonnements **unidirectionnels** aux sujets (`TopicFollower`) et aux joueurs (`UserFollower`). Pas
d'amitié, de demande ni de favori. Package `com.joutio.social`, base `joutio_social`, port local
8087. Service headless.

Lire : `../../AGENTS.md`, [`docs/backend/`](../../docs/backend/hexagonal.md), exposition dans
[`docs/api/joueurs.md`](../../docs/api/joueurs.md#social).

## Règles

- Suivre ou ne plus suivre, idempotent. Le désabonnement vérifie que l'acteur possède le suivi
  (`UnfollowUserCommand(followId, actorId)`, `NotFollowOwnerProblem`, catégorie `PERMISSION`).
- L'existence de la cible n'est pas vérifiée : un sujet ou un joueur inconnu n'a pas de projection
  et reste invisible. Aucune I/O dans les handlers.
- Aucune dépendance sortante.

## Lectures fournies (`joutio-social-domain`)

- `UserFollowerQuery` :
  - `GetUserFollowCountsQuery(userId)` ;
  - `GetUserFollowsQuery(userId, direction, limit)`, consommée aussi par leaderboard pour la
    portée « abonnements » ;
  - `ExistsUserFollowerQuery(followerId, followedId)`.
- `TopicFollowerQuery` : `GetTopicFollowsQuery(userId, limit)`,
  `ExistsTopicFollowerQuery(topicId, userId)`.
- `Search*Query` : surfaces d'administration seulement.

Événements consommés ailleurs :
- `UserFollowedEvent` → notification (`FOLLOW`), profile (badge Social), BFF (purge du cache des
  compteurs) ;
- `UserUnfollowedEvent` → BFF.

## Commandes

```bash
mvn -q -pl joutio-social-domain test
mvn -pl joutio-social-infrastructure test
```
