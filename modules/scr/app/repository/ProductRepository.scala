package repository

import models.{Product, ProductItem}

trait ProductRepository {
  def insert(product: Product, items: List[ProductItem]): Product
  def getAll(): List[(Product, List[ProductItem])]
  def findByTitle(title: String): List[(Product, List[ProductItem])]
  def findById(id: String): Option[(String, Product)]
  def delete(id: String): (Product, List[ProductItem])
}
