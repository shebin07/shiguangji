
use rk;


# 创建管理用户信息表
drop table if exists userinfo;
create table if not exists userinfo
(
    uiid      integer auto_increment comment '用户信息id',
    fuid      integer not null comment '用户id,外键',
    uiphone varchar(11) not null comment '手机号码',
    uiaddress varchar(120) not null comment '地址',
    add_time timestamp   not null default current_timestamp comment '创建时间',
    up_time  timestamp   not null default current_timestamp on update current_timestamp comment '修改时间',
    primary key (`uiid`),
    constraint `fk_ui_to_admin_uid` foreign key info(`fuid`) references adminuser(`uid`)
) comment '用户信息表';

commit;

# 批量插入测试数据
DROP PROCEDURE
    IF
        EXISTS userinfo_insert;

DELIMITER $$
CREATE PROCEDURE userinfo_insert()
BEGIN
    DECLARE
        i INT DEFAULT 1;
    WHILE
            i < 100
        DO
            insert into userinfo (uiphone,uiaddress,fuid) value (concat('13', FlOOR(RAND() * 899999999 + 100000000)), '大黑山程序员养殖基地',i);
            SET i = i + 1;

        END WHILE;
    COMMIT;

END $$
DELIMITER ;
CALL userinfo_insert();

commit;