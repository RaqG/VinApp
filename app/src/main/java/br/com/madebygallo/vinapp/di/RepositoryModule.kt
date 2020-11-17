package br.com.madebygallo.vinapp.di

import br.com.madebygallo.vinapp.data.VinAppRepository
import br.com.madebygallo.vinapp.database.VinAppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

/**
 * Created by RaqG on 14/07/2020.
 */

@Module
@InstallIn(ActivityRetainedComponent::class)
object RepositoryModule {

    @Provides
    @ActivityRetainedScoped
    fun provideVinAppRepository(
        vinAppDatabase: VinAppDatabase
    ): VinAppRepository {
        return VinAppRepository(
            vinAppDatabase
        )
    }
}