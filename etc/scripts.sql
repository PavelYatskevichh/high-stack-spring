CREATE SCHEMA public;

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

SELECT uuid_generate_v4();

SELECT gen_random_uuid() -- Don't need EXTENSION "uuid-ossp"

---

CREATE SCHEMA IF NOT EXISTS auth;

CREATE TYPE auth.user_roles
	AS ENUM ('AUTHOR', 'PUBLISHER', 'REVIEWER');

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
    ('00000000-0000-0000-0000-000000000002', 'Bob', 'Publisher', 'bob.publisher@example.com', 'PUBLISHER', CURRENT_TIMESTAMP),
    ('00000000-0000-0000-0000-000000000003', 'Charlie', 'Reviewer', 'charlie.reviewer@example.com', 'REVIEWER', CURRENT_TIMESTAMP);

---

-- Show content with tags
SELECT ci.id, ci.author_id, ci.status, t.name FROM content_creation.content_items ci
LEFT JOIN content_creation.content_item_tags cit ON cit.content_item_id = ci.id
LEFT JOIN content_creation.tags t ON t.id = cit.tag_id
ORDER BY ci.id ASC

-- Change status
update content_creation.content_items
set status = 'DRAFT'
where status = 'SUBMITTED'
and id = '11111111-1111-1111-1111-111111111111'