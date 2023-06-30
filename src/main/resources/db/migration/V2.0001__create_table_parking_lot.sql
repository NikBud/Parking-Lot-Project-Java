CREATE TABLE IF NOT EXISTS "parking_lot" (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL,
    begin_working_hour time(0) NOT NULL,
    end_working_hour time(0) NOT NULL,
    state VARCHAR(50) CHECK (state IN ('OPEN', 'TEMPORARILY_CLOSED')) NOT NULL
);

COMMENT ON COLUMN parking_lot.id IS 'This is the id of the parking lot so we can reference it in the parking_level table';
COMMENT ON COLUMN parking_lot.address IS 'This is the address of the parking lot';
COMMENT ON COLUMN parking_lot.name IS 'This is the name of the parking lot';
COMMENT ON COLUMN parking_lot.state IS 'This is the state of the parking lot, open or temporary_closed';