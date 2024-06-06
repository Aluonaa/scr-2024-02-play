package models.dto

import models.dao.entities.Account
import play.api.libs.json.{Json, Reads, Writes}

case class AccountDTO(id: String, money: Int)

object AccountDTO{

  implicit val reads: Reads[AccountDTO] = Json.reads[AccountDTO]

  implicit val writes: Writes[AccountDTO] = Json.writes[AccountDTO]


  def accountToDtoMapper(account: Account): AccountDTO = {
    AccountDTO(
      id = account.id,
      money = account.money)
  }
}
