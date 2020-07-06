package model

import (
	"github.com/jinzhu/gorm"
	uuid "github.com/satori/go.uuid"
)

type SysUser struct{
	gorm.Model
	UUID    uuid.UUID  `json:"uuid" gorm:"comment:'用户UUID'"`
	Name    string     `json:"name" gorm:"comment:'姓名'"`
	Age     string     `json:"age"  gorm:"comment:'年龄'"`
}
