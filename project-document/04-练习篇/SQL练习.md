SQL练习常见题目：
https://blog.csdn.net/weixin_45366499/article/details/116355430

（1）查询每个区域的用户数
（2）查询每个区域的男女用户数
（3）查询姓张的用户数
（4）筛选出id3～id5的用户
（5）筛选出绩效不达标的员工
（6）筛选出姓张的且绩效不达标的员工
（7）查询获得销售冠军超过两次的人
（8）查询某部门一年的月销售额最高涨幅
（9）查询每个季度绩效得分大于70分的员工
（10）删除重复值
（11）行列互换
（12）多列比较
（13）对成绩进行分组
（14）周累计数据获取
（15）周环比数据获取
（16）查询获奖员工信息
（17）计算用户留存情况
（18）筛选最受欢迎的课程
（19）筛选出每个年级最受欢迎的三门课程
（20）求累积和
（21）获取新增用户数
（22）获取用户首次购买时间
（23）同时获取用户和订单数据
（24）随机抽样
（25）获取沉默用户数
（26）获取新用户的订单数
（27）获取借款到期名单
（28）获取即将到期的借款信息
（29）获取历史逾期借款信息
（30）获取部门工资最高的员工
（补）求连续登陆三天的用户
（补）某互联网公司面试题



##### 01. 计算用户留存情况

>+ 需求：我们想看用户的次日留存数、三日留存数、七日留存数（只要用户首次登录以后再登录就算留存下来了），该怎么实现呢？
>+ 解题思路：（有两种统计逻辑，结果不一样）
>	+ 210按3
>	+ 3                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             NJJ6XXX照用户时间求出七日留存，首先按uid分组，求出每个uid的第一次登陆时间和最后一次登陆时间，算出中间间隔的时间，如果间隔为1就是次日留存，间隔为3就是3日留存，间隔为7就是7日留存，以此类推分别求出他们的数量。
>	+ 按照当日时间求出七日留存，如果用户登陆的时间正好等于当前日期前一天的日期，则去重统计uid数量即为次日留存数，以此类推分别求出三日留存、七日留存。

##### 测试数据

```sql
drop table t1;
cache table t1 as
(
select 1 uid, '2021/04/21 06:00'  login_time union all
select 1 uid, '2021/04/24 10:00' login_time union all
select 1 uid, '2021/04/25 19:00' login_time union all
select 2 uid, '2021/04/22 10:00' login_time union all
select 2 uid, '2021/04/28 09:00'  login_time union all
select 2 uid, '2021/04/29 14:00' login_time union all
select 3 uid, '2021/04/27 08:00'  login_time union all
select 3 uid, '2021/04/28 10:00' login_time
)
;
```

#### SQL

+ 样例

```sql
select 
  apptypeid,
  active_users, --18号用户数
  day2_active_users, 
  concat(day2_active_users/active_users*100,'%') as day2_active_user_rate, --18号用户数的次日留存
  day3_active_users,
  concat(day3_active_users/active_users*100,'%') as day3_active_user_rate, --18号用户数的3日留存
  day4_active_users,
  concat(day4_active_users/active_users*100,'%') as day4_active_user_rate, --18号用户数的4日留存
  day5_active_users,
  concat(day5_active_users/active_users*100,'%') as day5_active_user_rate, --18号用户数的5日留存
  day6_active_users,
  concat(day6_active_users/active_users*100,'%') as day6_active_user_rate, --18号用户数的6日留存
  day7_active_users,
  concat(day7_active_users/active_users*100,'%') as day7_active_user_rate, --18号用户数的7日留存
  dt
from 
(select 
  t1.apptypeid,
  count(t1.uid) as active_users,
  count(case when datediff(t2.cu_dt,t1.cu_dt)=1 then t2.uid end) as day2_active_users,
  count(case when datediff(t2.cu_dt,t1.cu_dt)=2 then t2.uid end) as day3_active_users,
  count(case when datediff(t2.cu_dt,t1.cu_dt)=3 then t2.uid end) as day4_active_users,
  count(case when datediff(t2.cu_dt,t1.cu_dt)=4 then t2.uid end) as day5_active_users,
  count(case when datediff(t2.cu_dt,t1.cu_dt)=5 then t2.uid end) as day6_active_users,
  count(case when datediff(t2.cu_dt,t1.cu_dt)=6 then t2.uid end) as day7_active_users,
  t1.dt
from 
(select
  apptypeid,
  uid,
  dt,
  from_unixtime(unix_timestamp(dt,'yyyyMMdd'),'yyyy-MM-dd') as cu_dt
from ods_center.ods_app_open
where dt='20211118'
group by apptypeid,uid,dt,from_unixtime(unix_timestamp(dt,'yyyyMMdd'),'yyyy-MM-dd')) t1

left join
(select
  apptypeid,
  uid,
  dt,
  from_unixtime(unix_timestamp(dt,'yyyyMMdd'),'yyyy-MM-dd') as cu_dt
from ods_center.ods_app_open
where dt>'20211118' and dt<='2021124'
group by apptypeid,uid,dt,from_unixtime(unix_timestamp(dt,'yyyyMMdd'),'yyyy-MM-dd')) t2
on t1.apptypeid=t2.apptypeid and t1.uid=t2.uid
group by t1.apptypeid,t1.dt) t3
```

+ SQL

```sql
with t2 as (
    select
        uid
         ,from_unixtime(unix_timestamp(login_time, 'yyyy/MM/dd HH:mm'), 'yyyy-MM-dd') as login_time
         ,min(from_unixtime(unix_timestamp(login_time, 'yyyy/MM/dd HH:mm'), 'yyyy-MM-dd')) over (partition by uid) as min_login_time
    from t1
),
t3 as (
    select
        uid
        ,count(case when datediff(login_time, min_login_time)=1 then uid end) day2_active_users
        ,count(case when datediff(login_time, min_login_time)=2 then uid end) day3_active_users
        ,count(case when datediff(login_time, min_login_time)=3 then uid end) day4_active_users
        ,count(case when datediff(login_time, min_login_time)=6 then uid end) day7_active_users
    from t2
    group by uid
)
select
    count(uid) user_count
    ,sum(day2_active_users) day2_active_users
    ,concat(cast((sum(day2_active_users)/count(uid))*100 as decimal(10,2)),  '%') as day2_active_users_rate
    ,sum(day3_active_users) day3_active_users
    ,concat(cast((sum(day3_active_users)/count(uid))*100 as decimal(10,2)),  '%') as day3_active_users_rate
    ,sum(day4_active_users) day4_active_users
    ,concat(cast((sum(day4_active_users)/count(uid))*100 as decimal(10,2)),  '%') as day4_active_users_rate
    ,sum(day7_active_users) day7_active_users
    ,concat(cast((sum(day7_active_users)/count(uid))*100 as decimal(10,2)),  '%') as day7_active_users_rate
from t3
;
```

