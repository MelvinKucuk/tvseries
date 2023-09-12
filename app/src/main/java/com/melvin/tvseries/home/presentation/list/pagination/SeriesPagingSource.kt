package com.melvin.tvseries.home.presentation.list.pagination

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.melvin.tvseries.home.data.SeriesService
import com.melvin.tvseries.home.data.model.toDomain
import com.melvin.tvseries.home.domain.Series

class SeriesPagingSource(
    private val seriesService: SeriesService,
): PagingSource<Int, Series>() {

    override fun getRefreshKey(state: PagingState<Int, Series>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Series> {
        return try {
            val page = params.key ?: 0
            val response = seriesService.getSeries(page = page)

            LoadResult.Page(
                data = response.body()?.map { it.toDomain() } ?: listOf(),
                prevKey = if (page == 0) null else page.minus(1),
                nextKey = if (response.code() == 404) null else page.plus(1),
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}