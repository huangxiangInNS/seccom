#!/bin/bash
cd target
#TODO判断uc.war是否存在，存在则删除
mv uc*.war uc.war
pwd
echo "Start copying..."
expect ../copyWarToDest.expect
echo "Finish copying."

cd ..
pwd
echo "Tail catalina log..."
expect tailCatalinaLog.expect
