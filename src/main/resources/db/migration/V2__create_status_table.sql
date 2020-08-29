use bugtrackingsoftware;
create table status (
                        status_id int unsigned auto_increment primary key,
                        status_name varchar(100) unique
);