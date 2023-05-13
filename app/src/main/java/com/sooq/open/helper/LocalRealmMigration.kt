package com.sooq.open.helper

import com.sooq.open.feature.data.localdata.CategoryLocalModel
import com.sooq.open.feature.data.localdata.SubCategoryLocalModel
import io.realm.DynamicRealm
import io.realm.RealmMigration
import io.realm.RealmSchema

class LocalRealmMigration : RealmMigration {
    override fun migrate(realm: DynamicRealm, oldVersion: Long, newVersion: Long) {
        migration1to2(realm.schema)
    }

    private fun migration1to2(schema: RealmSchema?) {
        val ownerSchema = schema?.get(CategoryLocalModel::class.java.name)
        val subCategorySchema = schema?.get(SubCategoryLocalModel::class.java.name)

        // 4.
        subCategorySchema?.let {
            ownerSchema?.addRealmListField("owner", it)
        }
    }
}