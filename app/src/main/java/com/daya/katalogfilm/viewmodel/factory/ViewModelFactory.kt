package com.daya.katalogfilm.viewmodel.factory

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModel
import com.daya.katalogfilm.viewmodel.DetailViewModel
import com.daya.katalogfilm.viewmodel.FavoriteViewModel
import com.daya.katalogfilm.viewmodel.RepoViewModel


class ViewModelFactory(private val application: Application) : ViewModelProvider.NewInstanceFactory() {

    companion object{
        @Volatile
        private var INSTANCE: ViewModelFactory? = null


        fun getInstance(application: Application): ViewModelFactory? {
            if (INSTANCE == null) {
                synchronized(ViewModelFactory::class) {
                    if (INSTANCE == null) {
                        INSTANCE  = ViewModelFactory(application)
                    }
                }
            }
            return INSTANCE
        }
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        when{
            modelClass.isAssignableFrom(RepoViewModel::class.java) ->{
                return RepoViewModel() as T
            }
            modelClass.isAssignableFrom(DetailViewModel::class.java) ->{
                return DetailViewModel() as T
            }
            modelClass.isAssignableFrom(FavoriteViewModel::class.java) ->{
                return FavoriteViewModel(application) as T
            }

            else ->{
                throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
            }
        }
    }

}