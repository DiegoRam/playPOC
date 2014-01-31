package models

import org.specs2.mutable._
import models.Models.{ResponseObject, Bundle}

/**
 * Created by diego on 1/31/14.
 */
class ModelTest extends Specification {

  "Bundle" should {
    "must have  an id" in {
      Bundle(1L, "RaspBerryPi Case", 10.50).id must beEqualTo(1L)
    }

    "must have a description " in {
      Bundle(1L, "case" , 13.5).description must beEqualTo("case")
    }

    "must have a price" in {
      Bundle(1L, "case", 15.5).cost must beEqualTo(15.5)
    }
  }

  "ResponseObject" should {
    "must have a totalCount" in {
      ResponseObject(2, "http://localhost:9000/bundles", Vector(Bundle(1L, "case", 10.4), Bundle(1L, "case", 10.4)))
      .totalCount must beEqualTo(2)
    }

    "must have a self field" in {
      ResponseObject(2, "http://localhost:9000/bundles", Vector(Bundle(1L, "case", 10.4), Bundle(1L, "case", 10.4)))
      .self must beEqualTo("http://localhost:9000/bundles")
    }

    "must have a list of bundles" in {
      ResponseObject(2, "http://localhost:9000/bundles", Vector(Bundle(1L, "case", 10.4), Bundle(1L, "case", 10.4)))
      .data must have size(2)
    }
  }


}
