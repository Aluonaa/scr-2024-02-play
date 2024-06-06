package models.dao.entities

import models.dto.PurchaseDTO

case class Purchase(id: String, accountId: String, money: Int)

object Purchase{
  def dtoToPurchaseMapper(purchaseDTO: PurchaseDTO): Purchase = {
    Purchase(
      id = purchaseDTO.id,
      accountId = purchaseDTO.accountId,
      money = purchaseDTO.money)
  }
}
