package models.services

import com.google.inject.Inject
import models.dao.entities.Account
import models.dao.repositories.{AccountRepository, ProductRepository}
import models.dto.AccountDTO
import models.services.constants.AppConstants
import models.services.response.BaseResponseDTO

trait AccountService {
  def create(email: String): BaseResponseDTO[AccountDTO]
  def addingFunds(email: String, money: Int): BaseResponseDTO[String]
  def balance(email: String): Int
}

class AccountServiceImpl @Inject()(val accountRepository: AccountRepository) extends AccountService {

  override def create(email: String): BaseResponseDTO[AccountDTO] = {
    accountRepository.findByEmail(email) match {
      case Some(x) => BaseResponseDTO.alreadyExist(AppConstants.AlreadyExistAccount, AccountDTO.accountToDtoMapper(x))
      case None =>
        val result = accountRepository.create(email)
        BaseResponseDTO.success(AppConstants.SuccessAddAccount, AccountDTO.accountToDtoMapper(result))
    }
  }

  override def addingFunds(email: String, money: Int): BaseResponseDTO[String] = {
    accountRepository.findByEmail(email) match {
      case Some(x) =>
        accountRepository.addingFunds(email, money)
        BaseResponseDTO.success(AppConstants.SuccessAddingFunds, email)
      case None =>
        BaseResponseDTO.notFound(AppConstants.FailureFindAccount, email)
    }
  }

  override def balance(email: String): Int = {
    accountRepository.balance(email)
  }
}