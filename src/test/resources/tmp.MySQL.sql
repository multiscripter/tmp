drop table if exists file_upload;
create table file_upload (
    id varchar(50) not null primary key,
    file_name varchar(50) not null,
    company_id varchar(50) not null,
    user_id varchar(50) not null,
    uploaded_date datetime
);
insert into file_upload (id, file_name, company_id, user_id, uploaded_date)
values
("e04a00c9-1b76-47e3-834d-ecb6ef9de0a3", "dump-1.csv", "1", "user-1", "2019-01-01 01:01:01"),
("3780c38a-68c9-4b0a-90da-3cbf4eac65ef", "dump-2.csv", "1", "user-1", "2019-02-02 02:02:02"),
("93dfdc51-e374-4d97-a040-49ae0ac9ff20", "dump-3.csv", "1", "user-1", "2019-03-03 03:03:03"),
("499d07d6-143d-477a-8028-218749b3d8bf", "null-4.csv", "1", "user-1", null),
("8f937ce8-6c83-4395-993b-8fc89fbea83f", "list-1.csv", "2", "user-1", "2019-03-03 03:03:03"),
("d9927a62-ef3c-47a0-a099-48b585f71ae4", "back-1.csv", "1", "user-2", "2019-03-03 03:03:03");