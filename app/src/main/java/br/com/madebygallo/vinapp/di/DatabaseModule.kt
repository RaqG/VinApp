package br.com.madebygallo.vinapp.di

import android.content.Context
import br.com.madebygallo.vinapp.database.VinAppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext

/**
 * Created by RaqG on 14/07/2020.
 */

@Module
@InstallIn(ApplicationComponent::class)
object DatabaseModule {

    @Provides
    fun providesDatabase(@ApplicationContext context: Context): VinAppDatabase {
        return VinAppDatabase.getInstance(context)
    }
}