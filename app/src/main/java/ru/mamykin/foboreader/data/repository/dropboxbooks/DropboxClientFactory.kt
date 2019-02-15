package ru.mamykin.foboreader.data.repository.dropboxbooks

import com.dropbox.core.DbxRequestConfig
import com.dropbox.core.http.OkHttp3Requestor
import com.dropbox.core.v2.DbxClientV2
import rx.Completable
import javax.inject.Inject

class DropboxClientFactory @Inject constructor() {

    private var client: DbxClientV2? = null

    fun getClient(): DbxClientV2 = client ?: throw IllegalStateException("Client not initialized")

    fun init(accessToken: String): Completable = Completable.fromCallable {
        if (client == null) {
            val requestConfig = DbxRequestConfig.newBuilder("FoBo Reader")
                    .withHttpRequestor(OkHttp3Requestor.INSTANCE)
                    .build()

            client = DbxClientV2(requestConfig, accessToken)
        }
    }
}