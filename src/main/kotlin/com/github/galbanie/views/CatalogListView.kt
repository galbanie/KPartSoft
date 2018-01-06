package com.github.galbanie.views

import com.github.galbanie.models.Catalog
import tornadofx.*

/**
 * Created by Galbanie on 18-01-01.
 */
class CatalogListView : View("Catalogs") {
    override val root = listview<Catalog> {

    }
}
