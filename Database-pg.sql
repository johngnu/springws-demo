-- create PostgreSQL 9.6 Database

CREATE TABLE public.libro
(
  id_libro serial,
  titulo character varying(255),
  fecha_edicion date,
  CONSTRAINT libro_pkey PRIMARY KEY (id_libro)
);

CREATE TABLE public.autor
(
  id_autor serial,
  nombre character varying(255),
  CONSTRAINT autor_pkey PRIMARY KEY (id_autor)
);

CREATE TABLE public.autor_libro
(
  id_autor integer,
  id_libro integer,
  CONSTRAINT autor_libro_id_autor_fkey FOREIGN KEY (id_autor)
      REFERENCES public.autor (id_autor) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT autor_libro_id_libro_fkey FOREIGN KEY (id_libro)
      REFERENCES public.libro (id_libro) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);
