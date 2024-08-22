package ch.fhaas.bobs_burger_api.services

import ch.fhaas.bobs_burger_api.entity.Entity
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class ResponseFormer(private val resultHandler: ResultHandler) {

    fun <T : Entity> entityResponseOf(result: T?): ResponseEntity<T> {
        if (result == null) return ResponseEntity(null, HttpStatus.BAD_REQUEST)
        return ResponseEntity(result, HttpStatus.OK)
    }
}