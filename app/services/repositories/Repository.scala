package services.repositories

import utils.CustomError

/**
 * Created by diego on 1/31/14.
 */
trait Repository { self: Persistent =>
  def getById(id: Long) : Option[Persistent]
  def getByName(name: String) : Option[Persistent]
  def getAll : List[Persistent]
  def save : Option[Persistent]
  def delete : Either[CustomError, Persistent]
}