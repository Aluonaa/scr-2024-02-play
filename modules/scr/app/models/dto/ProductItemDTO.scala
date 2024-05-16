package models.dto

import models.dao.entities.ProductItem
import play.api.libs.json.{Json, Reads, Writes}

case class ProductItemDTO(id: String, price: Int, count: Int, availability: Boolean)

object ProductItemDTO{

  implicit val reads: Reads[ProductItemDTO] = Json.reads[ProductItemDTO]

  implicit val writes: Writes[ProductItemDTO] = Json.writes[ProductItemDTO]

  def productItemToDto(productItem: ProductItem): ProductItemDTO = {
    ProductItemDTO(
      id = productItem.id,
      price = productItem.price,
      count = productItem.count,
      availability = productItem.availability)
  }

  def allProductItemToDto(productItems: List[ProductItem]): List[ProductItemDTO] = {
    productItems.map(p => productItemToDto(p))
  }
}
