package router

import (
	"github.com/gin-gonic/gin"
	"gotest/api"
	"gotest/middleware"
)

func InitUserRouter(Router *gin.RouterGroup){
	UserRouter := Router.Group("user").Use(middleware.Cors())
	{
		UserRouter.POST("getUserList",api.GetUserList)
		UserRouter.DELETE("deleteUser",api.DeleteUser)
	}
}
