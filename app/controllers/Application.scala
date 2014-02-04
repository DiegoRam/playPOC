package controllers

import play.api._
import play.api.mvc._
import play.api.libs.json._
import play.api.libs.functional.syntax._

object Application extends Controller {

  type RawBundle = (Long, String, Double)

  implicit val bundleParser: Reads[RawBundle] = (
      (__ \ "id").read[Long] and
      (__ \ "description").read[String] and
      (__ \ "cost").read[Double]
    ) tupled

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }
  def getBundles = Action { Ok }
  def createBundles = Action(parse.json) { implicit request =>
    request.body.validate[(List[RawBundle])] map {
      case head :: tail => println((head +: tail).mkString)
      case Nil => println("empty payload \n")
    }
    Ok
  }

}