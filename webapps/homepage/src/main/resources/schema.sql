CREATE TABLE IF NOT EXISTS person (
    id IDENTITY
    ,name NVARCHAR UNIQUE NOT NULL
    ,encrypted_password CHAR(60) NOT NULL
);