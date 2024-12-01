CREATE SCHEMA public;

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

SELECT uuid_generate_v4();

---

CREATE SCHEMA IF NOT EXISTS auth;

CREATE TYPE auth.user_roles
	AS ENUM ('AUTHOR', 'MODERATOR', 'REVIEWER');

CREATE TABLE auth.users (
    id UUID PRIMARY KEY,
    first_name VARCHAR(64) NOT NULL,
    last_name VARCHAR(64) NOT NULL,
    email VARCHAR(128) UNIQUE NOT NULL,
    role auth.user_roles NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO auth.users (id, first_name, last_name, email, role, created_at)
VALUES
    ('00000000-0000-0000-0000-000000000001', 'Alice', 'Author', 'alice.author@example.com', 'AUTHOR', CURRENT_TIMESTAMP),
    ('00000000-0000-0000-0000-000000000002', 'Bob', 'Moderator', 'bob.moderator@example.com', 'MODERATOR', CURRENT_TIMESTAMP),
    ('00000000-0000-0000-0000-000000000003', 'Charlie', 'Reviewer', 'charlie.reviewer@example.com', 'REVIEWER', CURRENT_TIMESTAMP);
