# 首先创建一个数据库(Tab补全)

drop database if exists todolist;
create database if not exists todolist;
use todolist;


# 创建管理用户数据表
drop table if exists adminuser;
create table if not exists adminuser
(
    uid      integer auto_increment comment '用户id',
    username varchar(20) not null comment '用户登录名',
    password varchar(32) not null comment '登录密码',
    add_time timestamp   not null default current_timestamp comment '创建时间',
    open_id varchar(40) comment '微信用户标识符',
    primary key (`uid`)
) comment '管理用户登录表';

commit;
# 插入管理用户测试数据
insert into adminuser (uid,username, password)
values (null,'wzf', 'c42fe3080e1e57b363c72b0dfd649c16');
insert into adminuser (uid, username, password)
values (null, 'admin', 'c42fe3080e1e57b363c72b0dfd649c16');

# 批量插入测试数据
DROP PROCEDURE
    IF
        EXISTS adminuser_insert;

DELIMITER $$
CREATE PROCEDURE adminuser_insert()
BEGIN
    DECLARE
        i INT DEFAULT 1;
    WHILE
            i < 20
        DO
            insert into adminuser (username, password) value (concat('user', i), 'c42fe3080e1e57b363c72b0dfd649c16');
            SET i = i + 1;

        END WHILE;
    COMMIT;

END $$
DELIMITER ;
CALL adminuser_insert();

commit;

# 记录事项表
drop table if exists listitem;
create table if not exists listitem
(
    li_id      integer auto_increment comment '事项id',
    li_uid     integer not null comment '用户id,外键',
    li_name varchar(50) not null comment '事项名称',
    li_finish boolean not null comment '是否完成',
    li_type varchar(10) not null comment '事项类型',
    li_info varchar(200) comment '详细信息',
    li_add_time timestamp   not null default current_timestamp comment '创建时间',
    li_finish_time  timestamp default null comment '完成时间',
    primary key (`li_id`),
    constraint `fk_li_to_admin_uid` foreign key info(`li_uid`) references adminuser(`uid`)
) comment '记录事项表';

#li_finish的触发器
CREATE TRIGGER trigger_li_finish_change before UPDATE ON listitem FOR EACH ROW
BEGIN
    IF
        ( new.li_finish = true )
    THEN
        SET new.li_finish_time = current_timestamp();
    END IF;
END;

commit;

# 批量插入测试数据
DROP PROCEDURE
    IF
        EXISTS listitem_insert;

DELIMITER $$
CREATE PROCEDURE listitem_insert()
BEGIN
    DECLARE
        i INT DEFAULT 1;
    DECLARE
        type VARCHAR(10) DEFAULT 'day';
    DECLARE
        finish boolean DEFAULT FALSE;
    WHILE
            i < 200
        DO
            set type :=(case
                            when (floor(rand()*1000))%3 = 1 then 'month'
                            when (floor(rand()*1000))%3 = 2 then 'week'
                            else 'day'
                end);
            set finish := (case
                               when (((floor(rand()*1000))-i)%2=0) then TRUE
                               else FALSE

                end);
            insert into listitem (li_uid,li_name,li_type,li_finish,li_add_time) value (rand()%21+1,concat('listitem_',i), type, finish,current_timestamp);
            SET i = i + 1;

        END WHILE;
    COMMIT;

END $$
DELIMITER ;
CALL listitem_insert();

commit;




# 创建备忘录表
drop table if exists memo;
create table if not exists memo
(
    m_id      integer auto_increment comment 'memo id',
    m_uid     integer not null comment '用户id,外键',
    m_info   varchar(200) default null comment '备忘录内容',
    m_add_time timestamp   not null default current_timestamp comment '创建时间',
    primary key (`m_id`),
    constraint `fk_m_to_admin_uid` foreign key info(`m_uid`) references adminuser(`uid`)
) comment '备忘录';

commit;



# 批量插入测试数据
DROP PROCEDURE
    IF
        EXISTS memo_insert;

DELIMITER $$
CREATE PROCEDURE memo_insert()
BEGIN
    DECLARE
        i INT DEFAULT 1;
    WHILE
            i < 20
        DO
            insert into memo (m_id, m_info,m_uid,m_add_time) value (i, concat('备忘录内容',i),2,current_timestamp);
            SET i = i + 1;

        END WHILE;
    COMMIT;

END $$
DELIMITER ;
CALL memo_insert();

commit;


ALTER TABLE listitem ADD li_important Integer default 0;