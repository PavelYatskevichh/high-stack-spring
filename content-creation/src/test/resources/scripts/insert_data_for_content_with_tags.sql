INSERT INTO content_creation.content_items (id, title, description, body, author_id, status, created_at)
VALUES
    ('11111111-dddd-1111-1111-111111111111', 'How to Start Your Own Business from Scratch',
    'This article provides a step-by-step guide for aspiring entrepreneurs looking to start their own business.',
    'Starting a business involves planning, securing funding, and executing your vision. This article walks you through these critical steps.',
    '00000000-dddd-0000-0000-000000000001', 'DRAFT', CURRENT_TIMESTAMP),
    ('11111111-dddd-1111-1111-111111111112', 'The Role of Big Data in Modern Marketing',
    'Learn how big data analytics is transforming marketing strategies and consumer insights.',
    'Big data helps companies predict trends, understand customer behavior, and make data-driven decisions. Learn how this is achieved.',
    '00000000-dddd-0000-0000-000000000001', 'SUBMITTED', CURRENT_TIMESTAMP);

INSERT INTO content_creation.revisions (id, content_item_id, revision_number, description, created_at)
VALUES
    ('22222222-dddd-2222-2222-222222222221', '11111111-dddd-1111-1111-111111111111', 1,
    'Revised the introduction to provide a more engaging hook for readers.', CURRENT_TIMESTAMP),
    ('22222222-dddd-2222-2222-222222222222', '11111111-dddd-1111-1111-111111111111', 2,
    'Adjusted formatting and removed unnecessary jargon for better readability.', CURRENT_TIMESTAMP);

INSERT INTO content_creation.tags (id, name)
VALUES
    ('33333333-dddd-3333-3333-333333333331', 'Technology'),
    ('33333333-dddd-3333-3333-333333333332', 'Education'),
    ('33333333-dddd-3333-3333-333333333333', 'Science');

INSERT INTO content_creation.content_item_tags (content_item_id, tag_id)
VALUES
    ('11111111-dddd-1111-1111-111111111111', '33333333-dddd-3333-3333-333333333331'),
    ('11111111-dddd-1111-1111-111111111111', '33333333-dddd-3333-3333-333333333332'),
    ('11111111-dddd-1111-1111-111111111112', '33333333-dddd-3333-3333-333333333333');
