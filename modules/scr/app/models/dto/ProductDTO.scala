package models.dto

import models.{Product, ProductItem}
import play.api.libs.json.{Json, Reads, Writes}

case class ProductDTO(id: String, title: String, description: String, items: List[ProductItemDTO] = List())

object ProductDTO{

  implicit val reads: Reads[ProductDTO] = Json.reads[ProductDTO]

  implicit val writes: Writes[ProductDTO] = Json.writes[ProductDTO]

  def productToDtoMapper(product: Product, items: List[ProductItem]): ProductDTO = {
    ProductDTO(
      id = product.id,
      title = product.title,
      description = product.description,
      items = ProductItemDTO.allProductItemToDto(items))
  }
}
