INSERT INTO usr (name, surname, age, email, password, phone_number, avatar, account_type, enabled)
VALUES ('Асан', 'Токтосунов', 25, 'asan@example.com', '$2a$12$WB2YUbFcCN0tm44SBcKUjua9yiFBsfB3vW02IjuwzY7HGtlQIKzy2', '0500123456', 'avatar1.png', 'admin', true),
       ('Улан', 'Маматов', 30, 'ulan@example.com', '$2a$12$WB2YUbFcCN0tm44SBcKUjua9yiFBsfB3vW02IjuwzY7HGtlQIKzy2', '0500234567', 'avatar2.png', 'user', true),
       ('Аскар', 'Нурбеков', 25, 'askar@example.com', '$2a$12$WB2YUbFcCN0tm44SBcKUjua9yiFBsfB3vW02IjuwzY7HGtlQIKzy2', '0500123546', 'avatar3.png', 'quest', true);

INSERT INTO categories (name, parent_id)
VALUES ('IT', null),
       ('HR', null);

INSERT INTO categories (name, parent_id)
VALUES ('Java Developer', 1),
       ('HR Specialist', 2);

INSERT INTO contact_type (type)
VALUES ('email'),
       ('phone');

INSERT INTO resumes (applicant_id, name, category_id, salary, is_active, created_date, update_time)
VALUES (2, 'Java Developer', 1, 1200.00, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (2, 'QA Engineer', 1, 900.00, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO vacancies (name, description, category_id, salary, exp_from, exp_to, is_active, author_id, created_date,
                       update_time)
VALUES ('Backend Developer', 'Spring Boot, PostgreSQL', 1, 1500.00, 2, 5, true, 2, CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP),
       ('HR Specialist', 'Подбор персонала, интервью', 2, 800.00, 1, 3, true, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO contacts_info (type_id, resume_id, contact_value)
VALUES (1, 1, 'asan@example.com'),
       (2, 1, '0500123456');

INSERT INTO education_info (resume_id, institution, program, start_date, end_date, degree)
VALUES (1, 'Kyrgyz State University', 'Computer Science', '2015-09-01', '2019-06-30', 'Bachelor'),
       (2, 'Manas University', 'Software Engineering', '2016-09-01', '2020-06-30', 'Bachelor');

-- WORK EXPERIENCE INFO
INSERT INTO work_experience_info (resume_id, years, company_name, position, responsibilities)
VALUES (1, 2, 'Tech Solutions', 'Junior Developer', 'Worked on Java backend development'),
       (2, 1, 'SoftPro', 'QA Tester', 'Manual testing and reporting bugs');

INSERT INTO responded_applicants (resume_id, vacancy_id, confirmation)
VALUES (1, 1, true),
       (2, 2, false);

INSERT INTO messages (responded_applicant_id, content, timestamp)
VALUES (1, 'Здравствуйте! Ваш отклик принят.', CURRENT_TIMESTAMP),
       (2, 'Спасибо за интерес к вакансии. Мы свяжемся с вами позже.', CURRENT_TIMESTAMP);