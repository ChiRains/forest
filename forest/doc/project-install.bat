cd ..
cd ..
set "home=%cd%"

cd %home%
cd publicdata
call mvn install

cd %home%
cd publicservice
call mvn install

cd %home%
cd file
call mvn install

cd %home%
cd personalcenter/personalcenter-api
call mvn install

cd %home%
cd pay/pay-api
call mvn install

cd %home%
cd my/my-api
call mvn install

cd %home%
cd sellercenter/sellercenter-api
call mvn install

cd %home%
cd good/good-api
call mvn install

cd %home%
cd marketing/marketing-api
call mvn install

cd %home%
cd brokerage/brokerage-api
call mvn install

cd %home%
cd orderform/orderform-api
call mvn install

cd %home%
cd seckill/seckill-api
call mvn install

cd %home%
cd my/my-api
call mvn install

cd %home%
cd personalcenter
call mvn install

cd %home%
cd pay
call mvn install

cd %home%
cd my
call mvn install

cd %home%
cd sellercenter
call mvn install

cd %home%
cd good
call mvn install

cd %home%
cd brokerage
call mvn install

cd %home%
cd warehouse
call mvn install

cd %home%
cd marketing
call mvn install

cd %home%
cd orderform
call mvn install

cd %home%
cd seckill
call mvn install

cd %home%
cd mall
call mvn install

cd %home%
cd forest
call mvn package

pause