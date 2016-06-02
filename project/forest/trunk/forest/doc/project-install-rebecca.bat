set "home=F:/qcloud/project"

cd %home%
cd component/personalcenter/trunk/personalcenter-api
call mvn install

cd %home%
cd component/my/trunk/my-api
call mvn install

cd %home%
cd component/sellercenter/trunk/sellercenter-api
call mvn install

cd %home%
cd mall/commoditycenter/trunk/commoditycenter-api
call mvn install

cd %home%
cd mall/marketing/trunk/marketing-api
call mvn install

cd %home%
cd mall/orderform/trunk/orderform-api
call mvn install

cd %home%
cd mall/groupbuys/trunk/groupbuys-api
call mvn install

cd %home%
cd mall/seckill/trunk/seckill-api
call mvn install



cd %home%
cd component/analysis/trunk
call mvn install

cd %home%
cd component/personalcenter/trunk
call mvn install

cd %home%
cd component/my/trunk
call mvn install

cd %home%
cd component/sellercenter/trunk
call mvn install

cd %home%
cd mall/commoditycenter/trunk
call mvn install

cd %home%
cd mall/distribution/trunk
call mvn install

cd %home%
cd mall/warehouse/trunk
call mvn install

cd %home%
cd mall/marketing/trunk
call mvn install

cd %home%
cd mall/orderform/trunk
call mvn install

cd %home%
cd mall/groupbuys/trunk
call mvn install

cd %home%
cd mall/seckill/trunk
call mvn install

cd %home%
cd mall/mall/trunk
call mvn install

cd %home%
cd project/forest/trunk
call mvn package

pause