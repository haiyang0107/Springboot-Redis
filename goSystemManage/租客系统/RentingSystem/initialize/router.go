package initialize

import (
	"github.com/gin-gonic/gin"
	ginSwagger "github.com/swaggo/gin-swagger"
	"github.com/swaggo/gin-swagger/swaggerFiles"
	"gotest/global"
	"gotest/middleware"
	. "gotest/router"
)

//初始化总路由
func Routers() *gin.Engine{
	var Router = gin.Default()
	global.GVA_LOG.Debug("use middleware logger")
	//跨域
	Router.Use(middleware.Cors())

	global.GVA_LOG.Debug("use middleware cors")
	Router.GET("/swagger/*any", ginSwagger.WrapHandler(swaggerFiles.Handler))
	global.GVA_LOG.Debug("register swagger handler")
	// 方便统一添加路由组前缀 多服务器上线使用
	ApiGroup := Router.Group("")
	InitUserRouter(ApiGroup) // 注册用户路由
	global.GVA_LOG.Info("router register success")
	return Router
}
