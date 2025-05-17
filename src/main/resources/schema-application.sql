CREATE TABLE IF NOT EXISTS session
(
    session_id UUID PRIMARY KEY,
    messages   JSONB NOT NULL
);
