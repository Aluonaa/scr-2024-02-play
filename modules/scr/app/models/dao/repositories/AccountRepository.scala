package models.dao.repositories

import models.dao.entities.Account
import models.dao.schema.AccountSchema
import org.squeryl.PrimitiveTypeMode._

trait AccountRepository {
  def findByEmail(email: String): Option[Account]
  def create(email: String): Account
  def addingFunds(email: String, money: Int): Unit
  def pay(email: String, money: Int): Unit
  def balance(email: String): Int
}

class AccountRepositoryImpl extends AccountRepository{

  val accountTable = AccountSchema.account


  override def findByEmail(email: String): Option[Account] = {
    transaction(from(accountTable)(r => where(r.id === email) select(r) ).headOption)
  }

  override def create(email: String): Account = {
    val account = Account(email, 0)
    transaction(accountTable.insert(account))
    account
  }

  override def addingFunds(email: String, money: Int): Unit = {
    transaction(update(accountTable)(a => where(a.id === email)set(a.money := a.money.~ + money)))
  }

  override def pay(email: String, money: Int): Unit = {
    transaction(update(accountTable)(a => where(a.id === email)set(a.money := a.money.~ - money)))
  }

  override def balance(email: String): Int = {
    transaction(from(accountTable)(r => where(r.id === email) select(r.money) ).headOption).getOrElse(0)
  }
}
