DROP TABLE IF EXISTS public."libraryDb" CASCADE;

CREATE TABLE IF NOT EXISTS public."libraryDb" (
                                                  id SERIAL PRIMARY KEY,
                                                  name VARCHAR(50),
                                                  avtor VARCHAR(50),
                                                  zhyl INT,
                                                  sany INT
);

CREATE SEQUENCE IF NOT EXISTS "libraryDb_id_seq";

ALTER TABLE public."libraryDb" ALTER COLUMN id SET DEFAULT nextval('"libraryDb_id_seq"');

INSERT INTO public."libraryDb" (name, avtor, zhyl, sany) VALUES
                                                             ('Baqytsyz Zhamal', 'Mirzhakyp Dulatov', 1910, 4),
                                                             ('On qol', 'Tolen Abdik', 1942, 5),
                                                             ('Abay zholy', 'Mukagaly Maqataev', 1942, 21)
ON CONFLICT DO NOTHING;

SELECT setval('"libraryDb_id_seq"', (SELECT MAX(id) FROM public."libraryDb"));
