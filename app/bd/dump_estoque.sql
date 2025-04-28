--
-- PostgreSQL database dump
--

-- Dumped from database version 17.4 (Debian 17.4-1.pgdg120+2)
-- Dumped by pg_dump version 17.4 (Debian 17.4-1.pgdg120+2)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: funcionario; Type: TABLE; Schema: public; Owner: admin
--

CREATE TABLE public.funcionario (
    id_funcionario integer NOT NULL,
    nome character varying(50) NOT NULL,
    id_gerente integer NOT NULL,
    salario numeric(8,2) NOT NULL
);


ALTER TABLE public.funcionario OWNER TO admin;

--
-- Name: funcionario_id_funcionario_seq; Type: SEQUENCE; Schema: public; Owner: admin
--

CREATE SEQUENCE public.funcionario_id_funcionario_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.funcionario_id_funcionario_seq OWNER TO admin;

--
-- Name: funcionario_id_funcionario_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: admin
--

ALTER SEQUENCE public.funcionario_id_funcionario_seq OWNED BY public.funcionario.id_funcionario;


--
-- Name: gerente; Type: TABLE; Schema: public; Owner: admin
--

CREATE TABLE public.gerente (
    id_gerente integer NOT NULL,
    id_usuario integer NOT NULL,
    id_setor integer NOT NULL,
    salario numeric(8,2) NOT NULL
);


ALTER TABLE public.gerente OWNER TO admin;

--
-- Name: gerente_id_gerente_seq; Type: SEQUENCE; Schema: public; Owner: admin
--

CREATE SEQUENCE public.gerente_id_gerente_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.gerente_id_gerente_seq OWNER TO admin;

--
-- Name: gerente_id_gerente_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: admin
--

ALTER SEQUENCE public.gerente_id_gerente_seq OWNED BY public.gerente.id_gerente;


--
-- Name: produto; Type: TABLE; Schema: public; Owner: admin
--

CREATE TABLE public.produto (
    id_produto integer NOT NULL,
    nome character varying(50),
    preco numeric(8,2),
    quantidade integer,
    id_setor integer NOT NULL
);


ALTER TABLE public.produto OWNER TO admin;

--
-- Name: produto_id_produto_seq; Type: SEQUENCE; Schema: public; Owner: admin
--

CREATE SEQUENCE public.produto_id_produto_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.produto_id_produto_seq OWNER TO admin;

--
-- Name: produto_id_produto_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: admin
--

ALTER SEQUENCE public.produto_id_produto_seq OWNED BY public.produto.id_produto;


--
-- Name: setor; Type: TABLE; Schema: public; Owner: admin
--

CREATE TABLE public.setor (
    id integer NOT NULL,
    nome character varying(30) NOT NULL
);


ALTER TABLE public.setor OWNER TO admin;

--
-- Name: setor_id_seq; Type: SEQUENCE; Schema: public; Owner: admin
--

CREATE SEQUENCE public.setor_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.setor_id_seq OWNER TO admin;

--
-- Name: setor_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: admin
--

ALTER SEQUENCE public.setor_id_seq OWNED BY public.setor.id;


--
-- Name: usuario; Type: TABLE; Schema: public; Owner: admin
--

CREATE TABLE public.usuario (
    id integer NOT NULL,
    nome character varying(50) NOT NULL,
    email character varying(50) NOT NULL,
    senha character varying(255) NOT NULL
);


ALTER TABLE public.usuario OWNER TO admin;

--
-- Name: usuario_id_seq; Type: SEQUENCE; Schema: public; Owner: admin
--

CREATE SEQUENCE public.usuario_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.usuario_id_seq OWNER TO admin;

--
-- Name: usuario_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: admin
--

ALTER SEQUENCE public.usuario_id_seq OWNED BY public.usuario.id;


--
-- Name: funcionario id_funcionario; Type: DEFAULT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.funcionario ALTER COLUMN id_funcionario SET DEFAULT nextval('public.funcionario_id_funcionario_seq'::regclass);


--
-- Name: gerente id_gerente; Type: DEFAULT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.gerente ALTER COLUMN id_gerente SET DEFAULT nextval('public.gerente_id_gerente_seq'::regclass);


--
-- Name: produto id_produto; Type: DEFAULT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.produto ALTER COLUMN id_produto SET DEFAULT nextval('public.produto_id_produto_seq'::regclass);


--
-- Name: setor id; Type: DEFAULT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.setor ALTER COLUMN id SET DEFAULT nextval('public.setor_id_seq'::regclass);


--
-- Name: usuario id; Type: DEFAULT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.usuario ALTER COLUMN id SET DEFAULT nextval('public.usuario_id_seq'::regclass);


