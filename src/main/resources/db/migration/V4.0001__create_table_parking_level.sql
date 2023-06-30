CREATE TABLE IF NOT EXISTS "parking_level" (
    id SERIAL PRIMARY KEY,
    floor VARCHAR(1) NOT NULL,
    number_of_spaces INT NOT NULL,
    lot_id INT NOT NULL REFERENCES parking_lot(id)
);

COMMENT ON COLUMN parking_level.id IS 'This is the ID of the parking level so that the parking space can reference it';
COMMENT ON COLUMN parking_level.floor IS 'This is the floor on which the parking level is in the parking lot';
COMMENT ON COLUMN parking_level.number_of_spaces IS 'This is the number of parking spaces on a particular level';
COMMENT ON COLUMN parking_level.lot_id IS 'This is the ID of the parking lot for which the level is';
