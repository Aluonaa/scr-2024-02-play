package models.dao.repositories

import com.google.inject.Inject
import models.dao.entities.Purchase
import models.dao.schema.PurchaseSchema
import org.squeryl.PrimitiveTypeMode.transaction

trait PurchaseRepository {
  def buy(purchase: Purchase): Purchase
}

class PurchaseRepositoryImpl @Inject()(val purchaseRepository: PurchaseRepository) extends PurchaseRepository {

  val purchaseTable = PurchaseSchema.purchase

  override def buy(purchase: Purchase): Purchase = {
    transaction(purchaseTable.insert(purchase))
  }
}
