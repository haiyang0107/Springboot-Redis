package response

import "gotest/model"

type ListUser struct {
	User model.SysUser `json:"user"`
}