--
-- Data for Name: funcionario; Type: TABLE DATA; Schema: public; Owner: admin
--

COPY public.funcionario (id_funcionario, nome, id_gerente, salario) FROM stdin;
1	Marcos Ferreira	1	4500.00
2	Paula Martins	1	4700.00
3	Lucas Rocha	2	4000.00
\.


--
-- Data for Name: gerente; Type: TABLE DATA; Schema: public; Owner: admin
--

COPY public.gerente (id_gerente, id_usuario, id_setor, salario) FROM stdin;
1	1	1	8500.00
2	2	3	7800.00
\.


--
-- Data for Name: produto; Type: TABLE DATA; Schema: public; Owner: admin
--

COPY public.produto (id_produto, nome, preco, quantidade, id_setor) FROM stdin;
1	Notebook Dell	4500.00	10	1
2	Mouse Logitech	150.00	50	1
3	Impressora HP	600.00	15	1
6	Cama Casal	2900.99	25	3
7	Cadeira de Jantar	250.00	10	3
\.


--
-- Data for Name: setor; Type: TABLE DATA; Schema: public; Owner: admin
--

COPY public.setor (id, nome) FROM stdin;
1	Tecnologia
2	Ferramentas
3	Mobilia
\.


--
-- Data for Name: usuario; Type: TABLE DATA; Schema: public; Owner: admin
--

COPY public.usuario (id, nome, email, senha) FROM stdin;
1	Carlos Silva	carlos.silva@email.com	senha123
2	Ana Souza	ana.souza@email.com	senha456
3	João Lima	joao.lima@email.com	senha789
\.


--
-- Name: funcionario_id_funcionario_seq; Type: SEQUENCE SET; Schema: public; Owner: admin
--

SELECT pg_catalog.setval('public.funcionario_id_funcionario_seq', 3, true);


--
-- Name: gerente_id_gerente_seq; Type: SEQUENCE SET; Schema: public; Owner: admin
--

SELECT pg_catalog.setval('public.gerente_id_gerente_seq', 2, true);


--
-- Name: produto_id_produto_seq; Type: SEQUENCE SET; Schema: public; Owner: admin
--

SELECT pg_catalog.setval('public.produto_id_produto_seq', 7, true);


--
-- Name: setor_id_seq; Type: SEQUENCE SET; Schema: public; Owner: admin
--

SELECT pg_catalog.setval('public.setor_id_seq', 3, true);


--
-- Name: usuario_id_seq; Type: SEQUENCE SET; Schema: public; Owner: admin
--

SELECT pg_catalog.setval('public.usuario_id_seq', 3, true);


--
-- Name: funcionario funcionario_pkey; Type: CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.funcionario
    ADD CONSTRAINT funcionario_pkey PRIMARY KEY (id_funcionario);


--
-- Name: gerente gerente_id_usuario_key; Type: CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.gerente
    ADD CONSTRAINT gerente_id_usuario_key UNIQUE (id_usuario);


--
-- Name: gerente gerente_pkey; Type: CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.gerente
    ADD CONSTRAINT gerente_pkey PRIMARY KEY (id_gerente);


--
-- Name: produto produto_pkey; Type: CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.produto
    ADD CONSTRAINT produto_pkey PRIMARY KEY (id_produto);


--
-- Name: setor setor_pkey; Type: CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.setor
    ADD CONSTRAINT setor_pkey PRIMARY KEY (id);


--
-- Name: usuario usuario_email_key; Type: CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT usuario_email_key UNIQUE (email);


--
-- Name: usuario usuario_pkey; Type: CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT usuario_pkey PRIMARY KEY (id);


--
-- Name: funcionario funcionario_id_gerente_fkey; Type: FK CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.funcionario
    ADD CONSTRAINT funcionario_id_gerente_fkey FOREIGN KEY (id_gerente) REFERENCES public.gerente(id_gerente);


--
-- Name: gerente gerente_id_setor_fkey; Type: FK CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.gerente
    ADD CONSTRAINT gerente_id_setor_fkey FOREIGN KEY (id_setor) REFERENCES public.setor(id);


--
-- Name: gerente gerente_id_usuario_fkey; Type: FK CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.gerente
    ADD CONSTRAINT gerente_id_usuario_fkey FOREIGN KEY (id_usuario) REFERENCES public.usuario(id);


--
-- Name: produto produto_id_setor_fkey; Type: FK CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.produto
    ADD CONSTRAINT produto_id_setor_fkey FOREIGN KEY (id_setor) REFERENCES public.setor(id);


--
-- PostgreSQL database dump complete
--

