#!/bin/bash
if [ ! $1 ]
then echo "please input [start|stop]"
exit 1
fi
#start hadoop
echo " ----------- $1 dfs ------------ "
ssh root@node01 "source /etc/profile;${HADOOP_HOME}/sbin/${1}-dfs.sh"
echo " ----------- $1 yarn ---------- "
ssh root@node01 "source /etc/profile;${HADOOP_HOME}/sbin/${1}-yarn.sh"
sleep 1s

echo " ----------- $1 zookeeper ----------"
#start zookeeper
for (( i=1; i<=3; i++ ))
do
# << E0F 只是一个标识，可以换做其他任意字符，多行复杂脚本使用

  echo "node0$i zk ${1} ..."
  ssh root@node0$i "source /etc/profile; zkServer.sh ${1}"
  echo "node0$i ${1} 完成."
done

echo " ----------- $1 kafka ------------"
# start kafka
if [ ${1} == 'stop' ]
then
for (( i=1; i<=3; i++ ))
do
  echo "node0$i kafka ${1} ..."
  ssh root@node0$i "source /etc/profile;${KAFKA_HOME}/bin/kafka-server-stop.sh"
  if [ `ps -ef|grep Kafka | wc -l` -gt 1 ]; then
  ssh root@node0$i `ps -ef | grep Kafka | grep -v grep | awk '{print $2}' | xargs kill -9`
  fi
  echo "node0$i ${1} 完成."
done
else
for (( i=1; i<=3; i++ ))
do
  echo "node0$i kafka ${1} ..."
  ssh root@node0$i "source /etc/profile;${KAFKA_HOME}/bin/kafka-server-${1}.sh -daemon /export/servers/kafka/config/server.properties"  
  echo "node0$i ${1} 完成."
done
sleep 1s
fi
# start flink
# /export/servers/flink/bin/${1}-cluster.sh
# start dolphinscheduler
# /opt/soft/dolphinscheduler/bin/start-all.sh
# systemctl restart nginx
