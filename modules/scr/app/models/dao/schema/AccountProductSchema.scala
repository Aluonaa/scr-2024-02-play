package models.dao.schema

import models.dao.entities.AccountProduct
import org.squeryl.Schema

object AccountProductSchema extends Schema{

  val accountProduct = table[AccountProduct]
}
