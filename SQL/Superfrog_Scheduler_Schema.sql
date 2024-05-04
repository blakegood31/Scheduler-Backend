create database Superfrog_Scheduler;

use Superfrog_Scheduler;

drop table request;
drop table customer;
drop table availability;
drop table spirit_calendar;
drop table student;
drop table user;


create table student(
    id VARCHAR(20) primary key not null,
    first_name VARCHAR(20) not null ,
    last_name VARCHAR(20) not null ,
    performance decimal(2, 1) not null,
    phone_number VARCHAR(10) not null,
    email VARCHAR(50) not null,
    active boolean
);

create table customer(
    id varchar(20) primary key not null ,
    fname varchar(25) not null ,
    lname varchar(25) not null ,
    email varchar(30) not null,
    phone varchar(10) not null
);

create table request(
    id VARCHAR(25) primary key not null,
    address varchar(50) not null ,
    description varchar(200) not null ,
    start_time DATETIME not null,
    end_time DATETIME not null,
    event_title varchar(20) not null ,
    status int not null ,
    sid VARCHAR(20) ,
    cid VARCHAR(20) not null ,
    special_instructions varchar(200),
    other_orgs varchar(100),
    event_type int,
    miles_from_tcu DOUBLE,
    foreign key (sid) references student (id),
    foreign key (cid) references customer (id)
);

create table spirit_calendar(
    event_id int primary key ,
    start_time DATETIME not null ,
    end_time DATETIME not null ,
    superfrog_assigned bool not null ,
    superfrog_id VARCHAR(25),
    foreign key (superfrog_id) references student(id)
);

create table availability(
    entry_id VARCHAR(25) primary key ,
    superfrog_id VARCHAR(25) not null,
    start_available DATETIME,
    end_available DATETIME,
    is_available bool not null,
    foreign key (superfrog_id) references student(id)
);

create table user(
    id integer primary key ,
    username varchar(25) not null ,
    password varchar(100) not null ,
    enabled bool,
    roles VARCHAR(50) not null
);

insert into student values('1001100011', 'Super', 'Frog', 4.3, '7618675309', 'super.frog@tcu.edu', true);
insert into student values('5315315315', 'Dr', 'Wei', 5.0, '1234567788', 'b.wei@tcu.edu', true);
insert into availability values('1234567890', '1001100011', '2024-04-18 12:30:00', '2024-04-18 17:00:00', true);
insert into customer values('0987654321', 'Victor', 'boschini', 'v.bo@tcu.edu', '1234567890');
insert into request values('3131313131', '123 TCU St, Ft. Worth, TX, 76110', 'A birthday party for superfrog', '2024-04-18 12:30:00', '2024-04-18 14:00:00', 'Superfrog Birthday', 1, '1001100011', '0987654321', 'Dont tell anyone about the party. Its a surprise!', 'TCU Athletics, CSE, Neeley', 0, 15.5);
insert into request values('4242424242', '123 TCU St, Ft. Worth, TX, 76110', 'A neeley fundraising event', '2024-04-18 12:30:00', '2024-04-18 14:00:00', 'Neeley Fundraiser', 2, '1001100011', '0987654321', 'TCU doesnt give neeley anything. We need more money', 'JP Morgan, Goldman Sachs', 1, 5.2);
insert into request values('5353535353', '123 TCU St, Ft. Worth, TX, 76110', 'Frogs by 90', '2024-04-18 12:30:00', '2024-04-18 14:00:00', 'Football Game', 3, '5315315315', '0987654321', 'Superfrog is the only one that can save us now', 'TCU Athletics', 2, 25.7);
insert into request values('9898989898', '456 TCU St, Ft. Worth, TX, 76110', 'A really nice description', '2024-04-18 12:30:00', '2024-04-18 14:00:00', 'A Fun Event', 0, null, '0987654321', 'really special instructions', 'TCU Frogs', 0, 10.0);
insert into user values(1, 'sf123', 'password', true, 'superfrog');
insert into user values(2, 'john', '12345', true, 'admin');
insert into user values(3, 'cust', '123', true, 'customer');
insert into user values(4, 'super.frog@tcu.edu', 'password', true, 'superfrog');
insert into user values(5, 'b.wei@tcu.edu', 'password', true, 'superfrog');

select * from request;
select * from student;

