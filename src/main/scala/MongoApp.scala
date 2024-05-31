import com.mongodb.{ServerApi, ServerApiVersion}
import org.mongodb.scala.{ConnectionString, MongoClient, MongoClientSettings, MongoDatabase, MongoCollection}
import org.mongodb.scala.Observable
import org.mongodb.scala.bson.Document

import nosql.Helpers._

object MongoApp {
  
  def connect(connectionString: String): MongoClient = {
    val serverApi = ServerApi.builder.version(ServerApiVersion.V1).build()

    val settings = MongoClientSettings
      .builder()
      .applyConnectionString(ConnectionString(connectionString))
      .serverApi(serverApi)
      .build()

    MongoClient(settings)
  }

  def getWorkplaceId(name: String)(implicit database: MongoDatabase): String = {
    val collection: MongoCollection[Document] = database.getCollection("workplace")
    collection.find(Document("name" -> name))
      .headResult()
      .getString("_id")
  }

  /* Question 1
   * All employees that are less than or equal to 50 and like Cooking
   */
  def question1()(implicit database: MongoDatabase) = {
    val collection: MongoCollection[Document] = database.getCollection("employee")
    val filter =  Document("age" -> Document("$lte" -> 50), "interests" -> "Cooking")

    collection.find(filter)
      .printResults()
  }
  
  /* Question 2 
   * Insert a new Employee
   */
  def question2()(implicit database: MongoDatabase) = {
    val collection: MongoCollection[Document] = database.getCollection("employee")
    val jsonString = """
    {
      "firstname": "Jake",
      "lastname": "Sample",
      "age": 26,
      "email": "jakesample@email.com",
      "interests": ["Biking", "Hiking"],
      "address_id": "91b5b7b3-2309-4e8a-8247-cd66d626ef0c",
      "workplace_id": "5345fcb9-6297-4b9f-aa15-cbee8460f28f"
    }
    """
    val document = Document(jsonString)
    collection.insertOne(document)
      .printResults()

  }
  
  /* Question 3 
   * Delete all employees that work for 'Great Plains Energy Inc.'
   *  and are greater than 46 years old and likes 'Tennis'.
   */
  def question3()(implicit database: MongoDatabase) = {
    val collection: MongoCollection[Document] = database.getCollection("employee")
    val workplace_id: String = getWorkplaceId("Great Plains Energy Inc.")
    val filter =  Document("age" -> Document("$gt" -> 46),
                           "interests" -> "Tennis",
                           "workplace_id" -> workplace_id)

    collection.deleteMany(filter)
      .printResults()
  }


  /* Question 4 
   * Add a new field called 'industry' to all employees that work for 'Health Net Inc.'  
   *  nd populate the field with the value 'Health Care'. 
   */
  def question4()(implicit database: MongoDatabase) = {
    val collection: MongoCollection[Document] = database.getCollection("employee")
    val workplace_id: String = getWorkplaceId("Health Net Inc.")
    val filter = Document("workplace_id" -> workplace_id)
    val set = Document("$set" -> Document("industry" -> "Health Care"))

    collection.updateMany(filter, set)
      .printResults()
  }


  /* Question 5 
   * Create an aggregate query to count the number of employees for each company
   *  and sort the output from largest employee count to lowest employee count 
   */
  def question5()(implicit database: MongoDatabase) = {
    import org.mongodb.scala.model.Sorts._
    import org.mongodb.scala.model.Aggregates._
    import org.mongodb.scala.model.Accumulators._

    val collection: MongoCollection[Document] = database.getCollection("employee")
    val pipeline = Seq(
      lookup("workplace", "workplace_id", "_id", "workplaceInfo"),
      group("$workplaceInfo.name", sum("count", 1)),
      sort(descending("count"))
    )

    collection.aggregate(pipeline)
      .printResults()
  }

  def main(args: Array[String]): Unit = {
    val connectionString = "mongodb://localhost:27017";
    val client: MongoClient = connect(connectionString)
    implicit val database: MongoDatabase = client.getDatabase("payroll")

    question1()
    question2()
    question3()
    question4()
    question5()

    client.close()
  }
}
