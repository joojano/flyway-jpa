CREATE TABLE tbUser (
    id varchar(255) NOT NULL DEFAULT gen_random_uuid(),
    username varchar(155) NOT NULL,
    password varchar(100) NOT NULL,
CONSTRAINT pk_tbUser PRIMARY KEY (id)
);