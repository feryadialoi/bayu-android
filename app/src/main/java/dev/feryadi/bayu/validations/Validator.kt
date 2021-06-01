package dev.feryadi.bayu.validations

import dev.feryadi.bayu.utils.StringUtil
import dev.feryadi.bayu.validations.rules.annotations.*
import id.turbin.suppliable.suppliableposmobile.validations.ValidationError
import javax.inject.Inject
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.memberProperties

@Suppress("UNCHECKED_CAST")
class Validator @Inject constructor() {
    fun <T> validate(
        data: Any,
        onError: (textErrors: List<ValidationError>) -> Unit,
        onValid: (data: T) -> Unit
    ) {
        val clazz = data::class
        val properties = clazz.memberProperties
        val errors = mutableListOf<ValidationError>()
        for (property in properties) {
            val label: String = property.findAnnotation<Label>()?.value ?: property.name

            if (property.findAnnotation<NotEmpty>() != null) {
                val value: String = property.call(data) as String
                if ("" == value) {
                    errors.add(
                        ValidationError(
                            name = property.name,
                            textError = "$label is empty"
                        )
                    )
                }
            }
            if (property.findAnnotation<Min>() != null) {
                val value: String = property.call(data) as String
                val minValue: Int = property.findAnnotation<Min>()!!.value
                if (value.length < minValue) {
                    errors.add(
                        ValidationError(
                            name = property.name,
                            textError = "$label minimal character is $minValue"
                        )
                    )
                }
            }
            if (property.findAnnotation<Email>() != null) {
                val value: String = property.call(data) as String
                if (!StringUtil.isEmailValid(email = value)) {
                    errors.add(
                        ValidationError(
                            name = property.name,
                            textError = "$label format is invalid"
                        )
                    )
                }
            }

            if (property.findAnnotation<Equal>() != null) {
                val valueOfEqual = property.findAnnotation<Equal>()!!.value
                val value: String = property.call(data) as String
                val valueOfPropertyToCompare =
                    properties.first { p -> p.name == valueOfEqual }.call(data) as String
                if (value != valueOfPropertyToCompare) {
                    errors.add(
                        ValidationError(
                            name = property.name,
                            textError = "$label doesn't match"
                        )
                    )
                }
            }
        }
        if (errors.size == 0) {
            onValid(data as T)
        } else {
            onError(errors)
        }
    }
}