package models.services

import models.dto.ProductDTO
import models.services.response.BaseResponseDTO

trait ProductService {

  def insert(productDTO: ProductDTO): BaseResponseDTO[ProductDTO]
  def delete(id: String): BaseResponseDTO[ProductDTO]
  def getByTitle(title: String): BaseResponseDTO[List[ProductDTO]]
  def getAll(): BaseResponseDTO[List[ProductDTO]]
}
