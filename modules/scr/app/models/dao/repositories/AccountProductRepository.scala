package models.dao.repositories

import models.dao.entities.{AccountProduct, ProductItem}
import models.dao.schema.AccountProductSchema
import org.squeryl.PrimitiveTypeMode._

trait AccountProductRepository {
  def insert(accountProduct: AccountProduct): AccountProduct
  def findById(id: String): Option[AccountProduct]
//  def findItemsByEmailAccount(email: String): List[ProductItem]
//  def cartCost(email: String): Int
}

class AccountProductRepositoryImpl extends AccountProductRepository {

  val accountProductTable = AccountProductSchema.accountProduct

  override def insert(accountProduct: AccountProduct): AccountProduct = {
    transaction(accountProductTable.insert(accountProduct))
  }

  override def findById(id: String): Option[AccountProduct] = {
    transaction(from(accountProductTable)(r => where(r.id === id) select(r) ).headOption)
  }

//  override def findItemsByEmailAccount(email: String): List[ProductItem] = {
//    transaction(from(accountProductTable)(r => where(r.accountId === email) select(r.productId)).headOption).getOrElse(List())
//  }
//
//  override def cartCost(email: String): Int = ???
}
