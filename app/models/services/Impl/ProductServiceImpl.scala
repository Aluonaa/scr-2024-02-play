package models.services.Impl

import models.dto.ProductDTO
import models.services.ProductService
import models.services.constants.AppConstants
import models.services.converter.{ProductItemMapper, ProductMapper}
import models.services.response.BaseResponseDTO
import models.{Product, ProductItem}
import repository.impl.ProductRepositoryImpl

class ProductServiceImpl extends ProductService {

  private val productRepository = new ProductRepositoryImpl

  override def insert(productDTO: ProductDTO): BaseResponseDTO[ProductDTO] = {
    val product: Product = ProductMapper.dtoToProductMapper(productDTO)
    val res = productRepository.findById(product.id)
    val productItems: List[ProductItem] = ProductItemMapper.allDtoToProductItem(productDTO.items, product)
    val productDTONew: ProductDTO = ProductMapper.productToDtoMapper(productRepository.insert(product, productItems), productItems)
    res match {
      case Some(_) => BaseResponseDTO.alreadyExist(AppConstants.ALREADY_EXIST_PRODUCT, productDTONew)
      case None => BaseResponseDTO.success(AppConstants.SUCCESS_ADD_PRODUCT, productDTONew)
    }
  }

  override def getByTitle(title: String): BaseResponseDTO[List[ProductDTO]] = {
    val result: List[ProductDTO] = productRepository.findByTitle(title).map(e => ProductMapper.productToDtoMapper(e._1, e._2))
    if(result.isEmpty) BaseResponseDTO.notFound(AppConstants.PRODUCTS_NOT_FOUND, result)
    else BaseResponseDTO.success(AppConstants.PRODUCTS_FOUND, result)
  }

  override def getAll(): BaseResponseDTO[List[ProductDTO]] = {
    val result: List[ProductDTO] = productRepository.getAll().map(p => ProductMapper.productToDtoMapper(p._1, p._2))
    if(result.isEmpty) BaseResponseDTO.notFound(AppConstants.PRODUCTS_NOT_FOUND, result)
    else BaseResponseDTO.success(AppConstants.PRODUCTS_FOUND, result)
  }

  override def delete(id: String): BaseResponseDTO[ProductDTO] = {
    productRepository.findById(id) match {
      case Some(x) =>
        val res = productRepository.delete(x._1)
        val result: ProductDTO = ProductMapper.productToDtoMapper(res._1, res._2)
        BaseResponseDTO.success(AppConstants.SUCCESS_DELETE_PRODUCT, result)

      case None => BaseResponseDTO.notFound(AppConstants.PRODUCT_NOT_FOUND, ProductDTO("", "", "", List()))
    }
  }
}
