-- Blocages entre joueurs (une ligne par blocage actif).
CREATE TABLE public.player_block (
    block_id character varying(255) NOT NULL,
    blocker_id character varying(255) NOT NULL,
    blocked_id character varying(255) NOT NULL,
    blocked_at timestamp without time zone NOT NULL,
    CONSTRAINT player_block_pkey PRIMARY KEY (block_id),
    CONSTRAINT uq_player_block_blocker_blocked UNIQUE (blocker_id, blocked_id)
);
CREATE INDEX idx_player_block_blocker ON public.player_block (blocker_id);
CREATE INDEX idx_player_block_blocked ON public.player_block (blocked_id);
