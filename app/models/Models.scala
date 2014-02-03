package models

import services.repositories.Persistent

/**
 * Created by diego on 1/31/14.
 */
object Models {

  case class Bundle(id: Long, description: String, cost: Double) extends Persistent
  case class EmptyBundle(id: Long = 0L, description: String = "", cost: Double = 0)
  case class ResponseObject(totalCount: Int = 0, self: String = "", data: Vector[Bundle])

}
