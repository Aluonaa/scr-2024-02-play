package models.dao.entities

import models.dto.AccountProductDTO

case class AccountProduct(id: String, productId: String, accountId: String)

object AccountProduct{
  def dtoToAccountProductMapper(accountProductDTO: AccountProductDTO): AccountProduct = {
    AccountProduct(
      id = accountProductDTO.id,
      productId = accountProductDTO.productId,
      accountId = accountProductDTO.accountId)
  }
}
