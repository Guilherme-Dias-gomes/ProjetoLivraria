CREATE TABLE IF NOT EXISTS T_PD_USER(
    id_user bigint auto_increment,
    nm_name varchar(255),
    ds_email varchar(255),
    password varchar(20),
    primary key (id_user)
);