INSERT INTO markets (address, img_url, name)
VALUES ('Avenida das Flores', 'mercadoflor.url.com', 'Mercado Flor'),
       ('Rua dos Pinheiros', 'superpinheiro.url.com', 'Super Pinheiro'),
       ('Avenida Central', 'mercadoavcentral.url.com', 'Mercado Avenida Central'),
       ('Praça dos Coqueiros', 'coqueiromercado.url.com', 'Coqueiro Mercado'),
       ('Rua da Saudade', 'mercadosaudade.url.com', 'Mercado Saudade'),
       ('Travessa do Sol', 'solsupermercado.url.com', 'Sol Supermercado'),
       ('Avenida das Acácias', 'acaiciasuper.url.com', 'Acácia Supermercado'),
       ('Rua dos Limoeiros', 'limoeirosuper.url.com', 'Supermercado Limoeiro'),
       ('Estrada Velha', 'velhamercado.url.com', 'Mercado Velha Estrada'),
       ('Beco das Orquídeas', 'orquideassuper.url.com', 'Supermercado Orquídeas');

INSERT INTO working_days (week_day, opening_time, closing_time)
VALUES ('MONDAY', '08:00:00', '18:00:00'),
       ('TUESDAY', '08:00:00', '18:00:00'),
       ('WEDNESDAY', '08:00:00', '18:00:00'),
       ('THURSDAY', '08:00:00', '18:00:00'),
       ('FRIDAY', '08:00:00', '18:00:00'),
       ('SATURDAY', '09:00:00', '14:00:00');

INSERT INTO markets_working_days
VALUES (1, 1),
       (1, 2),
       (1, 3),
       (1, 4),
       (1, 5),
       (1, 6);

INSERT INTO markets_working_days
VALUES (2, 1),
       (2, 2),
       (2, 3),
       (2, 4),
       (2, 5),
       (2, 6);

INSERT INTO markets_working_days
VALUES (3, 1),
       (3, 2),
       (3, 3),
       (3, 4),
       (3, 5),
       (3, 6);

INSERT INTO markets_working_days
VALUES (4, 1),
       (4, 2),
       (4, 3),
       (4, 4),
       (4, 5),
       (4, 6);

INSERT INTO markets_working_days
VALUES (5, 1),
       (5, 2),
       (5, 3),
       (5, 4),
       (5, 5),
       (5, 6);




