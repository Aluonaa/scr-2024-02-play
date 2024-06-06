package module

import models.dao.repositories.{AccountProductRepository, AccountProductRepositoryImpl, AccountRepository, AccountRepositoryImpl, ProductRepository, ProductRepositoryImpl, PurchaseRepository, PurchaseRepositoryImpl}
import models.services.{AccountProductService, AccountProductServiceImpl, AccountService, AccountServiceImpl, ProductService, ProductServiceImpl, PurchaseService, PurchaseServiceImpl}

import scala.di.AppModule

class ScrModule extends AppModule{
  override def configure(): Unit = {
    bindSingleton[ProductService, ProductServiceImpl]
    bindSingleton[ProductRepository, ProductRepositoryImpl]
    bindSingleton[PurchaseService, PurchaseServiceImpl]
    bindSingleton[PurchaseRepository, PurchaseRepositoryImpl]
    bindSingleton[AccountService, AccountServiceImpl]
    bindSingleton[AccountRepository, AccountRepositoryImpl]
    bindSingleton[AccountProductService, AccountProductServiceImpl]
    bindSingleton[AccountProductRepository, AccountProductRepositoryImpl]
  }
}
