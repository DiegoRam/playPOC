package services.repositories

import models.Models.Bundle
import utils.CustomError

/**
 * Created by diego on 1/31/14.
 */
object MemoryStorage {
  var memoryListBundles = Vector[Bundle]()

  implicit class BundleMemory(bundle: Bundle) extends Repository[Bundle]{

    override def delete: Either[CustomError, Bundle] = {
      try {
       memoryListBundles = memoryListBundles.filter(element => element.id != bundle.id)
       Right(bundle)
      } catch {
        case e: Exception => Left(CustomError("Failure deleting element"))
      }
    }

    override def save: Option[Bundle] = {
      memoryListBundles filter (another => another.id == bundle.id) match {
        case Seq(prevBundle, xs @ _*) => {
          memoryListBundles = bundle +: memoryListBundles.filter ( another => another.id != bundle.id)
          Some(bundle)
        }
        case Seq() => {
          memoryListBundles = bundle +: memoryListBundles
          Some(memoryListBundles.head)
        }
      }
    }

    override def getAll: List[Bundle] = memoryListBundles.toList

    override def getByName(name: String): Option[Bundle] = Some(memoryListBundles.filter ( another => another.description == name) head)

    override def getById(id: Long): Option[Bundle] = memoryListBundles.filter ( another => another.id == id) match {
      case Seq(head, tail @ _*) => Some(head)
      case Seq() => None
    }
  }
}