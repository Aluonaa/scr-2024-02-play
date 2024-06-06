package models.dto

import models.dao.entities.AccountProduct
import play.api.libs.json.{Json, Reads, Writes}

case class AccountProductDTO(id: String, productId: String, accountId: String)

object AccountProductDTO{

  implicit val reads: Reads[AccountProductDTO] = Json.reads[AccountProductDTO]

  implicit val writes: Writes[AccountProductDTO] = Json.writes[AccountProductDTO]

  def accountProductToDtoMapper(accountProduct: AccountProduct): AccountProductDTO = {
    AccountProductDTO(
      id = accountProduct.id,
      productId = accountProduct.productId,
      accountId = accountProduct.accountId)
  }
}
