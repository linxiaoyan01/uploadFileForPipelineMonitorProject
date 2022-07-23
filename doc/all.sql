create table local_file_path
(
    path varchar(100) not null
)
    comment '本地程序的文件路径设置';

create unique index local_file_path_path_uindex
    on local_file_path (path);

