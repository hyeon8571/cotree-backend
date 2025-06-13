ALTER TABLE payment ADD price NUMBER NOT NULL CHECK (price >= 0);
