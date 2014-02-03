package services.repositories

import models.Models.Bundle
import utils.CustomError

/**
 * Created by diego on 1/31/14.
 */
object MemoryStorage {

  implicit class BundleMemory(bundle: Bundle) extends Repository[Bundle]{
    override def delete: Either[CustomError, Persistent] = ???

    override def save: Option[Persistent] = ???

    override def getAll: List[Persistent] = ???

    override def getByName(name: String): Option[Persistent] = ???

    override def getById(id: Long): Option[Persistent] = ???
  }
}