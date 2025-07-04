CREATE TABLE utilizatori (
                             id BIGINT PRIMARY KEY AUTO_INCREMENT,
                            username VARCHAR(255) NOT NULL UNIQUE,
                            password VARCHAR(255) NOT NULL
);

CREATE TABLE roluri (
                        id BIGINT PRIMARY KEY AUTO_INCREMENT,
                     nume VARCHAR(50) NOT NULL
);

CREATE TABLE utilizatori_roluri (
                                    utilizator_id BIGINT NOT NULL,
                                    rol_id BIGINT NOT NULL,
                                    PRIMARY KEY (utilizator_id, rol_id),
                                    FOREIGN KEY (utilizator_id) REFERENCES utilizatori(id),
                                    FOREIGN KEY (rol_id) REFERENCES roluri(id)
);

CREATE TABLE pacienti (
                         id BIGINT PRIMARY KEY AUTO_INCREMENT,
                         nume VARCHAR(255),
                         prenume VARCHAR(255),
                         telefon VARCHAR(20),
                         email VARCHAR(255),
                         utilizator_id BIGINT UNIQUE,
                         FOREIGN KEY (utilizator_id) REFERENCES utilizatori(id)
);

CREATE TABLE clinici (
                         id BIGINT PRIMARY KEY AUTO_INCREMENT,
                         nume VARCHAR(255),
                         telefon VARCHAR(20),
                         adresa VARCHAR(255)
);

CREATE TABLE doctori (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        nume VARCHAR(255),
                        prenume VARCHAR(255),
                        telefon VARCHAR(20),
                        specializare VARCHAR(255),
                        email VARCHAR(255),
                        utilizator_id BIGINT UNIQUE,
                        FOREIGN KEY (utilizator_id) REFERENCES utilizatori(id)
);

CREATE TABLE doctor_clinica (
                                doctor_id BIGINT NOT NULL,
                                clinica_id BIGINT NOT NULL,
                                PRIMARY KEY (doctor_id, clinica_id),
                                FOREIGN KEY (doctor_id) REFERENCES doctori(id),
                                FOREIGN KEY (clinica_id) REFERENCES clinici(id)
);

CREATE TABLE programari (
                            id BIGINT PRIMARY KEY AUTO_INCREMENT,
                            data_ora TIMESTAMP,
                            pacient_id BIGINT NOT NULL,
                            doctor_id BIGINT NOT NULL,
                            clinica_id BIGINT,
                            observatii VARCHAR(500),
                            FOREIGN KEY (pacient_id) REFERENCES pacienti(id),
                            FOREIGN KEY (doctor_id) REFERENCES doctori(id),
                            FOREIGN KEY (clinica_id) REFERENCES clinici(id)
);

CREATE TABLE recenzii (
                          id BIGINT PRIMARY KEY AUTO_INCREMENT,
                          pacient_id BIGINT NOT NULL,
                          doctor_id BIGINT NOT NULL,
                          comentariu VARCHAR(1000),
                          rating INT,
                          data_recenzie TIMESTAMP,
                          FOREIGN KEY (pacient_id) REFERENCES pacienti(id),
                          FOREIGN KEY (doctor_id) REFERENCES doctori(id)
);

CREATE TABLE diagnostice (
                             id BIGINT AUTO_INCREMENT PRIMARY KEY,
                             detalii VARCHAR(1000),
                             cod_diagnostic VARCHAR(255),
                             data DATE,
                             programare_id BIGINT UNIQUE,
                             CONSTRAINT fk_diag_programare FOREIGN KEY (programare_id) REFERENCES programari(id)
);

CREATE TABLE retete (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        medicamente VARCHAR(1000),
                        instructiuni VARCHAR(1000),
                        data_emitere DATE,
                        programare_id BIGINT UNIQUE,
                        CONSTRAINT fk_reteta_programare FOREIGN KEY (programare_id) REFERENCES programari(id)
);
