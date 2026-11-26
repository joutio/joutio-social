-- Version des compteurs d'abonnements surveillés par les caches du BFF (WatchUserFollowCountsQuery) : un compteur par
-- joueur, incrémenté à chaque abonnement ou désabonnement qui le concerne.
CREATE TABLE public.live_version (
    user_id character varying(255) PRIMARY KEY,
    version bigint NOT NULL
);

COMMENT ON TABLE public.live_version IS 'Version des compteurs d''abonnements surveillés par les caches du BFF';
