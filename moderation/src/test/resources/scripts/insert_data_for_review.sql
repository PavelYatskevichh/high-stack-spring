INSERT INTO moderation.reviews (id, content_item_id, reviewer_id, status, message, reviewed_at)
VALUES
    ('88888888-dddd-8888-8888-888888888881', '11111111-dddd-1111-1111-111111111112', '00000000-dddd-0000-0000-000000000003',
    'APPROVED', 'Test message.', CURRENT_TIMESTAMP);

INSERT INTO moderation.quality_metrics (id, name, description)
VALUES
    ('99999999-dddd-9999-9999-999999999991', 'Clarity', 'Clarity of the content.'),
    ('99999999-dddd-9999-9999-999999999992', 'Relevance', 'Relevance to the topic.');

INSERT INTO moderation.review_quality_metrics (review_id, quality_metric_id, score)
VALUES
    ('88888888-dddd-8888-8888-888888888881', '99999999-dddd-9999-9999-999999999991', 9),
    ('88888888-dddd-8888-8888-888888888881', '99999999-dddd-9999-9999-999999999992', 8);
