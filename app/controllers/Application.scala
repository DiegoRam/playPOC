package controllers

import play.api._
import play.api.mvc._
import play.api.libs.json._
import play.api.libs.functional.syntax._
import models.Models.Bundle

object Application extends Controller {

  import services.repositories.MemoryStorage.BundleMemory

  type RawBundle = (Long, String, Double)

  val defaultBundle = Bundle(0L, "", 0)


  implicit val bundleParser: Reads[RawBundle] = (
    (__ \ "id").read[Long] and
      (__ \ "description").read[String] and
      (__ \ "cost").read[Double]
    ) tupled

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  def getBundles = Action {
    implicit request =>
      Ok(Json.toJson(Json.obj("data" -> (for {
        bundle <- defaultBundle.getAll
        jsonObject = Json.obj("id" -> bundle.id, "description" -> bundle.description, "cost" -> bundle.cost)
      } yield (jsonObject)))))
  }


  def createBundles = Action(parse.json) {
    implicit request =>
      request.body.validate[List[RawBundle]].map {
        case head::tail => {
          head :: tail foreach (bundle => Bundle(bundle._1, bundle._2, bundle._3).save)
          Ok(Json.obj("status" -> "ok"))
        }
      }.recoverTotal {
        e => BadRequest("Detected error:" + JsError.toFlatJson(e))
      }
  }

}