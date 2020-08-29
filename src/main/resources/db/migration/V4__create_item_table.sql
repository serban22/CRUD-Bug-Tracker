use bugtrackingsoftware;
create table item (
                      item_id int unsigned auto_increment primary key,
                      item_name varchar(100) unique,
                      status_id int unsigned,
                      type_id int unsigned,
                      foreign key (status_id) references status(status_id),
                      foreign key (type_id) references type(type_id)
)