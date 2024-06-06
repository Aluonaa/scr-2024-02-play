package models.dao.entities

import models.dto.AccountDTO
import org.squeryl.KeyedEntity

case class Account(id: String, money: Int) extends KeyedEntity[String]

object Account{
  def dtoToAccountMapper(accountDTO: AccountDTO): Account = {
    Account(
      id = accountDTO.id,
      money = accountDTO.money)
  }
}
