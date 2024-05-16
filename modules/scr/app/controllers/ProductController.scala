package controllers

import com.google.inject.Inject
import models.dto.ProductDTO
import models.services.ProductService
import models.services.response.BaseResponseDTO
import play.api.libs.json.Json
import play.api.mvc.{Action, AnyContent}

class ProductController @Inject()(val productService: ProductService) extends Authorization {

  def insert(): Action[ProductDTO] = authorize(parse.json[ProductDTO]) { rc =>
    val productDTO = rc.request.body
    val result: BaseResponseDTO[ProductDTO] = productService.insert(productDTO)
    Ok(Json.toJson(result))
  }

  def getByTitle(title: String): Action[AnyContent] = authorize{
    val result: BaseResponseDTO[List[ProductDTO]] = productService.getByTitle(title)
    Ok(Json.toJson(result))
  }

  def getAll(): Action[AnyContent] = authorize{
    val result: BaseResponseDTO[List[ProductDTO]] = productService.getAll()
    Ok(Json.toJson(result))
  }
  def delete(id: String): Action[AnyContent] = authorize{
    val result: BaseResponseDTO[ProductDTO] = productService.delete(id)
    Ok(Json.toJson(result))
  }
}

