package com.example.e_shop.di

import android.content.Context
import androidx.room.Room
import com.example.e_shop.database.ShopDatabase
import com.example.e_shop.utils.Constants.Companion.ROOM_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {


    @Singleton
    @Provides
    fun providesDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        ShopDatabase::class.java,
        ROOM_DATABASE
    ).fallbackToDestructiveMigration().build()
    // Don't use destructive migration in production app

    @Singleton
    @Provides
    fun providesProductDao(
        database: ShopDatabase
    ) = database.productDao()

    @Singleton
    @Provides
    fun providesCartDao(
        database: ShopDatabase
    ) = database.cartDao()
}