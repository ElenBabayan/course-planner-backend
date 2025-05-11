-- ---------- ApplicationDB schema ----------
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE IF NOT EXISTS Session (
                                       sessionID UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    messages  JSONB NOT NULL
    );
