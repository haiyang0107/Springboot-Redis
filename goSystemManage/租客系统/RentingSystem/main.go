package main

import (
	"gotest/core"
	"gotest/global"
	"gotest/initialize"
)

func main(){

	//加载文件，进行配置文件读取
	core.Init()
	switch global.GVA_CONFIG.System.DbType{
	case "mysql":
		initialize.Mysql()
	default:
		initialize.Mysql()
	}
	//初始化表结构
	initialize.DBTables()
	core.RunWindowsServer()
	// 程序结束前关闭数据库链接
	//defer global.GVA_DB.Close()

}
