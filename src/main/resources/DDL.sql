create table Category
(
    id   bigint primary key auto_increment,
    name varchar(255) not null
);

create table Income
(
    id       bigint primary key auto_increment,
    amount   decimal(9, 2) not null,
    add_date date          not null,
    comment  varchar(255)
);

create table Expense
(
    id          bigint primary key auto_increment,
    amount      decimal(9, 2) not null,
    add_date    date          not null,
    category_id bigint        not null,
    comment     varchar(255),
    FOREIGN KEY (category_id) REFERENCES Category (id)
);