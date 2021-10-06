package by.rodkin.task5

import androidx.paging.PagingSource
import androidx.paging.PagingState
import by.rodkin.task5.model.Cat
import by.rodkin.task5.retrofit.RetrofitService
import java.lang.Exception

class PagingSource(private val retrofitService: RetrofitService): PagingSource<Int, Cat>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int,Cat> {
        return try {
            val count = params.key ?: 1
            val loadSize = params.loadSize
            val response = retrofitService.loadImages(loadSize, count)
            LoadResult.Page(
                data = response,
                prevKey = null,
                nextKey = count + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Cat>): Int? = null

}