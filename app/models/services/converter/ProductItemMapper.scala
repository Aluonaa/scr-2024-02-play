package models.services.converter

import models.{Product, ProductItem}
import models.dto.ProductItemDTO

object ProductItemMapper {
  def dtoToProductItem(productItemDTO: ProductItemDTO, product: Product): ProductItem = {
    ProductItem(
      id = productItemDTO.id,
      price = productItemDTO.price,
      count = productItemDTO.count,
      availability = productItemDTO.availability,
      product = product)
  }

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

  def allDtoToProductItem(productItemsDTO: List[ProductItemDTO], product: Product): List[ProductItem] = {
    productItemsDTO.map(p => dtoToProductItem(p, product))
  }
}
