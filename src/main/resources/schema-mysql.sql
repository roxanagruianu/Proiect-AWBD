CREATE TABLE IF NOT EXISTS utilizatori (
                             id BIGINT PRIMARY KEY AUTO_INCREMENT,
                             username VARCHAR(255) NOT NULL UNIQUE,
                             password VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS roluri (
                        id BIGINT PRIMARY KEY AUTO_INCREMENT,
                        nume VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS utilizatori_roluri (
                                    utilizator_id BIGINT NOT NULL,
                                    rol_id BIGINT NOT NULL,
                                    PRIMARY KEY (utilizator_id, rol_id),
                                    FOREIGN KEY (utilizator_id) REFERENCES utilizatori(id) ON DELETE CASCADE,
                                    FOREIGN KEY (rol_id) REFERENCES roluri(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS pacienti (
                          id BIGINT PRIMARY KEY AUTO_INCREMENT,
                          nume VARCHAR(255),
                          prenume VARCHAR(255),
                          telefon VARCHAR(20),
                          email VARCHAR(255),
                          utilizator_id BIGINT UNIQUE,
                          FOREIGN KEY (utilizator_id) REFERENCES utilizatori(id) ON DELETE SET NULL
);

CREATE TABLE IF NOT EXISTS clinici (
                         id BIGINT PRIMARY KEY AUTO_INCREMENT,
                         nume VARCHAR(255),
                         telefon VARCHAR(20),
                         adresa VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS doctori (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         nume VARCHAR(255),
                         prenume VARCHAR(255),
                         telefon VARCHAR(20),
                         specializare VARCHAR(255),
                         email VARCHAR(255),
                         utilizator_id BIGINT UNIQUE,
                         FOREIGN KEY (utilizator_id) REFERENCES utilizatori(id) ON DELETE SET NULL
);

CREATE TABLE IF NOT EXISTS doctor_clinica (
                                doctor_id BIGINT NOT NULL,
                                clinica_id BIGINT NOT NULL,
                                PRIMARY KEY (doctor_id, clinica_id),
                                FOREIGN KEY (doctor_id) REFERENCES doctori(id) ON DELETE CASCADE,
                                FOREIGN KEY (clinica_id) REFERENCES clinici(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS programari (
                            id BIGINT PRIMARY KEY AUTO_INCREMENT,
                            data_ora TIMESTAMP,
                            pacient_id BIGINT NOT NULL,
                            doctor_id BIGINT NOT NULL,
                            clinica_id BIGINT,
                            observatii VARCHAR(500),
                            FOREIGN KEY (pacient_id) REFERENCES pacienti(id) ON DELETE CASCADE,
                            FOREIGN KEY (doctor_id) REFERENCES doctori(id) ON DELETE CASCADE,
                            FOREIGN KEY (clinica_id) REFERENCES clinici(id) ON DELETE SET NULL
);

CREATE TABLE IF NOT EXISTS recenzii (
                          id BIGINT PRIMARY KEY AUTO_INCREMENT,
                          pacient_id BIGINT NOT NULL,
                          doctor_id BIGINT NOT NULL,
                          comentariu TEXT,
                          rating INT CHECK (rating BETWEEN 1 AND 5),
                          data_recenzie TIMESTAMP,
                          FOREIGN KEY (pacient_id) REFERENCES pacienti(id) ON DELETE CASCADE,
                          FOREIGN KEY (doctor_id) REFERENCES doctori(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS diagnostice (
                             id BIGINT AUTO_INCREMENT PRIMARY KEY,
                             detalii TEXT,
                             cod_diagnostic VARCHAR(255),
                             data DATE,
                             programare_id BIGINT UNIQUE,
                             FOREIGN KEY (programare_id) REFERENCES programari(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS retete (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        medicamente TEXT,
                        instructiuni TEXT,
                        data_emitere DATE,
                        programare_id BIGINT UNIQUE,
                        FOREIGN KEY (programare_id) REFERENCES programari(id) ON DELETE CASCADE
);
