package module

import models.services.{ProductService, ProductServiceImpl}
import repository.{ProductRepository, ProductRepositoryImpl}

import scala.di.AppModule

class ScrModule extends AppModule{
  override def configure(): Unit = {
    bindSingleton[ProductService, ProductServiceImpl]
    bindSingleton[ProductRepository, ProductRepositoryImpl]
  }
}
