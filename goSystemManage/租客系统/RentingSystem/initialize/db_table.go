package initialize

import (
	"gotest/global"
	"gotest/model"
)

//注册数据库表专用
func DBTables(){
	db := global.GVA_DB
	db.AutoMigrate(model.SysUser{})
	global.GVA_LOG.Debug("register tables success")
}