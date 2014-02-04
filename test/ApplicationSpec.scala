import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner._

import play.api.test._
import play.api.test.Helpers._
import play.api.libs.json._

/**
 * Add your spec here.
 * You can mock out a whole application including requests, plugins etc.
 * For more information, consult the wiki.
 */
@RunWith(classOf[JUnitRunner])
class ApplicationSpec extends Specification {

  "playPOC Application" should {


    "render the index page" in new WithApplication{
      val home = route(FakeRequest(GET, "/")).get

      status(home) must equalTo(OK)
      contentType(home) must beSome.which(_ == "text/html")
      contentAsString(home) must contain ("Your new application is ready.")
    }

    "must process 10 json element on /Bundles " in new WithApplication {
      val jsonObjbect = Json.arr(for {
        i <- 1 to 10
        element = Json.obj("id" -> i.toDouble, "description" -> "case" , "cost" -> 10)
      } yield(element))

      println(jsonObjbect.toString)

      val request = route(FakeRequest(POST,"/bundles").withJsonBody(jsonObjbect)).get
      status(request) must equalTo(OK)
    }


    "render a json object with a thousand elements" in new WithApplication{
      val bundlesRequest = route(FakeRequest(GET, "/bundles")).get
      status(bundlesRequest) must equalTo(OK)
      contentType(bundlesRequest) must beSome.which( _ == "application/json")
      val jsonObject = contentAsJson(bundlesRequest)
      jsonObject.\("total_count") must not be None
      jsonObject.\("total_count") must beEqualTo(1000)
      jsonObject.\("data") must not be None
      jsonObject.\\("data").size must beEqualTo(1000)
    }
  }
}
