pragma foreign_keys = ON; 
begin transaction;

insert into customer (cid, name, phone) values ("AB123456", "George", "111222333");
insert into customer (cid, name, phone) values ("QW765234", "Manos", "555666777");
insert into customer (cid, name, phone) values ("XZ154176", "Babis", "888999000");

insert into roomtype (name, price) values ("Single", 40);
insert into roomtype (name, price) values ("Double", 50);

insert into room (rid, type) values ("101", 1);
insert into room (rid, type) values ("102", 2);
insert into room (rid, type) values ("201", 1);
insert into room (rid, type) values ("202", 2);

insert into reservation (startdate, enddate, customerid, roomid) values ("2017/1/1", "2017/1/2", "AB123456", "101");
insert into reservation (startdate, enddate, customerid, roomid) values ("2017/1/3", "2017/1/4", "QW765234", "102");

commit;
