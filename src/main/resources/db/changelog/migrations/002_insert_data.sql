-- USERS
INSERT INTO usr (name, surname, age, email, password, phone_number, avatar, account_type, enabled)
VALUES ('Асан', 'Токтосунов', 25, 'asan@example.com', '$2a$12$WB2YUbFcCN0tm44SBcKUjua9yiFBsfB3vW02IjuwzY7HGtlQIKzy2',
        '0500123456', 'avatar1.png', 'APPLICANT', true),
       ('Улан', 'Маматов', 30, 'ulan@example.com', '$2a$12$WB2YUbFcCN0tm44SBcKUjua9yiFBsfB3vW02IjuwzY7HGtlQIKzy2',
        '0500234567', 'avatar2.png', 'EMPLOYER', true),
       ('Аскар', 'Нурбеков', 25, 'askar@example.com', '$2a$12$WB2YUbFcCN0tm44SBcKUjua9yiFBsfB3vW02IjuwzY7HGtlQIKzy2',
        '0500123546', 'avatar3.png', 'APPLICANT', true);

-- CATEGORIES (root)
INSERT INTO categories (name, parent_id)
VALUES ('IT', null),
       ('HR', null);

-- CATEGORIES (children)
INSERT INTO categories (name, parent_id)
VALUES ('Java Developer', (SELECT id FROM categories WHERE name = 'IT')),
       ('HR Specialist', (SELECT id FROM categories WHERE name = 'HR'));

-- CONTACT TYPES
INSERT INTO contact_type (type)
VALUES ('email'),
       ('phone');

-- RESUMES
INSERT INTO resumes (applicant_id, name, category_id, salary, is_active, created_date, update_time)
VALUES ((SELECT id FROM usr WHERE email = 'ulan@example.com'),
        'Java Developer',
        (SELECT id FROM categories WHERE name = 'IT'),
        1200.00,
        true,
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP),
       ((SELECT id FROM usr WHERE email = 'ulan@example.com'),
        'QA Engineer',
        (SELECT id FROM categories WHERE name = 'IT'),
        900.00,
        true,
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP);

-- VACANCIES
INSERT INTO vacancies (name, description, category_id, salary, exp_from, exp_to,
                       is_active, author_id, created_date, update_time)
VALUES ('Backend Developer',
        'Spring Boot, PostgreSQL',
        (SELECT id FROM categories WHERE name = 'IT'),
        1500.00,
        2,
        5,
        true,
        (SELECT id FROM usr WHERE email = 'ulan@example.com'),
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP),
       ('HR Specialist',
        'Подбор персонала, интервью',
        (SELECT id FROM categories WHERE name = 'HR'),
        800.00,
        1,
        3,
        true,
        (SELECT id FROM usr WHERE email = 'ulan@example.com'),
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP);

-- CONTACTS INFO
INSERT INTO contacts_info (type_id, resume_id, contact_value)
VALUES ((SELECT id FROM contact_type WHERE type = 'email'),
        (SELECT id FROM resumes WHERE name = 'Java Developer'),
        'asan@example.com'),
       ((SELECT id FROM contact_type WHERE type = 'phone'),
        (SELECT id FROM resumes WHERE name = 'Java Developer'),
        '0500123456');

-- EDUCATION
INSERT INTO education_info (resume_id, institution, program, start_date, end_date, degree)
VALUES ((SELECT id FROM resumes WHERE name = 'Java Developer'),
        'Kyrgyz State University',
        'Computer Science',
        '2015-09-01',
        '2019-06-30',
        'Bachelor'),
       ((SELECT id FROM resumes WHERE name = 'QA Engineer'),
        'Manas University',
        'Software Engineering',
        '2016-09-01',
        '2020-06-30',
        'Bachelor');

-- WORK EXPERIENCE
INSERT INTO work_experience_info (resume_id, years, company_name, position, responsibilities)
VALUES ((SELECT id FROM resumes WHERE name = 'Java Developer'),
        2,
        'Tech Solutions',
        'Junior Developer',
        'Worked on Java backend development'),
       ((SELECT id FROM resumes WHERE name = 'QA Engineer'),
        1,
        'SoftPro',
        'QA Tester',
        'Manual testing and reporting bugs');

-- RESPONDED APPLICANTS
INSERT INTO responded_applicants (resume_id, vacancy_id, confirmation)
VALUES ((SELECT id FROM resumes WHERE name = 'Java Developer'),
        (SELECT id FROM vacancies WHERE name = 'Backend Developer'),
        true),
       ((SELECT id FROM resumes WHERE name = 'QA Engineer'),
        (SELECT id FROM vacancies WHERE name = 'HR Specialist'),
        false);

-- MESSAGES
INSERT INTO messages (responded_applicant_id, content, timestamp)
VALUES ((SELECT ra.id
         FROM responded_applicants ra
                  JOIN resumes r ON ra.resume_id = r.id
         WHERE r.name = 'Java Developer'
    LIMIT 1), 'Здравствуйте! Ваш отклик принят.', CURRENT_TIMESTAMP ),
(
    (SELECT ra.id
     FROM responded_applicants ra
     JOIN resumes r ON ra.resume_id = r.id
     WHERE r.name = 'QA Engineer'
     LIMIT 1),
    'Спасибо за интерес к вакансии. Мы свяжемся с вами позже.',
    CURRENT_TIMESTAMP
);