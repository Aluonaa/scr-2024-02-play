package models.services

import models.{Product, ProductItem}
import models.dto.ProductDTO
import models.services.constants.AppConstants
import models.services.response.BaseResponseDTO
import repository.impl.ProductRepositoryImpl

trait ProductService {

  def insert(productDTO: ProductDTO): BaseResponseDTO[ProductDTO]
  def delete(id: String): BaseResponseDTO[ProductDTO]
  def getByTitle(title: String): BaseResponseDTO[List[ProductDTO]]
  def getAll(): BaseResponseDTO[List[ProductDTO]]
}

class ProductServiceImpl extends ProductService {

  private val productRepository = new ProductRepositoryImpl

  override def insert(productDTO: ProductDTO): BaseResponseDTO[ProductDTO] = {
    val product: Product = Product.dtoToProductMapper(productDTO)
    val res = productRepository.findById(product.id)
    val productItems: List[ProductItem] = ProductItem.allDtoToProductItem(productDTO.items, product)
    val productDTONew: ProductDTO = ProductDTO.productToDtoMapper(productRepository.insert(product, productItems), productItems)
    res match {
      case Some(_) => BaseResponseDTO.alreadyExist(AppConstants.AlreadyExistProduct, productDTONew)
      case None => BaseResponseDTO.success(AppConstants.SuccessAddProducts, productDTONew)
    }
  }

  override def getByTitle(title: String): BaseResponseDTO[List[ProductDTO]] = {
    val result: List[ProductDTO] = productRepository.findByTitle(title).map(e => ProductDTO.productToDtoMapper(e._1, e._2))
    if(result.isEmpty) BaseResponseDTO.notFound(AppConstants.ProductsNotFound, result)
    else BaseResponseDTO.success(AppConstants.ProductsFound, result)
  }

  override def getAll(): BaseResponseDTO[List[ProductDTO]] = {
    val result: List[ProductDTO] = productRepository.getAll().map(p => ProductDTO.productToDtoMapper(p._1, p._2))
    if(result.isEmpty) BaseResponseDTO.notFound(AppConstants.ProductsNotFound, result)
    else BaseResponseDTO.success(AppConstants.ProductsFound, result)
  }

  override def delete(id: String): BaseResponseDTO[ProductDTO] = {
    productRepository.findById(id) match {
      case Some(x) =>
        val res = productRepository.delete(x._1)
        val result: ProductDTO = ProductDTO.productToDtoMapper(res._1, res._2)
        BaseResponseDTO.success(AppConstants.SuccessDeleteProduct, result)

      case None => BaseResponseDTO.notFound(AppConstants.ProductNotFound, ProductDTO("", "", "", List()))
    }
  }
}
