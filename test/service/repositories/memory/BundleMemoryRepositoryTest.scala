package services.repositories.memory

import org.specs2.Specification
import models.Models.{EmptyBundle, Bundle}

/**
 * Created by diego on 1/31/14.
 */
class BundleMemoryRepositoryTest extends Specification{
  import services.repositories.MemoryStorage.BundleMemory

  val newBundle = Bundle(1L, "RaspberryPi case", 12.78 )

  def is = s2"""
  $sequential
              createBundle  $createBundle
              saveBundle    $saveBundle
              getBundleById $getBundleById
              updateBundle  $updateBundle
              getByName     $getByName
              getAll        $getAll
              deleteBundle  $deleteBundle

  """

  def createBundle = newBundle must not be None

  def saveBundle = newBundle.save match {
    case Some(bundle) => success
    case None => failure
  }

  def getBundleById = newBundle.getById(1L) match {
    case Some(bundle) => success
    case None => failure
  }


  def updateBundle = {
    val anotherBundle = Bundle(1L, "beagleboard", 10.50)
    anotherBundle.save
    anotherBundle.getById(1L).getOrElse(Bundle(0L,"",0)).description must beEqualTo("beagleboard")
  }

  def getByName = newBundle.getByName("beagleboard").getOrElse(Bundle(0L,"",0)).description must beEqualTo("beagleboard")

  def deleteBundle =
    (newBundle.delete match {
      case Right(bundle) => {
        newBundle.getById(1L).getOrElse(Bundle(0L,"",0))
      }
      case Left(error) => {
        println(error.message)
        Bundle(0L,"",0)
      }
    }).id must beEqualTo(0L)

  def getAll = newBundle.getAll must not be empty
}
