package service

import (
	"gotest/global"
	"gotest/model"
)

func GetUserList()(err error,list interface{}){
	db := global.GVA_DB
    var userList []model.SysUser
	err = db.Find(&userList).Error
	return err , userList
}

func DeleteUser(id float64)(err error){
	var user model.SysUser
	err = global.GVA_DB.Where("id=?",id).Delete(&user).Error
	return err
}
