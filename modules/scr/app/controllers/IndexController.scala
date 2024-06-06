package controllers

import com.google.inject.Inject
import models.dto.LoginDTO
import models.services.AccountService
import models.{Paging, User, UserId}
import play.api.data.{Form, Forms, Mapping}
import play.api.data.Forms.{email, mapping, nonEmptyText}
import play.api.libs.json.{JsValue, Json, Writes}
import play.api.mvc.{Action, AnyContent, Controller, Cookie, DiscardingCookie}

case class Foo(f: String)

object Foo{
  implicit val writer: Writes[Foo] = Json.writes[Foo]
}

class IndexController @Inject()(val accountService: AccountService) extends Controller{

  def index = Action{
    Ok(views.html.index())
  }

  val mapping: Mapping[(String, String)] = Forms.tuple(
    "email" -> email,
    "password" -> nonEmptyText(minLength = 6)
  )

  val mapping2: Mapping[LoginDTO] = Forms.mapping(
    "email" -> email,
    "password" -> nonEmptyText(minLength = 6)
  )(LoginDTO.apply)(LoginDTO.unapply)

  val form: Form[LoginDTO] = Form(mapping2)

  def loginPage = Action{
    Ok(views.html.login(form))
  }

  def loginFormSubmit() = Action{ implicit req =>
    form.bindFromRequest.fold(
      formWithErrors => BadRequest(views.html.login(formWithErrors)),
      dto =>
        {
          accountService.create(dto.email)
          Redirect(routes.IndexController.index())
            .withSession("userId" -> dto.email)
        }
    )
  }
}

