package models

import models.dto.ProductDTO

case class Product(id: String, title: String, description: String)

object Product{
  def dtoToProductMapper(productDTO: ProductDTO): Product = {
    Product(
      id = productDTO.id,
      title = productDTO.title,
      description = productDTO.description)
  }

}
