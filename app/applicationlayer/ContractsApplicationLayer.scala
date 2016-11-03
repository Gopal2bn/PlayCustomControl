package applicationlayer

import models.contracts._

/**
  * Created by root on 10/31/16.
  */
class ContractsApplicationLayer {
def SearchContracts(serachCriteria: SearchCriteria) : Seq[SearchResult] = {
val searchResult=SearchResult("Caremark MCO","CVS Caremark MCO 2015","CARMK2015","1","Active","1/1/2015","7/31/2015")
  val searchResult1=SearchResult("Caremark MCO","CVS Caremark MCO 2015","CARMK2015","1","Active","1/1/2015","7/31/2015")
  val results = Seq(searchResult,searchResult1)
  results
}
}

