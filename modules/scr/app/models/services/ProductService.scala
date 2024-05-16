package models.services

import com.google.inject.Inject
import models.dao.entities.{Product, ProductItem}
import models.dto.ProductDTO
import models.services.constants.AppConstants
import models.services.response.BaseResponseDTO
import models.dao.repositories.ProductRepository



trait ProductService {

  def insert(productDTO: ProductDTO): BaseResponseDTO[ProductDTO]
  def delete(id: String): BaseResponseDTO[ProductDTO]
  def getByTitle(title: String): BaseResponseDTO[List[ProductDTO]]
  def getAll(): BaseResponseDTO[List[ProductDTO]]
}

class ProductServiceImpl @Inject()(val productRepository: ProductRepository) extends ProductService {

  override def insert(productDTO: ProductDTO): BaseResponseDTO[ProductDTO] = {
    val product: Product = Product.dtoToProductMapper(productDTO)
    val productItems: List[ProductItem] = ProductItem.allDtoToProductItem(productDTO.items, product.id)

//    def saveProductItems(productItems: List[ProductItem]) = productItems
//      .filter(p => productRepository.findByIdProductItem(p.id).isEmpty)
//      .map(p => productRepository.insert(p))

    def saveProductItems(productItems: List[ProductItem]) =
      for(x <- productItems
      if productRepository.findByIdProductItem(x.id).isEmpty)
      yield productRepository.insert(x)

    productRepository.findById(product.id) match {
      case Some(p) =>
        val productDTONew: ProductDTO = ProductDTO.productToDtoMapper(p, saveProductItems(productItems))
        BaseResponseDTO.alreadyExist(AppConstants.AlreadyExistProduct, productDTONew)
      case None =>
        val savedProduct = productRepository.insert(product)
        val productDTONew: ProductDTO = ProductDTO.productToDtoMapper(savedProduct, saveProductItems(productItems))
        BaseResponseDTO.success(AppConstants.SuccessAddProducts, productDTONew)
    }
  }

  override def getByTitle(title: String): BaseResponseDTO[List[ProductDTO]] = {
    val result: List[ProductDTO] = productRepository.findByTitle(title).map(p =>
      ProductDTO.productToDtoMapper(p, productRepository.findProductItemsByProductId(p.id)))
    if(result.isEmpty) BaseResponseDTO.notFound(AppConstants.ProductsNotFound, result)
    else BaseResponseDTO.success(AppConstants.ProductsFound, result)
  }

  override def getAll(): BaseResponseDTO[List[ProductDTO]] = {
    val result: List[ProductDTO] = productRepository.find().map(p =>
      ProductDTO.productToDtoMapper(p, productRepository.findProductItemsByProductId(p.id)))
    if(result.isEmpty) BaseResponseDTO.notFound(AppConstants.ProductsNotFound, result)
    else BaseResponseDTO.success(AppConstants.ProductsFound, result)
  }

  override def delete(id: String): BaseResponseDTO[ProductDTO] = {
    productRepository.findById(id) match {
      case Some(x) =>
        val productItems = productRepository.findProductItemsByProductId(x.id)
        productRepository.delete(x.id)
        val result: ProductDTO = ProductDTO.productToDtoMapper(x, productItems)
        BaseResponseDTO.success(AppConstants.SuccessDeleteProduct, result)

      case None => BaseResponseDTO.notFound(AppConstants.ProductNotFound, ProductDTO("", "", "", List()))
    }
  }
}
