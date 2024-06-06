package models.dto

import models.dao.entities.Purchase
import play.api.libs.json.{Json, Reads, Writes}

case class PurchaseDTO(id: String, accountId: String, money: Int, items: List[ProductItemDTO] = List())

object PurchaseDTO{

  implicit val reads: Reads[PurchaseDTO] = Json.reads[PurchaseDTO]

  implicit val writes: Writes[PurchaseDTO] = Json.writes[PurchaseDTO]

  def purchaseToDto(purchase: Purchase): PurchaseDTO = {
    PurchaseDTO(
      id = purchase.id,
      accountId = purchase.accountId,
      money = purchase.money)
  }
}