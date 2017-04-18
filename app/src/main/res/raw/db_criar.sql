CREATE TABLE carro (
 id integer NOT NULL PRIMARY KEY,
 nome varchar(255) NOT NULL,
 marca varchar(255) NOT NULL,
 cor varchar(255) NOT NULL,
 ano varchar(255) NOT NULL,
 valor varchar(255) NOT NULL
);
CREATE TABLE usuario (
 id integer NOT NULL PRIMARY KEY AUTOINCREMENT,
 user_name varchar(255) NOT NULL,
 password varchar(255) NOT NULL
);
