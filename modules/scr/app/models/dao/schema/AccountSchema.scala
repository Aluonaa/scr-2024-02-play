package models.dao.schema

import models.dao.entities.Account
import org.squeryl.Schema

object AccountSchema extends Schema{

  val account = table[Account]

}
