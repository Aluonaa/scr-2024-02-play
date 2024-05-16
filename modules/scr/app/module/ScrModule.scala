package module

import models.dao.repositories.{ProductRepository, ProductRepositoryImpl}
import models.services.{ProductService, ProductServiceImpl}

import scala.di.AppModule

class ScrModule extends AppModule{
  override def configure(): Unit = {
    bindSingleton[ProductService, ProductServiceImpl]
    bindSingleton[ProductRepository, ProductRepositoryImpl]
  }
}
