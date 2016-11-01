package controllers

import play.api._
import play.api.mvc._
import play.api.i18n._
import play.api.data.Form
import play.api.data.Forms._
import play.api.data.validation.Constraints._
import play.api.libs.json.Json
import models._
import views.formdata.ContractSearchCriteriaFormData

import scala.concurrent.{ExecutionContext, Future}

import javax.inject._

/**
  * Created y gopal on 10/29/16.
  */
@Singleton
class ContractsController @Inject()(val messagesApi: MessagesApi)
                                   (implicit ec: ExecutionContext) extends Controller with I18nSupport {
  val searchCriteriaForm: Form[ContractSearchCriteriaFormData] = Form {
    mapping(
      "contractName" -> nonEmptyText,
      "contractID" -> nonEmptyText,
      "contractType" -> nonEmptyText,
      "version" -> nonEmptyText,
      "category" -> nonEmptyText,
      "contractEntity" -> nonEmptyText
    )(ContractSearchCriteriaFormData.apply)(ContractSearchCriteriaFormData.unapply)
  }
  val contractEntityList=Map[String,String]("CMM" -> "Caremark MCO", "EXM" ->"Express Scripts MCO","CIG" -> "Cigna MMA")// List ("CaremarkMCO", "ExpressScriptsMCO","CignaMMA")
  val contractCategoryList=Map[String,String]("ACT" -> "Active", "IACT" ->"Inactive","HDN" -> "Hidden","SMP"->"Sample")
  /**
    * The index action.
    */
  def search = Action {
    Ok(views.html.contracts.contractsSearch(searchCriteriaForm,contractEntityList,contractCategoryList))
  }

  def search1 = Action { implicit request =>
    searchCriteriaForm.bindFromRequest.fold(
      errors => BadRequest(views.html.contracts.contractsSearch(searchCriteriaForm,contractEntityList,contractCategoryList)),
      label => {
        Redirect(routes.ContractsController.search())
      }
    )
  }
}
