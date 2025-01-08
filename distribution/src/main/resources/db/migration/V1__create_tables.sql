CREATE TABLE distribution.publications (
    id UUID PRIMARY KEY,
    content_item_id UUID NOT NULL,
    title VARCHAR(255) NOT NULL,
    body TEXT NOT NULL,
    published_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE distribution.audience (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE distribution.views (
    id UUID PRIMARY KEY,
    publication_id UUID NOT NULL REFERENCES distribution.publications(id),
    audience_id UUID NOT NULL REFERENCES distribution.audience(id),
    viewed_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE distribution.feedbacks (
    id UUID PRIMARY KEY,
    publication_id UUID NOT NULL REFERENCES distribution.publications(id),
    audience_id UUID NOT NULL REFERENCES distribution.audience(id),
    rating INT CHECK (rating BETWEEN 1 AND 5),
    comment TEXT,
    submitted_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
