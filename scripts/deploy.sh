#!/bin/bash

# .env 파일 읽어서 환경 변수로 설정
export $(cat /path/to/your/project/.env | xargs)

BUILD_JAR=$(ls /home/ec2-user/action/build/libs/*.jar)
JAR_NAME=$(basename $BUILD_JAR)
echo "> build 파일명: $JAR_NAME" >> /home/ec2-user/action/deploy.log

echo "> build 파일 복사" >> /home/ec2-user/action/deploy.log
DEPLOY_PATH=/home/ec2-user/action/
cp $BUILD_JAR $DEPLOY_PATH

echo "> 현재 실행중인 애플리케이션 pid 확인" >> /home/ec2-user/action/deploy.log
CURRENT_PID=$(pgrep -f $JAR_NAME)

if [ -z $CURRENT_PID ]
then
  echo "> 현재 구동중인 애플리케이션이 없으므로 종료하지 않습니다." >> /home/ec2-user/action/deploy.log
else
  echo "> kill -15 $CURRENT_PID"
  kill -15 $CURRENT_PID
  sleep 5
fi

# JAR 파일 이름에 -plain이 없는 경우, 여기에서 직접 수정
# 예: PLAIN_JAR_NAME=$(echo $JAR_NAME | sed 's/-plain//')
PLAIN_JAR_NAME=$JAR_NAME

DEPLOY_JAR=$DEPLOY_PATH$PLAIN_JAR_NAME
echo "> DEPLOY_JAR 배포"    >> /home/ec2-user/action/deploy.log
nohup java -jar $DEPLOY_JAR >> /home/ec2-user/deploy.log 2>/home/ec2-user/action/deploy_err.log &
