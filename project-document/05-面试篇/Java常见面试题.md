1.如果定位&避免死锁（什么是死锁&产生死锁的原因）
互斥：共享资源只能被一个线程占用 --互斥锁
占有且等待：线程当前占有至少一个资源并还想请求其它线程持有的其他资源就会造成等待。 --等待对方释放资源
不可抢占：资源只能由持有它的线程资源释放，其它线程不可强行占有该资源。--无法释放对方资源
循环等待：线程A等待线程B占有的资源，线程B等待线程A占有的资源，就是循环等待 -- 两个线程互相等待

破坏其中一个就行；

定位：
jps -- 查进程
jstack -- 

MySQL是怎么解决死锁的？
1.设置锁等待超时时间
innodb_lock_wait_timeout
2.死锁检测
innodb_deadlock_detect

-----
2.【MySQL】事务与隔离级别
事务只是一个改变，是一些操作的集合;是一个程序的执行单元；
通过某些手段，尽可能让这个执行单元满足这四个特性，那么我们就可以称为它是一个事务，或者说一个完美的事务；

事务的四大特性：ACID
原子性(Atomicity)：当前事务的操作要么同时成功，要么同时失败，原子性是由undo log日志来保证;
    满足原子操作单元，对数据的操作，要么全部执行，要么全部失败；
一致性(Consistency)：使用事物的最终目的，由业务代码正确逻辑保证;
    事务开始和完成，数据必须要保持一致；
隔离性(Isolation)：在事务并发执行时，它们内部的操作不能互相干扰；
    事务之前是相互独立的，中间状态对外不可见；
持久性(Durability)：数据的修改是永久的；

InnoDB引擎中，定义了四种隔离级别供我们使用，级别越高事务隔离性越好，但性能就越低，而隔离性是由MySQL的各种锁以及MVCC机制来实现的；
    read uncommit (读到未提交的数据):有脏读问题 -- 后台没有默认加读锁
    read commit (读到已提交的，读的结果跟随更新而变化):有不可重复读问题 (--orcal 默认) (高并发业务)
    repeatable read (可重复读，第一次读形成快照时刻，每次都跟第一次一样)：有幻读问题 (--mysql 默认) (ERP 报表业务)
    serializable(串读，串行化执行关联一个表的sql)：上面问题全部解决；
隔离级别：ru 、rc、rr、s

读锁(共享锁、s锁)：select ... lock in share mode;
写锁(排他锁、X锁): select ... for update;
写锁是排他的，会阻塞其它的写锁和读锁，update、detete、insert默认都会加写锁; （读写和写锁是互斥的）

set tx_isolation = 'read-uncommitted';
set tx_isolation = 'read-committed';
set tx_isolation = 'repeatable-read';
set tx_isolation = 'serializable';

session one:
    set tx_isolation = 'read-uncommited';

MVCC机制跟copy on write机制超不多