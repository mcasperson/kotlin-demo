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
            AWSDemo().createCloudFormationStack2(args[0])
        }
    }

    /**
     * Function that does not use the standard library functions
     */
    fun createCloudFormationStack(newStackName: String) {
        val credentialsProvider = ProfileCredentialsProvider()
        val client = AmazonCloudFormationClientBuilder
                .standard()
                .withCredentials(credentialsProvider)
                .withRegion(Regions.US_EAST_1)
                .build()

        val templatePath = javaClass.classLoader.getResource("WordPress.template.json").file
        val templateFile = File(templatePath)
        val template = FileUtils.readFileToString(templateFile, Charset.defaultCharset())

        val request = CreateStackRequest()
        request.stackName = newStackName
        request.templateBody = template

        val response = client.createStack(request)
        System.out.println("StackID: " + response.stackId)

        // Code Shape
        /*
        val credentialsProvider = ...
        val client = ...

        val templateFile = ...
        val template = ...

        val request = ...

        val response = ...
        */
    }

    /**
     * Function rewritten to use standard functions
     */
    fun createCloudFormationStack2(newStackName: String) {
        val client = ProfileCredentialsProvider().let { credentials ->
            AmazonCloudFormationClientBuilder
                    .standard()
                    .withCredentials(credentials)
                    .withRegion(Regions.US_EAST_1)
                    .build()
        }

        val template = javaClass.classLoader.getResource("WordPress.template.json").file.let { path ->
            File(path)
        }.let { templateFile ->
            FileUtils.readFileToString(templateFile, Charset.defaultCharset())
        }

        CreateStackRequest().also { request ->
            request.stackName = newStackName
            request.templateBody = template
        }.let { request ->
            client.createStack(request)
        }.also { response ->
            System.out.println("StackID: " + response.stackId)
        }

        // Code Shape
        /*
        val client = ... .let { credentials ->
            ...
        }

        val template = ... .let { path ->
            ...
        }.let { templateFile ->
            ...
        }

        ... .also { request ->
            ...
        }.let { request ->
            ...
        }.also { response ->
            ...
        }
         */
    }
}