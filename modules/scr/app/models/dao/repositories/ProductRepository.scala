package models.dao.repositories

import models.dao.entities.{ProductItem, Product}
import models.dao.schema.ProductSchema
import org.squeryl.PrimitiveTypeMode._


trait ProductRepository {
  def insert(product: Product): Product
  def insert(item: ProductItem): ProductItem
  def find(): List[Product]
  def findByTitle(title: String): List[Product]
  def findProductItemsByProductId(id: String): List[ProductItem]
  def findById(id: String): Option[Product]
  def findByIdProductItem(id: String): Option[ProductItem]
  def delete(id: String): Unit
}

class ProductRepositoryImpl extends ProductRepository{

  val productTable = ProductSchema.product
  val productItemTable = ProductSchema.productItem

  override def insert(product: Product): Product =
    transaction(productTable.insert(product))

  override def insert(productItem: ProductItem): ProductItem =
    transaction(productItemTable.insert(productItem))


  override def find(): List[Product] = {
    transaction(from(productTable)((r) => select(r)).toList)
  }

  override def findByTitle(title: String): List[Product] =
    transaction(from(productTable)(r => where(r.title === title) select(r)).toList)

    override def findById(id: String): Option[Product] = {
      transaction(from(productTable)(r => where(r.id === id) select(r) ).headOption)
    }

    override def findByIdProductItem(id: String): Option[ProductItem] = {
      transaction(from(productItemTable)(r => where(r.id === id) select(r) ).headOption)
    }

  override def delete(id: String): Unit = {
    transaction(
      productItemTable.deleteWhere(_.productId === id),
      productTable.deleteWhere(_.id === id)
    )
  }

  override def findProductItemsByProductId(productId: String): List[ProductItem] = {
    transaction(from(productItemTable)(r => where(r.productId === productId) select(r)).toList)
  }
}
