package br.com.joffer.mango

import java.lang.reflect.Field

object TestHelper {

    @Throws(
        SecurityException::class,
        NoSuchFieldException::class,
        ClassNotFoundException::class,
        IllegalArgumentException::class,
        IllegalAccessException::class
    )
    fun setInstanceValue(classInstance: Any, fieldName: String, newValue: Any) {
        val field = classInstance.javaClass.getDeclaredField(fieldName)
        field.isAccessible = true
        field.set(classInstance, newValue)
    }

    @Throws(
        SecurityException::class,
        NoSuchFieldException::class,
        IllegalArgumentException::class,
        IllegalAccessException::class
    )
    fun setStaticValue(clazz: Class<*>, fieldName: String, newValue: Any) {

        val field = clazz.getDeclaredField(fieldName)
        field.isAccessible = true

        field.set(null, newValue)
    }
}
