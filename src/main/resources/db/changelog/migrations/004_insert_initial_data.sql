
-- Authorities
insert into authorities (authority)
values ('MAKE'),
       ('EDIT'),
       ('REMOVE'),
       ('READ_ONLY');

-- Roles
insert into roles(role)
values ('ADMIN'),
       ('USER'),
       ('GUEST');

-- Role_auth
insert into role_auth (auth_id, role_id)
values ((select id from authorities where authority = 'READ_ONLY'),
        (select id from roles where role = 'ADMIN')),
       ((select id from authorities where authority = 'MAKE'),
        (select id from roles where role = 'ADMIN')),
       ((select id from authorities where authority = 'EDIT'),
        (select id from roles where role = 'ADMIN')),
       ((select id from authorities where authority = 'REMOVE'),
        (select id from roles where role = 'ADMIN')),
       ((select id from authorities where authority = 'READ_ONLY'),
        (select id from roles where role = 'USER')),
       ((select id from authorities where authority = 'MAKE'),
        (select id from roles where role = 'USER')),
       ((select id from authorities where authority = 'READ_ONLY'),
        (select id from roles where role = 'GUEST'));

insert into user_role(role_id, usr_id)
values ((select id from roles where role = 'ADMIN'),
        (select id from usr where email = 'asan@example.com')),
       ((select id from roles where role = 'USER'),
        (select id from usr where email = 'ulan@example.com')),
       ((select id from roles where role = 'GUEST'),
        (select id from usr where email = 'askar@example.com'));
