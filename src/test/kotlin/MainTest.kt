import com.apollographql.apollo3.annotations.ApolloExperimental
import com.apollographql.apollo3.annotations.ApolloInternal
import com.apollographql.apollo3.ast.*
import java.io.File
import kotlin.test.Test
import kotlin.test.assertEquals

class MainTest {
  @Test
  fun convertSchema() {
    File("schema.json").toGQLDocument(allowJson = true)
      .toUtf8()
      .let {
        File("schema.graphqls").writeText(it)
      }
  }

  val schema = File("schema.json").toGQLDocument(allowJson = true)
    .toSchema()

  @OptIn(ApolloInternal::class, ApolloExperimental::class)
  @Test
  fun validate() {
    val issues = File("bigQuery.graphql").toGQLDocument().validateAsExecutable(schema).issues

    print(issues)
  }
}