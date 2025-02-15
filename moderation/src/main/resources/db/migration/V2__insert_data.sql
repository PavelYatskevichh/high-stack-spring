INSERT INTO moderation.reviews (id, content_item_id, reviewer_id, status, message, reviewed_at)
VALUES
    ('88888888-8888-8888-8888-888888888881', '11111111-1111-1111-1111-111111111112', '00000000-0000-0000-0000-000000000003',
    'APPROVED', 'This content is exemplary and ready for approval.', CURRENT_TIMESTAMP),
    ('88888888-8888-8888-8888-888888888882', '11111111-1111-1111-1111-111111111111', '00000000-0000-0000-0000-000000000003',
    'NEEDS_REVISION', 'This is well-written but needs to address the target audience more clearly.', CURRENT_TIMESTAMP),
    ('88888888-8888-8888-8888-888888888883', '11111111-1111-1111-1111-111111111115', '00000000-0000-0000-0000-000000000003',
    'NEEDS_REVISION', 'The argument lacks supporting evidence; please provide sources.', CURRENT_TIMESTAMP),
    ('88888888-8888-8888-8888-888888888884', '11111111-1111-1111-1111-111111111116', '00000000-0000-0000-0000-000000000003',
    'APPROVED', 'Excellent work! Approved without further changes.', CURRENT_TIMESTAMP);

INSERT INTO moderation.quality_metrics (id, name, description)
VALUES
    ('99999999-9999-9999-9999-999999999991', 'Clarity', 'Clarity of the content.'),
    ('99999999-9999-9999-9999-999999999992', 'Relevance', 'Relevance to the topic.');

INSERT INTO moderation.review_quality_metrics (review_id, quality_metric_id, score)
VALUES
    ('88888888-8888-8888-8888-888888888881', '99999999-9999-9999-9999-999999999991', 9), -- Clarity
    ('88888888-8888-8888-8888-888888888881', '99999999-9999-9999-9999-999999999992', 8), -- Relevance
    ('88888888-8888-8888-8888-888888888882', '99999999-9999-9999-9999-999999999991', 6), -- Clarity
    ('88888888-8888-8888-8888-888888888882', '99999999-9999-9999-9999-999999999992', 7), -- Relevance
    ('88888888-8888-8888-8888-888888888883', '99999999-9999-9999-9999-999999999991', 5), -- Clarity
    ('88888888-8888-8888-8888-888888888883', '99999999-9999-9999-9999-999999999992', 4), -- Relevance
    ('88888888-8888-8888-8888-888888888884', '99999999-9999-9999-9999-999999999991', 8), -- Clarity
    ('88888888-8888-8888-8888-888888888884', '99999999-9999-9999-9999-999999999992', 9); -- Relevance
