cd ..
cd ..
set "home=%cd%"

cd %home%
cd publicdata
call mvn clean

cd %home%
cd publicservice
call mvn clean

cd %home%
cd file
call mvn clean

cd %home%
cd personalcenter/personalcenter-api
call mvn clean

cd %home%
cd pay/pay-api
call mvn clean

cd %home%
cd my/my-api
call mvn clean

cd %home%
cd organization/organization-api
call mvn clean

cd %home%
cd sellercenter/sellercenter-api
call mvn clean

cd %home%
cd goods/goods-api
call mvn clean

cd %home%
cd marketing/marketing-api
call mvn clean

cd %home%
cd brokerage/brokerage-api
call mvn clean

cd %home%
cd orderform/orderform-api
call mvn clean

cd %home%
cd seckill/seckill-api
call mvn clean

cd %home%
cd my/my-api
call mvn clean

cd %home%
cd publicdata
call mvn clean

cd %home%
cd publicservice
call mvn clean

cd %home%
cd personalcenter
call mvn clean

cd %home%
cd pay
call mvn clean

cd %home%
cd my
call mvn clean

cd %home%
cd organization
call mvn clean

cd %home%
cd sellercenter
call mvn clean

cd %home%
cd goods
call mvn clean

cd %home%
cd brokerage
call mvn clean

cd %home%
cd warehouse
call mvn clean

cd %home%
cd marketing
call mvn clean

cd %home%
cd orderform
call mvn clean

cd %home%
cd seckill
call mvn clean

cd %home%
cd mall
call mvn clean

cd %home%
cd forest
call mvn clean

pause