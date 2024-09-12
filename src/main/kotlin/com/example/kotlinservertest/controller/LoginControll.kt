import io.swagger.annotations.Api
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.ArraySchema
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import jakarta.validation.Valid
import org.springdoc.core.annotations.ParameterObject
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController




@Api(tags = ["Login"], description = "로그인 컨트롤러")
@RestController
@RequestMapping("api/v1/login")
class LoginController {
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Success", content = [
            Content(mediaType = "application/json", array = ArraySchema(schema = Schema(implementation = Any::class)))
        ]),
        ApiResponse(responseCode = "202", description = "사용하지 않는 파라미터", content = [Content()])
    ])
    @Operation(summary = "Get Test API", description = "This is GET API")
    @PostMapping
    fun postLogin(@Valid @ParameterObject getRequestDto: LoginRequestDto, bindingResult: BindingResult): Any {
        return {}
    }
}