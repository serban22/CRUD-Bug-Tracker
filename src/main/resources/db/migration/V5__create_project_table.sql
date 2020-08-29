use bugtrackingsoftware;
create table project (
                         project_id int unsigned auto_increment primary key,
                         project_name varchar(60),
                         project_shortname varchar(12),
                         project_description varchar(250),
                         status_id int unsigned,
                         foreign key (status_id) references status(status_id)
)