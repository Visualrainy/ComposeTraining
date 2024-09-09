package com.example.composetraining

class DefaultPaginator<K, T>(
    private val initialKey: K,
    private inline val onLoadUpdated: (Boolean) -> Unit,
    private inline val onRequest: suspend (K) -> Result<List<T>>,
    private inline val getNextKey: suspend (List<T>) -> K,
    private inline val onError: suspend (Throwable?) -> Unit,
    private inline val onSuccess: suspend (items: List<T>, newKey: K) -> Unit
) : Paginator<K, T> {
    private var currentKey: K = initialKey
    private var isMakingRequest = false

    override suspend fun loadNextItems() {
        if (isMakingRequest) return

        isMakingRequest = true
        onLoadUpdated(true)
        val result = onRequest(currentKey)
        isMakingRequest = false
        val items = result.getOrElse {
            onError(it)
            onLoadUpdated(false)
            return
        }
        currentKey = getNextKey(items)
        onSuccess(items, currentKey)
        onLoadUpdated(false)
    }

    override fun reset() {
        currentKey = initialKey
    }
}