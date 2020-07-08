package api

import (
    "fmt"
    "github.com/gin-gonic/gin"
    "gotest/global/response"
    request "gotest/model/request"
    "gotest/service"
)

func GetUserList(c *gin.Context){
   err , list := service.GetUserList()
    if err != nil{
        response.FailWithMessage("用户列表不存在",c)
    }else{
        response.OkWithData(list,c);
    }
}
//@Tags sysUser
//@Summary 删除用户
//@accept application/json
//@produce application/json
//@Param data body `request.GetById` 删除用户
//@Success 200 (string) string "{"success":true,"data":{},"message":"修改成功"}"
//@Router /user/deleteUser
func DeleteUser(c *gin.Context){
    var reqId request.GetById
    err := service.DeleteUser(reqId.Id)
    if err != nil {
        response.FailWithMessage(fmt.Sprintf("删除失败，%v", err), c)
    } else {
        response.OkWithMessage("删除成功", c)
    }
}