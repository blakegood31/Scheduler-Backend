use Superfrog_Scheduler;

drop table request;
drop table customer;
drop table availability;
drop table spirit_calendar;
drop table student;
drop table director;

create table director(
    id int primary key not null,
    name varchar(25) not null
);

create table student(
    id VARCHAR(20) primary key not null,
    name varchar(20) not null,
    performance decimal(2, 1) not null
);

create table customer(
    id int primary key not null ,
    name varchar(25) not null ,
    email varchar(30) not null,
    phone int(9) not null
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
    cid int not null ,
    special_instructions varchar(200),
    other_orgs varchar(100),
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

insert into director values(1, 'John Doe');
insert into student values('1001100011', 'Super Frog', 4.3);
insert into student values('5315315315', 'Dr. Wei', 5.0);
insert into availability values('1234567890', '1001100011', '2024-04-18 12:30:00', '2024-04-18 17:00:00', true);
insert into customer values('0987654321', 'V Bo', 'v.bo@tcu.edu', 1234567890);
insert into request values('3131313131', '123 TCU St, Ft. Worth, TX, 76110', 'A birthday party for superfrog', '2024-04-18 12:30:00', '2024-04-18 14:00:00', 'Superfrog Birthday', 0, '1001100011', 0987654321, 'Dont tell anyone about the party. Its a surprise!', 'TCU Athletics, CSE, Neeley');

