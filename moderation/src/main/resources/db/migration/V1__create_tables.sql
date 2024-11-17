CREATE TYPE moderation.review_status
	AS ENUM ('APPROVED', 'REJECTED', 'NEEDS_REVISION');

CREATE TABLE moderation.reviews (
    id UUID PRIMARY KEY,
    content_item_id UUID NOT NULL,
    reviewer_id UUID NOT NULL,
    status moderation.review_status NOT NULL,
    message TEXT,
    reviewed_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE moderation.quality_metrics (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT
);

CREATE TABLE moderation.review_quality_metrics (
    review_id UUID NOT NULL REFERENCES moderation.reviews(id),
    quality_metric_id UUID NOT NULL REFERENCES moderation.quality_metrics(id),
    score INT CHECK (score BETWEEN 1 AND 10),
    PRIMARY KEY (review_id, quality_metric_id)
);
