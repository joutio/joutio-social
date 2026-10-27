-- Joutio social — baseline schema (squashed)
-- Generated from a fresh PostgreSQL 16 database after applying the legacy migrations.

--
-- PostgreSQL database dump
--


-- Dumped from database version 16.13
-- Dumped by pg_dump version 16.13

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: topic_follower; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.topic_follower (
    follow_id character varying(255) NOT NULL,
    topic_id character varying(255) NOT NULL,
    user_id character varying(255) NOT NULL,
    followed_at timestamp without time zone NOT NULL
);


--
-- Name: user_follower; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.user_follower (
    follow_id character varying(255) NOT NULL,
    follower_id character varying(255) NOT NULL,
    followed_id character varying(255) NOT NULL,
    followed_at timestamp without time zone NOT NULL
);


--
-- Name: topic_follower topic_follower_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.topic_follower
    ADD CONSTRAINT topic_follower_pkey PRIMARY KEY (follow_id);


--
-- Name: topic_follower uq_topic_follower_topic_user; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.topic_follower
    ADD CONSTRAINT uq_topic_follower_topic_user UNIQUE (topic_id, user_id);


--
-- Name: user_follower uq_user_follower_follower_followed; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.user_follower
    ADD CONSTRAINT uq_user_follower_follower_followed UNIQUE (follower_id, followed_id);


--
-- Name: user_follower user_follower_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.user_follower
    ADD CONSTRAINT user_follower_pkey PRIMARY KEY (follow_id);


--
-- Name: idx_topic_follower_followed_at; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX idx_topic_follower_followed_at ON public.topic_follower USING btree (followed_at);


--
-- Name: idx_topic_follower_topic; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX idx_topic_follower_topic ON public.topic_follower USING btree (topic_id);


--
-- Name: idx_topic_follower_user; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX idx_topic_follower_user ON public.topic_follower USING btree (user_id);


--
-- Name: idx_user_follower_followed; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX idx_user_follower_followed ON public.user_follower USING btree (followed_id);


--
-- Name: idx_user_follower_followed_at; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX idx_user_follower_followed_at ON public.user_follower USING btree (followed_at);


--
-- Name: idx_user_follower_follower; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX idx_user_follower_follower ON public.user_follower USING btree (follower_id);


--
-- PostgreSQL database dump complete
--


