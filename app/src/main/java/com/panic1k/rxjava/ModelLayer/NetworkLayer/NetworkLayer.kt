package com.panic1k.rxjava.ModelLayer.NetworkLayer


import com.panic1k.rxjava.Common.StringLambda
import com.panic1k.rxjava.Common.VoidLambda
import com.panic1k.rxjava.DataLayer.NetworkLayer.EndpointInterfaces.JsonPlaceHolder
import com.panic1k.rxjava.DataLayer.NetworkLayer.Helpers.ServiceGenerator
import com.panic1k.rxjava.ModelLayer.Entities.Message
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException


typealias MessageLambda = (Message?)->Unit
typealias MessagesLambda = (List<Message>?)->Unit

class NetworkLayer {
    companion object { val instance = NetworkLayer() }

    private val placeHolderApi: JsonPlaceHolder

    init {
        placeHolderApi = ServiceGenerator.createService(JsonPlaceHolder::class.java)
    }


    //region End Point - SemiRx Way

    fun getMessages(success: MessagesLambda, failure: StringLambda) {
        val call = placeHolderApi.getMessages()

        call.enqueue(object: Callback<List<Message>> {
            override fun onResponse(call: Call<List<Message>>?, response: Response<List<Message>>?) {
                val article = parseResponse(response)
                success(article)
            }

            override fun onFailure(call: Call<List<Message>>?, t: Throwable?) {
                println("Failed to GET Message: ${ t?.message }")
                failure(t?.localizedMessage ?: "Unknown error occurred")
            }
        })
    }

    fun getMessage(articleId: String, success: MessageLambda, failure: VoidLambda) {
        val call = placeHolderApi.getMessage(articleId)

        call.enqueue(object: Callback<Message> {
            override fun onResponse(call: Call<Message>?, response: Response<Message>?) {
                val article = parseResponse(response)
                success(article)
            }

            override fun onFailure(call: Call<Message>?, t: Throwable?) {
                println("Failed to GET Message: ${ t?.message }")
                failure()
            }
        })
    }

    fun postMessage(message: Message, success: MessageLambda, failure: VoidLambda) {
        val call = placeHolderApi.postMessage(message)

        call.enqueue(object: Callback<Message>{
            override fun onResponse(call: Call<Message>?, response: Response<Message>?) {
                val article = parseResponse(response)
                success(article)
            }

            override fun onFailure(call: Call<Message>?, t: Throwable?) {
                println("Failed to POST Message: ${ t?.message }")
                failure()
            }
        })
    }

    private fun <T> parseResponse(response: Response<T>?): T? {
        val article = response?.body()

        if (article == null) {
            parseResponseError(response)
        }

        return article
    }

    private fun <T> parseResponseError(response: Response<T>?) {
        if(response == null) return //can't do anything here

        val responseBody = response.errorBody()

        if(responseBody != null) {
            try {
                val text = "responseBody = ${ responseBody.string() }"
                println("$text")
            } catch (e: IOException) {
                e.printStackTrace()
            }
        } else {
            val text = "responseBody == null"
            println("$text")
        }
    }

    //endregion

}