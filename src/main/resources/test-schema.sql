DROP TABLE IF EXISTS user CASCADE;
DROP TABLE IF EXISTS to_do  CASCADE;


CREATE TABLE user (
	user_id integer generated by default as identity,
	first_name varchar(255) not null,
	last_name varchar(255) not null,
	email_address varchar(255) not null,
	user_name varchar(255) not null,
	password varchar(255) not null,
	primary key (user_id)
);
CREATE TABLE to_do (
	todo_id integer generated by default as identity,
	user_id integer not null,
    task varchar(255),
    info text,
    due_date date,
    start_date date,
    date_complete date,
	primary key (todo_id)
);
ALTER TABLE user add constraint UK_8qrhl5wlohdjoko9nrorkdjkp unique (user_name);
ALTER TABLE user add constraint UK_8qrhl5wlohdjoko9nrorkdjkp unique (email_address);
ALTER TABLE to_do add constraint FKdtss7ab4sx4oagfqyacono43a foreign key (user_id) references pond on delete cascade;