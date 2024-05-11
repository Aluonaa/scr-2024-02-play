package models.services.response

import models.dto.{ProductDTO, ProductItemDTO}
import play.api.libs.functional.syntax.toFunctionalBuilderOps
import play.api.libs.json.{JsPath, Writes}



case class BaseResponseDTO[T](status: Boolean, description: String, result: T)

object BaseResponseDTO{

  def success[T](description: String, result: T): BaseResponseDTO[T] = {
    BaseResponseDTO(
      status = true,
      description = description,
      result = result)
  }

  def notFound[T](description: String, result: T): BaseResponseDTO[T] =  {
    BaseResponseDTO(
      status = false,
      description = description,
      result = result)
  }


  def alreadyExist[T](description: String, result: T): BaseResponseDTO[T] =  {
    BaseResponseDTO(
      status = true,
      description = description,
      result = result)
  }


//  implicit val readsItem: Reads[BaseResponseDTO[ProductItemDTO]] = (
//    (JsPath \ "status").read[Boolean] and
//      (JsPath \ "description").read[String] and
//      (JsPath \ "result").read[ProductItemDTO]
//    )(BaseResponseDTO[ProductItemDTO].apply _)

  implicit val writesItem: Writes[BaseResponseDTO[ProductItemDTO]] = (
    (JsPath \ "status").write[Boolean] and
      (JsPath \ "description").write[String] and
      (JsPath \ "result").write[ProductItemDTO]
    )(r => (r.status, r.description, r.result))
//
//  implicit val readsProduct: Reads[BaseResponseDTO[ProductDTO]] = (
//    (JsPath \ "status").read[Boolean] and
//      (JsPath \ "description").read[String] and
//      (JsPath \ "result").read[ProductDTO]
//  )(BaseResponseDTO.apply)

  implicit val writesProduct: Writes[BaseResponseDTO[ProductDTO]] = (
    (JsPath \ "status").write[Boolean] and
    (JsPath \ "description").write[String] and
    (JsPath \ "result").write[ProductDTO]
    )(r => (r.status, r.description, r.result))


  implicit val writesListProduct: Writes[BaseResponseDTO[List[ProductDTO]]] = (
    (JsPath \ "status").write[Boolean] and
      (JsPath \ "description").write[String] and
      (JsPath \ "result").write[List[ProductDTO]]
    )(r => (r.status, r.description, r.result))

}
