import com.amazonaws.auth.profile.ProfileCredentialsProvider
import com.amazonaws.regions.Regions
import com.amazonaws.services.cloudformation.AmazonCloudFormationClientBuilder
import com.amazonaws.services.cloudformation.model.CreateStackRequest
import org.apache.commons.io.FileUtils
import java.io.File
import java.nio.charset.Charset

class AWSDemo {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            AWSDemo().createCloudFormationStack(args[0])
        }
    }

    fun createCloudFormationStack(newStackName: String) {
        val credentialsProvider = ProfileCredentialsProvider()
        val client = AmazonCloudFormationClientBuilder
                .standard()
                .withCredentials(credentialsProvider)
                .withRegion(Regions.US_EAST_1)
                .build()

        val templateFile = File(javaClass.classLoader.getResource("WordPress.template.json").file)
        val template = FileUtils.readFileToString(templateFile, Charset.defaultCharset())

        val request = CreateStackRequest()
        request.stackName = newStackName
        request.templateBody = template

        val response = client.createStack(request)
        System.out.println("StackID: " + response.stackId)
    }

    fun createCloudFormationStack2(newStackName: String) {
        val client = ProfileCredentialsProvider().run {
            AmazonCloudFormationClientBuilder
                    .standard()
                    .withCredentials(this)
                    .withRegion(Regions.EU_WEST_1)
                    .build()
        }

        val template = File(javaClass.classLoader.getResource("WordPress.template.json").file).run {
            FileUtils.readFileToString(this, Charset.defaultCharset())
        }

        CreateStackRequest().apply {
            stackName = newStackName
            templateBody = template
        }.run {
            client.createStack(this)
        }.apply {
            System.out.println("StackID: " + stackId)
        }
    }
}