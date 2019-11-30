package com.daya.katalogfilm.util

import androidx.paging.PagedList
import org.mockito.ArgumentMatchers
import org.mockito.Mockito
import org.mockito.Mockito.`when`

fun <T> mockPagedList(list: List<T>): PagedList<T> {
    val pagedList = Mockito.mock(PagedList::class.java) as PagedList<T>
    `when`(pagedList[ArgumentMatchers.anyInt()]).then { invocation ->
        val index = invocation.arguments.first() as Int
        list[index]
    }
    `when`(pagedList.size).thenReturn(list.size)
    return pagedList
}