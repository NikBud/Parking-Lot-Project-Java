CREATE TABLE IF NOT EXISTS "working_days"
(
    id SERIAL PRIMARY KEY,
    name_of_day varchar(20),
    lot_id int NOT NULL REFERENCES parking_lot(id)
);