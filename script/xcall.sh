#!/bin/bash
for i in node01 node02 node03
do
	echo " <<<<<<<<<<<<<<<<<<<< $i $1 <<<<<<<<<<<<<<<<<<<<<"
	ssh $i "source /etc/profile;$*"
done
