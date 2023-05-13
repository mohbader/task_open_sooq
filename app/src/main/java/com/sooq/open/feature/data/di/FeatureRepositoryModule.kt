package com.sooq.open.feature.data.di

import com.sooq.open.feature.data.FeatureRepositoryImp
import com.sooq.open.feature.domain.FeatureRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
interface FeatureRepositoryModule {

    @Binds
    fun bindFeatureRepoStoryModule(featureRepository: FeatureRepositoryImp): FeatureRepository
}