# docker-usermodel
SpringMVC+mybatis+mysql的restful测试程序

###项目使用springMVC+mybatis+mysql构建的resultful风格的api接口
###共有5个接口，管理员登录，新增用户，删除用户，查询用户，修改用户
###使用user.sql导入mysql数据，数据库默认用户，root，密码为空
###测试方法，
         1.在测试其他接口之前需要先进行管理员登录
         2.mysql中只有2个有效用户，admin/111111 ,asianinfo/222222 密码在db中MD5加密
         3.用curl模拟测试的时，除登录方法外其余接口在测试时都需要增加
             --header "Authorization:{登录接口返回的Authorization}"
             --cookie "JSESSIONID={登录接口返回的set-cookie字段的值}"
             
####管理员登录接口：

       curl --user admin:111111  http://localhost:8081/dockerUserModel/users/auth
       返回值：{"message":"操作成功！","Authorization":"Basic YWRtaW46MTExMTEx","code":"1000"}
   
####新增用户接口

      curl -H "Content-Type: application/json" --header "Authorization:Basic YWRtaW46MTExMTEx" --cookie "JSESSIONID=62248422A55527D4E8E1713FAF591FCC;" -X POST -d '{"userName":"new","passwd":"111111","nickName":"管理员","userType":3,"comments":"修改用户"}' http://localhost:8081/dockerUserModel/users/user
      返回值：{"message":"操作成功！","code":"1000"}

####删除用户 

      curl --header "Authorization:Basic YWRtaW46MTExMTEx" --cookie "JSESSIONID=62248422A55527D4E8E1713FAF591FCC;" -X "DELETE" http://localhost:8081/dockerUserModel/users/user/{id}
 
####修改用户信息

     curl -H "Content-Type: application/json" --header "Authorization:Basic YWRtaW46MTExMTEx" --cookie "JSESSIONID=62248422A55527D4E8E1713FAF591FCC;" -X "PUT" -d '{"id":"7","userName":"asianinfo","passwd":"e3ceb5881a0a1fdaad01296d7554868d","nickName":"管理员修改","userType":2,"comments":"curl修改用户"}'  http://localhost:8081/dockerUserModel/users/user/{id}
     返回值：{"message":"操作成功！","code":"1000"}
 
####查询用户信息

     curl  --header "Authorization:Basic YWRtaW46MTExMTEx" --cookie "JSESSIONID=62248422A55527D4E8E1713FAF591FCC;" http://localhost:8081/dockerUserModel/users/{userNam}
     返回值：{"message":"操作成功！","result":{"userName":"admin","passwd":"96e79218965eb72c92a549dd5a330112","nickName":"","userType":2,"comments":"","id":10},"code":"1000"}
     
