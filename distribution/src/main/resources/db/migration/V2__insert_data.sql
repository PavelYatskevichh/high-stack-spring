INSERT INTO distribution.publications (id, content_item_id, moderator_id, title, body, published_at)
VALUES
    ('44444444-4444-4444-4444-444444444441', '11111111-1111-1111-1111-111111111112', '00000000-0000-0000-0000-000000000002', 'The Future of Renewable Energy',
     'Renewable energy sources, such as wind, solar, and hydro, are reshaping the global energy landscape. Learn how these technologies are becoming more efficient and accessible.', CURRENT_TIMESTAMP),
    ('44444444-4444-4444-4444-444444444442', '11111111-1111-1111-1111-111111111113', '00000000-0000-0000-0000-000000000002', '5 Strategies for Effective Team Management',
     'Managing teams effectively requires strong communication, clear goal-setting, and fostering a positive work culture. This article outlines five key strategies to achieve these.', CURRENT_TIMESTAMP),
    ('44444444-4444-4444-4444-444444444443', '11111111-1111-1111-1111-111111111114', '00000000-0000-0000-0000-000000000002', 'The Impact of AI on Healthcare',
    'AI has revolutionized healthcare through predictive analytics, personalized treatments, and improved diagnostic accuracy. Explore the latest advancements in this field.', CURRENT_TIMESTAMP);

INSERT INTO distribution.audience (id, name, email, created_at)
VALUES
    ('55555555-5555-5555-5555-555555555551', 'David Audience', 'david.audience@example.com', CURRENT_TIMESTAMP),
    ('55555555-5555-5555-5555-555555555552', 'Emma Viewer', 'emma.viewer@example.com', CURRENT_TIMESTAMP),
    ('55555555-5555-5555-5555-555555555553', 'Fiona Audience', 'fiona.audience@example.com', CURRENT_TIMESTAMP),
    ('55555555-5555-5555-5555-555555555554', 'George Viewer', 'george.viewer@example.com', CURRENT_TIMESTAMP),
    ('55555555-5555-5555-5555-555555555555', 'Hannah Reader', 'hannah.reader@example.com', CURRENT_TIMESTAMP);

INSERT INTO distribution.views (id, publication_id, audience_id, viewed_at)
VALUES
    ('66666666-6666-6666-6666-666666666661', '44444444-4444-4444-4444-444444444441', '55555555-5555-5555-5555-555555555551', CURRENT_TIMESTAMP),
    ('66666666-6666-6666-6666-666666666662', '44444444-4444-4444-4444-444444444441', '55555555-5555-5555-5555-555555555552', CURRENT_TIMESTAMP),
    ('66666666-6666-6666-6666-666666666663', '44444444-4444-4444-4444-444444444442', '55555555-5555-5555-5555-555555555553', CURRENT_TIMESTAMP),
    ('66666666-6666-6666-6666-666666666664', '44444444-4444-4444-4444-444444444442', '55555555-5555-5555-5555-555555555554', CURRENT_TIMESTAMP),
    ('66666666-6666-6666-6666-666666666665', '44444444-4444-4444-4444-444444444443', '55555555-5555-5555-5555-555555555555', CURRENT_TIMESTAMP);

INSERT INTO distribution.feedback (id, publication_id, audience_id, rating, comment, submitted_at)
VALUES
    ('77777777-7777-7777-7777-777777777771', '44444444-4444-4444-4444-444444444441', '55555555-5555-5555-5555-555555555551', 4, 'Great content!', CURRENT_TIMESTAMP),
    ('77777777-7777-7777-7777-777777777772', '44444444-4444-4444-4444-444444444441', '55555555-5555-5555-5555-555555555552', 5, 'Very informative!', CURRENT_TIMESTAMP),
    ('77777777-7777-7777-7777-777777777773', '44444444-4444-4444-4444-444444444442', '55555555-5555-5555-5555-555555555553', 3, 'Good but could be better.', CURRENT_TIMESTAMP),
    ('77777777-7777-7777-7777-777777777774', '44444444-4444-4444-4444-444444444443', '55555555-5555-5555-5555-555555555554', 4, 'Interesting insights.', CURRENT_TIMESTAMP);
