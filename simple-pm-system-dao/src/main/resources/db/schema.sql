CREATE TABLE employee
(
    id         bigserial PRIMARY KEY,
    name       text NOT NULL,
    surname    text NOT NULL,
    status     text NOT NULL,
    patronymic text,
    account    text UNIQUE,
    position   text,
    email      text
);

CREATE TABLE project
(
    id          bigserial PRIMARY KEY,
    code_name    text NOT NULL UNIQUE,
    name        text NOT NULL,
    status      text NOT NULL,
    description text
);

CREATE TABLE task
(
    id          bigserial PRIMARY KEY,
    name        text      NOT NULL,
    date_created timestamp NOT NULL,
    date_updated timestamp NOT NULL,
    author      bigint    NOT NULL,
    CONSTRAINT author_id foreign key (author) references employee (id),
    status      text      NOT NULL,
    executor    bigint,
    CONSTRAINT executor_id foreign key (executor) references employee (id),
    labor_costs  integer   NOT NULL CONSTRAINT positive_laborCosts CHECK (labor_costs > 0),
    project     bigint    NOT NULL,
    CONSTRAINT project_id foreign key (project) references project (id),
    deadline    timestamp NOT NULL,
    description text
);

CREATE TABLE project_participant
(
    id       bigserial PRIMARY KEY,
    employee bigint NOT NULL,
    CONSTRAINT employee_id foreign key (employee) references employee (id),
    project  bigint NOT NULL,
    CONSTRAINT project_id foreign key (project) references project (id),
    role     text NOT NULL
);