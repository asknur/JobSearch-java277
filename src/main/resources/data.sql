create table if not exists users
(
    id int auto_increment primary key,
    name text,
    surname text,
    age int,
    email text,
    password text,
    phone_number varchar(55),
    avatar text,
    account_type varchar(50)
    );

create table if not exists categories
(
    id int auto_increment primary key,
    name text,
    parent_id int
);

create table if not exists contact_type
(
    id int auto_increment primary key,
    type text
);

create table if not exists resumes
(
    id int auto_increment primary key,
    applicant_id int,
    name text,
    category_id int,
    salary real,
    is_active boolean,
    created_date timestamp,
    update_time timestamp,
    foreign key (applicant_id) references users(id) on DELETE cascade,
    foreign key (category_id) references categories(id) on DELETE cascade
    );

create table if not exists vacancies
(
    id int auto_increment primary key,
    name text,
    description text,
    category_id int,
    salary real,
    exp_from int,
    exp_to int,
    is_active boolean,
    author_id int,
    created_date timestamp,
    update_time timestamp,
    foreign key (category_id) references categories(id) on DELETE cascade,
    foreign key (author_id) references users(id) on DELETE cascade
    );

create table if not exists contacts_info
(
    id int auto_increment primary key,
    type_id int,
    resume_id int,
    contact_value text,
    foreign key (type_id) references contact_type(id) on DELETE cascade,
    foreign key (resume_id) references resumes(id) on DELETE cascade
    );

create table if not exists education_info
(
    id int auto_increment primary key,
    resume_id int,
    institution text,
    program text,
    start_date date,
    end_date date,
    degree text,
    foreign key (resume_id) references resumes(id) on DELETE cascade
    );

create table if not exists work_experience_info
(
    id int auto_increment primary key,
    resume_id int,
    years int,
    company_name text,
    position text,
    responsibilities text,
    foreign key (resume_id) references resumes(id) on DELETE cascade
    );

create table if not exists responded_applicants
(
    id int auto_increment primary key,
    resume_id int,
    vacancy_id int,
    confirmation boolean,
    foreign key (resume_id) references resumes(id) on DELETE cascade,
    foreign key (vacancy_id) references vacancies(id) on DELETE cascade
    );


create table if not exists messages
(
    id int auto_increment primary key,
    responded_applicant_id int,
    content text,
    timestamp timestamp,
    foreign key (responded_applicant_id) references responded_applicants(id) on DELETE cascade
    );

-- USERS
INSERT INTO users (name, surname, age, email, password, phone_number, avatar, account_type)
VALUES ('Асан', 'Токтосунов', 25, 'asan@example.com', '123', '0500123456', 'avatar1.png', 'applicant'),
       ('Улан', 'Маматов', 30, 'ulan@example.com', '123', '0500234567', 'avatar2.png', 'employer');

-- CATEGORIES
INSERT INTO categories (name, parent_id)
VALUES ('IT', null),
       ('HR', null);

-- CONTACT TYPE
INSERT INTO contact_type (type)
VALUES ('email'),
       ('phone');

-- RESUMES
INSERT INTO resumes (applicant_id, name, category_id, salary, is_active, created_date, update_time)
VALUES (1, 'Java Developer', 1, 1200.00, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (1, 'QA Engineer', 1, 900.00, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- VACANCIES
INSERT INTO vacancies (name, description, category_id, salary, exp_from, exp_to, is_active, author_id, created_date,
                       update_time)
VALUES ('Backend Developer', 'Spring Boot, PostgreSQL', 1, 1500.00, 2, 5, true, 2, CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP),
       ('HR Specialist', 'Подбор персонала, интервью', 2, 800.00, 1, 3, true, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- CONTACTS INFO
INSERT INTO contacts_info (type_id, resume_id, contact_value)
VALUES (1, 1, 'asan@example.com'),
       (2, 1, '0500123456');

-- EDUCATION INFO
INSERT INTO education_info (resume_id, institution, program, start_date, end_date, degree)
VALUES (1, 'Kyrgyz State University', 'Computer Science', '2015-09-01', '2019-06-30', 'Bachelor'),
       (2, 'Manas University', 'Software Engineering', '2016-09-01', '2020-06-30', 'Bachelor');

-- WORK EXPERIENCE INFO
INSERT INTO work_experience_info (resume_id, years, company_name, position, responsibilities)
VALUES (1, 2, 'Tech Solutions', 'Junior Developer', 'Worked on Java backend development'),
       (2, 1, 'SoftPro', 'QA Tester', 'Manual testing and reporting bugs');

-- RESPONDED APPLICANTS
INSERT INTO responded_applicants (resume_id, vacancy_id, confirmation)
VALUES (1, 1, true),
       (2, 2, false);

-- MESSAGES
INSERT INTO messages (responded_applicant_id, content, timestamp)
VALUES (1, 'Здравствуйте! Ваш отклик принят.', CURRENT_TIMESTAMP),
       (2, 'Спасибо за интерес к вакансии. Мы свяжемся с вами позже.', CURRENT_TIMESTAMP);