INSERT INTO roluri (nume) VALUES ('ROLE_ADMIN');
INSERT INTO roluri (nume) VALUES ('ROLE_DOCTOR');
INSERT INTO roluri (nume) VALUES ('ROLE_PACIENT');

INSERT INTO utilizatori (username, password) VALUES ('admin_test', '1234');

INSERT INTO utilizatori_roluri (utilizator_id, rol_id) VALUES (1, 4);

UPDATE utilizatori
SET password = '$2a$10$WzE1VJn3n2SqM9Lz7kLsvuBqshGTiHtD/R8EvZ3H8ri5yQv2Rg0z6'
WHERE username = 'admin_test';