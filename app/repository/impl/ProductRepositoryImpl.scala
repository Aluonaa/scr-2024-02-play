package repository.impl

import models.{Product, ProductItem}
import repository.ProductRepository

import scala.collection.mutable

class ProductRepositoryImpl extends ProductRepository{

  var productRepository = mutable.HashMap.empty[String, Product]
  var productItemRepository = mutable.HashMap.empty[String, ProductItem]

  override def insert(product: Product, itemList: List[ProductItem]): Product = {
    productRepository.put(product.id, product)
    itemList.foreach(i => productItemRepository.put(i.id, i))
    product
  }

  override def getAll(): List[(Product, List[ProductItem])] = {
    productRepository.values.map(p => (p, productItemRepository.values.filter(i => i.product.id == p.id).toList)).toList
  }

  override def findByTitle(title: String): List[(Product, List[ProductItem])] =
    productRepository.values.filter(_.title == title).map(p => (p, productItemRepository.values.filter(i => i.product.id == p.id).toList)).toList

  override def findById(id: String): Option[(String, Product)] = {
    productRepository.find(p => p._1 == id)
  }

  override def delete(id: String): (Product, List[ProductItem]) = {
    val itemsToRemove = productItemRepository.filter(i => i._2.product.id == id).toList
    val result = (productRepository(id), itemsToRemove.map(i => i._2))
    productRepository.remove(id)
    itemsToRemove.foreach(i => productItemRepository.remove(i._1))
    result
  }
}
