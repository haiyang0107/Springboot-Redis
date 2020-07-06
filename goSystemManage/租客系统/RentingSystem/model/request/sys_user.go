package request

import uuid "github.com/satori/go.uuid"

type deleteUser struct {
	UUID      uuid.UUID `json:"uuid"`
}