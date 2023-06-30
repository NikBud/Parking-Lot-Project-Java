CREATE TABLE IF NOT EXISTS "parking_space" (
    id SERIAL PRIMARY KEY,
    number INT NOT NULL,
    type VARCHAR(255) CHECK
        (type IN('REGULAR', 'ACCESSIBLE', 'FAMILY')) NOT NULL DEFAULT 'REGULAR',
    state VARCHAR(255) CHECK
        (state IN('AVAILABLE', 'OCCUPIED', 'TEMPORARILY_CLOSED')) NOT NULL DEFAULT 'AVAILABLE',
    parking_level_id INT REFERENCES parking_level(id),
    user_id INT REFERENCES user_table(id) UNIQUE
);

COMMENT ON COLUMN parking_space.id IS 'This is the ID of the parking space';
COMMENT ON COLUMN parking_space.number IS 'This is the number of the parking space on this floor (001,002,...)';
COMMENT ON COLUMN parking_space.type IS 'This is the type of the parking space, regular, accessible or family';
COMMENT ON COLUMN parking_space.state IS 'This is the state of the parking space, available, occupied or temporary closed';
COMMENT ON COLUMN parking_space.parking_level_id IS 'This is the level from the parking_level table';
COMMENT ON COLUMN parking_space.user_id IS 'This is the user id of the user that occupied the parking space';