package sealedClassHomeWork


sealed class ResultResponse {
    class Failure(val exception: Exception) : ResultResponse()
    class Success(val data: Any) : ResultResponse()
    object Loading: ResultResponse()
}
