CREATE TABLE IF NOT EXISTS "user_parking_lot" (
    id SERIAL PRIMARY KEY,
    user_id INT NOT NULL REFERENCES user_table(id),
    lot_id INT NOT NULL REFERENCES parking_lot(id)
);


COMMENT ON COLUMN user_parking_lot.id IS 'This is the ID of this junction table';
COMMENT ON COLUMN user_parking_lot.lot_id IS 'This is the ID of the parking lot';
COMMENT ON COLUMN user_parking_lot.user_id IS 'This is the ID of the user';