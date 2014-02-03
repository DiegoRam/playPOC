package services.repositories.memory

import org.specs2.Specification
import models.Models.Bundle

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
              updateBundle  $updateBundle
              deleteBundle  $deleteBundle

  """

  def createBundle = newBundle must not be None

  def saveBundle = newBundle.save.map()


  def updateBundle = {pending}
  def deleteBundle = {pending}

}
