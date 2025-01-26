create table city (
id SERIAL PRIMARY KEY,
name text,
people int,
metro Boolean);


create table place (
id SERIAL PRIMARY KEY,
name VARCHAR(30),
datedo date,
text text,
type text,
cityid int references city(id)
);

create table excursion (
id SERIAL PRIMARY KEY,
name VARCHAR(50));


create table svyaz (
exid integer references excursion(id),
placeid integer references place(id),
PRIMARY KEY (exid,placeid)
       );
