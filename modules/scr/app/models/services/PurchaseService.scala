package models.services

import com.google.inject.Inject
import models.dao.entities.Purchase
import models.dao.repositories.PurchaseRepository
import models.dto.PurchaseDTO
import models.services.constants.AppConstants
import models.services.response.BaseResponseDTO

trait PurchaseService {
  //def buy(email: String): BaseResponseDTO[PurchaseDTO]
}

class PurchaseServiceImpl @Inject()(val purchaseRepository: PurchaseRepository,
                                    val accountService: AccountService) extends PurchaseService {
//  override def buy(email: String): BaseResponseDTO[PurchaseDTO] = {
//    val balance = accountService.balance(email)
//
//    //val result = purchaseRepository.buy(purchase)
//    BaseResponseDTO.success(AppConstants.SuccessBuy, PurchaseDTO.purchaseToDto(result))
//  }
}
