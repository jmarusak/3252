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

  def ingest()(implicit database: MongoDatabase) = {
    val collection: MongoCollection[Document] = database.getCollection("employee")

    collection.find().printResults()
  }

  def main(args: Array[String]): Unit = {
    val connectionString = "mongodb://localhost:27017";
   
    val client: MongoClient = connect(connectionString)
    implicit val database: MongoDatabase = client.getDatabase("payroll")
    ingest()

    // close connection
    client.close()
  }
}
