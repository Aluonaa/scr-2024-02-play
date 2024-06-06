package models.dao.schema

import models.dao.entities.Purchase
import org.squeryl.Schema

object PurchaseSchema extends Schema{

  val purchase = table[Purchase]
}
