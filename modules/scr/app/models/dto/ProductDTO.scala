package models.dto

import models.UserId
import models.dao.entities.{Product, ProductItem}
import play.api.libs.json.{Json, Reads, Writes}
import play.api.mvc.{PathBindable, QueryStringBindable}

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

  def apply2(id: String, title: String, description: String): ProductDTO = {
    ProductDTO(
      id = id,
      title = title,
      description = description,
      items = List())
  }

  def unapply2(productDTO: ProductDTO) = {
    Some(productDTO.id, productDTO.title, productDTO.description)
  }
}
