insert into roles(name)
values ('ROLE_ADMIN'),
       ('ROLE_USER');

INSERT INTO users (id, username, password, role_name)
VALUES (1, 'user1', 'test', 'ROLE_ADMIN');

INSERT INTO users (id, username, password, role_name)
VALUES (2, 'user2', 'test', 'ROLE_USER');
