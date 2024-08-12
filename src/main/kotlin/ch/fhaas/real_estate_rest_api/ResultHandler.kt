package ch.fhaas.real_estate_rest_api

import ch.fhaas.real_estate_rest_api.entity.Entity
import org.springframework.stereotype.Service

@Service
class ResultHandler {

    fun <T : Entity> entityOf(result: Result<T>): T? {
        result.onSuccess {
            result.getOrNull()
        }.onFailure {
            printErrln(it)
            return null
        }
        return result.getOrNull()
    }

    fun <T : Entity> entityListOf(resultList: List<Result<T>>): List<T> =
        resultList.fold(emptyList()) { list, index ->
            val entity: T? = entityOf(index)
            entity?.let { list + it } ?: list
        }
}