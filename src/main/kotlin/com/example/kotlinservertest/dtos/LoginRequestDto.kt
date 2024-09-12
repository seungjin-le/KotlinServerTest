import io.swagger.v3.oas.annotations.media.Schema

data class LoginRequestDto(

    @field:Schema(description = "이메일")
    val email: String,
    @field:Schema(description = "패스워드")
    val password: String?
)