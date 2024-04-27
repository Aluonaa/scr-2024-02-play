package controllers

import models.dto.ProductDTO
import models.services.Impl.ProductServiceImpl
import models.services.ProductService
import models.services.response.BaseResponseDTO
import play.api.libs.json.Json
import play.api.mvc.{Action, AnyContent, Controller}

object ProductController extends Controller{

  private val productService: ProductService = new ProductServiceImpl()

  def insert(): Action[ProductDTO] = Action(parse.json[ProductDTO]) { rc =>
    val productDTO = rc.body
    val result: BaseResponseDTO[ProductDTO] = productService.insert(productDTO)
    Ok(Json.toJson(result))
  }

  def getByTitle(title: String): Action[AnyContent] = Action {
    val result: BaseResponseDTO[List[ProductDTO]] = productService.getByTitle(title)
    Ok(Json.toJson(result))
  }

  def getAll(): Action[AnyContent] = Action {
    val result: BaseResponseDTO[List[ProductDTO]] = productService.getAll()
    Ok(Json.toJson(result))
  }
  def delete(id: String): Action[AnyContent] = Action {
    val result: BaseResponseDTO[ProductDTO] = productService.delete(id)
    Ok(Json.toJson(result))
  }
}

