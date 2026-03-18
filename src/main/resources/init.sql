-- USERS
INSERT INTO user_table (name, role) VALUES ('Остап Бендер', 'MASTER');
INSERT INTO user_table (name, role) VALUES ('Киса Воробьянинов', 'MASTER');
INSERT INTO user_table (name, role) VALUES ('Диспетчер', 'DISPATCHER');

-- REQUESTS
INSERT INTO request (client_name, phone, address, description, status, created_at)
VALUES ('Иван', '12345', 'Москва', 'Не работает розетка', 'NEW', now());

INSERT INTO request (client_name, phone, address, description, status, created_at)
VALUES ('Мария', '67890', 'СПб', 'Протечка трубы', 'NEW', now());