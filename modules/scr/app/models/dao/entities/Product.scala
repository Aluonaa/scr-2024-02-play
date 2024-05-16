package models.dao.entities

import models.dto.{ProductDTO, ProductItemDTO}
import org.squeryl.KeyedEntity

case class Product(id: String, title: String, description: String) extends KeyedEntity[String]

object Product{
  def dtoToProductMapper(productDTO: ProductDTO): Product = {
    Product(
      id = productDTO.id,
      title = productDTO.title,
      description = productDTO.description)
  }
}

case class ProductItem(id: String, price: Int, count: Int, availability: Boolean, productId: String) extends KeyedEntity[String]

object ProductItem{
  def dtoToProductItem(productItemDTO: ProductItemDTO, productId: String): ProductItem = {
    ProductItem(
      id = productItemDTO.id,
      price = productItemDTO.price,
      count = productItemDTO.count,
      availability = productItemDTO.availability,
      productId = productId)
  }

  def allDtoToProductItem(productItemsDTO: List[ProductItemDTO], productId: String): List[ProductItem] = {
    productItemsDTO.map(p => dtoToProductItem(p, productId))
  }
}