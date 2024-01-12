INSERT INTO users (username, password, enabled)
VALUES
    ('pelle', 'pelle', 1),
    ('maja', 'maja', 1),
    ('måns', 'måns', 1),
    ('bill', 'bill', 1),
    ('bull', 'bull', 1);

INSERT INTO customers(first_name, last_name, address_id, user_id, email, phone, date_of_birth)
VALUES
    ('Pelle', 'Svanslös', 1, 1,'pelle@svanslos.se', '070123456', '1992-05-05'),
    ('Maja', 'Gräddnos', 2, 2, 'maja@graddnos.se', '123456789', '1993-02-10'),
    ('Måns', 'Elake', 3, 3, 'elake@mans.se', '112112112', '1990-06-07'),
    ('Bill', 'Katt', 4,  4, 'bill@katt.se', '0708456125', '1994-02-03'),
    ('Bull', 'Katt', 4, 5, 'bull@katt.se', '0709456324', '1994-02-03');

INSERT INTO address(street, postal_code, city)
VALUES
    ('Kattgränd 2b', 25874, 'Uppsala'),
    ('Spinnarebacken 1b', 25875, 'Uppsala'),
    ('Elakehörnet 1', 25877, 'Uppsala'),
    ('Tvillinggränd 1', 25877, 'Uppsala'),
    ('Mjölkbacken', 25878, 'Uppsala');

INSERT INTO destinations(country, city, hotel_name, price_per_week_sek, price_per_week_pln)
VALUES
    ('Malta', 'Sliema', 'Hotel Triq San Vincenz 111', 10000.0, 0.0),
    ('Irland', 'Dublin', 'Clayton Hotel Cardiff Lane', 11000.0, 0.0),
    ('Frankrike', 'Grenoble', 'Hotel Chem. du Murier 331', 12000.0, 0.0),
    ('Belgien', 'De Haan', 'Hotel Coppieterstraat 27', 13000.0, 0.0),
    ('Italien', 'San Remo', 'Hotel Bobby Executive', 14000.0, 0.0);


INSERT INTO authorities (user_id, authority) VALUES
    (1, 'USER'),
    (2, 'ADMIN'),
    (3, 'USER'),
    (4, 'USER'),
    (5, 'USER');



