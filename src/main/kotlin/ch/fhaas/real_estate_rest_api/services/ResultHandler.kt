package ch.fhaas.real_estate_rest_api.services

import ch.fhaas.real_estate_rest_api.entity.Entity
import ch.fhaas.real_estate_rest_api.printErrln
import org.springframework.stereotype.Service

@Service
class ResultHandler {

    fun <T> getEntityOfResult(result: Result<T>): T? {
        result.onSuccess {
            result.getOrNull()
        }.onFailure {
            printErrln(it)
            return null
        }
        return result.getOrNull()
    }

    fun <T : Entity> getListOfResult(resultList: List<Result<T>>): List<T> =
        resultList.fold(emptyList()) { list, index ->
            val entity: T? = getEntityOfResult(index)
            entity?.let { list + it } ?: list
        }
}