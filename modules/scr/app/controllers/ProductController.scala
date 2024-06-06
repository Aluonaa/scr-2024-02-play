package controllers

import com.google.inject.Inject
import models.dto.{ProductDTO, PurchaseDTO}
import models.services.{AccountService, ProductService, PurchaseService}
import models.services.response.BaseResponseDTO
import play.api.data.Forms.nonEmptyText
import play.api.data.{Form, Forms, Mapping}
import play.api.libs.json.Json
import play.api.mvc.{Action, AnyContent}

class ProductController @Inject()(
      val productService: ProductService,
      val purchaseService: PurchaseService,
      val accountService: AccountService) extends Authorization {

    private val insertProductMapping: Mapping[ProductDTO] = Forms.mapping(
      "id" -> nonEmptyText,
      "title" -> nonEmptyText,
      "description" -> nonEmptyText
    )(ProductDTO.apply2)(ProductDTO.unapply2)

    private val editProductForm: Form[ProductDTO] = Form(insertProductMapping)

    def insertProduct(): Action[AnyContent] = Action{
      Ok(views.html.products.insert(editProductForm))
    }

    def insertedProduct(): Action[AnyContent] = Action{
      Ok(views.html.products.inserted())
    }

  def insertFormSubmit() = Action{ implicit req =>
    editProductForm.bindFromRequest.fold(
      formWithErrors => BadRequest(views.html.products.insert(formWithErrors)),
      dto => {
        productService.insert(dto)
        Redirect(routes.ProductController.insertedProduct())
      }
    )
  }

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



  private val addingFundsMapping: Mapping[(String, String)] = Forms.tuple(
    "email" -> nonEmptyText,
    "money" -> nonEmptyText
  )

  private val addingFundsForm: Form[(String, String)] = Form(addingFundsMapping)

  def addingFunds(): Action[AnyContent] = Action{
    Ok(views.html.account.addingFunds(addingFundsForm))
  }

  def addedFunds(): Action[AnyContent] = Action{
    Ok(views.html.account.addedFunds())
  }

  def addingFundsFormSubmit(): Action[AnyContent] = Action{ implicit req =>
    addingFundsForm.bindFromRequest.fold(
      formWithErrors => BadRequest(views.html.account.addingFunds(formWithErrors)),
      dto => {
        accountService.addingFunds(dto._1, dto._2.toInt)
        Redirect(routes.ProductController.addedFunds())
      }
    )
  }

//  def buy(): Action[AnyContent] = authorize{ rc =>
//    val result: BaseResponseDTO[PurchaseDTO] =
//      purchaseService.buy(rc.request.session.get("email").getOrElse(""))
//    Ok(Json.toJson(result.result))
//  }


}

