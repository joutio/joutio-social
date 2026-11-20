-- Blocages : nommage aligné sur les abonnements (`UserFollower` → `UserBlock`).
ALTER TABLE public.player_block RENAME TO user_block;
ALTER TABLE public.user_block RENAME CONSTRAINT player_block_pkey TO user_block_pkey;
ALTER TABLE public.user_block RENAME CONSTRAINT uq_player_block_blocker_blocked TO uq_user_block_blocker_blocked;
ALTER INDEX public.idx_player_block_blocker RENAME TO idx_user_block_blocker;
ALTER INDEX public.idx_player_block_blocked RENAME TO idx_user_block_blocked;
