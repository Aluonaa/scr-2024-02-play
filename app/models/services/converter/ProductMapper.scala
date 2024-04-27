package models.services.converter

import models.dto.ProductDTO
import models.{Product, ProductItem}

object ProductMapper {
  def dtoToProductMapper(productDTO: ProductDTO): Product = {
    Product(
      id = productDTO.id,
      title = productDTO.title,
      description = productDTO.description)
  }

  def productToDtoMapper(product: Product, items: List[ProductItem]): ProductDTO = {
    ProductDTO(
      id = product.id,
      title = product.title,
      description = product.description,
      items = ProductItemMapper.allProductItemToDto(items))
  }

//  def allProductToDto(products: List[Product]): List[ProductDTO] = {
//    products.map(p => productToDtoMapper(p))
//  }
}
