package com.kevinroditi.pokemonapp_kevinroditi.core.util


/**
 * Sealed wrapper class to represent UI state
 * I have used this to:
 *  - Enforce explicit handling of Loading, Success and Error state
 *  - Prevents ambiguous nullable states in VM
 *
 * Usage:
 *  - Returned by UseCases
 *  - Observed by VM
 *  - Collected in Composable
 */


sealed class Resource<out T> {
    //Success
    data class Success<out T>(val value: T) : Resource<T>()

    //Error
    data class Error(
        val message: String,
        val throwable: Throwable? = null
    ) : Resource<Nothing>()

    //Loading
    object Loading : Resource<Nothing>()

}