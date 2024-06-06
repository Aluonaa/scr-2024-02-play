package models.dao.schema

import models.dao.entities.{ProductItem, Product}
import org.squeryl.Schema


object ProductSchema extends Schema{

  val product = table[Product]

  val productItem = table[ProductItem]

}
