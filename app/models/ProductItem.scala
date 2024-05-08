package models

import models.dto.ProductItemDTO
import models.services.converter.ProductItemMapper.dtoToProductItem

case class ProductItem(id: String, price: Int, count: Int, availability: Boolean, product: Product)

object ProductItem{
  def dtoToProductItem(productItemDTO: ProductItemDTO, product: Product): ProductItem = {
    ProductItem(
      id = productItemDTO.id,
      price = productItemDTO.price,
      count = productItemDTO.count,
      availability = productItemDTO.availability,
      product = product)
  }

  def allDtoToProductItem(productItemsDTO: List[ProductItemDTO], product: Product): List[ProductItem] = {
    productItemsDTO.map(p => dtoToProductItem(p, product))
  }
}
