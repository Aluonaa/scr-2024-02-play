package models.services

import com.google.inject.Inject
import models.dao.entities.{AccountProduct, ProductItem}
import models.dao.repositories.AccountProductRepository
import models.dto.AccountProductDTO
import models.services.constants.AppConstants
import models.services.response.BaseResponseDTO

trait AccountProductService{
//  def findItemsByEmailAccount(email: String): List[ProductItem]
//  def cartCost(email: String): Int
  def insert(accountProductDTO: AccountProductDTO): BaseResponseDTO[AccountProductDTO]
}

class AccountProductServiceImpl @Inject()(val accountProductRepository: AccountProductRepository) extends AccountProductService {

  override def insert(accountProductDTO: AccountProductDTO): BaseResponseDTO[AccountProductDTO] = {
    val accountProduct = AccountProduct.dtoToAccountProductMapper(accountProductDTO)
    accountProductRepository.findById(accountProduct.id) match {
      case Some(x) => BaseResponseDTO.alreadyExist(AppConstants.AlreadyExistProductInAccount, AccountProductDTO.accountProductToDtoMapper(x))
      case None =>
        val result = accountProductRepository.insert(accountProduct)
        BaseResponseDTO.success(AppConstants.SuccessAddProductInAccount, AccountProductDTO.accountProductToDtoMapper(result))
    }
  }

//  override def findItemsByEmailAccount(email: String): List[ProductItem] = ???
//
//  override def cartCost(email: String): Int = ???
}


